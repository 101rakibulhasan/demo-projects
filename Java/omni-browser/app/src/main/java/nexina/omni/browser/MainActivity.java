package nexina.omni.browser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class MainActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private String pass = "";
	private double n = 0;
	private String version = "";
	private HashMap<String, Object> firebase = new HashMap<>();
	private String download_app = "";
	
	private ArrayList<HashMap<String, Object>> retrieved_info = new ArrayList<>();
	
	private LinearLayout linear24;
	private LinearLayout linear25;
	private LinearLayout linear1;
	private LinearLayout linear26;
	private EditText edittext3;
	private Button button11;
	private TextView textview1;
	private LinearLayout linear2;
	private ProgressBar progressbar1;
	private LinearLayout linear10;
	private WebView webview1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear17;
	private LinearLayout linear21;
	private EditText edittext1;
	private LinearLayout linear6;
	private LinearLayout linear9;
	private ImageView imageview2;
	private ImageView imageview1;
	private Button button6;
	private Button button8;
	private LinearLayout linear32;
	private LinearLayout linear33;
	private ImageView imageview3;
	private LinearLayout linear11;
	private LinearLayout linear22;
	private EditText edittext2;
	private LinearLayout linear23;
	private Spinner spinner2;
	private Button button9;
	private LinearLayout linear37;
	private LinearLayout linear36;
	private LinearLayout linear34;
	private Button button13;
	private LinearLayout linear38;
	private Button button16;
	private Button button14;
	private LinearLayout linear39;
	private Button button17;
	private Button button15;
	private LinearLayout linear40;
	private Button button18;
	private LinearLayout left_box;
	private LinearLayout linear5;
	private LinearLayout linear14;
	private LinearLayout linear7;
	private LinearLayout linear30;
	private LinearLayout linear13;
	private LinearLayout linear12;
	private Button button1;
	private Button button2;
	private Button button12;
	private Button button4;
	private Button button5;
	private Button button10;
	
	private SharedPreferences log;
	private TimerTask favicon;
	private Intent open_activity = new Intent();
	private SharedPreferences setting;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear24 = (LinearLayout) findViewById(R.id.linear24);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		button11 = (Button) findViewById(R.id.button11);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		button6 = (Button) findViewById(R.id.button6);
		button8 = (Button) findViewById(R.id.button8);
		linear32 = (LinearLayout) findViewById(R.id.linear32);
		linear33 = (LinearLayout) findViewById(R.id.linear33);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		button9 = (Button) findViewById(R.id.button9);
		linear37 = (LinearLayout) findViewById(R.id.linear37);
		linear36 = (LinearLayout) findViewById(R.id.linear36);
		linear34 = (LinearLayout) findViewById(R.id.linear34);
		button13 = (Button) findViewById(R.id.button13);
		linear38 = (LinearLayout) findViewById(R.id.linear38);
		button16 = (Button) findViewById(R.id.button16);
		button14 = (Button) findViewById(R.id.button14);
		linear39 = (LinearLayout) findViewById(R.id.linear39);
		button17 = (Button) findViewById(R.id.button17);
		button15 = (Button) findViewById(R.id.button15);
		linear40 = (LinearLayout) findViewById(R.id.linear40);
		button18 = (Button) findViewById(R.id.button18);
		left_box = (LinearLayout) findViewById(R.id.left_box);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear30 = (LinearLayout) findViewById(R.id.linear30);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button12 = (Button) findViewById(R.id.button12);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button10 = (Button) findViewById(R.id.button10);
		log = getSharedPreferences("log", Activity.MODE_PRIVATE);
		setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
		
		edittext3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext2.getText().toString().equals(setting.getString("pass", ""))) {
					linear25.setVisibility(View.GONE);
					linear1.setVisibility(View.VISIBLE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "W R O N G");
				}
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				edittext1.setText(_url);
				progressbar1.setIndeterminate(false);
				progressbar1.setProgress((int)10);
				log.edit().putString("lasturl", webview1.getUrl()).commit();
				if (!_url.equals("omni://home")) {
					webview1.setVisibility(View.VISIBLE);
					linear10.setVisibility(View.GONE);
				}
				else {
					webview1.setVisibility(View.GONE);
					linear10.setVisibility(View.VISIBLE);
					edittext1.setText("");
				}
				progressbar1.setVisibility(View.VISIBLE);
				setTitle(webview1.getTitle());
				log.edit().putString(String.valueOf((long)(n)), _url).commit();
				n++;
				favicon = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								imageview1.setImageResource(R.drawable.ic_cached_black);
							}
						});
					}
				};
				_timer.schedule(favicon, (int)(1000));
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				progressbar1.setIndeterminate(true);
				progressbar1.setVisibility(View.GONE);
				if (_url.equals("omni://home")) {
					imageview1.setImageResource(R.drawable.ic_home_grey);
				}
				else {
					favicon = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									imageview1.setImageBitmap(webview1.getFavicon());
								}
							});
						}
					};
					_timer.schedule(favicon, (int)(1000));
				}
				super.onPageFinished(_param1, _param2);
			}
		});
		
		edittext1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl(webview1.getUrl());
			}
		});
		
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().contains("https://") || (edittext1.getText().toString().contains("http://") || (edittext1.getText().toString().contains("www.") || (edittext1.getText().toString().contains(".") || (edittext1.getText().toString().contains(".com") || (edittext1.getText().toString().contains(".in") || (edittext1.getText().toString().contains(".org") || (edittext1.getText().toString().contains(".na") || (edittext1.getText().toString().contains("m.") || (edittext1.getText().toString().contains(".xyz") || edittext1.getText().toString().contains(".na"))))))))))) {
					if (edittext1.getText().toString().contains("www.") || edittext1.getText().toString().contains("m.")) {
						webview1.loadUrl("https://".concat(edittext1.getText().toString()));
					}
					if (edittext1.getText().toString().contains(".") || (edittext1.getText().toString().contains(".com") || (edittext1.getText().toString().contains(".in") || (edittext1.getText().toString().contains(".org") || (edittext1.getText().toString().contains(".na") || (edittext1.getText().toString().contains("m.") || edittext1.getText().toString().contains(".xyz"))))))) {
						webview1.loadUrl("https://".concat(edittext1.getText().toString()));
					}
					if (edittext1.getText().toString().contains("https://") || edittext1.getText().toString().contains("https://")) {
						webview1.loadUrl(edittext1.getText().toString());
					}
				}
				else {
					if (!edittext1.getText().toString().equals("omni://home")) {
						webview1.loadUrl("https://www.google.com/search?q=".concat(edittext1.getText().toString()));
						webview1.setVisibility(View.VISIBLE);
						linear10.setVisibility(View.GONE);
					}
					else {
						webview1.setVisibility(View.GONE);
						linear10.setVisibility(View.VISIBLE);
					}
				}
			}
		});
		
		edittext2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.setVisibility(View.VISIBLE);
				linear10.setVisibility(View.GONE);
				webview1.loadUrl("https://www.google.com/search?q=".concat(edittext2.getText().toString()));
			}
		});
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://m.facebook.com/");
			}
		});
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://www.reddit.com/");
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://m.youtube.com/");
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://www.linkedin.com/");
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://mobile.twitter.com/");
			}
		});
		
		button18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("https://nexinacorp.wordpress.com");
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("omni://home");
				webview1.setVisibility(View.GONE);
				linear10.setVisibility(View.VISIBLE);
				edittext1.setText("omni://home");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (webview1.canGoBack()) {
					webview1.goBack();
				}
			}
		});
		
		button12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				download_app = retrieved_info.get((int)1).get("url").toString();
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (webview1.canGoForward()) {
					webview1.goForward();
				}
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				open_activity.setClass(getApplicationContext(), MainActivity.class);
				open_activity.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT); open_activity.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
				startActivity(open_activity);
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog custom = new AlertDialog.Builder(MainActivity.this).create(); LayoutInflater inflater = getLayoutInflater(); View convertView = (View) inflater.inflate(R.layout.menu, null); custom.setView(convertView); custom.requestWindowFeature(Window.FEATURE_NO_TITLE);
				Window window = custom.getWindow(); WindowManager.LayoutParams wlp = window.getAttributes(); wlp.gravity = Gravity.BOTTOM; window.setAttributes(wlp); window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); 
				wlp.y = 150; custom.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT)); LinearLayout s_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg); LinearLayout b_con = (LinearLayout) convertView.findViewById(R.id.linear_content);  ImageView b_img = (ImageView) convertView.findViewById(R.id.logo); android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable(); ab.setColor(Color.parseColor("#424242")); ab.setCornerRadius(25); b_con.setBackground(ab);  b_img.setElevation(5);
				
				
				 
				Button view_src = (Button) convertView.findViewById(R.id.m_item1);
				view_src.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						open_activity.setClass(getApplicationContext(), ViewSourceActivity.class);
						open_activity.putExtra("url", webview1.getUrl());
						open_activity.setAction(Intent.ACTION_VIEW);
						startActivity(open_activity);
						 }});
				Button lock = (Button) convertView.findViewById(R.id.m_item3);
				lock.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						open_activity.setClass(getApplicationContext(), LockActivity.class);
						open_activity.setAction(Intent.ACTION_VIEW);
						startActivity(open_activity);
						 }});
				Button bookmark = (Button) convertView.findViewById(R.id.m_item4);
				bookmark.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						open_activity.setClass(getApplicationContext(), NotreadyActivity.class);
						open_activity.setAction(Intent.ACTION_VIEW);
						startActivity(open_activity);
						 }});
				Button setting = (Button) convertView.findViewById(R.id.m_item5);
				setting.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						AlertDialog setting = new AlertDialog.Builder(MainActivity.this).create(); LayoutInflater inflater = getLayoutInflater(); View convertView = (View) inflater.inflate(R.layout.setting, null); setting.setView(convertView); setting.requestWindowFeature(Window.FEATURE_NO_TITLE);
						Window w = setting.getWindow(); WindowManager.LayoutParams lp = w.getAttributes(); lp.gravity = Gravity.BOTTOM; w.setAttributes(lp); w.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); 
						lp.y = 150; setting.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT)); LinearLayout s_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg); LinearLayout b_con = (LinearLayout) convertView.findViewById(R.id.linear_content);   android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable(); ab.setColor(Color.parseColor("#ffffff")); ab.setCornerRadius(25); b_con.setBackground(ab); 
						
						custom.dismiss();
						 setting.show();
						Button clear_cache = (Button) convertView.findViewById(R.id.clear_cache);
						clear_cache.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								webview1.clearCache(true);
								SketchwareUtil.showMessage(getApplicationContext(), "Cache Cleared !");
								 }});
						Button clear_cookie = (Button) convertView.findViewById(R.id.clear_cookies);
						clear_cookie.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {  CookieManager.getInstance().removeAllCookies(null); CookieManager.getInstance().flush(); } else {  
									 CookieManager cookieManager=CookieManager.getInstance(); cookieManager.removeAllCookie(); cookieManager.removeSessionCookie();
									 }
								SketchwareUtil.showMessage(getApplicationContext(), "Cookie Cleared !");
								 }});
						 }});
				Button info = (Button) convertView.findViewById(R.id.m_item7);
				info.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						 
						open_activity.setClass(getApplicationContext(), InfoActivity.class);
						open_activity.setAction(Intent.ACTION_VIEW);
						startActivity(open_activity);
						 }});
				Button history = (Button) convertView.findViewById(R.id.m_item9);
				history.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						open_activity.setClass(getApplicationContext(), HistoryActivity.class);
						open_activity.setAction(Intent.ACTION_VIEW);
						startActivity(open_activity);
						 }});
				Button download = (Button) convertView.findViewById(R.id.m_item6);
				download.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						Intent notiIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS); notiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(notiIntent); 
						 }});
				Button share = (Button) convertView.findViewById(R.id.m_item2);
				share.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); sharingIntent.setType("text/plain"); sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here"); sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, edittext1.getText().toString()); startActivity(Intent.createChooser(sharingIntent, "Share URL via")); 
						 }});
				Button desktop_v = (Button) convertView.findViewById(R.id.m_item8);
				desktop_v.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						String ua = webview1.getSettings().getUserAgentString();
						 String androidOSString = webview1.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1); 
						String newUserAgent = webview1.getSettings().getUserAgentString().replace(androidOSString, "(X11; Linux x86_64)"); 
						
						webview1.getSettings().setUserAgentString(newUserAgent);
						webview1.reload();
						 }});
				custom.show();
			}
		});
	}
	
	private void initializeLogic() {
		progressbar1.setVisibility(View.GONE);
		if (setting.getString("pass", "").equals("")) {
			linear1.setVisibility(View.VISIBLE);
			linear25.setVisibility(View.GONE);
			imageview2.setImageResource(R.drawable.ic_lock_outline_black);
		}
		else {
			linear25.setVisibility(View.VISIBLE);
			linear1.setVisibility(View.GONE);
			imageview2.setImageResource(R.drawable.ic_https_black);
		}
		if (!log.getString("lasturl", "").equals("")) {
			webview1.loadUrl(log.getString("lasturl", ""));
			if (log.getString("lasturl", "").equals("omni://home")) {
				linear10.setVisibility(View.VISIBLE);
				webview1.setVisibility(View.GONE);
			}
			else {
				webview1.setVisibility(View.VISIBLE);
				linear10.setVisibility(View.GONE);
			}
		}
		else {
			linear10.setVisibility(View.VISIBLE);
			webview1.setVisibility(View.GONE);
			webview1.loadUrl("omni://home");
		}
		android.graphics.drawable.GradientDrawable sd = new android.graphics.drawable.GradientDrawable();
		
		sd.setColor(Color.parseColor("#424242"));
		sd.setCornerRadii(new float[]{0, 0, 90, 90, 90, 90, 0, 0});
		
		left_box.setBackground(sd);
		android.graphics.drawable.GradientDrawable bd = new android.graphics.drawable.GradientDrawable();
		
		bd.setColor(Color.parseColor("#ffffff"));
		bd.setCornerRadii(new float[]{50, 50, 50, 50, 0, 0, 0, 0});
		
		linear4.setBackground(bd);
		linear30.setVisibility(View.GONE);
		webview1.setWebChromeClient(new CustomWebClient());
		_download_block();
		edittext1.setOnEditorActionListener(new TextView.OnEditorActionListener() {@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId== 2){
					if (edittext1.getText().toString().contains("https://") || (edittext1.getText().toString().contains("http://") || (edittext1.getText().toString().contains("www.") || (edittext1.getText().toString().contains(".") || (edittext1.getText().toString().contains(".com") || (edittext1.getText().toString().contains(".in") || (edittext1.getText().toString().contains(".org") || (edittext1.getText().toString().contains(".na") || (edittext1.getText().toString().contains("m.") || edittext1.getText().toString().contains(".xyz")))))))))) {
						if (edittext1.getText().toString().contains("www.") || edittext1.getText().toString().contains("m.")) {
							webview1.loadUrl("https://".concat(edittext1.getText().toString()));
						}
						if (edittext1.getText().toString().contains(".") || (edittext1.getText().toString().contains(".com") || (edittext1.getText().toString().contains(".in") || (edittext1.getText().toString().contains(".org") || (edittext1.getText().toString().contains(".na") || (edittext1.getText().toString().contains("m.") || edittext1.getText().toString().contains(".xyz"))))))) {
							webview1.loadUrl("https://".concat(edittext1.getText().toString()));
						}
						if (edittext1.getText().toString().contains("https://") || edittext1.getText().toString().contains("https://")) {
							webview1.loadUrl(edittext1.getText().toString());
						}
					}
					else {
						webview1.loadUrl("https://www.google.com/search?q=".concat(edittext1.getText().toString()));
					}
				} return false;} });
		edittext2.setOnEditorActionListener(new TextView.OnEditorActionListener() {@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId== 2){
					webview1.loadUrl("https://www.google.com/search?q=".concat(edittext2.getText().toString()));
				} return false;} });
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (webview1.canGoBack()) {
			webview1.goBack();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		n = 0;
		while(true) {
			if (log.getString(String.valueOf((long)(n)), "").equals("")) {
				break;
			}
			else {
				
				n++;
			}
		}
		if (log.getString("n", "").equals("")) {
			
		}
		else {
			if (log.getString("n", "").equals(webview1.getUrl())) {
				
			}
			else {
				webview1.loadUrl(log.getString(log.getString("n", ""), ""));
				log.edit().putString("n", "").commit();
			}
		}
	}
	public void _round (final View _view, final String _color, final String _sttoke_c, final double _radius) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadii(new float[]{40, 40, 40, 40, 0, 0, 0, 0});
		gd.setStroke(2, Color.parseColor(_sttoke_c));
		
		_view.setBackground(gd);
	}
	
	
	public void _border_radius (final View _view, final String _color, final String _stroke_c, final double _tl_x, final double _tl_y, final double _tr_x, final double _tr_y, final double _bl_x, final double _bl_y, final double _br_x, final double _br_y) {
		
	}
	
	
	public void _download_block () {
		if (FileUtil.isExistFile("/Download")) {
			
		}
		else {
			FileUtil.makeDir("/Download");
		}
		webview1.setDownloadListener(new DownloadListener() {
			
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
				
				String cookies = CookieManager.getInstance().getCookie(url);
				
				request.addRequestHeader("cookie", cookies);
				
				request.addRequestHeader("User-Agent", userAgent);
				
				request.setDescription("Downloading file...");
				
				request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				
				request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				
				java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/Download");
				
				if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}}
				 request.setDestinationInExternalPublicDir("/Download", URLUtil.guessFileName(url, contentDisposition, mimetype)); 
				
				DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				
				manager.enqueue(request);
				
				showMessage("Downloading File....");
				
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					
					public void onReceive(Context ctxt, Intent intent) {
						
						showMessage("Download Complete!");
						
						unregisterReceiver(this);
						
					}};
				
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
				
			}
			
		});
		
		
		
	}
	
	
	public void _webclient () {
	}
	
	public class CustomWebClient extends WebChromeClient {
		private View mCustomView;
		private WebChromeClient.CustomViewCallback mCustomViewCallback;
		protected FrameLayout frame;
		
		// Initially mOriginalOrientation is set to Landscape
		private int mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
		private int mOriginalSystemUiVisibility;
		
		// Constructor for CustomWebClient
		public CustomWebClient() {}
		
		public Bitmap getDefaultVideoPoster() {
			if (MainActivity.this == null) {
				return null; }
			return BitmapFactory.decodeResource(MainActivity.this.getApplicationContext().getResources(), 2130837573); }
		
		public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback viewCallback) {
			if (this.mCustomView != null) {
				onHideCustomView();
				return; }
			this.mCustomView = paramView;
			this.mOriginalSystemUiVisibility = MainActivity.this.getWindow().getDecorView().getSystemUiVisibility();
			// When CustomView is shown screen orientation changes to mOriginalOrientation (Landscape).
			MainActivity.this.setRequestedOrientation(this.mOriginalOrientation);
			// After that mOriginalOrientation is set to portrait.
			this.mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
			this.mCustomViewCallback = viewCallback; ((FrameLayout)MainActivity.this.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1)); MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(3846);
		}
		
		public void onHideCustomView() {
			((FrameLayout)MainActivity.this.getWindow().getDecorView()).removeView(this.mCustomView);
			this.mCustomView = null;
			MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
			// When CustomView is hidden, screen orientation is set to mOriginalOrientation (portrait).
			MainActivity.this.setRequestedOrientation(this.mOriginalOrientation);
			// After that mOriginalOrientation is set to landscape.
			this.mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE; this.mCustomViewCallback.onCustomViewHidden();
			this.mCustomViewCallback = null;
		}
	}
	
	{
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
