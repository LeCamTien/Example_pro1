package joomla.testcases;

import common.Log;
import constant.Constant;
import joomla.pages.ArticleHelpPage;
import joomla.pages.ArticlesPage;
import joomla.pages.HomePage;
import joomla.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_JOOMLA_ARTICLE_008 extends TestHelper {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ArticlesPage articlesPage = new ArticlesPage();
    ArticleHelpPage articleHelpPage = new ArticleHelpPage();

    @Test(description = "TC_JOOMLA_ARTICLE_008 - Verify user can access article's help section")
    public void f() {
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("2.Go to Articles page");
        homePage.goToArticlePage();

        Log.info("3.Click on 'Help' icon of the top right toolbar");
        articlesPage.clickButton("Help");

        Log.info("4.Verify the article's help window is display");
        Assert.assertTrue(articleHelpPage.doesHelpWindowOpen(), "The article's help window is displayed");
    }
}
