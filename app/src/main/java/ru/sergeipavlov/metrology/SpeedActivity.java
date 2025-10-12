package ru.sergeipavlov.metrology;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class SpeedActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_speed);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.speed_container, new SpeedFragment())
                    .commit();
        }
    }
}
