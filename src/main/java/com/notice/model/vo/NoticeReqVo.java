package com.notice.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class NoticeReqVo { // ��û������
	
	/** Notice ID */
	private Long id;
	
	/** ���� */
	private String title;	
	
	/** ���� */	
	private String contents;
	
	/** �ۼ��� */
	private String author;

	/** ����ID */
	private Long fileId;
}
