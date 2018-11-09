package com.jaswine.pblog.api;

import lombok.Data;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;


/**
 * 微信提供接口基类
 * @author Jaswine
 */
public abstract class BaseAPI {

	/**
	 * ACCESS_TOKEN
	 */
	protected static final String PARAM_ACCESS_TOKEN = "access_token";
	/**
	 *  基础API
	 */
	protected static final String BASE_URI = "https://api.weixin.qq.com";
	/**
	 * 媒体API
	 */
	protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
	/**
	 * 地图API
	 */
	protected static final String MP_URI = "https://mp.weixin.qq.com";
	/**
	 *
	 */
	protected static final String MCH_URI = "https://api.mch.weixin.qq.com";
	/**
	 *
	 */
	protected static final String OPEN_URI = "https://open.weixin.qq.com";
	/**
	 * WIFI API
	 */
	protected static final String WIFI_URI = "https://wifi.weixin.qq.com";

	/**
	 * content-type(JSON)
	 */
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
	/**
	 * content-type(XML)
	 */
	protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());

}
