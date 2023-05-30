package com.jingdong.manto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.utils.MantoCryptoUtils;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;

/* loaded from: classes16.dex */
public class MantoShortcutEntry extends AppCompatActivity {

    /* loaded from: classes16.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoShortcutEntry.this.finish();
        }
    }

    private boolean a(Bundle bundle) {
        String string = bundle.getString("extra_info");
        if (MantoStringUtils.isEmpty(string)) {
            return false;
        }
        String a2 = MantoCryptoUtils.a(string);
        String[] split = a2.split(a2.contains("#@#") ? "#@#" : ":");
        if (split.length < 3) {
            return false;
        }
        try {
            if (!MantoStringUtils.isEmpty(split[0]) && !MantoStringUtils.isEmpty(split[1])) {
                LaunchParam launchParam = new LaunchParam();
                launchParam.appId = split[0];
                String str = split[2];
                launchParam.debugType = str;
                if (TextUtils.isEmpty(str)) {
                    launchParam.debugType = "1";
                }
                if (split.length == 4 && !MantoStringUtils.isEmpty(split[3])) {
                    launchParam.launchPath = split[3];
                }
                if (split.length == 5 && !MantoStringUtils.isEmpty(split[4])) {
                    launchParam.extrasJson = split[4];
                }
                com.jingdong.a.p(launchParam, this);
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        getWindow().getDecorView();
        Intent intent = getIntent();
        if ((intent == null || (extras = intent.getExtras()) == null) ? false : a(extras)) {
            MantoThreadUtils.post(new a(), 5000);
        } else {
            finish();
        }
    }
}
