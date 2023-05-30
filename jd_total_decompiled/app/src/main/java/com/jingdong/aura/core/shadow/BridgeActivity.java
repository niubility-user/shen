package com.jingdong.aura.core.shadow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes4.dex */
public class BridgeActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent a = c.a(getIntent());
        if (a != null) {
            a.addFlags(33554432);
            startActivity(a);
            finish();
            return;
        }
        throw new RuntimeException("BridgeActivity got non bridgeTo parameter");
    }
}
