package pages;


import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class WallOvenSettingsPage extends BasePage {

    public WallOvenSettingsPage(WebDriver driver) {
        super(driver);
    }

    private By ovenStart = By.xpath("//android.widget.Button[contains(@text,'START')]");
    private By ovenSet = By.xpath("//android.widget.Button[contains(@text,'SET')]");
    private By ovenMode = By.xpath("//android.widget.TextView[contains(@text,'Mode')]");
    private By ovenSettingsHeader = By.xpath("//android.widget.TextView[contains(@text,'Cook Settings')]");
    private By ovenCookTemperature = By.xpath("//android.widget.TextView[contains(@text,'Cook Temperature')]");
    private By convConvert = By.xpath("//android.widget.TextView[contains(@text,'Conv Convert')]");
    private By cookTime = By.xpath("//android.widget.TextView[contains(@text,'Cook Time')]");
    private By warmAndHold = By.xpath("//android.widget.TextView[contains(@text,'Warm and Hold')]");
    private By delayStart = By.xpath("//android.widget.TextView[contains(@text,'Delay Start')]");
    private By temperatureText = By.id("temp_text");

    @FindBy(id = "mode")
    public WebElement mode;


    @FindBy(id = "start")
    public WebElement start;

    @Override
    public BasePage click(By by, String clazzName){
        driver.findElement(by).click();
        switch (clazzName) {
            case "OvenProductDescriptionPage":
                return PageFactory.initElements(driver, OvenProductDescriptionPage.class);
            case "OvenModePage":
                return PageFactory.initElements(driver, OvenModePage.class);
            default:
                return this;
        }
    }

}






