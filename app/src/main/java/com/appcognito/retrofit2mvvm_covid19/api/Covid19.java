package com.appcognito.retrofit2mvvm_covid19.api;

import android.util.Log;

import com.appcognito.retrofit2mvvm_covid19.api.models.SidoInfo;
import com.appcognito.retrofit2mvvm_covid19.ui.main.MainViewModel;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Covid19 {
    // data.go.kr 에서 "보건복지부_코로나19 시·도발생_현황"을 신청하면 금방 서비스키가 발급됨.
    // you should get service key from data.go.kr (South Korea Open API Portal)
    private String serviceKey = "you service key should be here";

    public void getSidoInfo(String fromDt, String endDt, final MainViewModel viewModel, final ApiListener listener) {
        try {
            RetrofitInterface apiService = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);

            Call<SidoInfo> call = apiService.getData(serviceKey, 10, 1, fromDt, endDt);
            call.enqueue(new Callback<SidoInfo>() {

                @Override
                public void onResponse(Call<SidoInfo> call, Response<SidoInfo> response) {
                    SidoInfo resp = response.body();
                    viewModel.setData(resp);
                    Log.e("getSidoInfo success", response.message());
                    listener.onSuccess();
                }

                @Override
                public void onFailure(Call<SidoInfo> call, Throwable t) {
                    Log.e("Response fail", t.getMessage());
                    if(t instanceof SocketTimeoutException) {
                        listener.onFail(t.getMessage());
                        Log.e("getSidoInfo", "TIMEOUT: " + t.getMessage());
                    } else {
                        listener.onFail(t.getMessage());
                        Log.e("getSidoInfo", "Error: " + t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }


}

