package joomla.pages;

import constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlesPage extends GeneralPage {
    String xpathArticle = "//table/tbody/tr//a[normalize-space(text())='%s']";
    String xpathMessage = "//div[@class='alert-message'][contains(text(),'%s')]";
    String xpathCheckbox = "//table/tbody/tr//a[normalize-space(text())='%s']//ancestor::tr//input[@type='checkbox']";
    String xpathStatusIcon = "//table/tbody/tr//a[normalize-space(text())='%s']//ancestor::tr//span[@class='icon-%s']";
    String xpathStatusItem = "//div[contains(@id,'filter_published')]//li[text()='%s']";
    String xpathCheckoutIcon = "//table/tbody/tr//a[normalize-space(text())='%s']//ancestor::tr//span[@class='icon-checkedout']";

    private By btnSearchTools = By.cssSelector("button[class$='js-stools-btn-filter']");
    private By divStatusFilter = By.xpath("//div[contains(@id,'filter_published')]");
    private By btnClear = By.cssSelector("button[class$='js-stools-btn-clear']");
    private By txtSearch = By.id("filter_search");

    public void clickButton(String nameButton) {
        String idButton = "toolbar-%s";
        if (nameButton.equals("Feature")) {
            nameButton = "Featured";
        } else if (nameButton.equals("Unfeature")) {
            nameButton = "Unfeatured";
        }
        Constant.DRIVER.findElement(By.id(String.format(idButton, nameButton.toLowerCase()))).click();
    }

    public void clickBtnSearchTools() {
        Constant.DRIVER.findElement(btnSearchTools).click();
    }

    public void clickBtnClear() {
        Constant.DRIVER.findElement(btnClear).click();
    }

    public void selectCheckbox(String article) {
        WebElement checkbox = Constant.DRIVER.findElement(By.xpath(String.format(xpathCheckbox, article)));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectStatusFilter(String status) throws InterruptedException {
        Thread.sleep(1000);
        Constant.DRIVER.findElement(divStatusFilter).click();
        Thread.sleep(1000);
        Constant.DRIVER.findElement(By.xpath(String.format(xpathStatusItem,status))).click();
    }

    public boolean doesArticleDisplay(String article) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathArticle, article))).size() == 1;
    }

    public boolean doesMessageDisplay(String message) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathMessage, message))).size() == 1;
    }

    public boolean doesStatusIconDisplay(String article, String status) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathStatusIcon, article, status.toLowerCase()))).size() == 1;
    }

    public boolean doesArticleCheckout(String title) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathCheckoutIcon,title))).size() == 1;
    }

    public void fillSearchFilter(String title) {
        Constant.DRIVER.findElement(txtSearch).sendKeys(title);
    }

    public boolean doSearchResultsDisplay(String title) {
        return Constant.DRIVER.findElements(By.xpath(String.format(xpathArticle, title))).size() >= 1;
    }
}