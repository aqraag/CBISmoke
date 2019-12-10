package com.generic.tests.GR.PDP;

import com.generic.page.PDP;
import com.generic.setup.Common;
import com.generic.setup.GlobalVariables;
import com.generic.setup.SelTestCase;

public class PDPValidation extends SelTestCase {

	public static void validate(String searchTerm) throws Exception {
		getCurrentFunctionName(true);
		// To be removed after R1 build, this is to handle blank page in mobile for new session.
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone))
			Common.refreshBrowser();
		PDP.NavigateToPDP(searchTerm);
		int numberOfItems = PDP.getNumberOfItems();
		String priceErrorMessage; 
		// price error message
		// for single PDP, validate the price is displayed below the title of the page for both desktop and mobile
		// for bundle PDP Desktop, validate the top price is displayed for the collection. (this is not displayed in mobile).
		// for bundle PDP mobile and desktop,validate the prices are displayed in bundle landing page for all items.
		if (numberOfItems == 1) {
			priceErrorMessage = "Top price is not dispayed";
		} else if (!SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && numberOfItems > 1) {
			sassert().assertTrue(PDP.validateBundlePriceIsDisplayed(), "Bundle Price is not dispayed");
			priceErrorMessage = "Top price for the bundle items are not dispayed";
		} else {
			priceErrorMessage = "Price for the bundle items are not dispayed";
		}
	 	sassert().assertTrue(PDP.validatePriceIsDisplayed(), priceErrorMessage);

		// for bundle PDP mobile, validate the price is displayed in mini PDP page
		if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone) && numberOfItems > 1) {
			PDP.clickBundleItems();
			sassert().assertTrue(PDP.validateMobileBundlePriceIsDisplayed(),
					"Top price for the bundle item (mini PDP) is not dispayed");
		}

		PDP.selectSwatches();
		sassert().assertTrue(!PDP.getBottomPrice().equals("$0.00"), "Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());

		// click add personalized button
		if (PDP.PersonalizedItem()) {
			String initialPrice = PDP.getBottomPrice();
			boolean isFreePersonalization = PDP.isFreePersonalization();
			PDP.clickAddPersonalizationButton();
			sassert().assertTrue(PDP.validatePersonalizedModal(), "Personalization Modal is not dispayed");
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone)) {
				PDP.selectPersonalizationModalSwatchesForiPhone();
				PDP.clickPersonalizationSaveAndCloseButtonForiPhone();
			} else {
				PDP.selectPersonalizationModalSwatches();
				PDP.clickPersonalizationSaveAndCloseButton();
			}
			sassert().assertTrue(PDP.validateAddedPersonalizedDetails(),
					"Added personalization details is not dispayed");
			if (!isFreePersonalization) {
				String finalPrice = PDP.getTotalPriceAfterAddedPersonalized(); // take final price after added
																				// personalization
				logs.debug("compare price" + initialPrice + finalPrice);
				sassert().assertTrue(PDP.validateTotalPriceAfterAddedPersonalized(initialPrice, finalPrice),
						"Bottom price is not updated correctly, Current price: " + PDP.getBottomPrice());
			}
		}
		sassert().assertTrue(PDP.validateAddToWLGRIsEnabled(), "Add to WL/GR button is not enabled");
		sassert().assertTrue(PDP.validateAddToCartIsEnabled(), "Add to Cart button is not enabled");
		PDP.clickAddToCartButton();
		sassert().assertTrue(PDP.validateProductIsAddedToCart(), "Product is not added successfully");
		getCurrentFunctionName(false);
	}
}
