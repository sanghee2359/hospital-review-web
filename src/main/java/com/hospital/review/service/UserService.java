package com.hospital.review.service;

import com.hospital.review.domain.User;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewAppException;
import com.hospital.review.repository.UserRepository;
import com.hospital.review.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}") // springframework
    private String key; // 키
    private Long expireTimeMs = 1000 * 60 * 60l; // expireTime : 1hour
    // userName 가져오기
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED, ""));
        return user;
    }

    public String join(String userName, String password) {
        // userName이 이미 존재함
        userRepository.findByUserName(userName)
                .ifPresent(user->{
                    throw new HospitalReviewAppException(ErrorCode.USERNAME_DUPLICATED,userName+"은 이미 존재합니다.");
                });

        User user= User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        // save
        userRepository.save(user);
        return "SUCCESS";
    }

    public  String login(String userName, String password){
        // userName 없을경우
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.USERNAME_NOTFOUND, userName+"이 없습니다."));
        // password 틀릴경우
        if(!encoder.matches(password, selectedUser.getPassword())) {
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD,password+"가 틀렸습니다.");
        }
        // 예외가 없다면 토큰 발행
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(),key,expireTimeMs);
        return token;
    }
}
