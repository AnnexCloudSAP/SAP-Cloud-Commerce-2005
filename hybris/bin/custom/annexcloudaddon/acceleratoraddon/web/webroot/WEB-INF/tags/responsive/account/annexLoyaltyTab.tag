<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="customerEmail" required="true"	type="java.lang.String"%>
<%@ attribute name="siteId" required="true" type="java.lang.String"%>
<%@ attribute name="token" required="true" type="java.lang.String"%>
<c:set var="gtmContainerId" value="${siteId}" />
<c:set var="gtmUserId" value="${customerEmail}" />
<c:set var="gtmUserToken" value="${token}" />

	<div class="tab-content">
		<div id="socialannex_dashboard"></div>
		<script>
			var siteID = '${gtmContainerId}';
			var sa_emailid = '${gtmUserId}';
			var token = '${gtmUserToken}';
			var sa_uni = sa_uni || [];
			sa_uni.push([ 'sa_pg', '5' ]);
			(function() {
				function sa_async_load() {
					var sa = document.createElement('script');
					sa.type = 'text/javascript';
					sa.async = true;
					sa.src = '//cdn.socialannex.com/partner/'+siteID+'/universal.js';
					var sax = document.getElementsByTagName('script')[0];
					sax.parentNode.insertBefore(sa, sax);
				}
				if (window.attachEvent) {
					window.attachEvent('onload', sa_async_load);
				} else {
					window.addEventListener('load', sa_async_load, false);
				}
			})();
		</script>
	</div>
