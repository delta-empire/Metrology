package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TimeActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_time;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.time_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new TimeFragment();
    }
}
