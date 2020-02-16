package com.holaf.easypay.ui;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.holaf.easypay.MainActivity;
import com.holaf.easypay.R;
import com.holaf.easypay.souce.SaveClass;
import com.holaf.easypay.souce.User;

import java.io.Serializable;

public class TermosUsoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_uso);
    }
    public void termosUso(View v){
        finish();
        Intent nextScreen = new Intent(getApplicationContext(),FillCpf.class);
        User user = (User) getIntent().getExtras().get("UserClass");
        SaveClass.save(user,getCacheDir().toString()+"/temp000");
        nextScreen.putExtra("UserClass", user);
        startActivity(nextScreen);
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
        finish();
    }
}
