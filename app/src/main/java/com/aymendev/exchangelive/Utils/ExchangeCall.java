package com.aymendev.exchangelive.Utils;

import android.util.Log;

import androidx.annotation.Nullable;

import com.aymendev.exchangelive.model.Money;
import com.aymendev.exchangelive.pojo.MoneyService;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeCall {
    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable List<Money> moneys);
        void onFailure();
    }

    // 2 - Public method to start fetching Money Data
    public static void fetchUserFollowing(Callbacks callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        MoneyService moneyService = MoneyService.retrofit.create(MoneyService.class);

        // 2.3 - Create the call on Exchange API
        Call<Money> call = moneyService.getFollowing();
        Log.i("TestAPI", moneyService.toString());
        // 2.4 - Start the call
        call.enqueue(new Callback<Money>() {

            @Override
            public void onResponse(Call<Money> call, Response<Money> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Money> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
