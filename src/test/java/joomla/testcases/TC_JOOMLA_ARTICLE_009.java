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

public class TC_JOOMLA_ARTICLE_009 extends TestHelper {
    public String title = Utilities.randomTitle();
    public String content = Utilities.randomContent();
    public String category = "Sample Data-Articles";

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();

    @BeforeMethod
    public void beforeMethod() {
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("2.Go to Articles page");
        homePage.goToArticlePage();

        Log.info("3.Click on New icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all field");
        newArticlePage.fillData(title, "Published", category, content);

        Log.info("5.Click on Save & Close of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify article saved successfully");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "'Article saved' message should display correctly");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "Created article should display on the articles table");
    }

    @Test(description = "TC_JOOMLA_ARTICLE_009 - Verify user can search for articles using the filter text field")
    public void f() {
        Log.info("7.Enter a title into Filter text field");
        articlesPage.fillSearchFilter(title);

        Log.info("8.Click on Search button");
        articlesPage.clickBtnSearchTools();

        Log.info("9.Verify the titles of displayed articles are partially matched with the entered keyword");
        Assert.assertTrue(articlesPage.doSearchResultsDisplay(title), "The titles of displayed articles should be partially matched with the entered keyword");
    }
}