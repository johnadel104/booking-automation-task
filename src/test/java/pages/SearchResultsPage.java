package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cards           = By.cssSelector("div[data-testid='property-card']");
    private final By seeAvailability = By.cssSelector("a[data-testid='availability-cta']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cards));
    }

    public HotelDetailsPage openFirstTolip() {
        List<WebElement> all = driver.findElements(cards);
        for (WebElement c : all) {
            if (c.getText().toLowerCase().contains("tolip")) {
                WebElement cta = c.findElement(seeAvailability);
                wait.until(ExpectedConditions.elementToBeClickable(cta)).click();
                return new HotelDetailsPage(driver);
            }
        }
        WebElement firstCta = wait.until(ExpectedConditions.elementToBeClickable(seeAvailability));
        firstCta.click();
        return new HotelDetailsPage(driver);
    }
}