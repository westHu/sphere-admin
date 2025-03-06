package com.sphere.pay.desensitize;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号脱敏器
 */
public class PhoneDesensitization extends JsonSerializer<String> {

    /**
     * 手机号正则
     */
    private static final Pattern DEFAULT_PATTERN = Pattern.compile("(13[0-9]|14[579]|15[0-3," +
            "5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}");


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeObject(desensitize(value));
        } catch (Exception e) {
            gen.writeObject(value);
        }
    }

    /**
     * 手机号脱敏 只保留前3位和后4位
     */
    private String desensitize(String target) {
        Matcher matcher = DEFAULT_PATTERN.matcher(target);
        if (matcher.find()) {
            String group = matcher.group();
            target = StrUtil.hide(group, 3, 7);
        }
        return target;
    }
}
