package com.hope.blog.common.enums;


/**
 * Create by lijin on 2022/2/13 15:44
 */
public enum Website {

    COMMON(1, "常用网址"),
    STUDY(2, "学习"),
    RESOURCE(3, "资源"),
    VIDEO(4, "影视");

    private final Integer code;

    private final String name;

    private Website(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        Website[] values = Website.values();
        for (Website value : values) {
            if (code.equals(value.code)) {
                return value.name;
            }
        }
        return null;
    }
}
