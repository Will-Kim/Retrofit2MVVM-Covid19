package com.appcognito.retrofit2mvvm_covid19.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appcognito.retrofit2mvvm_covid19.api.models.SidoInfo;

import java.util.List;

public class MainViewModel extends ViewModel {
    public static MutableLiveData<List<SidoInfo.SidoItem>> sidoItems = new MutableLiveData<>();
    public void setData(SidoInfo resp) {
        sidoItems.setValue(resp.body.items);
    }
}