package com.notice.model.vo;

public class ResultDataVo<T> extends ResultVo {	
	
	private T resultData;
	
	public ResultDataVo() {
		super();
	}
	
	public ResultDataVo(String status) {
		super(status);
	}
	
	/**
	 * 메시지 기본
	 * @param status
	 * @param resultData
	 * */	
	public ResultDataVo(String status, T resultData) {
		super(status);
		this.resultData = resultData;
	}
	
	
	public T getResultData(){
		return resultData;
	}
	
	public void setResultData(T resultData){
		this.resultData = resultData;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("ResultDataVo [");
		builder.append(resultData);
		builder.append("]");
		return builder.toString();
	}
}
