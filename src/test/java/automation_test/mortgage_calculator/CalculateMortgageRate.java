package automation_test.mortgage_calculator;

import org.testng.annotations.Test;
import page_objects.NavigationBar;
import utilities.DateUtils;

public class CalculateMortgageRate extends BaseClass {
    @Test
    public void calculateMonthlyPayment() {
        String[] date = DateUtils.returnNextMonth();

        new NavigationBar(driver)
                .navigateToHome()
                .typeHomePrice("300000")
                .typeDownPayment("60000")
                .clickDownPaymentInDollar()
                .typeLoanAmount("240000")
                .typeInterestRate("3")
                .typeLoanTermYears("30")
                .selectMonth(date[0])
                .typeYear(date[1])
                .typePropertyTax("5000")
                .typePMI("0.5")
                .typeHomeOwnerInsurance("1000")
                .typeMonthlyHOA("100")
                .selectLoanType("FHA")
                .selectBuyOrRefinance("Buy")
                .clickOnCalculateButton()
                .validateTotalMonthlyPayment("1,611.85");

    }
}
