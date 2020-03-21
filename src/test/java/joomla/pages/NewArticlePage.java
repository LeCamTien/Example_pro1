package joomla.pages;

import constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewArticlePage extends GeneralPage {
    private By txtTitle = By.id("jform_title");
    private By cbbStatus = By.xpath("//div[contains(@id,'jform_state')]//span");
    private By cbbCategory = By.xpath("//div[contains(@id,'jform_catid')]//span");
    private By iframe = By.xpath("//iframe[contains(@id,'jform_articletext')]");
    private By txtContent = By.xpath("//body[@id='tinymce']/p");

    String itemStatus = "//div[contains(@id,'jform_state')]//ul/li[text()='%s']";
    String itemCategory = "//div[contains(@id,'jform_catid')]//ul/li[text()='%s']";

    public WebElement getCbbStatus() {
        return Constant.DRIVER.findElement(cbbStatus);
    }

    public WebElement getCbbCategory() {
        return Constant.DRIVER.findElement(cbbCategory);
    }

    public WebElement getIframe() {
        return Constant.DRIVER.findElement(iframe);
    }

    public WebElement getTxtContent() {
        return Constant.DRIVER.findElement(txtContent);
    }

    public void clickButton(String nameButton) {
        String idButton = "toolbar-%s";
        switch (nameButton) {
            case "Save":
                nameButton = "Apply";
                break;
            case "Save & Close":
                nameButton = "Save";
                break;
            case "Save & New":
                nameButton = "Save-New";
                break;
        }
        Constant.DRIVER.findElement(By.id(String.format(idButton,nameButton.toLowerCase()))).click();
    }

    public void fillData(String title, String status, String category, String content) {
        Constant.DRIVER.findElement(txtTitle).sendKeys(title);
        if (!status.equals("")) {
            if (!this.getCbbStatus().getText().equals(status)) {
                this.getCbbStatus().click();
                Constant.DRIVER.findElement(By.xpath(String.format(itemStatus, status))).click();
            }
        }
        if (!category.equals("")) {
            if (!this.getCbbCategory().getText().equals(category)) {
                this.getCbbCategory().click();
                Constant.DRIVER.findElement(By.xpath(String.format(itemCategory, category))).click();
            }
        }
        Constant.DRIVER.switchTo().frame(this.getIframe());
        if (!this.getTxtContent().getText().equals("")) {
            this.getTxtContent().clear();
        }
        this.getTxtContent().sendKeys(content);
        Constant.DRIVER.switchTo().defaultContent();
    }
}
