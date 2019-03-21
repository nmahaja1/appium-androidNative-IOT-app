package tests;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pages.KenmoreDashboardPage;
import pages.OvenModePage;
import pages.OvenProductDescriptionPage;
import pages.WallOvenSettingsPage;
import util.TestUtility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestUtility.class})
public class KenmoreOvenProductTest extends TestBase{
    KenmoreDashboardPage dashboardPage;
    OvenProductDescriptionPage ovenPDP;
    WallOvenSettingsPage ovenSettings;
    OvenModePage ovenModePage;


    @Test
    public void testOvenOfflineScenario() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        String mode = dashboardPage.getModeElement().getText();
        OvenProductDescriptionPage ovenProductDescriptionPage = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        if(mode.equals("Offline")) {
            assertTrue(ovenProductDescriptionPage.isElementPresent(ovenProductDescriptionPage.getOfflineModeElementLocator()));
            System.out.println("Device Offline");
        }

    }

    @Test
    public void testOvenOnlineScenario() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        OvenProductDescriptionPage ovenProductDescriptionPage = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenProductDescriptionPage.isElementPresent(ovenProductDescriptionPage.getRemoteOnLocator()), "Product is offline");
    }

    //@Test
    public void testOvenSettings() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getConvConvert()), "Conv convert is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO figure out the stop button
    }

    @Test
    public void testOvenModeListDisplayed() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");

        List<String> actualModeList = ovenModePage.getOvenModes();
        assertTrue(CollectionUtils.isNotEmpty(actualModeList));
        assertTrue(ovenModePage.getExpectedModeList().equals(actualModeList));
    }

    @Test
    public void testOvenBakeCycle() throws InterruptedException {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.BAKE.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.BAKE.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page

    }

//    @Test
//    public void testOvenBakeCycleStop() throws InterruptedException {
//        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
//        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
//        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
//        System.out.println("Looking for clickable elements");
////        MobileElement mobileElement = ((AndroidDriver<MobileElement>)driver).findElementByAndroidUIAutomator("new UiSelector().clickable(true).instance(0)");
////        if(mobileElement != null) {
////            System.out.println(mobileElement.getTagName());
////        }
//
//        List<MobileElement> mobileElementList = ((AndroidDriver<MobileElement>)driver).findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\")");
//        System.out.println("Clicking on 2nd clickable element");
//        mobileElementList.get(1).click();
//        //List<MobileElement> stopPopupClickableElementList = ((AndroidDriver<MobileElement>)driver).findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
//
//        List<MobileElement> stopPopUpButtonList = ((AndroidDriver<MobileElement>)driver).findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\")");
//        System.out.println("Clicking on 2nd button on the popup");
//        stopPopUpButtonList.get(1).click();
//
////        for (MobileElement stopElement : stopPopupClickableElementList) {
////            System.out.println(" Text " + stopElement.getText());
////            System.out.println(" Id " + stopElement.getId());
////            System.out.println(" Class " + stopElement.getClass());
////            System.out.println(" TagName " + stopElement.getTagName());
////        }
//
//    }

    @Test
    public void testOvenBakeCycleStopUsingLocator() throws InterruptedException {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.BAKE.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.BAKE.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        ovenSettings.getStart().click();
        Thread.sleep(5000);
        //ovenPDP.getImageButtonList().get(1).click();
        ovenPDP.click(ovenPDP.getStopButton(), "ovenPDP");
        Thread.sleep(5000);
//        ovenPDP.getButtonList().get(1).click();
        ovenPDP = (OvenProductDescriptionPage) ovenPDP.click(ovenPDP.getStopOvenPopupLocator(), "OvenProductDescriptionPage");
        Thread.sleep(5000);
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is offline");
    }

    @Test
    public void testOvenConventionBakeCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.CONVENTION_BAKE.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.CONVENTION_BAKE.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getConvConvert()), "Conv convert is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
//        ovenSettings.getStart().click();
    }

    @Test
    public void testOvenConventionRoastCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.CONVENTION_ROAST.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.CONVENTION_ROAST.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
//        ovenSettings.getStart().click();
    }

    @Test
    public void testOvenAccelaHeatCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.ACCELA_HEAT.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.ACCELA_HEAT.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenStart()), "Product is Ready");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenSettingsHeader()), "Header is not set correctly");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenMode()), "Mode is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getOvenCookTemperature()), "Cook Temperature is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
//        ovenSettings.getStart().click();
    }

    @Test
    public void testOvenWarmAndHoldCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.WARM_AND_HOLD.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.WARM_AND_HOLD.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
//        ovenSettings.getStart().click();
    }

    @Test
    public void testOvenSlowCookHiCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.SLOW_COOK_HI.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.SLOW_COOK_HI.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
//        ovenSettings.getStart().click();
    }

    @Test
    public void testOvenSlowCookLoCycle() {
        dashboardPage = PageFactory.initElements(driver, KenmoreDashboardPage.class);
        assertTrue(dashboardPage.isElementPresent(dashboardPage.getOvenProductLocator()));
        ovenPDP = (OvenProductDescriptionPage) dashboardPage.click(dashboardPage.getOvenProductLocator(), "OvenProductDescriptionPage");
        assertTrue(ovenPDP.isElementPresent(ovenPDP.getRemoteOnLocator()), "Product is online");
        ovenSettings = (WallOvenSettingsPage) ovenPDP.click(ovenPDP.getRemoteOnLocator(), "WallOvenSettingsPage");
        ovenModePage = (OvenModePage) ovenSettings.click(ovenSettings.getOvenMode(), "OvenModePage");
        ovenSettings = (WallOvenSettingsPage) ovenModePage.clickOvenMode(ovenModePage.getOvenModeListElements().get(OvenModePage.OVEN_MODES.SLOW_COOK_LO.ordinal()));
        assertEquals(OvenModePage.OVEN_MODES.SLOW_COOK_LO.displayName(), ovenSettings.getMode().getText());
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getCookTime()), "Cook Time is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getWarmAndHold()), "Warm and Hold is not visible");
        assertTrue(ovenSettings.isElementPresent(ovenSettings.getDelayStart()), "Delay Start is not visible");
        //TODO Add assertions for the new page
    }

}
