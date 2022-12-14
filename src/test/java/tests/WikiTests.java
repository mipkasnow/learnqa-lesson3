package tests;

import helpers.AssertElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class WikiTests extends BaseTest {

    private final AssertElement assertElement = new AssertElement();

    @Test
    public void checkThatInputFieldContainsText() {
        assertElement.assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Search Wikipedia");
    }
}
