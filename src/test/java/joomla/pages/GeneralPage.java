package joomla.pages;

import constant.Constant;
import org.openqa.selenium.By;

public class GeneralPage {
    String lnkMenuTab = "//ul[@id='menu']/li/a[normalize-space(text())='%s']";
    String lnkSubMenuTab = "//ul[@id='menu']/li/ul/li/a[normalize-space(text())='%s']";

    public void clickMenuTab(String nameMenuTab) {
        Constant.DRIVER.findElement(By.xpath(String.format(lnkMenuTab,nameMenuTab))).click();
    }

    public void clickSubMenuTab(String nameMenuTab, String nameSubMenuTab) {
        this.clickMenuTab(nameMenuTab);
        Constant.DRIVER.findElement(By.xpath(String.format(lnkSubMenuTab,nameSubMenuTab))).click();
    }
}
