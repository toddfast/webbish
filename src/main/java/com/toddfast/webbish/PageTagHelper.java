package com.toddfast.webbish;

import javax.servlet.ServletContext;

/**
 *
 * @author Todd Fast
 */
public interface PageTagHelper {
	/**
	 *
	 *
	 */
	public Configuration getConfiguration(ServletContext context);


	/**
	 *
	 *
	 */
	public String resolveInclude(ServletContext context, String value,
		String defaultValue);

}
