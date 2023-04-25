package com.ldx.encrypt.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldx.encrypt.annotations.Encrypt;
import com.ldx.encrypt.properties.EncryptProperties;


import com.ldx.encrypt.result.Result;
import com.ldx.encrypt.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Uaena
 * @date 2023/4/24 20:17
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<Result> {
    private ObjectMapper om = new ObjectMapper();
    @Autowired
    EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if (body instanceof Result) {

                if (body.getMsg() != null) {
                    body.setMsg(AESUtils.encrypt(body.getMsg().getBytes(), keyBytes));
                }
                if (body.getData() != null) {
                    body.setData(AESUtils.encrypt(om.writeValueAsBytes( body.getData()), keyBytes));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
