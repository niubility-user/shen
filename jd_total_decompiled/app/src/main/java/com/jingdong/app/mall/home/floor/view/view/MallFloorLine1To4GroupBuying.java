package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.b.g.a;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.special.a;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.p;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorLine1To4GroupBuying extends MallFloorLineMore {
    public static boolean sHasPostClick;
    public static boolean sHasPostExpo;
    private int[] locColors;
    private f mBgSize;
    private GradientDrawable mDraLoc;
    private SimpleDraweeView mFloorBg;
    private e.b mIHomeListener;
    private SimpleDraweeView mIconLoc;
    private SimpleDraweeView mSdvClose;
    private f mSizeClose;
    private f mSizeIcon;
    private f mSizeRightLoc;
    private f mSizeTitle;
    private a mTitleItem;
    private GradientTextView mTvLoc;
    private GradientTextView mTvTitle;

    public MallFloorLine1To4GroupBuying(Context context, c cVar) {
        super(context, cVar);
        this.mSizeTitle = new f(-2, 65);
        this.mBgSize = new f(-1, -1);
        this.mSizeRightLoc = new f(-2, 36);
        this.mSizeIcon = new f(21, 21);
        this.mSizeClose = new f(24, 24);
        this.locColors = new int[]{-855638017, ViewCompat.MEASURED_SIZE_MASK};
        this.mIHomeListener = new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying.1
            {
                MallFloorLine1To4GroupBuying.this = this;
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
                MallFloorLine1To4GroupBuying.this.showTvTitle(true);
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
                MallFloorLine1To4GroupBuying.this.showTvTitle(true);
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                MallFloorLine1To4GroupBuying mallFloorLine1To4GroupBuying = MallFloorLine1To4GroupBuying.this;
                mallFloorLine1To4GroupBuying.showTvTitle(mallFloorLine1To4GroupBuying.mTitleItem == null || TextUtils.isEmpty(((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mFloorBindElement.mParentModel.getJsonString("floorBgImg")));
            }
        };
    }

    private void clipBottom(int i2) {
        com.jingdong.app.mall.home.n.h.e.a(this.mFloorBg, i2);
    }

    private void clipRound(int i2) {
        com.jingdong.app.mall.home.n.h.e.d(this.mFloorBg, i2);
    }

    private void clipTop(int i2) {
        com.jingdong.app.mall.home.n.h.e.h(this.mFloorBg, i2);
    }

    private void initFloorBg() {
        String jsonString = this.mFloorBindElement.mParentModel.getJsonString("floorBgImg");
        SimpleDraweeView simpleDraweeView = this.mFloorBg;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(((MallFloorLineMore) this).mContext);
            this.mFloorBg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView2 = this.mFloorBg;
            addView(simpleDraweeView2, 0, this.mBgSize.u(simpleDraweeView2));
        } else {
            f.d(simpleDraweeView, this.mBgSize, true);
        }
        if (!TextUtils.isEmpty(jsonString)) {
            this.mFloorBg.setVisibility(0);
            e.p(this.mFloorBg, jsonString, e.b, this.mIHomeListener);
            return;
        }
        this.mFloorBg.setVisibility(4);
    }

    private void initFloorContent() {
        int d = d.d(9);
        ViewGroup.LayoutParams layoutParams = this.mFloorContent.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.height = d.d(R2.anim.slide_out_to_top);
        layoutParams2.topMargin = this.mSizeTitle.h();
        this.mFloorContent.setPadding(d, 0, d, 0);
    }

    private void initFloorTitle() {
        a S = ((p) this.mPresenter).S();
        this.mTitleItem = S;
        if (S == null) {
            return;
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying.2
            {
                MallFloorLine1To4GroupBuying.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpEntity e2 = MallFloorLine1To4GroupBuying.this.mTitleItem.e();
                if (e2 == null) {
                    return;
                }
                MallFloorLine1To4GroupBuying.postOnceLog(((p) ((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mPresenter).Q(), false);
                com.jingdong.app.mall.home.r.c.a.s("Home_TopRight", "", e2.toString());
                l.e(MallFloorLine1To4GroupBuying.this.getContext(), e2);
            }
        });
        GradientTextView gradientTextView = this.mTvTitle;
        if (gradientTextView == null) {
            g gVar = new g(((MallFloorLineMore) this).mContext, true);
            gVar.f(1);
            gVar.d(16);
            gVar.m(32);
            this.mTvTitle = gVar.b();
            this.mSizeTitle.E(24, 0, 0, 0);
            View view = this.mTvTitle;
            addView(view, this.mSizeTitle.u(view));
        } else {
            f.d(gradientTextView, this.mSizeTitle, true);
        }
        this.mTvTitle.setText(this.mTitleItem.i());
        SimpleDraweeView simpleDraweeView = this.mSdvClose;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(((MallFloorLineMore) this).mContext);
            this.mSdvClose = homeDraweeView;
            homeDraweeView.setId(R.id.mallfloor_item1);
            this.mSizeClose.E(0, 20, 18, 0);
            RelativeLayout.LayoutParams u = this.mSizeClose.u(this.mSdvClose);
            u.addRule(11);
            addView(this.mSdvClose, u);
        } else {
            f.d(simpleDraweeView, this.mSizeClose, true);
        }
        String[] c2 = this.mTitleItem.c();
        boolean z = this.mTitleItem.l() && c2 != null && c2.length > 0;
        if (z) {
            this.mSdvClose.setVisibility(0);
            setCloseEvent(this.mSdvClose, this.mTitleItem);
            String a = this.mTitleItem.a();
            SimpleDraweeView simpleDraweeView2 = this.mSdvClose;
            JDDisplayImageOptions a2 = com.jingdong.app.mall.home.floor.ctrl.f.a();
            int i2 = R.drawable.home_floor_close_button;
            com.jingdong.app.mall.home.floor.ctrl.f.e(a, simpleDraweeView2, a2.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
            com.jingdong.app.mall.home.r.c.a.y("Home_NegativeCloseExpo", ((p) this.mPresenter).i(), "");
        } else {
            this.mSdvClose.setVisibility(8);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, this.locColors);
        this.mDraLoc = gradientDrawable;
        gradientDrawable.setCornerRadius(this.mSizeRightLoc.h() >> 1);
        if (this.mTvLoc == null) {
            g gVar2 = new g(((MallFloorLineMore) this).mContext, true);
            gVar2.f(1);
            gVar2.d(17);
            gVar2.m(20);
            GradientTextView b = gVar2.b();
            this.mTvLoc = b;
            b.setId(R.id.mallfloor_item2);
            this.mSizeRightLoc.J(38, 0, 0, 0);
            this.mSizeRightLoc.E(0, 15, z ? 49 : 18, 0);
            RelativeLayout.LayoutParams u2 = this.mSizeRightLoc.u(this.mTvLoc);
            u2.addRule(11);
            addView(this.mTvLoc, u2);
        } else {
            this.mSizeRightLoc.E(0, 15, z ? 49 : 18, 0);
            f.d(this.mTvLoc, this.mSizeRightLoc, true);
        }
        int i3 = m.i(this.mTitleItem.f(), -381927);
        String k2 = this.mTitleItem.k();
        SimpleDraweeView simpleDraweeView3 = this.mIconLoc;
        if (simpleDraweeView3 == null) {
            this.mIconLoc = new HomeDraweeView(((MallFloorLineMore) this).mContext);
            this.mSizeIcon.E(13, 23, 0, 0);
            RelativeLayout.LayoutParams u3 = this.mSizeIcon.u(this.mIconLoc);
            u3.addRule(5, this.mTvLoc.getId());
            addView(this.mIconLoc, u3);
        } else {
            f.d(simpleDraweeView3, this.mSizeIcon, true);
        }
        this.mTvLoc.setBackgroundDrawable(this.mDraLoc);
        this.mTvLoc.setText(k2);
        this.mTvLoc.setTextColor(i3);
        this.mTvLoc.setMaxWidth(d.d(260));
        if (TextUtils.isEmpty(k2)) {
            this.mTvLoc.setVisibility(4);
            this.mIconLoc.setVisibility(4);
        } else {
            this.mTvLoc.setVisibility(0);
            this.mIconLoc.setVisibility(0);
        }
        e.e(this.mIconLoc, this.mTitleItem.g(), false);
    }

    public static void postOnceLog(String str, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (z) {
            if (sHasPostExpo) {
                return;
            }
            sHasPostExpo = true;
            str2 = "\u4eac\u559c\u62fc\u62fc\u66dd\u5149";
        } else if (sHasPostClick) {
            return;
        } else {
            sHasPostClick = true;
            str2 = "\u4eac\u559c\u62fc\u62fc\u70b9\u51fb";
        }
        new com.jingdong.app.mall.home.q.a(str2, z, str).b();
    }

    private void setCloseEvent(final View view, final a aVar) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying.3
            {
                MallFloorLine1To4GroupBuying.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                final Context context = MallFloorLine1To4GroupBuying.this.getContext();
                if (context instanceof BaseActivity) {
                    com.jingdong.app.mall.home.floor.view.special.a.f().g((BaseActivity) context, view, aVar.c(), new a.d() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying.3.1
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // com.jingdong.app.mall.home.floor.view.special.a.d
                        public void onUserClose(String str) {
                            h hVar;
                            com.jingdong.app.mall.home.a.a(((p) ((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mPresenter).i());
                            JDHomeFragment z0 = JDHomeFragment.z0();
                            if (((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mFloorBindElement == null || z0 == null || (hVar = ((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mFloorBindElement.mParentModel) == null) {
                                return;
                            }
                            hVar.c0 = false;
                            HomeRecyclerAdapter t0 = z0.t0();
                            if (t0 != null) {
                                t0.q(((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mFloorBindElement);
                            }
                            String d = aVar.d();
                            if (TextUtils.isEmpty(d)) {
                                CharSequence text = context.getResources().getText(R.string.home_feedback_reason_toast);
                                com.jingdong.app.mall.home.o.a.f.n(text);
                                d = (String) text;
                            }
                            new com.jingdong.app.mall.home.q.a("\u8d1f\u53cd\u9988\u5173\u95ed", aVar.b()).b();
                            ToastUtils.showToastInCenter(context, (byte) 2, d, 0);
                            com.jingdong.app.mall.home.r.c.a.s("Home_NegativeFloorReason", ((p) ((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mPresenter).i() + CartConstant.KEY_YB_INFO_LINK + str, "");
                        }
                    });
                    com.jingdong.app.mall.home.r.c.a.s("Home_NegativeFloorClose", ((p) ((BaseMallColorFloor) MallFloorLine1To4GroupBuying.this).mPresenter).i(), "");
                }
            }
        });
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void checkFloorClip(float[] fArr) {
        int i2 = isFirstLineFloor() ? (int) fArr[0] : 0;
        int i3 = isLastLineFloor() ? (int) fArr[2] : 0;
        if (i2 != 0 && i3 != 0) {
            clipRound(i2);
        } else if (i2 != 0) {
            clipTop(i2);
        } else if (i3 != 0) {
            clipBottom(i3);
        } else {
            clipRound(0);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore
    protected void initLineItem() {
        BaseLineLayout lineViewByCache;
        List<com.jingdong.app.mall.home.floor.view.linefloor.base.a> T = ((p) this.mPresenter).T();
        int size = T.size();
        int size2 = this.mCacheViewList.size();
        boolean z = size == size2;
        if (!z && size2 > 0) {
            cleanUIOnMainThread();
        }
        for (int i2 = 0; i2 < size; i2++) {
            com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar = T.get(i2);
            com.jingdong.app.mall.home.o.a.f.n(aVar);
            com.jingdong.app.mall.home.floor.view.b.g.f fVar = (com.jingdong.app.mall.home.floor.view.b.g.f) aVar;
            if (z) {
                lineViewByCache = this.mCacheViewList.get(i2);
            } else {
                lineViewByCache = fVar.k().getLineViewByCache(((MallFloorLineMore) this).mContext, this);
                this.mCacheViewList.add(lineViewByCache);
                this.mFloorContent.addView(lineViewByCache);
            }
            lineViewByCache.q(fVar, this, i2, 0);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        initFloorBg();
        initFloorTitle();
        initFloorContent();
        initLineItem();
        postOnceLog(((p) this.mPresenter).R(), true);
    }

    protected void showTvTitle(boolean z) {
        this.mTvTitle.setVisibility(z ? 0 : 8);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useBgMarginColor() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        return true;
    }
}
