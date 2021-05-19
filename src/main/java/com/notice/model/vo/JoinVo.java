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
	
	/** ���� */
	private String title;	
	
	/** �ۼ��� */	
	private String createdDate;
	
	/** �ۼ��� */
	private String author;
		
	/** ���� ������ */
	private String lastDate;

	/** ���� */	
	private String contents;
	
	/** ����ID*/
	private Long fileId;
	
	/** ���ϸ� */
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
