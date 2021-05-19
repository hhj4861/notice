package com.notice.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Data;

@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@Data
public class NoticeResVo { // ��µ�����
	
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
	
	@Override
	public String toString() {
		return "NoticeResVo [id=" + id + ", title=" + title + ", createdDate=" + createdDate + ", author=" + author
				+ ", lastDate=" + lastDate + ", contents=" + contents + ", fileId=" + fileId + "]";
	}

	
}
