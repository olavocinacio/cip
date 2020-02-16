package com.holaf.easypay.javascript;

import android.content.Context;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;


public class JavaScriptInterpreter {
    Context context;
    WebView wv;

    public JavaScriptInterpreter(Context context) {
        this.context = context;
        WebView wv = new WebView(context);
        wv.getSettings().setJavaScriptEnabled(true);

    }

    public  void eval( String source, ValueCallback<String> callback ){
        wv.evaluateJavascript(source,callback);
        Log.d("js evalue","Run code");
    }

}
