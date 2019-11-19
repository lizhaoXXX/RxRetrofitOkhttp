package com.azhao.rxretrofit.network.rx.converterfactory;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author  azhao
 * $date    2018/3/22
 * $desc
 */
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(@NonNull ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}