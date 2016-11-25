package com.lqzj.common.util;

import com.google.common.base.Strings;

import java.util.regex.Pattern;

public final class CharacterUtils {

    private static final String chineseRegex = "[\u4e00-\u9fa5]";
    private static final Pattern chinesePattern = Pattern.compile(chineseRegex);

    private static final String letterDigitRegex = "[a-zA-Z0-9]";
    private static final Pattern letterDigitPattern = Pattern.compile(letterDigitRegex);

    private CharacterUtils() {
    }

    public static boolean isContainsChinese(String str) {
        return !Strings.isNullOrEmpty(str) && chinesePattern.matcher(str).find();
    }

    public static boolean isContainsLetterDigit(String str) {
        return !Strings.isNullOrEmpty(str) && letterDigitPattern.matcher(str).find();
    }
}
