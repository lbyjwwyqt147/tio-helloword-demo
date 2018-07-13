package com.example.tio.common;

import org.tio.core.intf.Packet;

/***
 *  消息包
 */
public class HelloPacket extends Packet {
	private static final long serialVersionUID = -172060606924066412L;
	public static final int HEADER_LENGHT = 5;//消息头的长度
	public static final String CHARSET = "utf-8";
	private byte type;  //消息类型
	private byte[] body;


	public HelloPacket() {
		super();
	}

	/**
	 * @param type
	 * @param body
	 * @author tanyaowu
	 */
	public HelloPacket(byte type, byte[] body) {
		super();
		this.type = type;
		this.body = body;
	}

	public byte[] getBody() {
		return body;
	}


	public void setBody(byte[] body) {
		this.body = body;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
}
