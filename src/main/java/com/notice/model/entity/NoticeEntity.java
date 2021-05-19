package com.notice.model.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_BOARD")
@DynamicInsert
@DynamicUpdate 
public class NoticeEntity {
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE",nullable = false)
	private String title;	
	
	@CreatedDate
	@Column(name = "CREATED_DATE",nullable = false,updatable = false)
	private String createdDate;
	
	@Column(name = "AUTHOR",nullable = false)
	private String author;
		
	@LastModifiedDate
	@Column(name = "LAST_DATE")
	private String lastDate;

	@Column(name = "CONTENTS")
	private String contents;
	
	@Column(name = "FILE_ID")
    private Long fileId;
	
//	@Builder
//	public NoticeEntity(Long id, String title, String createdDate, String author, String lastDate, String contents,
//			Long fileId) {
//		this.id = id;
//		this.title = title;
//		this.createdDate = createdDate;
//		this.author = author;
//		this.lastDate = lastDate;
//		this.contents = contents;
//		this.fileId = fileId;
//	}
	
}
