package net.loisusong.android.loisusong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import net.loisusong.android.loisusong.R;
import net.loisusong.android.loisusong.service.wrapper.PostIntentWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostLoisusongActivity extends AppCompatActivity {
	@BindView(R.id.tl_post_loisusong)
	Toolbar toolbar;
	@BindView(R.id.iv_post_loisusong)
	ImageView imageView;
	@BindView(R.id.tv_title)
	TextView titleTextView;
	@BindView(R.id.tv_date)
	TextView dateTextView;
	@BindView(R.id.wv_post_loisusong)
	WebView webView;

	private WebSettings webSettings;
	private String title, date, img, content;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_loisusong);

		ButterKnife.bind(this);
		setUpIntentData();
		setUpToolbar();
		setUpContent();
		setUpLayout();
	}

	private void setUpToolbar() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(Html.fromHtml(title));
	}

	private void setUpIntentData() {
		Intent intent = getIntent();
		PostIntentWrapper intentWrapper = new PostIntentWrapper(intent);
		title = intentWrapper.getTitle();
		date = intentWrapper.getDate();
		img = intentWrapper.getImg();
		content = intentWrapper.getContent();
	}

	private void setUpContent() {
		content = reformat(content);
		content = "<!DOCTYPE html>" +
				"<html><head></head><body>" +
				"<div style='width: \'100vw\'; " +
				"word-break: \'break-all\'; " +
				"word-wrap: \'break-word\';'>" +
				content + "</div></body></html>";
	}

	private String reformat(String s) {
		s = s.replaceAll("<p><iframe[^>]*></iframe></p>", "");
		s = s.replaceAll("Premiera Book", "'-apple-system', 'HelveticaNeue'");
		s = s.replaceAll("Times-u", "'-apple-system', 'HelveticaNeue'");
		s = s.replaceAll("font-size: 20px;", "font-size: 15px;");
		s = s.replaceAll("font-size: 22px;", "font-size: 15px;");
		s = s.replaceAll("class=\"wp-caption-text\"", "style=\"font-family: Helvetica; " +
				"font-size: 13px; font-style: italic; " +
				"text-align: left; line-height: 1.8em; " +
				"max-width: 96%; padding: 0 4px 5px; " +
				"background: #fff; margin: 0; box-sizing: border-box; " +
				"word-wrap: break-word;\"");
		s = s.replaceAll("width: [0-9]+px", "width: 100%");
		s = s.replaceAll("<img", "<img style=\"width: 100%; height: auto;\"");
		s = s.replaceAll("<p>(?i)y(?i)o(?i)u(?i)t(?i)u(?i)b(?i)e.+</p>", "");
		s = s.replaceAll("<div class=\"pdf24Plugin-cp\">.+</div>", "");
		return s;
	}

	private void setUpLayout() {
		setUpCommonView();
		setUpWebView();
	}

	private void setUpCommonView() {
		titleTextView.setText(Html.fromHtml(title));
		dateTextView.setText(date);
		Picasso.get()
				.load(img)
				.placeholder(R.drawable.image_placeholder)
				.into(imageView);
	}

	private void setUpWebView() {
		webSettings = webView.getSettings();
		webSettings.setDefaultTextEncodingName("utf-8");
		webSettings.setAppCacheEnabled(false);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.setWebViewClient(new PostWebViewClient());
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
			}
		});
		webView.loadData(content, "text/html; charset=utf-8", null);
	}

	public class PostWebViewClient extends WebViewClient {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
