/**
 * @author peanut
 * 此代码为了解决粘包粘包问题，步骤如下
 * 1.根据消息协定，先判断包中是否还剩余4个字节长度可读。不可读return,让包和下个包组合
 * 2.将长度从前4个字节中取出,同时标记,如果读完前4个字节读完后,后面的包长度不够,那就再和下个包进行组合
 * 3.将剩余的包拿出来byte[]转成我们要用的protobuf
 */
package com.coder;

import java.util.List;

import com.message.MsgBuffer.Msg;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtoBufDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		if(!in.isReadable(4))
		{ 
			return;
		}
		in.markReaderIndex();
		int bodyLength = in.readInt();

		if (!in.isReadable(bodyLength - 4))
		{
			in.resetReaderIndex();
		}
		else
		{
			byte[] bodyBytes = new byte[bodyLength-4];
			in.readBytes(bodyBytes);
			Msg msg = Msg.parseFrom(bodyBytes); //解析消息体 
			out.add(msg);
		}
		
	}

}
