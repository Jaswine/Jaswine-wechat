package com.jaswine.pblog.beans.message;


import lombok.Data;

/**
 * 图片信息
 * @author Jaswine
 */
@Data
public class ImageMessage extends BaseMessage {
	/**
	 * 图片链接（由微信系统生成）
	 */
	private String PicUrl;
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String MediaId;


	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
