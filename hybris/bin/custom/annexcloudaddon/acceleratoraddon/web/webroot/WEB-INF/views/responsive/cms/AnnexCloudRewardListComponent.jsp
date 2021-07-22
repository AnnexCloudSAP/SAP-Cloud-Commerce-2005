<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<html>
<head>
<meta name="${_csrf.parameterName}" content="${_csrf.token}"/>
<script type="text/javascript" src="/annexstorefront/_ui/addons/annexcloudaddon/responsive/common/js/annexcloudaddon.js"></script>
</head>
<body>
<c:url value="/pointredemption/price" var="priceRedemptionUrl"/>
<c:if test="${not empty annexcloudrewards and pointRedemptionType eq 'COUPON'}">
  <h4><b>Select Coupon To Redeem</b></h4><br />
  <select id ="selectcouponreward">
    <option disabled selected value> -- select an option -- </option>
        <c:forEach var="listValue" items="${annexcloudrewards}">
            <Option value="${listValue.rewardId}">${listValue.rewardName}</option>
        </c:forEach>
        </select>
   </c:if>
   <c:if test="${pointRedemptionType eq 'PRICE'}">
			<h4><b>Available Loyalty Points ${userPointsDetails.availablePoints}</b></h4>     
			<h4><b>Points Expiry Date ${userPointsDetails.pointsToExpireDate}</b></h4>  
			<h4><b>Available Credits ${userPointsDetails.creditsToCurrencyValue}</b></h4><br /> 
      		<input id="inputpointsreward" value="0" type="number"  max="${userPointsDetails.maxApplicableCreditOnCart}" min="0"   />  
      		<button id="btnSubmit">Claim</button><br>
      		<span id="error_msg"></span>
      </c:if>
   </body>
</html>