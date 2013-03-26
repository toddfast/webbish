<%@tag description="page" pageEncoding="UTF-8" 
	   import="com.toddfast.webbish.*"
	   body-content="scriptless"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="id" required="true" rtexprvalue="false"%>
<%@attribute name="themes" required="true" rtexprvalue="true"%>
<%@attribute name="anonymous" required="true" rtexprvalue="false"%>
<%@attribute name="title" required="true" rtexprvalue="true"%>

<%@attribute name="head" required="false" rtexprvalue="true"%>
<%@attribute name="header" required="false" rtexprvalue="true"%>
<%@attribute name="footer" required="false" rtexprvalue="true"%>
<%@attribute name="include" required="false" rtexprvalue="true"%>

<%-- Define our variables --%>
<%@variable name-given="static" scope="AT_BEGIN"%>

<%-- Find the configuration --%>
<%
{
	PageTagHelper helper=com.conga.nu.Services.$(PageTagHelper.class);
	request.setAttribute("helper",helper);
	request.setAttribute("configuration",
		helper.getConfiguration(config.getServletContext()));
}
%>
<jsp:useBean id="configuration" 
			 type="com.toddfast.webbish.Configuration"
			 scope="request"/>
<jsp:useBean id="helper"
			 type="com.toddfast.webbish.PageTagHelper"
			 scope="request"/>

<%-- Initialize important variables used throughout --%>
<c:set var="static" value="<%= configuration.getStaticResourcePath(config.getServletContext()) %>"/>

<%-- any content can be specified here e.g.: --%>
<%--<%@include file="/WEB-INF/jsp/jspf/__top.jspf"%>--%>
<%@include file="/META-INF/resources/WEB-INF/jsp/jspf/__top.jspf"%>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<meta name="copyright" content="<%= configuration.getMetaCopyright(config.getServletContext()) %>" />
		<meta name="section" content="<%= configuration.getMetaSection(config.getServletContext()) %>" />
		<meta name="robots" content="<%= configuration.getMetaRobots(config.getServletContext()) %>" />
		<meta name="revisit-after" content="<%= configuration.getMetaRevisitAfter(config.getServletContext()) %>" />
		<meta name="author" content="<%= configuration.getMetaAuthor(config.getServletContext()) %>" />
		<meta name="distribution" content="<%= configuration.getMetaDistribution(config.getServletContext()) %>" />
		<meta name="description" content="<%= configuration.getMetaDescription(config.getServletContext()) %>" />
		<meta name="keywords" content="<%= configuration.getMetaKeywords(config.getServletContext()) %>" />
		<meta name="viewport" content="<%= configuration.getMetaViewport(config.getServletContext()) %>">

		<c:choose>
		<c:when test="<%= configuration.getMetaFacebookPageID(config.getServletContext())!=null && !configuration.getMetaFacebookPageID(config.getServletContext()).isEmpty() %>">
		<meta name="fb:page_id" content="<%= configuration.getMetaFacebookPageID(config.getServletContext()) %>"/>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>

		<%-- Extra head content can go here --%>


		<%-- Determine the head include --%>
		<jsp:include page="<%= 	helper.resolveInclude(
				config.getServletContext(),head,"head") %>">
			<jsp:param name="id" value="${id}"/>
			<jsp:param name="static" value="${static}"/>
		</jsp:include>

		<%-- Title --%>
		<c:choose>
		<c:when test="${fullTitle!=null}">
		<title>${fullTitle}</title>
		</c:when>
		<c:otherwise>
		<title><%= String.format(configuration.getPageTitleFormatString(config.getServletContext()),title) %></title>
		</c:otherwise>
		</c:choose>

		<%-- Bootstrap the require.js and all modules --%>
		<script type="text/javascript" data-page="${id}" src="${static}/js/init.js"></script>
		<script type="text/javascript" src="${static}/js/config.js"></script>
		<script type="text/javascript" src="${static}/js/lib/require+jquery/require+jquery.js"></script>

		<%-- Define a module for the page --%>
		<script type="text/javascript">
			define("page",[],function() {
				return {
					id: "${id}",
					staticResourceBaseURL: "${static}",
					themes: "${themes}",
					parameters: <% com.toddfast.webbish.impl.PageTagHelperImpl.toJSON(
						out,request.getAttribute("it"),"\t\t\t\t\t"); %>
				};

			});
		</script>

		<%-- Themes --%>
		<%@variable name-given="themedir" scope="AT_BEGIN"%>
		<c:forTokens items="${themes}" delims=", " var="theme">
		<c:set var="themedir" value="${static}/theme/${fn:trim(theme)}"/>
		<!-- Theme: ${fn:trim(theme)} -->
		<link rel="shortcut icon" type="image/png" href="${themedir}/images/icon.png" />
		<link rel="stylesheet" type="text/css" href="${themedir}/theme.css" media="all" />
		<!--<link rel="stylesheet" type="text/css" href="${themedir}/css/page/${id}.css" media="all" />-->
		<script type="text/javascript">
			define("theme-${fn:trim(theme)}",[],function() {
				return { baseURL: "${static}/theme/${fn:trim(theme)}" };
			});
		</script>
		</c:forTokens>
		<link rel="stylesheet" type="text/css" href="${themedir}/css/page/${id}.css" media="all" />
	</head>


<%-- BODY --%>

<c:choose>
<c:when test="${allowAnonymous==true}">
<body id="${id}" class="anonymous">
</c:when>
<c:otherwise>
<body id="${id}" class="not-anonymous">
</c:otherwise>
</c:choose>
	<!--[if lt IE 7]>
		<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
	<![endif]-->

	<noscript>
		<p class="noscript">You must have JavaScript enabled to use this site.</p>
	</noscript>

	<%-- Determine the header include --%>
	<jsp:include page="<%= 	helper.resolveInclude(
			config.getServletContext(),header,"page-header") %>">

		<jsp:param name="id" value="${id}"/>
		<jsp:param name="static" value="${static}"/>
		<jsp:param name="themedir" value="${themedir}"/>
	</jsp:include>

	<%-- Start page content --%>

	<jsp:doBody/>

	<%-- End page content --%>


	<%-- Determine the footer include --%>
	<jsp:include page="<%= 	helper.resolveInclude(
			config.getServletContext(),footer,"page-footer") %>">
		<jsp:param name="id" value="${id}"/>
		<jsp:param name="static" value="${static}"/>
		<jsp:param name="themedir" value="${themedir}"/>
	</jsp:include>

	<%-- Determine the generic include --%>
	<jsp:include page="<%= 	helper.resolveInclude(
			config.getServletContext(),include,"empty") %>">
		<jsp:param name="id" value="${id}"/>
		<jsp:param name="static" value="${static}"/>
		<jsp:param name="themedir" value="${themedir}"/>
	</jsp:include>


<%--<%@include file="/WEB-INF/jsp/jspf/analytics.jspf" %>--%>
<%@include file="/META-INF/resources/WEB-INF/jsp/jspf/analytics.jspf" %>
<%--<%@include file="/WEB-INF/jsp/jspf/__bottom.jspf"%>--%>
<%@include file="/META-INF/resources/WEB-INF/jsp/jspf/__bottom.jspf"%>