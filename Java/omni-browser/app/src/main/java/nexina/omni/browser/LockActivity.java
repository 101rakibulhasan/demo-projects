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
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class LockActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout linear_bg;
	private LinearLayout linear_content;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear5;
	private Button enter_pass;
	private EditText enter_pass_input;
	private Button change_lock;
	private Button reset_lock;
	private TextView textview1;
	private EditText new_pass_input;
	private EditText confirm_pass_input;
	private Button create_lock;
	
	private Intent main = new Intent();
	private SharedPreferences setting;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.lock);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_bg = (LinearLayout) findViewById(R.id.linear_bg);
		linear_content = (LinearLayout) findViewById(R.id.linear_content);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		enter_pass = (Button) findViewById(R.id.enter_pass);
		enter_pass_input = (EditText) findViewById(R.id.enter_pass_input);
		change_lock = (Button) findViewById(R.id.change_lock);
		reset_lock = (Button) findViewById(R.id.reset_lock);
		textview1 = (TextView) findViewById(R.id.textview1);
		new_pass_input = (EditText) findViewById(R.id.new_pass_input);
		confirm_pass_input = (EditText) findViewById(R.id.confirm_pass_input);
		create_lock = (Button) findViewById(R.id.create_lock);
		setting = getSharedPreferences("setting", Activity.MODE_PRIVATE);
		
		enter_pass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (setting.getString("pass", "").equals(enter_pass_input.getText().toString())) {
					linear4.setVisibility(View.VISIBLE);
					linear3.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "W R O N G !");
				}
			}
		});
		
		change_lock.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear4.setVisibility(View.GONE);
				linear7.setVisibility(View.VISIBLE);
				textview1.setText("Edit Password");
			}
		});
		
		reset_lock.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				main.setAction(Intent.ACTION_VIEW);
				main.setClass(getApplicationContext(), MainActivity.class);
				setting.edit().putString("pass", "").commit();
				startActivity(main);
			}
		});
		
		create_lock.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!confirm_pass_input.getText().toString().equals("")) {
					if (confirm_pass_input.getText().toString().equals(new_pass_input.getText().toString())) {
						main.setAction(Intent.ACTION_VIEW);
						main.setClass(getApplicationContext(), MainActivity.class);
						setting.edit().putString("pass", confirm_pass_input.getText().toString()).commit();
						startActivity(main);
						finish();
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Passwors Does Not Match");
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Confirm Password Is Not Valid !");
				}
			}
		});
	}
	
	private void initializeLogic() {
		if (setting.getString("pass", "").equals("")) {
			linear3.setVisibility(View.GONE);
			linear4.setVisibility(View.GONE);
			linear7.setVisibility(View.VISIBLE);
		}
		else {
			linear3.setVisibility(View.VISIBLE);
			linear4.setVisibility(View.GONE);
			linear7.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
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
