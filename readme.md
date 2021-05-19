환경 : 자바1.8 , Gradle , SQL Lite , Spring boot
IDE : STS4

1. 각 API는 1개의 controller 에서 관리하며 기능에 따라 service를 구분.
2. DB는 sqllite를 사용하였으며, 테이블은 2개(TB_PRODUCT , TB_CUSTROMER) 생성 , TB_PRODUCT_PK 는 PRODUCT_ID TB_CUSTOMER_PK는 SEQ , TB_CUSTOMER_FK는 PRODUCT_ID로 TB_PRODUCT의 PK와 연결함.
3.JPA를 사용하여 DB조회하였으며, 2개이상 테이블 JOIN이 필요한 경우 확장성을 고려하여 QUERYDSL를 사용.
4.응답메시지는 별도 클래스(ResultVo) 를 생성하여 작성함.
5.각 조회 응답에 제공되는 총 투자금액(상품에 투자된 금액), 투자자수 등은 조회된 List stream을 통해 처리함.


