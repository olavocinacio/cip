package com.holaf.easypay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holaf.easypay.souce.SaveClass;
import com.holaf.easypay.souce.User;
import com.holaf.easypay.ui.FillCpf;
import com.holaf.easypay.ui.FillDataUserActivity;
import com.holaf.easypay.ui.NewUserActivity;

import java.io.Serializable;


public class MainActivity extends Activity {
    private ProgressBar progressBar;
    private TextView js_cons;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.intro_progress);
        js_cons = findViewById(R.id.textView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                load000();
            }
        }, 900);

    }
    public  void load000(){
        SaveClass.load(this, getCacheDir().toString() + "/temp000", new SaveClass.OnLoadListener() {
            @Override
            public void onLoad(Object o) {
                user = (User) o;
                if (user==null){
                    startActivity(new Intent(getApplicationContext(),NewUserActivity.class));
                    return;
                }
                SaveClass.deleteFile(getCacheDir().toString() + "/temp000");
                Intent nextScreen  = new Intent(getApplicationContext(), FillCpf.class);
                nextScreen.putExtra("UserClass", user);
                finish();
                startActivity(nextScreen);
            }
        });
    }
}
