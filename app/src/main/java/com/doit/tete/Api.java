package com.doit.tete;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/bucheon-api/api/pub/v1/bt-devices") //베이스 url뒤에 이어받을 주소
    Call<List<Data>> getData(); // 받아올 JSON데이터의 시작이 배열이기에 List타입으로 받아줌


}
