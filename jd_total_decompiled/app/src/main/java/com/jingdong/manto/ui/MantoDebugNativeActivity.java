package com.jingdong.manto.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jingdong.manto.R;

/* loaded from: classes16.dex */
public class MantoDebugNativeActivity extends MantoBaseActivity implements View.OnClickListener {
    private ImageView a;

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_debug_native_activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == this.a.getId()) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ImageView imageView = (ImageView) findViewById(R.id.manto_debug_back_image);
        this.a = imageView;
        imageView.setOnClickListener(this);
        com.jingdong.manto.s.c.b().c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.manto.s.c.b().a();
    }
}
