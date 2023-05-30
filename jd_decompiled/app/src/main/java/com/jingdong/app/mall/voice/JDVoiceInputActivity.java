package com.jingdong.app.mall.voice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class JDVoiceInputActivity extends BaseActivity implements View.OnClickListener {
    public static final int LIMIT_TIME = 55;
    public static final int MAX_TIME = 60;
    private static final String TAG = "SpeechRecognizer";
    private String className;
    private LinearLayout errorLayout;
    private String fromType;
    private Handler handler;
    private boolean isDestroy;
    private LinearLayout layout;
    private IntelligentAssistance mIat;
    private ImageView mic;
    private TextView over;
    private TextView time;
    private TimerTask timerTask;
    private TextView title;
    private long voiceInputTime;
    private long voiceInputTotalTime;
    private VoiceWaveView waveView1;
    private VoiceWaveView waveView2;
    private VoiceWaveView waveView3;
    private double x;

    /* renamed from: i  reason: collision with root package name */
    private int f12018i = 0;
    private StringBuilder sb = new StringBuilder();
    private Timer timer = new Timer();
    private boolean isSchedule = false;
    private EventBus eventBus = EventBus.getDefault();
    private byte oldVolume = 0;
    private boolean isNetWorkError = false;
    private boolean isNeedCloseActivity = false;
    private boolean isEvaluateCenter = false;
    private boolean isAddress = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class JDVoiceListener extends JDVoiceImpIntelligent {
        private JDVoiceListener() {
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void changVolum(byte b) {
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u5f53\u524d\u97f3\u91cf\uff1a" + ((int) b));
            }
            if (b == 0) {
                b = 1;
            }
            if (JDVoiceInputActivity.this.oldVolume == b || Math.abs(b - JDVoiceInputActivity.this.oldVolume) <= 2) {
                return;
            }
            if (b >= 15) {
                b = 15;
            }
            JDVoiceInputActivity.this.oldVolume = b;
            if (JDVoiceInputActivity.this.waveView1 != null) {
                VoiceWaveView voiceWaveView = JDVoiceInputActivity.this.waveView1;
                double d = b;
                double d2 = JDVoiceInputActivity.this.x;
                Double.isNaN(d);
                voiceWaveView.setRange((d * d2) + 30.0d);
            }
            if (JDVoiceInputActivity.this.waveView2 != null) {
                VoiceWaveView voiceWaveView2 = JDVoiceInputActivity.this.waveView2;
                double d3 = b;
                double d4 = JDVoiceInputActivity.this.x;
                Double.isNaN(d3);
                voiceWaveView2.setRange(((d3 * d4) + 30.0d) * 0.8d);
            }
            if (JDVoiceInputActivity.this.waveView3 != null) {
                VoiceWaveView voiceWaveView3 = JDVoiceInputActivity.this.waveView3;
                double d5 = b;
                double d6 = JDVoiceInputActivity.this.x;
                Double.isNaN(d5);
                voiceWaveView3.setRange(((d5 * d6) + 30.0d) * 0.7d);
            }
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void initComplete(byte b) {
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u521d\u59cb\u5316\uff1a" + ((int) b));
            }
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void phoneticRecognitionResult(String str, boolean z) {
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u8fd4\u56de\u7ed3\u679conResult\uff1a" + str);
            }
            JDVoiceInputActivity.this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_RESULT, JDVoiceInputActivity.this.checkResult(str), false, JDVoiceInputActivity.this.fromType));
            if (!TextUtils.isEmpty(str)) {
                JDVoiceInputActivity.this.sb.append(str);
            }
            if (z) {
                JDVoiceInputActivity.this.uploadVoiceInputTime();
                JDVoiceInputActivity.this.removeTimerTask();
                JDVoiceInputActivity.this.time.setText("");
                if (JDVoiceInputActivity.this.isNeedCloseActivity) {
                    JDVoiceInputActivity.this.finishActivity();
                } else if (JDVoiceInputActivity.this.isAddress) {
                    if (JDVoiceInputActivity.this.sb.length() > 0) {
                        JDVoiceInputActivity.this.sb.setLength(0);
                        JDVoiceInputActivity.this.finishActivity();
                        return;
                    }
                    JDVoiceInputActivity.this.mic.setVisibility(0);
                    JDVoiceInputActivity.this.over.setEnabled(true);
                    JDVoiceInputActivity.this.over.setVisibility(4);
                    JDVoiceInputActivity.this.title.setText(JDVoiceInputActivity.this.getString(R.string.lib_voice_incomprehension));
                } else {
                    JDVoiceInputActivity.this.mic.setVisibility(0);
                    JDVoiceInputActivity.this.over.setEnabled(true);
                    JDVoiceInputActivity.this.over.setVisibility(4);
                    if (JDVoiceInputActivity.this.sb.length() > 0) {
                        JDVoiceInputActivity.this.sb.setLength(0);
                        JDVoiceInputActivity.this.title.setText(JDVoiceInputActivity.this.getString(R.string.lib_voice_click_speak));
                        return;
                    }
                    JDVoiceInputActivity.this.title.setText(JDVoiceInputActivity.this.getString(R.string.lib_voice_incomprehension));
                }
            }
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void phoneticRecognitionStart() {
            JDVoiceInputActivity jDVoiceInputActivity;
            int i2;
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u5f00\u59cb\u5f55\u97f3\uff01");
            }
            JDVoiceInputActivity.this.voiceInputTime = System.currentTimeMillis();
            JDVoiceInputActivity.this.sb.setLength(0);
            TextView textView = JDVoiceInputActivity.this.title;
            if (JDVoiceInputActivity.this.isAddress) {
                jDVoiceInputActivity = JDVoiceInputActivity.this;
                i2 = R.string.lib_voice_please_speak_settlement;
            } else {
                jDVoiceInputActivity = JDVoiceInputActivity.this;
                i2 = R.string.lib_voice_please_speak;
            }
            textView.setText(jDVoiceInputActivity.getString(i2));
            JDVoiceInputActivity.this.mic.setVisibility(4);
            JDVoiceInputActivity.this.over.setEnabled(true);
            JDVoiceInputActivity.this.over.setVisibility(JDVoiceInputActivity.this.isAddress ? 4 : 0);
            JDVoiceInputActivity.this.waveView1.setRange(30.0d);
            JDVoiceInputActivity.this.waveView2.setRange(20.0d);
            JDVoiceInputActivity.this.waveView3.setRange(10.0d);
            if (!JDVoiceInputActivity.this.isSchedule) {
                JDVoiceInputActivity.this.isSchedule = true;
                JDVoiceInputActivity.this.timer.schedule(JDVoiceInputActivity.this.getTimerTask(), 1000L, 1000L);
            }
            JDVoiceInputActivity.this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_BEGIN, JDVoiceInputActivity.this.fromType));
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void recognitionError(byte b) {
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u53d1\u751f\u9519\u8bef,\u9519\u8bef\u7801\u4e3a\uff1a" + ((int) b));
            }
            JDVoiceInputActivity.this.uploadVoiceInputTime();
            JDVoiceInputActivity.this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_ERROR, JDVoiceInputActivity.this.fromType));
            JDVoiceInputActivity.this.removeTimerTask();
            JDVoiceInputActivity.this.time.setText("");
            JDVoiceInputActivity.this.waveView1.stopVoiceWave();
            JDVoiceInputActivity.this.waveView2.stopVoiceWave();
            JDVoiceInputActivity.this.waveView3.stopVoiceWave();
            if (JDVoiceInputActivity.this.isNeedCloseActivity) {
                JDVoiceInputActivity.this.finishActivity();
            } else if (JDVoiceInputActivity.this.onShowNetWorkErrorView(true)) {
            } else {
                JDVoiceInputActivity.this.layout.setVisibility(0);
                JDVoiceInputActivity.this.errorLayout.setVisibility(8);
                JDVoiceInputActivity.this.mic.setVisibility(0);
                JDVoiceInputActivity.this.over.setEnabled(true);
                JDVoiceInputActivity.this.over.setVisibility(4);
                JDVoiceInputActivity.this.title.setText(JDVoiceInputActivity.this.getString(R.string.lib_voice_incomprehension));
            }
        }

        @Override // com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack
        public void speakStop() {
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(JDVoiceInputActivity.TAG, "\u7ed3\u675f\u5f55\u97f3\uff01");
            }
            JDVoiceInputActivity.this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_END, JDVoiceInputActivity.this.fromType));
            JDVoiceInputActivity.this.removeTimerTask();
            JDVoiceInputActivity.this.time.setText("");
            if (JDVoiceInputActivity.this.isDestroy) {
                return;
            }
            JDVoiceInputActivity.this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_RESULT, "", false, JDVoiceInputActivity.this.fromType));
        }
    }

    static /* synthetic */ int access$508(JDVoiceInputActivity jDVoiceInputActivity) {
        int i2 = jDVoiceInputActivity.f12018i;
        jDVoiceInputActivity.f12018i = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String checkResult(String str) {
        return str != null ? str.replace("\u3002", "") : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        this.voiceInputTotalTime = System.currentTimeMillis() - this.voiceInputTotalTime;
        if (this.isEvaluateCenter) {
            JDMtaUtils.sendExposureData(this, getClass(), "Evaluate_CommentEdit", "", "CommentsShare_LayerTimeExpo", this.voiceInputTotalTime + "", "", "", "");
        }
        finish();
        overridePendingTransition(0, R.anim.jd_dialog_bottom_exit);
    }

    private void initConfig() {
        if (OKLog.D) {
            OKLog.d(TAG, "\u8bed\u97f3\u8f93\u5165\u63a7\u4ef6\u542f\u52a8\u6210\u529f\uff01");
        }
        IntelligentAssistance intelligentAssistance = IntelligentAssistance.getInstance();
        this.mIat = intelligentAssistance;
        intelligentAssistance.initSdk(this.isAddress ? JDVoiceConstant.ADDRESS : JDVoiceConstant.EVALUATE, new JDVoiceListener());
        this.voiceInputTotalTime = System.currentTimeMillis();
        double dip2px = (double) (DPIUtil.dip2px(60.0f) - 30);
        Double.isNaN(dip2px);
        this.x = dip2px / 15.0d;
    }

    private void initView() {
        this.layout = (LinearLayout) findViewById(R.id.lib_voice_layout);
        View findViewById = findViewById(R.id.lib_voice_blank);
        this.title = (TextView) findViewById(R.id.lib_voice_title);
        this.over = (TextView) findViewById(R.id.lib_voice_over);
        this.mic = (ImageView) findViewById(R.id.lib_voice_mic);
        this.time = (TextView) findViewById(R.id.lib_voice_time);
        this.waveView1 = (VoiceWaveView) findViewById(R.id.lib_voice_voiceWave1);
        this.waveView2 = (VoiceWaveView) findViewById(R.id.lib_voice_voiceWave2);
        this.waveView3 = (VoiceWaveView) findViewById(R.id.lib_voice_voiceWave3);
        this.errorLayout = (LinearLayout) findViewById(R.id.lib_voice_error_layout);
        TextView textView = (TextView) findViewById(R.id.lib_voice_error_btn);
        ((TextView) findViewById(R.id.lib_voice_error_txt1)).setText(getString(R.string.lib_voice_network_failed));
        ((TextView) findViewById(R.id.lib_voice_error_txt2)).setText(getString(R.string.lib_voice_network_please_check));
        textView.setText(getString(R.string.lib_voice_network_please_setting));
        ((ImageView) findViewById(R.id.lib_voice_close)).setOnClickListener(this);
        this.over.setOnClickListener(this);
        this.mic.setOnClickListener(this);
        findViewById.setOnClickListener(this);
        ((ImageView) findViewById(R.id.lib_voice_error_close)).setOnClickListener(this);
        textView.setOnClickListener(this);
        this.handler = new Handler() { // from class: com.jingdong.app.mall.voice.JDVoiceInputActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (!JDVoiceInputActivity.this.isDestroy && message.what == 60) {
                    if (!NetUtils.isNetworkAvailable() && !JDVoiceInputActivity.this.isNetWorkError) {
                        JDVoiceInputActivity.this.isDestroy = true;
                        JDVoiceInputActivity.this.onShowNetWorkErrorView(false);
                    }
                    if (JDVoiceInputActivity.this.time != null) {
                        JDVoiceInputActivity.this.time.setText(String.valueOf(JDVoiceInputActivity.this.f12018i));
                    }
                    if (JDVoiceInputActivity.this.f12018i < 55 || JDVoiceInputActivity.this.f12018i >= 60) {
                        if (JDVoiceInputActivity.this.f12018i >= 60) {
                            JDVoiceInputActivity.this.title.setText(JDVoiceInputActivity.this.getString(R.string.lib_voice_progress));
                            JDVoiceInputActivity.this.over.setEnabled(false);
                            if (JDVoiceInputActivity.this.mIat != null) {
                                if (OKLog.D) {
                                    OKLog.d(JDVoiceInputActivity.TAG, "60\u79d2\u8d85\u65f6\u81ea\u52a8\u505c\u6b62");
                                }
                                JDVoiceInputActivity.this.mIat.stopSpeak();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    TextView textView2 = JDVoiceInputActivity.this.title;
                    JDVoiceInputActivity jDVoiceInputActivity = JDVoiceInputActivity.this;
                    textView2.setText(jDVoiceInputActivity.getString(R.string.lib_voice_less, new Object[]{Integer.valueOf(60 - jDVoiceInputActivity.f12018i)}));
                }
            }
        };
        if (NetUtils.isNetworkAvailable()) {
            this.layout.setVisibility(0);
            this.errorLayout.setVisibility(8);
            this.isNeedCloseActivity = false;
            startJDVoiceInput();
            return;
        }
        onShowNetWorkErrorView(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onShowNetWorkErrorView(boolean z) {
        if (NetUtils.isNetworkAvailable() || this.isNetWorkError) {
            return false;
        }
        this.isNetWorkError = true;
        if (!z) {
            removeTimerTask();
            this.time.setText("");
            this.waveView1.stopVoiceWave();
            this.waveView2.stopVoiceWave();
            this.waveView3.stopVoiceWave();
        }
        this.layout.setVisibility(8);
        getWindow().addFlags(2);
        getWindow().setDimAmount(0.5f);
        this.errorLayout.setVisibility(0);
        return true;
    }

    private void startJDVoiceInput() {
        if (PermissionHelper.hasPermission(this, "android.permission.RECORD_AUDIO")) {
            IntelligentAssistance intelligentAssistance = this.mIat;
            if (intelligentAssistance != null) {
                intelligentAssistance.startSpeak();
                return;
            }
            return;
        }
        PermissionHelper.requestPermission(this, PermissionHelper.generateBundle("voice", JDVoiceInputActivity.class.getSimpleName(), "startJDVoiceInput"), "android.permission.RECORD_AUDIO", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.app.mall.voice.JDVoiceInputActivity.3
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (JDVoiceInputActivity.this.mIat != null) {
                    JDVoiceInputActivity.this.mIat.startSpeak();
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        }, "\u9ea6\u514b\u98ce", "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u9ea6\u514b\u98ce\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u53ef\u4ee5\u6b63\u5e38\u4f7f\u7528\u8bed\u97f3\u8f93\u5165\u529f\u80fd");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadVoiceInputTime() {
        this.voiceInputTime = System.currentTimeMillis() - this.voiceInputTime;
        if (this.isEvaluateCenter) {
            JDMtaUtils.onClick(this, "CommentsShare_ContinuityVoiceTimeExpo", this.className, this.voiceInputTime + "");
        }
    }

    @Override // com.jingdong.common.BaseActivity
    public void checkNetwork() {
    }

    public TimerTask getTimerTask() {
        TimerTask timerTask = new TimerTask() { // from class: com.jingdong.app.mall.voice.JDVoiceInputActivity.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (JDVoiceInputActivity.this.f12018i >= 60) {
                    JDVoiceInputActivity.this.removeTimerTask();
                    return;
                }
                JDVoiceInputActivity.access$508(JDVoiceInputActivity.this);
                Message obtain = Message.obtain();
                obtain.what = 60;
                JDVoiceInputActivity.this.handler.sendMessage(obtain);
            }
        };
        this.timerTask = timerTask;
        return timerTask;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IntelligentAssistance intelligentAssistance;
        int id = view.getId();
        if (id != R.id.lib_voice_blank && id != R.id.lib_voice_close) {
            if (id == R.id.lib_voice_error_close) {
                finishActivity();
                return;
            } else if (id == R.id.lib_voice_mic) {
                startJDVoiceInput();
                return;
            } else if (id == R.id.lib_voice_over) {
                this.title.setText(getString(R.string.lib_voice_progress));
                this.over.setEnabled(false);
                this.isNeedCloseActivity = true;
                removeTimerTask();
                this.time.setText("");
                this.waveView1.stopVoiceWave();
                this.waveView2.stopVoiceWave();
                this.waveView3.stopVoiceWave();
                if (this.mIat != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u4e2d\u65ad\u8bed\u97f3\u542c\u5199");
                    }
                    this.mIat.stopSpeak();
                    return;
                }
                if (NetUtils.isNetworkAvailable()) {
                    uploadVoiceInputTime();
                }
                finishActivity();
                return;
            } else if (id == R.id.lib_voice_error_btn) {
                finishActivity();
                if (this.isNetWorkError) {
                    startActivity(new Intent("android.settings.SETTINGS"));
                    return;
                }
                return;
            } else {
                return;
            }
        }
        if (NetUtils.isNetworkAvailable() && (intelligentAssistance = this.mIat) != null && intelligentAssistance.isListening()) {
            uploadVoiceInputTime();
        }
        finishActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra("fromType");
        this.fromType = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            this.fromType = "";
        }
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_CREATE, this.fromType));
        super.onCreate(bundle);
        boolean z = false;
        overridePendingTransition(R.anim.jd_dialog_bottom_enter, 0);
        this.statusBarTintEnable = true;
        this.statusBarTransparentEnable = true;
        setContentView(R.layout.lib_voice_activity_input);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        String stringExtra2 = getIntent().getStringExtra("className");
        this.className = stringExtra2;
        this.isEvaluateCenter = stringExtra2 != null && stringExtra2.contains("evaluatecenter");
        String str = this.className;
        if (str != null && str.contains(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)) {
            z = true;
        }
        this.isAddress = z;
        initConfig();
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        IntelligentAssistance intelligentAssistance = this.mIat;
        if (intelligentAssistance != null) {
            intelligentAssistance.cancelSpeak();
        }
        this.isDestroy = true;
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_DESTROY, this.fromType));
        super.onDestroy();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            finishActivity();
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public void removeTimerTask() {
        TimerTask timerTask;
        if (!this.isSchedule || (timerTask = this.timerTask) == null) {
            return;
        }
        this.isSchedule = false;
        timerTask.cancel();
        this.f12018i = 0;
    }
}
