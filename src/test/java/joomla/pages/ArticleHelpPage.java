package joomla.pages;

import common.Log;
import constant.Constant;

import java.util.Set;

public class ArticleHelpPage {

    public boolean doesHelpWindowOpen() {
        String parentWindowHandle = Constant.DRIVER.getWindowHandle();
        Set<String> windowHandles = Constant.DRIVER.getWindowHandles();
        for (String handle: windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                Constant.DRIVER.switchTo().window(handle);
                if (Constant.DRIVER.getTitle().equals(Constant.HELP_TITLE)) {
                    Log.info(Constant.DRIVER.getTitle());
                    return true;
                }
            }
        }
        return false;
    }
}
