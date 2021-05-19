package com.notice.model.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class FileVo implements Serializable{ // 요청데이터
	
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;	
	
	/** 파일경로 */	
	private String filepath;
	
	/** 파일명 */
	private String fileNm;
	
	/** 원본파일명 */
	private String orgFileNm;

}
