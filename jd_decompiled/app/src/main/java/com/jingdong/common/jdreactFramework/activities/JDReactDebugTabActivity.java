package com.jingdong.common.jdreactFramework.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jingdong.jdreactframewok.R;

/* loaded from: classes5.dex */
public class JDReactDebugTabActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment currentFragment;
    private DebugToolsFragment debugToolsFragment;
    private FragmentManager fragmentManager;
    private PluginListFragment pluginListFragment;
    private TextView tvDebugTools;
    private TextView tvPluginList;

    @SuppressLint({"NewApi"})
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        PluginListFragment pluginListFragment = this.pluginListFragment;
        if (pluginListFragment != null) {
            fragmentTransaction.hide(pluginListFragment);
        }
        DebugToolsFragment debugToolsFragment = this.debugToolsFragment;
        if (debugToolsFragment != null) {
            fragmentTransaction.hide(debugToolsFragment);
        }
    }

    private void initViews() {
        this.tvPluginList = (TextView) findViewById(R.id.tv_plugin_list);
        this.tvDebugTools = (TextView) findViewById(R.id.tv_debug_tools);
        this.tvPluginList.setOnClickListener(this);
        this.tvDebugTools.setOnClickListener(this);
    }

    private void resetBtn() {
        this.tvPluginList.setSelected(false);
        this.tvDebugTools.setSelected(false);
    }

    @SuppressLint({"NewApi"})
    private void setTabSelection(int i2) {
        resetBtn();
        FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        hideFragments(beginTransaction);
        if (i2 == 0) {
            this.tvPluginList.setSelected(true);
            Fragment fragment = this.pluginListFragment;
            if (fragment == null) {
                PluginListFragment pluginListFragment = new PluginListFragment();
                this.pluginListFragment = pluginListFragment;
                beginTransaction.add(R.id.fl_content, pluginListFragment);
            } else {
                beginTransaction.show(fragment);
            }
            this.currentFragment = this.pluginListFragment;
        } else if (i2 == 1) {
            this.tvDebugTools.setSelected(true);
            Fragment fragment2 = this.debugToolsFragment;
            if (fragment2 == null) {
                DebugToolsFragment debugToolsFragment = new DebugToolsFragment();
                this.debugToolsFragment = debugToolsFragment;
                beginTransaction.add(R.id.fl_content, debugToolsFragment);
            } else {
                beginTransaction.show(fragment2);
            }
            this.currentFragment = this.debugToolsFragment;
        }
        beginTransaction.commit();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_plugin_list) {
            setTabSelection(0);
        } else if (id == R.id.tv_debug_tools) {
            setTabSelection(1);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.react_native_debug_main);
        initViews();
        this.fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Fragment fragment = this.currentFragment;
        if (fragment != null) {
            fragment.onResume();
        }
    }
}
