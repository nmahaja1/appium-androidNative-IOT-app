package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static AppiumDriver driver = null;

    @BeforeMethod
    public void initializeSuite() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        /* These are the capabilities we must provide to run our test on TestObject */
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("deviceName", "TestDevice");
        capabilities.setCapability("app", "/Users/nayanankmahajan/Documents/test_appium/KenmoreSmart.apk");
        capabilities.setCapability("noReset", "true");

        /* Check your Basic Setup page to find the URL that corresponds to your device */
        URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    //Test cleanup
    public void TeardownTest()
    {
        driver.quit();
    }

    protected boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
}
