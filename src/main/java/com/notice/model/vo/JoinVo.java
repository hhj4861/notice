package com.notice.model.vo;


import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class JoinVo {
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
	
	/** 파일명 */
	private String orgFileNm;
	
	@QueryProjection
	public JoinVo(Long id, String title, String createdDate, String author, String lastDate, String contents,
			Long fileId, String orgFileNm) {
		super();
		this.id = id;
		this.title = title;
		this.createdDate = createdDate;
		this.author = author;
		this.lastDate = lastDate;
		this.contents = contents;
		this.fileId = fileId;
		this.orgFileNm = orgFileNm;
	}

	@Override
	public String toString() {
		return "JoinVo [id=" + id + ", title=" + title + ", createdDate=" + createdDate + ", author=" + author
				+ ", lastDate=" + lastDate + ", contents=" + contents + ", fileId=" + fileId + ", orgFileNm=" + orgFileNm
				+ "]";
	}
	

}
