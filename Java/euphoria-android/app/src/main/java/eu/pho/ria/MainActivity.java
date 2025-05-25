package eu.pho.ria;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	public final int REQ_CD_FUJ = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String name_str = "";
	private String phone_str = "";
	private double id = 0;
	private String id_str = "";
	private String propic_name = "";
	private String propic_furl = "";
	private String url = "";
	private String package_name = "";
	private HashMap<String, Object> vers = new HashMap<>();
	private String latest_vers = "";
	private String my_vers = "";
	private String app_down = "";
	private String latest_down = "";
	private String filedir = "";
	private HashMap<String, Object> users_info = new HashMap<>();
	private double save = 0;
	private double i = 0;
	private double n = 0;
	
	private ArrayList<String> filepick_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> vers_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> users_map = new ArrayList<>();
	
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private LinearLayout linear21;
	private LinearLayout linear22;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private LinearLayout linear27;
	private TextView textview7;
	private ImageView imageview9;
	private TextView textview3;
	private EditText edittext4;
	private TextView textview4;
	private EditText edittext5;
	private Button button1;
	private TextView textview5;
	private EditText edittext6;
	private TextView textview6;
	
	private AlertDialog.Builder connection;
	private SharedPreferences name;
	private Intent chat = new Intent();
	private DatabaseReference userdata = _firebase.getReference("users");
	private ChildEventListener _userdata_child_listener;
	private ObjectAnimator popup = new ObjectAnimator();
	private StorageReference vol = _firebase_storage.getReference("bi");
	private OnCompleteListener<Uri> _vol_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _vol_download_success_listener;
	private OnSuccessListener _vol_delete_success_listener;
	private OnProgressListener _vol_upload_progress_listener;
	private OnProgressListener _vol_download_progress_listener;
	private OnFailureListener _vol_failure_listener;
	
	private Intent fuj = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference update = _firebase.getReference("app_update");
	private ChildEventListener _update_child_listener;
	private AlertDialog.Builder update_available;
	private StorageReference app_download = _firebase_storage.getReference("app");
	private OnCompleteListener<Uri> _app_download_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _app_download_download_success_listener;
	private OnSuccessListener _app_download_delete_success_listener;
	private OnProgressListener _app_download_upload_progress_listener;
	private OnProgressListener _app_download_download_progress_listener;
	private OnFailureListener _app_download_failure_listener;
	
	private Intent open_file = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
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
		linear16 = findViewById(R.id.linear16);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		linear21 = findViewById(R.id.linear21);
		linear22 = findViewById(R.id.linear22);
		linear23 = findViewById(R.id.linear23);
		linear24 = findViewById(R.id.linear24);
		linear27 = findViewById(R.id.linear27);
		textview7 = findViewById(R.id.textview7);
		imageview9 = findViewById(R.id.imageview9);
		textview3 = findViewById(R.id.textview3);
		edittext4 = findViewById(R.id.edittext4);
		textview4 = findViewById(R.id.textview4);
		edittext5 = findViewById(R.id.edittext5);
		button1 = findViewById(R.id.button1);
		textview5 = findViewById(R.id.textview5);
		edittext6 = findViewById(R.id.edittext6);
		textview6 = findViewById(R.id.textview6);
		connection = new AlertDialog.Builder(this);
		name = getSharedPreferences("name", Activity.MODE_PRIVATE);
		fuj.setType("*/*");
		fuj.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		update_available = new AlertDialog.Builder(this);
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fuj, REQ_CD_FUJ);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!url.equals("")) {
					vol.child(propic_name).putFile(Uri.fromFile(new File(url))).addOnFailureListener(_vol_failure_listener).addOnProgressListener(_vol_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return vol.child(propic_name).getDownloadUrl();
						}}).addOnCompleteListener(_vol_upload_success_listener);
				}
				else {
					
				}
				name_str = edittext4.getText().toString();
				phone_str = edittext5.getText().toString();
				name.edit().putString("name", edittext4.getText().toString()).commit();
				name.edit().putString("phone", edittext5.getText().toString()).commit();
				name.edit().putString("id", edittext6.getText().toString()).commit();
				for(int _repeat129 = 0; _repeat129 < (int)(n); _repeat129++) {
					if (users_map.get((int)i).get("usr_id").toString().equals(edittext6.getText().toString())) {
						i++;
					}
					else {
						users_info = new HashMap<>();
						users_info.put("usr_name", edittext4.getText().toString());
						users_info.put("usr_phone", edittext5.getText().toString());
						users_info.put("usr_id", edittext6.getText().toString());
						userdata.push().updateChildren(users_info);
						break;
					}
				}
