package com.lawyee.cardstck.html;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;

import com.lawyee.cardstck.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        bindViewData();
    }

    private void bindViewData() {
        mWebview.getSettings().setJavaScriptEnabled(true);
//        mWebview.loadUrl("file:///android_assets/web.html");
        mWebview.loadUrl("file:///android_asset/web.html");
        mWebview.addJavascriptInterface(new JsInterfacel(this), "android");
    }


    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mWebview = (WebView) findViewById(R.id.webview);

        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mWebview.loadUrl("javascript:javashow('xiao')");
                break;
            case R.id.button2:
                mWebview.loadUrl("javascript:javashow('小核酸')");
                break;
        }
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
  /*  @JavascriptInterface
    public void javashow(final String name) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity.this, "22222", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

  /*  @JavascriptInterface
    public void show() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(Main2Activity.this).setMessage("2222").show();
            }
        });
    }*/

    public class JsInterfacel {
        private Context mContext;

        public JsInterfacel(Context context) {
            this.mContext = context;
        }


        @JavascriptInterface
        public void show() {
            new AlertDialog.Builder(mContext).setMessage("2222").show();
        }

        @JavascriptInterface
        public void javashow(final  String name){
//            new AlertDialog.Builder(mContext).setMessage("2222"+name).show();
            mWebview.loadUrl("javascript:javashow('"+name+"')");
        }
    }
}
