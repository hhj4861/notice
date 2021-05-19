package com.notice.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements IErrorCode{
	
	OK_200(HttpStatus.OK.value(),"200","OK")
	,ERR_400(HttpStatus.BAD_REQUEST.value(),"400","�������� ������ �ֽ��ϴ�.")
	,ERR_500(HttpStatus.OK.value(),"500","���񽺿� ������ �ֽ��ϴ�.");
	
	
	private int status;
	
	private String errCode;
	
	private String errMsg;
	
	
	private ErrorCode(int status, String errcode, String errMsg) {		
		this.status = status;
		this.errCode = errcode;
		this.errMsg = errMsg;		
	}
	
	@Override
	public int getHttpStatusCode() {
		return this.status;
	}

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return this.errCode;
	}

	@Override
	public String getErrorMessage(String... args) {
		// TODO Auto-generated method stub
		return this.errMsg;
	}

}
