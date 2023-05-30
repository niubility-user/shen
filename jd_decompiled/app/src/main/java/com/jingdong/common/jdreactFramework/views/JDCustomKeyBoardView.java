package com.jingdong.common.jdreactFramework.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes5.dex */
public class JDCustomKeyBoardView extends KeyboardView {
    private Context mContext;
    private Keyboard mKeyBoard;

    public JDCustomKeyBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        int i2;
        int i3;
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_delete_key_normal);
        if (drawable == null) {
            return;
        }
        Rect rect = new Rect();
        if (rect.isEmpty()) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i4 = key.width;
            if (intrinsicWidth > i4) {
                i3 = (i4 * intrinsicHeight) / intrinsicWidth;
                i2 = i4;
            } else {
                i2 = intrinsicWidth;
                i3 = intrinsicHeight;
            }
            int i5 = key.height;
            if (i3 > i5) {
                i2 = (intrinsicWidth * i5) / intrinsicHeight;
                i3 = i5;
            }
            int i6 = i2 - 38;
            int i7 = i3 - 40;
            int i8 = key.x + ((i4 - i6) / 2);
            int i9 = key.y + ((i5 - i7) / 2);
            rect = new Rect(i8, i9, i6 + i8, i7 + i9);
        }
        if (rect.isEmpty()) {
            return;
        }
        drawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        drawable.draw(canvas);
    }

    private void drawKeyBackground(Keyboard.Key key, Canvas canvas, int i2) {
        ColorDrawable colorDrawable = new ColorDrawable(i2);
        colorDrawable.setBounds(key.x, key.y + getPaddingTop(), key.x + key.width, key.y + key.height + getPaddingTop());
        colorDrawable.draw(canvas);
    }

    private void drawText(Canvas canvas, Keyboard.Key key, int i2) {
        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(i2);
        CharSequence charSequence = key.label;
        if (charSequence != null) {
            if (charSequence.toString().length() > 1 && key.codes.length < 2) {
                try {
                    Field declaredField = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    declaredField.setAccessible(true);
                    ((Integer) declaredField.get(this)).intValue();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (NoSuchFieldException e3) {
                    e3.printStackTrace();
                }
                paint.setTextSize(sp2px(this.mContext, 16.0f));
                paint.setTypeface(Typeface.MONOSPACE);
            } else {
                try {
                    Field declaredField2 = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    declaredField2.setAccessible(true);
                    ((Integer) declaredField2.get(this)).intValue();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (NoSuchFieldException e5) {
                    e5.printStackTrace();
                }
                paint.setTextSize(sp2px(this.mContext, 16.0f));
                paint.setTypeface(Typeface.DEFAULT);
            }
            paint.getTextBounds(key.label.toString(), 0, key.label.toString().length(), rect);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2), key.y + (key.height / 2) + (rect.height() / 2) + getPaddingTop(), paint);
            return;
        }
        Drawable drawable = key.icon;
        if (drawable != null) {
            drawable.setBounds(key.x + ((key.width - drawable.getIntrinsicWidth()) / 2), key.y + ((key.height - key.icon.getIntrinsicHeight()) / 2), key.x + ((key.width - key.icon.getIntrinsicWidth()) / 2) + key.icon.getIntrinsicWidth(), key.y + ((key.height - key.icon.getIntrinsicHeight()) / 2) + key.icon.getIntrinsicHeight());
            key.icon.draw(canvas);
        }
    }

    public int dp2px(Context context, float f2) {
        DisplayMetrics displayMetricsObject = JDReactHelper.newInstance().getDisplayMetricsObject(context);
        return (int) ((f2 * (displayMetricsObject != null ? displayMetricsObject.density : 1.0f)) + 0.5f);
    }

    @Override // android.inputmethodservice.KeyboardView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Keyboard keyboard = getKeyboard();
        this.mKeyBoard = keyboard;
        List<Keyboard.Key> keys = keyboard != null ? keyboard.getKeys() : null;
        if (keys != null) {
            for (Keyboard.Key key : keys) {
                int[] iArr = key.codes;
                if (iArr[0] == -32) {
                    drawKeyBackground(key, canvas, getResources().getColor(R.color.color_DFE3E9));
                } else if (iArr[0] == -4) {
                    drawKeyBackground(key, canvas, getResources().getColor(R.color.color_DFE3E9));
                    drawText(canvas, key, getResources().getColor(17170444));
                } else if (iArr[0] == -5) {
                    drawKeyBackground(key, R.drawable.btn_keyboard_delete_click, canvas);
                    drawDeleteButton(key, canvas);
                }
            }
        }
    }

    public int px2dp(Context context, float f2) {
        DisplayMetrics displayMetricsObject = JDReactHelper.newInstance().getDisplayMetricsObject(context);
        return (int) ((f2 / (displayMetricsObject != null ? displayMetricsObject.density : 1.0f)) + 0.5f);
    }

    public int px2sp(Context context, float f2) {
        DisplayMetrics displayMetricsObject = JDReactHelper.newInstance().getDisplayMetricsObject(context);
        return (int) ((f2 / (displayMetricsObject != null ? displayMetricsObject.scaledDensity : 1.0f)) + 0.5f);
    }

    public int sp2px(Context context, float f2) {
        DisplayMetrics displayMetricsObject = JDReactHelper.newInstance().getDisplayMetricsObject(context);
        return (int) ((f2 * (displayMetricsObject != null ? displayMetricsObject.scaledDensity : 1.0f)) + 0.5f);
    }

    public JDCustomKeyBoardView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
    }

    private void drawKeyBackground(Keyboard.Key key, int i2, Canvas canvas) {
        Drawable drawable = this.mContext.getResources().getDrawable(i2);
        int[] currentDrawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            drawable.setState(currentDrawableState);
        }
        drawable.setBounds(key.x, key.y + getPaddingTop(), key.x + key.width, key.y + key.height + getPaddingTop());
        drawable.draw(canvas);
    }
}
