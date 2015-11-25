package com.duan.projectmonngon;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.duan.projectmonngon.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivityData extends Activity implements TextWatcher{

	
	ArrayList<Configdata> arrayList;
	AdapterView adapterView;
	ArrayList<String> arrNameSearch; 
	Intent intent ;
	AutoCompleteTextView _autoCompleteTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		arrayList = null;
		arrNameSearch = null;
		arrayList = new ArrayList<Configdata>();
		arrNameSearch = new ArrayList<String>();
		///Kiem tra xem luc nay ban chon button nao de lay data tuong ung
		Intent getIntent = getIntent();
		Bundle _data = getIntent.getBundleExtra("button_click");
		int _buttonclick = _data.getInt("ID_button");
		switch (_buttonclick) {
		case 1:
			new getdata().execute("http://monngonapp.esy.es/data_mon_nuong.json");
			break;
		case 2:
			new getdata().execute("http://monngonapp.esy.es/data_mon_chien_xao.json");
			break;
		case 3:
			new getdata().execute("http://monngonapp.esy.es/data_mon_goi.json");
			break;
		case 4:
			new getdata().execute("http://monngonapp.esy.es/data_mon_kho.json");
			break;
		}
		//
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		
		adapterView = new AdapterView(getApplicationContext(), R.layout.rowview_search, arrayList);
		listView.setAdapter(adapterView);
		
		intent = new Intent(MainActivityData.this, ShowInfoActivity.class);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(android.widget.AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("name", arrayList.get(arg2).getName());
				bundle.putString("img", arrayList.get(arg2).getImg());
				bundle.putString("chebien", arrayList.get(arg2).getChebien());
				intent.putExtra("data_monan", bundle);
				startActivity(intent);
			}
		});

		_autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_search);
		_autoCompleteTextView.setInputType(0);
		_autoCompleteTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_autoCompleteTextView.setInputType(1);
			}
		});
		_autoCompleteTextView.addTextChangedListener(this);
		_autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrNameSearch));
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class getdata extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog ;
		//set dialog cho menu load
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivityData.this);
			dialog.setMessage("Chời xíu....");
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... urls) {
			// TODO Auto-generated method stub
			try {
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse response =  httpClient.execute(httppost);
			
				int status = response.getStatusLine().getStatusCode();
				Log.e("status", Integer.toString(status));
				if(status == 200){
					
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);
					JSONObject jsonObject = new JSONObject(data);
					JSONArray jsonArray = jsonObject.getJSONArray("monngon");
					//Log.e("jsonArray", jsonArray.toString());
					for(int i = 0; i < jsonArray.length(); i ++){
						
						JSONObject object = jsonArray.getJSONObject(i);
						Configdata configdata = new Configdata();
						//Giáº£i mÃ£ Base64
						//---------------
						byte[] decode_name = Base64.decode(object.getString("name").getBytes(), 0);
						byte[] decode_chebien = Base64.decode(object.getString("huongdan").getBytes(), 0);
						//---------------
						
						configdata.setName(new String(decode_name));
						configdata.setImg(object.getString("image"));
						configdata.setChebien(new String(decode_chebien));
						arrayList.add(configdata);
						arrNameSearch.add(new String(decode_name));
					}
					return true;
				}
				
			} catch (IOException e) {
				// TODO: handle exception
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return false;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			adapterView.notifyDataSetChanged();
		}
		
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		for(int i = 0; i < arrayList.size(); i++){
			
			if(arrayList.get(i).getName().equals(_autoCompleteTextView.getText().toString()) == true){
				
				Bundle bundle = new Bundle();
				bundle.putString("name", arrayList.get(i).getName());
				bundle.putString("img", arrayList.get(i).getImg());
				bundle.putString("chebien", arrayList.get(i).getChebien());
				intent.putExtra("data_monan", bundle);
				startActivity(intent);
				return;
			}
			
			Log.e("Check: ", Boolean.toString(arrayList.get(i).getName().equals(_autoCompleteTextView.getText())));
		}
	}
}
