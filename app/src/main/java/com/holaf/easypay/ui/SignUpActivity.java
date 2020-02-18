package com.holaf.easypay.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.holaf.easypay.HomeActivity;
import com.holaf.easypay.R;
import com.holaf.easypay.souce.User;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        User u = (User) getIntent().getExtras().get("UserClass");

        try {
            Log.d("debug ",getApplicationContext().getFilesDir().getAbsolutePath());
            u.Save(getApplicationContext().getFilesDir().getAbsolutePath(), (String) getIntent().getExtras().get("code_000"));
        } catch (User.FileAlreadyExistSException e) {
            Toast.makeText(getApplicationContext(),"Usuario ja cadastrado",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),NewUserActivity.class));
            finish();
            e.printStackTrace();
            return;
        }
        Intent nextScren = new Intent(getApplicationContext(), HomeActivity.class);
//        nextScren.putExtra("")
        startActivity(nextScren);
        finish();
    }
}
