package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HotelDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstReserve = By.xpath("//button[contains(.,\"I'll reserve\") or contains(.,'Reserve')]");

    public HotelDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstReserve));
    }

    public ReservationPage clickFirstReserve() {
        wait.until(ExpectedConditions.elementToBeClickable(firstReserve)).click();
        return new ReservationPage(driver);
    }
}