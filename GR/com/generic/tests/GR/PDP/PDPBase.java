package com.generic.tests.GR.PDP;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.tests.GR.PDP.WistListGuestValidation;
import com.generic.tests.GR.PDP.PDPValidation;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class PDPBase extends SelTestCase {


	// possible scenarios
	public static final String singlePDP = "Validate PDP Single active elements";
	public static final String bundlePDP = "Validate PDP Bundle active elements";
	public static final String personalizedPDP = "Validate PDP Personalized active elements";
	public static final String singlePDPSearchTerm = "lights";
	public static final String BundlePDPSearchTerm = "Rugs";
	public static final String personalizedPDPSearchTerm = "personalized";
	public static final String wishListGuestValidation = "Wish List Guest Validation";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PDPSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
	}

	@DataProvider(name = "PDP_SC", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "PDP_SC")
	public void PDPTest(String caseId, String runTest, String desc, String proprties)
			throws Exception {
		Testlogs.set(new SASLogger("PDP_SC " + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName(SheetVariables.PDPCaseId);
		Testlogs.get().debug("Case Browser: " + testObject.getParameter("browserName"));
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));

		try { 

			if (proprties.contains(this.singlePDP)) {
				PDPValidation.validate(singlePDPSearchTerm);
			}
			if (proprties.contains(this.bundlePDP)) {
				PDPValidation.validate(BundlePDPSearchTerm);	
			}

     if (proprties.contains(this.personalizedPDP)) {
				PDPValidation.validate(personalizedPDPSearchTerm);	
			}
		
      if (proprties.contains(this.wishListGuestValidation)) {
                WistListGuestValidation.validate(); 
            }
			if (proprties.contains(this.personalizedPDP)) { 
				PDPValidation.validate(personalizedPDPSearchTerm);	
			} 
	 


			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver(), testDataSheet + "_" + caseId);
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test
}
