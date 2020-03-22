package common;

import constant.Constant;

public class Utilities {

    public static String randomTitle() {
        return Constant.FAKER.lorem().sentence();
    }

    public static String randomContent() {
        return Constant.FAKER.lorem().paragraph();
    }
}