package com.notice.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class NoticeReqVo { // 요청데이터
	
	/** Notice ID */
	private Long id;
	
	/** 제목 */
	private String title;	
	
	/** 내용 */	
	private String contents;
	
	/** 작성자 */
	private String author;

	/** 파일ID */
	private Long fileId;
}
