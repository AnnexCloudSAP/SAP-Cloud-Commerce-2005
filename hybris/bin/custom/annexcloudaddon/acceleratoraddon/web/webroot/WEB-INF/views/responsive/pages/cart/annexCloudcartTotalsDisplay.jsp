<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="annexcart" tagdir="/WEB-INF/tags/addons/annexcloudaddon/responsive/cart" %>
<%-- Verified that there's a pre-existing bug regarding the setting of showTax; created issue  --%>
<div class="row">
    <div class="col-xs-12 col-md-5 col-lg-6">
        <div class="cart-voucher">
            <cart:cartVoucher cartData="${cartData}"/>
        </div>
    </div>
    <div class="col-xs-12 col-md-7 col-lg-6">
        <div class="cart-totals">
            <annexcart:cartTotals cartData="${cartData}" showTax="false"/>
            <cart:ajaxCartTotals/>
        </div>
    </div>
</div>