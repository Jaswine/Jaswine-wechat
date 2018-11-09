package com.jaswine.pblog.entity.message;

import lombok.Data;

/**
 * 语音信息
 * @author Jaswine
 */
@Data
public class VoiceMessage extends BaseMessage{

	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String MediaId;

	/**
	 * 语音格式，如amr，speex等
	 */
	private String Format;

	/**
	 * 语音识别结果，UTF8编码(需开启语音识别功能)
	 */
	private String Recognition;
}
