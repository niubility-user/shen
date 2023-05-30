package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class VideoActivity extends Activity {
    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        p.a(this).a(i2, i3, intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.requestWindowFeature(1);
        super.getWindow().setFormat(-3);
        Intent intent = super.getIntent();
        Bundle bundleExtra = intent != null ? intent.getBundleExtra("extraData") : null;
        if (bundleExtra != null) {
            bundleExtra.putInt("callMode", 1);
            p.a(super.getApplicationContext()).a((String) null, bundleExtra, (com.tencent.tbs.video.interfaces.a) null);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        p.a(this).a(this, 4);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        p.a(this).a(this, 3);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        p.a(this).a(this, 2);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        p.a(this).a(this, 1);
    }
}
