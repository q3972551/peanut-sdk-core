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
