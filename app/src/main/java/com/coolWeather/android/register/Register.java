package com.coolWeather.android.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coolWeather.android.LoginActivity;
import com.coolWeather.android.MainActivity;
import com.coolWeather.android.R;

public class Register extends AppCompatActivity {
    private static final String TAG = "Register";
private MyDataBaseHelper dbRegisterHelper;
private EditText account_text;
private EditText password_text;
private EditText password_again_text;
private EditText school_text;
private Button registerResult_button;
//用户注册时所填写的名字以及密码
String newName;
String password;
String passwordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbRegisterHelper=new MyDataBaseHelper(this,"UserData.db",null,1);
        account_text=(EditText)findViewById(R.id.account_text);
        password_text=(EditText)findViewById(R.id.password_text);
        password_again_text=(EditText)findViewById(R.id.password_again_text);
        school_text=(EditText)findViewById(R.id.school_text);
        registerResult_button=(Button)findViewById(R.id.btn_register) ;




        registerResult_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerResult();
            }
        });



    }

    private boolean CheckPasswordIsTrue(String password, String passwordAgain) {
        if(password.equals(passwordAgain)){
            return false;
        }
        return true;
    }

    public boolean register(String account,String password){
        SQLiteDatabase db = dbRegisterHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account", newName);
        values.put("password", password);
        db.insert("userdata",null,values);
        db.close();
        return true;
}

    private boolean CheckIsDataAlreadyInDBorNot(String newName) {
            SQLiteDatabase db = dbRegisterHelper.getWritableDatabase();
            String Query="Select * from userdata where account=?";
            Cursor cursor=db.rawQuery(Query,new String[]{newName});
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }
        return false;
    }
    public void registerResult(){
        newName=account_text.getText().toString();
        password=password_text.getText().toString();
        passwordAgain=password_again_text.getText().toString();
        Log.d(TAG,"newName is "+newName);
        Log.d(TAG,"password is "+password);
        Log.d(TAG,"passwordAgain is "+passwordAgain);
        if(CheckPasswordIsTrue( password, passwordAgain)){
            Toast.makeText(Register.this,"两次密码输入不同",Toast.LENGTH_SHORT).show();
        }
        else {
            if (CheckIsDataAlreadyInDBorNot(newName)) {
                Toast.makeText(Register.this,"用户名已存在",Toast.LENGTH_SHORT).show();
            }else{
                if(register(newName,password)){
                    Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

}
