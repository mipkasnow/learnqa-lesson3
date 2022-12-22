package tests.lesson4;

import com.codeborne.selenide.SelenideElement;
import helpers.Swiper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static helpers.Wrapper.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LessonFourTests extends BaseTest {

    private final Swiper swiper = new Swiper();

    private final SelenideElement searchField = elementByXpathTextContains("Search Wikipedia");
    private final SelenideElement searchInput = elementByIdWiki("search_src_text");
    private final SelenideElement skipButton = elementByXpathTextContains("SKIP");
    private final SelenideElement saveArticleButton = elementByAccessibilityId("Save");
    private final SelenideElement addToListButton = elementByXpathTextContains("ADD TO LIST");
    private final SelenideElement nameOfTheListInput = elementByIdWiki("text_input");
    private final SelenideElement okButton = elementByXpathTextContains("OK");
    private final SelenideElement viewListButton = elementByXpathTextContains("VIEW LIST");
    private final SelenideElement backButton = elementByAccessibilityId("Navigate up");

    @BeforeEach
    public void skipFirstPage() {
        skipButton.click();
    }

    @Test
    public void firstTest() {
        var searchTextOne = "Java";
        var titleOne = "Java (programming language)";
        var searchTextTwo = "Kotlin";
        var titleTwo = "Kotlin (programming language)";
        var folderName = "Learn programming";

        searchField.click();
        searchInput.sendKeys(searchTextOne);
        elementByXpathTextContains(titleOne).click();
        saveArticleButton.shouldHave(attribute("enabled", "true")).click();
        addToListButton.click();
        nameOfTheListInput.sendKeys(folderName);
        okButton.shouldHave(attribute("enabled", "true")).click();

        backButton.click();

        searchInput.sendKeys(searchTextTwo);
        elementByXpathTextContains(titleTwo).click();
        saveArticleButton.shouldHave(attribute("enabled", "true")).click();
        addToListButton.click();
        elementByXpathTextContains(folderName).click();
        viewListButton.click();

        elementByXpathTextContains("2 of 2 articles available offline").should(appear);

        var javaArticle = elementByXpathTextContains(titleOne);

        swiper.swipeElementFromCenterToLeft(javaArticle);
        elementByXpathTextContains("1 of 1 article available offline").should(appear);
        elementByXpathTextContains("UNDO").should(appear);

        elementByXpathTextContains(titleTwo).click();
        elementByXpath("//*[@resource-id='org.wikipedia:id/page_contents_container']//*[@text='" + titleTwo + "']")
                .should(appear);
    }

    @Test
    public void secondTest() {
        var searchTextOne = "Java";
        var titleOne = "Java (programming language)";
        var articleTitleLocator = elementByXpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View");

        searchField.click();
        searchInput.sendKeys(searchTextOne);
        elementByXpathTextContains(titleOne).click();

        assertElementPresent(articleTitleLocator);
    }

    public void assertElementPresent(SelenideElement element) {
        //sleep(4000); if we need to wait element
        boolean isVisible = element.isDisplayed();
        assertTrue(isVisible);
    }
}
