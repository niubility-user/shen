package com.jd.lib.un.voice.view;

import android.app.Activity;
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
import androidx.annotation.Nullable;
import com.jd.lib.un.voice.R;
import com.jd.lib.un.voice.asr.OnSpeechListener;
import com.jd.lib.un.voice.asr.UnAsrHelper;
import com.jd.lib.un.voice.asr.UnAsrType;
import com.jingdong.app.mall.voice.JDVoiceInputEvent;
import com.jingdong.app.mall.voice.VoiceWaveView;
import com.jingdong.common.DpiUtil;
import de.greenrobot.event.EventBus;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes16.dex */
public class UnVoiceInputActivity extends Activity implements View.OnClickListener, OnSpeechListener {
    public static final int LIMIT_TIME = 55;
    public static final int MAX_TIME = 60;
    private static final String TAG = "UnVoiceInputActivity";
    private String className;
    private LinearLayout errorLayout;
    private String fromType;
    private Handler handler;
    private boolean isDestroy;
    private boolean isShot;
    private LinearLayout layout;
    private ImageView mic;
    private TextView over;
    private TextView time;
    private TimerTask timerTask;
    private TextView title;
    private UnAsrHelper unAsrHelper;
    private long voiceInputTime;
    private long voiceInputTotalTime;
    private VoiceWaveView waveView1;
    private VoiceWaveView waveView2;
    private VoiceWaveView waveView3;
    private double x;

    /* renamed from: i  reason: collision with root package name */
    private int f5878i = 0;
    private StringBuilder sb = new StringBuilder();
    private Timer timer = new Timer();
    private boolean isSchedule = false;
    private EventBus eventBus = EventBus.getDefault();
    private int oldVolume = 0;
    private boolean isNetWorkError = false;
    private boolean isNeedCloseActivity = false;

    static /* synthetic */ int access$208(UnVoiceInputActivity unVoiceInputActivity) {
        int i2 = unVoiceInputActivity.f5878i;
        unVoiceInputActivity.f5878i = i2 + 1;
        return i2;
    }

    private String checkResult(String str) {
        return str != null ? str.replace("\u3002", "") : "";
    }

    private void finishActivity() {
        this.voiceInputTotalTime = System.currentTimeMillis() - this.voiceInputTotalTime;
        this.unAsrHelper.stop();
        finish();
        overridePendingTransition(0, R.anim.jd_dialog_bottom_exit);
    }

