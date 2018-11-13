package com.jaswine.pblog.beans.message;

import lombok.Data;

/**
 * 视频消息
 * @author Jaswine
 */
@Data
public class VedioMessage extends BaseMessage {
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String MediaId;

	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String ThumbMediaId;
}
