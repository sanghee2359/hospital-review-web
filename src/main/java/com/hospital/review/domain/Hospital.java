package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hospital {
    @Id
    private Long id;
    private String hospitalName;
    private String roadNameAddress;

    @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY) // 나중에 getReview를 할 때 쿼리가 날아간다.
    private List<Review> reviews;
}
