package br.com.ram.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.ram.R;

public class SplashScreenActivity extends AppCompatActivity {

    public static final int DELAY_TO_CALL_START_PAGE = 2_000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Create delay to call start page
        delayToCallStartPage();

    }

    private void delayToCallStartPage() {
        //Set a delay to call the start page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Call start page
                goToStartPage();
            }
        }, DELAY_TO_CALL_START_PAGE);
    }

    private void goToStartPage() {
        Intent intent = new Intent(this, StudentsListActivity.class);
        startActivity(intent);
        finish();
    }
}