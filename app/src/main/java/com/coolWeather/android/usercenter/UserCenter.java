package com.coolWeather.android.usercenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.coolWeather.android.usercenter.PersonalItemView;
import com.coolWeather.android.R;

public class UserCenter extends AppCompatActivity {


//RelativeLayout l=(RelativeLayout)findViewById(R.id.nickname_layout);
private static Context context;
    private static final String TAG = "UserCenter";
    PersonalItemView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"进入我的加载布局前");
        setContentView(R.layout.activity_usercenter);
        l=(PersonalItemView) findViewById(R.id.nickname_layout);
        context=getApplicationContext();

    }

    @Override
    protected void onResume() {
        super.onResume();
        returnData();
    }
    public String  returnData() {
        SharedPreferences spf = getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = spf.getString("account", "");
        Log.d(TAG, "昵称为：" + name);
        l.setData(name);
        return name;
    }
}
