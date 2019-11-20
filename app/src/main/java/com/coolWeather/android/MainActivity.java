package com.coolWeather.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coolWeather.android.register.MyDataBaseHelper;
import com.coolWeather.android.register.Register;

public class MainActivity extends AppCompatActivity {
    private MyDataBaseHelper dbHelper;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login_button;
    private Button register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new MyDataBaseHelper(this,"UserData.db",null,1);
        accountEdit=(EditText)findViewById(R.id.accountLog_text);
        passwordEdit=(EditText)findViewById(R.id.passwordLog_text);
        login_button=(Button)findViewById(R.id.login_button);
        register_button=(Button)findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if(login(account,password)){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean login(String account,String password){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select * from   userdata where account=? and password=?";
        Cursor cursor=db.rawQuery(sql,new String[]{account,password});
        if(cursor.moveToFirst()){
            cursor.close();
            return  true;
        }
        return false;
    }
}
