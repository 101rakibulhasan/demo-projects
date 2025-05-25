package eu.pho.ria;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.File;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ChatActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String phone_str = "";
	private HashMap<String, Object> chatdatamap = new HashMap<>();
	private String username = "";
	private String id_str = "";
	private String propic_furl = "";
	private String propic = "";
	private double off_on = 0;
	private String active_status = "";
	private String act_status_db_sender = "";
	private String act_status_db_rec = "";
	private HashMap<String, Object> usr_status = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> chatmap = new ArrayList<>();
	private ArrayList<String> option_items = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> usr_status_listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView textview1;
	private ImageView imageview6;
	private Button button1;
	private LinearLayout linear22;
	private LinearLayout linear15;
	private ListView listview1;
	private LinearLayout linear23;
	private ListView listview3;
	private ListView listview2;
	private TextView textview5;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private Button button2;
	private Button button5;
	private Button button4;
	private Button button6;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ImageView imageview4;
	private EditText edittext2;
	private ImageView imageview7;
	private ImageView imageview5;
	
	private DatabaseReference chat = _firebase.getReference("chatdata");
	private ChildEventListener _chat_child_listener;
	private TimerTask sender_time;
	private TimerTask off;
	private Calendar time = Calendar.getInstance();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private MediaPlayer music;
	private AlertDialog.Builder profile_dialog;
	private SharedPreferences backup_json;
	private Intent profile = new Intent();
	private StorageReference file_data = _firebase_storage.getReference("resources");
	private OnCompleteListener<Uri> _file_data_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _file_data_download_success_listener;
	private OnSuccessListener _file_data_delete_success_listener;
	private OnProgressListener _file_data_upload_progress_listener;
	private OnProgressListener _file_data_download_progress_listener;
	private OnFailureListener _file_data_failure_listener;
	
	private Intent file_upload = new Intent();
	private AlertDialog.Builder delete_data;
	private ObjectAnimator rotate_anticlockwise = new ObjectAnimator();
	private SharedPreferences data;
	private Intent image = new Intent();
	private Intent video = new Intent();
	private Intent other = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chat);
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
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		textview1 = findViewById(R.id.textview1);
		imageview6 = findViewById(R.id.imageview6);
		button1 = findViewById(R.id.button1);
		linear22 = findViewById(R.id.linear22);
		linear15 = findViewById(R.id.linear15);
		listview1 = findViewById(R.id.listview1);
		linear23 = findViewById(R.id.linear23);
		listview3 = findViewById(R.id.listview3);
		listview2 = findViewById(R.id.listview2);
		textview5 = findViewById(R.id.textview5);
		linear16 = findViewById(R.id.linear16);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		button2 = findViewById(R.id.button2);
		button5 = findViewById(R.id.button5);
		button4 = findViewById(R.id.button4);
		button6 = findViewById(R.id.button6);
		linear5 = findViewById(R.id.linear5);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		imageview4 = findViewById(R.id.imageview4);
		edittext2 = findViewById(R.id.edittext2);
		imageview7 = findViewById(R.id.imageview7);
		imageview5 = findViewById(R.id.imageview5);
		net = new RequestNetwork(this);
		profile_dialog = new AlertDialog.Builder(this);
		backup_json = getSharedPreferences("backup", Activity.MODE_PRIVATE);
		delete_data = new AlertDialog.Builder(this);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!textview1.getText().toString().equals("Options")) {
					textview1.setText("Options");
					linear15.setVisibility(View.VISIBLE);
					linear22.setVisibility(View.GONE);
					rotate_anticlockwise.setTarget(button1);
					rotate_anticlockwise.setPropertyName("rotation");
					rotate_anticlockwise.setFloatValues((float)(0));
					rotate_anticlockwise.setFloatValues((float)(0), (float)(180));
					rotate_anticlockwise.setDuration((int)(500));
					rotate_anticlockwise.start();
				}
				else {
					textview1.setText(username);
					linear15.setVisibility(View.GONE);
					linear22.setVisibility(View.VISIBLE);
					rotate_anticlockwise.setTarget(button1);
					rotate_anticlockwise.setPropertyName("rotation");
					rotate_anticlockwise.setFloatValues((float)(0));
					rotate_anticlockwise.setFloatValues((float)(180), (float)(0));
					rotate_anticlockwise.setDuration((int)(500));
					rotate_anticlockwise.start();
				}
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (chatmap.get((int)_position).get("msg").toString().contains("http")) {
					profile.setAction(Intent.ACTION_VIEW);
					profile.setData(Uri.parse(chatmap.get((int)_position).get("msg").toString()));
					startActivity(profile);
				}
				else {
					profile.setClass(getApplicationContext(), ProfileActivity.class);
					profile.setAction(Intent.ACTION_VIEW);
					profile.putExtra("username", chatmap.get((int)_position).get("name").toString());
					profile.putExtra("phone", chatmap.get((int)_position).get("phone").toString());
					startActivity(profile);
				}
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					image.setClass(getApplicationContext(), ImageActivity.class);
					image.setAction(Intent.ACTION_VIEW);
					startActivity(image);
				}
				if (_position == 1) {
					video.setClass(getApplicationContext(), VideoActivity.class);
					video.setAction(Intent.ACTION_VIEW);
					startActivity(video);
				}
				if (_position == 2) {
					other.setClass(getApplicationContext(), OtherActivity.class);
					other.setAction(Intent.ACTION_VIEW);
					startActivity(other);
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (music.isPlaying()) {
					music.pause();
					button5.setBackgroundResource(R.drawable.ic_pause_circle_fill_white);
				}
				else {
					music.start();
					music.setLooping(music.isLooping());
					button5.setBackgroundResource(R.drawable.ic_play_circle_fill_white);
					SketchwareUtil.showMessage(getApplicationContext(), "Enjoy");
				}
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				file_upload.setClass(getApplicationContext(), FileUploadActivity.class);
				file_upload.setAction(Intent.ACTION_VIEW);
				startActivity(file_upload);
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (off_on == 0) {
					linear23.setVisibility(View.VISIBLE);
					off_on = 1;
				}
				else {
					linear23.setVisibility(View.GONE);
					off_on = 0;
				}
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext2.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Write Something! ");
				}
				else {
					time = Calendar.getInstance();
					chatdatamap = new HashMap<>();
					chatdatamap.put("phone", phone_str);
					chatdatamap.put("name", username);
					chatdatamap.put("msg", edittext2.getText().toString());
					chatdatamap.put("id", id_str);
					chatdatamap.put("time", new SimpleDateFormat("dd/MM hh:mm a").format(time.getTime()));
					chatdatamap.put("status", "*".concat(username.concat("Just Joined In".concat("*"))));
					chatdatamap.put("pp", propic_furl);
					chat.push().updateChildren(chatdatamap);
					net.startRequestNetwork(RequestNetworkController.GET, "http://www.google.com", "lol", _net_request_listener);
					ChatActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
					edittext2.setText("");
				}
				chatdatamap.put("status", active_status);
				
			}
		});
		
		_chat_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				chat.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						chatmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								chatmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(chatmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
		chat.addChildEventListener(_chat_child_listener);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_file_data_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_file_data_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_file_data_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_file_data_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_file_data_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_file_data_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
	}
	
	private void initializeLogic() {
		off_on = 0;
		linear15.setVisibility(View.GONE);
		listview1.setVisibility(View.VISIBLE);
		linear23.setVisibility(View.GONE);
		textview1.setText(getIntent().getStringExtra("name"));
		phone_str = getIntent().getStringExtra("phone");
		username = getIntent().getStringExtra("name");
		id_str = getIntent().getStringExtra("id");
		propic_furl = getIntent().getStringExtra("propic_furl");
		data.edit().putString("pp", getIntent().getStringExtra("int_pp")).commit();
		imageview6.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(data.getString("pp", ""), 1024, 1024));
		android.graphics.drawable.GradientDrawable c = new android.graphics.drawable.GradientDrawable();
		c.setColor(Color.parseColor("#FFFFFF"));
		c.setCornerRadius(60);
		linear8.setBackground(c);
		net.startRequestNetwork(RequestNetworkController.GET, "http://www.google.com", "lol", _net_request_listener);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
		music = MediaPlayer.create(getApplicationContext(), R.raw.maiya);
		listview1.setStackFromBottom(true);
		if (!backup_json.getString("backup", "").equals("")) {
			chatmap = new Gson().fromJson(backup_json.getString("backup", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(chatmap));
		}
		SketchwareUtil.showMessage(getApplicationContext(), "REFRESHING DATA _");
		option_items.add("Image");
		option_items.add("Video");
		option_items.add("Others");
		listview2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, option_items));
		active_status = "*".concat(username.concat(" Just Joined".concat("*")));
		chatdatamap.put("phone", phone_str);
		chatdatamap.put("name", username);
		chatdatamap.put("msg", edittext2.getText().toString());
		chatdatamap.put("id", id_str);
		chatdatamap.put("time", new SimpleDateFormat("dd/MM hh:mm a").format(time.getTime()));
		chatdatamap.put("status", active_status);
		chatdatamap.put("pp", propic_furl);
		chat.push().updateChildren(chatdatamap);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		backup_json.edit().putString("backup", new Gson().toJson(chatmap)).commit();
		music.reset();
		active_status = "*".concat(username.concat(" Just Left".concat("*")));
		chatdatamap.put("phone", phone_str);
		chatdatamap.put("name", username);
		chatdatamap.put("msg", edittext2.getText().toString());
		chatdatamap.put("id", id_str);
		chatdatamap.put("time", new SimpleDateFormat("dd/MM hh:mm a").format(time.getTime()));
		chatdatamap.put("status", active_status);
		chat.push().updateChildren(chatdatamap);
	}
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.chatbox, null);
			}
			
			final LinearLayout sender = _view.findViewById(R.id.sender);
			final LinearLayout receiver = _view.findViewById(R.id.receiver);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout senderbox = _view.findViewById(R.id.senderbox);
			final TextView sender_time = _view.findViewById(R.id.sender_time);
			final TextView sender_msg = _view.findViewById(R.id.sender_msg);
			final ImageView sender_profile = _view.findViewById(R.id.sender_profile);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView receiver_profile = _view.findViewById(R.id.receiver_profile);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final LinearLayout receiverbox = _view.findViewById(R.id.receiverbox);
			final TextView receiver_time = _view.findViewById(R.id.receiver_time);
			final TextView receiver_msg = _view.findViewById(R.id.receiver_msg);
			
			sender_time.setVisibility(View.VISIBLE);
			receiver_time.setVisibility(View.VISIBLE);
			
			sender_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/accuratist.ttf"), 0);
			receiver_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/accuratist.ttf"), 0);
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/homespun.ttf"), 0);
			if (username.equals(chatmap.get((int)_position).get("name").toString()) && id_str.equals(chatmap.get((int)_position).get("id").toString())) {
				sender.setVisibility(View.VISIBLE);
				receiver.setVisibility(View.GONE);
				sender_time.setText(chatmap.get((int)_position).get("time").toString());
				textview1.setText(chatmap.get((int)_position).get("name").toString());
				sender_msg.setText(chatmap.get((int)_position).get("msg").toString());
				if (chatmap.get((int)_position).get("msg").toString().equals("")) {
					sender_msg.setText(chatmap.get((int)_position).get("status").toString());
				}
				act_status_db_sender = chatmap.get((int)_position).get("status").toString();
				Glide.with(getApplicationContext()).load(Uri.parse(chatmap.get((int)_position).get("pp").toString())).into(sender_profile);
			}
			else {
				receiver.setVisibility(View.VISIBLE);
				receiver_time.setText(chatmap.get((int)_position).get("time").toString());
				textview2.setText(chatmap.get((int)_position).get("name").toString());
				sender.setVisibility(View.GONE);
				receiver_msg.setText(chatmap.get((int)_position).get("msg").toString());
				if (chatmap.get((int)_position).get("msg").toString().equals("")) {
					receiver_msg.setText(chatmap.get((int)_position).get("status").toString());
				}
				act_status_db_rec = chatmap.get((int)_position).get("status").toString();
				Glide.with(getApplicationContext()).load(Uri.parse(chatmap.get((int)_position).get("pp").toString())).into(receiver_profile);
			}
			android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
			a.setColor(Color.parseColor("#FFFFFF"));
			a.setCornerRadius(40);
			senderbox.setBackground(a);
			android.graphics.drawable.GradientDrawable b = new android.graphics.drawable.GradientDrawable();
			b.setColor(Color.parseColor("#747474"));
			b.setCornerRadius(40);
			receiverbox.setBackground(b);
			
			return _view;
		}
	}
	
	public class Listview3Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.sticker, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			return _view;
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