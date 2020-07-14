package com.ry.tzui.net.service;

import com.ry.tzui.bean.PublicResponseEntity;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface IServiceApi {

    @GET("")
    Observable<PublicResponseEntity<Object>> getMes(@Header("Authorization") String authorization);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String var1);
}
