package helpers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AssertElement {

    public void assertElementHasText(By by, String text) {
        var element = $(by);
        element.shouldHave(text(text));
    }
}
