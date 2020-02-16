package com.holaf.easypay.ui;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.holaf.easypay.R;
import com.holaf.easypay.souce.SaveClass;
import com.holaf.easypay.souce.User;

public class FillDataUserActivity extends Activity {
    private User user;
    private TextView user_welcome;
    private EditText email, fone, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_data_user);

        user_welcome = findViewById(R.id.fill_data_screen_welcome);
        email = findViewById(R.id.fill_data_screen_email);
        fone = findViewById(R.id.fill_data_screen_fone);
        password = findViewById(R.id.fill_data_screen_password);
        passwordConfirm = findViewById(R.id.fill_data_screen_password_confirm);

        SaveClass.deleteFile(getCacheDir().toString() + "/temp000");

        try {
            user = (User) getIntent().getExtras().get("UserClass");
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "NullPointerException check user name", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), NewUserActivity.class));
            return;
        }

        user_welcome.setText(getString(R.string.hi) + ", " + user.getName() + "\n" + getString(R.string.fill_your_data));
        fone.addTextChangedListener(MaskEditUtil.mask(fone, MaskEditUtil.FORMAT_FONE));


        textWatchADD((TextView) findViewById(R.id.fill_data_screen_email_txt), email, getString(R.string.email), ": muito curto", 2);
        textWatchADD((TextView) findViewById(R.id.fill_data_screen_password_text), password, getString(R.string.password), ": muito curta", User.CONFIG.MIN_LEN_PASSWORD);
        textWatchADD((TextView) findViewById(R.id.fill_data_screen_fone_txt), fone, getString(R.string.fone), ": muito curto", 15);

        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView t = findViewById(R.id.fill_data_screen_password_confirm_text);
                Spannable txt = new SpannableString(getString(R.string.confirm_password));
                if (s.length()>0 && !s.toString().equals(password.getText().toString())) {
                    txt = new SpannableString(getString(R.string.confirm_password) + ": senhas não compativeis");
                    txt.setSpan(new ForegroundColorSpan(Color.RED), getString(R.string.confirm_password).length() + 1, txt.length(), 0);
                }
                t.setText(txt);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    // (34)9 9995-4627
    public void next(View v) {
        String s_email = email.getText().toString(),
                s_fone = fone.getText().toString().replaceAll("[\\D]", ""),
                s_pass = password.getText().toString(),
                s_confirm_pass = passwordConfirm.getText().toString();
        if (s_email.length() < 2) {
            Toast.makeText(getApplicationContext(), "email:  inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s_fone.length() < 11) {
            Toast.makeText(getApplicationContext(), "telefone:  inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s_pass.length() < User.CONFIG.MIN_LEN_PASSWORD || s_pass.equals(user.getName().toLowerCase().replaceAll("[^a-z]", ""))) {
            Toast.makeText(getApplicationContext(), "password:  inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!s_pass.equals(s_confirm_pass)) {
            Toast.makeText(getApplicationContext(), "senhas não se correspondem", Toast.LENGTH_SHORT).show();
            return;
        }
        user.setEmail(s_email);
        user.setNumber(s_fone);

        Intent nextScreen = new Intent(getApplicationContext(), SignUpActivity.class);
        nextScreen.putExtra("UserClass", user);
        nextScreen.putExtra("code_000", s_pass);
        startActivity(nextScreen);
        finish();


    }

    private void textWatchADD(final TextView v, EditText e, final String defaultS, final String msg, final int min_len) {
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Spannable txt = new SpannableString(defaultS);
                int len = s.length();
                if (len > 0 && len < min_len) {
                    txt = new SpannableString(defaultS + msg);
                    txt.setSpan(new ForegroundColorSpan(Color.RED), defaultS.length() + 1, txt.length(), 0);
                }
                v.setText(txt);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
