package pages;

import io.appium.java_client.MobileElement;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Data
public class OvenProductDescriptionPage extends BasePage {

    public OvenProductDescriptionPage(WebDriver driver) {
        super(driver);
    }

    private By offlineModeElementLocator = By.xpath("//android.widget.TextView[contains(@text,'Offline')]");

    private By remoteOnLocator = By.xpath("//android.widget.TextView[contains(@text,'Remote On')]");

    private By bakeCycleLocator = By.xpath("//android.widget.TextView[contains(@text,'Bake')]");

    private By stopOvenButtonLocator = By.xpath("//android.widget.Button[contains(@text,'STOP')]");

    private By stopOvenPopupLocator = By.xpath("//android.widget.Button[contains(@text,'STOP OVEN')]");

    private By stopOven = By.xpath("//android.widget.ImageButton[contains(@content-desc,'STOP')]");
    private By stopButton = By.id("stop_button");

    @FindBy(className = "android.widget.ImageButton")
    private List<WebElement> imageButtonList;

    @Override
    public BasePage click(By by, String clazzName){
        driver.findElement(by).click();
        switch (clazzName) {
            case "OvenProductDescriptionPage":
                return PageFactory.initElements(driver, OvenProductDescriptionPage.class);
            case "WallOvenSettingsPage":
                return PageFactory.initElements(driver, WallOvenSettingsPage.class);
            default:
                return this;
        }
    }
}
