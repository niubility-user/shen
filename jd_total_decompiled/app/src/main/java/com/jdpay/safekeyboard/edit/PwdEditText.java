package com.jdpay.safekeyboard.edit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.safekeyboard.R;
import com.jdpay.safekeyboard.keyboard.AbstractKeyboard;
import com.jdpay.safekeyboard.keyboard.FinishCallback;
import com.jdpay.safekeyboard.keyboard.KeyboardController;
import com.jdpay.system.SystemInfo;

/* loaded from: classes18.dex */
public class PwdEditText extends AppCompatEditText {
    public static int MODE;
    private FinishCallback finishCallback;
    private KeyboardController keyboardControler;
    private View layout_root;
    private int mBackgroundColor;
    private int mBgCorner;
    private float mBgCornerWidth;
    private int mBoardColor;
    private final AbstractKeyboard.CallBack mCallBack;
    private int mHeight;
    private StringBuffer mNumber;
    private Paint mPaint;
    private int mPasswordRadius;
    private int mPasswordSize;
    private int mWidth;
    private int passwordNumber;

    public PwdEditText(Context context) {
        super(context);
        this.passwordNumber = 6;
        this.mPasswordRadius = 6;
        this.mBgCornerWidth = 2.0f;
        this.mNumber = new StringBuffer();
        this.mBoardColor = getResources().getColor(R.color.jdpay_edit_keyboard_gray);
        this.mBackgroundColor = getResources().getColor(R.color.jdpay_edit_keyboard_bg);
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.PwdEditText.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                PwdEditText.this.deleteAll();
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (PwdEditText.this.layout_root != null) {
                    PwdEditText pwdEditText = PwdEditText.this;
                    pwdEditText.scrollTo(0, pwdEditText.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                PwdEditText.this.refreshAll(str);
                if (PwdEditText.this.keyboardControler.getInputLength() != PwdEditText.this.passwordNumber || PwdEditText.this.finishCallback == null) {
                    return;
                }
                PwdEditText.this.finishCallback.onFinish(new String(PwdEditText.this.keyboardControler.getCryptoData().getResultString()));
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                PwdEditText.this.refreshAll("");
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onFinish(str);
                }
            }
        };
        init();
        initPaint();
    }

    private void check() {
        post(new Runnable() { // from class: com.jdpay.safekeyboard.edit.PwdEditText.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PwdEditText.this.layout_root = ((ViewGroup) ContextHelper.getActivity(PwdEditText.this.getContext()).findViewById(16908290)).getChildAt(0);
                    float bottom = PwdEditText.this.layout_root.getBottom();
                    float keyboardHeight = PwdEditText.this.keyboardControler.getKeyboardHeight();
                    PwdEditText.this.getLocationInWindow(new int[2]);
                    if (bottom - keyboardHeight <= r3[1] + (PwdEditText.this.mHeight / 2)) {
                        PwdEditText pwdEditText = PwdEditText.this;
                        pwdEditText.scrollTo((int) (((r3[1] + 200) - bottom) + keyboardHeight), pwdEditText.layout_root);
                    }
                } catch (Exception e2) {
                    e2.getLocalizedMessage();
                }
            }
        });
    }

    private void drawBackGround(Canvas canvas) {
        this.mPaint.setColor(this.mBackgroundColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(((getWidth() / 2) - (this.mWidth / 2)) + (this.mBgCornerWidth / 2.0f), ((getHeight() / 2) - (this.mHeight / 2)) + (this.mBgCornerWidth / 2.0f), ((getWidth() / 2) + (this.mWidth / 2)) - (this.mBgCornerWidth / 2.0f), ((getHeight() / 2) + (this.mHeight / 2)) - (this.mBgCornerWidth / 2.0f)), dp2px(this.mBgCorner), dp2px(this.mBgCorner), this.mPaint);
        canvas.save();
    }

    private void drawLines(Canvas canvas) {
        this.mPaint.setColor(this.mBoardColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(dp2px(this.mBgCornerWidth));
        for (int i2 = 1; i2 < this.passwordNumber; i2++) {
            int i3 = this.mHeight;
            canvas.drawLine(this.mHeight * i2, (getHeight() / 2) - (i3 / 2), i3 * i2, (getHeight() / 2) + (this.mHeight / 2), this.mPaint);
        }
    }

    private void drawPassword(Canvas canvas) {
        this.mPaint.setColor(getResources().getColor(R.color.jdpay_edit_keyboard_pwd_bg));
        this.mPaint.setStyle(Paint.Style.FILL);
        for (int i2 = 0; i2 < this.mNumber.length(); i2++) {
            int i3 = this.mHeight;
            canvas.drawCircle((i3 / 2) + (i2 * i3), i3 / 2, this.mPasswordRadius, this.mPaint);
        }
        canvas.save();
    }

    private void drawPasswordRect(Canvas canvas) {
        this.mPaint.setColor(this.mBoardColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(dp2px(this.mBgCornerWidth));
        canvas.drawRoundRect(new RectF(((getWidth() / 2) - (this.mWidth / 2)) + (dp2px(this.mBgCornerWidth) / 2), ((getHeight() / 2) - (this.mHeight / 2)) + (dp2px(this.mBgCornerWidth) / 2), ((getWidth() / 2) + (this.mWidth / 2)) - (dp2px(this.mBgCornerWidth) / 2), ((getHeight() / 2) + (this.mHeight / 2)) - (dp2px(this.mBgCornerWidth) / 2)), dp2px(this.mBgCorner), dp2px(this.mBgCorner), this.mPaint);
    }

    private void init() {
        setBackgroundDrawable(null);
        setLongClickable(false);
        setTextIsSelectable(false);
        setCursorVisible(false);
        KeyboardController keyboardController = new KeyboardController(ContextHelper.getActivity(getContext()), KeyboardController.KeyboardType.SAFEKEYBOARD, 1);
        this.keyboardControler = keyboardController;
        keyboardController.init();
        this.keyboardControler.setCallback(this.mCallBack);
        this.keyboardControler.setSystemShowSoftInputDisable(this);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PasswordView);
        this.passwordNumber = obtainStyledAttributes.getInt(R.styleable.PasswordView_passwordNumber, this.passwordNumber);
        this.mBgCorner = (int) obtainStyledAttributes.getDimension(R.styleable.PasswordView_bgCorner, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(this.mBoardColor);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(dp2px(2.0f));
    }

    public boolean checkRegexFind(String str) {
        return this.keyboardControler.checkRegexFind(str);
    }

    public boolean checkRegexMatch(String str) {
        return this.keyboardControler.checkRegexMatch(str);
    }

    public void clearContent() {
        deleteAll();
        this.keyboardControler.clearContent();
    }

    public void deleteAll() {
        StringBuffer stringBuffer = this.mNumber;
        stringBuffer.delete(0, stringBuffer.length());
        invalidate();
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && this.keyboardControler.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public int dp2px(float f2) {
        return (int) ((f2 * SystemInfo.getDensity()) + 0.5f);
    }

    public String getEncryptContent() {
        return new String(this.keyboardControler.getCryptoData().getResultString());
    }

    public int getInputLength() {
        KeyboardController keyboardController = this.keyboardControler;
        if (keyboardController != null) {
            return keyboardController.getInputLength();
        }
        return 0;
    }

    public Object getKeyboard() {
        return this.keyboardControler.getReallKeyboard();
    }

    public float getKeyboardHeight() {
        return this.keyboardControler.getKeyboardHeight();
    }

    public String getTempCipherText() {
        return this.keyboardControler.getTempCipherText();
    }

    public void hideKeyboard() {
        KeyboardController keyboardController = this.keyboardControler;
        if (keyboardController != null) {
            keyboardController.hideKeyboard();
        }
    }

    public boolean isKeyboardShowing() {
        KeyboardController keyboardController = this.keyboardControler;
        if (keyboardController != null) {
            return keyboardController.isKeyboardShowing();
        }
        return false;
    }

    public void onDestroy() {
        this.keyboardControler.onDestroy();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        drawBackGround(canvas);
        drawPasswordRect(canvas);
        drawLines(canvas);
        drawPassword(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int width = getWidth();
            this.mWidth = width;
            int i4 = width / this.passwordNumber;
            this.mPasswordSize = i4;
            this.mHeight = i4;
            this.mPasswordRadius = i4 / 10;
        } else if (mode == 1073741824) {
            int size = View.MeasureSpec.getSize(i2);
            this.mWidth = size;
            int i5 = size / this.passwordNumber;
            this.mPasswordSize = i5;
            this.mHeight = i5;
            this.mPasswordRadius = i5 / 10;
        }
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.keyboardControler.setDefaultValue(getText().toString());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.keyboardControler.show();
        check();
        return super.onTouchEvent(motionEvent);
    }

    public void refreshAll(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mNumber.deleteCharAt(r3.length() - 1);
        } else if (this.mNumber.length() < this.passwordNumber) {
            this.mNumber.append(str);
        }
        invalidate();
    }

    public void scrollTo(int i2, View view) {
        if (view == null) {
            return;
        }
        if (i2 > 100) {
            view.scrollTo(0, i2);
        } else {
            view.scrollTo(0, 0);
        }
    }

    public void setBoardColor(int i2) {
        this.mBoardColor = i2;
        invalidate();
    }

    public void setDefaultValue(String str) {
        this.keyboardControler.setDefaultValue(str);
    }

    public void setFinishCallback(FinishCallback finishCallback) {
        this.finishCallback = finishCallback;
    }

    public void showKeyboard() {
        this.keyboardControler.show();
        check();
    }

    public PwdEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.passwordNumber = 6;
        this.mPasswordRadius = 6;
        this.mBgCornerWidth = 2.0f;
        this.mNumber = new StringBuffer();
        this.mBoardColor = getResources().getColor(R.color.jdpay_edit_keyboard_gray);
        this.mBackgroundColor = getResources().getColor(R.color.jdpay_edit_keyboard_bg);
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.PwdEditText.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                PwdEditText.this.deleteAll();
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (PwdEditText.this.layout_root != null) {
                    PwdEditText pwdEditText = PwdEditText.this;
                    pwdEditText.scrollTo(0, pwdEditText.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                PwdEditText.this.refreshAll(str);
                if (PwdEditText.this.keyboardControler.getInputLength() != PwdEditText.this.passwordNumber || PwdEditText.this.finishCallback == null) {
                    return;
                }
                PwdEditText.this.finishCallback.onFinish(new String(PwdEditText.this.keyboardControler.getCryptoData().getResultString()));
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                PwdEditText.this.refreshAll("");
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (PwdEditText.this.finishCallback != null) {
                    PwdEditText.this.finishCallback.onFinish(str);
                }
            }
        };
        init();
        initPaint();
        initAttrs(context, attributeSet);
    }
}
