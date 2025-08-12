package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.YearMonth;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait  = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // عناصر الصفحة
    private final By destinationInput   = By.id("ss");
    private final By suggestions        = By.cssSelector("div[data-testid='autocomplete-results'] li");
    private final By dateFieldStartBtn  = By.cssSelector("button[data-testid='date-display-field-start']");
    private final By searchBtn          = By.cssSelector("button[type='submit']");
    private final By monthHeader        = By.cssSelector("div[data-testid='calendar-month']:first-of-type h3");
    private final By nextMonthBtn       = By.cssSelector("button[aria-label*='Next month']");

    private By dayCell(int y, int m, int d) {
        String mm = (m < 10 ? "0"+m : ""+m);
        String dd = (d < 10 ? "0"+d : ""+d);
        return By.cssSelector("td[data-date='"+y+"-"+mm+"-"+dd+"'] span");
    }

    public HomePage open() {
        driver.get("https://www.booking.com/?lang=en-us");
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationInput));
        return this;
    }


    public HomePage setQueryAlexTolip() {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(destinationInput));
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);
        input.sendKeys("Alexandria Tolip");

        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestions)).click();
        return this;
    }


    public HomePage pickDates() {
        wait.until(ExpectedConditions.elementToBeClickable(dateFieldStartBtn)).click();

        YearMonth target = YearMonth.of(2025, 10);
        int guard = 0;
        while (!isTargetMonthVisible(target)) {
            wait.until(ExpectedConditions.elementToBeClickable(nextMonthBtn)).click();
            guard++;
            if (guard > 24) throw new RuntimeException("Month not found: " + target);
        }

        wait.until(ExpectedConditions.elementToBeClickable(dayCell(2025, 10, 10))).click();
        wait.until(ExpectedConditions.elementToBeClickable(dayCell(2025, 10, 14))).click();
        return this;
    }

    private boolean isTargetMonthVisible(YearMonth target) {
        try {
            String header = wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeader)).getText().trim();
            String[] parts = header.split("\\s+");
            if (parts.length < 2) return false;
            String monthName = parts[0];
            int y = Integer.parseInt(parts[1]);
            int m = monthNameToNumber(monthName);
            return (y == target.getYear() && m == target.getMonthValue());
        } catch (Exception e) {
            return false;
        }
    }

    private int monthNameToNumber(String name) {
        switch (name.toLowerCase()) {
            case "january": return 1;
            case "february": return 2;
            case "march": return 3;
            case "april": return 4;
            case "may": return 5;
            case "june": return 6;
            case "july": return 7;
            case "august": return 8;
            case "september": return 9;
            case "october": return 10;
            case "november": return 11;
            case "december": return 12;
            default: return -1;
        }
    }

    public SearchResultsPage submitSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return new SearchResultsPage(driver);
    }
}