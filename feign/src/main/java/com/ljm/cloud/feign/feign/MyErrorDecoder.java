package com.ljm.cloud.feign.feign;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @author lijunming
 * @date 2018/9/22 下午6:45
 */
public class MyErrorDecoder implements ErrorDecoder {
    final Decoder decoder;
    final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    MyErrorDecoder(Decoder decoder) {
        this.decoder = decoder;
    }


    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            return (Exception) decoder.decode(response, Exception.class);
        } catch (IOException fallbackToDefault) {
            return defaultDecoder.decode(methodKey, response);
        }

    }
}
