환경 : 자바1.8 , Gradle , SQL Lite , Spring boot , JPA 

IDE : STS4

PORT : 9001


[실행방법] 
- github소스를 다운로드
- IDE(STS등)에서 gradle 프로젝트로 import
- Spring Boot App Run 
- SQL Lite 실행 후 github에서 다운로드 받은 test.db 위치 설정 (test DB 위치 : C:\\test\\test.db)
- 웹 실행 (localhost://9001) 



[프로젝트 설명]
1. 각 API는 1개의 controller 에서 관리하며 기능에 따라 service를 구분.
	* NoticeController
		* NoticeService
		* FileService
2. 서비스 기능
	* 목록조회
		* 공지목록 조회
		* 페이징 처리
	* 등록처리 (공지 및 첨부파일)
	* 상세조회
	* 수정 (공지내용, 첨부파일)
	* 삭제	
	* 첨부파일 다운로드
	
3. Exception은 공통 Exception (CommonException)을 별도로 작성하여 처리함.
4. 오류 발생 시 error.html 으로 처리함.
5. junit 으로 TDD 작성 테스트 진행

DB는 sqllite를 사용하였으며, 테이블은 2개(TB_BOARD, TB_FILE) 생성함.

JPA 사용 하였으며, UPDATE 및 다중조인은 QueryDsl 로 처리함.

	* NoticeRepository
		* NoticeRepositoryCustom
		* NoticeRepositoryImpl
	* FileRepository

테이블은 다음과 같이 작성.

* TB_BOARD

ID|TITLE|CREATED_DATE|AUTHOR|LAST_DATE|CONTETNS|FILE_ID
---|---|---|---|---|---|---|
1|제목|2021-05-20 01:00:56|작성자|2021-05-20 01:00:56|내용|1|

* TB_FILE

ID|FILE_NM|FILE_PATH|ORG_FILE_NM
---|---|---|---|
1|1c2f01a60830766fd0aa3a6ef843b58d|C:\Users\lenovo\Documents\notice\files\1c2f01a60830766fd0aa3a6ef843b58d|TEST.PNG|
