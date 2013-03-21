package com.toddfast.webbish.impl;

import com.toddfast.webbish.Configuration;
import javax.servlet.ServletContext;

/**
 *
 * @author Todd Fast
 */
public class DefaultConfiguration implements Configuration {


	@Override
	public String getStaticResourcePath(ServletContext context) {
		return context.getContextPath()+"/static";
	}


	@Override
	public String getPageTitleFormatString(ServletContext context) {
		return "%s";
	}


	@Override
	public String getMetaCopyright(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaSection(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaRobots(ServletContext context) {
		return "index, follow";
	}


	@Override
	public String getMetaRevisitAfter(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaAuthor(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaDistribution(ServletContext context) {
		return "global";
	}


	@Override
	public String getMetaDescription(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaKeywords(ServletContext context) {
		return "";
	}


	@Override
	public String getMetaViewport(ServletContext context) {
		return "width=device-width, initial-scale=1.0";
	}


	@Override
	public String getMetaFacebookPageID(ServletContext context) {
		return "";
	}
}
