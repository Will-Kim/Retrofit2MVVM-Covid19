package com.appcognito.retrofit2mvvm_covid19.api;

import com.appcognito.retrofit2mvvm_covid19.api.models.SidoInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("openapi/service/rest/Covid19/getCovid19SidoInfStateJson")
    @Headers({"Accept: application/xml"})
    Call<SidoInfo> getData(@Query("serviceKey") String serviceKey,
                           @Query("numOfRows") int numOfRows,
                           @Query("pageNo") int pageNo,
                           @Query("startCreateDt") String startCreate_dt,
                           @Query("endCreateDt") String endCreateDt);
}
