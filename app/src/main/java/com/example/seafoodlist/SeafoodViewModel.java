package com.example.seafoodlist;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
public class SeafoodViewModel extends ViewModel {
    //mengambil data dari class Seafood
    private MutableLiveData<ArrayList<Seafood>> listSeafood = new MutableLiveData<>();
    //me-set data list Seafood dengan mengambil data dari API
    public void setListSeafood() {
        final ArrayList<Seafood> seafoodArrayList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood";
        client.get(url, new AsyncHttpResponseHandler() {
            //dijalankan ketika koneksi berhasil
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("meals");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject seafoods = list.getJSONObject(i);
                        Seafood seafood = new Seafood();
                        seafood.setId(seafoods.getString("idMeal"));
                        seafood.setTitlee(seafoods.getString("strMeal"));
                        seafood.setImage(seafoods.getString("strMealThumb"));
                        seafoodArrayList.add(seafood);
                    }
                    listSeafood.postValue(seafoodArrayList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //dijalankan ketika koneksi gagal
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                Log.d("onFailure", errorMessage);
            }
        });
    }
    //mengambil data dari class Seafood
    public MutableLiveData<ArrayList<Seafood>> getListSeafood() {
        return listSeafood;
    }
}
