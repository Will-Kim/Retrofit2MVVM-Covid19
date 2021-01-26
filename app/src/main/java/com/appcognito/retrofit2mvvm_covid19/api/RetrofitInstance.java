package com.appcognito.retrofit2mvvm_covid19.api;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://openapi.data.go.kr/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                            new Persister(new AnnotationStrategy() // important part!
                            )))
                    .build();
        }
        return retrofit;
    }
}
