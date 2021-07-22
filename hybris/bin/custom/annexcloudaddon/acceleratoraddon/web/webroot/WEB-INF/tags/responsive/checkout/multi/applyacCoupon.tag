	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:parseNumber var="availablePoints" integerOnly = "true" type = "number" value = "${point.availablePoints}" />
	
									<h4><b> <spring:theme code="checkout.multi.coupon.customer.loyalty.details"/></b></h4>
									<h5><spring:theme code="checkout.multi.coupon.available.credits"/>	<p class="annex_promotion_pdp"><b>
				 						${availablePoints} <spring:theme code="annex.point"/></b></p></h5>
										<h5><b>${couponvalue.rewardId}</b></h5>
									<c:url var="couponRedemptionUrl"
										value="/checkout/multi/point-redemption/addCoupon" />
									<c:url var="couponRedemptionNextStepUrl"
										value="/checkout/multi/point-redemption/next" />
									<c:url var="pointRemoveUrl"
										value="/checkout/multi/point-redemption/remove" />
							  
							  <c:if test="${not empty annexcloudrewards}">
							  			<form:form id="applyCouponForm"
												   modelAttribute="couponRedemptionForm" action="${couponRedemptionUrl}" method="post" >
											<select id="rewardId" name="rewardId">	
											<option value="0">select coupon</option>									      
												<c:forEach var="couponvalue" items="${annexcloudrewards}">
												    <option value="${couponvalue.rewardId}">${couponvalue.rewardName}</option>
												</c:forEach>
											</select>
										</form:form>
							  </c:if> 
							    <c:if test="${not empty acAppliedCoupon}">
							     		<c:url var="couponRemovalStepUrl"  value="/checkout/multi/point-redemption/removeCoupon" />
										 <form:form id="removeAnnexCouponForm" modelAttribute="couponRedemptionForm" action="${couponRemovalStepUrl}" method="post">
										 <span class="js-annex-cloud-release-voucher voucher-list__item-box" id="voucher-code-${acAppliedCoupon}">
							             ${acAppliedCoupon}<input id="coupon" name="coupon" hidden="hidden" value="${acAppliedCoupon}" type="text"><span class="glyphicon glyphicon-remove js-annex-cloud-release-voucher-remove-btn voucher-list__item-remove"></span>
							             </span>
							            <div>
							    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</div>
										</form:form>
							    </c:if> 