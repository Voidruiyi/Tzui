package com.ry.tzui.net.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ry.tzui.net.service.IServiceApi;
import com.ry.tzui.net.service.Url;
import com.ry.tzui.util.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceMoudle<T> {

    private static final String TAG = "okhttp";
    Retrofit retrofit;
    IServiceApi serviceApi;

    public Retrofit provideRetrofit() {
        synchronized (ServiceMoudle.class) {
            if (retrofit == null) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    public void log(String message) {
                        LogUtil.logI(TAG, message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();

                builder.connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .addInterceptor(loggingInterceptor);
                OkHttpClient okHttpClient = builder.build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(Url.SERVICE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
        }
        return retrofit;
    }

    public IServiceApi getServiceApi(Retrofit retrofit) {
        synchronized (ServiceMoudle.class) {
            if (serviceApi == null) {
                serviceApi = retrofit.create(IServiceApi.class);
            }
        }
        return serviceApi;
    }

    public static IServiceApi getApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                LogUtil.logI(TAG, message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.SERVICE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(IServiceApi.class);

    }
}
