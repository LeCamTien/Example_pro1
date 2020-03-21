package joomla.pages;

import constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlesPage extends GeneralPage {
    String xpathArticle = "//table/tbody/tr//a[normalize-space(text())='%s']";
    String xpathMessage = "//div[@class='alert-message'][contains(text(),'%s')]";
    String xpathCheckbox = "//table/tbody/tr//a[normalize-space(text())='%s']//ancestor::tr//input[@type='checkbox']";

    public void goToArticlePage() {
        this.clickSubMenuTab("Content", "Articles");
    }

    public void clickButton(String nameButton) {
        String xpathButton = "button[class*='button-%s']";
        if (nameButton.equals("Feature")) {
            nameButton = "Featured";
        } else if (nameButton.equals("Unfeature")) {
            nameButton = "Unfeatured";
        }
        Constant.DRIVER.findElement(By.cssSelector(String.format(xpathButton, nameButton.toLowerCase()))).click();
    }

    public boolean doesArticleDisplay(String article) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathArticle,article))).size() == 1;
    }

    public boolean doesMessageDisplay(String message) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathMessage,message))).size() == 1;
    }

    public void selectCheckbox(String article) {
        WebElement checkbox = Constant.DRIVER.findElement(By.xpath(String.format(xpathCheckbox,article)));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
}
