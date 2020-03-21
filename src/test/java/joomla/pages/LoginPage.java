package joomla.pages;

import constant.Constant;
import org.openqa.selenium.By;

public class LoginPage {
    private By txtUsername = By.id("mod-login-username");
    private By txtPassword = By.id("mod-login-password");
    private By btnLogin = By.cssSelector("button[class$='login-button']");

    public void login(String username, String password) {
        Constant.DRIVER.findElement(txtUsername).sendKeys(username);
        Constant.DRIVER.findElement(txtPassword).sendKeys(password);
        Constant.DRIVER.findElement(btnLogin).click();
    }
}
