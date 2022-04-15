package com.doit.tete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RetrofitAdapter mAdapter, mAdapter2, mAdapter3, mAdapter1;
    private ArrayList<Data> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://senior.bucheon.go.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.getData().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Data> data = (ArrayList<Data>) response.body();
                    Log.d("TEST", "성공성공");
                    Log.d("TEST", String.valueOf(data));

                    List<ArrayList<Data>> i = new ArrayList<ArrayList<Data>>();


                    i.add(data);
                    i.add(data);
                    i.add(data);
                    i.add(data);
//                    i.add(data.get(0).getDevices());
//                    i.add(data.get(1).getDevices());
//                    i.add(data.get(2).getDevices());
//                    i.add(data.get(3).getDevices());
                 //   Log.d("TEST", String.valueOf(i));


                    mAdapter = new RetrofitAdapter(i);
//                    mAdapter = new RetrofitAdapter(data.get(1).getDevices());
//                    mAdapter2 = new RetrofitAdapter(data.get(2).getDevices());
//                    mAdapter3 = new RetrofitAdapter(data.get(3).getDevices());

                    recyclerView.setAdapter(mAdapter);


                }
            }

            @Override
            public void onFailure(Call <List<Data>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}