package com.jingdong.common.widget.custom.livewidget.loopgallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public class CustomizedCarouselFigureViewPager extends ViewPager implements View.OnTouchListener {
    private static final String TAG = "CustomizedCarouselFigureViewPager";
    protected boolean isCarousel;
    float lastX;
    float lastY;
    private boolean mDispatchTouch;
    private boolean mFirstLayout;
    private boolean mOnTouchFlag;
    private ViewPager.OnPageChangeListener mOuterPageChangeListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private View.OnTouchListener onTouchListener;
    private ViewGroup parent;
    private float xLast;
    private float yLast;

    public CustomizedCarouselFigureViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.isCarousel = false;
        this.mOnTouchFlag = false;
        this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1
            private float mPreviousOffset = -1.0f;
            private float mPreviousPosition = -1.0f;

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
                if (CustomizedCarouselFigureViewPager.this.getAdapter() != null) {
                    CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager = CustomizedCarouselFigureViewPager.this;
                    if (customizedCarouselFigureViewPager.isCarousel && customizedCarouselFigureViewPager.getAdapter().getCount() > 3) {
                        int currentItem = CustomizedCarouselFigureViewPager.this.getCurrentItem();
                        boolean z = true;
                        if ((i2 != 0 && i2 != 1) || (currentItem != 1 && currentItem != CustomizedCarouselFigureViewPager.this.getAdapter().getCount() - 2)) {
                            z = false;
                        }
                        if (z) {
                            CustomizedCarouselFigureViewPager.this.setCurrentItem(CustomizedCarouselFigureViewPager.this.getEdgeNextPosition(currentItem), false);
                        }
                    }
                }
                if (CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                    CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i2);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
                CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager = CustomizedCarouselFigureViewPager.this;
                if (customizedCarouselFigureViewPager.isCarousel && customizedCarouselFigureViewPager.getAdapter() != null && CustomizedCarouselFigureViewPager.this.getAdapter().getCount() > 3) {
                    CustomizedCarouselFigureViewPager.this.getEdgeNextPosition(i2);
                    if (f2 == 0.0f && this.mPreviousOffset == 0.0f && (i2 == 1 || i2 == CustomizedCarouselFigureViewPager.this.getAdapter().getCount() + (-2))) {
                        CustomizedCarouselFigureViewPager.this.post(new Runnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0048: INVOKE 
                              (wrap: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager : 0x0041: IGET 
                              (r6v0 'this' com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1 A[IMMUTABLE_TYPE, THIS])
                             A[WRAPPED] (LINE:5) com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.this$0 com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager)
                              (wrap: java.lang.Runnable : 0x0045: CONSTRUCTOR 
                              (r6v0 'this' com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1 A[IMMUTABLE_TYPE, THIS])
                              (r0 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                             A[MD:(com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1, int):void (m), WRAPPED] call: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.1.<init>(com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1, int):void type: CONSTRUCTOR)
                             type: VIRTUAL call: android.view.ViewGroup.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:5) in method: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.onPageScrolled(int, float, int):void, file: classes12.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            boolean r1 = r0.isCarousel
                            r2 = 1
                            r3 = 0
                            r4 = 0
                            if (r1 == 0) goto L4b
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            if (r0 == 0) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            int r0 = r0.getCount()
                            r1 = 3
                            if (r0 <= r1) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$100(r0, r7)
                            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                            if (r1 != 0) goto L3e
                            float r1 = r6.mPreviousOffset
                            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                            if (r1 != 0) goto L3e
                            if (r7 == r2) goto L3c
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r1 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r1 = r1.getAdapter()
                            int r1 = r1.getCount()
                            int r1 = r1 + (-2)
                            if (r7 != r1) goto L3e
                        L3c:
                            r1 = 1
                            goto L3f
                        L3e:
                            r1 = 0
                        L3f:
                            if (r1 == 0) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r1 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1$1 r5 = new com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1$1
                            r5.<init>()
                            r1.post(r5)
                        L4b:
                            r6.mPreviousOffset = r8
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r7 = r0.toRealPosition(r7)
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r0)
                            if (r0 == 0) goto L90
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            if (r0 == 0) goto L76
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r0 = r0.getRealCount()
                            int r0 = r0 - r2
                            if (r7 == r0) goto L76
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r0)
                            r0.onPageScrolled(r7, r8, r9)
                            goto L90
                        L76:
                            double r8 = (double) r8
                            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                            if (r2 <= 0) goto L87
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r7 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r7 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r7)
                            r7.onPageScrolled(r3, r4, r3)
                            goto L90
                        L87:
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r8 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r8 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r8)
                            r8.onPageScrolled(r7, r4, r3)
                        L90:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.AnonymousClass1.onPageScrolled(int, float, int):void");
                    }

                    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                    public void onPageSelected(int i2) {
                        int realPosition = CustomizedCarouselFigureViewPager.this.toRealPosition(i2);
                        float f2 = realPosition;
                        if (this.mPreviousPosition != f2) {
                            this.mPreviousPosition = f2;
                            if (CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                                CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                            }
                        }
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int getEdgeNextPosition(int i2) {
                if (!this.isCarousel || getRealCount() <= 1) {
                    return i2;
                }
                if (i2 == 1) {
                    return getRealCount() + 1;
                }
                if (i2 == getRealCount() + 2) {
                    return 2;
                }
                return i2;
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (this.mDispatchTouch) {
                    return super.dispatchTouchEvent(motionEvent);
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    View.OnTouchListener onTouchListener = this.onTouchListener;
                    if (onTouchListener != null) {
                        onTouchListener.onTouch(null, motionEvent);
                    }
                } else if (action == 2) {
                    float f2 = 0;
                    float f3 = rawX;
                    float f4 = rawY;
                    if (((int) (Math.abs(f3 - this.lastX) + f2)) >= ((int) (f2 + Math.abs(f4 - this.lastY)))) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    this.lastX = f3;
                    this.lastY = f4;
                }
                boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
                Log.d(TAG, "dispatchTouchEvent ->  result = " + dispatchTouchEvent);
                return dispatchTouchEvent;
            }

            public int getRealCount() {
                if (getAdapter() == null) {
                    return 0;
                }
                if (this.isCarousel && getAdapter().getCount() > 3) {
                    return getAdapter().getCount() - 4;
                }
                return getAdapter().getCount();
            }

            public void init(ViewGroup viewGroup, boolean z) {
                this.parent = viewGroup;
                this.isCarousel = z;
                super.setOnTouchListener(this);
                super.setOnPageChangeListener(this.onPageChangeListener);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                if (this.mFirstLayout) {
                    super.onAttachedToWindow();
                }
                this.mFirstLayout = false;
            }

            @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
                boolean z;
                try {
                    z = super.onInterceptTouchEvent(motionEvent);
                    try {
                        int action = motionEvent.getAction();
                        if (action != 0) {
                            if (action == 2 && Math.abs(this.xLast - motionEvent.getX()) < 10.0f) {
                                Math.abs(this.yLast - motionEvent.getY());
                            }
                        } else {
                            this.xLast = motionEvent.getX();
                            this.yLast = motionEvent.getY();
                        }
                        Log.d(TAG, "dispatchTouchEvent ->  result = " + z);
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        e.printStackTrace();
                        return z;
                    }
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    z = false;
                }
                return z;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.mOnTouchFlag) {
                    return onTouchEvent(motionEvent);
                }
                View.OnTouchListener onTouchListener = this.onTouchListener;
                if (onTouchListener != null) {
                    onTouchListener.onTouch(view, motionEvent);
                }
                int action = motionEvent.getAction();
                if (action != 1 && action != 3) {
                    ViewGroup viewGroup = this.parent;
                    if (viewGroup != null) {
                        viewGroup.requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    ViewGroup viewGroup2 = this.parent;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(false);
                    }
                    View.OnTouchListener onTouchListener2 = this.onTouchListener;
                    if (onTouchListener2 != null) {
                        onTouchListener2.onTouch(view, motionEvent);
                    }
                }
                try {
                    onTouchEvent(motionEvent);
                } catch (Throwable th) {
                    if (Log.E) {
                        th.printStackTrace();
                    }
                }
                return true;
            }

            @Override // androidx.viewpager.widget.ViewPager, android.view.View
            public boolean onTouchEvent(MotionEvent motionEvent) {
                boolean z;
                try {
                    z = super.onTouchEvent(motionEvent);
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    Log.d(TAG, "onTouchEvent ->  result = " + z);
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    e.printStackTrace();
                    return z;
                }
                return z;
            }

            @Override // androidx.viewpager.widget.ViewPager
            public void setAdapter(PagerAdapter pagerAdapter) {
                super.setAdapter(pagerAdapter);
                if (!this.isCarousel || pagerAdapter.getCount() <= 1) {
                    return;
                }
                setCurrentItem(2, false);
            }

            public void setDispatchTouch(boolean z) {
                this.mDispatchTouch = z;
            }

            @Override // androidx.viewpager.widget.ViewPager
            public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
                this.mOuterPageChangeListener = onPageChangeListener;
            }

            public void setOnTouchFlag(boolean z) {
                this.mOnTouchFlag = z;
            }

            @Override // android.view.View
            public void setOnTouchListener(View.OnTouchListener onTouchListener) {
                this.onTouchListener = onTouchListener;
            }

            public int toRealPosition(int i2) {
                if (getAdapter() == null) {
                    return 0;
                }
                if (this.isCarousel) {
                    int realCount = getRealCount();
                    if (realCount == 0) {
                        return 0;
                    }
                    int i3 = (i2 - 2) % realCount;
                    return i3 < 0 ? i3 + realCount : i3;
                }
                return i2;
            }

            public CustomizedCarouselFigureViewPager(Context context) {
                super(context);
                this.mFirstLayout = true;
                this.isCarousel = false;
                this.mOnTouchFlag = false;
                this.onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1
                    private float mPreviousOffset = -1.0f;
                    private float mPreviousPosition = -1.0f;

                    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                    public void onPageScrollStateChanged(int i2) {
                        if (CustomizedCarouselFigureViewPager.this.getAdapter() != null) {
                            CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager = CustomizedCarouselFigureViewPager.this;
                            if (customizedCarouselFigureViewPager.isCarousel && customizedCarouselFigureViewPager.getAdapter().getCount() > 3) {
                                int currentItem = CustomizedCarouselFigureViewPager.this.getCurrentItem();
                                boolean z = true;
                                if ((i2 != 0 && i2 != 1) || (currentItem != 1 && currentItem != CustomizedCarouselFigureViewPager.this.getAdapter().getCount() - 2)) {
                                    z = false;
                                }
                                if (z) {
                                    CustomizedCarouselFigureViewPager.this.setCurrentItem(CustomizedCarouselFigureViewPager.this.getEdgeNextPosition(currentItem), false);
                                }
                            }
                        }
                        if (CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                            CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener.onPageScrollStateChanged(i2);
                        }
                    }

                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0048: INVOKE 
                          (wrap: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager : 0x0041: IGET 
                          (r6v0 'this' com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1 A[IMMUTABLE_TYPE, THIS])
                         A[WRAPPED] (LINE:5) com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.this$0 com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager)
                          (wrap: java.lang.Runnable : 0x0045: CONSTRUCTOR 
                          (r6v0 'this' com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1 A[IMMUTABLE_TYPE, THIS])
                          (r0 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1, int):void (m), WRAPPED] call: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.1.<init>(com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1, int):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.view.ViewGroup.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:5) in method: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.1.onPageScrolled(int, float, int):void, file: classes12.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        Caused by: java.lang.NullPointerException
                        */
                    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                    public void onPageScrolled(int r7, float r8, int r9) {
                        /*
                            r6 = this;
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            boolean r1 = r0.isCarousel
                            r2 = 1
                            r3 = 0
                            r4 = 0
                            if (r1 == 0) goto L4b
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            if (r0 == 0) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            int r0 = r0.getCount()
                            r1 = 3
                            if (r0 <= r1) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$100(r0, r7)
                            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                            if (r1 != 0) goto L3e
                            float r1 = r6.mPreviousOffset
                            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                            if (r1 != 0) goto L3e
                            if (r7 == r2) goto L3c
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r1 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r1 = r1.getAdapter()
                            int r1 = r1.getCount()
                            int r1 = r1 + (-2)
                            if (r7 != r1) goto L3e
                        L3c:
                            r1 = 1
                            goto L3f
                        L3e:
                            r1 = 0
                        L3f:
                            if (r1 == 0) goto L4b
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r1 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1$1 r5 = new com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager$1$1
                            r5.<init>()
                            r1.post(r5)
                        L4b:
                            r6.mPreviousOffset = r8
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r7 = r0.toRealPosition(r7)
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r0)
                            if (r0 == 0) goto L90
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
                            if (r0 == 0) goto L76
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            int r0 = r0.getRealCount()
                            int r0 = r0 - r2
                            if (r7 == r0) goto L76
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r0)
                            r0.onPageScrolled(r7, r8, r9)
                            goto L90
                        L76:
                            double r8 = (double) r8
                            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                            if (r2 <= 0) goto L87
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r7 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r7 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r7)
                            r7.onPageScrolled(r3, r4, r3)
                            goto L90
                        L87:
                            com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager r8 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.this
                            androidx.viewpager.widget.ViewPager$OnPageChangeListener r8 = com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.access$000(r8)
                            r8.onPageScrolled(r7, r4, r3)
                        L90:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.custom.livewidget.loopgallery.CustomizedCarouselFigureViewPager.AnonymousClass1.onPageScrolled(int, float, int):void");
                    }

                    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                    public void onPageSelected(int i2) {
                        int realPosition = CustomizedCarouselFigureViewPager.this.toRealPosition(i2);
                        float f2 = realPosition;
                        if (this.mPreviousPosition != f2) {
                            this.mPreviousPosition = f2;
                            if (CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener != null) {
                                CustomizedCarouselFigureViewPager.this.mOuterPageChangeListener.onPageSelected(realPosition);
                            }
                        }
                    }
                };
            }
        }
