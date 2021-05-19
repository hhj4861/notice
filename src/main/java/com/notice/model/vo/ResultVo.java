package com.notice.model.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class ResultVo {

	public final static String SUCCESS = "success";
	public final static String FAIL = "fail";

	/** ó�� ��� */
	private String status;
	/** ��� �ڵ� */
	private String code;
	/** �޽��� */
	private String msg;
	/** ��¥ */
	private String date;

	public ResultVo() {
		this.status = SUCCESS;
		this.code = "00";
		this.msg = "";
		setNowDate();
	}

	public ResultVo(String status) {
		this.status = status;
		if (SUCCESS.equals(status)) {
			this.code = "00";
			this.msg = "���� ó�� �Ǿ����ϴ�.";
		} else if (FAIL.equals(status)) {
			this.code = "11";
			this.msg = "ó�� �� ������ �߻��Ͽ����ϴ�.";
		} else if ("exception".equals(status)) {
			this.code = "99";
			this.msg = "exception";
		}
		setNowDate();
	}

	public ResultVo(String status, String msg) {
		this.status = status;
		this.msg = msg;
		if (FAIL.equals(status)) {
			this.code = "10";
		} else if ("exception".equals(status)) {
			this.code = "99";
		}
		setNowDate();
	}

	public ResultVo(String status, String code, String msg) {
		this.status = status;
		this.code = code;
		this.msg = msg;		
		setNowDate();
	}
	

	public void setNowDate() {
		date = new Date().toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
