package com.jingdong.app.mall.more;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.t;
import com.jingdong.app.mall.utils.ui.NumberPicker;
import com.jingdong.common.utils.CommonBase;

/* loaded from: classes4.dex */
public class PushTimeSettingActivity extends MyActivity implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private int f11278g;

    /* renamed from: h  reason: collision with root package name */
    private int f11279h;

    /* renamed from: i  reason: collision with root package name */
    private NumberPicker f11280i;

    /* renamed from: j  reason: collision with root package name */
    private NumberPicker f11281j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f11282k;

    /* loaded from: classes4.dex */
    class a implements NumberPicker.i {
        a() {
        }

        @Override // com.jingdong.app.mall.utils.ui.NumberPicker.i
        public void a(NumberPicker numberPicker, int i2, int i3) {
            PushTimeSettingActivity.this.f11278g = i3;
            PushTimeSettingActivity pushTimeSettingActivity = PushTimeSettingActivity.this;
            pushTimeSettingActivity.B(pushTimeSettingActivity.f11278g, PushTimeSettingActivity.this.f11279h);
        }
    }

    /* loaded from: classes4.dex */
    class b implements NumberPicker.i {
        b() {
        }

        @Override // com.jingdong.app.mall.utils.ui.NumberPicker.i
        public void a(NumberPicker numberPicker, int i2, int i3) {
            PushTimeSettingActivity.this.f11279h = i3;
            PushTimeSettingActivity pushTimeSettingActivity = PushTimeSettingActivity.this;
            pushTimeSettingActivity.B(pushTimeSettingActivity.f11278g, PushTimeSettingActivity.this.f11279h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11283g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f11284h;

        c(int i2, int i3) {
            this.f11283g = i2;
            this.f11284h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PushTimeSettingActivity.this.f11282k != null) {
                PushTimeSettingActivity.this.f11282k.setText(t.b(this.f11283g, this.f11284h));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ NumberPicker f11286g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f11287h;

        d(PushTimeSettingActivity pushTimeSettingActivity, NumberPicker numberPicker, boolean z) {
            this.f11286g = numberPicker;
            this.f11287h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker numberPicker = this.f11286g;
            if (numberPicker != null) {
                numberPicker.handleButtonOnclick(this.f11287h);
            }
        }
    }

    private void A(NumberPicker numberPicker, boolean z) {
        post(new d(this, numberPicker, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B(int i2, int i3) {
        post(new c(i2, i3));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.setting_up_imageview) {
            switch (id) {
                case R.id.setting_dialog_cancle /* 2131694471 */:
                    finish();
                    return;
                case R.id.setting_dialog_ok /* 2131694472 */:
                    CommonBase.putIntToPreference("setting_start_time", this.f11280i.getValue());
                    CommonBase.putIntToPreference("setting_end_time", this.f11281j.getValue());
                    setResult(-1);
                    finish();
                    return;
                case R.id.setting_down_imageview /* 2131694473 */:
                    A(this.f11280i, true);
                    return;
                case R.id.setting_end_down_imageview /* 2131694474 */:
                    A(this.f11281j, true);
                    return;
                case R.id.setting_end_up_imageview /* 2131694475 */:
                    A(this.f11281j, false);
                    return;
                default:
                    finish();
                    return;
            }
        }
        A(this.f11280i, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.settingdialog_number_picker);
        findViewById(R.id.setting_dialog_cancle).setOnClickListener(this);
        findViewById(R.id.setting_dialog_ok).setOnClickListener(this);
        this.f11282k = (TextView) findViewById(R.id.setting_number_picker_title);
        this.f11280i = (NumberPicker) findViewById(R.id.setting_number_picker_start);
        this.f11281j = (NumberPicker) findViewById(R.id.setting_number_picker_end);
        findViewById(R.id.setting_up_imageview).setOnClickListener(this);
        findViewById(R.id.setting_down_imageview).setOnClickListener(this);
        findViewById(R.id.setting_end_up_imageview).setOnClickListener(this);
        findViewById(R.id.setting_end_down_imageview).setOnClickListener(this);
        this.f11280i.setMinValue(0);
        this.f11280i.setMaxValue(23);
        this.f11280i.setWrapSelectorWheel(true);
        this.f11280i.setDescendantFocusability(393216);
        this.f11281j.setMinValue(0);
        this.f11281j.setMaxValue(23);
        this.f11281j.setWrapSelectorWheel(true);
        this.f11281j.setDescendantFocusability(393216);
        this.f11280i.setOnValueChangedListener(new a());
        this.f11281j.setOnValueChangedListener(new b());
        this.f11278g = CommonBase.getIntFromPreference("setting_start_time", 0);
        this.f11279h = CommonBase.getIntFromPreference("setting_end_time", 0);
        this.f11280i.setValue(this.f11278g);
        this.f11281j.setValue(this.f11279h);
        B(this.f11278g, this.f11279h);
    }
}
