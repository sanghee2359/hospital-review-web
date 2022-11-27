# Endpoints
### 병원 리뷰 생성기능 && 특정 병원의 리뷰를 조회하는 기능
| 구분 | 기능 | METHOD | API |
| --- | --- | --- | --- |
| 병원 리뷰 생성 | 리뷰 생성 | POST | /api/v1/hospitals/{id}/reviews |
| 병원 리뷰 조회 | 리뷰 1개 조회 기능 | GET | /api/v1/reviews/{id} |
| 병원 리뷰 조회 | 해당 병원의 리뷰만 모두 조회 | GET | /api/v1/hospitals/{id}/reviews |
| 병원 리뷰 조회 | 병원 정보와 함께 리뷰 조회 | GET | /api/v1/hospitals/{id} |

### ec2 접속주소
ec2-3-38-172-197.ap-northeast-2.compute.amazonaws.com:8080/api/v1/reviews/{id}
ec2-3-38-172-197.ap-northeast-2.compute.amazonaws.com:8080/api/v1/hospitals/{id}
