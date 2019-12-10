package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

import com.generic.selector.PLPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	// CBI
	public static boolean searchAndVerifyResults(String SearchTerm, boolean recommendedOption) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad))
				disableMonetate();
			clickSearchicon();
			typeSearch(SearchTerm);
			if (recommendedOption) {
				String productName = pickRecommendedOption();
				result = verifyPickedProduct(productName);
			} else {
				clickSearch(SearchTerm);
				result = verifySeachResultPage();
			}
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	private static boolean verifySeachResultPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result = true; 
			result = result && verifyProductImagesDisplayed();
			List<String> productsNames = getfirst3ProductsNames();
			sortByPriceLowToHigh();
			List<String> sortedProductsNames = getfirst3ProductsNames();
			result = result && compareOperationResults(productsNames, sortedProductsNames);

			List<String> productsNamesBeforeFilter = getfirst3ProductsNames();
			SelectFilter();
			List<String> productsNamesAfterFilter = getfirst3ProductsNames();
			result = result && compareOperationResults(productsNamesBeforeFilter, productsNamesAfterFilter);

			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}


	// CBI
	private static void clickOnFilterBy() throws Exception {
		try {
			getCurrentFunctionName(true);
			if(isFG())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.FilterContainer.get());
			if(isGR())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainer.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static void selectFilterName() throws Exception {
		try {
			getCurrentFunctionName(true);
			if(isFG())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.FilterContainerContents.get() , "ForceAction,click");
			if (isGR())
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRFilterContainerContents.get(), "ForceAction,click");
			
			if(isMobile())
			{
				if(isFG())
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.allCatigories.get(), "ForceAction,click");
				if(isGR())
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRallCatigories.get(), "ForceAction,click");
					
					
			}
			Thread.sleep(3000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	private static void SelectFilter() throws Exception {
		try {
			getCurrentFunctionName(true);
			clickOnFilterBy();
			selectFilterName();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static int getNumberOfProducts() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<WebElement> namesWebElements = SelectorUtil.getAllElements(PLPSelectors.productsNames.get());
			int productNumbers = namesWebElements.size();
			getCurrentFunctionName(false);
			return productNumbers;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static boolean compareOperationResults(List<String> productsNames, List<String> sortedProductsNames) {
		boolean result = false;
		try {
			getCurrentFunctionName(true);
			result = !productsNames.equals(sortedProductsNames);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		return result;
	}

	// CBI
	private static void sortByPriceLowToHigh() throws Exception {

		try {
			getCurrentFunctionName(true);
			
			if(isFG()) {
				if (isMobile())
				{
					clickOnSortMenu();
				}
				SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.PriceLowToHigh.get());
			}
			if(isGR())
			{
				if(isMobile())
				{
					SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.GRMobileSorting.get(),"FFF2");
				}
			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//CBI
	private static void clickOnSortMenu() throws Exception {
		try {
		getCurrentFunctionName(true);
		SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.mobileSortingMenu.get());
		getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static List<String> getfirst3ProductsNames() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> productsName = new ArrayList<String>();
			List<WebElement> namesWebElements = SelectorUtil.getAllElements(PLPSelectors.productsNames.get());
			for (int index = 0; index < namesWebElements.size() && index < 3; index++) {
				WebElement prodname = namesWebElements.get(index);
				String prodnameStr = prodname.getText();
				productsName.add(prodnameStr);
			}
			getCurrentFunctionName(false);
			return productsName;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static boolean verifyProductImagesDisplayed() throws InterruptedException {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			boolean result =  SelectorUtil.isImgLoaded(PLPSelectors.productsImages.get());
			getCurrentFunctionName(false);
			return result ; 
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	private static boolean verifyPickedProduct(String productName) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean result;
			String productTitle = PDP.getTitle();
			result = productTitle.contains(productName);
			getCurrentFunctionName(false);
			return result;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	private static void clickSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get(), searchTerm + ",pressEnter");
			
			if(isMobile())
				Thread.sleep(5000);
			
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// CBI
	public static String pickRecommendedOption() throws Exception {
		try {
			getCurrentFunctionName(true);

			String SelectorSS = PLPSelectors.recommendedOption.get();
			WebElement recommendedProduct = SelectorUtil.getelement(SelectorSS);

			String itemTitle = recommendedProduct.getText();
			logs.debug("Picked item: " + itemTitle);
			recommendedProduct.click();

			getCurrentFunctionName(false);
			return itemTitle;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void typeSearch(String searchTerm) throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.searchBox.get(), searchTerm);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// CBI
	public static void clickSearchicon() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PLPSelectors.SearchIcon.get());
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

}
