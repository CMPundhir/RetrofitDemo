package com.cmpundhir.cm.retrofitdemo.api;

import com.cmpundhir.cm.retrofitdemo.model.GithubUserPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiInterface {
    @GET("users")
    Call<List<GithubUserPojo>> getUSersList();
}
