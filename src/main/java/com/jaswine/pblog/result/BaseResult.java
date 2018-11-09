package com.jaswine.pblog.result;


/**
 * 微信返回数据基类
 * @author Jaswine
 */
public abstract class BaseResult {

	/**
	 * 成功状态码
	 */
	private static final String SUCCESS_CODE = "0";

	/**
	 * 错误码
	 */
	private String errcode;
	/**
	 * 错误信息
	 */
	private String errmsg;


	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
