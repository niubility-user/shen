package com.jingdong.app.mall.voice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.jd.lib.un.voice.R;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class VoiceWaveView extends SurfaceView implements SurfaceHolder.Callback, LifecycleObserver {
    private float centerY;
    private ScheduledExecutorService executorService;
    private int lineAlpha;
    private int lineColor;
    private float lineWidth;
    private double offsetSpace;
    private double offsetX;
    private Paint paint;
    private float sinPeriod;
    private double sinRange;
    private boolean surfaceAlive;
    private int taskPeriod;
    private int viewWidth;

    public VoiceWaveView(Context context) {
        super(context);
        this.sinRange = 80.0d;
        this.sinPeriod = 1.0f;
        this.offsetSpace = 0.1d;
        this.taskPeriod = 10;
        this.offsetX = 1.0d;
        this.surfaceAlive = false;
        this.executorService = null;
        this.lineColor = -1;
        this.lineWidth = 2.0f;
        this.lineAlpha = 255;
        this.sinRange = 10.0d;
        this.sinPeriod = 10.0f;
        this.offsetSpace = 0.10000000149011612d;
        this.taskPeriod = 10;
        initConfig(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double degreeToRad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    private Runnable getWaveTask() {
        return new Runnable() { // from class: com.jingdong.app.mall.voice.VoiceWaveView.2
            @Override // java.lang.Runnable
            public void run() {
                if (VoiceWaveView.this.sinRange <= 0.0d || !VoiceWaveView.this.surfaceAlive) {
                    return;
                }
                Canvas lockCanvas = VoiceWaveView.this.getHolder().lockCanvas();
                if (lockCanvas != null) {
                    lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                }
                for (float f2 = 0.0f; VoiceWaveView.this.surfaceAlive && f2 < VoiceWaveView.this.viewWidth; f2 += 1.0f) {
                    double d = VoiceWaveView.this.centerY;
                    double d2 = VoiceWaveView.this.sinRange;
                    VoiceWaveView voiceWaveView = VoiceWaveView.this;
                    Double.isNaN(d);
                    float sin = (float) (d - (d2 * Math.sin((voiceWaveView.degreeToRad(voiceWaveView.sinPeriod * f2) - VoiceWaveView.this.offsetX) - 10.0d)));
                    if (VoiceWaveView.this.surfaceAlive && lockCanvas != null) {
                        lockCanvas.drawPoint(f2, sin, VoiceWaveView.this.paint);
                    }
                }
                try {
                    VoiceWaveView.this.getHolder().unlockCanvasAndPost(lockCanvas);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                VoiceWaveView.this.offsetX += VoiceWaveView.this.offsetSpace;
                if (VoiceWaveView.this.offsetX >= VoiceWaveView.this.viewWidth) {
                    VoiceWaveView.this.offsetX = 0.0d;
                }
            }
        };
    }

    private void initConfig(Context context) {
        if (context instanceof LifecycleOwner) {
            ((LifecycleOwner) context).getLifecycle().addObserver(this);
        }
        getHolder().addCallback(this);
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
    }

    public void setRange(double d) {
        double d2 = this.lineWidth / 2.0f;
        Double.isNaN(d2);
        this.sinRange = d - d2;
        ScheduledExecutorService scheduledExecutorService = this.executorService;
        boolean z = scheduledExecutorService == null || scheduledExecutorService.isShutdown();
        if (this.surfaceAlive && z) {
            try {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.CallerRunsPolicy() { // from class: com.jingdong.app.mall.voice.VoiceWaveView.3
                    @Override // java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy, java.util.concurrent.RejectedExecutionHandler
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        super.rejectedExecution(runnable, threadPoolExecutor);
                    }
                });
                this.executorService = scheduledThreadPoolExecutor;
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(getWaveTask(), 10L, this.taskPeriod, TimeUnit.MILLISECONDS);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stopVoiceWave() {
        this.sinRange = 0.0d;
        try {
            this.executorService.shutdown();
            if (this.surfaceAlive && getHolder().getSurface().isValid()) {
                Canvas lockCanvas = getHolder().lockCanvas();
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                getHolder().unlockCanvasAndPost(lockCanvas);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceAlive = true;
        if (this.paint == null) {
            Paint paint = new Paint();
            this.paint = paint;
            paint.setColor(this.lineColor);
            this.paint.setStrokeWidth(this.lineWidth);
            this.paint.setAlpha(this.lineAlpha);
            this.paint.setAntiAlias(true);
        }
        this.viewWidth = getWidth();
        this.centerY = getHeight() / 2.0f;
        if (this.executorService == null) {
            try {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.CallerRunsPolicy() { // from class: com.jingdong.app.mall.voice.VoiceWaveView.1
                    @Override // java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy, java.util.concurrent.RejectedExecutionHandler
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        super.rejectedExecution(runnable, threadPoolExecutor);
                    }
                });
                this.executorService = scheduledThreadPoolExecutor;
                scheduledThreadPoolExecutor.scheduleWithFixedDelay(getWaveTask(), 10L, this.taskPeriod, TimeUnit.MILLISECONDS);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.surfaceAlive = false;
    }

    public VoiceWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes;
        this.sinRange = 80.0d;
        this.sinPeriod = 1.0f;
        this.offsetSpace = 0.1d;
        this.taskPeriod = 10;
        this.offsetX = 1.0d;
        this.surfaceAlive = false;
        this.executorService = null;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VoiceWaveView)) != null) {
            this.lineColor = obtainStyledAttributes.getColor(R.styleable.VoiceWaveView_line_color_v, -1);
            this.lineWidth = obtainStyledAttributes.getDimension(R.styleable.VoiceWaveView_line_width_v, 2.0f);
            this.lineAlpha = obtainStyledAttributes.getInt(R.styleable.VoiceWaveView_line_alpha_v, 255);
            this.sinRange = obtainStyledAttributes.getInt(R.styleable.VoiceWaveView_range_v, 10);
            this.sinPeriod = obtainStyledAttributes.getFloat(R.styleable.VoiceWaveView_period_sin_v, 10.0f);
            this.offsetSpace = obtainStyledAttributes.getFloat(R.styleable.VoiceWaveView_period_x_v, 0.1f);
            this.taskPeriod = obtainStyledAttributes.getInt(R.styleable.VoiceWaveView_period_task_v, 10);
            obtainStyledAttributes.recycle();
        }
        initConfig(context);
    }
}
