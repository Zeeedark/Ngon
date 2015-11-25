package com.duan.projectmonngon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivityMenu extends Activity {

	private Button _btn_mon_nuong, _btn_mon_xao, _btn_mon_goi, _btn_mon_kho;
	private Intent _intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_menu);
		_btn_mon_nuong = (Button) findViewById(R.id._btnMonNuong);
		_btn_mon_xao = (Button) findViewById(R.id._btnMonxao);
		_btn_mon_goi = (Button) findViewById(R.id._btnMonGoi);
		_btn_mon_kho = (Button) findViewById(R.id._btnMonKho);
		_intent = new Intent(getBaseContext(), MainActivityData.class);
		_btn_mon_nuong.setOnClickListener(new OnClickListener() {

			@Override
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putInt("ID_button", 1);
				_intent.putExtra("button_click", bundle);
				startActivity(_intent);
			}
		});

		_btn_mon_xao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putInt("ID_button", 2);
				_intent.putExtra("button_click", bundle);
				startActivity(_intent);
			}
		});

		_btn_mon_goi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putInt("ID_button", 3);
				_intent.putExtra("button_click", bundle);
				startActivity(_intent);
			}
		});

		_btn_mon_kho.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putInt("ID_button", 4);
				_intent.putExtra("button_click", bundle);
				startActivity(_intent);
			}
		});
	}

}
