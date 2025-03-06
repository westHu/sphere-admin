package com.sphere.pay.desensitize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 密码脱敏器:
 */

public class PasswordDesensitization extends JsonSerializer<String> {


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeObject("********");
        } catch (Exception e) {
            gen.writeObject(value);
        }
    }
}
