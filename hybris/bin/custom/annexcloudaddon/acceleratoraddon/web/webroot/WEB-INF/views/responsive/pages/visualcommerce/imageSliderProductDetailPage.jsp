<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="gtmContainerId" value="${siteId}" />

<c:if test="${not empty gtmContainerId}">
	<div id="sa_s22_instagram" class="sa_s22_instagram_product"></div>

	<script type="text/javascript">
		var sa_s22_product_code = "${product.code}"; //Pass the specific product code
		var sa_uni = sa_uni || [];
		sa_uni.push([ 'sa_pg', '${component.pageId}' ]);
		(function() {
			function sa_async_load() {
				if (!document.getElementById("saUniversal")) {
					var sa = document.createElement('script');
					sa.type = 'text/javascript';
					sa.async = true;
					sa.src = 'https://cdn.socialannex.com/partner/${gtmContainerId}/universal.js';
					var sax = document.getElementsByTagName('script')[0];
					sax.parentNode.insertBefore(sa, sax);
				}
			}
			if (window.attachEvent) {
				window.attachEvent('onload', sa_async_load);
			} else {
				window.addEventListener('load', sa_async_load, false);
			}
		})();
	</script>
</c:if>