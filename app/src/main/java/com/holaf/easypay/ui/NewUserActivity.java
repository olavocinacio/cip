package com.holaf.easypay.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.holaf.easypay.R;
import com.holaf.easypay.souce.User;
import com.holaf.easypay.ui.login.LoginActivity;

public class NewUserActivity extends Activity {
    private EditText name;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        name = findViewById(R.id.new_user_name);

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN && name.getText().toString().equals(getResources().getString(R.string.insert_name))){
                    name.setText("");
                }
                return false;
            }
        });

        name.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        nextScreen(v);
                    return true;
                }
                return false;
            }
        });
//        name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (name.getText().toString().equals(getResources().getString(R.string.insert_name))){
//                    name.setText("");
//                }
//
//            }
//        });

    }
    public void login(View v){
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
    public void nextScreen(View v){
        String userName = name.getText().toString();
        if(userName.equals(getString(R.string.insert_name)) || userName.length()==0 ){
            Toast.makeText(getApplicationContext(), R.string.invalid_name,Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(userName);
        Intent nextscreen = new Intent(getApplicationContext(), TermosUsoActivity.class);
        nextscreen.putExtra("UserClass", user);
        finish();
        startActivity(nextscreen);
    }
}
