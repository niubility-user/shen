package com.jingdong.content.component.widget.immersionbanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.content.component.R;
import com.jingdong.content.component.entity.ImmersionItemEntity;
import com.jingdong.content.component.util.VolumeChangeHelper;
import com.jingdong.content.component.widget.immersionbanner.ClickEventConstants;
import com.jingdong.content.component.widget.videocontrol.ContentVideoHolderView;
import com.jingdong.content.component.widget.videocontrol.IPlayClickListener;
import com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder;
import com.jingdong.content.component.widget.videocontrol.IVoiceClickListener;
import com.jingdong.content.component.widget.videocontrol.PlayerListManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;

/* loaded from: classes12.dex */
public class FaXianImmersionBannerView extends FrameLayout implements VolumeChangeHelper.a {
    public static final String TAG = "FXBannerView";
    private boolean fragmentIsResumed;
    private IBannerViewClick iBannerViewClick;
    private IBannerViewExpo iBannerViewExpo;
    private ContentLoopViewPager immersionBannerVP;
    private ViewPager.OnPageChangeListener immersionPageChangeListener;
    private HashMap<String, ImmersionItemEntity> indicateExpoList;
    private CustomViewPager indicatorBannerVP;
    private FrameLayout indicatorContainerFl;
    private boolean isBannerLoopling;
    private int lastStateIdlePosition;
    private Context mContext;
    private FaXianImmersionBannerAdapter mImmersionAdapter;
    private FaXianIndicatorBannerAdapter mIndicatorAdapter;
    private List<ImmersionItemEntity> mTopBanners;
    private boolean mVideoSharePlayer;
    private PlayerListManager playerListManager;
    private HashMap<String, ImmersionItemEntity> topExpoList;
    private boolean videoAutoPlay;
    private ViewParam viewParam;
    private VolumeChangeHelper volumeChangeHelper;

    public FaXianImmersionBannerView(@NonNull Context context) {
        this(context, null);
    }

    private void addExpoEntity(int i2, HashMap<String, ImmersionItemEntity> hashMap) {
        ImmersionItemEntity itemEntity = getItemEntity(i2);
        if (itemEntity == null || hashMap.containsKey(itemEntity.id)) {
            return;
        }
        hashMap.put(itemEntity.id, itemEntity);
    }

