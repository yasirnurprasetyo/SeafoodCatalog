package com.example.seafoodlist;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    public static final String DATA = "seafood_data";
    private ProgressBar progressBar;
    private TextView instruction, title;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        progressBar = findViewById(R.id.progressBar);
        title = findViewById(R.id.tv_title);
        instruction = findViewById(R.id.tv_item_instruction);
        image = findViewById(R.id.img_item_photo);

        final Seafood seafood = getIntent().getParcelableExtra(DATA);
        final ArrayList<Seafood> seafoodArrayList = new ArrayList<>();
        Glide.with(this)
                .load(seafood.getImage())
                .into(image);
        //ambil data dari api themealdb
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + seafood.getId();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            //dijalankan ketika koneksi ke api berhasil
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray array = responseObject.getJSONArray("meals");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject itemSeafood = array.getJSONObject(i);
                        title.setText(seafood.setTitle(itemSeafood.getString("strMeal")));
                        instruction.setText(seafood.setInstruction(itemSeafood.getString("strInstructions")));
                        seafoodArrayList.add(seafood);
                    }
                } catch (Exception e) {
                    Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            //dijalankan ketika koneksi gagal
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Request Gagal";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Terlarang";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Tidak ditemukan";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                Log.d("onFailure", errorMessage);
            }
        });
    }
}
