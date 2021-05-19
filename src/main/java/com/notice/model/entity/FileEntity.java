package com.notice.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_FILE")
@DynamicInsert
@DynamicUpdate 
public class FileEntity {
	
	/** ID */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 파일 경로 */
	@Column(name = "FILE_PATH", nullable = false)
	private String filepath;
	
	/** 파일명 */
	@Column(name = "FILE_NM", nullable = false)
	private String fileNm;
	
	/** 원본파일명 */
	@Column(name = "ORG_FILE_NM", nullable = false)
	private String orgFileNm;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	

}
