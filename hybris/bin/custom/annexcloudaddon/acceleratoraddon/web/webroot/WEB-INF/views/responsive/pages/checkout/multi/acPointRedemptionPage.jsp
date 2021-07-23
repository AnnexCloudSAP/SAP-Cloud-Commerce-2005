<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="multiCheckout"	tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="acCheckout"	tagdir="/WEB-INF/tags/addons/annexcloudaddon/responsive/checkout/multi"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"	tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">

	<div class="row">
		<div class="col-sm-6">
			<div class="checkout-headline">
				<span class="glyphicon glyphicon-lock"></span>
				<spring:theme code="checkout.multi.secure.checkout" />
			</div>
			<multiCheckout:checkoutSteps checkoutSteps="${checkoutSteps}"
				progressBarId="${progressBarId}">
				<jsp:body>
                    <ycommerce:testId code="checkoutStepThree">
                        <div class="checkout-paymentmethod">
                            <div class="checkout-indent">
     						 <c:if test="${pointRedemptionType eq 'COUPON'}">
									<acCheckout:applyacCoupon/>
     						  </c:if>
     						  	<c:url var="pointRedemptionNextStepUrl" 	value="/checkout/multi/point-redemption/next" />
     						    <form:form id="pointRedemptionNextStepForm"  action="${pointRedemptionNextStepUrl}" method="get">
							    </form:form>
                            </div>
                        </div>
								<button id="pointRedemptionButton"   type="submit" class="btn btn-primary btn-block checkout-next"><spring:theme code="checkout.multi.deliveryMethod.continue"/></button>
                    </ycommerce:testId>
               </jsp:body>
			</multiCheckout:checkoutSteps>
		</div>
		<div class="col-sm-6 hidden-xs">
			<multiCheckout:checkoutOrderDetails cartData="${cartData}"
				showDeliveryAddress="true" showPaymentInfo="false"
				showTaxEstimate="false" showTax="true" />
		</div>
		<div class="col-sm-12 col-lg-12">
			<cms:pageSlot position="SideContent" var="feature" element="div"
				class="checkout-help">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
	</div>
</template:page>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
button {
	border: 2px solid #179e37;
	background: #a0cdaa;
	padding: 8px 24px;
	font-size: 16px;
	color: #fff;
	font-weight: bold;
}

button:focus {
	outline: none;
}

button a {
	color: #fff;
}
</style>
