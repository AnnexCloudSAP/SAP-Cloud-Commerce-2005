	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:parseNumber var="availablePoints" integerOnly = "true" type = "number" value = "${userPointsDetails.availablePoints}" />
										<h4><b><spring:theme code="checkout.multi.customer.loyalty.details"/></b></h4>	
										<h5> <spring:theme code="checkout.multi.available.points"/>&nbsp;<p class="annex_promotion_pdp"><b>
				 						${availablePoints} Points = 
				 						<format:price	priceData="${userPointsDetails.creditsToCurrencyValue}" 	displayFreeForZero="true" /></b></p></h5>  
									    <h5><spring:theme code="checkout.multi.point.exp.date"/>&nbsp;&nbsp;<b>${userPointsDetails.pointsToExpireDate}</b> </h5>   
									<c:url var="pointRedemptionUrl"					value="/checkout/multi/point-redemption/add" />
									<c:url var="pointRemoveUrl"					value="/checkout/multi/point-redemption/remove" />
										
									 <c:if test="${empty userPointsDetails.appliedPointsOnCart or userPointsDetails.appliedPointsOnCart == '0.0'}">									
										<form:form id="applyPointsForm" modelAttribute="pointRedemptionForm"		action="${pointRedemptionUrl}" method="post">
											<h5><spring:theme code="checkout.multi.apply.points"/></h5>
											<input id="deductAmount" name="deductAmount" value="0" 	type="number" 	max="${userPointsDetails.maxApplicableCreditOnCart}" min="0" /> 
											<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" /> 
										</form:form>
									</c:if>
									
									<c:if test="${not empty userPointsDetails.appliedPointsOnCart and  userPointsDetails.appliedPointsOnCart != '0.0'}">
									    <form:form modelAttribute="pointRedemptionForm" action="${pointRemoveUrl}" method="post">
											<input id="deductAmount" name="deductAmount" value="0"	type="hidden" /> 
									 
											<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" /> 
											<h5><spring:theme code="checkout.multi.applied.points"/></h5>
                                         		<button type="button"> <fmt:formatNumber type="number" minFractionDigits="0" value="${userPointsDetails.appliedPointsOnCart}" />   &nbsp; &nbsp;<a	href="javascript:void(0)" class="remove-annex-points-button" ><i class="fa fa-times" aria-hidden="true"></i></a>
												</button>
									    </form:form>
									</c:if>