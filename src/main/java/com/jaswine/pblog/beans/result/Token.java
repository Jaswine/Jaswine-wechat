package com.jaswine.pblog.beans.result;

import lombok.Data;

/**
 * Access_Token实体
 * @author Jaswine
 */
@Data
public class Token extends BaseResult {

	/**
	 * access_token
	 */
	private String access_token;
	/**
	 * 有效期(秒/s)
	 */
	private int expires_in;
}