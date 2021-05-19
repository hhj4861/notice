package com.notice.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Data;

@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@Data
public class NoticeResVo { // 출력데이터
	
	/** Id */	
	private Long id;
	
	/** 제목 */
	private String title;	
	
	/** 작성일 */	
	private String createdDate;
	
	/** 작성자 */
	private String author;
		
	/** 최종 수정일 */
	private String lastDate;

	/** 내용 */	
	private String contents;
	
	/** 파일ID*/
	private Long fileId;
	
	@Override
	public String toString() {
		return "NoticeResVo [id=" + id + ", title=" + title + ", createdDate=" + createdDate + ", author=" + author
				+ ", lastDate=" + lastDate + ", contents=" + contents + ", fileId=" + fileId + "]";
	}

	
}
