package com.coder;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class StringBufferDecoder extends MessageToMessageDecoder<ByteBuf>
{
	// TODO Use CharsetDecoder instead.
	private final Charset charset;

	public StringBufferDecoder() {
		this(Charset.defaultCharset());
	}

	public StringBufferDecoder(Charset charset) {
		if (charset == null) {
			throw new NullPointerException("charset");
		}
		this.charset = charset;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		String buffer = msg.toString(charset);
		String regex  = "\\{\"msghead\":\\{([^}]*)\\},\"msgbody\":(\"0\"|\\{([^}]*)\\})\\}";

		Pattern pattern = Pattern.compile(regex); 	
		Matcher matcher = pattern.matcher(buffer); 
	
		while (matcher.find())
		{
			out.add(matcher.group()); 
		}
	}
}
