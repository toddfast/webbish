package com.toddfast.webbish;

import javax.servlet.ServletContext;

/**
 *
 * @author Todd Fast
 */
public interface ConfigurationLocator {

	/**
	 *
	 *
	 */
	public Configuration getConfiguration(ServletContext context);

}
