package automation_test.php_travel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ScreenCapture;

public class TestNGWebDriver {
    private static final Logger LOGGER = LogManager.getLogger(TestNGWebDriver.class);

    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://phptravels.com/demo/");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyHomePageTitle() {
        String expectedTitle = "Book Your Free Demo Now - Phptravels";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

    }

    @AfterMethod
    public void browserCleanUp(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                ScreenCapture.getScreenShot(driver);
            } catch (Exception e) {
                LOGGER.error("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }
}
