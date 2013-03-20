package com.toddfast.webbish.impl;

import com.toddfast.webbish.Configuration;

/**
 *
 * @author Todd Fast
 */
public class DefaultConfiguration implements Configuration {


	@Override
	public String getStaticResourcePath() {
		return "static";
	}


	@Override
	public String getPageTitleFormatString() {
		return "%s";
	}


	@Override
	public String getMetaCopyright() {
		return "";
	}


	@Override
	public String getMetaSection() {
		return "";
	}


	@Override
	public String getMetaRobots() {
		return "index, follow";
	}


	@Override
	public String getMetaRevisitAfter() {
		return "";
	}


	@Override
	public String getMetaAuthor() {
		return "";
	}


	@Override
	public String getMetaDistribution() {
		return "global";
	}


	@Override
	public String getMetaDescription() {
		return "";
	}


	@Override
	public String getMetaKeywords() {
		return "";
	}


	@Override
	public String getMetaViewport() {
		return "width=device-width, initial-scale=1.0";
	}


	@Override
	public String getMetaFacebookPageID() {
		return "";
	}
}
