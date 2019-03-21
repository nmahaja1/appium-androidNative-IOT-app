package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class OvenModePage extends BasePage {

    public enum OVEN_MODES {
        BAKE("Bake"),
        CONVENTION_BAKE("Convection Bake"),
        CONVENTION_ROAST("Convection Roast"),
        ACCELA_HEAT("Accela Heat"),
        WARM_AND_HOLD("Warm And Hold"),
        SLOW_COOK_HI("Slow Cook Hi"),
        SLOW_COOK_LO("Slow Cook Low");
        private String displayName;

        OVEN_MODES(String displayName) {
            this.displayName = displayName;
        }

        public String displayName()
        {
            return displayName;
        }

        @Override
        public String toString()
        {
            return displayName;
        }
    }

    public OvenModePage(WebDriver driver) {
        super(driver);
    }

    List<String> expectedModeList = Arrays.asList("Bake",
            "Convection Bake",
            "Convection Roast",
            "Accela Heat",
            "Warm And Hold",
            "Slow Cook Hi",
            "Slow Cook Lo");

    @Override
    public BasePage click(By by, String className) {
        return null;
    }

    public BasePage clickOvenMode(WebElement ovenMode) {
        ovenMode.click();
        return PageFactory.initElements(driver, WallOvenSettingsPage.class);
    }

    public List<String> getOvenModes() {
        return getOvenModeListElements().stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public List<WebElement> getOvenModeListElements() {
        return driver.findElements(By.id("name"));
    }
}
