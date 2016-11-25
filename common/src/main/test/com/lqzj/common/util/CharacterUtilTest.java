package com.lqzj.common.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharacterUtilTest {

    @Test
    public void should_return_false_when_given_null_or_empty_string(){
        assertFalse(CharacterUtils.isContainsChinese(null));
        assertFalse(CharacterUtils.isContainsChinese(""));
        assertFalse(CharacterUtils.isContainsLetterDigit(null));
        assertFalse(CharacterUtils.isContainsLetterDigit(""));
    }

    @Test
    public void should_return_false_when_given_en_or_number_string() {
        assertFalse(CharacterUtils.isContainsChinese("test string"));
        assertFalse(CharacterUtils.isContainsChinese("123 321"));
        assertFalse(CharacterUtils.isContainsChinese("test 123 string"));
    }

    @Test
    public void should_return_true_when_given_en_or_number_string() {
        assertTrue(CharacterUtils.isContainsLetterDigit("test string"));
        assertTrue(CharacterUtils.isContainsLetterDigit("123 321"));
        assertTrue(CharacterUtils.isContainsLetterDigit("test 123 string"));
        assertTrue(CharacterUtils.isContainsLetterDigit("test 中文 string"));
    }

    @Test
    public void should_return_true_when_given_contain_any_chinese_string() throws Exception {
        assertTrue(CharacterUtils.isContainsChinese("test 中文 string"));
        assertTrue(CharacterUtils.isContainsChinese("123 中文 dd"));
        assertTrue(CharacterUtils.isContainsChinese("123 中文 444"));
    }
}
