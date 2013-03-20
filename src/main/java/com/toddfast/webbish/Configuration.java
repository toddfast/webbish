package com.toddfast.webbish;



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
	public String getStaticResourcePath();


	/**
	 *
	 *
	 */
	public String getPageTitleFormatString();


	/**
	 *
	 *
	 */
	public String getMetaCopyright();


	/**
	 *
	 *
	 */
	public String getMetaSection();


	/**
	 * index, follow
	 *
	 */
	public String getMetaRobots();


	/**
	 *
	 *
	 */
	public String getMetaRevisitAfter();


	/**
	 *
	 *
	 */
	public String getMetaAuthor();


	/**
	 * global
	 *
	 */
	public String getMetaDistribution();


	/**
	 *
	 *
	 */
	public String getMetaDescription();


	/**
	 *
	 *
	 */
	public String getMetaKeywords();


	/**
	 * width=device-width, initial-scale=1.0
	 *
	 */
	public String getMetaViewport();


	/**
	 *
	 *
	 */
	public String getMetaFacebookPageID();
}
