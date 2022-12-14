package helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Wrapper {

    public static SelenideElement elementById(String id) {
        return $(By.id("org.wikipedia:id/" + id));
    }

    public static SelenideElement elementByXpathTextContains(String text) {
        return $x("//*[contains(@text,'" + text + "')]");
    }

    public static SelenideElement elementByXpath(String xpath) {
        return $x(xpath);
    }


}
