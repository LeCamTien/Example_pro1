package common;

import constant.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHelper {
    public static void openBrowser(String browser, String url) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            Constant.DRIVER = new ChromeDriver();
        }
        else if (browser.equals("ie")) {
            WebDriverManager.iedriver().setup();
            Constant.DRIVER = new InternetExplorerDriver();
        }
        else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            Constant.DRIVER = new FirefoxDriver();
        }
        Constant.DRIVER.get(url);
        Constant.DRIVER.manage().window().maximize();
    }
}
