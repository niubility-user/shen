package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes3.dex */
public class PdKeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "KeyboardChangeListener";
    private int lastVisiableBottom;
    private View mContentView;
    private PdKeyBoardListener mKeyBoardListen;

    public PdKeyboardChangeListener(View view) {
        if (view == null) {
            if (Log.D) {
                Log.d(TAG, "contextObj is null");
                return;
            }
            return;
        }
        this.mContentView = view;
        addContentTreeObserver();
    }

    private void addContentTreeObserver() {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void destroy() {
        View view = this.mContentView;
        if (view == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.mContentView.getWindowVisibleDisplayFrame(rect);
        int i2 = rect.bottom;
        if (this.lastVisiableBottom == i2) {
            return;
        }
        this.lastVisiableBottom = i2;
        PdKeyBoardListener pdKeyBoardListener = this.mKeyBoardListen;
        if (pdKeyBoardListener != null) {
            pdKeyBoardListener.onKeyboardChange(i2);
        }
    }

    public void setKeyBoardListener(PdKeyBoardListener pdKeyBoardListener) {
        this.mKeyBoardListen = pdKeyBoardListener;
    }
}
