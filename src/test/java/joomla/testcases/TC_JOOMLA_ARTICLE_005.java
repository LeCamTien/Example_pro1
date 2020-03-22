package joomla.testcases;

import common.Log;
import common.Utilities;
import constant.Constant;
import joomla.pages.ArticlesPage;
import joomla.pages.HomePage;
import joomla.pages.LoginPage;
import joomla.pages.NewArticlePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_JOOMLA_ARTICLE_005 extends TestHelper {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();

    String title = Utilities.randomTitle();
    String category = "Sample Data-Articles";
    String content = Utilities.randomContent();

    @BeforeMethod
    public void beforeMethod() {
        Log.info("------- Pre-conditions -------");
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("2.Go to Article page");
        homePage.goToArticlePage();

        Log.info("3.Click on 'New' icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all fields");
        newArticlePage.fillData(title,"",category,content);

        Log.info("5.Click on 'Save & Close' icon of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"),"'Article saved' message should display");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title),"Created article is displayed on the articles table");
    }

    @Test(description = "TC_JOOMLA_ARTICLE_005 - Verify user can move an article to the archive")
    public void f() throws InterruptedException {
        Log.info("7.Check on the recently added article's checkbox");
        articlesPage.selectCheckbox(title);

        Log.info("8.Click on 'Archive' icon of the top right toolbar");
        articlesPage.clickButton("Archive");

        Log.info("9.Verify the confirm message is displayed");
        Assert.assertTrue(articlesPage.doesMessageDisplay("article archived"),"The '1 article archived' message is displayed");

        Log.info("10.Select 'Archived' item of 'Status' dropdown list");
        articlesPage.clickBtnSearchTools();
        articlesPage.selectStatusFilter("Archived");

        Log.info("11.Verify the archived article is displayed on the table grid");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title),"The archived article should display on the table grid");
    }

    @AfterMethod
    public void afterClass() {
        Log.info("------- Clean -------");
        articlesPage.clickBtnClear();
    }
}
