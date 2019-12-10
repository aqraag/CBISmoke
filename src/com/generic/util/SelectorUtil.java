package com.generic.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.generic.setup.SelTestCase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import com.beust.jcommander.internal.Lists;
import com.generic.selector.SignInSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.GlobalVariables;
import com.generic.setup.LoggingMsg;

public class SelectorUtil extends SelTestCase {

	public static Boolean isAnErrorSelector = Boolean.FALSE;
	// public static String textValue;
	public static ThreadLocal<String> textValue = new ThreadLocal<String>();
	public static ThreadLocal<Screenshot> screenShot = new ThreadLocal<Screenshot>();
	// public static int numberOfFoundElements;
	public static ThreadLocal<String> numberOfFoundElements = new ThreadLocal<String>();
	private static By parentBy = null;

	@SuppressWarnings("rawtypes")
	public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo,
			boolean isValidationStep) throws IOException, InterruptedException {
		Thread.sleep(500);
		try {
			Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			Element htmlDoc = doc.select("html").first();
			initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, htmlDoc);
		} catch (NoSuchElementException e) {
			Thread.sleep(10000);
			if (SelTestCase.getBrowserName().contains(GlobalVariables.browsers.firefox))
				Thread.sleep(5000);
			logs.debug("Second try for getting element");
			Document doc = Jsoup.parse(SelTestCase.getDriver().getPageSource());
			Element htmlDoc = doc.select("html").first();
			initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, htmlDoc);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initializeElementsSelectorsMaps(LinkedHashMap<String, LinkedHashMap> webElementsInfo,
			boolean isValidationStep, Element htmlDoc) throws IOException {
		getCurrentFunctionName(true);
		Elements foundElements = null;
		String selectorType = null;

		for (String subStr : webElementsInfo.keySet()) {
			if (subStr.contains("error") && !isAnErrorSelector && isValidationStep) {
				isAnErrorSelector = Boolean.TRUE;
			}
			foundElements = htmlDoc.select("[id=" + subStr + "]");
			selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact class"));
				foundElements = htmlDoc.select("[class=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "i class"));
				foundElements = htmlDoc.select("[class=" + subStr + " i]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "exact name"));
				foundElements = htmlDoc.select("[name=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "name" : selectorType);
			}

			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*id"));
				// use regular expression (register.)?firstName for example
				foundElements = htmlDoc.select("[id~=(register.)?" + subStr + "$]");
				selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug("in *id");
				foundElements = htmlDoc.select("[id*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "id" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// use * to mean contains
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*class"));
				foundElements = htmlDoc.select("[class*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "class" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*name"));
				foundElements = htmlDoc.select("[name*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "name" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "*title"));
				foundElements = htmlDoc.select("[title*=" + subStr + "]");
				selectorType = (!(foundElements.isEmpty()) ? "title" : selectorType);
			}
			if (foundElements.isEmpty()) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "xpath"));
				foundElements = htmlDoc.select("*:containsOwn(" + subStr + ")");
				selectorType = (!(foundElements.isEmpty()) ? subStr : selectorType);
			}

			if (foundElements.isEmpty() && subStr.contains("css,")) {
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE, "CSS"));
				foundElements = htmlDoc.select(subStr.replace("css,", ""));
				selectorType = (!(foundElements.isEmpty()) ? subStr : selectorType);
			}

			if (foundElements.isEmpty() && subStr.contains(":eq")) { 
				// TODO: check the if (eq) case
				// logs.debug(MessageFormat.format(LoggingMsg.IN_SELECTOR_TYPE,"xpath"));
				foundElements = htmlDoc.select(subStr);
				// nth-child() is the Selenium equivalent to JSoup eq()
				String subStrTemp = subStr;
				if (subStr.contains(":eq")) {
					int startIndex = subStr.indexOf("(") + 1;
					int endIndex = subStr.indexOf(")");
					String nthIndex = subStr.substring(startIndex, endIndex);
					// eq() is zero-base but nth-child() is one-base
					int nthIndexVal = Integer.parseInt(nthIndex) + 1;
					subStrTemp = subStr.replace(nthIndex, "" + nthIndexVal);
					subStrTemp = subStrTemp.replace(":eq", ":nth-child");
				}
				String selType = "css," + subStrTemp;
				selectorType = (!(foundElements.isEmpty()) ? selType : selectorType);
			}

			// The following if is used to get the parent element from which we do a
			// recursive call to get the child on which is our target
			if (webElementsInfo.get(subStr) != null
					&& (webElementsInfo.get(subStr).get("value")).toString().contains("child")
					&& !foundElements.isEmpty()) {
				String tempValue = (webElementsInfo.get(subStr).get("value")).toString();
				Element e = foundElements.first();
				String childSelStr = tempValue.split(",")[1];
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(childSelStr);
				valuesArr.add("");
				LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
				webElementInfo.put("value", "");
				webElementInfo.put("selector", "");
				webElementInfo.put("action", "");
				webElementInfo.put("SelType", "");

				webElementsInfo.remove(childSelStr);
				webElementsInfo.remove(subStr);
				webElementsInfo.put(childSelStr, webElementInfo);
				// Keeping this for Debugging purposes.
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
				// parentBy is essential for driver getElement function used in
				// doAppropriateAction() function to get the same element found by JSoup
				parentBy = getBySelectorForElements(foundElements, selectorType);
				// parent element found should be set to null as we don't have to do the if
				// following this
				foundElements = null;
				SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep, e);
				logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));

			}

			if (foundElements != null && !foundElements.isEmpty()) {
				Map<String, Object> webElementInfo = webElementsInfo.get(subStr);
				webElementInfo.put("selector", getStringSelectorForElements(foundElements, selectorType));
				webElementInfo.put("SelType", selectorType);
				webElementInfo.put("by", getBySelectorForElements(foundElements, selectorType));
				webElementInfo.put("action", getActiontype(foundElements));
				webElementInfo.put("parentBy", parentBy);
				parentBy = null;
				numberOfFoundElements.set(foundElements.size() + "");
				SelTestCase.logs.debug(MessageFormat.format(LoggingMsg.VALID_SEL_MSG, Arrays.asList(webElementInfo)));

			} else {
				logs.debug(LoggingMsg.NO_VALID_SEL_ERROR_MSG);
				throw new NoSuchElementException();
			}
		}
		getCurrentFunctionName(false);

	}

	private static String getActiontype(Elements foundElements) {
		String ActionType = "";
		// TODO fix multiple elements
		for (org.jsoup.nodes.Element e : foundElements) {
			 //logs.debug(e.toString());//Debugging purposes
			 //logs.debug("DEBUG POINT ------------->" + e.attr("type") );
			if (e.tagName().equals("input") && (e.attr("type").equals("number") || e.attr("type").equals("text")
					|| e.attr("type").equals("password") || e.attr("type").equals("") || e.attr("type").equals("tel")
					|| e.attr("type").equals("email")|| e.attr("type").equals("search"))) {
				return "type";
			} else if (e.tagName().equals("select")) {
				return "selectByText";
			} else if (e.tagName().equals("input") && e.attr("type").equals("checkbox")) {
				return "check";
			} else if (e.tagName().equals("button") || e.tagName().equals("img") || e.tagName().equals("a")
					|| e.tagName().equals("li") || e.tagName().equals("form") || e.tagName().equals("label")
					|| (e.tagName().equals("input") && (e.attr("type").equals("radio")))) {
				return "click";
			} else if (e.tagName().equals("input") && e.attr("type").equals("submit")) {
				return "click";
			} else if (e.tagName().equals("input") && e.attr("type").equals("radio")) {
				return "click";
			} else if (e.tagName().equals("p") || e.tagName().equals("tr") || e.tagName().equals("body")
					|| e.tagName().equals("td") || e.tagName().contains("h") || e.tagName().contains("ul")
					|| e.tagName().contains("dd")) {
				return "gettext";
			} else if (e.tagName().equals("div") || e.tagName().equals("span")) {
				return "click,gettext";
			} else {
				logs.debug(LoggingMsg.DEFAULT_ACTION_MSG);
				logs.debug("element type is: " + e.tagName());
				return "Validate";
			}
		}
		return ActionType;
	}

	public static By getBySelectorForElements(Elements foundElements, String selType) {
		By selector = null;
		for (org.jsoup.nodes.Element e : foundElements) {
			selector = null;
			if (selType.equals("id")) {
				selector = By.id(e.id());
			} else if (selType.equals("class")) {
				String[] tempClassArr = e.className().split(" ");
				StringBuilder className = new StringBuilder();
				for (String s : tempClassArr) {
					className.append(".");
					className.append(s);
				}
				selector = By.cssSelector(className.toString());
			} else if (selType.equals("name")) {
				selector = By.name(e.attr("name"));
			} else if (selType.equals("title")) {
				selector = By.xpath("//*[contains(@" + selType + ",'" + e.attr(selType) + "')]");
			} else {

				if (selType.contains("css")) {
					String selTemp = selType.split(",")[1];
					selector = By.cssSelector(selTemp);
				} else {
					selector = By.xpath("//*[contains(text(),'" + selType + "')]");
				}
			}

		}
		return selector;

	}

	public static String getStringSelectorForElements(Elements foundElements, String selType) {
		getCurrentFunctionName(true);

		logs.debug(MessageFormat.format(LoggingMsg.SEL_TYPE, selType));

		String selector = "";
		for (org.jsoup.nodes.Element e : foundElements) {
			selector = null;
			switch (selType) {
			case "id":
				selector = e.id();
				break;
			case "class":
				String[] tempClassArr = e.className().split(" ");
				StringBuilder className = new StringBuilder();
				for (String s : tempClassArr) {
					className.append(".");
					className.append(s);
				}
				selector = className.toString();
				break;
			case "name":
				selector = e.attr("name");
				break;
			case "title":
				selector = "//*[contains(@" + selType + ",'" + e.attr(selType) + "')]";
				break;

			default:
				if (selType.contains("css")) {
					String selTemp = selType.split(",")[1];
					selector = selTemp;
				} else {
					selector = "//*[contains(@text,'" + selType + "')]";
				}
				break;
			}
		}
		getCurrentFunctionName(false);
		return selector;
	}

	
	
	public static void doAppropriateAction(Map<String, Object> webElementInfo, boolean doAction) throws Exception {
		getCurrentFunctionName(true);
		textValue.set("");

		String browser = SelTestCase.getBrowserName();

		try {
			WebElement parent = null;
			WebElement field = null;
			WebElement field2 = null;
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver())
					.withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			
			String selector = (String) webElementInfo.get("selector");
			String action = (String) webElementInfo.get("action");
			String value = (String) webElementInfo.get("value");
			By byAction = (By) webElementInfo.get("by");
			By parentBy = (By) webElementInfo.get("parentBy");
			
			// get element using parent-child relationship OR DOM-element relationship
			if (byAction != null) {
				if (parentBy != null) {
					parent = getDriver().findElement(parentBy);
					field = parent.findElement(byAction);
				} else {
					// used to get the element at specific index when there are multiple elements of
					// the same selector
					if (value.contains("index")) {
						int elementIndex = Integer.parseInt(value.split("index,")[1].split(",")[0]);
						
						field = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElements(byAction).get(elementIndex);
							}
						});
						
					} else {
						field = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(byAction);
							}
						});
					}
				}
			}
			if (!doAction)
			{
				webElementInfo.put("elemnt" , field);
				return;
				
			}
			if (!selector.equals("")) {
				if (!SelectorUtil.isAnErrorSelector) {
					if (value.contains("ForceAction")) {
						action = value.split("ForceAction,")[1].split(",")[0];
					}

					if (action.equals("hover")) {

						logs.debug("Hovering: " + byAction.toString());

						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);
						Thread.sleep(200);
						field2 = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(byAction);
							}
						});

						Actions HoverAction = new Actions(getDriver());
						HoverAction.moveToElement(field2).click().build().perform();
					}

					if (action.equals("type")) {
						if (value.contains("getCurrentValue")) {
							logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt", byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							// I used the value attr instead of getText() as the input has the text as a
							// value
							textValue.set(field.getAttribute("value"));
							logs.debug("the text value is: " + SelectorUtil.textValue.get());
						} else {

							logs.debug(MessageFormat.format(LoggingMsg.WRITING_TO_SEL, "", value, byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							field.clear();
							String tempVal = value;
							if (value.contains("pressEnter")) {
								tempVal = value.split(",")[0];
							}
							if (browser.contains(GlobalVariables.browsers.iPhone))
							{
								int index = 0;
								for (index=0; index < tempVal.length(); index++) {
									String character = String.valueOf(tempVal.charAt(index));
									field.sendKeys(character);
								}
							}else {
								field.sendKeys(tempVal);
							}
							if (!tempVal.equals(value)) {
								field.sendKeys(Keys.ENTER);
								if (browser.contains("edge")) {
									field.sendKeys(Keys.ENTER);
								}
							}
						}
					} else if (value.contains("VisualTesting")) {

						logs.debug("Visual testing for: " + field.toString());
						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);

						Thread.sleep(500);

						field2 = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver driver) {
								return driver.findElement(byAction);
							}
						});
						
						if (browser.contains("mobile")) {
							screenShot.set(new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
									.shootingStrategy(ShootingStrategies.scaling((float) 3.0))
									.takeScreenshot(SelTestCase.getDriver(), field2));
						} else
							screenShot.set(new AShot().takeScreenshot(SelTestCase.getDriver(), field2));
						
					} else if (action.equals("click")) {
						if (!value.contains("noClick")) {

							logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
							JavascriptExecutor jse = (JavascriptExecutor) getDriver();
							jse.executeScript("arguments[0].scrollIntoView(false)", field);
							Thread.sleep(200);
							field2 = wait.until(new Function<WebDriver, WebElement>() {
								public WebElement apply(WebDriver driver) {
									return driver.findElement(byAction);
								}
							});
							logs.debug("browser..." + browser);
							if (browser.contains(GlobalVariables.browsers.firefox)) {
								logs.debug("clicking..." + SelTestCase.getBrowserName());
								field2.click();
							} else
								((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
										field2);

						} else
							logs.debug("NO clicking..." + SelTestCase.getBrowserName());
					} else if (action.equals("check")) {
						logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, byAction.toString(), value));
						if (value.contains("true")) {
							if (!field.isSelected()) {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", "not "));

								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse = (JavascriptExecutor) getDriver();
								jse.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
							} else {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							}
						} else {
							if (field.isSelected()) {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "un", ""));

								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse = (JavascriptExecutor) getDriver();
								jse.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
							} else {
								logs.debug(MessageFormat.format(LoggingMsg.CHECKING_UNCHECKING_MSG, "", ""));
							}
						}
					} else if (action.equals("gettext")) {
						logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt", byAction.toString()));
						textValue.set(field.getText());
						logs.debug("text is :" + textValue.get());
					} else if (action.equals("click,gettext")) {
						logs.debug(MessageFormat.format(LoggingMsg.GETTING_SEL, "txt, click", byAction.toString()));

						JavascriptExecutor jse = (JavascriptExecutor) getDriver();
						jse.executeScript("arguments[0].scrollIntoView(false)", field);

						String textVal = "";
						try {
							textVal = field.getText();
							textValue.set(textVal);
							logs.debug("text is :" + textValue.get());
						} catch (Exception e) {
							logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));
							throw new NoSuchElementException(
									MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "get text"));

						}
						try {
							if (value.isEmpty()) {

								logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, byAction.toString()));
								JavascriptExecutor jse1 = (JavascriptExecutor) getDriver();
								jse1.executeScript("arguments[0].scrollIntoView(false)", field);
								Thread.sleep(200);
								field2 = wait.until(new Function<WebDriver, WebElement>() {
									public WebElement apply(WebDriver driver) {
										return driver.findElement(byAction);
									}
								});
								logs.debug("browser..." + browser);
								if (browser.contains(GlobalVariables.browsers.firefox)) {
									logs.debug("clicking..." + browser);
									field2.click();
								} else
									((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()",
											field2);
								// field.click();
							}
						} catch (Exception e) {
							logs.debug(MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
							throw new NoSuchElementException(
									MessageFormat.format(LoggingMsg.FAILED_ACTION_MSG, "click"));
						}

					} else if (action.equals("selectByText")) {
						logs.debug(
								MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "value", byAction.toString()));
						Select select = new Select(field);

						// //Keep the block below for debugging purposes
						// List<WebElement> options = select.getOptions();
						// logs.debug(options.toString());
						// for(int i=1; i<options.size(); i++) {
						// logs.debug(options.get(i).getText());
						// }
						String textVal = "";
						try {
							if (!value.isEmpty() && !value.contains("FFF")) {
								List<WebElement> options = select.getOptions();
								for (int i = 0; i < options.size(); i++) {
									// logs.debug(options.get(i).getText().trim());
									if (options.get(i).getText().toLowerCase().trim().contains(value.toLowerCase())
											&& !value.equals("")) {
										logs.debug(MessageFormat.format(LoggingMsg.SELECTED_INDEX, i));
										select.selectByIndex(i);
										break;
									}
								}
							} else if (value.contains("FFF")) {
								String index = value.split("FFF")[1];
								logs.debug(
										"Direct selection " + MessageFormat.format(LoggingMsg.SELECTED_INDEX, index));
								select.selectByIndex(Integer.parseInt(index));
							} else {
								textVal = select.getFirstSelectedOption().getText();
							}
						} catch (Exception e) {
							logs.debug(LoggingMsg.TRY_ALT_WAY_MSG);
							if (!value.isEmpty()) {
								select.selectByVisibleText(value);
							} else {
								textVal = select.getFirstSelectedOption().getText();
							}
						}
						textValue.set(textVal);
					}
				} else if (action.equals("Validate") && SelectorUtil.isAnErrorSelector) {
					if (!value.isEmpty()) {
						Assert.assertEquals("The " + selector + " has incorrect error msg", field.getText(), value);
						logs.debug(MessageFormat.format(LoggingMsg.ERROR_VERIFICATION_SEL_MSG, selector));
					}
				}
				webElementInfo.put("elemnt" , field2);
			} else {
				throw new Exception(ExceptionMsg.noValidSelector);
			}
		} catch (Exception e) {
			throw e;
		}
		getCurrentFunctionName(false);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isDisplayed(String value) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(value);
		boolean isDisplayed = isDisplayed(subStrArr) ; 
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isDisplayed(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);

		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		if (items.size() == 0)
			return false;

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", items.get(0));
		boolean isDisplayed = true;
		if (!items.get(0).isDisplayed())
			isDisplayed = false;
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isDisplayed(List<String> subStrArr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		boolean isDisplayed = true;
		if (!items.get(index).isDisplayed())
			isDisplayed = false;
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotDisplayed(String value) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(value);
		boolean isNotDisplayed = isNotDisplayed(subStrArr) ; 
		getCurrentFunctionName(false);
		return isNotDisplayed;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotDisplayed(String value, String index) throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed = false;
		try {
			LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(value, index, false);
			return isNotDisplayed;

		} catch (NoSuchElementException e) {
			isNotDisplayed = true;
			return isNotDisplayed;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public static boolean isNotDisplayed(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		boolean isNotDisplayed = false;

		try {
			List<String> valuesArr = new ArrayList<String>();
			valuesArr.add("");
			LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
					new ArrayList<String>(subStrArr), valuesArr, false);
			return isNotDisplayed;

		} catch (NoSuchElementException e) {
			isNotDisplayed = true;
			return isNotDisplayed;
		}
	}

	@SuppressWarnings("rawtypes")
	public static String getAttr(List<String> subStrArr, String attr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		String attrValue = items.get(0).getAttribute(attr);
		getCurrentFunctionName(false);
		return attrValue;
	}

	@SuppressWarnings("rawtypes")
	public static String getAttrString (String Str, String attr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(Str);
		return getAttr(subStrArr,attr,index);
	}
	
	@SuppressWarnings("rawtypes")
	public static String getAttr(List<String> subStrArr, String attr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));
		String attrValue = items.get(index).getAttribute(attr);
		getCurrentFunctionName(false);
		return attrValue;
	}

	@SuppressWarnings("rawtypes")
	public static WebElement getNthElement(List<String> subStrArr, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		JavascriptExecutor jse1 = (JavascriptExecutor) getDriver();
		jse1.executeScript("arguments[0].scrollIntoView(false)", items.get(index));

		getCurrentFunctionName(false);
		return items.get(index);
	}

	@SuppressWarnings("rawtypes")
	public static WebElement getNthElement(String selector, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add(selector);
		getCurrentFunctionName(false);
		return SelectorUtil.getNthElement(valuesArr, index);
		
	}
	
	@SuppressWarnings("rawtypes")
	public static List<WebElement> getAllElements(String value) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(value);
		getCurrentFunctionName(false);
		return	getAllElements(subStrArr); 
	}
	
	@SuppressWarnings("rawtypes")
	public static List<WebElement> getAllElements(List<String> subStrArr) throws Exception {
		getCurrentFunctionName(true);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(
				new ArrayList<String>(subStrArr), valuesArr, false);
		List<WebElement> items = getDriver().findElements((By) webelementsInfo.get(subStrArr.get(0)).get("by"));

		getCurrentFunctionName(false);
		return items;
	}

	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr,
			List<String> valuesArr) throws Exception {
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}

	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr)
			throws Exception {
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}
	
	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(String selector)
			throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(selector);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}
	
	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(String selector, String value)
			throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(selector);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add(value);
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, true);
	}
	
	@SuppressWarnings("rawtypes")
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(String selector, String value,boolean doAction)
			throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(selector);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add(value);
		return initializeSelectorsAndDoActions(subStrArr, valuesArr, doAction);
	}
	
	public static WebElement getelement(String selector) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(selector);
		List<String> valuesArr = new ArrayList<String>();
		valuesArr.add("");
		return (WebElement) initializeSelectorsAndDoActions(subStrArr, valuesArr, false).get(selector).get("elemnt");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, LinkedHashMap> initializeSelectorsAndDoActions(List<String> subStrArr,
			List<String> valuesArr, boolean action) throws Exception {
		LinkedHashMap<String, LinkedHashMap> webElementsInfo = new LinkedHashMap<String, LinkedHashMap>();

		int index = 0;
		boolean isValidationStep = false;
		for (String key : subStrArr) {
			// logs.debug(key);
			LinkedHashMap<String, Object> webElementInfo = new LinkedHashMap<>();
			webElementInfo.put("value", valuesArr.get(index));
			webElementInfo.put("selector", "");
			webElementInfo.put("action", "");
			webElementInfo.put("SelType", "");
			webElementInfo.put("element", "");
			
			index++;

			webElementsInfo.remove(key);
			webElementsInfo.put(key, webElementInfo);
		}
		try {
			// Keeping this for Debugging purposes.
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));
			SelectorUtil.initializeElementsSelectorsMaps(webElementsInfo, isValidationStep);
			logs.debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, Arrays.asList(webElementsInfo)));

			for (String key : webElementsInfo.keySet()) {
				LinkedHashMap<String, Object> webElementInfo = webElementsInfo.get(key);
				SelectorUtil.doAppropriateAction(webElementInfo,action);
			}

			Thread.sleep(500);
		} catch (Exception e) {
			logs.debug(MessageFormat.format(LoggingMsg.FORMATTED_ERROR_MSG, e.getMessage()));
			throw new NoSuchElementException("No such element: " + Arrays.asList(webElementsInfo));

		} finally {
			valuesArr.clear();
			subStrArr.clear();
		}

		logs.debug(MessageFormat.format(LoggingMsg.FINISHED_ACTION_ON_ELEMENTS_MSG, Arrays.asList(webElementsInfo)));
		return webElementsInfo;

	}

	public static boolean isImgLoaded(String selector) {
		WebElement img = getDriver().findElement(By.cssSelector(selector));
		return ImageLoaded(img);
	}
	
	public static boolean isImgsLoaded(String selector, int NumberOfVerifiedImages) {
		List<WebElement> imgs = getDriver().findElements(By.cssSelector(selector));
		boolean loaded = false;

		for (int elementIndex = 0; elementIndex < imgs.size()
				&& elementIndex < NumberOfVerifiedImages; elementIndex++) {
			WebElement img = imgs.get(elementIndex);
			loaded = loaded && ImageLoaded(img);
		}

		return loaded;

	}

	public static boolean ImageLoaded(WebElement img) {
		Object result = (Boolean) ((JavascriptExecutor) getDriver()).executeScript(
				"return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && "
						+ "arguments[0].naturalWidth > 0",img);
		if (result instanceof Boolean) {
			return (Boolean) result;
		} else {
			return false;
		}
	}


	public static String getCurrentPageUrl() throws Exception {
		return SelTestCase.getDriver().getCurrentUrl();
	}

	public static void clickOnWebElement(WebElement element) throws Exception {
		((JavascriptExecutor) SelTestCase.getDriver()).executeScript("arguments[0].click()", element);
	}

	/**
	* Get the elements list by pass the selector as a string.
	*
	* @param selector.
	* @return List<WebElement>
	* @throws Exception
	*/
	@SuppressWarnings("rawtypes")
	public static List<WebElement> getElementsList(String selector) throws Exception {
		getCurrentFunctionName(true);

		LinkedHashMap<String, LinkedHashMap> webelementsInfo = SelectorUtil.initializeSelectorsAndDoActions(selector, "", false);			
		String Byselector = (By) webelementsInfo.get(selector).get("by")+"";
		logs.debug(Byselector);
		List<WebElement> listElements = getDriver().findElements((By) webelementsInfo.get(selector).get("by"));

		getCurrentFunctionName(false);
		return listElements;
	}

	/**
	* Check if element exist at document.
	*
	* @param Byselector.
	* @return boolean
	* @throws Exception
	*/
	public static boolean isElementExist(By Byselector) throws Exception {
		getCurrentFunctionName(true);
		boolean isElementExist = false;
		List<WebElement> listItems = getDriver().findElements(Byselector);

		if (listItems.size() != 0) {
			isElementExist = true;
		}
		getCurrentFunctionName(false);
		return isElementExist;
	}
	public static boolean isValidClickableItem(WebElement element) throws Exception {
		getCurrentFunctionName(true);
		 String elementHref = element.getAttribute("href");
		    //click on the element 
		     clickOnWebElement(element);
		     Thread.sleep(1000);
		    //check if the current page is the correct page
		    logs.debug("elementHref: "+elementHref);
		    getCurrentFunctionName(false);
		    if (elementHref == "")
		    {
		    	return false; 
		    }
		    else
		    {
		    	return true; 
		    }
	}
	public static WebElement getRandomWebElement(List<WebElement> items) throws Exception {
		logs.debug("WebElement List Size = " + items.size());
		Random random = new Random();
		int index = random.nextInt(items.size());
		WebElement element = items.get(index);
        return element;

	}

	/**
	* Check if GWT Loader.
	*
	* @return boolean
	* @throws Exception
	*/
	public static boolean CheckGWTLoadedEventPWA() throws Exception {
		getCurrentFunctionName(true);
		JavascriptExecutor JS = (JavascriptExecutor) getDriver();
		boolean gwtLoadedEventPWA = (boolean)JS.executeScript("return window.gwtLoadedEventPWA");
		getCurrentFunctionName(false);
		return gwtLoadedEventPWA;
	}

	/**
	* Check if GWT Loader.
	*
	* @return boolean
	* @throws Exception
	*/
	public static void waitGWTLoadedEventPWA() throws Exception {
		getCurrentFunctionName(true);
		boolean gwtLoadedEventPWA = CheckGWTLoadedEventPWA();
		int tries = 0;
		while (!gwtLoadedEventPWA) {
			Thread.sleep(500);
			gwtLoadedEventPWA = CheckGWTLoadedEventPWA();
			if(tries == 9) {
				throw new NoSuchElementException("Error in Loading GWT.");
			}
			tries ++;
		}
		getCurrentFunctionName(false);
	}

	/**
	* Wait the loading button.
	*
	* @return boolean
	* @throws Exception
	*/
	public static void waitingLoadingButton(String loadingButtonCssSelector) throws Exception {
		int loapingCount = 0;
		getCurrentFunctionName(true);
		boolean isIphone = SelTestCase.getBrowserName().contains(GlobalVariables.browsers.iPhone);
		while (isIphone && !isElementExist(By.cssSelector(loadingButtonCssSelector)) && loapingCount < 10) {
			loapingCount++;
			Thread.sleep(500);
		}
		getCurrentFunctionName(false);
	}
	
	/**
	* Open my account menu for mobile if it was not opened
	*
	* @throws Exception
	*/
	public static void openMobileAccountMenu() throws Exception {
		getCurrentFunctionName(true);
		boolean isPWAMobile = getBrowserName().contains(GlobalVariables.browsers.iPhone);
		if (isPWAMobile) {
			boolean isAccountMobileOpened = SelectorUtil.isElementExist(By.cssSelector(SignInSelectors.myAccountModal));
			if (!isAccountMobileOpened) {
				SelectorUtil.initializeSelectorsAndDoActions(SignInSelectors.accountMenuIcon.get());
			}
		}
		getCurrentFunctionName(false);
	}

	/**
	* Get the account item (Sign in/create account page or welcome message).
	*
	* @param WebElement
	* @throws Exception
	*/
	public static WebElement getMenuLinkMobilePWA(String linkUrl) throws Exception {
		getCurrentFunctionName(true);

		logs.debug("Open account menu for PWA mobile");

		// Open the account menu.
		openMobileAccountMenu();

		// Get an account items list.
		List <WebElement> menuItems = SelectorUtil.getElementsList(SignInSelectors.accountMenuList);
		WebElement linkElement = menuItems.get(0);
		int index = 0;
		// Get the Sign in/create account page or welcome message item.
		for (index=0; index < menuItems.size(); index++) {
			WebElement item = menuItems.get(index);
			String itemHref = item.getAttribute("href");
			// Check if the item is sign in/create account (By check create account page link) or welcome message.
			if (itemHref.contains(linkUrl)) {
				linkElement = item;
				break;
			}
		}
		logs.debug("The account item (Sign in/create account page or welcome message): " + linkElement);
		getCurrentFunctionName(false);
		return linkElement;
	}
	
	@SuppressWarnings("rawtypes")
	public static void selectActiveOption(String Str, String value) throws Exception {		
		LinkedHashMap<String, LinkedHashMap> webelementsInfo = initializeSelectorsAndDoActions(Str, value, false);
		WebElement items = getDriver().findElement((By) webelementsInfo.get(Str).get("by"));
		Select select = new Select(items);
			List<WebElement> options = select.getOptions();
			for (int i = 1; i < options.size(); i++) {
				// logs.debug(options.get(i).getText().trim());
				logs.debug("khara" + options.get(i).getText());
				if (!options.get(i).getText().toLowerCase().trim().contains("no longer")) {
					logs.debug(MessageFormat.format(LoggingMsg.SELECTED_INDEX, i));
					select.selectByIndex(i);
					break;
				}
				
			}
		}
}
