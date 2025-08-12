package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReservationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By hotelName = By.cssSelector("[data-testid='hotel-name'], h1");

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(hotelName),
                ExpectedConditions.urlContains("book")
        ));
    }

    public String getHotelName() {
        try { return driver.findElement(hotelName).getText(); }
        catch (Exception e) { return ""; }
    }
}