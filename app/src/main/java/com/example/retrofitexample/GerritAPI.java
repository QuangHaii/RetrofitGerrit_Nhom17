package com.example.retrofitexample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {

    @GET("changes/")
    Call<ResponseBody> loadChanges(@Query("q") String status);
}