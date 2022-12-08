package com.hospital.review.domain;

import com.hospital.review.domain.dto.VisitResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    private Hospital hospitalId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    private String disease;

    private float amount; //진료비
    public VisitResponse toResponse() {
        return VisitResponse.builder()
                .hospitalName(this.hospitalId.getHospitalName())
                .userName(this.userId.getUserName())
                .disease(this.disease)
                .amount(this.amount)
                .build();
    }
}