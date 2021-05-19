package com.notice.model.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class FileVo implements Serializable{ // ��û������
	
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;	
	
	/** ���ϰ�� */	
	private String filepath;
	
	/** ���ϸ� */
	private String fileNm;
	
	/** �������ϸ� */
	private String orgFileNm;

}
