package com.jingdong.app.mall.crash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes3.dex */
public class JDCallOnTimeActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private ListView f8322g;

    /* renamed from: h  reason: collision with root package name */
    private e f8323h;

    /* renamed from: i  reason: collision with root package name */
    private View f8324i;

    /* renamed from: j  reason: collision with root package name */
    private CheckBox f8325j;

    /* renamed from: k  reason: collision with root package name */
    JDDialog f8326k;

    /* renamed from: l  reason: collision with root package name */
    private Handler f8327l = new a();

    /* loaded from: classes3.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                JDDialog jDDialog = JDCallOnTimeActivity.this.f8326k;
                if (jDDialog != null && jDDialog.isShowing()) {
                    JDCallOnTimeActivity.this.f8326k.dismiss();
                }
                JDCallOnTimeActivity.this.f8322g.setAdapter((ListAdapter) JDCallOnTimeActivity.this.f8323h);
                JDCallOnTimeActivity.this.f8323h.notifyDataSetChanged();
            }
            super.handleMessage(message);
        }
    }

    /* loaded from: classes3.dex */
    class b implements View.OnClickListener {

        /* loaded from: classes3.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.jingdong.app.mall.crash.a.b();
                JDCallOnTimeActivity.this.f8323h.a(com.jingdong.app.mall.crash.a.d());
                JDCallOnTimeActivity.this.f8327l.sendEmptyMessage(1);
            }
        }

        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDCallOnTimeActivity.this.f8326k = JDDialogFactory.getInstance().createJdDialogWithStyle2(JDCallOnTimeActivity.this, "\u786e\u8ba4\u5220\u9664\u5168\u90e8\u9875\u9762\u52a0\u8f7d\u4fe1\u606f", "\u53d6\u6d88", "\u786e\u5b9a");
            JDCallOnTimeActivity.this.f8326k.setOnRightButtonClickListener(new a());
            try {
                JDCallOnTimeActivity.this.f8326k.show();
            } catch (Throwable th) {
                if (Log.E) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    class c implements View.OnClickListener {
        c(JDCallOnTimeActivity jDCallOnTimeActivity) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if ((view instanceof CheckBox) && ((CheckBox) view).isChecked()) {
                SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                edit.putBoolean("callOnTime", true);
                edit.commit();
                AuraConfig.setAuraDebugTimeListener(com.jingdong.app.mall.crash.b.a());
                return;
            }
            SharedPreferences.Editor edit2 = CommonBase.getJdSharedPreferences().edit();
            edit2.putBoolean("callOnTime", false);
            edit2.commit();
            AuraConfig.setAuraDebugTimeListener(null);
        }
    }

    /* loaded from: classes3.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDCallOnTimeActivity.this.f8323h.a(com.jingdong.app.mall.crash.a.d());
            JDCallOnTimeActivity.this.f8327l.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_callontime);
        this.f8324i = findViewById(R.id.relativeLayout_clear_all);
        this.f8325j = (CheckBox) findViewById(R.id.personal_item_checkbox);
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.f8323h = new e();
        this.f8322g = (ListView) findViewById(R.id.call_activitytime_listview);
        this.f8324i.setOnClickListener(new b());
        if (CommonBase.getJdSharedPreferences().getBoolean("callOnTime", true)) {
            this.f8325j.setChecked(true);
        } else {
            this.f8325j.setChecked(false);
        }
        this.f8325j.setOnClickListener(new c(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Handler handler = this.f8327l;
        if (handler != null) {
            handler.removeMessages(1);
            this.f8327l = null;
        }
        this.f8323h = null;
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        new Thread(new d()).start();
        super.onResume();
    }
}
