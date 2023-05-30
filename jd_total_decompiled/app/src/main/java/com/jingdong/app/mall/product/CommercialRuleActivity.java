package com.jingdong.app.mall.product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;

/* loaded from: classes4.dex */
public class CommercialRuleActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private String f11553g;

    /* renamed from: h  reason: collision with root package name */
    private String f11554h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ TextView f11555g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ TextView f11556h;

        a(TextView textView, TextView textView2) {
            this.f11555g = textView;
            this.f11556h = textView2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f11555g.setText(CommercialRuleActivity.this.f11553g);
            this.f11556h.setText(CommercialRuleActivity.this.f11554h);
        }
    }

    private void w() {
        ((TextView) findViewById(R.id.jdnews_detail_time)).setVisibility(8);
        post(new a((TextView) findViewById(R.id.jdnews_detail_title), (TextView) findViewById(R.id.jdnews_detail_content)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jdnews_detail_activityl);
        ((TextView) findViewById(R.id.fd)).setText(R.string.rule_title);
        setTitleBack((ImageView) findViewById(R.id.fe));
        Intent intent = getIntent();
        this.f11553g = intent.getExtras().getString("title");
        this.f11554h = intent.getExtras().getString("detail");
        w();
    }
}
