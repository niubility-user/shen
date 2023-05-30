package com.jingdong.common.unification.pagenumswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager;
import com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesMessageManager;
import com.jingdong.common.unification.scenes.base.BaseScenes;
import com.jingdong.common.unification.scenes.base.DefaultScenes;
import com.jingdong.common.unification.scenes.custom.ImageScenes;
import com.jingdong.common.unification.scenes.custom.LineScenes;
import com.jingdong.common.unification.scenes.custom.TextScenes;
import com.jingdong.common.unification.scenes.utils.ScenesConstant;

/* loaded from: classes6.dex */
public class PageNumSwitchView extends View implements PageNumSwitchScenesMessageManager.OnScenesAnimListener, OnViewThemeConfig<PageNumSwitchView> {
    private static final int MAX_ANIM_DURATION = 350;
    private static final int MIN_ANIM_DURATION = 100;
    private static int TYPE_FRONT = 0;
    private static int TYPE_POST = 1;
    private int bottomValue;
    private int bottomValueTextColor;
    private int bottomValueTextSize;
    private int currentType;
    private int finalValue;
    private int frontBackgroundResId;
    private BaseScenes frontBgBaseScenes;
    private BaseScenes frontBottomBaseScenes;
    private Typeface frontBottomTextType;
    private BaseScenes frontCurrentTopBaseScenes;
    private BaseScenes frontElderBaseScenes;
    private LineScenes frontLineBaseScenes;
    private BaseScenes frontNextElderBaseScenes;
    private BaseScenes frontNextTopBaseScenes;
    private BaseScenes frontRootBaseScenes;
    private Typeface frontTopTextType;
    private boolean initFinish;
    private boolean isAutoDark;
    private boolean isAutoElder;
    private boolean isDarkMode;
    private boolean isElderMode;
    private long lastSetTopTime;
    private OnClickGotoTopListener listener;
    private int maxTopValue;
    private int minTopValue;
    private int postBackgroundResId;
    private BaseScenes postBgBaseScenes;
    private BaseScenes postRootBaseScenes;
    private BaseScenes rootBaseScenes;
    private PageNumSwitchScenesManager scenesManager;
    private PageNumSwitchScenesMessageManager scenesMessageManager;
    private BaseScenes tempNetTop;
    private BaseScenes tempTop;
    private int topValue;
    private int topValueTextColor;
    private int topValueTextSize;

    /* loaded from: classes6.dex */
    public interface OnClickGotoTopListener {
        void onClickGotoTop(View view);
    }

    public PageNumSwitchView(Context context) {
        this(context, null);
    }

    private boolean checkTopValueInRange(int i2) {
        return i2 >= this.minTopValue && i2 <= this.maxTopValue;
    }

    private long computeAnimNeedDuration() {
        long currentTimeMillis = System.currentTimeMillis() - this.lastSetTopTime;
        if (currentTimeMillis < 100) {
            return 100L;
        }
        if (currentTimeMillis < 350) {
            return currentTimeMillis;
        }
        return 350L;
    }

    private int getCustomMeasureHeight(int i2) {
        return View.MeasureSpec.getMode(i2) == 1073741824 ? View.MeasureSpec.getSize(i2) : DpiUtil.dip2px(getContext(), 40.0f);
    }

    private int getCustomMeasureWidth(int i2) {
        return View.MeasureSpec.getMode(i2) == 1073741824 ? View.MeasureSpec.getSize(i2) : DpiUtil.dip2px(getContext(), 40.0f);
    }

