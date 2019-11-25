package com.generic.selector;

import com.generic.setup.cselector;

public class HomePageSelectors
{
	public static final cselector logo = new cselector("css,#logo1 a.logo-anchor","css,#headerBox > div.u-flexbox.u-width-full.t-header-bar__title > div > a");
	public static final cselector accountMenu = new cselector ("css,.gwt-MenuItem > a#accountMenu","css,#pwa-my-account-button .pw-button");
	public static final cselector accountMenuItems = new cselector ("css,.gwt-MenuItem > div > a","css,div.pw-list-tile > a");
	public static final cselector navIcon = new cselector("css,.nav-toggle", "css,.t-header__menu-icon .pw-button");
	
	public static final cselector countrySelector = new cselector("css,#gwt_country_changer", "css,div.t-footer-links  > div > div > button");
    public static final cselector accordionHeader =  new cselector("css,div.footerInner > div > div > div > h3","css,div.pw-accordion > div.pw-accordion__item"); 
    public static final cselector globalFooter =  new cselector("css,div.footerInner","css,footer > div.c-custom-accordion"); 

    
	public static final String header = "header-container";
	public static final String body_topNavLinks = "css,#main-navigation-large>.container>.nav-links";
	public static final String body_categorySlider = "page-section category-slider";
	public static final String body_valuepropPromo = "four-desc page-section ocm-valueprop-promo";
	public static final String body_navTabsContainer = "nav-tabs-container";
	public static final String body = "homepage-value-packs";
	public static final String footerBottomSection = "footer-bottom-section";
	public static final String footerTopSection = "footer-top-section";
	public static final String footerLogo = "footer-ocm-logo";
	public static final String footerEmailSignUp = "footer-email-sign-up";

	
}
