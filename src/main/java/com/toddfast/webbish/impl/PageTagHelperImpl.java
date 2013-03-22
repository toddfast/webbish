package com.toddfast.webbish.impl;

import com.conga.nu.Scope;
import com.conga.nu.ServiceProvider;
import com.toddfast.webbish.Configuration;
import com.toddfast.webbish.PageTagHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;

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


	/**
	 * A simplistic JSON serializer
	 *
	 * @param object
	 * @param indent
	 * @param out
	 * @throws IOException
	 */
	public static void toJSON(JspWriter out, Object object, String indent)
			throws IOException {
	
		if (object==null) {
			out.println("null");
		}
		else
		if (object.getClass().isArray()) {
			Object[] array=((Object[])object);
			List<Object> list=new ArrayList<Object>(Arrays.asList(array));
			toJSON(out,list,indent);
		}
		else
		if (object instanceof java.util.Map) {
			out.write("{");
			int i=0;
			for (Map.Entry<?,?> entry: ((Map<?,?>)object).entrySet()) {
				if (i++ > 0) {
					out.write(",");
				}

				out.write("\n");
				out.write(indent);
				out.write("\t\"");
				out.write(entry.getKey().toString());
				out.write("\": ");

				toJSON(out,entry.getValue(),indent+"\t");
			}
			out.write("\n");
			out.write(indent);
			out.write("}");
		}
		else
		if (object instanceof Iterable) {
			out.write("[");
			int i=0;
			for (Iterator iterator=((Iterable)object).iterator();
					iterator.hasNext(); ) {
				if (i++ > 0) {
					out.write(",");
				}

				out.write("\n");
				out.write(indent);
				out.write("\t");
				toJSON(out,iterator.next(),indent+"\t");
			}
			out.write("\n");
			out.write(indent);
			out.write("]");
		}
		else
		if (object.getClass().getName().endsWith("JSONObject")) {
			// Try to detect if this is an org.json.JSONObject or derivative (e.g.
			// from Jettison) and just inline it directly
			out.write(object.toString());
		}
		else
		if (object instanceof Number) {
			out.write(object.toString());
		}
		else {
			out.write("\""+object.toString()+"\"");
		}
	}
}