//				if (edittext4.getText().toString().equals("") || (edittext5.getText().toString().equals("") || (name.getString("pp", "").equals("") && propic_furl.equals("")))) {
//					SketchwareUtil.showMessage(getApplicationContext(), "Pick An Image And Fill All Textbox !");
//				}
//				else {
					chat.setClass(getApplicationContext(), ChatActivity.class);
					chat.setAction(Intent.ACTION_VIEW);
					chat.putExtra("name", edittext4.getText().toString());
					chat.putExtra("phone", edittext5.getText().toString());
					chat.putExtra("id", name.getString("id", ""));
					chat.putExtra("int_pp", name.getString("pp", ""));
					chat.putExtra("propic_furl", name.getString("propic_furl", ""));
					startActivity(chat);
					finish();
//				}
			}
		});
		
		_userdata_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				userdata.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						users_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								users_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						n = users_map.size();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		userdata.addChildEventListener(_userdata_child_listener);
		
		_vol_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_vol_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_vol_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				propic_furl = _downloadUrl;
				name.edit().putString("propic_furl", propic_furl).commit();
			}
		};
		
		_vol_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_vol_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_vol_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_update_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				update.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						vers_listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								vers_listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						latest_vers = vers_listmap.get((int)0).get("v").toString();
						latest_down = vers_listmap.get((int)0).get("download").toString();
						if (Double.parseDouble(latest_vers) > Double.parseDouble(my_vers)) {
							update_available.setTitle("Update Available");
							update_available.setMessage("New App Available !");
							update_available.setPositiveButton("Update", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									_firebase_storage.getReferenceFromUrl(latest_down).getFile(new File(FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat("euphoria_mane.apk")))).addOnSuccessListener(_app_download_download_success_listener).addOnFailureListener(_app_download_failure_listener).addOnProgressListener(_app_download_download_progress_listener);
								}
							});
							update_available.setNegativeButton("Later, Fuck You", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									SketchwareUtil.showMessage(getApplicationContext(), "Lazy Fuck !");
								}
							});
							update_available.create().show();
						}
						else {
							if (Double.parseDouble(my_vers) > Double.parseDouble(latest_vers)) {
								update.child("app").child("v").setValue(my_vers);
							}
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		update.addChildEventListener(_update_child_listener);
		
		_app_download_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_app_download_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_app_download_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_app_download_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				filedir = FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat("euphoria_mane.apk"));
				SketchwareUtil.showMessage(getApplicationContext(), "Install This Shit And Say Goodbye To Old Pal");
				File file = new File(filedir);
				Uri path = Uri.fromFile(file);
				Intent i = new Intent();
				i.setData(Uri.fromFile(file));
				startActivity(i);
			}
		};
		
		_app_download_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_app_download_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
	}
	
	private void initializeLogic() {
		i = 0;
		package_name = "eu.pho.ria";
		try{
			android.content.pm.PackageInfo p = getPackageManager().getPackageInfo(package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
			my_vers = p.versionName;}
		catch(Exception e)
		{showMessage(e.toString());}
		DatabaseReference rootRef = _firebase.getReference();
		rootRef.child("version").addListenerForSingleValueEvent(new ValueEventListener()
		{@Override
			public void onDataChange(DataSnapshot snapshot)
			{if(snapshot.exists())
				{}
				else{
					vers = new HashMap<>();
					vers.put("v", my_vers);
					vers.put("download", app_down);
				}};
			@Override
			public void onCancelled(DatabaseError _error)
			{}});
		id = SketchwareUtil.getRandom((int)(1000), (int)(5000));
		id_str = String.valueOf((long)(id));
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
		edittext4.setText(name.getString("name", ""));
		edittext5.setText(name.getString("phone", ""));
		if (name.getString("id", "").equals("")) {
			edittext6.setText(id_str);
			textview7.setVisibility(View.VISIBLE);
			imageview9.setImageResource(R.drawable.ic_assignment_ind_black);
		}
		else {
			edittext6.setText(name.getString("id", ""));
			textview7.setVisibility(View.GONE);
			edittext4.setEnabled(false);
			edittext5.setEnabled(false);
			edittext6.setEnabled(false);
		}
		android.graphics.drawable.GradientDrawable b = new android.graphics.drawable.GradientDrawable();
		b.setColor(Color.parseColor("#BBDEFB"));
		b.setCornerRadius(20);
		button1.setBackground(b);
		if (name.getString("pp", "").equals("")) {
			imageview9.setImageResource(R.drawable.ic_assignment_ind_black);
		}
		else {
			imageview9.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(name.getString("pp", ""), 1024, 1024));
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FUJ:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				url = _filePath.get((int)(0));
				propic_name = Uri.parse(url).getLastPathSegment();
				imageview9.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(url, 1024, 1024));
				name.edit().putString("pp", url).commit();
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
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
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
