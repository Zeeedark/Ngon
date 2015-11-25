package com.duan.projectmonngon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.duan.projectmonngon.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterView extends ArrayAdapter<Configdata> {

	ArrayList<Configdata> arrayList;
	LayoutInflater inflater;
	int Resource;
	ViewHoder holder;
		//khai bao  Contructor
	public AdapterView(Context context, int resource, ArrayList<Configdata> object) {
		super(context, resource, object);
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		arrayList = object;
		
	}
	// Lay gia tri roi hien thi
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			holder = new ViewHoder();
			v = inflater.inflate(Resource, null);
			holder._ImageView = (ImageView) v.findViewById(R.id.imageView);
			holder._Name = (TextView) v.findViewById(R.id.textViewName);
			v.setTag(holder);
			
		}else{
			
			holder = (ViewHoder) v.getTag();
		}
		holder._ImageView.setImageResource(R.drawable.loading);
		new DownloadImageTask(holder._ImageView).execute(arrayList.get(position).getImg());
		holder._Name.setText(arrayList.get(position).getName());
		
		return v;
	}
	
	static class ViewHoder{
		public ImageView _ImageView;
		public TextView _Name;
		
	}

		// tai hinh anh ve bo nho tam thoi 
	
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
		//tra ve kieu bipmap
		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}
