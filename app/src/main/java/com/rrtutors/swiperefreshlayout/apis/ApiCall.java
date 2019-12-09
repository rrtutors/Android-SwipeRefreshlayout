package com.rrtutors.swiperefreshlayout.apis;


import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCall {
    public static final String BASE_IRL="https://newsapi.org/";
    public static final String SUB_IRL="v2/sources?apiKey=API_KEY";

    @GET(SUB_IRL)
       public Call<NewsResult>fetchNews();

}
