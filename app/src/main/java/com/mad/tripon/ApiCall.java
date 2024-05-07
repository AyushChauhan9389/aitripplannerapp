package com.mad.tripon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCall {
    @POST("place/textsearch/json?key=AIzaSyDnfusXpuJiI2pu9d6_PaJCKfm8kGcsJo4")
    @Headers({"Content-Type: application/json"})
    Call<PlaceResponseModel> getAllPlaces(@Query("query") String query);
}
