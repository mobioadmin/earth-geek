package com.nasa.earthquiz;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button Land;
	Button Ocean,volcano,weather;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Land = (Button)findViewById(R.id.button3);
		Ocean = (Button)findViewById(R.id.button1);
		volcano=(Button)findViewById(R.id.button2);
		weather=(Button)findViewById(R.id.button4);
		
		Land.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(MainActivity.this, ShowQuiz.class);
				i.putExtra("CatagoryType", "Land");
				
				startActivity(i);
				
				
			}
		});
		
		Ocean.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent();
				i.setClass(MainActivity.this, ShowQuiz.class);
				i.putExtra("CatagoryType", "Water");
				startActivity(i);
			}
		});
		
		volcano.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent();
				i.setClass(MainActivity.this, ShowQuiz.class);
				i.putExtra("CatagoryType", "Volcanoes");
				startActivity(i);
			}
		});

		weather.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		Intent i = new Intent();
		i.setClass(MainActivity.this, ShowQuiz.class);
		i.putExtra("CatagoryType", "Weather");
		startActivity(i);
	}
});
		
		
		
		
		
	}

	
}
