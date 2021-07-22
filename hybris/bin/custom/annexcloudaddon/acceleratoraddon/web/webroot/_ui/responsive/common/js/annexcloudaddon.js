/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
ACC.action = {

	_autoload : [ "bindNewsletter", "bindPointRedemption",
			"bindLoyaltyEnrollment","bindLoyaltyOptOut","bindToacCouponCheckout","bindToacPointsCheckout" ,"bindGiftCardCheck","bindGiftCardRemove"],

	bindNewsletter : function() {
		$('#newsletterSubmit').click(function() {
			var emailID = $("#newsletterEmail").is(":checked");
			var actionName = $("#actionName").val();
			$.ajax({
				url : ACC.config.encodedContextPath + '/newsletter/signup',
				data : {
					emailId : emailID,
					actionName : actionName
				},
				type : 'POST',
				success : function(data) {
					$('.result').html(data);
				},
				error : function(e) {
				}
			});
		});
	},
	bindLoyaltyOptOut : function() {
		$('#optOutSubmit').click(
				function() {
					var enrollmentFlag = 0;
					if ($('#optOutCheckbox').is(":checked"))
					{
					enrollmentFlag=2;
					}else
					{
					 $('.optOutResult').html("Please checked the enrollment checkbox");
					 return false;
					}
					$.ajax({
						url : ACC.config.encodedContextPath
								+ '/my-account/loyalty-point/optout',
						data : {
							optOutValue : enrollmentFlag
						},
						type : 'POST',
						success : function(data) {
						if(data==1)
						{
						  $('.optOutResult').html("Error occurred during enrollment");
						}else
						{
						  $('.optOutResult').html("Your are Opt out successfully.");
						}

						},
						error : function(e) {
						}
					});
				});
	}	
	,
	bindLoyaltyEnrollment : function() {
		$('#optInSubmit').click(
				function() {
					var enrollmentFlag = $("#enrollmentCheckbox").val();
					if ($('#enrollmentCheckbox').is(":checked"))
					{
					enrollmentFlag=true;
					}else
					{
					 $('.optInResult').html("Please checked the enrollment checkbox");
					 return false;
					}
					$.ajax({
						url : ACC.config.encodedContextPath
								+ '/my-account/loyalty-point/enrollment',
						data : {
							status : enrollmentFlag
						},
						type : 'POST',
						success : function(data) {
						if(data!=0)
						{
						  $('.optInResult').html("Error occurred during enrollment");
						}else
						{
						  $('.optInResult').html("Your are enrolled successfully. Enjoy Loyalty program benefits");
						}

						},
						error : function(e) {
						}
					});
				});
	},
	bindPointRedemption : function() {

		$('#selectcouponreward').change(
				function() {
					var value = $('#selectcouponreward').val();
					var token = $("meta[name='_csrf']").attr("content");
					$.ajax({
						type : "get",
						headers : {
							"X-CSRF-TOKEN" : token
						},
						url : ACC.config.encodedContextPath
								+ '/pointredemption/couponcode',
						data : {
							rewardId : value
						},
						success : function(msg) {
							$('#js-voucher-code-text').val(msg);
							$('#js-voucher-code-text').css('background-color',
									'#DEDEDE'); // change the background color
						}
					});
				});

				$('#selectpricereward').change(
                				function() {
                					var rewardId = $('#selectpricereward').val();
                				    var deductAmount=$("#selectpricereward option:selected").text();
                					var token = $("meta[name='_csrf']").attr("content");
                					$.ajax({
                						type : "get",
                						headers : {
                							"X-CSRF-TOKEN" : token
                						},
                						url : ACC.config.encodedContextPath
                								+ '/pointredemption/price',
                						data : {
                							rewardId : rewardId,
                							deductAmount : deductAmount
                						},
                						success : function(msg) {
                							window.location.href=ACC.config.encodedContextPath +'/cart'
                						}
                					});
                				});
				
				 $("#btnSubmit").click(function(){
					 $("#error_msg").text("");   					
     					var rewardId = 100;
     				    var deductAmount=$('#inputpointsreward').val();
     				    var maxAmount =parseFloat($('#inputpointsreward').attr('max'));
     					var token = $("meta[name='_csrf']").attr("content");
     					if(deductAmount<=maxAmount)
     						{
	        						$.ajax({
	            						type : "get",
	            						headers : {
	            							"X-CSRF-TOKEN" : token
	            						},
	            						url : ACC.config.encodedContextPath
	            								+ '/pointredemption/price',
	            						data : {
	            							rewardId : rewardId,
	            							deductAmount : deductAmount
	            						},
	            						success : function(msg) {
	            							window.location.href=ACC.config.encodedContextPath +'/cart'
	            						}
	            					});
     						}	
     					$("#error_msg").text("Maximum allowed credit value is "+maxAmount);
				 });
   	        },

   	     bindToacCouponCheckout: function () {	
	        $('.js-annex-cloud-release-voucher-remove-btn').on("click", function (event) {
	    		var form = $("#removeAnnexCouponForm")[0];
	    		form.submit();
	        });
	        
	       	$(document).on("click", "#pointRedemptionButton", function() {
				var couponForm = $("#applyCouponForm")[0];
				var pointsForm = $("#applyPointsForm")[0];
				if (typeof couponForm === "undefined" && typeof pointsForm === "undefined") {
					$("#pointRedemptionNextStepForm").submit();	
				}else if(typeof couponForm !== 'undefined'){ 
						couponForm.submit();						
					}else{
							pointsForm.submit();
					}
			});
	    },
	
	    bindToacPointsCheckout:  function(){
	 	   $(document).on("click",".remove-annex-points-button",function(){
	 	     var form = $(this).closest("form");
	 	     form.submit();
	 	   });
	 	} ,
		bindGiftCardCheck : function() {
			$('#checkgiftcard').click(function() {				 
				var giftcardCode = $("#giftcardcodeCheck").val();
				var redeemAmount = $("#redeemAmount").val();
				console.log(giftcardCode);
				console.log(redeemAmount);
				$.ajax({
					url : ACC.config.encodedContextPath + '/checkout/multi/giftcard-redemption/checkGiftCardBalance',
					data : {
						giftcardCode : giftcardCode,
						redeemAmount : redeemAmount
						
					},
					type : 'POST',
					success : function(obj) {	
						console.log(obj);
						console.log(obj.msg);
						console.log(obj.card_balance);
						if(obj.msg == 'Success')
							{
							$("#AC_Gift_Card").hide();
							$("#giftCardRedemption").show();
							$("#bal").text(obj.card_balance);//added to show giftcard bal on jsp
							$("#giftcardcodeCheck").val(giftcardCode);
							$("#appliedCode").text(giftcardCode);// to show giftcardcode
							$("#giftcardCode").text(giftcardCode)
							$("#giftcardCode").val(giftcardCode);
							console.log(giftcardCode);
							console.log($("#giftcardCode").val());
							}	 
					},
					error : function(e) {
					}
				});
			});
		},
		bindGiftCardRemove : function() {
				$(document).on("click", "#giftCardRemove" , function() {
					var form = $("#removeAnnexGiftCardForm");
			    	form.submit();
			    	$("#appliedGiftCard").hide();
				});	
		}
}