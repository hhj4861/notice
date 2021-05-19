package com.notice.model;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * -���
 *  Status.spec
 * 
 * - ���� :
 * ���� ���� ���� : ������, �����Ϸ�
 * 	{@linkplain Status#ING}
 * 	{@linkplain Status#DONE}
 * </pre>
 */
public enum Status {
	/** ���� ���� ���� :  ������ */
	ING("ing", "������"),
	
	/** ���� ���� ���� :  �����Ϸ� */
	DONE("done", "�����Ϸ�")
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
	 * {@link Status}���� code�� �ش��ϴ� {@link Status}�� ��ȯ
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
	 * {@link Status}���� code�� �ش��ϴ� viewName�� ��ȯ
	 * @param {@link Status#code}
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public static String getViewNameOf(String code) throws IllegalArgumentException {
		return getValueOf(code).getViewName();
	}
}
