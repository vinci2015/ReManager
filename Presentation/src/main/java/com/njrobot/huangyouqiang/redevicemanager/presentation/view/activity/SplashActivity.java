package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.presentation.utils.Constant;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.rl_root) RelativeLayout rootView;
    @BindView(R.id.rl_pwd) RelativeLayout rlPassword;
    @BindView(R.id.tv_pwd) TextView tvPassword;
    private StringBuilder currentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        currentStr = new StringBuilder();
    }
    public void onNumberClick(View view){
        String s = ((Button)view).getText().toString();
        addPassword(s);
    }

    public void addPassword(String s){
        rlPassword.setVisibility(View.VISIBLE);
        currentStr.append(s);
        tvPassword.setText(currentStr.toString());
        if(currentStr.length() == 4){
            checkPassword(currentStr.toString());
        }
    }
    private void checkPassword(String s){
        if(s.equals(Constant.STR_PASSWPRD)){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else{
            Snackbar.make(rootView,"password is incorrect",Snackbar.LENGTH_SHORT).show();
            tvPassword.setText("");
            rlPassword.setVisibility(View.INVISIBLE);
        }
        currentStr.setLength(0);
    }
    public void onDeletePwd(View view){
        currentStr.setLength(0);
        tvPassword.setText("");
    }
}
