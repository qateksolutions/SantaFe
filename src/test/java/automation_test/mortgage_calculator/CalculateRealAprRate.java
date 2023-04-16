package automation_test.mortgage_calculator;

import org.testng.annotations.Test;
import page_objects.NavigationBar;

public class CalculateRealAprRate extends BaseClass {
    @Test
    public void calculateRealApr() {
        new NavigationBar(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice("200000")
                .typeDownPayment("15000")
                .selectDownPaymentInDollar()
                .typeInterestRate("3")
                .clickOnCalculateButton()
                .validateRealAprRate("3.130%");
    }

}
