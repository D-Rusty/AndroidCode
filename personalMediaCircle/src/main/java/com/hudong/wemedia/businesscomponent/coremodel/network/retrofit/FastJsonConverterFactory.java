package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 作者: 方天文
 * 日期: 2017/5/11:下午5:02
 * 概况:
 */

public class FastJsonConverterFactory extends Converter.Factory {
    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FatJsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FatJsonRequestBodyConverter<>();
    }

    class FatJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private Type type;

        FatJsonResponseBodyConverter(Type type) {
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            BufferedSource bufferedSource = Okio.buffer(value.source());
            String tempStr = bufferedSource.readUtf8();
            bufferedSource.close();
            Log.i("test", "FatJsonResponseBodyConverter==" + tempStr);
            return JSON.parseObject(tempStr, type);
        }
    }


    class FatJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        @Override
        public RequestBody convert(T value) throws IOException {
            Log.i("test", "FatJsonResponseBodyConverter==RequestBody" );
            return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
        }
    }


}
