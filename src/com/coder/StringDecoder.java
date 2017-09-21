package com.coder;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class StringDecoder extends ByteToMessageDecoder
{
	private final Charset charset;
	
	public StringDecoder() {
		this.charset = Charset.defaultCharset();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception
	{
		// TODO Auto-generated method stub
		in.markReaderIndex();
		int len = in.readableBytes();
		byte[] bytes  = new byte[len];
		in.readBytes(bytes);
		String buffer = new String(bytes,charset); 
		String regex  = "\\{\"msghead\":\\{([^}]*)\\},\"msgbody\":(\"0\"|\\{([^}]*)\\})\\}";

		Pattern pattern = Pattern.compile(regex); 	
		Matcher matcher = pattern.matcher(buffer); 

		boolean isOK = false;
		while (matcher.find())
		{
			out.add(matcher.group()); 
			isOK = true;
		}
		
		if (!isOK)
		{
			in.resetReaderIndex();
		}
	}

}
