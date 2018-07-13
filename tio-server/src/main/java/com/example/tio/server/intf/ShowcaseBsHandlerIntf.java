package com.example.tio.server.intf;

import com.example.tio.common.HelloPacket;
import org.tio.core.ChannelContext;

/**
 * 业务处理器接口
 */
public interface ShowcaseBsHandlerIntf {


	 Object handler(HelloPacket packet, ChannelContext channelContext) throws Exception;

}
