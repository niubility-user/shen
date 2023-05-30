package com.jingdong.common.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.address.R;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import com.jingdong.common.unification.statusbar.IStatusController;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.AreaCodeSelectListenerParcel;
import com.jingdong.common.utils.JDAreaCodeSelectUtil;
import com.jingdong.common.utils.JDAreaCodeSelectViewHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JdAreaCodeSelectActivity extends FragmentActivity implements IStatusController, JDAreaCodeSelectViewHelper.OnAreaCodeListener {
    private static final String TAG = JdAreaCodeSelectActivity.class.getSimpleName();
    private JDAreaCodeSelectViewHelper helper;
    private JDAreaCodeSelectUtil.AreaCodeSelectListener mAreaCodeSelectListener;
    private AreaCodeSelectListenerParcel parcel;
    private RelativeLayout rootView;

    private void addAreaCodeSelectView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, pageHeight());
        layoutParams.addRule(12);
        JDAreaCodeSelectViewHelper jDAreaCodeSelectViewHelper = new JDAreaCodeSelectViewHelper(this);
        this.helper = jDAreaCodeSelectViewHelper;
        jDAreaCodeSelectViewHelper.getView().setOnClickListener(null);
        if (this.helper.getView() != null) {
            this.rootView.addView(this.helper.getView(), layoutParams);
            this.helper.setOnAreaCodeListener(this);
            this.helper.setData("data");
            this.helper.showAreaCode();
        }
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra("parcel")) {
            return;
        }
        AreaCodeSelectListenerParcel areaCodeSelectListenerParcel = (AreaCodeSelectListenerParcel) intent.getParcelableExtra("parcel");
        this.parcel = areaCodeSelectListenerParcel;
        if (areaCodeSelectListenerParcel != null) {
            this.mAreaCodeSelectListener = areaCodeSelectListenerParcel.getAreaCodeSelectListener();
        }
    }

    private void initView() {
        setFinishOnTouchOutside(true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_view);
        this.rootView = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JdAreaCodeSelectActivity.1
            {
                JdAreaCodeSelectActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JdAreaCodeSelectActivity.this.finish();
            }
        });
        addAreaCodeSelectView();
    }

    private int pageHeight() {
        int appHeight = DPIUtil.getAppHeight(this);
        int appWidth = DPIUtil.getAppWidth(this);
        if (appHeight <= appWidth) {
            appHeight = appWidth;
        }
        if (UnAndroidUtils.checkDeviceHasNavigationBar(this)) {
            appHeight += UnStatusBarTintUtil.getNavigationBarHeight(this);
        }
        double d = appHeight;
        Double.isNaN(d);
        return (int) (d * 0.85d);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.jd_dialog_bottom_enter, R.anim.jd_dialog_bottom_exit);
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public String getServerConfigValue() {
        return null;
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean isDisplayCutout() {
        return false;
    }

    @Override // com.jingdong.common.utils.JDAreaCodeSelectViewHelper.OnAreaCodeListener
    public void onAreaCodeSelected(AreaBeanVO areaBeanVO) {
        if (Log.D) {
            Log.d(TAG, "onAreaCodeSelected");
        }
        JDAreaCodeSelectUtil.AreaCodeSelectListener areaCodeSelectListener = this.mAreaCodeSelectListener;
        if (areaCodeSelectListener != null) {
            areaCodeSelectListener.onSelect(JDJSON.toJSONString(areaBeanVO));
        }
        finish();
    }

    @Override // com.jingdong.common.utils.JDAreaCodeSelectViewHelper.OnAreaCodeListener
    public void onClose() {
        if (Log.D) {
            Log.d(TAG, "onClose");
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        UnStatusBarTintUtil.init(this);
        if (Log.D) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate(bundle);
        setFinishOnTouchOutside(true);
        overridePendingTransition(R.anim.jd_dialog_bottom_enter, R.anim.jd_dialog_bottom_exit);
        setContentView(R.layout.activity_jd_area_code_select);
        UnStatusBarTintUtil.setStatusBar4Base(this, statusBarHint());
        initData();
        initView();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (Log.D) {
            Log.d(TAG, "onDestroy");
        }
        JDAreaCodeSelectViewHelper jDAreaCodeSelectViewHelper = this.helper;
        if (jDAreaCodeSelectViewHelper != null) {
            jDAreaCodeSelectViewHelper.destroy();
        }
        this.mAreaCodeSelectListener = null;
        this.parcel = null;
    }

    @Override // com.jingdong.common.utils.JDAreaCodeSelectViewHelper.OnAreaCodeListener
    public void onLoadAreaCode(boolean z) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (Log.D) {
            Log.d(TAG, "onPause");
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (Log.D) {
            Log.d(TAG, "onRestart");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        UnStatusBarTintUtil.init(this);
        if (Log.D) {
            Log.d(TAG, "onResume");
        }
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public int statusBarHint() {
        return 1;
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean statusBarTransparentEnable() {
        return true;
    }
}
