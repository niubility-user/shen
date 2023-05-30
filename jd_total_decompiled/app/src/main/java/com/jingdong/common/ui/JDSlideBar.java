package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.common.DpiUtil;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class JDSlideBar extends View implements OnViewThemeConfig<JDSlideBar> {
    private static final int DEFAULT_HEIGHT = 300;
    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_WIDTH = 20;
    private static final int MAX_SPACE_HEIGHT = 24;
    private static final float SHOW_HEIGHT_PERCENT = 0.85f;
    private String[] allChars;
    private Map<String, FontRect> cacheFontRect;
    private ISlideBarTouchChangeLisener callback;
    private int defaultHeight;
    private int defaultWidth;
    private Handler handler;
    private int height;
    private Runnable hiddenAction;
    private Animation hiddenAnim;
    private boolean isAutoDark;
    private boolean isDarkMode;
    private int maxSpaceHeight;
    private boolean needNotes;
    private TextView notesTextView;
    private Paint paint;
    private int selectedPosition;
    private Runnable showAction;
    private Animation showAnim;
    private String[] soureChars;
    private float space;
    private int textSize;
    private int touchPosition;
    private int width;

    /* loaded from: classes6.dex */
    public static class FontRect {
        public float fontHeight;
        public float fontWidth;

        public boolean isUseful() {
            return (this.fontHeight == 0.0f || this.fontWidth == 0.0f) ? false : true;
        }
    }

    /* loaded from: classes6.dex */
    public class HiddenNoteCallback implements Runnable {
        public HiddenNoteCallback() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JDSlideBar.this.notesTextView == null || JDSlideBar.this.notesTextView.getVisibility() == 8) {
                return;
            }
            JDSlideBar.this.notesTextView.clearAnimation();
            JDSlideBar.this.notesTextView.startAnimation(JDSlideBar.this.hiddenAnim);
            JDSlideBar.this.notesTextView.setVisibility(8);
        }
    }

    /* loaded from: classes6.dex */
    public interface ISlideBarTouchChangeLisener {
        void onSlideBarSelected(String str);
    }

    /* loaded from: classes6.dex */
    public class ShowNoteCallback implements Runnable {
        public ShowNoteCallback() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (JDSlideBar.this.notesTextView == null || JDSlideBar.this.notesTextView.getVisibility() == 0) {
                return;
            }
            JDSlideBar.this.notesTextView.clearAnimation();
            JDSlideBar.this.notesTextView.startAnimation(JDSlideBar.this.showAnim);
            JDSlideBar.this.notesTextView.setVisibility(0);
        }
    }

    public JDSlideBar(Context context) {
        this(context, null);
    }

    private float calculationSpace(int i2, int i3) {
        return i3 == 0 ? i2 : ((i2 - getPaddingTop()) - getPaddingBottom()) / (i3 * 1.0f);
    }

    private int calculationTouchPosition(float f2) {
        if (this.space == 0.0f) {
            this.space = 1.0f;
        }
        return ((int) Math.ceil((f2 - getPaddingTop()) / this.space)) - 1;
    }

    private void changeNoteStatus() {
        this.handler.removeCallbacks(this.hiddenAction);
        this.handler.post(this.showAction);
        this.handler.postDelayed(this.hiddenAction, 500L);
    }

    private boolean checkTouchInRange(float f2) {
        return f2 >= ((float) getPaddingTop()) && f2 <= ((float) (getHeight() - getPaddingBottom()));
    }

    private void drawText(Canvas canvas) {
        int i2 = 0;
        while (true) {
            String[] strArr = this.soureChars;
            if (i2 >= strArr.length) {
                return;
            }
            String str = strArr[i2];
            FontRect fontRect = getFontRect(str, this.paint);
            float f2 = this.space;
            canvas.drawText(str, (this.width / 2) - (fontRect.fontWidth / 2.0f), getPaddingTop() + (i2 * f2) + (f2 / 2.0f) + (fontRect.fontHeight / 2.0f), this.paint);
            i2++;
        }
    }

    private int getCurrentDataSize() {
        return this.soureChars.length;
    }

    private int getCustomSize(int i2, int i3) {
        return View.MeasureSpec.getMode(i3) == 1073741824 ? View.MeasureSpec.getSize(i3) : i2;
    }

    private int getFitHeight(int i2, int i3) {
        float f2 = this.space;
        int i4 = this.maxSpaceHeight;
        if (f2 > i4) {
            this.space = i4;
            return getPaddingTop() + (i3 * i4) + getPaddingBottom();
        }
        return i2;
    }

    private FontRect getFontRect(String str, Paint paint) {
        FontRect fontRect = this.cacheFontRect.get(str);
        if (fontRect == null || !fontRect.isUseful()) {
            FontRect fontRect2 = new FontRect();
            paint.getTextBounds(str, 0, str.length(), new Rect());
            fontRect2.fontWidth = r1.width();
            fontRect2.fontHeight = r1.height();
            this.cacheFontRect.put(str, fontRect2);
            return fontRect2;
        }
        return fontRect;
    }

    private void iniConfig() {
        this.textSize = DpiUtil.sp2px(getContext(), 10.0f);
        this.paint = new Paint(1);
        if (isDarkMode()) {
            this.paint.setColor(ContextCompat.getColor(getContext(), R.color.un_content_level_2_dark));
        } else {
            this.paint.setColor(ContextCompat.getColor(getContext(), R.color.un_content_level_2));
        }
        this.paint.setTextSize(this.textSize);
        this.defaultWidth = DpiUtil.dip2px(getContext(), 20.0f);
        this.defaultHeight = DpiUtil.dip2px(getContext(), 300.0f);
        this.maxSpaceHeight = DpiUtil.dip2px(getContext(), 24.0f);
        setLayerType(2, null);
        this.handler = new Handler(Looper.getMainLooper());
        this.showAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_alpha_show);
        this.hiddenAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_alpha_hidden);
        this.showAction = new ShowNoteCallback();
        this.hiddenAction = new HiddenNoteCallback();
    }

    private void notifyTouchChange(int i2) {
        if (i2 >= 0) {
            String[] strArr = this.soureChars;
            if (i2 < strArr.length) {
                final String str = strArr[i2];
                if (this.needNotes) {
                    showNotes(str);
                }
                this.selectedPosition = i2;
                refresh();
                if (this.callback != null) {
                    postDelayed(new Runnable() { // from class: com.jingdong.common.ui.JDSlideBar.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JDSlideBar.this.callback.onSlideBarSelected(str);
                        }
                    }, 150L);
                }
            }
        }
    }

    private void showNotes(String str) {
        TextView textView = this.notesTextView;
        if (textView != null) {
            textView.setText(str);
            changeNoteStatus();
        }
    }

    public void changeLetterToSelected(String str) {
        int i2 = 0;
        while (true) {
            String[] strArr = this.soureChars;
            if (i2 >= strArr.length) {
                return;
            }
            if (strArr[i2].equals(str)) {
                changePositionToSelected(i2);
            }
            i2++;
        }
    }

    public void changePositionToSelected(int i2) {
        if (i2 < 0 || i2 > this.soureChars.length - 1 || i2 == this.selectedPosition) {
            return;
        }
        this.selectedPosition = i2;
        postInvalidate();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int calculationTouchPosition;
        float y = motionEvent.getY();
        if (motionEvent.getAction() == 0) {
            this.touchPosition = -1;
        }
        if (checkTouchInRange(y) && this.touchPosition != (calculationTouchPosition = calculationTouchPosition(y))) {
            this.touchPosition = calculationTouchPosition;
            notifyTouchChange(calculationTouchPosition);
        }
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar elderMode() {
        return null;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDark;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return false;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isDarkMode() {
        if (this.isAutoDark) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return false;
    }

    public boolean isNeedNotes() {
        return this.needNotes;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        this.width = getCustomSize(this.defaultWidth, i2);
        int customSize = getCustomSize(this.defaultHeight, i3);
        this.height = customSize;
        this.height = (int) (customSize * SHOW_HEIGHT_PERCENT);
        int currentDataSize = getCurrentDataSize();
        this.space = calculationSpace(this.height, currentDataSize);
        int fitHeight = getFitHeight(this.height, currentDataSize);
        this.height = fitHeight;
        setMeasuredDimension(this.width, fitHeight);
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        invalidate();
    }

    public void release() {
        Map<String, FontRect> map = this.cacheFontRect;
        if (map != null) {
            map.clear();
        }
        if (this.soureChars != null) {
            this.soureChars = this.allChars;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar setAutoElderMode(boolean z) {
        return null;
    }

    public void setCallback(ISlideBarTouchChangeLisener iSlideBarTouchChangeLisener) {
        this.callback = iSlideBarTouchChangeLisener;
    }

    public void setData(List<String> list) {
        if (list == null) {
            return;
        }
        String[] strArr = new String[list.size()];
        this.soureChars = strArr;
        list.toArray(strArr);
        refresh();
    }

    public void setDataAndAutoHeight(List<String> list) {
        if (list == null) {
            return;
        }
        String[] strArr = new String[list.size()];
        this.soureChars = strArr;
        list.toArray(strArr);
        requestLayout();
        refresh();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar setElderMode(boolean z) {
        return null;
    }

    public void setNeedNotes(boolean z) {
        this.needNotes = z;
    }

    public void setNotesTextView(TextView textView) {
        this.notesTextView = textView;
    }

    public JDSlideBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar darkMode() {
        this.paint.setColor(ContextCompat.getColor(getContext(), R.color.un_content_level_2_dark));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar normalMode() {
        this.paint.setColor(ContextCompat.getColor(getContext(), R.color.un_content_level_2));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public JDSlideBar setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public JDSlideBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        String[] strArr = {"A", "B", "C", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "E", "F", "G", DYConstants.LETTER_H, "I", "J", "K", "L", "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, IShareAdapter.SHARE_ACTION_OPEN, IShareAdapter.SHARE_ACTION_PANE, "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.allChars = strArr;
        this.soureChars = strArr;
        this.selectedPosition = -1;
        this.needNotes = true;
        this.paint = null;
        this.notesTextView = null;
        this.touchPosition = -1;
        this.cacheFontRect = new HashMap();
        this.isAutoDark = false;
        iniConfig();
        setClickable(true);
    }
}
