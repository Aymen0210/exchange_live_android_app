package com.aymendev.exchangelive.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aymendev.exchangelive.R;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    Button btn_get;
    TextView text;
    ExchangeLiveViewModel exchangeLiveViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get = findViewById(R.id.btn_get);
        text=findViewById(R.id.text);

        btn_get.setOnClickListener(this);

        exchangeLiveViewModel= ViewModelProviders.of(this).get(ExchangeLiveViewModel.class);
        exchangeLiveViewModel.muDataGeted.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("TestAPI",s);
                text.setText(s);
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_get){
            exchangeLiveViewModel.getData();
        }
    }
}
