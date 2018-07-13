package com.example.tio.common;

/**
 * 消息类型定义
 */
public interface Type {

	/**
	 * 绑定用户
	 */
	byte BIND_USER = 1;

	/**
	 * 发送消息
	 */
	byte SEND_INFO = 2;

	/**
	 * 心跳
	 */
	byte HEART_BEAT_REQ = 99;

}
