package com.example.xumengyin.mypractice.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.example.xumengyin.mypractice.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/27.
 */

public class WebViewTestActivity extends BaseActivity
{
	@BindView(R.id.webview)
	WebView vWebView;
	@BindView(R.id.button)
	Button vButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWebView();
	}

	private void initWebView()
	{
		WebSettings setting= vWebView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setJavaScriptCanOpenWindowsAutomatically(true);
		vWebView.loadUrl("file:///android_asset/js.html");
		vWebView.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result)
			{
				logMsg("onJsAlert");
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			public boolean onJsConfirm(WebView view, String url, String message, JsResult result)
			{
				logMsg("onJsConfirm");
				return super.onJsConfirm(view, url, message, result);
			}

			@Override
			public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result)
			{
				logMsg("onJsPrompt");
				return super.onJsPrompt(view, url, message, defaultValue, result);
			}
		});




	}
	@Override
	protected int getcontentView()
	{
		return R.layout.activity_web_view_test;
	}
	@OnClick(R.id.button)
	public void load(View view)
	{
//		logMsg("load");
//		vWebView.loadUrl("javascript:callJS()");
		//vWebView.evaluateJavascript();
	}
}
