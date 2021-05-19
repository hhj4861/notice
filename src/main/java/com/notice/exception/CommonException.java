package com.notice.exception;

import lombok.Data;

@Data
public class CommonException extends Exception{
	
	private int status;
	
	private String errCode;
	
	private String errMsg;
	
	private String devMsg;
	
	
//	public CommonException(int status, String errcode, String errMsg) {	
//		this.status = status;
//		this.errCode = errcode;
//		this.errMsg = errMsg;		
//	}
//	
	public CommonException(int status, ErrorCode error, String...errMsg) {	
		this.status = status;
		this.errCode = error.getErrorCode();
		this.errMsg = error.getErrorMessage(errMsg);		
	}
	
	public CommonException(String devMsg, ErrorCode err, String...errArgs) {
		this(null,devMsg,err,errArgs);
	}
	
	public CommonException(Exception ex, String devMsg, ErrorCode err, String...errArgs) {
		this(err.getHttpStatusCode(),err,errArgs);
		
		this.devMsg = devMsg;
		if(ex != null) {
			setDevMsg(devMsg+","+ex.getMessage());
			super.initCause(ex.getCause());
		}
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
	
}
