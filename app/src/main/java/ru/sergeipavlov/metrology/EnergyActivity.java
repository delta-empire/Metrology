package ru.sergeipavlov.metrology;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class EnergyActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_energy);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.energy_container, new EnergyFragment())
                    .commit();
        }
    }
}
