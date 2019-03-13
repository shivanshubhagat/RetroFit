package com.example.myapplication.activity.activity.activities.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.activity.activity.activities.adapter.CountryAdapter;
import com.example.myapplication.activity.activity.activities.model.Country;
import com.example.myapplication.activity.activity.activities.rest.CountryApi;
import java.util.ArrayList;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> saveCountryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createRetrofit();

        Call<ArrayList<Country>> call = createRetrofit().getCountries();
        Paper.init(MainActivity.this);
        saveCountryArrayList = Paper.book().read("countries");
        if (!Paper.book().contains("countries")) {
            call.enqueue(new Callback<ArrayList<Country>>() {
                @Override
                public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                    if (!response.body().isEmpty()) {
                        ArrayList<Country> countryArrayList = response.body();
                        Paper.book().write("countries", countryArrayList);
                        recyclerView.setAdapter(new CountryAdapter(countryArrayList));
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                }
            });
        } else {
            recyclerView.setAdapter(new CountryAdapter(saveCountryArrayList));
        }
    }

    private CountryApi createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CountryApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CountryApi countryAPI = retrofit.create(CountryApi.class);
        return countryAPI;
    }
}
