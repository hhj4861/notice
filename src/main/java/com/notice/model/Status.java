package com.notice.model;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * -사용
 *  Status.spec
 * 
 * - 내용 :
 * 투자 모집 상태 : 모집중, 모집완료
 * 	{@linkplain Status#ING}
 * 	{@linkplain Status#DONE}
 * </pre>
 */
public enum Status {
	/** 투자 모집 상태 :  모집중 */
	ING("ing", "모집중"),
	
	/** 투자 모집 상태 :  모집완료 */
	DONE("done", "모집완료")
	;
	
	private String code;
	private String viewName;
	
	private Status(String code, String viewName) {
		this.code = code;
		this.viewName = viewName;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getViewName() {
		return this.viewName;
	}
	
	public static boolean isValidcode(String code) {
		try {
			getValueOf(code);
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * {@link Status}에서 code에 해당하는 {@link Status}를 반환
	 * @param {@link Status#code}
	 * @return
	 * @throws IllegalArgumentException if the code does not contain {@link Status}.
	 */
	public static Status getValueOf(String code) throws IllegalArgumentException {
		for (Status v : Status.values()) {
			if (StringUtils.equals(v.code, code)) {
				return v;
			}
		}
		throw new IllegalArgumentException("[ERR] PRODUCT STATUS MACTH ERROR [" + code + "] , [" + Status.class.getSimpleName()+"]");
	}
	
	/**
	 * {@link Status}에서 code에 해당하는 viewName을 반환
	 * @param {@link Status#code}
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public static String getViewNameOf(String code) throws IllegalArgumentException {
		return getValueOf(code).getViewName();
	}
}