    private void initConfig() {
        this.unAsrHelper = new UnAsrHelper(this).setArsType(UnAsrType.LONG).setOnSpeechListener(this);
        this.voiceInputTotalTime = System.currentTimeMillis();
        double dip2px = (double) (DpiUtil.dip2px(this, 60.0f) - 30);
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
        this.handler = new Handler() { // from class: com.jd.lib.un.voice.view.UnVoiceInputActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (!UnVoiceInputActivity.this.isDestroy && message.what == 60) {
                    if (UnVoiceInputActivity.this.time != null) {
                        UnVoiceInputActivity.this.time.setText(String.valueOf(UnVoiceInputActivity.this.f5878i));
                    }
                    if (UnVoiceInputActivity.this.f5878i < 55 || UnVoiceInputActivity.this.f5878i >= 60) {
                        if (UnVoiceInputActivity.this.f5878i >= 60) {
                            UnVoiceInputActivity.this.title.setText(UnVoiceInputActivity.this.getString(R.string.lib_voice_progress));
                            UnVoiceInputActivity.this.over.setEnabled(false);
                            if (UnVoiceInputActivity.this.unAsrHelper != null) {
                                UnVoiceInputActivity.this.unAsrHelper.stop();
                                UnVoiceInputActivity.this.unAsrHelper.cancel();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    TextView textView2 = UnVoiceInputActivity.this.title;
                    UnVoiceInputActivity unVoiceInputActivity = UnVoiceInputActivity.this;
                    textView2.setText(unVoiceInputActivity.getString(R.string.lib_voice_less, new Object[]{Integer.valueOf(60 - unVoiceInputActivity.f5878i)}));
                }
            }
        };
        this.layout.setVisibility(0);
        this.errorLayout.setVisibility(8);
        this.isNeedCloseActivity = false;
        startJDVoiceInput();
    }

    private boolean onShowNetWorkErrorView(boolean z) {
        this.layout.setVisibility(8);
        getWindow().addFlags(2);
        getWindow().setDimAmount(0.5f);
        this.errorLayout.setVisibility(0);
        return true;
    }

    private void startJDVoiceInput() {
        UnAsrHelper unAsrHelper = this.unAsrHelper;
        if (unAsrHelper != null) {
            unAsrHelper.start();
        }
        if (this.isDestroy) {
            return;
        }
        this.voiceInputTime = System.currentTimeMillis();
        this.sb.setLength(0);
        this.title.setText(getString(this.isShot ? R.string.lib_voice_please_speak_settlement : R.string.lib_voice_please_speak));
        this.mic.setVisibility(4);
        this.over.setEnabled(true);
        this.over.setVisibility(this.isShot ? 4 : 0);
        this.waveView1.setRange(30.0d);
        this.waveView2.setRange(20.0d);
        this.waveView3.setRange(10.0d);
        if (!this.isSchedule) {
            this.isSchedule = true;
            this.timer.schedule(getTimerTask(), 1000L, 1000L);
        }
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_BEGIN, this.fromType));
    }

    private void uploadVoiceInputTime() {
        this.voiceInputTime = System.currentTimeMillis() - this.voiceInputTime;
    }

    public TimerTask getTimerTask() {
        TimerTask timerTask = new TimerTask() { // from class: com.jd.lib.un.voice.view.UnVoiceInputActivity.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (UnVoiceInputActivity.this.f5878i >= 60) {
                    UnVoiceInputActivity.this.removeTimerTask();
                    return;
                }
                UnVoiceInputActivity.access$208(UnVoiceInputActivity.this);
                Message obtain = Message.obtain();
                obtain.what = 60;
                UnVoiceInputActivity.this.handler.sendMessage(obtain);
            }
        };
        this.timerTask = timerTask;
        return timerTask;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
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
                UnAsrHelper unAsrHelper = this.unAsrHelper;
                if (unAsrHelper != null) {
                    unAsrHelper.stop();
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
        finishActivity();
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        this.fromType = getIntent().getStringExtra("fromType");
        this.isShot = getIntent().getBooleanExtra("isShot", false);
        if (TextUtils.isEmpty(this.fromType)) {
            this.fromType = "";
        }
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_CREATE, this.fromType));
        super.onCreate(bundle);
        overridePendingTransition(R.anim.jd_dialog_bottom_enter, 0);
        setContentView(R.layout.un_lib_voice_activity_input);
        initConfig();
        initView();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.unAsrHelper.cancel();
        this.isDestroy = true;
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_DESTROY, this.fromType));
        super.onDestroy();
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onEnd() {
        uploadVoiceInputTime();
        removeTimerTask();
        this.time.setText("");
        this.waveView1.stopVoiceWave();
        this.waveView2.stopVoiceWave();
        this.waveView3.stopVoiceWave();
        if (this.isNeedCloseActivity) {
            finishActivity();
        } else if (this.isShot) {
            if (this.sb.length() > 0) {
                this.sb.setLength(0);
                finishActivity();
                return;
            }
            this.mic.setVisibility(0);
            this.over.setEnabled(true);
            this.over.setVisibility(4);
            this.title.setText(getString(R.string.lib_voice_incomprehension));
        } else {
            this.mic.setVisibility(0);
            this.over.setEnabled(true);
            this.over.setVisibility(4);
            if (this.sb.length() > 0) {
                this.sb.setLength(0);
                this.title.setText(getString(R.string.lib_voice_click_speak));
                return;
            }
            this.title.setText(getString(R.string.lib_voice_incomprehension));
        }
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onError(int i2, String str) {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            finishActivity();
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onPrepared() {
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onResult(String str) {
        String str2 = "result: " + str;
        if (this.isDestroy) {
            return;
        }
        this.eventBus.post(new JDVoiceInputEvent(JDVoiceInputEvent.EVENT_TYPE_RESULT, checkResult(str), false, this.fromType));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.sb.append(str);
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onStartSpeech() {
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onTemp(String str) {
        String str2 = "temp: " + str;
    }

    @Override // com.jd.lib.un.voice.asr.OnSpeechListener
    public void onVolume(int i2) {
        if (this.isDestroy) {
            return;
        }
        if (i2 == 0) {
            i2 = 1;
        }
        int i3 = this.oldVolume;
        if (i3 == i2 || Math.abs(i2 - i3) <= 2) {
            return;
        }
        if (i2 >= 15) {
            i2 = 15;
        }
        this.oldVolume = i2;
        VoiceWaveView voiceWaveView = this.waveView1;
        if (voiceWaveView != null) {
            double d = i2;
            double d2 = this.x;
            Double.isNaN(d);
            voiceWaveView.setRange((d * d2) + 30.0d);
        }
        VoiceWaveView voiceWaveView2 = this.waveView2;
        if (voiceWaveView2 != null) {
            double d3 = i2;
            double d4 = this.x;
            Double.isNaN(d3);
            voiceWaveView2.setRange(((d3 * d4) + 30.0d) * 0.8d);
        }
        VoiceWaveView voiceWaveView3 = this.waveView3;
        if (voiceWaveView3 != null) {
            double d5 = i2;
            double d6 = this.x;
            Double.isNaN(d5);
            voiceWaveView3.setRange(((d5 * d6) + 30.0d) * 0.7d);
        }
    }

    public void removeTimerTask() {
        TimerTask timerTask;
        if (!this.isSchedule || (timerTask = this.timerTask) == null) {
            return;
        }
        this.isSchedule = false;
        timerTask.cancel();
        this.f5878i = 0;
    }
}
