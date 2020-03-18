package constant;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

public class Constant {
    public static WebDriver DRIVER;
    public static final String URL = "http://localhost:1234/joomla30/administrator/";
    public static final String USERNAME = "satt";
    public static final String PASSWORD = "123456";
    public static final Faker FAKER = new Faker();
}
