package com.ldx.encrypt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Uaena
 * @date 2023/4/24 20:14
 */
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    private final static String DEFAULT_KEY = "chat_zijizhang_c";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
