package com.toleey.smbms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	//需要定制的时间日期格式
	private String datePattern;
	public StringToDateConverter( String datePattern) {
		this.datePattern=datePattern;
	}
	
	
	//参数ss就是表单提交过来的时间日期
	@Override
	public Date convert(String ss) {
		Date date = null;
		try {
				date = new SimpleDateFormat(datePattern).parse(ss);
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return date;
	}

   
}
