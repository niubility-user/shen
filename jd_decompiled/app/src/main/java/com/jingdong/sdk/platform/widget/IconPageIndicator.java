package com.jingdong.sdk.platform.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class IconPageIndicator extends HorizontalScrollView implements PageIndicator {
    public static final int ORDER_INDICATOR_COUNT = 7;
    private Runnable mIconSelector;
    private final LinearLayout mIconsLayout;
    private ViewPager.OnPageChangeListener mListener;
    private int mSelectedIndex;
    private ViewPager mViewPager;

    public IconPageIndicator(Context context) {
        this(context, null);
    }

    private void animateToIcon(int i2) {
        this.mIconsLayout.getChildAt(i2);
        Runnable runnable = this.mIconSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: CONSTRUCTOR (r0v2 'runnable2' java.lang.Runnable) = 
              (r1v0 'this' com.jingdong.sdk.platform.widget.IconPageIndicator A[IMMUTABLE_TYPE, THIS])
              (r2 I:android.view.View A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.jingdong.sdk.platform.widget.IconPageIndicator, android.view.View):void (m)] (LINE:4) call: com.jingdong.sdk.platform.widget.IconPageIndicator.1.<init>(com.jingdong.sdk.platform.widget.IconPageIndicator, android.view.View):void type: CONSTRUCTOR in method: com.jingdong.sdk.platform.widget.IconPageIndicator.animateToIcon(int):void, file: classes10.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            android.widget.LinearLayout r0 = r1.mIconsLayout
            android.view.View r2 = r0.getChildAt(r2)
            java.lang.Runnable r0 = r1.mIconSelector
            if (r0 == 0) goto Ld
            r1.removeCallbacks(r0)
        Ld:
            com.jingdong.sdk.platform.widget.IconPageIndicator$1 r0 = new com.jingdong.sdk.platform.widget.IconPageIndicator$1
            r0.<init>()
            r1.mIconSelector = r0
            r1.post(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.widget.IconPageIndicator.animateToIcon(int):void");
    }

    public void addImageView(IconPagerAdapter iconPagerAdapter, int i2) {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        int dip2px = DPIUtil.dip2px(5.0f);
        layoutParams.width = dip2px;
        layoutParams.height = dip2px;
        imageView.setImageResource(iconPagerAdapter.getIconResId(i2));
        this.mIconsLayout.addView(imageView, layoutParams);
    }

    @Override // com.jingdong.sdk.platform.widget.PageIndicator
    public void notifyDataSetChanged() {
        this.mIconsLayout.removeAllViews();
        PagerAdapter adapter = this.mViewPager.getAdapter();
        if (adapter instanceof IconPagerAdapter) {
            IconPagerAdapter iconPagerAdapter = (IconPagerAdapter) adapter;
            int count = iconPagerAdapter.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                addImageView(iconPagerAdapter, i2);
            }
            if (this.mSelectedIndex > count) {
                this.mSelectedIndex = count - 1;
            }
            setCurrentItem(this.mSelectedIndex);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.mIconSelector;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.mIconSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i2, f2, i3);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        setCurrentItem(i2);
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i2);
        }
    }

    public void reset() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        notifyDataSetChanged();
        this.mViewPager = new ViewPager(getContext());
    }

    @Override // com.jingdong.sdk.platform.widget.PageIndicator
    public void setCurrentItem(int i2) {
        if (this.mViewPager != null) {
            this.mSelectedIndex = i2;
            int childCount = this.mIconsLayout.getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.mIconsLayout.getChildAt(i3);
                boolean z = i3 == i2;
                childAt.setSelected(z);
                if (z) {
                    animateToIcon(i2);
                }
                i3++;
            }
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    @Override // com.jingdong.sdk.platform.widget.PageIndicator
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    @Override // com.jingdong.sdk.platform.widget.PageIndicator
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.addOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.mViewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
            notifyDataSetChanged();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setHorizontalScrollBarEnabled(false);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mIconsLayout = linearLayout;
        addView(linearLayout, new FrameLayout.LayoutParams(-2, -1, 17));
    }

    @Override // com.jingdong.sdk.platform.widget.PageIndicator
    public void setViewPager(ViewPager viewPager, int i2) {
        setViewPager(viewPager);
        setCurrentItem(i2);
    }
}
