package joomla.pages;

public class HomePage extends GeneralPage {

    public void goToArticlePage() {
        this.clickSubMenuTab("Content", "Articles");
    }
}