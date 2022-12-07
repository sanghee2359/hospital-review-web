package com.hospital.review.service;

import com.hospital.review.domain.Disease;
import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.User;
import com.hospital.review.domain.Visit;
import com.hospital.review.domain.dto.VisitCreateRequest;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewAppException;
import com.hospital.review.repository.DiseaseRepository;
import com.hospital.review.repository.HospitalRepository;
import com.hospital.review.repository.UserRepository;
import com.hospital.review.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final DiseaseRepository diseaseRepository;



    public void createVisit(VisitCreateRequest dto, String userName, String code) {
        // hospital
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.HOSPITAL_NOT_FOUNDED,
                        String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED,
                        String.format("user : %s은 없습니다.", userName)));

        // 질병
        /*Disease disease = diseaseRepository.findByCode(code)
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.DISEASE_NOT_FOUNDED,
                        String.format("해당 %s 질병은 없습니다.", code)));
*/
        // visit
        Visit visit = Visit.builder()
                .userId(user)
                .hospitalId(hospital)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();

        // visit 저장
        visitRepository.save(visit);
    }
}
