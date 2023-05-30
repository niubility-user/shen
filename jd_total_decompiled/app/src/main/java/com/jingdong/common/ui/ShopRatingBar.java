package com.jingdong.common.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.DpiUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ShopRatingBar extends LinearLayout {
    private static final int MAX_SCORE = 5;
    private static final int MAX_STAR_COUNT = 5;
    private int almostScore;
    private Context context;
    private int emptyScore;
    private int fullScore;
    private int halfScore;
    private boolean hasText;
    private int height;
    private boolean isDark;
    private int score;
    private HashMap<Integer, ScoreResEntity> scoreResMap;
    private String str;

    /* renamed from: tv  reason: collision with root package name */
    private TextView f12435tv;

    /* loaded from: classes6.dex */
    public static class ScoreResEntity {
        public int darkRes;
        public int lightRes;

        public ScoreResEntity() {
        }

        public ScoreResEntity(int i2, int i3) {
            this.lightRes = i2;
            this.darkRes = i3;
        }
    }

    public ShopRatingBar(Context context) {
        this(context, null);
    }

    private double decimals(double d) {
        String valueOf = String.valueOf(d);
        return valueOf.length() > 3 ? Double.valueOf(valueOf.substring(0, 3)).doubleValue() : d;
    }

    private void initChildViews() {
        removeAllViews();
        if (this.hasText) {
            TextView textView = new TextView(this.context);
            this.f12435tv = textView;
            textView.setText(this.str);
            this.f12435tv.setTextSize(10.0f);
            this.f12435tv.setTextColor(getResources().getColor(this.isDark ? R.color.white : R.color.c_2E2D2D));
            addView(this.f12435tv);
        }
        int i2 = this.score / 10;
        for (int i3 = 1; i3 <= 5; i3++) {
            ImageView imageView = new ImageView(this.context);
            if (i3 <= i2) {
                imageView.setImageResource(this.scoreResMap.get(Integer.valueOf(this.fullScore)).darkRes);
            } else if (i3 == i2 + 1) {
                int i4 = this.score - (i2 * 10);
                if (this.scoreResMap.containsKey(Integer.valueOf(i4))) {
                    ScoreResEntity scoreResEntity = this.scoreResMap.get(Integer.valueOf(i4));
                    if (this.isDark) {
                        int i5 = scoreResEntity.darkRes;
                        if (i5 == 0) {
                            i5 = this.scoreResMap.get(Integer.valueOf(this.halfScore)).darkRes;
                        }
                        imageView.setImageResource(i5);
                    } else {
                        int i6 = scoreResEntity.lightRes;
                        if (i6 == 0) {
                            i6 = this.scoreResMap.get(Integer.valueOf(this.halfScore)).lightRes;
                        }
                        imageView.setImageResource(i6);
                    }
                } else {
                    imageView.setImageResource(this.isDark ? this.scoreResMap.get(Integer.valueOf(this.halfScore)).darkRes : this.scoreResMap.get(Integer.valueOf(this.halfScore)).lightRes);
                }
            } else {
                imageView.setImageResource(this.isDark ? this.scoreResMap.get(Integer.valueOf(this.emptyScore)).darkRes : this.scoreResMap.get(Integer.valueOf(this.emptyScore)).lightRes);
            }
            addView(imageView);
        }
    }

    private void initScoreResMap() {
        for (int i2 = 0; i2 < 11; i2++) {
            this.scoreResMap.put(Integer.valueOf(i2), new ScoreResEntity());
        }
        this.scoreResMap.get(Integer.valueOf(this.emptyScore)).darkRes = R.drawable.no_star_dark_new;
        this.scoreResMap.get(Integer.valueOf(this.emptyScore)).lightRes = R.drawable.no_star_light_new;
        int i3 = R.drawable.star_half_dark_new;
        this.scoreResMap.get(Integer.valueOf(this.halfScore)).darkRes = i3;
        int i4 = R.drawable.star_half_light_new;
        this.scoreResMap.get(Integer.valueOf(this.halfScore)).lightRes = i4;
        this.scoreResMap.get(Integer.valueOf(this.almostScore)).darkRes = i3;
        this.scoreResMap.get(Integer.valueOf(this.almostScore)).lightRes = i4;
        this.scoreResMap.get(Integer.valueOf(this.fullScore)).darkRes = R.drawable.star_full_dark;
        this.scoreResMap.get(Integer.valueOf(this.fullScore)).lightRes = R.drawable.star_full_light;
    }

    public void isCustomStar(boolean z) {
        HashMap<Integer, ScoreResEntity> hashMap;
        if (z && (hashMap = this.scoreResMap) != null) {
            hashMap.clear();
        }
        setFullAndEmptyStar(0, 0, 0);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int dip2px;
        int measuredWidth;
        int dip2px2;
        int childCount = getChildCount();
        int i6 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            if (i6 == 0) {
                if (this.hasText) {
                    dip2px = DpiUtil.dip2px(this.context, 7.0f);
                } else {
                    dip2px = DpiUtil.dip2px(this.context, 6.0f);
                }
                measuredWidth = childAt.getMeasuredWidth();
            } else if (i6 == 1) {
                if (this.hasText) {
                    dip2px2 = DpiUtil.dip2px(this.context, 4.0f);
                } else {
                    dip2px2 = DpiUtil.dip2px(this.context, 2.0f);
                }
                dip2px = i4 + dip2px2;
                measuredWidth = childAt.getMeasuredWidth();
            } else {
                dip2px = i4 + DpiUtil.dip2px(this.context, 2.0f);
                measuredWidth = childAt.getMeasuredWidth();
            }
            int i7 = measuredWidth + dip2px;
            int measuredHeight = (this.height - childAt.getMeasuredHeight()) / 2;
            childAt.layout(dip2px, measuredHeight, i7, childAt.getMeasuredHeight() + measuredHeight);
            i6++;
            i4 = i7;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int dip2px;
        int dip2px2;
        super.onMeasure(i2, i3);
        measureChildren(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int dip2px3 = DpiUtil.dip2px(this.context, 15.0f);
        this.height = dip2px3;
        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            i4 += getChildAt(i5).getMeasuredWidth();
        }
        if (this.hasText) {
            dip2px = (DpiUtil.dip2px(this.context, 7.0f) * 2) + DpiUtil.dip2px(this.context, 4.0f);
            dip2px2 = DpiUtil.dip2px(this.context, 2.0f);
        } else {
            dip2px = DpiUtil.dip2px(this.context, 6.0f) * 2;
            dip2px2 = DpiUtil.dip2px(this.context, 2.0f);
        }
        int i6 = i4 + dip2px + (dip2px2 * 4);
        boolean z = mode == Integer.MIN_VALUE || mode == 0;
        boolean z2 = mode2 == Integer.MIN_VALUE || mode2 == 0;
        if (z && z2) {
            setMeasuredDimension(i6, dip2px3);
        } else if (z) {
            setMeasuredDimension(i6, size2);
        } else if (z2) {
            setMeasuredDimension(size, dip2px3);
        }
    }

    public void setCustomStar(double d, int i2, int i3) {
        if (d >= 1.0d || d <= 0.0d) {
            return;
        }
        int decimals = (int) (decimals(d) * 10.0d);
        if (this.scoreResMap.containsKey(Integer.valueOf(decimals))) {
            this.scoreResMap.get(Integer.valueOf(decimals)).lightRes = i2;
            this.scoreResMap.get(Integer.valueOf(decimals)).darkRes = i3;
            return;
        }
        this.scoreResMap.put(Integer.valueOf(decimals), new ScoreResEntity(i2, i3));
    }

    public void setDarkMode(boolean z) {
        this.isDark = z;
        setBackgroundResource(z ? R.drawable.dark_bg_shop_rating_bar : R.drawable.light_bg_shop_rating_bar);
    }

    public void setFullAndEmptyStar(int i2, int i3, int i4) {
        if (i2 == 0) {
            if (this.isDark) {
                i2 = R.drawable.star_full_light;
            } else {
                i2 = R.drawable.star_full_dark;
            }
        }
        if (i3 == 0 && !this.isDark) {
            i3 = R.drawable.no_star_light_new;
        }
        if (i4 == 0 && this.isDark) {
            i4 = R.drawable.no_star_dark_new;
        }
        if (this.scoreResMap.containsKey(Integer.valueOf(this.fullScore))) {
            this.scoreResMap.get(Integer.valueOf(this.fullScore)).lightRes = i2;
            this.scoreResMap.get(Integer.valueOf(this.fullScore)).darkRes = i2;
        } else {
            this.scoreResMap.put(Integer.valueOf(this.fullScore), new ScoreResEntity(i2, i2));
        }
        if (this.scoreResMap.containsKey(Integer.valueOf(this.emptyScore))) {
            this.scoreResMap.get(Integer.valueOf(this.emptyScore)).lightRes = i3;
            this.scoreResMap.get(Integer.valueOf(this.emptyScore)).darkRes = i4;
            return;
        }
        this.scoreResMap.put(Integer.valueOf(this.emptyScore), new ScoreResEntity(i3, i4));
    }

    public void setScore(double d) {
        if (d < 0.0d) {
            this.score = 0;
        } else if (d > 5.0d) {
            this.score = 50;
        } else {
            this.score = (int) (decimals(d) * 10.0d);
        }
        initChildViews();
    }

    public void setText(String str) {
        this.str = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.hasText = true;
    }

    public void setTextColor(int i2) {
        TextView textView = this.f12435tv;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public ShopRatingBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShopRatingBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.fullScore = 10;
        this.emptyScore = 0;
        this.halfScore = 5;
        this.almostScore = 9;
        this.context = context;
        setOrientation(0);
        setGravity(16);
        this.scoreResMap = new HashMap<>();
        initScoreResMap();
        setBackgroundResource(R.drawable.light_bg_shop_rating_bar);
    }
}
