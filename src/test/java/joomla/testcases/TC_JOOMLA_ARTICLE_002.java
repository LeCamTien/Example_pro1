package joomla.testcases;

import common.Log;
import common.Utilities;
import constant.Constant;
import joomla.pages.ArticlesPage;
import joomla.pages.EditArticlePage;
import joomla.pages.LoginPage;
import joomla.pages.NewArticlePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_JOOMLA_ARTICLE_002 extends TestHelper {
    LoginPage loginPage = new LoginPage();
    ArticlesPage articlesPage = new ArticlesPage();
    NewArticlePage newArticlePage = new NewArticlePage();
    EditArticlePage editArticlePage = new EditArticlePage();

    String title1 = Utilities.randomTitle();
    String content1 = Utilities.randomContent();
    String category1 = "Sample Data-Articles";

    String title2 = Utilities.randomTitle();
    String content2 = Utilities.randomContent();
    String category2 = "Blog";

    @BeforeMethod
    public void beforeMethod() {
        Log.info("------- Pre-conditions -------");
        Log.info("1.Login with valid account");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Log.info("2.Go to Articles page");
        articlesPage.goToArticlePage();

        Log.info("3.Click on New icon of the top right toolbar");
        articlesPage.clickButton("New");

        Log.info("4.Fill data into all fields");
        newArticlePage.fillData(title1, "", category1, content1);

        Log.info("5.Click on 'Save & Close' icon of the top right toolbar");
        newArticlePage.clickButton("Save & Close");

        Log.info("6.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title1), "Created article should display on the articles table");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "\"Article successfully saved\" message is displayed");
    }

    @Test(description = "TC_JOOMLA_ARTICLE_002 - Verify user can edit an article")
    public void f() {
        Log.info("7.Go to Articles page");
        articlesPage.goToArticlePage();

        Log.info("8.Check on the recently added article's checkbox");
        articlesPage.selectCheckbox(title1);

        Log.info("9.Click on 'Edit' icon of the top right toolbar");
        articlesPage.clickButton("Edit");

        Log.info("10.Fill data into all updated fields");
        Log.info(title2);
        editArticlePage.fillData(title2,"",category2,content2);

        Log.info("11.Validate new data in all updated fields");
        editArticlePage.validateData(title2,"",category2,content2);

        Log.info("12.Click on 'Save & Close' icon of the top right toolbar");
        editArticlePage.clickButton("Save & Close");

        Log.info("13.Verify the article is saved successfully");
        Assert.assertTrue(articlesPage.doesArticleDisplay(title2), "Created article should display on the articles table");
        Assert.assertTrue(articlesPage.doesMessageDisplay("Article saved"), "\"Article successfully saved\" message should display");
    }
}
