package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ScaleSignalActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scale_signal;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.scale_signal_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new ScaleSignalFragment();
    }
}
