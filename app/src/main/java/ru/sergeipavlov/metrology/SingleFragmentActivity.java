package ru.sergeipavlov.metrology;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class SingleFragmentActivity extends BaseActivity {

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract int getFragmentContainerId();

    @NonNull
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutResId());
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getFragmentContainerId(), createFragment())
                    .commit();
        }
    }
}
