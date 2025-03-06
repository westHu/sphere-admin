package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UploadTypeEnum {

    NOTIFICATION, ANNOUNCEMENT, AGENT, EXAMINE, OTHER;


    public static UploadTypeEnum codeOf(String text) {
        try {
            return valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }


}
