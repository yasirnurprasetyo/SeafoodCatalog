package com.example.seafoodlist;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ListSeafoodAdapter adapter;
    private SeafoodViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menampilkan list view
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.rv_seafood);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ListSeafoodAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SeafoodViewModel.class);
        model.setListSeafood();
        model.getListSeafood().observe(this, new Observer<ArrayList<Seafood>>() {
            @Override
            public void onChanged(ArrayList<Seafood> seafoods) {
                if (seafoods != null) {
                    adapter.setSeafoodArrayList(seafoods);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        progressBar.setVisibility(View.VISIBLE); //menampilkan progresBar
    }
    //menampilkan menu pada actionBar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //melakukan aksi ketika menu diklik
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    //perintah untuk menampilkan MyProfile Activity
    public void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.myProfile:
                Intent moveIntent = new Intent(MainActivity.this, MyProfile.class);
                startActivity(moveIntent);
                break;
        }
    }
}
