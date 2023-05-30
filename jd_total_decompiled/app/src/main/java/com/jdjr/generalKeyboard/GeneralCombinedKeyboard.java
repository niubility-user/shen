package com.jdjr.generalKeyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.GeneralFunctionalKeyboard;
import com.jdjr.generalKeyboard.common.AnimatorUtils;
import com.jdjr.generalKeyboard.common.KeyboardAdapter;
import com.jdjr.generalKeyboard.common.KeyboardViewPager;
import com.jdjr.generalKeyboard.common.ViewsUtils;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class GeneralCombinedKeyboard extends FrameLayout {
    private static final String TAG = "combined_keyboard";
    private List<GeneralFunctionalKeyboard> allCombinedKeyboard;
    private int currentPageIndex;
    private Context mContext;
    private boolean mIsKeyboardShown;
    private LinearLayout mRootView;
    View.OnTouchListener onTouchListener;
    private KeyboardViewPager vpKeyboard;

    public GeneralCombinedKeyboard(Context context, List<GeneralFunctionalKeyboard> list) {
        super(context);
        this.currentPageIndex = 0;
        this.mIsKeyboardShown = false;
        this.onTouchListener = new View.OnTouchListener() { // from class: com.jdjr.generalKeyboard.GeneralCombinedKeyboard.2
            {
                GeneralCombinedKeyboard.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                GeneralCombinedKeyboard generalCombinedKeyboard = GeneralCombinedKeyboard.this;
                if (!generalCombinedKeyboard.getCurrentRect(generalCombinedKeyboard.vpKeyboard).contains((int) motionEvent.getX(), (int) motionEvent.getY()) && GeneralCombinedKeyboard.this.mIsKeyboardShown) {
                    GeneralCombinedKeyboard.this.hide();
                }
                return true;
            }
        };
        this.mContext = context;
        if (list != null && list.size() > 0) {
            this.allCombinedKeyboard = list;
        } else {
            this.allCombinedKeyboard = new ArrayList();
        }
        initLayout();
        initData();
    }

    private void initData() {
        for (int i2 = 0; i2 < this.allCombinedKeyboard.size(); i2++) {
            this.allCombinedKeyboard.get(i2).setCombinedCallback(new GeneralFunctionalKeyboard.CombinedCallback() { // from class: com.jdjr.generalKeyboard.GeneralCombinedKeyboard.1
                {
                    GeneralCombinedKeyboard.this = this;
                }

                @Override // com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.CombinedCallback
                public void onClick(GeneralKeyboard.FunctionalActionType functionalActionType) {
                    if (functionalActionType == GeneralKeyboard.FunctionalActionType.HIDE) {
                        GeneralCombinedKeyboard.this.hide();
                    }
                }
            });
        }
    }

    private void initPagerAdapter() {
        this.vpKeyboard.setAdapter(new KeyboardAdapter(this.allCombinedKeyboard, this.mContext));
    }

    private void turnToPosition(int i2) {
        this.currentPageIndex = i2;
        if (i2 == 0) {
            this.allCombinedKeyboard.get(0).setBackVisibility(4);
        } else if (i2 == 1) {
            this.allCombinedKeyboard.get(1).setBackVisibility(0);
            this.allCombinedKeyboard.get(1).setCountdown();
        }
        this.vpKeyboard.setEnabled(false);
        this.vpKeyboard.setCurrentItem(this.currentPageIndex, true);
    }

    public void clearAllKeyboard() {
        for (int i2 = 0; i2 < this.allCombinedKeyboard.size(); i2++) {
            this.allCombinedKeyboard.get(i2).clearKeyboard();
        }
    }

    public void clearKeyboard() {
        this.allCombinedKeyboard.get(this.currentPageIndex).clearKeyboard();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() != 1 && this.mIsKeyboardShown) {
            if (this.currentPageIndex == 0) {
                hide();
            }
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    protected Rect getCurrentRect(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    public void hide() {
        if (this.mIsKeyboardShown) {
            AnimatorUtils.initDownAnimator(this.vpKeyboard, this.mRootView, getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY));
        }
        this.mIsKeyboardShown = false;
        this.allCombinedKeyboard.get(0).setActionClick(GeneralKeyboard.FunctionalActionType.HIDE, "00000");
        for (int i2 = 0; i2 < this.allCombinedKeyboard.size(); i2++) {
            this.allCombinedKeyboard.get(i2).clearKeyboard();
            this.allCombinedKeyboard.get(i2).stopCountdown();
            this.allCombinedKeyboard.get(i2).closeEye();
        }
    }

    protected void initLayout() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.security_general_keyboard_container, (ViewGroup) null, false);
        this.mRootView = linearLayout;
        linearLayout.setOnTouchListener(this.onTouchListener);
        KeyboardViewPager keyboardViewPager = new KeyboardViewPager(this.mContext);
        this.vpKeyboard = keyboardViewPager;
        keyboardViewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) this.mContext.getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY)));
        initPagerAdapter();
    }

    public void onBackKeyClick() {
        turnToPosition(0);
    }

    public void onNextKeyClick() {
        turnToPosition(1);
    }

    public void releaseCppKeyboard() {
        for (int i2 = 0; i2 < this.allCombinedKeyboard.size(); i2++) {
            this.allCombinedKeyboard.get(i2).releaseCppKeyboard();
        }
    }

    public void show(Activity activity) {
        if (activity == null) {
            return;
        }
        this.allCombinedKeyboard.get(0).hideSystemKeyboard(activity);
        this.allCombinedKeyboard.get(0).setCountdown();
        View findViewById = activity.getWindow().findViewById(16908290);
        ViewsUtils.addToParent(this.mRootView, this.vpKeyboard);
        ViewsUtils.addToParent(findViewById, this.mRootView);
        if (!this.mIsKeyboardShown) {
            AnimatorUtils.initUpAnimator(this.vpKeyboard, getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY));
        }
        this.mIsKeyboardShown = true;
        this.vpKeyboard.setScroll(false);
    }
}
