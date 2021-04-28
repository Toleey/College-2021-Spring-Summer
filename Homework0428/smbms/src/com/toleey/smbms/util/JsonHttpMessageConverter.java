package com.toleey.smbms.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter {

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException { // TODO
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
		super.writeInternal(obj, outputMessage);
	}

	/*
	 * private static SerializeConfig mapping = new SerializeConfig(); private
	 * static String dateFormat; static { dateFormat = "yyyy-MM-dd";
	 * mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat)); }
	 * 
	 * @Override protected void writeInternal(Object obj, HttpOutputMessage
	 * outputMessage) throws IOException, HttpMessageNotWritableException { // TODO
	 * Auto-generated method stub
	 * 
	 * OutputStream out = outputMessage.getBody(); String text =
	 * JSON.toJSONString(obj, mapping, this.getFeatures()); byte[] bytes =
	 * text.getBytes(this.getCharset()); out.write(bytes);
	 * 
	 * }
	 */

}
