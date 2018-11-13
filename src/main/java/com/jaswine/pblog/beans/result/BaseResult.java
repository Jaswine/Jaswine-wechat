package com.jaswine.pblog.beans.result;


import lombok.Data;

/**
 * 微信返回数据基类
 * @author Jaswine
 */
@Data
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


}
