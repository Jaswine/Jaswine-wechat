package com.jaswine.pblog.api;


import com.jaswine.pblog.result.Token;

/**
 * 获得access_token接口
 * @author Jaswine
 */
public class TokenAPI extends BaseAPI {



	public static Token getToken(String appid, String appsecret){
		HttpUriRequest request = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/token")
				.addParameter("grant_type", "client_credential")
				.addParameter("appid", appid)
				.addParameter("secret", appsecret)
				.build();

	}
}
