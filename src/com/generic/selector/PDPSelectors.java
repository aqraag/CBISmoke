package com.generic.selector;

import com.generic.setup.cselector;

public class PDPSelectors {
	
	public static final cselector continueShowppingBtn=new cselector("css,#cbiBody > div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div > div > table > tbody > tr.dialogMiddle > td.dialogMiddleCenter > div > table > tbody > tr:nth-child(3) > td > div > button.button.secondary");
	public static final cselector checkOutBtn=new cselector("css,#cbiBody > div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div > div > table > tbody > tr.dialogMiddle > td.dialogMiddleCenter > div > table > tbody > tr:nth-child(3) > td > div > button.button.primary","css,#gwt-add-to-cart-btn");
	//CBI SMK
	public static final cselector avaibleSelection = new cselector("div.gwt-product-options-panel-option-title");
	public static final cselector title = new cselector("css,.gwt-product-detail-product-name-label","css,.t-product-details-heading__title.u-margin-bottom");
	public static final cselector avaibleOptions = new cselector("css,.gwt-product-option-panel-widget-panel>div","css,.c-product-option-items__container");
	public static final cselector allColors = new cselector("css,.gwt-image-picker-option >img","css,.pw-swatch__chip>.pw-swatch__chip-inner>.c-product-option-items__item-content.gwt-image-picker-option");
	public static final cselector allSizes = new cselector("css,.gwt-product-option-panel-listbox-container>div> .gwt-ListBox","css,.c-product-options__content.u-border-brand-color.u-border-radius");
	public static final cselector allFabrics = new cselector("css,.gwt-image-picker-option-holder>.gwt-selection-chip-picker-option", "css,.pw-swatch__chip>.pw-swatch__chip-inner");
	public static final cselector allShipLeadTime = new cselector("css,.gwt-image-picker-option-holder>.gwt-selection-chip-picker-option","css,.pw-swatch__chip>.pw-swatch__chip-inner");
	public static final cselector addToCartBtn = new cselector("gwt-add-to-cart-btn");
	public static final cselector addToCartCloseBtn = new cselector("gwt-Button ok-cancel-close-btn");
	public static final cselector firstSwatchInOptions = new cselector("css,div#gwt-option-panel > div:nth-child({0})> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child(1)","css,div.c-product-options> div:nth-child({0})> div.u-margin-top > div > div:nth-child(1) > div > button > div > div > div > div > div");
	public static final cselector topPrice = new cselector("gwt-pdp-main-was-now-price-holder","css,.t-product-details-heading__price.t-product-details__price>div");
	public static final cselector addToWLGRBtnEnabled = new cselector("css,.button.secondary.gift-registry","css,.t-product-details>div>.pw-button.u-width-full.u-text-uppercase");
	public static final cselector addToWLGRBtnDisabled = new cselector("css,.button.secondary.gift-registry.disabled","css,.u-wishlist-gift-disable");
	public static final cselector addToCartBtnEnabled = new cselector("css,.button.primary#gwt-add-to-cart-btn","css,.c-add-to-cart");
	public static final cselector addToCartBtnDisabled = new cselector("css,.button.primary.disabled#gwt-add-to-cart-btn","css,.u-bg-disabled-color-add-to-cart");
	public static final cselector bottomPrice = new cselector("gwt-product-detail-widget-total-price-amount","css,.t-product-details-price>span");
	public static final cselector activeListBox = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-listbox>.gwt-product-option-panel-listbox-container>div>select > option:nth-child(2)");
	public static final cselector activeSwatchOption = new cselector("css,div#gwt-option-panel > div:nth-child(1)> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child(1)","css,div.c-product-options> div:nth-child(1)> div.u-margin-top > div > div:nth-child(1) > div > button > div > div > div > div > div");
	public static final cselector ListBox = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-listbox>.gwt-product-option-panel-listbox-container>div>select","css,.c-product-options__content.u-border-brand-color.u-border-radius");
	public static final cselector swatchContainer = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-swatchbox");
	public static final cselector imageOption = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>div#gwt-option-panel > div:nth-child({0})> .gwt-product-option-panel-widget-panel > div  > div > div:nth-child({1})","css,div.c-product-options> div:nth-child({0})> div.u-margin-top > div > div:nth-child({1}) > div > button > div > div > div > div > div");
	public static final cselector chooseYourItems = new cselector("gwt-choose-your-item-btn");
	public static final cselector numberOfBundleItems = new cselector("css,.gwt-product-detail-products-container>.gwt-product-detail-widget","css,.t-product-details-heading__price.t-product-details__price>div");	
	public static final cselector itemsID = new cselector("css,.gwt-product-detail-products-container>.gwt-product-detail-widget");	
	public static final cselector addToCartModal = new cselector("css,.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div");
	public static final cselector topPriceBundleDesktop = new cselector("css,.gwt-product-detail-right-panel.gwt-product-right-content-panel>.gwt-pdp-main-was-now-price-holder");
	public  final static cselector topPriceSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-right-center-content-panel>.gwt-product-price-container","css,.t-product-details-heading__price.t-product-details__price>div");
	public static final cselector addPersonalizationSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-detail-widget-personalization-panel>.gwt-personalize-link-style");
	public static final cselector addToWLGRBtnEnabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-gift-regestry-panel","css,.t-product-details>div>.pw-button.u-width-full.u-text-uppercase");
	public static final cselector addToWLGRBtnDisabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel.disabled>.product-bottom-gift-regestry-panel","css,.u-wishlist-gift-disable");
	public static final cselector addToCartBtnEnabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-qty-atc-panel>div>button#gwt-add-to-cart-btn.button.primary","css,.c-add-to-cart");
	public static final cselector addToCartBtnDisabledSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-qty-atc-panel>div>button#gwt-add-to-cart-btn.button.primary.disabled","css,.u-bg-disabled-color-add-to-cart");
	public static final cselector bottomPriceSingle = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-bottom-content-panel>.product-bottom-total-price-panel>div>.gwt-product-detail-widget-total-price-amount","css,.t-product-details-price>span");
	public static final cselector bundleItems = new cselector("u-widget-product-icon u-position-absolute");
	public static final cselector bundleItem = new cselector("css,#gwt_bundledetail_json.store", "css,#bundle-detail");

