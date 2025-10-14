package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class VolumeActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_volume;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.volume_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new VolumeFragment();
    }
}
