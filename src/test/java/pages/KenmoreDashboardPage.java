package pages;

import io.appium.java_client.AppiumDriver;
import lombok.Data;
import lombok.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class KenmoreDashboardPage extends BasePage{

    public KenmoreDashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "db_l2")
    private WebElement modeElement;

    private By ovenProductLocator =  By.xpath("//android.widget.TextView[contains(@text,'Oven')]");

    @Override
    public BasePage click(By by, String clazzName){
        driver.findElement(by).click();
        switch (clazzName) {
            case "OvenProductDescriptionPage":
                return PageFactory.initElements(driver, OvenProductDescriptionPage.class);
            default:
                return this;
        }
    }

}
