package com.jingdong.app.mall.aura.internal;

import android.content.Intent;
import android.content.res.JDMobiSec;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.widget.TempTitle;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes19.dex */
public class UfoPageNotFound extends MyActivity implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private TempTitle f7933g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f7934h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f7935i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f7936j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f7937k;

    /* renamed from: l  reason: collision with root package name */
    private Button f7938l;

    /* renamed from: m  reason: collision with root package name */
    private int f7939m = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements TempTitle.TitleClickListener {
        a() {
        }

        @Override // com.jingdong.common.widget.TempTitle.TitleClickListener
        public void onLeftClicked() {
            UfoPageNotFound.this.finish();
        }

        @Override // com.jingdong.common.widget.TempTitle.TitleClickListener
        public void onRightClicked() {
        }
    }

    private void init() {
        this.f7933g = (TempTitle) findViewById(R.id.titlebar);
        this.f7934h = (ImageView) findViewById(R.id.ufo_image);
        this.f7935i = (TextView) findViewById(R.id.ufo_text1);
        this.f7936j = (TextView) findViewById(R.id.ufo_text2);
        this.f7937k = (TextView) findViewById(R.id.ufo_text3);
        this.f7938l = (Button) findViewById(R.id.ufo_refresh);
        int i2 = this.f7939m;
        if (i2 == 0) {
            this.f7934h.setBackgroundResource(R.drawable.ufo_page_not_found);
            this.f7935i.setText(R.string.error_string);
            this.f7936j.setText(R.string.ufo_page_text_error1);
            this.f7937k.setText(R.string.ufo_page_text_error3);
            this.f7937k.setVisibility(0);
            this.f7938l.setVisibility(8);
        } else if (i2 == 1) {
            this.f7934h.setBackgroundResource(R.drawable.aura_load_failed);
            this.f7935i.setText(R.string.ufo_page_text_load_fail);
            this.f7938l.setText(R.string.ufo_page_text_restart);
            this.f7936j.setVisibility(8);
            this.f7937k.setVisibility(8);
            this.f7938l.setVisibility(0);
        } else if (i2 == 2) {
            this.f7934h.setBackgroundResource(R.drawable.aura_error);
            this.f7935i.setText(R.string.error_string);
            this.f7936j.setText(R.string.ufo_page_text_error1);
            this.f7937k.setText(R.string.ufo_page_text_error2);
            this.f7938l.setText(R.string.ufo_page_text_clean);
            this.f7937k.setVisibility(0);
            this.f7938l.setVisibility(0);
        }
        TempTitle tempTitle = this.f7933g;
        if (tempTitle != null) {
            tempTitle.setOnTitleClickListener(new a());
        }
        this.f7938l.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.ufo_refresh) {
            return;
        }
        int i2 = this.f7939m;
        if (i2 == 1) {
            JDMtaUtils.sendCommonData(this, JDMobiSec.n1("db98157949c3053a6752f23146aa55"), "", "", this, "", getClass(), "");
            finish();
            BaseFrameUtil.getInstance().restartApp(getThisActivity());
        } else if (i2 == 2) {
            JDMtaUtils.sendCommonData(this, JDMobiSec.n1("db98157949c3053a6743fb3848a5"), "", "", this, "", getClass(), "");
            startActivity(new Intent(JDMobiSec.n1("cf901e5456cb06714b45e32940a5568f98f367bf66f6573b38f22c34953052304ca6a62332f921f2064276b00d")));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ufo_page_not_found);
        if (FileUtils.getDataDiskFreeSize(true) <= 2) {
            this.f7939m = 2;
        } else {
            this.f7939m = 1;
        }
        JDMtaUtils.sendCommonData(this, JDMobiSec.n1("db98157949c3053a674ef82976ad5e89d8da"), String.valueOf(this.f7939m), JDMobiSec.n1("c19039545cc3163a"), this, "", getClass(), "");
        init();
    }
}
