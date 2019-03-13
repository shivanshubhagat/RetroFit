package com.example.myapplication.activity.activity.activities.rest;


import com.example.myapplication.activity.activity.activities.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {

    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all/?fields=name;capital")
    Call<ArrayList<Country>> getCountries();

}