    private void clearExpoList() {
        HashMap<String, ImmersionItemEntity> hashMap = this.topExpoList;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<String, ImmersionItemEntity> hashMap2 = this.indicateExpoList;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickSelect(int i2, int i3, int i4) {
        if (this.indicatorBannerVP == null || this.mIndicatorAdapter == null) {
            return;
        }
        int scrollPos = getScrollPos(i2 - 2, i3, i4, -2);
        int scrollPos2 = getScrollPos(i2 - 1, i3, i4, -1);
        int scrollPos3 = getScrollPos(i2 + 1, i3, i4, 1);
        int scrollPos4 = getScrollPos(i2 + 2, i3, i4, 2);
        if (scrollPos == -100) {
            scrollPos = scrollPos2 != -100 ? scrollPos2 : scrollPos3 != -100 ? scrollPos3 : scrollPos4 != -100 ? scrollPos4 : 0;
        }
        int superGetCurrentItem = this.indicatorBannerVP.superGetCurrentItem() - scrollPos;
        if (superGetCurrentItem >= 0) {
            this.indicatorBannerVP.superSetCurrentItem(superGetCurrentItem, true);
        } else {
            this.indicatorBannerVP.setCurrentItem(Math.abs(superGetCurrentItem));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getIndicateFixedIndex(int i2, int i3, int i4) {
        int i5;
        int i6 = 0;
        if (this.indicatorBannerVP == null || this.mIndicatorAdapter == null) {
            return 0;
        }
        if (i2 != i3) {
            i5 = 0;
            for (int i7 = 1; i7 <= 2 && (i5 = getScrollPos(i2 - i7, i3, i4, -i7)) == -100; i7++) {
            }
            if (i5 == -100) {
                for (int i8 = 1; i8 <= 2; i8++) {
                    i5 = getScrollPos(i2 + i8, i3, i4, i8);
                    if (i5 != -100) {
                        break;
                    }
                }
            }
            if (i5 == -100) {
                return i5;
            }
        } else {
            i5 = 0;
        }
        if (i5 < 0) {
            i6 = Math.abs(i5);
        } else if (i5 > 0) {
            i6 = 5 - i5;
        }
        return i6 + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmersionItemEntity getItemEntity(int i2) {
        List<ImmersionItemEntity> list = this.mTopBanners;
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (i2 > 0) {
            i2 %= size;
        } else if (i2 < 0) {
            i2 += size;
        }
        if (this.mTopBanners.size() > i2) {
            return this.mTopBanners.get(i2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<IPlayerVideoHolder> getPlayList() {
        ContentVideoHolderView contentVideoHolderView;
        ArrayList arrayList = new ArrayList();
        View selectedView = getSelectedView();
        if (selectedView == null || (contentVideoHolderView = (ContentVideoHolderView) selectedView.findViewById(R.id.content_holder_video)) == null) {
            return arrayList;
        }
        ViewParam viewParam = this.viewParam;
        contentVideoHolderView.setVideoVoiceOn(viewParam != null && viewParam.isVoiceOn);
        contentVideoHolderView.bindPlayBtn(!this.videoAutoPlay && contentVideoHolderView.isVideo());
        if (this.videoAutoPlay && contentVideoHolderView.isVideo()) {
            final MarqueeTextView marqueeTextView = (MarqueeTextView) selectedView.findViewById(R.id.immersion_banner_title_tv);
            if (marqueeTextView != null) {
                marqueeTextView.postDelayed(new Runnable() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.5
                    @Override // java.lang.Runnable
                    public void run() {
                        marqueeTextView.requestFocus();
                        marqueeTextView.startScroll();
                    }
                }, 200L);
            }
            if (this.immersionBannerVP == null) {
                return arrayList;
            }
            arrayList.add(contentVideoHolderView);
        }
        return arrayList;
    }

    private int getScrollPos(int i2, int i3, int i4, int i5) {
        if (i2 > i4 - 1) {
            i2 -= i4;
        } else if (i2 < 0) {
            i2 += i4;
        }
        if (i2 == i3) {
            return i5;
        }
        return -100;
    }

    private View getSelectedView() {
        ContentLoopViewPager contentLoopViewPager = this.immersionBannerVP;
        if (contentLoopViewPager == null) {
            return null;
        }
        int scrollX = contentLoopViewPager.getScrollX();
        int childCount = this.immersionBannerVP.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.immersionBannerVP.getChildAt(i2);
            if (!((ViewPager.LayoutParams) childAt.getLayoutParams()).isDecor && (childAt.getLeft() - scrollX) / this.immersionBannerVP.getMClientWidth() == 0.0f) {
                return childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void indicateStartScroll() {
        CustomViewPager customViewPager = this.indicatorBannerVP;
        if (customViewPager != null) {
            customViewPager.startLoop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void indicateStopScroll() {
        CustomViewPager customViewPager = this.indicatorBannerVP;
        if (customViewPager != null) {
            customViewPager.stopLoop();
        }
    }

    private void init() {
        PlayerListManager playerListManager = new PlayerListManager();
        this.playerListManager = playerListManager;
        playerListManager.setLoopPlay(true);
        this.playerListManager.setmVideoSharePlayer(this.mVideoSharePlayer);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initImmersionBannerView() {
        this.immersionBannerVP.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked != 3 && actionMasked != 4) {
                                return false;
                            }
                        }
                    }
                    FaXianImmersionBannerView.this.indicateStartScroll();
                    return false;
                }
                FaXianImmersionBannerView.this.indicateStopScroll();
                return false;
            }
        });
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
                if (i2 == 0) {
                    int currentItem = FaXianImmersionBannerView.this.immersionBannerVP.getCurrentItem();
                    int unused = FaXianImmersionBannerView.this.lastStateIdlePosition;
                    if (OKLog.D) {
                        OKLog.d(FaXianImmersionBannerView.TAG, "immersionPage onPageScrollStateChanged, lastStateIdlePosition :" + FaXianImmersionBannerView.this.lastStateIdlePosition + " currentPos : " + currentItem);
                    }
                    ImmersionItemEntity itemEntity = FaXianImmersionBannerView.this.getItemEntity(currentItem);
                    if (itemEntity == null) {
                        return;
                    }
                    Integer num = itemEntity.remainTime;
                    FaXianImmersionBannerView.this.indicatorBannerVP.setSlideInterval((num == null || num.intValue() <= 0) ? 4000 : itemEntity.remainTime.intValue() * 1000);
                    FaXianImmersionBannerView.this.indicatorBannerVP.startLoop();
                    FaXianImmersionBannerView.this.getPlayList();
                    FaXianImmersionBannerView.this.immersionBannerVP.post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x007a: INVOKE 
                          (wrap: com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager : 0x0071: IGET 
                          (wrap: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView : 0x006f: IGET (r3v0 'this' com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:10) com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.4.this$0 com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView)
                         A[MD:(com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView):com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager (m), WRAPPED] (LINE:1) com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.immersionBannerVP com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager)
                          (wrap: java.lang.Runnable : 0x0077: CONSTRUCTOR 
                          (r3v0 'this' com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4 A[IMMUTABLE_TYPE, THIS])
                          (r0 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4, java.util.List):void (m), WRAPPED] call: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.4.1.<init>(com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4, java.util.List):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.view.ViewGroup.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:1) in method: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.4.onPageScrollStateChanged(int):void, file: classes12.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 27 more
                        */
                    /*
                        this = this;
                        if (r4 != 0) goto L82
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r4 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager r4 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1400(r4)
                        int r4 = r4.getCurrentItem()
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1200(r0)
                        boolean r0 = com.jingdong.sdk.oklog.OKLog.D
                        if (r0 == 0) goto L39
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        r0.<init>()
                        java.lang.String r1 = "immersionPage onPageScrollStateChanged, lastStateIdlePosition :"
                        r0.append(r1)
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        int r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1200(r1)
                        r0.append(r1)
                        java.lang.String r1 = " currentPos : "
                        r0.append(r1)
                        r0.append(r4)
                        java.lang.String r0 = r0.toString()
                        java.lang.String r1 = "FXBannerView"
                        com.jingdong.sdk.oklog.OKLog.d(r1, r0)
                    L39:
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.entity.ImmersionItemEntity r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$700(r0, r4)
                        if (r0 != 0) goto L42
                        return
                    L42:
                        java.lang.Integer r1 = r0.remainTime
                        if (r1 == 0) goto L55
                        int r1 = r1.intValue()
                        if (r1 <= 0) goto L55
                        java.lang.Integer r0 = r0.remainTime
                        int r0 = r0.intValue()
                        int r0 = r0 * 1000
                        goto L57
                    L55:
                        r0 = 4000(0xfa0, float:5.605E-42)
                    L57:
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.CustomViewPager r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$000(r1)
                        r1.setSlideInterval(r0)
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.CustomViewPager r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$000(r0)
                        r0.startLoop()
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        java.util.List r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1500(r0)
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.ContentLoopViewPager r1 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1400(r1)
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4$1 r2 = new com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView$4$1
                        r2.<init>()
                        r1.post(r2)
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView r0 = com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.this
                        com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.access$1202(r0, r4)
                    L82:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.AnonymousClass4.onPageScrollStateChanged(int):void");
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    FaXianImmersionBannerView.this.pageTopSelected(i2);
                    FaXianImmersionBannerView.this.setIndicatorPageSelected(i2);
                    if (FaXianImmersionBannerView.this.lastStateIdlePosition == i2 || FaXianImmersionBannerView.this.playerListManager == null) {
                        return;
                    }
                    FaXianImmersionBannerView.this.playerListManager.pausePlayer();
                }
            };
            this.immersionPageChangeListener = onPageChangeListener;
            this.immersionBannerVP.addOnPageChangeListener(onPageChangeListener);
            this.immersionBannerVP.setOffscreenPageLimit(3);
        }

        private void initIndicatorBannerView() {
            findViewById(R.id.indicatorContainer).setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.1
                /* JADX WARN: Code restructure failed: missing block: B:8:0x000d, code lost:
                    if (r3 != 3) goto L12;
                 */
                @Override // android.view.View.OnTouchListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked != 0) {
                        if (actionMasked != 1) {
                            if (actionMasked != 2) {
                            }
                        }
                        FaXianImmersionBannerView.this.getParent().requestDisallowInterceptTouchEvent(false);
                        return FaXianImmersionBannerView.this.indicatorBannerVP.dispatchTouchEvent(motionEvent);
                    }
                    FaXianImmersionBannerView.this.getParent().requestDisallowInterceptTouchEvent(true);
                    return FaXianImmersionBannerView.this.indicatorBannerVP.dispatchTouchEvent(motionEvent);
                }
            });
            this.indicatorBannerVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.2
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    FaXianImmersionBannerView.this.pageIndicateSelected(i2);
                    FaXianImmersionBannerView.this.setImmersionPageSelected(i2);
                    if (FaXianImmersionBannerView.this.viewParam != null) {
                        if (!FaXianImmersionBannerView.this.viewParam.lastIsClick || FaXianImmersionBannerView.this.viewParam.lastPosition != i2) {
                            if (FaXianImmersionBannerView.this.viewParam.lastPosition > 0 && FaXianImmersionBannerView.this.mImmersionAdapter != null && FaXianImmersionBannerView.this.iBannerViewClick != null) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put(ClickEventConstants.CLICK_TYPE_KEY, "1");
                                StringBuilder sb = new StringBuilder();
                                FaXianImmersionBannerView faXianImmersionBannerView = FaXianImmersionBannerView.this;
                                sb.append(faXianImmersionBannerView.getIndicateFixedIndex(i2, faXianImmersionBannerView.viewParam.lastPosition, FaXianImmersionBannerView.this.mImmersionAdapter.getItemCount()));
                                sb.append("");
                                hashMap.put(ClickEventConstants.SHOW_INDEX_KEY, sb.toString());
                                FaXianImmersionBannerView.this.iBannerViewClick.onClick(i2, FaXianImmersionBannerView.this.getItemEntity(i2), hashMap, ClickEventConstants.ORGINAL_FROM.INDICAT_BANNER);
                            }
                            FaXianImmersionBannerView.this.viewParam.lastPosition = i2;
                            return;
                        }
                        FaXianImmersionBannerView.this.viewParam.lastIsClick = false;
                        FaXianImmersionBannerView.this.viewParam.lastPosition = i2;
                    }
                }
            });
        }

        private void initView(Context context) {
            this.mContext = context;
            setClipChildren(false);
            LayoutInflater.from(this.mContext.getApplicationContext()).inflate(R.layout.faxain_item_immersion_banner, this);
            this.immersionBannerVP = (ContentLoopViewPager) findViewById(R.id.conetnt_immersion_viewpager);
            this.indicatorBannerVP = (CustomViewPager) findViewById(R.id.conetnt_indicator_banner_view);
            this.indicatorContainerFl = (FrameLayout) findViewById(R.id.indicatorContainer);
            this.viewParam = new ViewParam();
            initIndicatorBannerView();
            initImmersionBannerView();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void pageIndicateSelected(int i2) {
            int size = this.mTopBanners.size();
            if (this.indicateExpoList == null) {
                this.indicateExpoList = new HashMap<>();
            }
            int i3 = size < 5 ? 1 : 2;
            addExpoEntity(i2, this.indicateExpoList);
            for (int i4 = 1; i4 <= i3; i4++) {
                addExpoEntity(i2 - i4, this.indicateExpoList);
            }
            for (int i5 = 1; i5 <= i3; i5++) {
                addExpoEntity(i2 + i5, this.indicateExpoList);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void pageTopSelected(int i2) {
            if (this.topExpoList == null) {
                this.topExpoList = new HashMap<>();
            }
            ImmersionItemEntity itemEntity = getItemEntity(i2);
            if (itemEntity == null || this.topExpoList.containsKey(itemEntity.id)) {
                return;
            }
            this.topExpoList.put(itemEntity.id, itemEntity);
        }

        private void registerVolumeListener() {
            if (this.volumeChangeHelper == null) {
                this.volumeChangeHelper = new VolumeChangeHelper(this.mContext);
            }
            this.volumeChangeHelper.e(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setImmersionPageSelected(int i2) {
            CustomViewPager customViewPager;
            int superGetCurrentItem;
            if (this.immersionBannerVP == null || (customViewPager = this.indicatorBannerVP) == null || this.immersionBannerVP.superGetCurrentItem() == (superGetCurrentItem = customViewPager.superGetCurrentItem())) {
                return;
            }
            this.immersionBannerVP.superSetCurrentItem(superGetCurrentItem, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIndicatorPageSelected(int i2) {
            int superGetCurrentItem;
            ContentLoopViewPager contentLoopViewPager = this.immersionBannerVP;
            if (contentLoopViewPager == null || this.indicatorBannerVP == null || this.indicatorBannerVP.superGetCurrentItem() == (superGetCurrentItem = contentLoopViewPager.superGetCurrentItem())) {
                return;
            }
            this.indicatorBannerVP.superSetCurrentItem(superGetCurrentItem, true);
        }

        private void setVoiceOn(boolean z) {
            if (this.viewParam == null) {
                this.viewParam = new ViewParam();
            }
            this.viewParam.isVoiceOn = z;
            FaXianImmersionBannerAdapter faXianImmersionBannerAdapter = this.mImmersionAdapter;
            if (faXianImmersionBannerAdapter != null) {
                faXianImmersionBannerAdapter.setVoiceOn(z);
                this.mImmersionAdapter.refreshViews();
            }
        }

        private void unregisterReceiver() {
            VolumeChangeHelper volumeChangeHelper = this.volumeChangeHelper;
            if (volumeChangeHelper != null) {
                volumeChangeHelper.f();
            }
        }

        public void expoBannerData() {
            IBannerViewExpo iBannerViewExpo = this.iBannerViewExpo;
            if (iBannerViewExpo != null) {
                iBannerViewExpo.onExpo(this.topExpoList, null, ClickEventConstants.ORGINAL_FROM.TOP_BANNER_EXPO);
                this.iBannerViewExpo.onExpo(this.indicateExpoList, null, ClickEventConstants.ORGINAL_FROM.INDICAT_BANNER_EXPO);
                clearExpoList();
            }
        }

        public JDVideoView getRealPlayerView() {
            PlayerListManager playerListManager = this.playerListManager;
            if (playerListManager == null || playerListManager.getRealVideoView() == null) {
                return null;
            }
            return this.playerListManager.getRealVideoView();
        }

        public boolean isBannerLoopling() {
            return this.isBannerLoopling;
        }

        public boolean isHalfVisiable(View view) {
            if (view == null) {
                return false;
            }
            Rect rect = new Rect();
            if (view.getLocalVisibleRect(rect)) {
                view.getLocationInWindow(new int[2]);
                return rect.bottom - rect.top > view.getHeight() / 2;
            }
            return false;
        }

        public boolean isVideoAutoPlay() {
            return this.videoAutoPlay;
        }

        public boolean isVoiceOn() {
            ViewParam viewParam = this.viewParam;
            return viewParam != null && viewParam.isVoiceOn;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            onResume();
        }

        public void onDestory() {
            unregisterReceiver();
            this.volumeChangeHelper = null;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            onStop();
            onDestory();
        }

        public void onFragmentResume() {
            this.fragmentIsResumed = true;
            onResume();
            List<ImmersionItemEntity> list = this.mTopBanners;
            if (list != null && list.size() > 0 && getParent() != null) {
                registerVolumeListener();
            }
            if (OKLog.D) {
                OKLog.d(TAG, "onFragmentResume");
                StringBuilder sb = new StringBuilder();
                sb.append("mTopBanners.isNotEmpty \uff1a");
                List<ImmersionItemEntity> list2 = this.mTopBanners;
                sb.append(list2 != null && list2.size() > 0);
                OKLog.d(TAG, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("haveParent \uff1a");
                sb2.append(getParent() != null);
                OKLog.d(TAG, sb2.toString());
            }
        }

        public void onFragmentStop() {
            this.fragmentIsResumed = false;
            onStop();
            unregisterReceiver();
            this.volumeChangeHelper = null;
            if (OKLog.D) {
                OKLog.d(TAG, "onFragmentStop");
            }
        }

        public void onFragmentStopWithoutPlayer() {
            this.fragmentIsResumed = false;
            this.isBannerLoopling = false;
            CustomViewPager customViewPager = this.indicatorBannerVP;
            if (customViewPager != null) {
                customViewPager.stopLoop();
            }
            unregisterReceiver();
            this.volumeChangeHelper = null;
            if (OKLog.D) {
                OKLog.d(TAG, "onFragmentStopWithoutPlayer");
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
        }

        public void onResume() {
            if (!this.isBannerLoopling && this.fragmentIsResumed && ViewCompat.isAttachedToWindow(this) && this.indicatorBannerVP != null && isHalfVisiable(this)) {
                this.isBannerLoopling = true;
                startScroll();
                this.indicatorBannerVP.requestLayout();
                this.immersionPageChangeListener.onPageScrollStateChanged(0);
            }
        }

        public void onStop() {
            reset();
            stopScroll();
            this.isBannerLoopling = false;
        }

        @Override // com.jingdong.content.component.util.VolumeChangeHelper.a
        public void onVolumeDownToMin() {
            setVoiceOn(true);
        }

        @Override // com.jingdong.content.component.util.VolumeChangeHelper.a
        public void onVolumeUp() {
            setVoiceOn(true);
        }

        public void reloadUrl(List<ImmersionItemEntity> list, boolean z) {
            if (z) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (list.get(i2) != null && !TextUtils.isEmpty(list.get(i2).playUrl)) {
                        arrayList.add(list.get(i2).playUrl);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                PreloadManager.getInstance().doPreload(arrayList);
            }
        }

        public void reset() {
            this.lastStateIdlePosition = -1;
        }

        public void setVideoAutoPlay(boolean z) {
            if (this.videoAutoPlay != z) {
                this.videoAutoPlay = z;
                List<IPlayerVideoHolder> playList = getPlayList();
                PlayerListManager playerListManager = this.playerListManager;
                if (playerListManager != null) {
                    playerListManager.playVideoList(playList);
                }
            }
        }

        public void setViewClickEvent(IBannerViewClick iBannerViewClick) {
            this.iBannerViewClick = iBannerViewClick;
        }

        public void setViewExpoEvent(IBannerViewExpo iBannerViewExpo) {
            this.iBannerViewExpo = iBannerViewExpo;
        }

        public void setmVideoSharePlayer(boolean z) {
            this.mVideoSharePlayer = z;
            PlayerListManager playerListManager = this.playerListManager;
            if (playerListManager != null) {
                playerListManager.setmVideoSharePlayer(z);
            }
        }

        public void startScroll() {
            CustomViewPager customViewPager = this.indicatorBannerVP;
            if (customViewPager != null) {
                customViewPager.startLoop();
            }
        }

        public void stopScroll() {
            CustomViewPager customViewPager = this.indicatorBannerVP;
            if (customViewPager != null) {
                customViewPager.stopLoop();
            }
            PlayerListManager playerListManager = this.playerListManager;
            if (playerListManager != null) {
                playerListManager.detachPlayer();
            }
        }

        public void updateData(List<ImmersionItemEntity> list, boolean z) {
            updateData(list, z, false);
        }

        public FaXianImmersionBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public void updateData(List<ImmersionItemEntity> list, boolean z, boolean z2) {
            if (list != null && list.size() > 0) {
                int size = list.size();
                this.videoAutoPlay = z;
                if (z2) {
                    reloadUrl(list, z);
                }
                reset();
                this.mTopBanners = list;
                Integer num = list.get(0).remainTime;
                if (num == null || num.intValue() <= 0) {
                    num = 4;
                }
                this.indicatorBannerVP.setPageTransformer(false, new GalleryTransformer(0.2f, size < 5 ? 3 : 5));
                this.indicatorBannerVP.setOffscreenPageLimit(10);
                this.indicatorBannerVP.setCustomOffset(BaseInfo.getScreenWidth() * 0.4f);
                FaXianIndicatorBannerAdapter faXianIndicatorBannerAdapter = new FaXianIndicatorBannerAdapter(list, this.mContext);
                this.mIndicatorAdapter = faXianIndicatorBannerAdapter;
                this.indicatorBannerVP.setAdapter((FaxianTJBannerViewAdapter) faXianIndicatorBannerAdapter);
                this.indicatorBannerVP.isSupportLoop(true);
                this.indicatorBannerVP.setSlideInterval(num.intValue() * 1000);
                this.indicatorBannerVP.isSupportAutoScroll(true);
                FaXianImmersionBannerAdapter faXianImmersionBannerAdapter = new FaXianImmersionBannerAdapter(list, isVideoAutoPlay(), this.mContext);
                this.mImmersionAdapter = faXianImmersionBannerAdapter;
                this.immersionBannerVP.setAdapter((FaxianTJBannerViewAdapter) faXianImmersionBannerAdapter);
                this.immersionBannerVP.isSupportLoop(true);
                this.immersionBannerVP.isSupportAutoScroll(false);
                this.immersionBannerVP.postDelayed(new Runnable() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (FaXianImmersionBannerView.this.immersionPageChangeListener != null) {
                            FaXianImmersionBannerView.this.immersionPageChangeListener.onPageScrollStateChanged(0);
                        }
                    }
                }, 200L);
                this.mIndicatorAdapter.setIViewClickListener(new IBannerViewClick() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.7
                    @Override // com.jingdong.content.component.widget.immersionbanner.IBannerViewClick
                    public void onClick(int i2, ImmersionItemEntity immersionItemEntity, HashMap<String, Object> hashMap, String str) {
                        if (FaXianImmersionBannerView.this.viewParam != null) {
                            FaXianImmersionBannerView.this.viewParam.lastPosition = i2;
                            FaXianImmersionBannerView.this.viewParam.lastIsClick = true;
                        }
                        int itemCount = FaXianImmersionBannerView.this.mIndicatorAdapter.getItemCount();
                        int currentItem = FaXianImmersionBannerView.this.indicatorBannerVP.getCurrentItem();
                        FaXianImmersionBannerView.this.clickSelect(i2, currentItem, itemCount);
                        if (FaXianImmersionBannerView.this.iBannerViewClick != null) {
                            if (hashMap == null) {
                                hashMap = new HashMap<>();
                            }
                            hashMap.put(ClickEventConstants.SHOW_INDEX_KEY, FaXianImmersionBannerView.this.getIndicateFixedIndex(i2, currentItem, itemCount) + "");
                            FaXianImmersionBannerView.this.iBannerViewClick.onClick(i2, immersionItemEntity, hashMap, str);
                        }
                    }
                });
                this.mImmersionAdapter.setIViewClickListener(new IBannerViewClick() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.8
                    @Override // com.jingdong.content.component.widget.immersionbanner.IBannerViewClick
                    public void onClick(int i2, ImmersionItemEntity immersionItemEntity, HashMap<String, Object> hashMap, String str) {
                        if (FaXianImmersionBannerView.this.iBannerViewClick != null) {
                            FaXianImmersionBannerView.this.iBannerViewClick.onClick(i2, immersionItemEntity, hashMap, str);
                        }
                    }
                });
                this.mImmersionAdapter.setVoiceClickListener(new IVoiceClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.9
                    @Override // com.jingdong.content.component.widget.videocontrol.IVoiceClickListener
                    public void targetVoice(boolean z3) {
                        if (FaXianImmersionBannerView.this.viewParam != null) {
                            FaXianImmersionBannerView.this.viewParam.isVoiceOn = z3;
                            FaXianImmersionBannerView.this.mImmersionAdapter.refreshViews();
                        }
                    }
                });
                this.mImmersionAdapter.setPlayClickListener(new IPlayClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerView.10
                    @Override // com.jingdong.content.component.widget.videocontrol.IPlayClickListener
                    public void toPlay(ContentVideoHolderView contentVideoHolderView) {
                        if (FaXianImmersionBannerView.this.playerListManager != null) {
                            ArrayList arrayList = new ArrayList();
                            if (contentVideoHolderView != null) {
                                arrayList.add(contentVideoHolderView);
                            }
                            FaXianImmersionBannerView.this.playerListManager.playVideoList(arrayList);
                        }
                    }
                });
                registerVolumeListener();
                return;
            }
            stopScroll();
        }

        public FaXianImmersionBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.lastStateIdlePosition = -1;
            this.topExpoList = new HashMap<>();
            this.indicateExpoList = new HashMap<>();
            initView(context);
            init();
        }

        public void init(Context context) {
            this.mContext = context;
        }

        public void stopScroll(boolean z) {
            PlayerListManager playerListManager;
            CustomViewPager customViewPager = this.indicatorBannerVP;
            if (customViewPager != null) {
                customViewPager.stopLoop();
            }
            if (z && (playerListManager = this.playerListManager) != null) {
                playerListManager.detachPlayer();
            }
        }
    }