    private void initScenes() {
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int i2 = paddingLeft + 0;
        int i3 = paddingTop + 0;
        int i4 = width - paddingRight;
        int i5 = height - paddingBottom;
        DefaultScenes defaultScenes = new DefaultScenes(this, new Rect(i2, i3, i4, i5));
        this.rootBaseScenes = defaultScenes;
        this.scenesManager.setRootBaseScenes(defaultScenes);
        this.frontRootBaseScenes = new DefaultScenes(this, new Rect(i2, i3, i4, i5));
        int i6 = i2 - paddingLeft;
        int i7 = i3 - paddingTop;
        int i8 = paddingRight + i4;
        int i9 = paddingBottom + i5;
        ImageScenes imageScenes = new ImageScenes(this, new Rect(i6, i7, i8, i9));
        this.frontBgBaseScenes = imageScenes;
        imageScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_BITMAP_OBJ, parseBitmapByResId(this.frontBackgroundResId));
        this.frontRootBaseScenes.addScenes(this.frontBgBaseScenes);
        DpiUtil.dip2px(getContext(), 7.0f);
        int i10 = ((i5 - i3) / 2) + i3;
        TextScenes textScenes = new TextScenes(this, new Rect(i2, i3, i4, i10), true, false);
        this.frontCurrentTopBaseScenes = textScenes;
        textScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        this.frontCurrentTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.topValueTextSize);
        this.frontCurrentTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.topValueTextColor);
        this.frontCurrentTopBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.frontTopTextType);
        this.frontRootBaseScenes.addScenes(this.frontCurrentTopBaseScenes);
        TextScenes textScenes2 = new TextScenes(this, new Rect(i2, i3, i4, i10), true, false);
        this.frontNextTopBaseScenes = textScenes2;
        textScenes2.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        this.frontNextTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.topValueTextSize);
        this.frontNextTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.topValueTextColor);
        this.frontNextTopBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.frontTopTextType);
        this.frontNextTopBaseScenes.setVisible(false);
        this.frontRootBaseScenes.addScenes(this.frontNextTopBaseScenes);
        DpiUtil.dip2px(getContext(), 7.0f);
        TextScenes textScenes3 = new TextScenes(this, new Rect(i2, i3, i4, i5), true, true);
        this.frontElderBaseScenes = textScenes3;
        textScenes3.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        this.frontElderBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.topValueTextSize);
        this.frontElderBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.topValueTextColor);
        this.frontElderBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.frontTopTextType);
        this.frontElderBaseScenes.setVisible(false);
        this.frontRootBaseScenes.addScenes(this.frontElderBaseScenes);
        TextScenes textScenes4 = new TextScenes(this, new Rect(i2, i3, i4, i5), true, true);
        this.frontNextElderBaseScenes = textScenes4;
        textScenes4.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        this.frontNextElderBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.topValueTextSize);
        this.frontNextElderBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.topValueTextColor);
        this.frontNextElderBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.frontTopTextType);
        this.frontNextElderBaseScenes.setVisible(false);
        this.frontRootBaseScenes.addScenes(this.frontNextElderBaseScenes);
        TextScenes textScenes5 = new TextScenes(this, new Rect(i2, DpiUtil.dip2px(getContext(), 7.0f) + i10, i4, i5), false, false);
        this.frontBottomBaseScenes = textScenes5;
        textScenes5.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.bottomValue + "");
        this.frontBottomBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, this.bottomValueTextSize);
        this.frontBottomBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, this.bottomValueTextColor);
        this.frontBottomBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, this.frontBottomTextType);
        this.frontBottomBaseScenes.setVisible(true);
        this.frontRootBaseScenes.addScenes(this.frontBottomBaseScenes);
        DpiUtil.dip2px(getContext(), 2.0f);
        LineScenes lineScenes = new LineScenes(this, new Rect(i2, i10, i4, i5));
        this.frontLineBaseScenes = lineScenes;
        lineScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_LINE_WIDTH_INT, 3);
        this.frontLineBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_PADDING_INT, getWidth() / 6);
        this.frontLineBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_LINE_COLOR_INT, parseColorByResId(R.color.page_num_switch_line_color));
        this.frontRootBaseScenes.addScenes(this.frontLineBaseScenes);
        DefaultScenes defaultScenes2 = new DefaultScenes(this, new Rect(i2, i3, i4, i5));
        this.postRootBaseScenes = defaultScenes2;
        defaultScenes2.setVisible(false);
        ImageScenes imageScenes2 = new ImageScenes(this, new Rect(i6, i7, i8, i9));
        this.postBgBaseScenes = imageScenes2;
        imageScenes2.addOrUpdateObjData(ScenesConstant.Value.SCENES_BITMAP_OBJ, parseBitmapByResId(this.postBackgroundResId));
        this.postRootBaseScenes.addScenes(this.postBgBaseScenes);
        if (isDarkMode()) {
            darkMode();
        }
        this.rootBaseScenes.addScenes(this.frontRootBaseScenes);
        this.rootBaseScenes.addScenes(this.postRootBaseScenes);
    }

    private boolean isNeedRefresh() {
        return ViewCompat.isLaidOut(this) && this.initFinish;
    }

    private void notifyScenesRelease() {
        safeScenesRelease(this.rootBaseScenes);
        safeScenesRelease(this.frontRootBaseScenes);
        safeScenesRelease(this.postRootBaseScenes);
        safeScenesRelease(this.postBgBaseScenes);
        safeScenesRelease(this.frontCurrentTopBaseScenes);
        safeScenesRelease(this.frontNextTopBaseScenes);
        safeScenesRelease(this.frontBottomBaseScenes);
        safeScenesRelease(this.frontLineBaseScenes);
        safeScenesRelease(this.frontBgBaseScenes);
        safeScenesRelease(this.frontElderBaseScenes);
        safeScenesRelease(this.frontNextElderBaseScenes);
    }

    private Bitmap parseBitmapByResId(@DrawableRes int i2) {
        return BitmapFactory.decodeResource(getResources(), i2);
    }

    private int parseColorByResId(@ColorRes int i2) {
        return ContextCompat.getColor(getContext(), i2);
    }

    private void parseConfig(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.PageNumSwitchView, i2, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.PageNumSwitchView_front_top_text_value) {
                this.topValue = obtainStyledAttributes.getInt(index, this.topValue);
            } else if (index == R.styleable.PageNumSwitchView_front_top_text_color) {
                this.topValueTextColor = obtainStyledAttributes.getColor(index, this.topValueTextColor);
            } else if (index == R.styleable.PageNumSwitchView_front_top_text_size) {
                this.topValueTextSize = obtainStyledAttributes.getDimensionPixelSize(index, this.topValueTextSize);
            } else if (index == R.styleable.PageNumSwitchView_front_bottom_text_value) {
                this.bottomValue = obtainStyledAttributes.getInt(index, this.bottomValue);
            } else if (index == R.styleable.PageNumSwitchView_front_bottom_text_color) {
                this.bottomValueTextColor = obtainStyledAttributes.getColor(index, this.bottomValueTextColor);
            } else if (index == R.styleable.PageNumSwitchView_front_bottom_text_size) {
                this.bottomValueTextSize = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomValueTextSize);
            } else if (index == R.styleable.PageNumSwitchView_front_top_min_text_value) {
                this.minTopValue = obtainStyledAttributes.getInt(index, this.minTopValue);
            } else if (index == R.styleable.PageNumSwitchView_front_top_max_text_value) {
                this.maxTopValue = obtainStyledAttributes.getInt(index, this.maxTopValue);
            } else if (index == R.styleable.PageNumSwitchView_post_background) {
                this.postBackgroundResId = obtainStyledAttributes.getResourceId(index, this.postBackgroundResId);
            } else if (index == R.styleable.PageNumSwitchView_front_background) {
                this.frontBackgroundResId = obtainStyledAttributes.getResourceId(index, this.frontBackgroundResId);
            } else if (index == R.styleable.PageNumSwitchView_pnsIsAutoDark) {
                this.isAutoDark = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.PageNumSwitchView_pnsIsDarkMode) {
                this.isDarkMode = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.PageNumSwitchView_pnsIsAutoElder) {
                this.isAutoElder = obtainStyledAttributes.getBoolean(index, false);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void refreshView() {
        BaseScenes baseScenes = this.frontCurrentTopBaseScenes;
        if (baseScenes != null) {
            baseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        }
        BaseScenes baseScenes2 = this.frontElderBaseScenes;
        if (baseScenes2 != null) {
            baseScenes2.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.topValue + "");
        }
        invalidate();
    }

    private void registerOnClickListener() {
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.pagenumswitch.PageNumSwitchView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PageNumSwitchView.this.listener == null || PageNumSwitchView.this.currentType != PageNumSwitchView.TYPE_POST) {
                    return;
                }
                PageNumSwitchView.this.listener.onClickGotoTop(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRootScenesVisible() {
        this.frontRootBaseScenes.setVisible(true);
        this.postRootBaseScenes.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetTopValueToFinalValue() {
        this.scenesMessageManager.clear();
        this.frontCurrentTopBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.finalValue + "");
        this.frontElderBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, this.finalValue + "");
        refreshView();
    }

    private void safeScenesRelease(BaseScenes baseScenes) {
        if (baseScenes != null) {
            baseScenes.release();
        }
    }

    private void setTopValueOnly(int i2) {
        this.topValue = i2;
    }

    private void startDecreaseScenesAnim() {
        if (this.initFinish) {
            Animation createUpToNormalAnim = PageNumSwitchScenesManager.createUpToNormalAnim(this.frontRootBaseScenes);
            Animation createNormalToBottomAnim = PageNumSwitchScenesManager.createNormalToBottomAnim(this.postRootBaseScenes);
            resetRootScenesVisible();
            this.scenesManager.startAnim(this.postRootBaseScenes, createNormalToBottomAnim, this.frontRootBaseScenes, createUpToNormalAnim, new PageNumSwitchScenesManager.OnScenesAnimListener() { // from class: com.jingdong.common.unification.pagenumswitch.PageNumSwitchView.2
                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimEnd() {
                    PageNumSwitchView.this.frontRootBaseScenes.setVisible(true);
                    PageNumSwitchView.this.postRootBaseScenes.setVisible(false);
                }

                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimStart() {
                    PageNumSwitchView.this.resetRootScenesVisible();
                    PageNumSwitchView.this.resetTopValueToFinalValue();
                    PageNumSwitchView.this.scenesMessageManager.enqueueScenesEmptyMessage(350);
                }
            });
        }
    }

    private void startDecreaseTopAnim(long j2) {
        if (this.initFinish) {
            Animation createUpToNormalAnim = PageNumSwitchScenesManager.createUpToNormalAnim(this.frontNextTopBaseScenes, j2);
            this.scenesManager.startAnim(this.frontCurrentTopBaseScenes, PageNumSwitchScenesManager.createNormalToBottomAnim(this.frontCurrentTopBaseScenes, j2), this.frontNextTopBaseScenes, createUpToNormalAnim, new PageNumSwitchScenesManager.OnScenesAnimListener() { // from class: com.jingdong.common.unification.pagenumswitch.PageNumSwitchView.4
                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimEnd() {
                    PageNumSwitchView.this.frontNextTopBaseScenes.setVisible(false);
                    PageNumSwitchView.this.frontCurrentTopBaseScenes.setVisible(true);
                }

                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimStart() {
                    PageNumSwitchView.this.frontNextTopBaseScenes.setVisible(true);
                    BaseScenes baseScenes = PageNumSwitchView.this.frontCurrentTopBaseScenes;
                    PageNumSwitchView pageNumSwitchView = PageNumSwitchView.this;
                    pageNumSwitchView.frontCurrentTopBaseScenes = pageNumSwitchView.frontNextTopBaseScenes;
                    PageNumSwitchView.this.frontNextTopBaseScenes = baseScenes;
                }
            });
        }
    }

    private void startIncreaseScenesAnim() {
        if (this.initFinish) {
            Animation createNormalToUpAnim = PageNumSwitchScenesManager.createNormalToUpAnim(this.frontRootBaseScenes);
            Animation createBottomToNormalAnim = PageNumSwitchScenesManager.createBottomToNormalAnim(this.postRootBaseScenes);
            resetRootScenesVisible();
            this.scenesManager.startAnim(this.frontRootBaseScenes, createNormalToUpAnim, this.postRootBaseScenes, createBottomToNormalAnim, new PageNumSwitchScenesManager.OnScenesAnimListener() { // from class: com.jingdong.common.unification.pagenumswitch.PageNumSwitchView.1
                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimEnd() {
                    PageNumSwitchView.this.frontRootBaseScenes.setVisible(false);
                    PageNumSwitchView.this.postRootBaseScenes.setVisible(true);
                }

                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimStart() {
                    PageNumSwitchView.this.resetRootScenesVisible();
                }
            });
        }
    }

    private void startIncreaseTopAnim(long j2) {
        if (this.initFinish) {
            Animation createNormalToUpAnim = PageNumSwitchScenesManager.createNormalToUpAnim(this.frontCurrentTopBaseScenes, j2);
            Animation createBottomToNormalAnim = PageNumSwitchScenesManager.createBottomToNormalAnim(this.frontNextTopBaseScenes, j2);
            this.scenesManager.startAnim(this.frontCurrentTopBaseScenes, createNormalToUpAnim, this.frontNextTopBaseScenes, createBottomToNormalAnim, new PageNumSwitchScenesManager.OnScenesAnimListener() { // from class: com.jingdong.common.unification.pagenumswitch.PageNumSwitchView.3
                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimEnd() {
                    PageNumSwitchView.this.frontNextTopBaseScenes.setVisible(false);
                    PageNumSwitchView.this.frontCurrentTopBaseScenes.setVisible(true);
                }

                @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.OnScenesAnimListener
                public void onAnimStart() {
                    PageNumSwitchView.this.frontNextTopBaseScenes.setVisible(true);
                    BaseScenes baseScenes = PageNumSwitchView.this.frontCurrentTopBaseScenes;
                    PageNumSwitchView pageNumSwitchView = PageNumSwitchView.this;
                    pageNumSwitchView.frontCurrentTopBaseScenes = pageNumSwitchView.frontNextTopBaseScenes;
                    PageNumSwitchView.this.frontNextTopBaseScenes = baseScenes;
                }
            });
        }
    }

    public void changeToFrontScenes() {
        int i2 = this.currentType;
        int i3 = TYPE_FRONT;
        if (i2 != i3) {
            this.currentType = i3;
            startDecreaseScenesAnim();
        }
    }

    public void changeToPostScenes() {
        int i2 = this.currentType;
        int i3 = TYPE_POST;
        if (i2 != i3) {
            this.currentType = i3;
            startIncreaseScenesAnim();
        }
    }

    public void decreaseTopValue() {
        setTopValue(this.topValue - 1);
    }

    public int getBottomValue() {
        return this.bottomValue;
    }

    public int getCurrentType() {
        return this.currentType;
    }

    public int getMaxTopValue() {
        return this.maxTopValue;
    }

    public int getMinTopValue() {
        return this.minTopValue;
    }

    public int getTopValue() {
        return this.topValue;
    }

    public void increaseTopValue() {
        setTopValue(this.topValue + 1);
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDark;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return this.isAutoElder;
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
        if (this.isAutoElder) {
            return UnWidgetThemeController.getInstance().isElderModel();
        }
        return this.isElderMode;
    }

    public boolean isFrontScenes() {
        return this.currentType == TYPE_FRONT;
    }

    public boolean isPostScenes() {
        return this.currentType == TYPE_POST;
    }

    @Override // com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesMessageManager.OnScenesAnimListener
    public void onChangeTopValue(int i2, int i3) {
        setTopValue(i2, i3, false);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.scenesManager.draw(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            this.initFinish = false;
            notifyScenesRelease();
            initScenes();
            this.initFinish = true;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(getCustomMeasureWidth(i2), getCustomMeasureHeight(i3));
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (view == this && i2 == 0) {
            refreshView();
        }
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isElderMode()) {
            elderMode();
        } else if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
        refreshView();
    }

    public void setBottomValue(int i2) {
        if (isNeedRefresh()) {
            this.frontBottomBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
            refreshView();
        }
        this.bottomValue = i2;
    }

    public void setBottomValueTextColor(int i2) {
        if (isNeedRefresh()) {
            this.frontBottomBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, i2);
            refreshView();
        }
        this.bottomValueTextColor = i2;
    }

    public void setBottomValueTextColorByResId(@ColorRes int i2) {
        setBottomValueTextColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setBottomValueTextSize(int i2) {
        if (isNeedRefresh()) {
            this.frontBottomBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, DpiUtil.sp2px(getContext(), i2));
            refreshView();
        }
        this.bottomValueTextSize = DpiUtil.sp2px(getContext(), i2);
    }

    public void setBottomValueTextType(Typeface typeface) {
        if (isNeedRefresh()) {
            this.frontBottomBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, typeface);
            refreshView();
        }
        this.frontBottomTextType = typeface;
    }

    public void setFrontScenesBackground(@DrawableRes int i2) {
        if (isNeedRefresh()) {
            this.frontBackgroundResId = i2;
            this.frontBgBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_BITMAP_OBJ, parseBitmapByResId(i2));
            refreshView();
        }
        this.frontBackgroundResId = i2;
    }

    public void setMaxTopValue(int i2) {
        if (i2 < this.minTopValue) {
            this.minTopValue = i2;
        }
        this.maxTopValue = i2;
        if (this.topValue > i2) {
            setTopValue(i2);
        }
    }

    public void setMinTopValue(int i2) {
        if (i2 > this.maxTopValue) {
            this.maxTopValue = i2;
        }
        this.minTopValue = i2;
        if (this.topValue < i2) {
            setTopValue(i2);
        }
    }

    public void setOnClickGotoTopListener(OnClickGotoTopListener onClickGotoTopListener) {
        this.listener = onClickGotoTopListener;
        registerOnClickListener();
    }

    public void setPostScenesBackground(@DrawableRes int i2) {
        if (isNeedRefresh()) {
            this.postBackgroundResId = i2;
            this.postBgBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_BITMAP_OBJ, parseBitmapByResId(i2));
            refreshView();
        }
        this.postBackgroundResId = i2;
    }

    public void setTopValue(int i2) {
        setTopValue(i2, 350L, true);
    }

    public void setTopValueTextColor(int i2) {
        if (isNeedRefresh()) {
            this.frontCurrentTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, i2);
            this.frontNextTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_COLOR_INT, i2);
            refreshView();
        }
        this.topValueTextColor = i2;
    }

    public void setTopValueTextColorByResId(@ColorRes int i2) {
        setTopValueTextColor(ContextCompat.getColor(getContext(), i2));
    }

    public void setTopValueTextSize(int i2) {
        if (isNeedRefresh()) {
            float f2 = i2;
            this.frontCurrentTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, DpiUtil.sp2px(getContext(), f2));
            this.frontNextTopBaseScenes.addOrUpdateIntData(ScenesConstant.Value.SCENES_TEXT_SIZE_INT, DpiUtil.sp2px(getContext(), f2));
            refreshView();
        }
        this.topValueTextSize = DpiUtil.sp2px(getContext(), i2);
    }

    public void setTopValueTextType(Typeface typeface) {
        if (isNeedRefresh()) {
            this.frontCurrentTopBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, typeface);
            this.frontNextTopBaseScenes.addOrUpdateObjData(ScenesConstant.Value.SCENES_TEXT_TYPE_INT, typeface);
            refreshView();
        }
        this.frontTopTextType = typeface;
    }

    public PageNumSwitchView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void setTopValue(int i2, long j2, boolean z) {
        if (checkTopValueInRange(i2) && i2 != this.topValue) {
            if (z) {
                if (this.finalValue == i2) {
                    return;
                }
                this.finalValue = i2;
                this.scenesMessageManager.enqueueScenesMessage(i2, (int) computeAnimNeedDuration());
                this.lastSetTopTime = System.currentTimeMillis();
                return;
            }
            if (this.currentType == TYPE_FRONT) {
                if (isNeedRefresh()) {
                    if (isElderMode() && i2 < 10) {
                        this.frontCurrentTopBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                        this.frontElderBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                        refreshView();
                    } else {
                        this.frontNextTopBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                        this.frontNextElderBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                        if (i2 > this.topValue) {
                            startIncreaseTopAnim(j2);
                        } else {
                            startDecreaseTopAnim(j2);
                        }
                    }
                }
            } else if (isNeedRefresh()) {
                this.frontCurrentTopBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                this.frontElderBaseScenes.addOrUpdateStrData(ScenesConstant.Value.SCENES_TEXT_STR, i2 + "");
                refreshView();
            }
            setTopValueOnly(i2);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView darkMode() {
        BaseScenes baseScenes = this.tempTop;
        if (baseScenes != null) {
            this.frontCurrentTopBaseScenes = baseScenes;
            this.frontNextTopBaseScenes = this.tempNetTop;
        }
        BaseScenes baseScenes2 = this.frontElderBaseScenes;
        if (baseScenes2 != null) {
            baseScenes2.setVisible(false);
        }
        BaseScenes baseScenes3 = this.frontNextElderBaseScenes;
        if (baseScenes3 != null) {
            baseScenes3.setVisible(false);
        }
        BaseScenes baseScenes4 = this.frontCurrentTopBaseScenes;
        if (baseScenes4 != null) {
            baseScenes4.setVisible(true);
        }
        LineScenes lineScenes = this.frontLineBaseScenes;
        if (lineScenes != null) {
            lineScenes.setVisible(true);
        }
        BaseScenes baseScenes5 = this.frontBottomBaseScenes;
        if (baseScenes5 != null) {
            baseScenes5.setVisible(true);
        }
        setBottomValueTextColor(getResources().getColor(R.color.c_C7C7C7));
        setTopValueTextColor(getResources().getColor(R.color.un_content_level_1_dark));
        setFrontScenesBackground(R.drawable.button_m_06_dark);
        setPostScenesBackground(R.drawable.button_m_01_01_dark);
        LineScenes lineScenes2 = this.frontLineBaseScenes;
        if (lineScenes2 != null) {
            lineScenes2.setDrawableId(R.drawable.un_page_num_swith_line);
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView elderMode() {
        this.frontCurrentTopBaseScenes.setVisible(false);
        this.frontLineBaseScenes.setVisible(false);
        this.frontBottomBaseScenes.setVisible(false);
        this.frontElderBaseScenes.setVisible(true);
        if (this.tempTop == null) {
            this.tempTop = this.frontCurrentTopBaseScenes;
            this.tempNetTop = this.frontNextTopBaseScenes;
            this.frontCurrentTopBaseScenes = this.frontElderBaseScenes;
            this.frontNextTopBaseScenes = this.frontNextElderBaseScenes;
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView normalMode() {
        BaseScenes baseScenes = this.tempTop;
        if (baseScenes != null) {
            this.frontCurrentTopBaseScenes = baseScenes;
            this.frontNextTopBaseScenes = this.tempNetTop;
        }
        BaseScenes baseScenes2 = this.frontElderBaseScenes;
        if (baseScenes2 != null) {
            baseScenes2.setVisible(false);
        }
        BaseScenes baseScenes3 = this.frontNextElderBaseScenes;
        if (baseScenes3 != null) {
            baseScenes3.setVisible(false);
        }
        BaseScenes baseScenes4 = this.frontCurrentTopBaseScenes;
        if (baseScenes4 != null) {
            baseScenes4.setVisible(true);
        }
        LineScenes lineScenes = this.frontLineBaseScenes;
        if (lineScenes != null) {
            lineScenes.setVisible(true);
        }
        BaseScenes baseScenes5 = this.frontBottomBaseScenes;
        if (baseScenes5 != null) {
            baseScenes5.setVisible(true);
        }
        setBottomValueTextColor(getResources().getColor(R.color.page_num_switch_text_color));
        setTopValueTextColor(getResources().getColor(R.color.page_num_switch_text_top_color));
        setFrontScenesBackground(R.drawable.button_m_06);
        setPostScenesBackground(R.drawable.button_m_01_01);
        LineScenes lineScenes2 = this.frontLineBaseScenes;
        if (lineScenes2 != null) {
            lineScenes2.setDrawableId(R.drawable.un_page_num_swith_line);
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView setAutoElderMode(boolean z) {
        this.isAutoElder = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public PageNumSwitchView setElderMode(boolean z) {
        this.isElderMode = z;
        return this;
    }

    public PageNumSwitchView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.currentType = TYPE_FRONT;
        this.topValueTextColor = parseColorByResId(R.color.page_num_switch_text_top_color);
        this.topValueTextSize = DpiUtil.sp2px(getContext(), 14.0f);
        this.maxTopValue = Integer.MAX_VALUE;
        this.minTopValue = Integer.MIN_VALUE;
        this.bottomValueTextColor = parseColorByResId(R.color.page_num_switch_text_color);
        this.bottomValueTextSize = DpiUtil.sp2px(getContext(), 11.0f);
        this.postBackgroundResId = R.drawable.button_m_01_01;
        this.frontBackgroundResId = R.drawable.page_num_switch_front_bg;
        this.frontTopTextType = null;
        this.frontBottomTextType = null;
        this.lastSetTopTime = 0L;
        this.initFinish = false;
        this.scenesManager = PageNumSwitchScenesManager.createScenesManager(this);
        PageNumSwitchScenesMessageManager createScenesMessageManager = PageNumSwitchScenesMessageManager.createScenesMessageManager();
        this.scenesMessageManager = createScenesMessageManager;
        createScenesMessageManager.setOnScenesAnimListener(this);
        parseConfig(attributeSet, i2);
        setLayerType(1, null);
    }
}
