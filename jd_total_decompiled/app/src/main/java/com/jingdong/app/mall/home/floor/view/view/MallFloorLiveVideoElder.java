package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.ElderFloorTitleLayout;
import com.jingdong.app.mall.home.floor.view.widget.ElderLiveLottieView;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.r.f.a.s;

/* loaded from: classes4.dex */
public class MallFloorLiveVideoElder extends BaseMallFloor<s> {
    private f mBgSize;
    private com.jingdong.app.mall.home.r.e.f mBindData;
    private SimpleDraweeView mFloorBg;
    private VideoElderItem mLeftItem;
    private f mLeftSize;
    private VideoElderItem mRightItem;
    private f mRightSize;
    private ElderFloorTitleLayout mTitleLayout;
    private f mTitleSize;

    /* loaded from: classes4.dex */
    public class VideoElderItem extends RelativeLayout {
        private static final String ELDER_BUBBLE_ALL_KEY = "elderAllBubble_";
        private int mBubbleTimes;
        private com.jingdong.app.mall.home.r.e.f mElement;
        private f mLottieSize;
        private ElderLiveLottieView mLottieView;
        private SimpleDraweeView mSkuView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoElderItem(Context context) {
            super(context);
            MallFloorLiveVideoElder.this = r2;
            this.mLottieSize = new f(-1, -1);
            HomeDraweeView homeDraweeView = new HomeDraweeView(context);
            this.mSkuView = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.mSkuView, new RelativeLayout.LayoutParams(-1, -1));
            e.d(this.mSkuView, d.d(8));
        }

