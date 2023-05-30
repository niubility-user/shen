package com.jd.manto.center;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jingdong.manto.ui.MantoBaseActivity;

/* loaded from: classes17.dex */
public class MantoCenterAboutActivity extends MantoBaseActivity {

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoCenterAboutActivity.this.finish();
        }
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_center_about;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        View findViewById = findViewById(R.id.action_bar_container);
        View findViewById2 = findViewById.findViewById(R.id.manto_ui_nav_option);
        TextView textView = (TextView) findViewById(R.id.manto_app_desc);
        ((TextView) findViewById.findViewById(R.id.manto_ui_nav_title)).setText("\u5173\u4e8e");
        String stringExtra = getIntent().getStringExtra("tinyAppIntroduction");
        if (!TextUtils.isEmpty(stringExtra)) {
            textView.setText(stringExtra);
        }
        findViewById2.setVisibility(4);
        findViewById.findViewById(R.id.manto_ui_nav_back).setOnClickListener(new a());
        b.a(this, -1, true);
    }
}
