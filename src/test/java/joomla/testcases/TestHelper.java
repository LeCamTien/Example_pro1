package joomla.testcases;

import common.BrowserHelper;
import common.Log;
import constant.Constant;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestHelper {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser) {
        Log.info("---------- Start test ----------");
        Log.info("0.Navigate to Joomla");
        BrowserHelper.openBrowser(browser, Constant.URL);
    }

    @AfterClass
    public void afterClass() {
        Log.info("---------- End test ----------");
//        Constant.DRIVER.quit();
    }
}
