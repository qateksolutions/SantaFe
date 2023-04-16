package testNG_Maven;

import data.DataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestNgMavenExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExampleTest.class);

    // This is the data provider method
    @Test(dataProvider = "MultipleColumnValues", dataProviderClass = DataStore.class)
    public void run(String name, String state, int zipCode) {
        LOGGER.info("Name is: " + name);
        LOGGER.info("State is: " + state);
        LOGGER.info("Zip code is: " + zipCode);
    }
}
