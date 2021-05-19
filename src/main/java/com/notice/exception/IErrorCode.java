package com.notice.exception;

public interface IErrorCode {
	public int getHttpStatusCode();
	public String getErrorCode();
	public String getErrorMessage(String... args);
}
