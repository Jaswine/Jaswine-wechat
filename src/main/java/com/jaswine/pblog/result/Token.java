package com.jaswine.pblog.result;

/**
 * Access_Token实体
 * @author Jaswine
 */
public class Token extends BaseResult {

	/**
	 * access_token
	 */
	private String access_token;
	/**
	 * 有效期(秒/s)
	 */
	private int expires_in;



	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expiresIn) {
		expires_in = expiresIn;
	}
}