package com.toddfast.webbish.impl;

import com.conga.nu.Scope;
import com.conga.nu.ServiceProvider;
import com.toddfast.webbish.Configuration;
import com.toddfast.webbish.PageTagHelper;
import javax.servlet.ServletContext;

/**
 *
 *
 * @author Todd Fast
 */
@ServiceProvider(scope=Scope.APPLICATION)
public class PageTagHelperImpl implements PageTagHelper {

	/**
	 *
	 *
	 */
	@Override
	public Configuration getConfiguration(ServletContext context) {
		Configuration configuration=null;
		try {
			// Try to load the configuration
			configuration=com.conga.nu.Services.$(Configuration.class);
		}
		catch (com.conga.nu.ServiceInstantiationException e) {
			if (context!=null) {
				context.log("Could not find instance of "+
					Configuration.class.getName()+"; using defaults",e);
			}

			// Create a default configuration
			configuration=new DefaultConfiguration();
		}

		return configuration;
	}


	/**
	 *
	 *
	 */
	@Override
	public String resolveInclude(ServletContext context, String value,
			String defaultValue) {
		if (value==null) {
			value="";
		}

		if (!value.contains(".jspf")) {
			if (value.trim().equals("")) {
				value=defaultValue;
			}
			value="/WEB-INF/jsp/jspf/"+value+".jspf";
		}

		return value;
	}
}