        private void bindLottie(int i2) {
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            this.mBubbleTimes = 0;
            if (((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).T(i2) && ((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).U() && hasBubbleTimes(i2)) {
                ElderLiveLottieView elderLiveLottieView = this.mLottieView;
                if (elderLiveLottieView == null) {
                    ElderLiveLottieView elderLiveLottieView2 = new ElderLiveLottieView(getContext());
                    this.mLottieView = elderLiveLottieView2;
                    RelativeLayout.LayoutParams u = this.mLottieSize.u(elderLiveLottieView2);
                    u.addRule(9);
                    addView(this.mLottieView, u);
                } else {
                    f.d(elderLiveLottieView, this.mLottieSize, true);
                }
                this.mLottieView.f(this, i2);
                return;
            }
            ElderLiveLottieView elderLiveLottieView3 = this.mLottieView;
            if (elderLiveLottieView3 != null) {
                elderLiveLottieView3.i();
                this.mLottieView.setVisibility(8);
            }
        }

        public void addBubbleTimes(int i2) {
            if (this.mElement == null) {
                return;
            }
            int Q = ((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).Q();
            int i3 = this.mBubbleTimes;
            if (i3 >= Q) {
                return;
            }
            this.mBubbleTimes = i3 + 1;
            com.jingdong.app.mall.home.floor.view.b.f.d.c(ELDER_BUBBLE_ALL_KEY.concat(this.mElement.s()).concat(String.valueOf(i2)), ((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).P());
        }

        public void bindItem(com.jingdong.app.mall.home.r.e.f fVar, int i2) {
            if (fVar == null) {
                return;
            }
            this.mElement = fVar;
            MallFloorLiveVideoElder.this.setClickListener(this, i2);
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.mSkuView, i2 == 0 ? fVar.u() : fVar.v());
            bindLottie(i2);
        }

        public boolean hasBubbleTimes(int i2) {
            if (this.mElement == null) {
                return false;
            }
            if (this.mBubbleTimes >= ((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).Q()) {
                return false;
            }
            return com.jingdong.app.mall.home.floor.view.b.f.d.g(ELDER_BUBBLE_ALL_KEY.concat(this.mElement.s()).concat(String.valueOf(i2)), ((s) ((BaseMallColorFloor) MallFloorLiveVideoElder.this).mPresenter).P());
        }
    }

    public MallFloorLiveVideoElder(Context context) {
        super(context);
        this.mLeftSize = new f(319, 319);
        this.mRightSize = new f(319, 319);
        this.mTitleSize = new f(-1, 100);
        this.mBgSize = new f(-1, -1);
        setContentDescription("\u4eac\u4e1c\u76f4\u64ad");
    }

    private void bindFloorBg() {
        String jsonString = this.mFloorBindElement.mParentModel.getJsonString("floorBgImg", "");
        SimpleDraweeView simpleDraweeView = this.mFloorBg;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mFloorBg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView2 = this.mFloorBg;
            addView(simpleDraweeView2, 0, this.mBgSize.u(simpleDraweeView2));
        } else {
            f.d(simpleDraweeView, this.mBgSize, true);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.mFloorBg, jsonString);
    }

    private void bindItemView() {
        if (this.mBindData == null) {
            return;
        }
        VideoElderItem videoElderItem = this.mLeftItem;
        if (videoElderItem == null) {
            this.mLeftItem = new VideoElderItem(getContext());
            this.mLeftSize.F(new Rect(24, 100, 0, 0));
            RelativeLayout.LayoutParams u = this.mLeftSize.u(this.mLeftItem);
            u.addRule(9);
            addView(this.mLeftItem, u);
        } else {
            f.c(videoElderItem, this.mLeftSize);
        }
        this.mLeftItem.bindItem(this.mBindData, 0);
        VideoElderItem videoElderItem2 = this.mRightItem;
        if (videoElderItem2 == null) {
            this.mRightItem = new VideoElderItem(getContext());
            this.mRightSize.F(new Rect(0, 100, 24, 0));
            RelativeLayout.LayoutParams u2 = this.mRightSize.u(this.mRightItem);
            u2.addRule(11);
            addView(this.mRightItem, u2);
        } else {
            f.c(videoElderItem2, this.mRightSize);
        }
        this.mRightItem.bindItem(this.mBindData, 1);
    }

    private void bindTitleLayout() {
        if (this.mBindData == null) {
            return;
        }
        ElderFloorTitleLayout elderFloorTitleLayout = this.mTitleLayout;
        if (elderFloorTitleLayout == null) {
            ElderFloorTitleLayout elderFloorTitleLayout2 = new ElderFloorTitleLayout(getContext(), -588469);
            this.mTitleLayout = elderFloorTitleLayout2;
            addView(elderFloorTitleLayout2, this.mTitleSize.u(elderFloorTitleLayout2));
        } else {
            f.c(elderFloorTitleLayout, this.mTitleSize);
        }
        this.mTitleLayout.d(this.mBindData);
        setClickListener(this.mTitleLayout, 0);
    }

    public void setClickListener(View view, final int i2) {
        com.jingdong.app.mall.home.r.e.f fVar = this.mBindData;
        if (fVar == null) {
            return;
        }
        fVar.getJump();
        view.setOnClickListener(new View.OnClickListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: INVOKE 
              (r3v0 'view' android.view.View)
              (wrap: android.view.View$OnClickListener : 0x000b: CONSTRUCTOR 
              (r2v0 'this' com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.jingdong.common.entity.JumpEntity A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'i2' int A[DONT_INLINE])
             A[MD:(com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder, com.jingdong.common.entity.JumpEntity, int):void (m), WRAPPED] (LINE:3) call: com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder.1.<init>(com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder, com.jingdong.common.entity.JumpEntity, int):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.view.View.setOnClickListener(android.view.View$OnClickListener):void A[MD:(android.view.View$OnClickListener):void (c)] (LINE:3) in method: com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder.setClickListener(android.view.View, int):void, file: classes4.dex
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
            com.jingdong.app.mall.home.r.e.f r0 = r2.mBindData
            if (r0 != 0) goto L5
            return
        L5:
            com.jingdong.common.entity.JumpEntity r0 = r0.getJump()
            com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder$1 r1 = new com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder$1
            r1.<init>()
            r3.setOnClickListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder.setClickListener(android.view.View, int):void");
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        com.jingdong.app.mall.home.r.e.f R = ((s) this.mPresenter).R();
        this.mBindData = R;
        if (R == null) {
            return;
        }
        bindTitleLayout();
        bindItemView();
        bindFloorBg();
        setClickListener(this, 0);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useRoundMarginColor() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public s createPresenter() {
        return new s();
    }
}