	public static final cselector miniPDPPrice = new cselector("css,.t-product-details-heading >div>.t-product-details-heading__price.t-product-details__price");
	public static final cselector ListBoxOption = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>.gwt-product-option-panel>.gwt-product-option-panel-listbox>.gwt-product-option-panel-listbox-container>div>select>option", "css,.c-product-options__content.u-border-brand-color.u-border-radius>option");
	public static final cselector allImageOptions = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>div#gwt-option-panel > div:nth-child({0})> .gwt-product-option-panel-widget-panel > div  > div > div","css,div.c-product-options> div:nth-child({0})> div.u-margin-top > div > div > div > button > div > div > div > div > div");
	public static final cselector expiredPDP = new cselector("css,.gwt-product-content-panel>.gwt-product-right-content-panel>div>.gwt-product-detail-widget-base-expired-label","css,.t-product-details-messages__expired");
	public static final cselector closeBundleProductModal = new cselector("css,.c-icon-label-button");
	
	public static final String bottomPriceBundle = "css,#{0}>" + bottomPriceSingle.DesktopSelector.replace("css,", "");
	public static final String addToCartBtnDisabledBundle = "css,#{0}>" + addToCartBtnDisabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToCartBtnEnabledBundle = "css,#{0}>" + addToWLGRBtnEnabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToWLGRBtnEnabledBundle = "css,#{0}>" + addToWLGRBtnEnabledSingle.DesktopSelector.replace("css,", "");
	public static final String addToWLGRBtnDisabledBundle = "css,#{0}>" + addToWLGRBtnDisabledSingle.DesktopSelector.replace("css,", "");
	public final static String topPriceBundle = "css,#{0}>" + topPriceSingle.DesktopSelector.replace("css,", "");
	public static final String ListBoxBundle = "css,#{0}>" + ListBox.DesktopSelector.replace("css,", "");
	public static final String activeListBoxBundle = "css,#{0}>" + activeListBox.DesktopSelector.replace("css,", "");
	public static final String swatchContainerBundle = "css,#{0}>" + swatchContainer.DesktopSelector.replace("css,", "");
	public static final String imageOptionBundle = "css,#{0}>" + imageOption.DesktopSelector.replace("css,", "");

