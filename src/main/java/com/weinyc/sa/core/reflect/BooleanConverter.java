package com.weinyc.sa.core.reflect;

/**
 * Don't use this file. Ronghai may remove it.
 * @author Ronghai
 * Nov 21, 2012 3:30:39 PM
 * If you wanna change this file, please let me know and send modify information to me (Ronghai.Wei@Lake5Media.com)
 * Keep code clean and remove unused code.
 * @param <T>
 */
public class BooleanConverter extends StringParamConverter<Boolean> { 
	public Boolean convert(String o) throws ConverterException {
		if( o != null ) o = o.trim();
		return Boolean.valueOf(o);
	}

	public Class<Boolean> returnClazz() {  
		return Boolean.class;
	} 
 

}
 
