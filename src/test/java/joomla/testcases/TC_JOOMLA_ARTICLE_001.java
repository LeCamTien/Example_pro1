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

public class TC_JOOMLA_ARTICLE_001 extends TestHelper {
    public String title = Utilities.randomTitle();
    public String category = "Sample Data-Articles";
    public String content = Utilities.randomContent();

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();

    @BeforeMethod
    public void beforeMethod() {
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
    }

    @Test(description = "TC_JOOMLA_ARTICLE_001 - Verify user can create a new article with valid information")
    public void f() {
        Log.info("2.Go to Articles page");
        homePage.goToArticlePage();

        Log.info("3.Click on New icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all fields");
        newArticlePage.fillData(title, "", category, content);

        Log.info("5.Click on 'Save & Close' icon of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "Created article should display on the articles table");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "\"Article successfully saved\" message should display");
    }
}