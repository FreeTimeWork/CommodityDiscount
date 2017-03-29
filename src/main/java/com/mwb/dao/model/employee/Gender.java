package com.mwb.dao.model.employee;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public enum Gender {

    M("M", "Male"), F("F", "Female");

    private static final Map<String, Gender> code2Gender;

    private String code;
    private String description;

    static {
        code2Gender = new HashMap<String, Gender>();

        for (Gender gender : Gender.values()) {
            code2Gender.put(gender.getCode(), gender);
        }
    }

    public static Gender fromCode(String code) {
        return code2Gender.get(code);
    }

    Gender(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
