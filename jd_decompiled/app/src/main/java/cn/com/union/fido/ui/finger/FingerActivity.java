package cn.com.union.fido.ui.finger;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.ui.FIDOUISDK;
import cn.com.union.fido.ui.IActivityPresenter;
import cn.com.union.fido.ui.IFidoView;
import com.jd.dynamic.DYConstants;
import com.jdcn.fido.R;
import com.jdjr.risk.jdcn.common.managers.JDCNUIModeManager;

@TargetApi(23)
/* loaded from: classes.dex */
public class FingerActivity extends Activity implements IFidoView {
    private int ErrorCount = 0;
    private TextView authTitleTop;
    private TextView cancelView;
    private TextView changeLoginMode;
    private IActivityPresenter fidoPresenter;
    private RelativeLayout linearLayout;
    Activity mContext;
    private TextView scanText;
    private View verticalLine;

    private void handleViewStyle() {
        String normalHintText = FIDOUISDK.getNormalHintText();
        if (!TextUtils.isEmpty(normalHintText)) {
            this.authTitleTop.setText(normalHintText);
        }
        int normalHintColor = FIDOUISDK.getNormalHintColor();
        if (normalHintColor != 0) {
            this.authTitleTop.setTextColor(normalHintColor);
        }
        String abnormalHintText = FIDOUISDK.getAbnormalHintText();
        if (!TextUtils.isEmpty(abnormalHintText)) {
            this.scanText.setText(abnormalHintText);
        }
        int abnormalHintColor = FIDOUISDK.getAbnormalHintColor();
        if (abnormalHintColor != 0) {
            this.scanText.setTextColor(abnormalHintColor);
        }
        String btnBottomText = FIDOUISDK.getBtnBottomText();
        if (!TextUtils.isEmpty(btnBottomText)) {
            this.cancelView.setText(btnBottomText);
        }
        int btnBottomColor = FIDOUISDK.getBtnBottomColor();
        if (btnBottomColor != 0) {
            this.cancelView.setTextColor(btnBottomColor);
        }
        String btnRinghtText = FIDOUISDK.getBtnRinghtText();
        if (!TextUtils.isEmpty(btnRinghtText)) {
            this.changeLoginMode.setText(btnRinghtText);
        }
        int btnRinghtColor = FIDOUISDK.getBtnRinghtColor();
        if (btnRinghtColor != 0) {
            this.changeLoginMode.setTextColor(btnRinghtColor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewDisplay() {
        if (this.linearLayout == null) {
            this.linearLayout = (RelativeLayout) findViewById(R.id.exit_layout);
        }
        RelativeLayout relativeLayout = this.linearLayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    @Override // cn.com.union.fido.ui.IFidoView
    public void authViewDisplay(String str) {
        runOnUiThread(new Runnable() { // from class: cn.com.union.fido.ui.finger.FingerActivity.3
            @Override // java.lang.Runnable
            public void run() {
                FingerActivity.this.viewDisplay();
            }
        });
    }

    @Override // cn.com.union.fido.ui.IFidoView
    public void onAuthenticationError(String str) {
        finish();
    }

    @Override // cn.com.union.fido.ui.IFidoView
    public void onAuthenticationFailed(String str) {
        TextView textView = this.scanText;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.authTitleTop;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
        TextView textView3 = this.scanText;
        if (textView3 != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.scanText, "translationX", textView3.getTranslationX(), 50.0f, -100.0f, 80.0f, -60.0f, -40.0f, 10.0f, 8.0f, -3.0f, 3.0f);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.setDuration(500L);
            ofFloat.start();
        }
        if (str.equals(Operation.Auth.name()) && DYConstants.DY_TRUE.equals(FIDOUISDK.getHasOtherAuthMode())) {
            TextView textView4 = this.changeLoginMode;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
            View view = this.verticalLine;
            if (view != null) {
                view.setVisibility(0);
            }
            if (this.cancelView != null) {
                String btnLeftText = FIDOUISDK.getBtnLeftText();
                if (!TextUtils.isEmpty(btnLeftText)) {
                    this.cancelView.setText(btnLeftText);
                }
                int btnLeftColor = FIDOUISDK.getBtnLeftColor();
                if (btnLeftColor != 0) {
                    this.cancelView.setTextColor(btnLeftColor);
                }
            }
        }
        int i2 = this.ErrorCount + 1;
        this.ErrorCount = i2;
        if (i2 == 3) {
            IActivityPresenter iActivityPresenter = this.fidoPresenter;
            if (iActivityPresenter != null) {
                iActivityPresenter.handlerActivityResult(24);
                this.fidoPresenter = null;
            }
            finish();
        }
    }

    @Override // cn.com.union.fido.ui.IFidoView
    public void onAuthenticationHelp(String str, CharSequence charSequence) {
    }

    @Override // cn.com.union.fido.ui.IFidoView
    public void onAuthenticationSucceeded(String str) {
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        IActivityPresenter iActivityPresenter = this.fidoPresenter;
        if (iActivityPresenter != null) {
            iActivityPresenter.handlerActivityResult(18);
            this.fidoPresenter = null;
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        int uIMode = JDCNUIModeManager.getInstance().getUIMode();
        setContentView((uIMode == 0 || uIMode != 1) ? R.layout.activity_finger_alliance_light : R.layout.activity_finger_alliance_dark);
        this.scanText = (TextView) findViewById(R.id.user_scan_text);
        this.authTitleTop = (TextView) findViewById(R.id.authTitle_top);
        this.verticalLine = findViewById(R.id.vertical_line);
        TextView textView = (TextView) findViewById(R.id.change_login_mode);
        this.changeLoginMode = textView;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: cn.com.union.fido.ui.finger.FingerActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (FingerActivity.this.fidoPresenter != null) {
                        FingerActivity.this.fidoPresenter.handlerActivityResult(25);
                        FingerActivity.this.fidoPresenter = null;
                    }
                    FingerActivity.this.finish();
                }
            });
        }
        TextView textView2 = (TextView) findViewById(R.id.btn_cancel);
        this.cancelView = textView2;
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.com.union.fido.ui.finger.FingerActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (FingerActivity.this.fidoPresenter != null) {
                        FingerActivity.this.fidoPresenter.handlerActivityResult(18);
                        FingerActivity.this.fidoPresenter = null;
                    }
                    FingerActivity.this.finish();
                }
            });
        }
        handleViewStyle();
        IActivityPresenter fidoPresenter = FIDOUISDK.getFidoPresenter();
        this.fidoPresenter = fidoPresenter;
        if (fidoPresenter == null) {
            finish();
            return;
        }
        fidoPresenter.setFidoView(this);
        this.fidoPresenter.startAuthenticator(this.mContext);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        IActivityPresenter iActivityPresenter = this.fidoPresenter;
        if (iActivityPresenter != null) {
            iActivityPresenter.handlerActivityResult(18);
            this.fidoPresenter = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        IActivityPresenter iActivityPresenter = this.fidoPresenter;
        if (iActivityPresenter != null) {
            iActivityPresenter.handlerActivityResult(18);
            this.fidoPresenter = null;
        }
        finish();
    }
}
