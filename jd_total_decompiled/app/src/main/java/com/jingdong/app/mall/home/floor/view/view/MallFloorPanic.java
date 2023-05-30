package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.model.entity.PanicFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.PanicFloorEngine;
import com.jingdong.app.mall.home.floor.view.adapter.MallPanicFloorRecyclerAdapter;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.ElasticHorizontalRecyclerView;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.floor.view.widget.c;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.e;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.f.a.w;
import com.jingdong.app.mall.home.widget.HomeTitleRightView;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorPanic extends BaseMallFloor<w> {
    private static final int FIRST_BETWEEN_LAST = 4;
    private static final int RIGHT_HEIGHT_NEW = 36;
    protected static final String TAG = "MallFloor_Panic";
    private int firstItemPosition;
    private boolean isShown;
    private String lastAlgorithmFrom;
    private int lastItemPosition;
    private f mBgSize;
    private FitTopImage mFloorBg;
    private ElasticHorizontalRecyclerView mRecyclerView;

    public MallFloorPanic(Context context) {
        super(context);
        this.mBgSize = new f(-1, -1);
    }

    private LinearLayout addSecKillView(w wVar, ViewGroup.LayoutParams layoutParams) {
        Typeface typeFace = FontsUtil.getTypeFace(getContext());
        Typeface typeFace2 = FontsUtil.getTypeFace(getContext(), 4098);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(wVar.w0() ? getContext().getResources().getDrawable(R.drawable.home_seckill_time_bg) : getSecKillStockBack());
        linearLayout.setOrientation(0);
        TextView textView = new TextView(getContext());
        textView.setTypeface(typeFace2, 1);
        textView.setTextSize(0, d.d(wVar.w0() ? 18 : 22));
        textView.setTextColor(-1);
        textView.setIncludeFontPadding(false);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(wVar.w0() ? d.d(69) : d.d(R2.anim.settlement_dialog_bottom_exit) - wVar.f0(), -1);
        textView.setBackgroundDrawable(wVar.w0() ? null : getSecKillFillBack());
        linearLayout.addView(textView, layoutParams2);
        textView.setGravity(17);
        textView.setText(wVar.l0());
        TimeFormatView timeFormatView = new TimeFormatView(getContext());
        timeFormatView.n(typeFace);
        wVar.v0(timeFormatView);
        wVar.E0(timeFormatView);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(wVar.w0() ? d.d(95) : wVar.f0(), wVar.e0());
        layoutParams3.leftMargin = d.d(((w) this.mPresenter).w0() ? 3 : 0);
        layoutParams3.gravity = 16;
        linearLayout.addView(timeFormatView, layoutParams3);
        try {
            wVar.c0(linearLayout, timeFormatView);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return linearLayout;
    }

    private void createViewPager() {
        if (this.mRecyclerView != null) {
            return;
        }
        this.mRecyclerView = new ElasticHorizontalRecyclerView(this.mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        e.b(this.mRecyclerView);
        this.mRecyclerView.b(new ElasticHorizontalRecyclerView.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.3
            {
                MallFloorPanic.this = this;
            }

            @Override // com.jingdong.app.mall.home.floor.view.widget.ElasticHorizontalRecyclerView.b
            public void onFooterClick() {
                ((w) ((BaseMallColorFloor) MallFloorPanic.this).mPresenter).z0();
            }
        });
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.4
            {
                MallFloorPanic.this = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (i2 != 0) {
                    a.u("Home_Seckill_Slide", ((w) ((BaseMallColorFloor) MallFloorPanic.this).mPresenter).V(false), ((w) ((BaseMallColorFloor) MallFloorPanic.this).mPresenter).g0(), RecommendMtaUtils.Home_PageId, null, "");
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                com.jingdong.app.mall.home.o.a.f.n(layoutManager);
                LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) layoutManager;
                int findFirstVisibleItemPosition = linearLayoutManager2.findFirstVisibleItemPosition();
                int findLastVisibleItemPosition = linearLayoutManager2.findLastVisibleItemPosition();
                MallFloorPanic mallFloorPanic = MallFloorPanic.this;
                mallFloorPanic.firstItemPosition = Math.min(findFirstVisibleItemPosition, mallFloorPanic.firstItemPosition);
                MallFloorPanic mallFloorPanic2 = MallFloorPanic.this;
                mallFloorPanic2.lastItemPosition = Math.max(findLastVisibleItemPosition, mallFloorPanic2.lastItemPosition);
            }
        });
    }

    private synchronized View getContentView(int i2) {
        if (((w) this.mPresenter).Y()) {
            return null;
        }
        createViewPager();
        ViewGroup.LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2 == null) {
            layoutParams2 = new RelativeLayout.LayoutParams(-1, i2);
        } else {
            layoutParams2.height = i2;
        }
        this.mRecyclerView.setLayoutParams(layoutParams2);
        this.mRecyclerView.setPadding(d.d(12), 0, 0, 0);
        this.mRecyclerView.setClipToPadding(false);
        List<?> T = ((w) this.mPresenter).T();
        this.mRecyclerView.setGoRedirect(T.size() > 0);
        this.mRecyclerView.setAdapter(new MallPanicFloorRecyclerAdapter(this.mContext, (w) this.mPresenter, T));
        return this.mRecyclerView;
    }

    private View getHeaderView() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setContentDescription(((w) this.mPresenter).s());
        relativeLayout.setId(R.id.mallfloor_item1);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, ((w) this.mPresenter).j0()));
        TextView textView = new TextView(getContext());
        textView.setText(((w) this.mPresenter).s());
        textView.setTextSize(0, d.d(32));
        textView.getPaint().setFakeBoldText(true);
        textView.setMaxWidth(d.d(130));
        textView.setGravity(16);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(((w) this.mPresenter).u0());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.leftMargin = d.d(20);
        relativeLayout.addView(textView, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(d.d(((w) this.mPresenter).w0() ? R2.anim.settlement_dialog_right_exit : R2.anim.slide_in_from_top_medium_time), d.d(((w) this.mPresenter).w0() ? 32 : 36));
        layoutParams2.leftMargin = d.d(((w) this.mPresenter).w0() ? R2.anim.pickerview_dialog_scale_in : 154);
        layoutParams2.addRule(15);
        relativeLayout.addView(addSecKillView((w) this.mPresenter, layoutParams2));
        if (!TextUtils.isEmpty(((w) this.mPresenter).q())) {
            int[] r0 = ((w) this.mPresenter).r0();
            HomeTitleRightView homeTitleRightView = new HomeTitleRightView(getContext());
            homeTitleRightView.setGravity(16);
            homeTitleRightView.setMaxLines(1);
            homeTitleRightView.setTextGradient(GradientTextView.GradientType.LeftTopToRightBottom, r0);
            homeTitleRightView.setText(((w) this.mPresenter).q());
            homeTitleRightView.setEllipsize(TextUtils.TruncateAt.END);
            homeTitleRightView.setMaxWidth(d.d(300));
            homeTitleRightView.setPadding(0, 0, d.d(40), 0);
            homeTitleRightView.b(d.d(21));
            homeTitleRightView.a(r0.length > 1 ? r0[r0.length - 1] : r0[0]);
            homeTitleRightView.setTextSize(0, d.d(24));
            homeTitleRightView.c(((w) this.mPresenter).w0());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams3.addRule(11);
            relativeLayout.addView(homeTitleRightView, layoutParams3);
        }
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.5
            {
                MallFloorPanic.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MallFloorPanic.this.onClickRightCornerAdView();
            }
        });
        loadTitleImg(((w) this.mPresenter).s0(), textView, relativeLayout);
        return relativeLayout;
    }

    private Drawable getSecKillFillBack() {
        float d = d.d(18);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{d, d, d, d, d, d, d, d}, null, null));
        shapeDrawable.getPaint().setColor(-54495);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        return shapeDrawable;
    }

    private Drawable getSecKillStockBack() {
        float d = d.d(18);
        float[] fArr = {d, d, d, d, d, d, d, d};
        float d2 = d.d(1) + 1;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(d2, d2, d2, d2), fArr));
        shapeDrawable.getPaint().setColor(-54495);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setStrokeWidth(d2);
        shapeDrawable.getPaint().setAntiAlias(true);
        return shapeDrawable;
    }

    private void loadTitleImg(String str, final TextView textView, final View view) {
        if (!TextUtils.isEmpty(str)) {
            com.jingdong.app.mall.home.floor.ctrl.f.i(str, new com.jingdong.app.mall.home.t.a() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.6
                {
                    MallFloorPanic.this = this;
                }

                @Override // com.jingdong.app.mall.home.t.a
                public void onBitmapWithUiNull(Bitmap bitmap) {
                    if (bitmap == null || bitmap.isRecycled()) {
                        MallFloorPanic.this.loadingTitleImgFailed(textView);
                    } else {
                        MallFloorPanic.this.loadingTitleImgComplete(textView, bitmap, view);
                    }
                }
            });
        } else {
            loadingTitleImgFailed(textView);
        }
    }

    public void loadingTitleImgComplete(TextView textView, Bitmap bitmap, View view) {
        c cVar = new c(getResources(), bitmap);
        cVar.setBounds(0, 0, ((w) this.mPresenter).k0(), ((w) this.mPresenter).j0());
        view.setBackgroundDrawable(cVar);
        textView.setText("");
    }

    public void loadingTitleImgFailed(TextView textView) {
        textView.setText(((w) this.mPresenter).s());
    }

    private void notifyDataSetChange() {
        final RecyclerView.Adapter adapter;
        ElasticHorizontalRecyclerView elasticHorizontalRecyclerView = this.mRecyclerView;
        if (elasticHorizontalRecyclerView == null || (adapter = elasticHorizontalRecyclerView.getAdapter()) == null) {
            return;
        }
        if (!this.mRecyclerView.isComputingLayout()) {
            adapter.notifyDataSetChanged();
        } else {
            com.jingdong.app.mall.home.o.a.f.u0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.2
                {
                    MallFloorPanic.this = this;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                public void safeRun() {
                    if (MallFloorPanic.this.mRecyclerView.isComputingLayout()) {
                        return;
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void sendEventAndClearPosition() {
        if (this.isShown) {
            w wVar = (w) this.mPresenter;
            int max = Math.max(this.firstItemPosition, 0);
            int i2 = this.lastItemPosition;
            if (i2 == 0) {
                i2 = 4;
            }
            String d0 = wVar.d0(max, i2);
            if (!TextUtils.isEmpty(d0)) {
                a.y("Home_SeckillExpo", "", d0);
            }
        }
        this.isShown = false;
        this.firstItemPosition = 0;
        this.lastItemPosition = 0 + 4;
        this.lastAlgorithmFrom = "";
    }

    private void setFloorBg() {
        String jsonString = this.mFloorBindElement.getJsonString("bgImg");
        FitTopImage fitTopImage = new FitTopImage(this.mContext);
        this.mFloorBg = fitTopImage;
        addView(fitTopImage, 0, this.mBgSize.u(fitTopImage));
        if (!TextUtils.isEmpty(jsonString)) {
            this.mFloorBg.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.mFloorBg, jsonString);
            return;
        }
        this.mFloorBg.setVisibility(4);
    }

    private void startProductDetailActivity(Context context, Long l2, String str, SourceEntity sourceEntity) {
        if (context == null || l2 == null) {
            return;
        }
        Bundle build = DeeplinkProductDetailHelper.BundleBuilder.from(l2.longValue()).imageTitlePrice(null, str, null).sourceEntity(sourceEntity).build();
        if (sourceEntity != null) {
            build.putSerializable("source", sourceEntity);
        }
        String string = build.getString("clickUrl");
        if (!TextUtils.isEmpty(string)) {
            build.putString("targetUrl", string);
        }
        DeeplinkProductDetailHelper.startProductDetail(context, build);
    }

    private void startTimeTickOnMainThread() {
        ((w) this.mPresenter).F0();
    }

    public void execJump(JumpEntity jumpEntity) {
        l.e(getContext(), jumpEntity);
    }

    public void initFloorView() {
        if (isMainThread()) {
            initFloorViewOnMainThread();
        } else {
            postToMainThread("initFloorViewOnMainThread", null, new Object[0]);
        }
    }

    protected void initFloorViewOnMainThread() {
        notifyDataSetChange();
    }

    public void jumpToRightCornerAd(JumpEntity jumpEntity) {
        l.a(jumpEntity);
        l.e(getContext(), jumpEntity);
    }

    public void onClickItem(Product product, boolean z, int i2) {
        if (l.k() || product == null) {
            return;
        }
        String str = "";
        if (z) {
            startProductDetailActivity(getContext(), product.getId(), product.getName(), new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_MIAOSHA, ""));
            return;
        }
        JumpEntity jumpEntity = product.jump;
        if (jumpEntity != null) {
            l.e(this.mContext, jumpEntity);
            try {
                int i3 = product.msItemType;
                if (i3 != 1 && i3 != 8) {
                    JDMtaUtils.sendCommonData(getContext(), "Home_SeckillMarketAccess", "" + (i2 + 1), "", a.f10642k, "", "MiaoShaActivity", "", RecommendMtaUtils.Home_PageId);
                    return;
                }
                String o0 = ((w) this.mPresenter).o0(i2);
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(l.a)) {
                    str = l.a + CartConstant.KEY_YB_INFO_LINK;
                }
                sb.append(str);
                sb.append(i2 + 1);
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(product.getId());
                a.u("Home_HandSeckill", "", o0, RecommendMtaUtils.Home_PageId, null, sb.toString());
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    protected void onClickRightCornerAdView() {
        String str;
        if (l.k()) {
            return;
        }
        jumpToRightCornerAd(((w) this.mPresenter).i0());
        try {
            String V = ((w) this.mPresenter).V(true);
            String q0 = ((w) this.mPresenter).q0();
            StringBuilder sb = new StringBuilder();
            if (TextUtils.isEmpty(l.a)) {
                str = "";
            } else {
                str = l.a + CartConstant.KEY_YB_INFO_LINK;
            }
            sb.append(str);
            sb.append("0_");
            a.u("Home_HandSeckill", V, q0, RecommendMtaUtils.Home_PageId, null, sb.toString());
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeRefresh() {
        super.onHomeRefresh();
        sendEventAndClearPosition();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        this.isShown = isFloorDisplay();
        ElasticHorizontalRecyclerView elasticHorizontalRecyclerView = this.mRecyclerView;
        if (elasticHorizontalRecyclerView == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = elasticHorizontalRecyclerView.getLayoutManager();
        com.jingdong.app.mall.home.o.a.f.n(layoutManager);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (linearLayoutManager == null) {
            return;
        }
        this.firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        this.lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        boolean isFloorDisplay = isFloorDisplay();
        if (this.isShown || !isFloorDisplay) {
            return;
        }
        this.isShown = true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeStop() {
        super.onHomeStop();
        if (this.isShown) {
            String d0 = ((w) this.mPresenter).d0(this.firstItemPosition, this.lastItemPosition);
            if (TextUtils.isEmpty(d0)) {
                return;
            }
            a.y("Home_SeckillExpo", "", d0);
        }
    }

    public void onRefreshViewPager(int i2, int i3, int i4) {
        sendEventAndClearPosition();
        com.jingdong.app.mall.home.o.a.f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorPanic.1
            {
                MallFloorPanic.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MallFloorPanic mallFloorPanic = MallFloorPanic.this;
                mallFloorPanic.isShown = mallFloorPanic.isFloorDisplay();
            }
        });
        this.lastAlgorithmFrom = ((w) this.mPresenter).m0();
        removeAllViews();
        ((w) this.mPresenter).Z();
        ((w) this.mPresenter).D0();
        notifyDataSetChange();
        View contentView = getContentView(i2);
        if (contentView != null) {
            setFloorBg();
            View headerView = getHeaderView();
            addView(headerView);
            setBlowByView(contentView, headerView);
            addView(contentView);
            onSetVisible(true);
            return;
        }
        onSetVisible(false);
    }

    public void sendMaiDianData(String str, String str2) {
        JDMtaUtils.sendCommonData(getContext(), str, str2, "", a.f10642k, "", "MiaoShaActivity", "", RecommendMtaUtils.Home_PageId);
    }

    public void startTimeTick() {
        if (isMainThread()) {
            startTimeTickOnMainThread();
        } else {
            postToMainThread("startTimeTickOnMainThread", null, new Object[0]);
        }
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
    @Nullable
    public w createPresenter() {
        return new w(PanicFloorEntity.class, PanicFloorEngine.class);
    }
}
