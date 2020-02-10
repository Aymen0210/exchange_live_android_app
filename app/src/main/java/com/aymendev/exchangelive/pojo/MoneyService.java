package com.aymendev.exchangelive.pojo;

import com.aymendev.exchangelive.model.Money;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MoneyService {

    @GET("latest")
    Call<Money> getFollowing();
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
