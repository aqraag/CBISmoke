package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.generic.selector.HomePageSelectors;
import com.generic.selector.PDPSelectors;
import com.generic.setup.Common;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.RandomUtilities;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {

	public static class keys {
	
		public static final String id = "id";
		public static final String name = "name";
		public static final String title = "title";
		public static final String url = "url";
		public static final String qty = "qty";
		public static final String color = "color";
		public static final String fleece = "fleece";
		public static final String memory = "memory";
		public static final String size = "size";
		public static final String bundleProducts = "bundleProducts";
		public static final String desc = "desc";
		public static final String price = "price";
		public static final String WLRandomName = "Wish list";

	}
	
	public static boolean verifyAddToCartConfirmationDisplayed() throws Exception
	{
		getCurrentFunctionName(true);
		boolean isConfirmationDisplayed=SelectorUtil.isDisplayed(PDPSelectors.continueShowppingBtn.get());
		getCurrentFunctionName(false);
		return isConfirmationDisplayed;
	}
	
	public static void clickCheckOutBtn() throws Exception {
			try {
				getCurrentFunctionName(true);
				String subStrArr = PDPSelectors.checkOutBtn.get();
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
	
	public static void clickContinueBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.continueShowppingBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static boolean isDisplayedAddToCardBtn() throws Exception {
		getCurrentFunctionName(true);
		boolean isDiplayedBtn=SelectorUtil.isDisplayed(PDPSelectors.addToCartBtn.get());
		getCurrentFunctionName(false);
		return isDiplayedBtn;
	}

	// done - SMK
	public static String NavigateToPDP(String SearchTerm) throws Exception {
		getCurrentFunctionName(true);
		// This is to handle production Monetate issue on iPad for search field.
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPad))
			HomePage.updateMmonetate();
		PLP.clickSearchicon();
		PLP.typeSearch(SearchTerm);
		String itemName = PLP.pickRecommendedOption();
		getCurrentFunctionName(false);
		return itemName;
	}
	
	public static void NavigateToPDP() throws Exception {
		getCurrentFunctionName(true);
		String SearchTerm = "Rugs";
		NavigateToPDP(SearchTerm);
		getCurrentFunctionName(false);
	}

	// done - SMK
	public static void selectSize() throws Exception {
		try {
			getCurrentFunctionName(true);
			Thread.sleep(1000);
			String subStrArr = PDPSelectors.allSizes.get();
			String valuesArr = "FFF1";
			if (!SelectorUtil.isNotDisplayed(subStrArr)) {
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done - SMK
	public static void clickAddToCartButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToCartBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		} 
	}

	// done - SMK
	public static void clickAddToCartCloseBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToCartCloseBtn.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	// This method to return all available options except the sizes dropdown
	// Size drop-down has a different selector
	public static int getNumberOfOptions() throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = PDPSelectors.avaibleOptions.get();
		int numberOfAvaibleOptions = 0;
		if (!SelectorUtil.isNotDisplayed(subStrArr)) {
			numberOfAvaibleOptions = SelectorUtil.getAllElements(subStrArr).size();
		}
		logs.debug("number Of Avaible Options" + numberOfAvaibleOptions);
		getCurrentFunctionName(false);
		return numberOfAvaibleOptions;
	}

	// done - SMK
	// This method to return all available List Boxes
	public static int getNumberListBoxes() throws Exception {
		getCurrentFunctionName(true);
		String Str = PDPSelectors.allSizes.get();
		int numberOfListBoxes = 0;
		if (!SelectorUtil.isNotDisplayed(Str)) {
			SelectorUtil.initializeSelectorsAndDoActions(Str);
			numberOfListBoxes = SelectorUtil.getAllElements(Str).size();
		}
		logs.debug("number Of Avaible List Boxes" + numberOfListBoxes);
		getCurrentFunctionName(false);
		return numberOfListBoxes;
	}
	
// done - SMK
	public static void selectNthListBoxFirstValue(int index) throws Exception {
		getCurrentFunctionName(true);
		String Str = PDPSelectors.allSizes.get();
		String value = "index," + index + ",FFF1";
		SelectorUtil.initializeSelectorsAndDoActions(Str, value);
		getCurrentFunctionName(false);
	}
	
	// done - SMK
		public static void selectNthOptionFirstSwatch(int index) throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = MessageFormat.format(PDPSelectors.firstSwatchInOptions.get(), index);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, subStrArr));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		// Clicking on the div on desktop and iPad does not select the options,
		// you need to click on the img if there is an img tag.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			String nthSel = subStrArr + ">img";
			if (!SelectorUtil.isNotDisplayed(nthSel))
				SelectorUtil.initializeSelectorsAndDoActions(nthSel);
		}
		getCurrentFunctionName(false);

	}
	
	// done - SMK
	public static void selectNtSwatchNthOption(int swatchIndex, int optionIndex ) throws Exception {
		getCurrentFunctionName(true);
		String subStrArr = MessageFormat.format(PDPSelectors.imageOption.get(), swatchIndex, optionIndex);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, subStrArr));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
		// Clicking on the div on desktop and iPad does not select the options,
		// you need to click on the img if there is an img tag.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			String nthSel = subStrArr + ">img";
			if (!SelectorUtil.isNotDisplayed(nthSel))
				SelectorUtil.initializeSelectorsAndDoActions(nthSel);
		}
		getCurrentFunctionName(false);

	}

	// done - SMK
	// This method to dynamically select all available options
	// to be merged with selectSwatches function
	public static void selectSwatchesSingle() throws Exception {
		try {
			getCurrentFunctionName(true);
			int numberOfPanels = getNumberOfOptions();
			int numberOfListBoxes = getNumberListBoxes();
			if (!validateAddToCartIsNotDisabled()) {
				if (numberOfPanels != 0) {
					for (int i = 1; i <= numberOfPanels; i++) {
						selectNthOptionFirstSwatch(i);
						Thread.sleep(1500);
					}
				}

				if (numberOfListBoxes != 0) {
					for (int i = 0; i < numberOfListBoxes; i++) {
						selectNthListBoxFirstValue(i);
						Thread.sleep(1500);
					}
				}
			}
			getCurrentFunctionName(false);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void selectNthListBoxFirstActiveValueSinglePDP(int Listindex) throws Exception {
//		List<WebElement> items = new ArrayList<WebElement>();
		try {
			String selector = PDPSelectors.ListBox.get();
			String index = "index," + Listindex;
			SelectorUtil.selectActiveOption(selector, index);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void selectNthListBoxFirstActiveValueBundlePDP(String Str, int Listindex) throws Exception {
//		List<WebElement> items = new ArrayList<WebElement>();
		try {
			String ProductID = getProductID(0);
			String selector = MessageFormat.format(PDPSelectors.ListBoxBundle, ProductID);
			String index = "index," + Listindex;
			SelectorUtil.selectActiveOption(selector, index);

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static int getAllOptionsSizeForSwatch(int index) throws Exception {

		int allOptionsSize = SelectorUtil
				.getAllElements(MessageFormat.format(PDPSelectors.allImageOptions.get(), index)).size();
		return allOptionsSize;

	}

	// done - SMK
		public static void addProductsToCart() throws Exception {
			getCurrentFunctionName(true);
			selectSwatches();
			clickAddToCartButton();

			if (PDP.bundleProduct() &&  getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				closeModalforBundleItem();
			}

			Thread.sleep(1000);
			getCurrentFunctionName(false);
		}


		// Done CBI
		public static void closeModalforBundleItem() throws Exception {
			getCurrentFunctionName(true);	
			SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.closeBundleProductModal.get());	
			getCurrentFunctionName(false);


		}


	// done - SMK
	public static boolean validatePriceIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		try {
			boolean isDisplayed;
			logs.debug("Validate if top price exist");
			String selector = PDPSelectors.topPriceSingle.get();
			if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				String ProductID = getProductID(0);
				logs.debug(PDPSelectors.topPriceBundle);
				selector = MessageFormat.format(PDPSelectors.topPriceBundle, ProductID);
			}
			isDisplayed = SelectorUtil.isDisplayed(selector);
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done - SMK
	public static boolean validateExpiredPDPMsgIsNotDisplayedSinglePDP() throws Exception {
		getCurrentFunctionName(true);
		try {
			boolean isNotDisplayed;
			logs.debug("Validate PDP is sold out");
			String selector = PDPSelectors.expiredPDP.get();
			isNotDisplayed = SelectorUtil.isNotDisplayed(selector);
			getCurrentFunctionName(false);
			return isNotDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static boolean validateExpiredPDPMsgIsNotDisplayedBundlePDP() throws Exception {
		getCurrentFunctionName(true);
		try {
			boolean isNotDisplayed;
			logs.debug("Validate PDP is sold out");
			String ProductID = getProductID(0);
			logs.debug(PDPSelectors.expiredPDPBundle);
			String selector = MessageFormat.format(PDPSelectors.topPriceBundle, ProductID);
			isNotDisplayed = SelectorUtil.isNotDisplayed(selector);
			getCurrentFunctionName(false);
			return isNotDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static boolean validateBundlePriceIsDisplayed() throws Exception { 
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if top price exist for Bundle PDP");
		isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.topPriceBundleDesktop.get());

		getCurrentFunctionName(false);
		return isDisplayed;
	}

	// done - SMK
	public static boolean validateAddToWLGRIsEnabled() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		logs.debug("Validate if Add To WL/GR Is Displayed");
		// here it will pass if the button exist regardless if it is enabled or
		// disabled.
		// because there is no attribute to verify if it is enabled.
		String selectorEnabled = PDPSelectors.addToWLGRBtnEnabledSingle.get();
		String selectorDisabled = PDPSelectors.addToCartBtnDisabledSingle.get();
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && getNumberOfItems() > 1) {
			String ProductID = getProductID(0);
			logs.debug(PDPSelectors.addToWLGRBtnEnabledBundle);
			selectorEnabled= MessageFormat.format(PDPSelectors.addToWLGRBtnEnabledBundle, ProductID);
			logs.debug(PDPSelectors.addToCartBtnDisabledBundle);
			selectorDisabled= MessageFormat.format(PDPSelectors.addToCartBtnDisabledBundle, ProductID);
		}
		SelectorUtil.isDisplayed(selectorEnabled);
		logs.debug("Validate if Add To WL/GR Is not disabled");
		isNotDisplayed = SelectorUtil.isNotDisplayed(selectorDisabled);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	// done - SMK
	public static boolean validateAddToCartIsEnabled() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		logs.debug("Validate if Add To Cart Is Displayed");
		// here it will pass if the button exist regardless if it is enabled or
		// disabled.
		// because there is no attribute to verify if it is enabled.
		String selectorEnabled = PDPSelectors.addToCartBtnEnabledSingle.get();
		String selectorDisabled = PDPSelectors.addToCartBtnDisabledSingle.get();

    if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && getNumberOfItems() > 1) {
			String ProductID = getProductID(0);

			logs.debug(PDPSelectors.addToCartBtnEnabledBundle);
			selectorEnabled= MessageFormat.format(PDPSelectors.addToCartBtnEnabledBundle, ProductID);	
			logs.debug(PDPSelectors.addToCartBtnDisabledBundle);
			selectorDisabled= MessageFormat.format(PDPSelectors.addToCartBtnDisabledBundle, ProductID);
		}
		SelectorUtil.isDisplayed(selectorEnabled);
		logs.debug("Validate if Add To Cart Is not disabled");
		isNotDisplayed = SelectorUtil.isNotDisplayed(selectorDisabled);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}
	
	// done - SMK
	public static boolean validateAddToCartIsNotDisabled() throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed;
		String selectorDisabled = PDPSelectors.addToCartBtnDisabledSingle.get();
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && getNumberOfItems() > 1) {
			String ProductID = getProductID(0);	
			logs.debug(PDPSelectors.addToCartBtnDisabledBundle);
			selectorDisabled= MessageFormat.format(PDPSelectors.addToCartBtnDisabledBundle, ProductID);
		}
		logs.debug("Validate if Add To Cart Is disabled");
		isNotDisplayed = SelectorUtil.isNotDisplayed(selectorDisabled);
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}

	// done - SMK
	public static String getBottomPrice() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Validate if bottom price is updated after seleting options");
		String selector = PDPSelectors.bottomPriceSingle.get();
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && getNumberOfItems() > 1 ) {
			String ProductID = getProductID(0);
			selector= MessageFormat.format(PDPSelectors.bottomPriceBundle, ProductID);
		}
		SelectorUtil.initializeSelectorsAndDoActions(selector);
		String price = SelectorUtil.textValue.get();
		getCurrentFunctionName(false);
		return price;
	}

	// done - SMK
	public static boolean validateProductIsAddedToCart() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		// Validate the add to cart modal is displayed for Desktop and iPad.
		// For Mobile, verify it from mini cart because there is no add to cart modal in
		// mobile.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.addToCartModal.get());
		} else {
			HomePage.clickOnMiniCart();
			isDisplayed = HomePage.validateMiniCartProductIsDsiplayed();
		}
		return isDisplayed;
	}

	// done - SMK
	public static void clickAddToWLGR() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.addToWLGRBtnEnabled.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static String getProductID(int index) throws Exception {
		try {
			getCurrentFunctionName(true);
			String Str = PDPSelectors.itemsID.get();
			String ID = SelectorUtil.getAttrString(Str, "id", index);
			getCurrentFunctionName(false);
			return ID;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static int getNumberOfItems() throws Exception {
		try {
			getCurrentFunctionName(true);
			String Str = PDPSelectors.numberOfBundleItems.get();
			int numberOfItems = 0;
			if (!SelectorUtil.isNotDisplayed(Str)) {
			numberOfItems = SelectorUtil.getAllElements(Str).size();
			}
			logs.debug("Number of Items: " + numberOfItems);
			if(bundleProduct() && numberOfItems == 1) {
				logs.debug("This is a bundle product with one item");	
				numberOfItems = 2;
			}
			getCurrentFunctionName(false);
			return numberOfItems;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// done - SMK
	public static boolean bundleProduct() throws Exception {
		getCurrentFunctionName(true);
		try {	
		SelectorUtil.isDisplayed(PDPSelectors.bundleItem.get());
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
	}

	// done - SMK
	public static int getNumberOfListsInProduct(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
			// String Str = PDPSelectors.ListBox.get();
			int numberOfItems = 0;
			if (!SelectorUtil.isNotDisplayed(Str)) {
			numberOfItems = SelectorUtil.getAllElements(Str).size();
			}
			logs.debug("Number of Lists In Product: " + numberOfItems);
			getCurrentFunctionName(false);
			return numberOfItems;

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static int getNumberOfActiveListsInProduct(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
			// String Str = PDPSelectors.ListBox.get();
			int numberOfActiveLists = 0;
			if (!SelectorUtil.isNotDisplayed(Str)) {
				numberOfActiveLists = SelectorUtil.getAllElements(Str).size();
			}
			logs.debug("Number of Active Lists: " + numberOfActiveLists);
			getCurrentFunctionName(false);
			return numberOfActiveLists;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void selectNthOptionFirstSwatchBundle(String Str) throws Exception {
		getCurrentFunctionName(true);
		// String StrBundle = MessageFormat.format(Str, index);
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, Str));
		SelectorUtil.initializeSelectorsAndDoActions(Str);
		// Clicking on the div on desktop and iPad does not select the options,
		// you need to click on the img if there is an img tag.
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			String nthSel = Str + ">img";
			if (!SelectorUtil.isNotDisplayed(nthSel))
				SelectorUtil.initializeSelectorsAndDoActions(nthSel);
		}
		getCurrentFunctionName(false);

	}

	// done - SMK
	public static void selectNthListBoxFirstValueBundle(String Str, int index) throws Exception {
		try {
			getCurrentFunctionName(true);
			String value = "index," + index + ",FFF1";
			SelectorUtil.initializeSelectorsAndDoActions(Str, value);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	// done - SMK
	public static int getNumberOfimageOptionsInProduct(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
			int numberOfAvaibleOptions = 0;
			if (!SelectorUtil.isNotDisplayed(Str)) {
				numberOfAvaibleOptions = SelectorUtil.getAllElements(Str).size();
			}
			logs.debug("number Of Avaible Options" + numberOfAvaibleOptions);
			getCurrentFunctionName(false);
			return numberOfAvaibleOptions;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static boolean activeLists(String Str) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isNotDisplayed = false;
			isNotDisplayed = SelectorUtil.isNotDisplayed(PDPSelectors.activeListBox.get());
			return isNotDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done - SMK
	public static void selectSwatches() throws Exception {
		try {
			getCurrentFunctionName(true);

			if (!validateAddToCartIsNotDisabled()) {
				if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
					String ProductID = getProductID(0);
					String ListSelector = MessageFormat.format(PDPSelectors.ListBoxBundle, ProductID);
					String activeLists = MessageFormat.format(PDPSelectors.activeListBoxBundle, ProductID);
					String swatchContainerSelector = MessageFormat.format(PDPSelectors.swatchContainerBundle,
							ProductID);
			//		String imageOptionSelector = MessageFormat.format(PDPSelectors.imageOptionBundle, ProductID);
					int numberOfActiveListBoxes = 0;
					int i = 0;
					int listIndex = 0;
					int numberOfListBoxes = 0;
					int index = 0;
					if (!activeLists(activeLists)) {
						numberOfActiveListBoxes = getNumberOfListsInProduct(activeLists);
						numberOfListBoxes = getNumberOfListsInProduct(ListSelector);
						for (; i < numberOfListBoxes; i++) {
							listIndex++;
							index++;
							selectNthListBoxFirstValueBundle(ListSelector, i);
							Thread.sleep(500);
							int numberOfNewActiveListBoxes = getNumberOfListsInProduct(activeLists);
							if (numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
								numberOfActiveListBoxes = numberOfNewActiveListBoxes;
							} else {
								break;
							}
						}
					}
					int numberOfimageOptions = getNumberOfimageOptionsInProduct(swatchContainerSelector);
					if (numberOfimageOptions != 0) {
						for (index++; index <= numberOfimageOptions + listIndex; index++) {
							selectNthOptionFirstSwatchBundle("css,#" + ProductID + ">" + MessageFormat
									.format(PDPSelectors.imageOption.get(), index, 1).replace("css,", ""));
							Thread.sleep(500);
						}
					}
					if (!activeLists(activeLists)) {
						int numberOfNewActiveListBoxes = getNumberOfListsInProduct(activeLists);
						if (numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
							for (int j = listIndex; j < numberOfListBoxes; j++) {
								selectNthListBoxFirstActiveValueBundlePDP(ListSelector,j);
								if (numberOfNewActiveListBoxes > numberOfActiveListBoxes) {
									numberOfActiveListBoxes = numberOfNewActiveListBoxes;
								} else {
									break;
								}
							}
						}
					}

				} else {
					selectSwatchesSingle();
				}
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// Done SMK
	public static void clickBundleItems() throws Exception {
		getCurrentFunctionName(true);
		String selector = PDPSelectors.bundleItems.get();
		logs.debug("Clicking on any bundle item");
		if (!SelectorUtil.isNotDisplayed(selector)) {
			SelectorUtil.initializeSelectorsAndDoActions(selector);
		}
	}

	// Done SMK
	public static boolean validateMobileBundlePriceIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate if top price exist");
		String selector = PDPSelectors.miniPDPPrice.get();
		isDisplayed = SelectorUtil.isDisplayed(selector);
		getCurrentFunctionName(false);
		return isDisplayed;
	}
	
public static boolean validateSelectRegistryOrWishListModalIsDisplayed() throws Exception {
        getCurrentFunctionName(true);
        boolean isDisplayed;
        logs.debug("Validate select a registry or wish list modal exist");
        isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.SelectRegistryOrWishListModal.get());
        getCurrentFunctionName(false);
        return isDisplayed;
    }
    
    public static void clickOnCreateNewWL() throws Exception{
        getCurrentFunctionName(true);
        logs.debug("Click on create new wish list");
        WebElement element = SelectorUtil.getelement(PDPSelectors.giftRegistryListBox.get());
        WebElement option =  element.findElement(By.cssSelector(PDPSelectors.createNewWL.get()));
        option.click();
        clickOnCreateNewWLConfirmationBtn();
        getCurrentFunctionName(false);
    }
    
    public static boolean validateNameYourNewWLModalIsDisplayed() throws Exception {
        getCurrentFunctionName(true);
        boolean isDisplayed = true;
        logs.debug("Validate Name your new wish list modal exist");
        isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.nameYourNewWL.get());
        getCurrentFunctionName(false);
        return isDisplayed;
    }
    
    public static void createNewWL(String WLName) throws Exception {
        getCurrentFunctionName(true);       
            SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.WLName.get(), WLName);
            SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.nameYourNewWLconfirmationBtn.get());
            getCurrentFunctionName(false);      
    }
    
    public static String getWishListName() {
            int random = (int )(Math.random() * 1000000 + 1);
            String WLName = keys.WLRandomName + random;
            return WLName;
    }
   
    public static boolean validateCreatedWLisSelectedByDefault(String createdWL) throws Exception {
        getCurrentFunctionName(true);
        boolean isSelected = true;
        Select element = new Select(SelectorUtil.getelement(PDPSelectors.giftRegistryListBox.get()));
        WebElement option = element.getFirstSelectedOption();
        String defaultWL = option.getText();
        String selectedWL =  defaultWL;
        selectedWL = selectedWL.replaceAll("\"", "");
        if(selectedWL.equals(createdWL)) {
            isSelected = true;
        }else {
            isSelected = false;
        }
        getCurrentFunctionName(false);
        return isSelected;
    }
    
    public static void clickOnCreateNewWLConfirmationBtn() throws Exception{
        getCurrentFunctionName(true);
        logs.debug("Click on create new wish list");
        SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.createNewWLConfirmationBtn.get());
        getCurrentFunctionName(false);
    }
    
    public static boolean validateConfirmationModalWithCorrectProductIsDisplayed(String selectedProductName) throws Exception {
        getCurrentFunctionName(true);
        boolean isDisplayed;
        logs.debug("Validate confirmation modal exist");
        isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.confirmationModal.get());
        logs.debug("Validate confirmation modal exist menu" +isDisplayed +"   " +selectedProductName);

        WebElement addToCardProductElement = SelectorUtil.getelement(PDPSelectors.addToCardProductName.get());
        if(addToCardProductElement.getText().equals(selectedProductName) &&
                SelectorUtil.isDisplayed(PDPSelectors.viewListBtn.get()) 
                && isDisplayed) {
        SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.viewListBtn.get());

        isDisplayed = true;
        }else {
            isDisplayed = false;
        }
        logs.debug("Validate confirmation modal exist" +isDisplayed );
        getCurrentFunctionName(false);
        return isDisplayed;
    }
    
    
    public static boolean addedProductIsDisplayedInTheWL(String addedProductName) throws Exception {
        getCurrentFunctionName(true);
        boolean isDisplayed = true;
        List<WebElement> products = SelectorUtil.getElementsList(PDPSelectors.addedProductName.get());
        List<WebElement> addToCartBtns = SelectorUtil.getElementsList(PDPSelectors.myWLAddToCartBtn.get());
        for(int i = 0;i< products.size() ; i++) {
            if(products.get(i).getText().contains(addedProductName)) {
                addToCartBtns.get(i).click();
                return true;
            }
        }
        getCurrentFunctionName(false);
        return isDisplayed;
    }
    
	public static void clickOnCheckout() throws Exception {
		getCurrentFunctionName(true);
		logs.debug("Clicking on Checkout");
		SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.addToCartConfirmationModalCheckoutBtn.get());
		getCurrentFunctionName(false);
	}
	
	public static boolean validateAddToCartModalIsDisplayed() throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed;
		logs.debug("Validate add to cart confirmation modal displayed");
		isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.addToCartConfirmationModal.get());
		getCurrentFunctionName(false);
		return isDisplayed;
	}
	
	public static boolean addedProductIsDisplayedInShoppingCart(String addedProductName) throws Exception {
		getCurrentFunctionName(true);
		boolean isDisplayed = true;
		List<WebElement> products = SelectorUtil.getElementsList(PDPSelectors.shoppingCartProductsName.get());

        for(int i = 0;i< products.size() ; i++) {
        	if(products.get(i).getText().contains(addedProductName)) {
        		return true;
        	}  	
        }
		getCurrentFunctionName(false);
		return isDisplayed;
	} 

	


	// done-ocm
	public static String getTitle() throws Exception {
		try {
			getCurrentFunctionName(true);
			SelectorUtil.initializeSelectorsAndDoActions(PDPSelectors.title.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

		public static void hoverMiniCart() throws Exception {
		getCurrentFunctionName(true);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement field = getDriver().findElement(By.cssSelector(HomePageSelectors.miniCartBtn.get().replace("css,", "")));

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", field);
		Thread.sleep(200);
		WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector(HomePageSelectors.miniCartBtn.get().replace("css,", "")));
			}
		});

		Actions HoverAction = new Actions(getDriver());
		HoverAction.moveToElement(field2).click().build().perform();
		getCurrentFunctionName(false);
	}
	
	//Done SMK
	public static boolean PersonalizedItem() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isDisplayed = false;
			String addPersonalizedButtonSelector = PDPSelectors.addPersonalizedButton.get();
			if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				String ProductID = getProductID(0);
				addPersonalizedButtonSelector = "css,#" + ProductID + ">"
						+ PDPSelectors.addPersonalizedButton.get().replace("css,", "");
				logs.debug("addPersonalizedButtonSelector:  " + addPersonalizedButtonSelector);
			}
			SelectorUtil.isDisplayed(addPersonalizedButtonSelector);
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static void clickAddPersonalizationButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			String addPersonalizedButtonSelector = PDPSelectors.addPersonalizedButton.get(); 
			if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				String ProductID = getProductID(0);
		    addPersonalizedButtonSelector = "css,#" + ProductID + ">" + PDPSelectors.addPersonalizedButton.get().replace("css,", "");
			logs.debug("addPersonalizedButtonSelector:  " + addPersonalizedButtonSelector); 
			}

			SelectorUtil.initializeSelectorsAndDoActions(addPersonalizedButtonSelector);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	public static boolean isFreePersonalization() throws Exception {// check if add personalization free or not 
		getCurrentFunctionName(true);
		boolean isFree = true;
		String addPersonalizedButtonSelector = PDPSelectors.personlizedTitle.get();// for iPhone
		if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
			addPersonalizedButtonSelector = PDPSelectors.addPersonalizedButton.get();// for single PDP
			if (getNumberOfItems() > 1) {// for bundle PDP
	     	String ProductID = getProductID(0);
	        addPersonalizedButtonSelector = "css,#" + ProductID + ">" + PDPSelectors.addPersonalizedButton.get().replace("css,", "");
			
			}
		}
		WebElement element = SelectorUtil.getelement(addPersonalizedButtonSelector);
		String personalizationText = element.getText().toLowerCase();
		logs.debug("personalizationText:  " + personalizationText);

		if (!personalizationText.contains("free")) {
			isFree = false; 
		}
		logs.debug("isFreePersonalization: " + isFree);
		getCurrentFunctionName(false);
		return isFree;

	}
	
	public static void clickPersonalizationSaveAndCloseButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			String subStrArr = PDPSelectors.personalizationSaveAndCloseButton.get();
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	public static void clickPersonalizationSaveAndCloseButtonForiPhone() throws Exception {
		List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.personalizedItems.get());
		WebElement element = elementsList.get(elementsList.size() - 1);
		SelectorUtil.clickOnWebElement(element);
		clickPersonalizationSaveAndCloseButton();
	}

		
	public static boolean isPersonalizedStyle() throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.personlizedStyleItem.get());
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isPersonalizedInputSwatchesDisplayed(String value) throws Exception {
		try {
			getCurrentFunctionName(true);
			boolean isDisplayed = SelectorUtil.isDisplayed(value);
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static void selectPersonalizationModalSwatchesForiPhone() throws Exception {
		getCurrentFunctionName(true);
	    closeOpendItem();
		List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.personalizedItems.get());
		for (int i = 0; i < elementsList.size()-1; i++) {
			WebElement element = elementsList.get(i);
			SelectorUtil.clickOnWebElement(element);
			if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedInputValue.get())) {// input container like MONOGRAM or any value
				WebElement input = SelectorUtil.getelement(PDPSelectors.personalizedInputValue.get());
				input.sendKeys(RandomUtilities.getRandomStringWithLength(3));
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemColors1.get())) { // like item color 
				List<WebElement> itemColors = SelectorUtil.getAllElements(PDPSelectors.personalizedItemColors1.get());
				if (itemColors.size() > 0) {
					WebElement firstItemColor = itemColors.get(0);
					SelectorUtil.clickOnWebElement(firstItemColor);			
				}
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemMenu.get())) {// like item size 
				WebElement menu = SelectorUtil.getelement(PDPSelectors.personalizedItemMenu.get());
				List<WebElement> options =  menu.findElements(By.cssSelector(PDPSelectors.personalizedMenuOptions.get()));
				options.get(1).click();// the first item is selected size 
			}
		}
		getCurrentFunctionName(false);
	}

	
	public static void selectPersonalizationModalSwatches() throws Exception {
		getCurrentFunctionName(true);
		closeOpendItem();
		List<WebElement> elementsList = SelectorUtil.getAllElements(PDPSelectors.personalizedItems.get());

		for (int i = 0; i < elementsList.size(); i++) {
			WebElement element = elementsList.get(i);
			SelectorUtil.clickOnWebElement(element);
			if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedInputValue.get())) {// input container like MONOGRAM or any value
				WebElement input = SelectorUtil.getelement(PDPSelectors.personalizedInputValue.get());
				input.sendKeys(RandomUtilities.getRandomStringWithLength(3));
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemColors1.get())) { // like item color 
				List<WebElement> itemColors = SelectorUtil.getAllElements(PDPSelectors.personalizedItemColors1.get());
				if (itemColors.size() > 0) {
					WebElement firstItemColor = itemColors.get(0);
					SelectorUtil.clickOnWebElement(firstItemColor);			
				}
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemColors2.get())){ // like thread color 
				List<WebElement> itemColors = SelectorUtil.getAllElements(PDPSelectors.personalizedItemColors2.get());
				if (itemColors.size() > 0) { 
					WebElement firstItemColor = itemColors.get(0);
					SelectorUtil.clickOnWebElement(firstItemColor);	
				}
				
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedTypeFaces.get())) {// like TypeFace , English style , etching style (Roman
				List<WebElement> items = SelectorUtil.getAllElements(PDPSelectors.personalizedTypeFaces.get());
				if (items.size() > 0) {
					WebElement firstItemColor = items.get(0);
					SelectorUtil.clickOnWebElement(firstItemColor);	
				}
			}else if (isPersonalizedInputSwatchesDisplayed(PDPSelectors.personalizedItemMenu.get())) {// like item size 
				WebElement menu = SelectorUtil.getelement(PDPSelectors.personalizedItemMenu.get());
				List<WebElement> options =  menu.findElements(By.cssSelector(PDPSelectors.personalizedMenuOptions.get()));
				options.get(1).click();// the first item is selected size 
			}
			//Thread.sleep(1000);
		}
		getCurrentFunctionName(false);
	}
	
	public static void closeOpendItem( )throws Exception {
		try {
			WebElement e = SelectorUtil.getelement(PDPSelectors.personalizedOpenItem.get());
			SelectorUtil.clickOnWebElement(e);
		} catch (NoSuchElementException e) {
		}
		
	}
	
	public static boolean validateTotalPriceAfterAddedPersonalized(String intialPrice , String finalPrice) throws Exception {
		boolean isChanged = true;
		if (intialPrice.equals(finalPrice)) {
			isChanged = false;
		}
		return isChanged;

	}

	public static boolean validateAddedPersonalizedDetails() throws Exception {
		getCurrentFunctionName(true);
		boolean isAdded = true;
		String addedPersonlizedDetailsSelector  =  PDPSelectors.addedPersonlizedDetails.get();
		if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) ) {
		String ProductID = getProductID(0);
	    addedPersonlizedDetailsSelector = "css,#" + ProductID + ">" + PDPSelectors.addedPersonlizedDetails.get().replace("css,", "");
		}
	    List<WebElement> addedPersonlizedDetailsItems = SelectorUtil.getAllElements(addedPersonlizedDetailsSelector);
        if (addedPersonlizedDetailsItems.size() <= 0) {
        	isAdded = false;
         }
		getCurrentFunctionName(false);

        return isAdded;
	}
	
	public static boolean validatePersonalizedModal() throws Exception {
		boolean isDisplayed = true;
		isDisplayed = SelectorUtil.isDisplayed(PDPSelectors.personlizedModal.get());
		return isDisplayed;

	}
	
	public static String getTotalPriceAfterAddedPersonalized() throws Exception {// in GR : total bottom price doesn't change after added personalized .based on the discussion with Emad , I compare the total price in personalized details with bottom price to make sure the price is changed 
		getCurrentFunctionName(true);
		String addedPersonlizedDetailsSelector  =  PDPSelectors.addedPersonlizedDetails.get();
		if (getNumberOfItems() > 1 && !SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) ) {
		String ProductID = getProductID(0);
	    addedPersonlizedDetailsSelector = "css,#" + ProductID + ">" + PDPSelectors.addedPersonlizedDetails.get().replace("css,", "");
		}
	    List<WebElement> addedPersonlizedDetailsItems = SelectorUtil.getAllElements(addedPersonlizedDetailsSelector);
        WebElement totalPriceElement = addedPersonlizedDetailsItems.get(addedPersonlizedDetailsItems.size() - 1);
        String totalPrice = totalPriceElement.getText();
		logs.debug("totalPriceElement:  " + totalPrice);

        
		getCurrentFunctionName(false);
		return totalPrice;
	}



}