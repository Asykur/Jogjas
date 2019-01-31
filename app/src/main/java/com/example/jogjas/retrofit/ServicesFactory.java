package com.example.jogjas.retrofit;

import com.example.jogjas.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yudha Pratama Putra on 15/08/18.
 */
public class ServicesFactory {
    private static boolean ENABLE_LOGGING = BuildConfig.DEBUG;

    private static String BASE_URL = "http://erporate.com/bootcamp/";


    public static Services getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(generateClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Services.class);

    }

    private static OkHttpClient generateClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        if (ENABLE_LOGGING) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        return clientBuilder.build();
    }

}