package com.doit.tete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RetrofitAdapter mAdapter;
    private final String URL = "https://senior.bucheon.go.kr";     // 베이스url을 상수로 지정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //리사이클러뷰 연결
        // 레이아웃매니저 객체생성후 리사이클러뷰에 셋팅
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        // 레트로핏 객체 생성
        // url불러와서 GSON형식으로 컨버터 후 빌드
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //  Api인터페이스 생성
        // 인터페이스를 통해 JSON데이터 호출후 요청
        Api api = retrofit.create(Api.class);
        api.getData().enqueue(new Callback<List<Data>>() {
            // 서버와의 통신에 성공했을때
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    // data안에 받아온 JSON데이터를 저장
                    ArrayList<Data> data = (ArrayList<Data>) response.body();
                    Log.d("TEST", "성공성공"); // 통신에 성공했을때 로그 출력
                    Log.d("TEST", String.valueOf(data)); //받아온 JSON데이터 로그출력

                    //어뎁터와 같은타입의  i 리스트를 생성
                    List<ArrayList<Data>> i = new ArrayList<ArrayList<Data>>();

                    // 리스트안에 JSON데이터 저장 전부 동일한 값을 데이터
                    i.add(data);
                    i.add(data);
                    i.add(data);
                    i.add(data);

                    // 저장된 리스트를 어댑터로 넘겨주고 리사이클러뷰에 셋팅
                    mAdapter = new RetrofitAdapter(i);
                    recyclerView.setAdapter(mAdapter);


                }
            }
                    // 서버와의 통신에 실패했을때
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}