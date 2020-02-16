package com.holaf.easypay.ui;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.holaf.easypay.R;
import com.holaf.easypay.souce.CpfValidator;
import com.holaf.easypay.souce.SaveClass;
import com.holaf.easypay.souce.User;

public class FillCpf extends Activity {
    TextView wellcome;
    EditText cpf;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_cpf);
        wellcome = findViewById(R.id.fill_cpf_screen_welcome);
        cpf = findViewById(R.id.fill_cpf_screen_cpf);


        user =(User) getIntent().getExtras().get("UserClass");
        wellcome.setText(getString(R.string.great)+"!\n"+user.getName()+"\n"+getString(R.string.lets_start));


        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF, new Runnable() {
            @Override
            public void run() {
                if (cpf.length() == MaskEditUtil.FORMAT_CPF.length()) {
                    final String cpfstring = cpf.getText().toString();
                    new Thread() {
                        @Override
                        public void run() {
                            if (!CpfValidator.CPF(cpfstring)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView t = findViewById(R.id.fill_cpf_screen_cpf_txt);
                                        Spannable spannable = new SpannableString(getString(R.string.cpf) + " * " + getString(R.string.invalid));
                                        spannable.setSpan(new ForegroundColorSpan(Color.RED), 4, spannable.length(), 0);
                                        t.setText(spannable);
                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView t = findViewById(R.id.fill_cpf_screen_cpf_txt);
                                        Spannable spannable = new SpannableString(getString(R.string.cpf) + " tudo certo* ");
                                        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), 4, spannable.length(), 0);
                                        t.setText(spannable);
                                        user.setCpf(cpfstring.replace(".","").replace("-",""));

                                        Intent nextScreen = new Intent(getApplicationContext(),FillDataUserActivity.class);
                                        nextScreen.putExtra("UserClass", user);
                                        startActivity(nextScreen);
                                        finish();


                                    }
                                });
                            }
                        }
                    }.start();
                } else {
                    TextView t = findViewById(R.id.fill_cpf_screen_cpf_txt);
                    t.setText(R.string.cpf);
                }
            }
        }));
    }

    @Override
    public void onBackPressed() {
        Intent nextScreen = new Intent(getApplicationContext(),TermosUsoActivity.class);
        nextScreen.putExtra("UserClass", user);
        startActivity(nextScreen);
        finish();
    }
}
