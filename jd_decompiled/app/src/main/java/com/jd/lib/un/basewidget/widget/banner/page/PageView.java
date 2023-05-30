package com.jd.lib.un.basewidget.widget.banner.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.banner.BannerAdapter;
import com.jd.lib.un.basewidget.widget.banner.BannerView;

/* loaded from: classes16.dex */
public class PageView extends RelativeLayout implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
    private BannerAdapter bannerAdapter;
    private BannerView bannerView;
    private TextView divideLineTv;
    private TextView endValueTv;
    private int mDivideLineColor;
    private int mDivideLineSize;
    private int mEndValueColor;
    private int mEndValueSize;
    private int mMaxValue;
    private int mMinValue;
    private int mValue;
    private int mValueColor;
    private int mValueSize;
    private TextView valueTv;

    public PageView(Context context) {
        this(context, null);
    }

    private void configDivideWidth() {
        float parseStrWidth = parseStrWidth(this.divideLineTv.getPaint(), "/");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.divideLineTv.getLayoutParams();
        layoutParams.width = (int) parseStrWidth;
        this.divideLineTv.setLayoutParams(layoutParams);
    }

    private void configEndValueWidth() {
        float parseStrWidth = parseStrWidth(this.endValueTv.getPaint(), this.mMaxValue + "");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.endValueTv.getLayoutParams();
        layoutParams.width = (int) parseStrWidth;
        this.endValueTv.setLayoutParams(layoutParams);
    }

    private void configValueWidth() {
        float parseStrWidth = parseStrWidth(this.valueTv.getPaint(), this.mMaxValue + "");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.valueTv.getLayoutParams();
        layoutParams.width = (int) parseStrWidth;
        this.valueTv.setLayoutParams(layoutParams);
    }

    private void configWidth() {
        configValueWidth();
        configDivideWidth();
        configEndValueWidth();
    }

    private int getCount() {
        BannerAdapter bannerAdapter = this.bannerAdapter;
        if (bannerAdapter == null) {
            return 0;
        }
        return bannerAdapter.getItemCount();
    }

    @SuppressLint({"ResourceType"})
    private void initView() {
        setGravity(17);
        setBackgroundResource(R.drawable.page_num_bg);
        TextView textView = new TextView(getContext());
        this.valueTv = textView;
        textView.setTextColor(this.mValueColor);
        this.valueTv.setTextSize(this.mValueSize);
        this.valueTv.setText(this.mValue + "");
        this.valueTv.setId(17);
        addView(this.valueTv);
        TextView textView2 = new TextView(getContext());
        this.divideLineTv = textView2;
        textView2.setTextColor(this.mDivideLineColor);
        this.divideLineTv.setTextSize(this.mDivideLineColor);
        this.divideLineTv.setText("/");
        this.divideLineTv.setId(18);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(1, 17);
        layoutParams.addRule(4, 17);
        addView(this.divideLineTv, layoutParams);
        TextView textView3 = new TextView(getContext());
        this.endValueTv = textView3;
        textView3.setTextSize(this.mEndValueSize);
        this.endValueTv.setTextColor(this.mEndValueColor);
        this.endValueTv.setText(this.endValueTv + "");
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, 18);
        layoutParams2.addRule(4, 18);
        addView(this.endValueTv, layoutParams2);
        setPadding(30, 0, 30, 5);
    }

    private float parseStrWidth(Paint paint, String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        return paint.measureText(str);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
        if (pagerAdapter2 == null || !(pagerAdapter2 instanceof BannerAdapter)) {
            return;
        }
        this.bannerAdapter = (BannerAdapter) pagerAdapter2;
        this.bannerView.setCurrentItem(0);
        setValue(1);
        setMaxValue(getCount());
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        setValue(i2 + 1);
    }

    public void setBannerView(BannerView bannerView) {
        this.bannerView = bannerView;
        this.bannerAdapter = bannerView.getAdapter();
        this.bannerView.removeOnPageChangeListener(this);
        this.bannerView.addOnPageChangeListener(this);
        this.bannerView.removeOnAdapterChangeListener(this);
        this.bannerView.addOnAdapterChangeListener(this);
        setMaxValue(getCount());
    }

    public void setDivideLineColor(int i2) {
        this.mDivideLineColor = i2;
        this.divideLineTv.setTextColor(i2);
    }

    public void setDivideLineSize(int i2) {
        this.mDivideLineSize = i2;
        configDivideWidth();
    }

    public void setEndValueColor(int i2) {
        this.mEndValueColor = i2;
        this.endValueTv.setTextColor(i2);
    }

    public void setEndValueSize(int i2) {
        this.mEndValueSize = i2;
        configEndValueWidth();
    }

    public void setMaxValue(int i2) {
        this.mMaxValue = i2;
        this.endValueTv.setText(i2 + "");
        configValueWidth();
        configEndValueWidth();
    }

    public void setValue(int i2) {
        this.valueTv.setText(i2 + "");
    }

    public void setValueColor(int i2) {
        this.mValueColor = i2;
        this.valueTv.setTextColor(i2);
    }

    public void setValueSize(int i2) {
        this.mValueSize = i2;
        configValueWidth();
    }

    public PageView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PageView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMinValue = 1;
        this.mMaxValue = 1;
        this.mValueSize = 16;
        this.mEndValueSize = 12;
        this.mDivideLineSize = 12;
        this.mValueColor = -1;
        this.mDivideLineColor = -1;
        this.mEndValueColor = -1;
        this.mValue = 1;
        initView();
        configWidth();
    }
}
