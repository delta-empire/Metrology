package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SpeedActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_speed;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.speed_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new SpeedFragment();
    }
}
