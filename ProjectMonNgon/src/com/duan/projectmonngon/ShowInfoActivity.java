package com.duan.projectmonngon;

import java.io.InputStream;

import com.duan.projectmonngon.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowInfoActivity extends Activity{

	TextView _text_name_info, _text_cachlam_info;
	ImageView _ImageView_info;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_info_search);
		
		/// lay du lieu
		Intent getdata = getIntent();
		Bundle data = getdata.getBundleExtra("data_monan");
		_text_name_info = (TextView) findViewById(R.id.text_name_info);
		_text_cachlam_info = (TextView) findViewById(R.id.text_cachlam_info);
		_ImageView_info = (ImageView) findViewById(R.id.imageView_info);
		
		
		new DownloadImageTask(_ImageView_info).execute(data.getString("img"));
		_text_name_info.setText(data.getString("name"));
		_text_cachlam_info.setText(data.getString("chebien"));
		
	}


	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}

}
