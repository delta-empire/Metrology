package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FrequencyActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_frequency;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.frequency_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new FrequencyFragment();
    }
}