	public static final String expiredPDPBundle = "css,#{0}>" + expiredPDP.DesktopSelector.replace("css,", "");
	public static final String ListBoxOptionBundle = "css,#{0}>" + ListBoxOption.DesktopSelector.replace("css,", "");
	
	public static final cselector SelectRegistryOrWishListModal = new cselector("css,#gwt-add-to-gift-registry-modal","css,div.c-pdp-modal.m-wishlist-registery");
    public static final cselector giftRegistryListBox = new cselector("css,select.gift-registry-list-bx","css,div.pw-sheet__content > div > div > select");
    public static final cselector createNewWL = new cselector("css,select.gift-registry-list-bx > option[value='Create New Wish List']","css, div.pw-sheet__content > div > div > select > option[value='Create New Wish List']");
    public static final cselector createNewWLConfirmationBtn = new cselector("css,div.gwt-submit-cancel-dialog-button-panel > button.button.primary","css, div.pw-sheet__content > div > div > button");
    public static final cselector nameYourNewWL = new cselector("css,#gwt-wishlist-create-modal","css,div.m-create_new_wishlist_modal");
    public static final cselector WLName = new cselector("css,input#wishlist_name_id");
    public static final cselector nameYourNewWLconfirmationBtn = new cselector("css,#gwt-wishlist-create-modal  button.button.primary","css, div.pw-sheet__content > div > div > button");
    public static final cselector confirmationModal = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.gwt-added-to-wish-list-modal","css,div.m-product_added_to_wishlist_modal");
    public static final cselector productName = new cselector("css,div.gwt-product-main-title-panel > div > h2","css,div.c-custom-sheet__content > div >div> div> div> div.u-justify-center");
    public static final cselector addToCardProductName = new cselector("css,div.addToCartProductName > div","css,span.t-product-details-heading__title-text > div > div");
    public static final cselector viewListBtn = new cselector("css,td > div > button.button.primary","css,button.pw-button.pw--secondary");
    public static final cselector addedProductName = new cselector("css,div.gwt_gr_label.gwt_gr_number_label","css, div.t-registry-list-product-list__product-name");
    public static final cselector myWLAddToCartBtn = new cselector("css,div.wish-list-add-to-cart > div > button","css,div.t-registry-list-product-list__product-add-to-cart.u-flex-grow > button");
    public static final cselector addToCartConfirmationModal = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div");
    public static final cselector addToCartConfirmationModalCheckoutBtn = new cselector("css,div.gwt-DialogBox.ok-cancel-dlog.gwt_addtocart_div button.button.primary");
    public static final cselector shoppingCartProductsName = new cselector("css,div.order-item-name > div");
	
