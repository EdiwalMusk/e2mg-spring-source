package com.e2mg;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest2
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String regex = "^([a-zA-Z_]\\w{0,127})$";
        Pattern pattern = Pattern.compile(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 129; i++) {
            stringBuilder.append("a");
        }
        System.out.println(pattern.matcher(stringBuilder).find());
    }
}
