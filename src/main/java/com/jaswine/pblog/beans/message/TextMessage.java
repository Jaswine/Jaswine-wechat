package com.jaswine.pblog.beans.message;


import lombok.Data;

/**
 * 文本消息实体类
 * @author Jaswine
 */
@Data
public class TextMessage extends BaseMessage {
	/**
	 * 文本消息内容
	 */
	private String Content;

}
