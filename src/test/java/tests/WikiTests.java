package tests;

import helpers.AssertElement;
import helpers.Wrapper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;

public class WikiTests extends BaseTest {

    private final AssertElement assertElement = new AssertElement();

    @Test
    public void checkThatInputFieldContainsText() {
        assertElement.assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Search WikipediaRRRR");
    }

    @Test
    public void searchResults(){
        Wrapper.elementByXpathTextContains("Search Wikipedia").click();
        Wrapper.elementById("search_src_text").sendKeys("Warhammer");
        Wrapper.elementsById("page_list_item_title").shouldHave(sizeGreaterThan(1));
        Wrapper.elementByAccessibilityId("Clear query").click();
        Wrapper.elementById("page_list_item_title").shouldNotBe(visible);
    }
}
