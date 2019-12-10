package com.generic.selector;

import com.generic.setup.cselector;

public class PLPSelectors {

	//CBI
	public static final cselector SearchIcon = new cselector("css,#uNavTop button.searchboxAsButton", "css,.t-header__fades-in>button");
	public static final cselector searchBox = new cselector("css,#searchBox input#headerBox","css,form>div>div>input");
	public static final cselector recommendedOption =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid a.unbxd-as-popular-product-info","css,.c--align-middle a.pw-link.c-arrange.u-flexbox.c--align-middle.u-align-center");
	public static final cselector SearchButton =new cselector("css,li.unbxd-as-popular-product.unbxd-as-popular-product-grid a.unbxd-as-popular-product-info");
    public static final cselector product =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,div.pw-list-tile__content  a.c-product-item__main-image"); 
    public static final cselector productName =  new cselector("css,div.gwt-product-info-panel-details-panel-html > h2 > a","css,a.pw-link.c-product-item-title-link > div > div > h2 > div"); 
    public static final cselector productContainer =  new cselector("css,div.unbxd-products-display-panel","css,footer > div.c-custom-accordion"); 
    public static final cselector productsImages = new cselector("css,div.iwc-main-img-wrapper img.iwc-main-img", "css,article > a > div > img");
    public static final cselector productsNames = new cselector("css,a.gwt-sub-category-info-panel-link","css,a.pw-link.c-product-item-title-link > div > div > h2 > div > div");
    public static final cselector PriceLowToHigh = new cselector("Price: High to Low","css,#unbxd_normal_sort_price-asc > div");
	public static final cselector FilterContainer = new cselector("css,div.text-facets-container-panel div.unbxd-all-options-container span.gwt-InlineLabel.selected-option-span","css,div.c-product-list__container-filter-option.u-flex-1.u-margin-end > div > button > div");
	public static final cselector FilterContainerContents =  new cselector("css,div.unbxd-facet-option.text-facet span.unbxd-facet-option-checkbox input","css,.u-color-neutral-70.u-text-uppercase.u-text-align-center.u-letter-spacing-3");
	public static final cselector mobileSortingMenu = new cselector("","css,.c-product-list__filters-sort-button");
	public static final cselector allCatigories = new cselector("","css,div.c-custom-accordion__list-of-items-item>div>div>div>input");
	public static final cselector GRMobileSorting = new cselector("","css,#app-main > div > div:nth-child(2) > div > div > div.u-flexbox.u-direction-column-reverse > div > div.c-product-list__container-sort-option.u-flex-1 > div > div > div > select");
	public static final cselector GRFilterContainer = new cselector("","css,div.pw-accordion__close-icon");
	public static final cselector GRFilterContainerContents = new cselector("","css,.u-text-letter-spacing-0 > div");
	public static final cselector GRallCatigories = new cselector("","css,.pw-field__label-wrap.pw--end > label > div > div > div");

}
