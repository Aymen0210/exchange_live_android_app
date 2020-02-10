package com.aymendev.exchangelive.ui;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aymendev.exchangelive.Utils.ExchangeCall;
import com.aymendev.exchangelive.model.Money;

import java.util.List;

public class ExchangeLiveViewModel extends ViewModel implements ExchangeCall.Callbacks {
    MutableLiveData<String> muDataGeted = new MutableLiveData<>();

    public void getData() {
        executeHttpRequestWithRetrofit();
    }

    void executeHttpRequestWithRetrofit() {
        updateUIWhenStartingHTTPRequest();
        ExchangeCall.fetchUserFollowing(this);
    }

    @Override
    public void onResponse(@Nullable List<Money> moneys) {
        if (moneys != null) updateInterfaceWithMoneyLive(moneys);
    }

    private void updateInterfaceWithMoneyLive(List<Money> moneys) {

        String stringBuilder = "";
        for (Money money : moneys) {
            stringBuilder=stringBuilder+"-" + moneys.toString() + "\n";
        }
//        for (int i = 0; i < moneys.size() - 1; i++) {
            Log.i("TestAPI", moneys.get(0).toString());
            muDataGeted.setValue(stringBuilder);
//        }
    }

    @Override
    public void onFailure() {
        updateUIWhenStopingHTTPRequest("An error happened !");
        Log.i("TestAPI", "error");
    }

    private void updateUIWhenStartingHTTPRequest(){
        this.muDataGeted.setValue("Downloading...");
    }
    private void updateUIWhenStopingHTTPRequest(String response){
        this.muDataGeted.setValue(response);
    }
}
