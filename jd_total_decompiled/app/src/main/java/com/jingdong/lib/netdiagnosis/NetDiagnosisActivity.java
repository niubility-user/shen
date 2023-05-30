package com.jingdong.lib.netdiagnosis;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import f.f;
import java.util.concurrent.Callable;

/* loaded from: classes14.dex */
public class NetDiagnosisActivity extends BaseActivity {
    private boolean clickTag;
    private Button diagnoseBtn;
    private TextView explainText;
    private NetDiagnoseProgressView progressBar;
    private ImageView stateImage;
    private TextView stateText;
    private f.C0827f tcs;
    private ImageView titleBack;
    private TextView titleText;
    private int state = 0;
    public Handler handler = new a();

    /* loaded from: classes14.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            OKLog.d("net-result", message.what + " --- " + message.arg1);
            int i2 = message.what;
            if (i2 == 1) {
                NetDiagnosisActivity.this.progressBar.n(message.arg1);
            } else if (i2 != 3) {
            } else {
                OKLog.d("net-result", message.what + "   " + message.arg1);
                int i3 = message.arg1;
                if (i3 == 0) {
                    NetDiagnosisActivity.this.initState();
                } else if (i3 == 1) {
                    NetDiagnosisActivity.this.overState(message.arg2);
                } else if (i3 == 2) {
                    NetDiagnosisActivity.this.overState(6);
                } else if (i3 == 3) {
                    NetDiagnosisActivity.this.overState(7);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NetDiagnosisActivity.this.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (NetDiagnosisActivity.this.clickTag) {
                NetDiagnosisActivity.this.cancelState();
            } else {
                NetDiagnosisActivity.this.startState();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements f.d<Object, Object> {
        final /* synthetic */ int a;

        /* loaded from: classes14.dex */
        class a implements Animation.AnimationListener {
            a() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                NetDiagnosisActivity.this.progressBar.n(0);
                NetDiagnosisActivity.this.diagnoseBtn.setEnabled(true);
                NetDiagnosisActivity.this.diagnoseBtn.setText(NetDiagnosisActivity.this.getResources().getText(R.string.net_diagnosis_button_text_4));
                NetDiagnosisActivity.this.stateText.setText(NetDiagnosisActivity.this.getResources().getText(R.string.net_diagnosis_state_3));
                TextView textView = NetDiagnosisActivity.this.explainText;
                Resources resources = NetDiagnosisActivity.this.getResources();
                d dVar = d.this;
                textView.setText(resources.getText(NetDiagnosisActivity.this.getStringId(dVar.a)));
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        }

        /* loaded from: classes14.dex */
        class b implements Animation.AnimationListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Animation f12974g;

            b(Animation animation) {
                this.f12974g = animation;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                NetDiagnosisActivity.this.progressBar.setVisibility(4);
                NetDiagnosisActivity.this.stateImage.setVisibility(0);
                NetDiagnosisActivity.this.stateImage.startAnimation(this.f12974g);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        }

        d(int i2) {
            this.a = i2;
        }

