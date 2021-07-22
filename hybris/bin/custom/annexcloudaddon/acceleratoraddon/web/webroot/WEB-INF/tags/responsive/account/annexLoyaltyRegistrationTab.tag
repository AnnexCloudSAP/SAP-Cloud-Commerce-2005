<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="s15_toad-main-dashboard">
	<div class="toad-main-dashboard">
		<div align="center" class="pa-section">
			<img class="img-responsive pa-logo"		src="https://cdn.socialannex.com/sanewimg/logo1.png">
			<hr>
			<h3 class="to-rewards toad-dashboard-head"><spring:theme code="annex.loyalty.registration.rewards"/></h3> 

			<p class="toad-dashboard-subtext">
				<spring:theme code="annex.loyalty.registration.dashboard"/>
			</p>
			 <c:url var="annexRegistrationUrl"					value="/my-account/loyalty-point" />
			 <form:form commandName="annexLoyaltyRegistrationForm" action="${annexRegistrationUrl}" method="post">
				<div class="checkbox1">
					<label align="center"> 
					 <form:checkbox path="registerLoyalty" /> 
					 <form:errors path="registerLoyalty" cssClass="error" /> 
						<spring:theme code="annex.loyalty.registration.read.accept"/><a href="https://www.annexcloud.com/terms-and-condition" target="_blank"><spring:theme code="annex.loyalty.registration.tc"/></a>
					</label>
				</div>
				<h4>
					<button class="btn btn-sm sa-join"   type="submit"
						style="cursor: pointer;">JOIN NOW</button>
				</h4>
			  </form:form>
		</div>
	</div>
</div>



									 
									  

<!-- <div class="optIn__section loyalty-wrapper">
   <div class="loyalty-title"><h1>Loyalty Enrollment Program</h1></div>
   <div class="optInResult"></div>
   <div class="optIn-content">
       <div class="enrollmentLabel"><input type="checkbox" id="enrollmentCheckbox" />
       <label for="enrollmentCheckbox" class="label">Enroll yourself for loyalty program and earn points on various
       action:</label></div>
       <input class="btn-default" type='button' id="optInSubmit" value='Enrolled' />
   </div>
</div> -->