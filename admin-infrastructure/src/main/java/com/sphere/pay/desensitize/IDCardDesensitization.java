package com.sphere.pay.desensitize;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证脱敏器:
 */

public class IDCardDesensitization extends JsonSerializer<String> {

    /**
     * 15位身份证号正则
     */
    private static final Pattern PATTERN15 = Pattern.compile("^([1-9][0-9]{5}[0-9]{2}((0[1-9])|(10|11|12))(" +
            "([0-2][1-9])|10|20|30|31)[0-9]{3})$");

    /**
     * 18位身份证号正则
     */
    private static final Pattern PATTERN18 = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12)" +
            ")(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeObject(desensitize(value));
        } catch (Exception e) {
            gen.writeObject(value);
        }
    }

    /**
     * 身份证脱敏(18位和15位)
     * 先做18位的脱敏处理，如果满足则不会进入15位
     * 如果15位身份证不会匹配18位，则进入15位的处理
     */
    public String desensitize(String target) {
        target = desensitize(PATTERN18, target);
        return desensitize(PATTERN15, target);
    }

    /**
     * 前三位和年份后两位
     */
    private String desensitize(Pattern pattern, String target) {
        if (StrUtil.isNotBlank(target)) {
            Matcher matcher = pattern.matcher(target);
            if (matcher.find()) {
                int year = pattern.equals(PATTERN18) ? 8 : 6;
                String group = matcher.group();
                target = StrUtil.hide(group, 0, 2);
                target = StrUtil.hide(target, year, year + 2);
            }
        }
        return target;
    }
}
