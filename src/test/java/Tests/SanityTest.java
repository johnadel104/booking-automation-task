package Tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SanityTest extends BaseTest {

    @Test
    public void book_Tolip_in_Alexandria_withFixedDates() {
        ReservationPage res = new HomePage(driver)
                .open()
                .setQueryAlexTolip()
                .pickDates()
                .submitSearch()
                .openFirstTolip()
                .clickFirstReserve();

        Assert.assertFalse(res.getHotelName().isBlank(), "Hotel name is empty on reservation page");
    }
}