package com.example.notificationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NotifyClickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);

        if (WoApplication.isOpened) {
            Log.e("xie", "go main!");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Log.e("xie", "go login!");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
