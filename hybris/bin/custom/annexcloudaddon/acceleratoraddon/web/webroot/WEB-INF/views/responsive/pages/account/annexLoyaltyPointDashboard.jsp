<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="annexLoyalty" tagdir="/WEB-INF/tags/addons/annexcloudaddon/responsive/account" %>


	<annexLoyalty:annexLoyaltyTab customerEmail="${annexCloudData.userId}" siteId="${annexCloudData.site}" token="${annexCloudData.token}" />
