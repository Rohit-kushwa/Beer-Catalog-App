package com.example.simplebeercatalogapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/v2/beers?size=" +100+ "&response_type=json")
    Call<List<PostPoJo>>  getPosts();
}
