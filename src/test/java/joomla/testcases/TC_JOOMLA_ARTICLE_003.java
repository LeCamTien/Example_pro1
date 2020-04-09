package joomla.testcases;

import common.Log;
import common.Utilities;
import constant.Constant;
import joomla.pages.ArticlesPage;
import joomla.pages.HomePage;
import joomla.pages.LoginPage;
import joomla.pages.NewArticlePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_JOOMLA_ARTICLE_003 extends TestHelper {
    public String title = Utilities.randomTitle();
    public String category = "Sample Data-Articles";
    public String content = Utilities.randomContent();

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();

    @BeforeMethod
    public void beforeMethod() {
        Log.info("------- Pre-conditions -------");
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("2.Go to Article page");
        homePage.goToArticlePage();

        Log.info("3.Click on New icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all fields");
        newArticlePage.fillData(title, "Unpublished", category, content);

        Log.info("5.Click on 'Save & Close' icon of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "'Article saved' message should display");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "Created article should display on the articles table");
    }

    @Test(description = "TC_JOOMLA_ARTICLE_003 - Verify that user can publish an unpublish article")
    public void f() {
        Log.info("7.Check on the recently added article's checkbox");
        articlesPage.selectCheckbox(title);

        Log.info("8.Click on 'Publish' icon of the top right toolbar");
        articlesPage.clickButton("Publish");

        Log.info("9.Verify the article is published successfully");
        Assert.assertTrue(articlesPage.doesStatusIconDisplay(title, "Publish"), "The icon of the selected item should be showed as 'Publish'");
        Assert.assertTrue(articlesPage.doesMessageDisplay("article published"), "The '1 article published' message should display");
    }
}