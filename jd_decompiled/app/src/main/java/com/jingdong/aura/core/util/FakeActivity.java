package com.jingdong.aura.core.util;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes.dex */
public class FakeActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
    }
}
