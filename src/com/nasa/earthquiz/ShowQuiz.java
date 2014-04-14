package com.nasa.earthquiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nasa.earthquiz.model.Quiz;

public class ShowQuiz extends Activity {

	ProgressDialog pDialog;
	String CatagoryType;
	Button A, B, C, D;
	TextView QuizTitle;
	TextView quiznum;
	ImageView QuizImage;
	JSONArray quizes = null;
	private Handler handler;
	List<Quiz> AllLandQuizes = new LinkedList<Quiz>();
	ArrayList<Integer> alreayAskedQuiz = new ArrayList<Integer>();
	Quiz q;
	String json = null;
	int score = 0;
	int number = 0;
	String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);
		handler = new Handler();
		CatagoryType = getIntent().getStringExtra("CatagoryType");
		initializeProperty();
		path = "jsondata/last.json";

		GetPlaceAsyncTask categoryTask = new GetPlaceAsyncTask();
		categoryTask.execute();

		//q = AllLandQuizes.get(number);

		/*AssetManager assets = getAssets(); // get app's AssetManager
		InputStream stream;
		try {

			stream = assets.open(q.getQuizPicture());

			Bitmap bit = BitmapFactory.decodeStream(stream);
			QuizImage.setImageBitmap(bit);
		} catch (IOException e) {
			Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
		}

		QuizTitle.setText(q.getQuizTitle());
		A.setText(q.getQuizAns());
		B.setText(q.getQuizOp1());
		C.setText(q.getQuizOp2());
		D.setText(q.getQuizOp3());
		quiznum.setText("" + (number + 1) + "/4");*/

		A.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (A.getText().equals(q.getQuizAns())) {
					++score;
					++number;
					A.setBackgroundColor(getResources().getColor(R.color.right));
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);

				} else {
					++number;
					A.setBackgroundColor(getResources().getColor(R.color.wrong));
					Toast.makeText(ShowQuiz.this, "Wrong", Toast.LENGTH_LONG)
							.show();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);

				}

			}
		});

		B.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (B.getText().equals(q.getQuizAns())) {
					
					++score;
					++number;
					B.setBackgroundColor(getResources().getColor(R.color.right));
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);

					Toast.makeText(ShowQuiz.this, "Rignt", Toast.LENGTH_LONG)
							.show();
				} else {
					++number;
					B.setBackgroundColor(getResources().getColor(R.color.wrong));
					Toast.makeText(ShowQuiz.this, "Wrong", Toast.LENGTH_LONG)
							.show();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);

				}
			}
		});

		C.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (C.getText().equals(q.getQuizAns())) {
					++score;
					++number;
					C.setBackgroundColor(getResources().getColor(R.color.right));
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);
					Toast.makeText(ShowQuiz.this, "Rignt", Toast.LENGTH_LONG)
							.show();
				} else {
					++number;
					C.setBackgroundColor(getResources().getColor(R.color.wrong));
					Toast.makeText(ShowQuiz.this, "Wrong", Toast.LENGTH_LONG)
							.show();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);
				}
			}
		});

		D.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (D.getText().equals(q.getQuizAns())) {
					++score;
					++number;
					D.setBackgroundColor(getResources().getColor(R.color.right));
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);
					Toast.makeText(ShowQuiz.this, "Rignt", Toast.LENGTH_LONG)
							.show();
				} else {
					++number;
					D.setBackgroundColor(getResources().getColor(R.color.wrong));
					Toast.makeText(ShowQuiz.this, "Wrong", Toast.LENGTH_LONG)
							.show();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							nextQuiz();
						}
					}, 1000);
				}
			}
		});

	}

	private void initializeProperty() {
		A = (Button) findViewById(R.id.button1);
		B = (Button) findViewById(R.id.button2);
		C = (Button) findViewById(R.id.button3);
		D = (Button) findViewById(R.id.button4);
		QuizTitle = (TextView) findViewById(R.id.tv_quiz_title);
		QuizImage = (ImageView) findViewById(R.id.iv_quiz_pic);
		quiznum = (TextView) findViewById(R.id.tv_quiz_num);

	}

	public static String converJsonToStringFromAssetFolder(String fileName,
			Context context) throws IOException {
		AssetManager manager = context.getAssets();
		InputStream file = manager.open(fileName);

		byte[] data = new byte[file.available()];
		file.read(data);
		file.close();
		return new String(data);
	}

	private class GetPlaceAsyncTask extends AsyncTask<String, Integer, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ShowQuiz.this);
			pDialog.setMessage("Listing Categories...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... url) {
			try {
				json = converJsonToStringFromAssetFolder(path,
						getApplicationContext());
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			JSONObject jsonobject;
			try {
				jsonobject = new JSONObject(json);

				quizes = (JSONArray) jsonobject.getJSONArray("Quizes");
				for (int i = 0; i < quizes.length(); i++) {
					JSONObject c = quizes.getJSONObject(i);

					if (c.getString("QuizCategory").equals(CatagoryType)) {
						Quiz quiz = new Quiz();

						quiz.setQuizId(c.getInt("Quizid"));
						quiz.setQuizTitle(c.getString("QuizTitle"));
						quiz.setQuizAns(c.getString("QuizAns"));
						quiz.setQuizHint(c.getString("QuizHint"));
						quiz.setQuizDescription(c.getString("QuizDescription"));
						quiz.setQuizPicture(c.getString("QuizPicture"));
						quiz.setQuizCategory(c.getString("QuizCategory"));
						quiz.setQuizOp1(c.getString("QuizOp1"));
						quiz.setQuizOp2(c.getString("QuizOp2"));
						quiz.setQuizOp3(c.getString("QuizOp3"));

						AllLandQuizes.add(quiz);
					}

				}

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return null;

		}

		// OnPostExecute - Set the ListAdapter & Start ImageLoader AsyncTask
		@Override
		protected void onPostExecute(Void params) {

			pDialog.dismiss();

			nextQuiz();

		}
	}

	private void nextQuiz() {

		if (number == AllLandQuizes.size()) {

			Intent i = new Intent();
			i.setClass(ShowQuiz.this, ShowScore.class);
			i.putExtra("score", "" + score);
			startActivity(i);
			finish();

		} else {

			Random randomQuiz = new Random();

			//int randomInt = randomQuiz.nextInt(AllLandQuizes.size());

			q = AllLandQuizes.get(number);

			AssetManager assets = getAssets(); // get app's AssetManager
			InputStream stream;
			try {
				// stream = assets.open(region + "/" + nextImageName + ".png");
				stream = assets.open(q.getQuizPicture());

				Bitmap bit = BitmapFactory.decodeStream(stream);
				QuizImage.setImageBitmap(bit);
			} catch (IOException e) {
				Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
			}

			QuizTitle.setText(q.getQuizTitle());
			quiznum.setText("Question " + (number + 1) + " Out of "+AllLandQuizes.size());

			A.setBackgroundColor(getResources().getColor(R.color.white));
			B.setBackgroundColor(getResources().getColor(R.color.white));
			C.setBackgroundColor(getResources().getColor(R.color.white));
			D.setBackgroundColor(getResources().getColor(R.color.white));
			
			A.setText(q.getQuizAns());
			B.setText(q.getQuizOp1());
			C.setText(q.getQuizOp2());
			D.setText(q.getQuizOp3());

		}
	}

}
