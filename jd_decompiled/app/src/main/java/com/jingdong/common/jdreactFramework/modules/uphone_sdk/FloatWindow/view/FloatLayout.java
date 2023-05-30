package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.service.FloatMonkService;

/* loaded from: classes5.dex */
public class FloatLayout extends FrameLayout {
    private long endTime;
    final Handler handler;
    private boolean isclick;
    public TestCallBack mCallBack;
    private Context mContext;
    private final ImageView mFloatView;
    private final TextView mTextView;
    private float mTouchStartX;
    private float mTouchStartY;
    private final WindowManager mWindowManager;
    private WindowManager.LayoutParams mWmParams;
    private int showTime;
    private long startTime;

    public FloatLayout(Context context) {
        this(context, null);
        this.mContext = context;
    }

    static /* synthetic */ int access$008(FloatLayout floatLayout) {
        int i2 = floatLayout.showTime;
        floatLayout.showTime = i2 + 1;
        return i2;
    }

    public static String secToTime(int i2) {
        if (i2 <= 0) {
            return "00:00";
        }
        int i3 = i2 / 60;
        if (i3 < 60) {
            return unitFormat(i3) + ":" + unitFormat(i2 % 60);
        }
        return "60:00";
    }

    public static String unitFormat(int i2) {
        if (i2 >= 0 && i2 < 10) {
            return "0" + Integer.toString(i2);
        }
        return "" + i2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            this.mTouchStartX = motionEvent.getX();
            this.mTouchStartY = motionEvent.getY();
        } else if (action == 1) {
            this.endTime = System.currentTimeMillis();
            if (r0 - this.startTime > 100.0d) {
                this.isclick = false;
            } else {
                this.isclick = true;
            }
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (Math.abs(this.mTouchStartX - x) > 3.0f && Math.abs(this.mTouchStartY - y) > 3.0f) {
                WindowManager.LayoutParams layoutParams = this.mWmParams;
                layoutParams.x = (int) (rawX - this.mTouchStartX);
                layoutParams.y = (int) (rawY - this.mTouchStartY);
                this.mWindowManager.updateViewLayout(this, layoutParams);
                return false;
            }
        }
        if (this.isclick) {
            ((FloatMonkService) this.mContext).click();
            this.isclick = false;
        }
        return true;
    }

    public void setParams(WindowManager.LayoutParams layoutParams) {
        this.mWmParams = layoutParams;
    }

    public void setTime(int i2) {
        this.showTime = i2;
        this.mTextView.setText(secToTime(i2));
        if (i2 > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1), 1000L);
        }
    }

    public FloatLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view.FloatLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                FloatLayout.access$008(FloatLayout.this);
                if (Build.VERSION.SDK_INT >= 19 ? FloatLayout.this.isAttachedToWindow() : true) {
                    FloatLayout.this.mTextView.setText(FloatLayout.secToTime(FloatLayout.this.showTime));
                    FloatLayout.this.handler.sendMessageDelayed(FloatLayout.this.handler.obtainMessage(1), 1000L);
                }
            }
        };
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        LayoutInflater.from(context).inflate(R.layout.float_littlemonk_layout, this);
        this.mFloatView = (ImageView) findViewById(R.id.float_id);
        this.mTextView = (TextView) findViewById(R.id.float_text);
    }
}
