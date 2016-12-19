/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package tony.com.foolhttp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.tony.foolhttp.FoolHttp


class MainActivity : AppCompatActivity() {

    lateinit var test1: Button;


    lateinit var codeText: TextView;
    lateinit var webview: WebView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        test1 = findViewById(R.id.test_right) as Button;

        codeText = findViewById(R.id.res_code) as TextView;
        webview = findViewById(R.id.mywebview) as WebView;
        webview.setWebChromeClient(WebChromeClient());
        webview.setWebViewClient(WebViewClient());

        test1.setOnClickListener { view ->

            FoolHttp.get("https://www.baidu.com", null, { response ->
                runOnUiThread {
                    codeText.text = "status:" + response.httpStateCode.toString();
                    webview.loadData(response.toString(), "text/html", response.httpEncode);

                }

            }, { response, request ->
                runOnUiThread {
                    codeText.text = "failed:" + response.httpStateCode.toString();

                }

            });

        }


    }
}