        @Override // f.d
        public Object then(f.f<Object> fVar) throws Exception {
            int i2 = this.a;
            NetDiagnosisActivity.this.state = 1;
            NetDiagnosisActivity.this.progressBar.p();
            NetDiagnosisActivity.this.clickTag = false;
            NetDiagnosisActivity.this.stateImage.setBackgroundDrawable(NetDiagnosisActivity.this.getResources().getDrawable(R.drawable.net_diagnose_report));
            Animation endIndexAnimation = NetDiagnosisActivity.this.endIndexAnimation();
            endIndexAnimation.setAnimationListener(new a());
            Animation endProAnimation = NetDiagnosisActivity.this.endProAnimation();
            endProAnimation.setAnimationListener(new b(endIndexAnimation));
            NetDiagnosisActivity.this.progressBar.startAnimation(endProAnimation);
            NetDiagnosisActivity.this.progressBar.p.startAnimation(NetDiagnosisActivity.this.endTextAnimation());
            NetDiagnosisActivity.this.progressBar.q.startAnimation(NetDiagnosisActivity.this.endTextAnimation());
            if (i2 == 6) {
                return null;
            }
            if (i2 == 7) {
                i2 = 5;
            }
            NetDiagnosisActivity.this.sendCommonData("InternetDiagnose_DiagnoseResult", String.valueOf(i2));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements Callable<Object> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f12976g;

        e(int i2) {
            this.f12976g = i2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            int i2 = this.f12976g;
            if (i2 != 7 && i2 != 6) {
                for (int l2 = NetDiagnosisActivity.this.progressBar.l(); l2 <= 100; l2++) {
                    Thread.sleep(100L);
                    NetDiagnosisActivity.this.progressBar.n(l2);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements Callable<Object> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ f.f f12978g;

        f(f.f fVar) {
            this.f12978g = fVar;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            Thread.sleep(1000L);
            if (!this.f12978g.r()) {
                NetDiagnosisController.getController().netDiagnose(NetDiagnosisActivity.this.handler);
                return null;
            }
            Message obtainMessage = NetDiagnosisActivity.this.handler.obtainMessage(3);
            obtainMessage.arg1 = 0;
            NetDiagnosisActivity.this.handler.sendMessage(obtainMessage);
            return null;
        }
    }

    private void ableView() {
        this.diagnoseBtn.setEnabled(true);
        if (this.stateText.getText().equals(getResources().getString(R.string.net_diagnosis_state_1))) {
            this.explainText.setText(getResources().getText(R.string.net_diagnosis_explain));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelState() {
        this.state = -1;
        this.progressBar.p();
        this.tcs.b();
        NetDiagnosisController.getController().cancelTasks();
        this.diagnoseBtn.setEnabled(false);
        this.diagnoseBtn.setText(getResources().getText(R.string.net_diagnosis_button_text_3));
        this.clickTag = false;
        sendCommonData("InternetDiagnose_CancelDiagnose", "");
    }

    private void enableView() {
        this.diagnoseBtn.setEnabled(false);
        this.explainText.setText(getResources().getText(R.string.net_diagnosis_report_6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation endIndexAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.3f, 1.0f, 0.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(400L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(400L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation endProAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(400L);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
        alphaAnimation.setDuration(400L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation endTextAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400L);
        return alphaAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initState() {
        if (OKLog.D) {
            OKLog.d("netDiag", "initState");
        }
        this.clickTag = false;
        this.stateImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.net_diagnose_index));
        this.stateImage.setVisibility(0);
        this.progressBar.setVisibility(4);
        this.progressBar.n(0);
        this.diagnoseBtn.setEnabled(true);
        this.diagnoseBtn.setText(getResources().getText(R.string.net_diagnosis_button_text_4));
        this.stateText.setText(getResources().getText(R.string.net_diagnosis_state_1));
        this.explainText.setText(getResources().getText(R.string.net_diagnosis_explain));
    }

    private void initTitleView() {
        this.titleText = (TextView) findViewById(R.id.titleText);
        ImageView imageView = (ImageView) findViewById(R.id.title_left_imageView);
        this.titleBack = imageView;
        imageView.setBackgroundResource(R.drawable.common_title_back_selector);
        this.titleBack.setVisibility(0);
        this.titleText.setText(getResources().getText(R.string.net_diagnosis_name));
        this.titleBack.setOnClickListener(new b());
        this.diagnoseBtn = (Button) findViewById(R.id.diagnose);
        this.progressBar = (NetDiagnoseProgressView) findViewById(R.id.progressbar);
        this.stateImage = (ImageView) findViewById(R.id.state_image);
        this.stateText = (TextView) findViewById(R.id.state_text);
        this.explainText = (TextView) findViewById(R.id.explain);
    }

    private void netDiagnose() {
        this.diagnoseBtn.setOnClickListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void overState(int i2) {
        f.f.c(new e(i2), f.f.f19368i).h(new d(i2), f.f.f19370k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCommonData(String str, String str2) {
        JDMtaUtils.sendCommonData(this, str, str2, "", this, NetDiagnosisController.START_NET_DIAGNOSE_PASSWORD, "", "", "InternetDiagnose_HomeMain");
        if (OKLog.D) {
            OKLog.d(NetDiagnosisController.TAG, "\u57cb\u70b9--\u300b event_id=" + str + "  page_id=InternetDiagnose_HomeMain  event_param=" + str2);
        }
    }

    private void setPro() {
        f.C0827f l2 = f.f.l();
        this.tcs = l2;
        f.f.c(new f(l2.a()), f.f.f19368i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startState() {
        int i2 = this.state;
        if (i2 == -1) {
            sendCommonData("InternetDiagnose_ReDiagnose", "");
        } else if (i2 == 0) {
            sendCommonData("InternetDiagnose_Diagnose", "");
        } else if (i2 == 1) {
            sendCommonData("InternetDiagnose_DiagnoseAgain", "");
        }
        this.clickTag = true;
        this.stateImage.setVisibility(4);
        this.progressBar.setVisibility(0);
        this.progressBar.o();
        this.progressBar.n(0);
        this.stateText.setText(getResources().getText(R.string.net_diagnosis_state_2));
        this.explainText.setText("\u6574\u4e2a\u8bca\u65ad\u8fc7\u7a0b\u53ef\u80fd\u9700\u898160\u79d2\n\u8bca\u65ad\u8fc7\u7a0b\u4e2d\u8bf7\u4e0d\u8981\u8fdb\u884c\u5176\u4ed6\u64cd\u4f5c");
        this.diagnoseBtn.setText(getResources().getText(R.string.net_diagnosis_button_text_2));
        setPro();
    }

    @Override // com.jingdong.common.BaseActivity
    public void checkNetwork() {
        super.checkNetwork();
        if (NetUtils.isNetworkAvailable()) {
            ableView();
        } else {
            enableView();
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        super.finish();
        NetDiagnosisController.getController().cancelTasks();
    }

    public int getStringId(int i2) {
        int i3 = R.string.net_diagnosis_report_5;
        switch (i2) {
            case 1:
                return R.string.net_diagnosis_report_1;
            case 2:
                return R.string.net_diagnosis_report_2;
            case 3:
                return R.string.net_diagnosis_report_3;
            case 4:
                return R.string.net_diagnosis_report_4;
            case 5:
            default:
                return i3;
            case 6:
                return R.string.net_diagnosis_report_6;
            case 7:
                return R.string.net_diagnosis_report_7;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (OKLog.D) {
            OKLog.d("netDiag", "oncreate");
        }
        setContentView(R.layout.net_diagnose_activity);
        initTitleView();
        netDiagnose();
        NetUtils.isNetworkAvailable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (OKLog.D) {
            OKLog.d("netDiag", "onResume");
        }
        this.isUseBasePV = false;
        super.onResume();
        JDMtaUtils.sendPagePv(this, this, NetDiagnosisController.START_NET_DIAGNOSE_PASSWORD, "InternetDiagnose_HomeMain", "");
    }
}
