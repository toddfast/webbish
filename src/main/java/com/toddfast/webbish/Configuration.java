package com.toddfast.webbish;

import javax.servlet.ServletContext;



/**
 *
 *
 * @author Todd Fast
 */
public interface Configuration {

	/**
	 *
	 *
	 */
	public String getStaticResourcePath(ServletContext context);


	/**
	 *
	 *
	 */
	public String getPageTitleFormatString(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaCopyright(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaSection(ServletContext context);


	/**
	 * index, follow
	 *
	 */
	public String getMetaRobots(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaRevisitAfter(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaAuthor(ServletContext context);


	/**
	 * global
	 *
	 */
	public String getMetaDistribution(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaDescription(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaKeywords(ServletContext context);


	/**
	 * width=device-width, initial-scale=1.0
	 *
	 */
	public String getMetaViewport(ServletContext context);


	/**
	 *
	 *
	 */
	public String getMetaFacebookPageID(ServletContext context);
}
