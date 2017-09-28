/**
 * @author peanut
 * 这段代码主要是做装包用，步骤如下：
 * 1.将消息转为byte[](因为最后发的就是byte[])
 * 2.将byte[]的长度算出来，同时将长度放入消息包中的前4个字节
 * 3.将byte[]放入消息包中
 */
package com.coder;

import com.message.MsgBuffer.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
public class ProtoBufEncoder extends MessageToByteEncoder<Msg>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out)
			throws Exception {
		// TODO Auto-generated method stub
		byte[] bytes  = msg.toByteArray();
		int sumLength = bytes.length + 4;
		out.writeInt(sumLength);             //将总长度放入前4个字节中
		out.writeBytes(bytes);               //将消息放入bytebuf中
	
	}


}
