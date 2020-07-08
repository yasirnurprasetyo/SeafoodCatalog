package com.example.seafoodlist;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class SplashActivity extends Activity {
    //untuk menampilkan splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000); //berapa menit ketika ditampilkan
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    //berfungsi untuk melakukan activity dari mana ke mana
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}