package com.jingdong.common.widget.custom.livewidget.loopgallery;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.reflect.Field;

/* loaded from: classes12.dex */
public class LoopGalleryCarouselFigureView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "LoopGalleryCarouselFigureView";
    private static final int VIEW_CHANGE_INTERVAL = 3000;
    private static final int VIEW_CHANGE_INTERVAL_RESUME = 2000;
    private long actionDownTime;
    private int bgResource;
    private LinearLayout cursorContent;
    private int cursorHeight;
    private int cursorMarginRight;
    private int cursorSpace;
    private int cursorWidth;
    private Handler handler;
    private boolean isAutoPlay;
    private boolean isCarousel;
    private boolean isDispatchTouch;
    private boolean isFromUserCursor;
    private boolean isInVisibilityPause;
    private boolean isPause;
    private boolean isUserTouched;
    private int lastPosition;
    private int lightResource;
    private Context mContext;
    private int normalResource;
    private int oldCursorPosition;
    private OnPageSelectedListener onPageSelectedListener;
    protected CustomizedCarouselFigureViewPager pager;
    private int playtime;
    private int selectCursorWidth;
    private long token;
    private float x;
    private float y;

    /* loaded from: classes12.dex */
    public interface OnPageSelectedListener {
        void onPageMove(int i2, int i3);

        void onPageSelected(int i2);

        void onUserSelectedFrom(int i2);
    }

    /* loaded from: classes12.dex */
    private static class ViewPagerScroller extends Scroller {
        private int mScrollDuration;

        public ViewPagerScroller(Context context) {
            super(context);
            this.mScrollDuration = 2000;
        }

        public void initViewPagerScroll(ViewPager viewPager) {
            try {
                Field declaredField = ViewPager.class.getDeclaredField("mScroller");
                declaredField.setAccessible(true);
                declaredField.set(viewPager, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void setScrollDuration(int i2) {
            this.mScrollDuration = i2;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5, int i6) {
            super.startScroll(i2, i3, i4, i5, this.mScrollDuration);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5) {
            super.startScroll(i2, i3, i4, i5, this.mScrollDuration);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
            this.mScrollDuration = 2000;
        }
    }

    public LoopGalleryCarouselFigureView(Context context) {
        super(context);
        this.cursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorHeight = DPIUtil.dip2px(13.0f);
        this.selectCursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorSpace = DPIUtil.dip2px(7.0f);
        this.lightResource = R.drawable.icon_gallery_point_white;
        this.normalResource = R.drawable.icon_gallery_point_grey;
        this.isFromUserCursor = false;
        this.bgResource = 0;
        this.token = -1L;
        this.playtime = 0;
        this.isInVisibilityPause = false;
        this.handler = new Handler() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.LoopGalleryCarouselFigureView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager;
                if (!LoopGalleryCarouselFigureView.this.isPause && (customizedCarouselFigureViewPager = LoopGalleryCarouselFigureView.this.pager) != null && customizedCarouselFigureViewPager.getChildCount() > 1 && LoopGalleryCarouselFigureView.this.pager.getAdapter() != null && LoopGalleryCarouselFigureView.this.pager.getAdapter().getCount() >= 2) {
                    try {
                        if (LoopGalleryCarouselFigureView.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        int i2 = message.what;
                        if (LoopGalleryCarouselFigureView.this.isCarousel) {
                            if (i2 == 0 && LoopGalleryCarouselFigureView.this.pager.getCurrentItem() != 0) {
                                CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager2 = LoopGalleryCarouselFigureView.this.pager;
                                customizedCarouselFigureViewPager2.setCurrentItem(customizedCarouselFigureViewPager2.getRealCount() + 2);
                            } else if (i2 == LoopGalleryCarouselFigureView.this.pager.getRealCount() + 2) {
                                LoopGalleryCarouselFigureView.this.pager.setCurrentItem(3);
                            } else {
                                LoopGalleryCarouselFigureView.this.pager.setCurrentItem(i2 + 1);
                            }
                        } else {
                            CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager3 = LoopGalleryCarouselFigureView.this.pager;
                            customizedCarouselFigureViewPager3.setCurrentItem((i2 + 1) % customizedCarouselFigureViewPager3.getAdapter().getCount());
                        }
                        if (Log.D) {
                            Log.d(LoopGalleryCarouselFigureView.TAG, "handleMessage: position = " + i2);
                        }
                        LoopGalleryCarouselFigureView.this.isUserTouched = false;
                    } catch (Exception unused) {
                    }
                }
            }
        };
        this.actionDownTime = -1L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void autoChangeViewPagerPosition(int i2) {
        if (this.pager != null && this.isAutoPlay) {
            this.token = System.currentTimeMillis();
            Message obtain = Message.obtain();
            obtain.what = this.pager.getCurrentItem();
            obtain.obj = Long.valueOf(this.token);
            this.handler.sendMessageDelayed(obtain, i2);
        }
    }

    private void closeLight(int i2) {
        LinearLayout linearLayout = this.cursorContent;
        if (linearLayout != null) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i2);
            if (imageView != null) {
                this.cursorContent.removeView(imageView);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorWidth, this.cursorHeight);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, this.cursorSpace, 0);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
            simpleDraweeView.setLayoutParams(layoutParams);
            simpleDraweeView.setImageResource(this.normalResource);
            this.cursorContent.addView(simpleDraweeView, i2);
        }
    }

    private void openLight(int i2) {
        LinearLayout linearLayout = this.cursorContent;
        if (linearLayout != null) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i2);
            if (imageView != null) {
                this.cursorContent.removeView(imageView);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.selectCursorWidth, this.cursorHeight);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, this.cursorSpace, 0);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
            simpleDraweeView.setLayoutParams(layoutParams);
            simpleDraweeView.setImageResource(this.lightResource);
            this.cursorContent.addView(simpleDraweeView, i2);
        }
        this.oldCursorPosition = i2;
    }

    public void createCursor(int i2) {
        if (Log.D) {
            Log.d(TAG, " -->> createCursor size = " + i2);
        }
        if (i2 < 1) {
            setVisibility(8);
            return;
        }
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        if (i2 < 2) {
            this.cursorContent.setVisibility(8);
            return;
        }
        if (this.cursorContent.getVisibility() == 8) {
            this.cursorContent.setVisibility(0);
        }
        this.cursorContent.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cursorWidth, this.cursorHeight);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, 0, this.cursorSpace, 0);
        for (int i3 = 0; i3 < i2 && i2 > 1; i3++) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
            simpleDraweeView.setLayoutParams(layoutParams);
            if (!this.isFromUserCursor) {
                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER);
            }
            simpleDraweeView.setImageResource(this.normalResource);
            this.cursorContent.addView(simpleDraweeView);
        }
        CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager = this.pager;
        openLight(customizedCarouselFigureViewPager.toRealPosition(customizedCarouselFigureViewPager.getCurrentItem()));
        if (this.cursorContent.getParent() == null) {
            addView(this.cursorContent);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.isDispatchTouch && getPagerView() != null) {
            boolean dispatchTouchEvent = getPagerView().dispatchTouchEvent(motionEvent);
            OKLog.d(TAG, "getPagerView dispatchTouchEvent: result = " + dispatchTouchEvent);
            return dispatchTouchEvent;
        }
        if (this.isAutoPlay) {
            if (Log.D && Build.VERSION.SDK_INT >= 19) {
                Log.d(TAG, "dispatchTouchEvent: action = " + MotionEvent.actionToString(action));
            }
            if (action == 1 || action == 3 || action == 4) {
                autoChangeViewPagerPosition(3000);
                long currentTimeMillis = System.currentTimeMillis();
                if (Log.D) {
                    Log.d(TAG, "dispatchTouchEvent: actionDownTime = " + this.actionDownTime + ", currTime = " + currentTimeMillis + ", ViewConfiguration.getLongPressTimeout() = " + ViewConfiguration.getLongPressTimeout());
                }
                OnPageSelectedListener onPageSelectedListener = this.onPageSelectedListener;
                if (onPageSelectedListener != null) {
                    onPageSelectedListener.onPageMove(1, motionEvent.getX() - this.x > 0.0f ? 0 : 1);
                }
                if (action == 1) {
                    long j2 = this.actionDownTime;
                    if (j2 != -1 && currentTimeMillis - j2 >= ViewConfiguration.getLongPressTimeout() && Math.abs(this.x - motionEvent.getX()) < 10.0f && Math.abs(this.y - motionEvent.getY()) < 10.0f) {
                        this.actionDownTime = -1L;
                        return false;
                    }
                }
            } else if (action == 0) {
                this.actionDownTime = System.currentTimeMillis();
                this.handler.removeCallbacksAndMessages(null);
                this.isUserTouched = true;
                this.x = motionEvent.getX();
                this.y = motionEvent.getY();
                OnPageSelectedListener onPageSelectedListener2 = this.onPageSelectedListener;
                if (onPageSelectedListener2 != null) {
                    onPageSelectedListener2.onPageMove(0, -1);
                }
            }
        }
        boolean dispatchTouchEvent2 = super.dispatchTouchEvent(motionEvent);
        if (Log.D) {
            Log.d(TAG, "dispatchTouchEvent: result = " + dispatchTouchEvent2);
        }
        if (action == 0 && !dispatchTouchEvent2) {
            autoChangeViewPagerPosition(3000);
        }
        return dispatchTouchEvent2;
    }

    public CustomizedCarouselFigureViewPager getPagerView() {
        return this.pager;
    }

    public void init(Context context, ViewGroup viewGroup, int i2, int i3, boolean z, boolean z2, int i4, int i5, int i6) {
        init(context, viewGroup, i2, i3, 0, z, z2, i4, i5, i6);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Log.D) {
            Log.d(TAG, "onAttachedToWindow:");
        }
        if (this.pager != null) {
            autoChangeViewPagerPosition(3000);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Log.D) {
            Log.d(TAG, "onDetachedFromWindow:");
        }
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (Log.D) {
            Log.d(TAG, "onPageSelected: position = " + i2);
        }
        OnPageSelectedListener onPageSelectedListener = this.onPageSelectedListener;
        if (onPageSelectedListener != null) {
            onPageSelectedListener.onPageSelected(i2);
            if (this.isUserTouched) {
                this.onPageSelectedListener.onUserSelectedFrom(this.lastPosition);
            }
        }
        this.isUserTouched = false;
        this.lastPosition = i2;
        closeLight(this.oldCursorPosition);
        openLight(i2);
        int i3 = this.playtime;
        if (i3 == 0) {
            i3 = 3000;
        }
        autoChangeViewPagerPosition(i3);
    }

    public void onPause() {
        this.token = System.currentTimeMillis();
        this.isPause = true;
    }

    public void onResume() {
        this.isPause = false;
        autoChangeViewPagerPosition(2000);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager = this.pager;
        if (customizedCarouselFigureViewPager == null) {
            return super.onTouchEvent(motionEvent);
        }
        boolean dispatchTouchEvent = customizedCarouselFigureViewPager.dispatchTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (Log.D && Build.VERSION.SDK_INT >= 19) {
            Log.e(TAG, "onTouchEvent->pager.dispatchTouchEvent: action = " + MotionEvent.actionToString(action) + " result = " + dispatchTouchEvent);
        }
        return dispatchTouchEvent;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (this.isInVisibilityPause) {
            if (i2 == 0) {
                onResume();
            } else if (i2 == 4 || i2 == 8) {
                onPause();
            }
        }
    }

    public void setCursor(int i2, int i3, int i4, int i5, int i6) {
        if (i2 > 0) {
            this.cursorWidth = i2;
        }
        if (i3 > 0) {
            this.cursorHeight = i3;
        }
        if (i4 > 0) {
            this.cursorSpace = i4;
        }
        if (i5 > 0) {
            this.isFromUserCursor = true;
            this.lightResource = i5;
        }
        if (i6 > 0) {
            this.isFromUserCursor = true;
            this.normalResource = i6;
        }
    }

    public void setDispatchTouch(boolean z) {
        if (getPagerView() != null) {
            getPagerView().setDispatchTouch(z);
        }
        this.isDispatchTouch = z;
    }

    public void setInVisibilityPause() {
        this.isInVisibilityPause = true;
    }

    public void setOnPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
        this.onPageSelectedListener = onPageSelectedListener;
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        if (pageTransformer != null) {
            this.pager.setPageTransformer(true, pageTransformer);
        }
    }

    public void setPagerAdapter(final LoopGalleryPagerAdapter loopGalleryPagerAdapter) {
        if (this.mContext == null || this.pager == null || loopGalleryPagerAdapter == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.LoopGalleryCarouselFigureView.2
            @Override // java.lang.Runnable
            public void run() {
                loopGalleryPagerAdapter.setCarousel(LoopGalleryCarouselFigureView.this.isCarousel);
                LoopGalleryCarouselFigureView.this.pager.setAdapter(loopGalleryPagerAdapter);
                LoopGalleryCarouselFigureView loopGalleryCarouselFigureView = LoopGalleryCarouselFigureView.this;
                loopGalleryCarouselFigureView.createCursor(loopGalleryCarouselFigureView.pager.getRealCount());
                LoopGalleryCarouselFigureView loopGalleryCarouselFigureView2 = LoopGalleryCarouselFigureView.this;
                loopGalleryCarouselFigureView2.autoChangeViewPagerPosition(loopGalleryCarouselFigureView2.playtime == 0 ? 3000 : LoopGalleryCarouselFigureView.this.playtime);
            }
        });
    }

    public void init(Context context, ViewGroup viewGroup, int i2, int i3, int i4, boolean z, boolean z2, int i5, int i6, int i7) {
        init(context, viewGroup, i2, i3, i4, z, z2, i5, i6, i7, false);
    }

    public void init(Context context, ViewGroup viewGroup, int i2, int i3, int i4, boolean z, boolean z2, int i5, int i6, int i7, boolean z3) {
        CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager;
        this.mContext = context;
        this.isCarousel = z;
        this.isAutoPlay = z2;
        if (z3 && (customizedCarouselFigureViewPager = this.pager) != null) {
            removeView(customizedCarouselFigureViewPager);
            this.pager = null;
        }
        if (this.pager == null) {
            CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager2 = new CustomizedCarouselFigureViewPager(this.mContext);
            this.pager = customizedCarouselFigureViewPager2;
            customizedCarouselFigureViewPager2.setOffscreenPageLimit(i7);
            this.pager.setOnTouchFlag(true);
            this.pager.setClipChildren(true);
            this.pager.setPageMargin(i4);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i3);
            if (i5 <= 0) {
                i5 = 0;
            }
            layoutParams.setMargins(i4, 0, 0, i5);
            layoutParams.gravity = 83;
            this.pager.setLayoutParams(layoutParams);
            this.pager.setOnPageChangeListener(this);
            addView(this.pager);
        }
        if (this.cursorContent == null) {
            this.cursorContent = new LinearLayout(getContext());
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.setMargins(0, 0, this.cursorMarginRight, i6);
            layoutParams2.gravity = 81;
            this.cursorContent.setPadding(0, 0, 0, 0);
            this.cursorContent.setOrientation(0);
            this.cursorContent.setLayoutParams(layoutParams2);
            int i8 = this.bgResource;
            if (i8 > 0) {
                this.cursorContent.setBackgroundResource(i8);
            }
        }
        this.pager.init(viewGroup, z);
    }

    public void setCursor(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i2 > 0) {
            this.selectCursorWidth = i2;
        }
        if (i3 > 0) {
            this.cursorWidth = i3;
        }
        if (i4 > 0) {
            this.cursorHeight = i4;
        }
        if (i5 >= 0) {
            this.cursorSpace = i5;
        }
        if (i6 > 0) {
            this.isFromUserCursor = true;
            this.lightResource = i6;
        }
        if (i7 > 0) {
            this.isFromUserCursor = true;
            this.normalResource = i7;
        }
        if (i8 > 0) {
            this.bgResource = i8;
        }
    }

    public LoopGalleryCarouselFigureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.cursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorHeight = DPIUtil.dip2px(13.0f);
        this.selectCursorWidth = DPIUtil.dip2px(13.0f);
        this.cursorSpace = DPIUtil.dip2px(7.0f);
        this.lightResource = R.drawable.icon_gallery_point_white;
        this.normalResource = R.drawable.icon_gallery_point_grey;
        this.isFromUserCursor = false;
        this.bgResource = 0;
        this.token = -1L;
        this.playtime = 0;
        this.isInVisibilityPause = false;
        this.handler = new Handler() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.LoopGalleryCarouselFigureView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager;
                if (!LoopGalleryCarouselFigureView.this.isPause && (customizedCarouselFigureViewPager = LoopGalleryCarouselFigureView.this.pager) != null && customizedCarouselFigureViewPager.getChildCount() > 1 && LoopGalleryCarouselFigureView.this.pager.getAdapter() != null && LoopGalleryCarouselFigureView.this.pager.getAdapter().getCount() >= 2) {
                    try {
                        if (LoopGalleryCarouselFigureView.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        int i2 = message.what;
                        if (LoopGalleryCarouselFigureView.this.isCarousel) {
                            if (i2 == 0 && LoopGalleryCarouselFigureView.this.pager.getCurrentItem() != 0) {
                                CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager2 = LoopGalleryCarouselFigureView.this.pager;
                                customizedCarouselFigureViewPager2.setCurrentItem(customizedCarouselFigureViewPager2.getRealCount() + 2);
                            } else if (i2 == LoopGalleryCarouselFigureView.this.pager.getRealCount() + 2) {
                                LoopGalleryCarouselFigureView.this.pager.setCurrentItem(3);
                            } else {
                                LoopGalleryCarouselFigureView.this.pager.setCurrentItem(i2 + 1);
                            }
                        } else {
                            CustomizedCarouselFigureViewPager customizedCarouselFigureViewPager3 = LoopGalleryCarouselFigureView.this.pager;
                            customizedCarouselFigureViewPager3.setCurrentItem((i2 + 1) % customizedCarouselFigureViewPager3.getAdapter().getCount());
                        }
                        if (Log.D) {
                            Log.d(LoopGalleryCarouselFigureView.TAG, "handleMessage: position = " + i2);
                        }
                        LoopGalleryCarouselFigureView.this.isUserTouched = false;
                    } catch (Exception unused) {
                    }
                }
            }
        };
        this.actionDownTime = -1L;
    }

    public void init(Context context, ViewGroup viewGroup, int i2, int i3, int i4, boolean z, boolean z2, int i5, int i6, int i7, int i8, int i9, boolean z3) {
        this.playtime = i8;
        this.cursorMarginRight = i7;
        init(context, viewGroup, i2, i3, i4, z, z2, i5, i6, i9, z3);
    }

    public void init(Context context, ViewGroup viewGroup, int i2, int i3, boolean z, boolean z2, int i4, int i5, int i6, int i7) {
        init(context, viewGroup, i2, i3, z, z2, i4, i5, i7);
        if (i6 > 0) {
            ViewPagerScroller viewPagerScroller = new ViewPagerScroller(context);
            viewPagerScroller.setScrollDuration(i6);
            viewPagerScroller.initViewPagerScroll(this.pager);
        }
    }
}