	public static final cselector addPersonalizedButton = new cselector("css,.gwt-product-content-panel>div.gwt-product-right-content-panel>div.gwt-product-detail-widget-personalization-panel> div.gwt-personalize-link-style" , "pw-button c-add-personalization-button" );
	public static final cselector personalizedItems = new cselector("button third gwt-tab-header-sign-button","css,div.pw-accordion--is-prerender > button");
	public static final cselector personalizedOpenItem = new cselector ("button third gwt-tab-header-sign-button","css,div.pw-accordion--is-open > button");
	public static final cselector personalizedItemColors1 = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option >div >div>div>div.gwt-image-picker-option>img","css,div.pw-accordion--is-open>div > div > div > div > div > div > div > div>div>div>div.u-margin-top.m-product-options-no-padding>div>div>div>button");
	public static final cselector personalizedTypeFaces = new cselector ("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>div>div.gwt-personalization-image-picker-option>img");
	public static final cselector personalizedItemColors2 = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option > div>div.gwt-personalization-swatch-picker-option>img");
	public static final cselector tabHeaderTitle = new cselector("css,div.gwt-accordion-tab-header>div.gwt-tab-header-title");
	public static final cselector personalizedInputValue = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>input","css,div.pw-accordion--is-open>div>div>div>div>div>div>div>div>div>div>div>div>div>input");
	public static final cselector itemSizeMenu = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>div.gwt-product-option-panel-listbox-container");
	public static final cselector personalizationSaveAndCloseButton = new cselector ("css,div.gwt-submit-cancel-dialog-button-panel > button.button.primary" , "css,div.pw-accordion--is-open>div>div>div>div>button.pw--primary");
	public static final cselector addedPersonlizedDetails = new cselector("css,.gwt-product-content-panel>div.gwt-product-right-content-panel>div.gwt-product-detail-widget-personalization-panel> div.gwt-product-detail-widget-personalization-chosen-values>div.gwt-HTML","css,div.c-personalization-chosen-values>div>div.c-personalization-chosen-value");
	public static final cselector personlizedModal = new cselector ("gwt-DialogBox","pw-lockup pw--is-locked");
	public static final cselector personalizedItemMenu = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>div.gwt-product-option-panel-listbox-container>div>select","css,div.pw-accordion--is-open>div>div>div>div>div>div>div>div>div>div>div>div>div>select");
	public static final cselector personalizedMenuOptions = new cselector("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>div.gwt-product-option-panel-listbox-container>div>select>option","css,div.pw-accordion--is-open>div>div>div>div>div>div>div>div>div>div>div>div>div>select>option");
	public static final cselector personlizedStyleItem = new cselector ("css,div.gwt-accordion-tab-content.tabopen > div > div > div.gwt-personalization-modal-accordions-content-option>div.gwt-option-picker-image-picker>div.gwt-personalization-image-picker-option>img");
	public static final cselector personlizedTitle = new cselector ("css,div.c-add-personalization-panel>span");

	// done-OCM
	public static String price = "price-and-special-messaging";
	public static String id = "code";
	public static String desc = "description";
	public static String socialMediaButtons = "css,div.st-btns";
	public static String shareBtn = "st-label";
	public static String numberOfProductsInBundle = "included-items-shipping";
	public static String productsInBundle = "css,div.each-included-item .information";
	public static String reviewsCount = "css,a.bv-rating-label.bv-text-link.bv-focusable>span";
	public static String reviewCountPerEachRate = "bv-inline-histogram-ratings-score";
	public static String starsRating = "bv-rating-stars-on bv-rating-stars";
	public static String secondaryRating = "bv-rating-ratio bv-td";

	public static String closSocialShareBtn = "st-close";
	public static String PDPnavs = "css,div.nav-tabs-container>ul>li";
	public static String PDPnavs_mobile = "css,div.tab-content>div>div>button";
	public static String favButton = "addToWishlist";
	public static String randomVariant = "css,.each-option-holder>div>.btn";
	public static String optionHolder = "each-option-holder";
	public static String optionHolderTitle="css,p>span.variant-text";

	public static String colorLable = "css,body>div>div>div>div>div>div>div>div>div>ul>li.attribute.color>div.label";
	public static String color = "Select color: ";
	public static String sizeAndFamilyLable = "css,div.product-variations > ul > div > ul > li > div.label";
	public static String lengthLable = "css,div>div>div.product-sister-styles>ul>li>div.label";
	public static String cart_popup = "popup-btn-checkout";
	public static String qty = "input-qty no-spin";
	public static String SA = "stock-in-stock";
	public static String cartPopupProductQty = "quantity";
	public static String miniCartProductUnitPrice = "unit-price";
	public static String minicart = "cart-component";
	public static String OverView;
	public static String size;

}
