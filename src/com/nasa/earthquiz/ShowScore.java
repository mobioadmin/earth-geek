package com.nasa.earthquiz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowScore extends Activity{

	TextView result;
	String score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_layout);
		
		score =getIntent().getStringExtra("score");
		result = (TextView)findViewById(R.id.tv_result);
		result.setText(score);
		
	}
	

}
