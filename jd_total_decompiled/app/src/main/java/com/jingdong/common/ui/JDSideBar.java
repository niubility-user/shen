package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.jd.base.history.widget.R;
import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.jdsdk.auraSetting.AuraConstants;

/* loaded from: classes6.dex */
public class JDSideBar extends View {
    private static final String TAG = "SideBar";
    private int choose;
    private int chooseForPaint;
    private boolean isSlideFuncUsed;
    private Context mContext;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint;
    public String[] sideBarAlphabetArray;

    /* loaded from: classes6.dex */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str);
    }

    public JDSideBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.sideBarAlphabetArray = new String[]{"A", "B", "C", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "E", "F", "G", DYConstants.LETTER_H, "I", "J", "K", "L", "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, IShareAdapter.SHARE_ACTION_OPEN, IShareAdapter.SHARE_ACTION_PANE, "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.choose = -1;
        this.chooseForPaint = -1;
        this.paint = new Paint();
        this.isSlideFuncUsed = false;
        this.mContext = context;
    }

    private void actionUp() {
        this.choose = -1;
        invalidate();
        TextView textView = this.mTextDialog;
        if (textView != null) {
            textView.setVisibility(4);
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TextView textView;
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        float rawX = motionEvent.getRawX();
        int left = getLeft();
        if (UnLog.D) {
            UnLog.d(TAG, "abc dispatchTouchEvent() -->> x = " + x);
            UnLog.d(TAG, "abc dispatchTouchEvent() -->> rawX = " + rawX);
            UnLog.d(TAG, "abc dispatchTouchEvent() -->> left = " + left);
        }
        int i2 = this.choose;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.onTouchingLetterChangedListener;
        int height = (int) ((y / getHeight()) * this.sideBarAlphabetArray.length);
        if (UnLog.D) {
            UnLog.d(TAG, "dispatchTouchEvent() -->> c = " + height);
        }
        if (rawX - x <= 10.0f) {
            actionUp();
            return true;
        }
        if (action != 1) {
            if (this.isSlideFuncUsed) {
                setBackgroundResource(R.drawable.sidebar_background);
            }
            if (i2 != height && height >= 0) {
                String[] strArr = this.sideBarAlphabetArray;
                if (height < strArr.length) {
                    if (onTouchingLetterChangedListener != null) {
                        onTouchingLetterChangedListener.onTouchingLetterChanged(strArr[height]);
                    }
                    if (this.isSlideFuncUsed && (textView = this.mTextDialog) != null) {
                        textView.setText(this.sideBarAlphabetArray[height]);
                        this.mTextDialog.setVisibility(0);
                    }
                    this.choose = height;
                    this.chooseForPaint = height;
                    invalidate();
                }
            }
        } else {
            actionUp();
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        float length = height / this.sideBarAlphabetArray.length;
        if (UnLog.D) {
            UnLog.d(TAG, "onDraw() -->> height = " + height);
            UnLog.d(TAG, "onDraw() -->> width = " + width);
            UnLog.d(TAG, "onDraw() -->> singleHeight = " + length);
            UnLog.d(TAG, "onDraw() -->> sideBarAlphabetArray.length = " + this.sideBarAlphabetArray.length);
        }
        for (int i2 = 0; i2 < this.sideBarAlphabetArray.length; i2++) {
            this.paint.setColor(Color.parseColor("#252525"));
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(DpiUtil.dip2px(this.mContext, 11.0f));
            if (i2 == this.chooseForPaint) {
                this.paint.setColor(Color.parseColor("#f15353"));
                this.paint.setFakeBoldText(true);
            }
            float measureText = (width / 2) - (this.paint.measureText(this.sideBarAlphabetArray[i2]) / 2.0f);
            float measureText2 = (i2 * length) + (length / 2.0f) + (this.paint.measureText(this.sideBarAlphabetArray[i2]) / 2.0f);
            if (UnLog.D) {
                UnLog.d(TAG, "onDraw() -->> for i = " + i2 + " yPos = " + measureText2);
            }
            canvas.drawText(this.sideBarAlphabetArray[i2], measureText, measureText2, this.paint);
            this.paint.reset();
        }
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public void setSlideFuncUsed(boolean z) {
        this.isSlideFuncUsed = z;
    }

    public void setTextView(TextView textView) {
        this.mTextDialog = textView;
    }

    public JDSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.sideBarAlphabetArray = new String[]{"A", "B", "C", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "E", "F", "G", DYConstants.LETTER_H, "I", "J", "K", "L", "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, IShareAdapter.SHARE_ACTION_OPEN, IShareAdapter.SHARE_ACTION_PANE, "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.choose = -1;
        this.chooseForPaint = -1;
        this.paint = new Paint();
        this.isSlideFuncUsed = false;
        this.mContext = context;
    }

    public JDSideBar(Context context) {
        super(context);
        this.sideBarAlphabetArray = new String[]{"A", "B", "C", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "E", "F", "G", DYConstants.LETTER_H, "I", "J", "K", "L", "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, IShareAdapter.SHARE_ACTION_OPEN, IShareAdapter.SHARE_ACTION_PANE, "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.choose = -1;
        this.chooseForPaint = -1;
        this.paint = new Paint();
        this.isSlideFuncUsed = false;
        this.mContext = context;
    }
}
