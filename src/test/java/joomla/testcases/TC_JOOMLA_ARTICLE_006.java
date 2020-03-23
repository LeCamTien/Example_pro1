package joomla.testcases;

import common.BrowserHelper;
import common.Log;
import common.Utilities;
import constant.Constant;
import joomla.pages.ArticlesPage;
import joomla.pages.HomePage;
import joomla.pages.LoginPage;
import joomla.pages.NewArticlePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_JOOMLA_ARTICLE_006 extends TestHelper {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();

    String title = Utilities.randomTitle();
    String category = "Sample Data-Articles";
    String content = Utilities.randomContent();

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

        Log.info("5.Click on 'Save' icon of the top right toolbar");
        newArticlePage.clickButton("Save");

        Log.info("6.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "'Article saved' message should display");

        Log.info("7.Close the browser window");
        Constant.DRIVER.close();
    }

    @Parameters("browser")
    @Test(description = "TC_JOOMLA_ARTICLE_006 - Verify user can check in an article")
    public void f(String browser) {
        Log.info("8.Navigate to Joomla");
        BrowserHelper.openBrowser(browser, Constant.URL);

        Log.info("9.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("10.Go to Articles page");
        homePage.goToArticlePage();

        Log.info("11.Verify article is saved successfully");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title), "Created article should display on the articles table");

        Log.info("12.Verify article is checking out");
        Assert.assertTrue(articlesPage.doesArticleCheckout(title), "Article should display as checking out");

        Log.info("13.Check on the recently added article's checkbox");
        articlesPage.selectCheckbox(title);

        Log.info("14.Click on 'Check in' icon of the top right toolbar");
        articlesPage.clickButton("Checkin");

        Log.info("15.Verify the article is checked in successfully");
        Assert.assertTrue(articlesPage.doesMessageDisplay("article checked in"), "The '1 article successfully checked in' message should display");
        Assert.assertFalse(articlesPage.doesArticleCheckout(title), "The lock icon next to the article should be removed");
    }
}
