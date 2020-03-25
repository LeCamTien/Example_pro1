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

public class TC_JOOMLA_ARTICLE_007 extends TestHelper {
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

        Log.info("2.Go to Articles page");
        homePage.goToArticlePage();

        Log.info("3.Click on 'New' icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all fields");
        newArticlePage.fillData(title, "Published", category, content);

        Log.info("5.Click on 'Save & Close' icon of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify article is saved successfully");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "Created article should display on the article table");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "'Article saved' message should display");
    }

    @Test(description = "TC_JOOMLA_ARTICLE_007 - Verify user can move an article to trash section")
    public void f() throws InterruptedException {
        Log.info("7.Check on the recently added article's checkbox");
        articlesPage.selectCheckbox(title);

        Log.info("8.Click on 'Trash' icon of the top right toolbar");
        articlesPage.clickButton("Trash");

        Log.info("9.Verify the confirm message is displayed");
        Assert.assertTrue(articlesPage.doesMessageDisplay("article trashed"), "'1 article trashes' message should display");

        Log.info("10.Select 'Trashed' item of 'Status' dropdown list");
        articlesPage.clickBtnSearchTools();
        articlesPage.selectStatusFilter("Trashed");

        Log.info("11.Verify the deleted article is displayed on the table grid");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "The deleted article should display on the table grid");
    }
}
