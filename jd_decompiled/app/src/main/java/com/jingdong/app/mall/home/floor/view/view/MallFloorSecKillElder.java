package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.feedssub.a;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.SecKillElderEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LabelLayout;
import com.jingdong.app.mall.home.floor.view.widget.ElderFloorTitleLayout;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.f.a.x;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class MallFloorSecKillElder extends BaseMallFloor<x> implements SecKillElderEntity.LadySecKillDataChangeCallBack {
    private final SecKillElderItemView[] itemViews;
    private final f mBgSize;
    private SecKillElderEntity mBindData;
    private SimpleDraweeView mFloorBg;
    private SecKillElderTitle mTitleLayout;
    private final f mTitleSize;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class SecKillElderItemView extends RelativeLayout {
        private f containerSize;
        private TextView jdPrice;
        private Context mContext;
        private f mJdPriceSize;
        private TextView mShaPrice;
        private f mShaPriceSize;
        private LinearLayout priceContainer;
        private SimpleDraweeView skuIv;
        private f skuSize;

        public SecKillElderItemView(Context context) {
            super(context);
            this.skuSize = new f(-1, 208);
            this.containerSize = new f(-2, 56);
            this.mShaPriceSize = new f(-2, -1);
            this.mJdPriceSize = new f(-2, 48);
            this.mContext = context;
        }

        private void bindSku(Product product) {
            SimpleDraweeView simpleDraweeView = this.skuIv;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
                this.skuIv = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.skuIv, this.skuSize.u(this.skuIv));
            } else {
                f.c(simpleDraweeView, this.skuSize);
            }
            e.d(this.skuIv, d.d(12));
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.skuIv, product.getImageUrl());
        }

        public void bindItem(final SecKillElderEntity secKillElderEntity, Product product, final int i2) {
            if (product == null) {
                return;
            }
            bindSku(product);
            bindPrice(secKillElderEntity, product);
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder.SecKillElderItemView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SecKillElderEntity secKillElderEntity2 = secKillElderEntity;
                    if (secKillElderEntity2 != null) {
                        secKillElderEntity2.onItemClick(i2, SecKillElderItemView.this.mContext);
                    }
                }
            });
        }

        public void bindPrice(SecKillElderEntity secKillElderEntity, Product product) {
            LinearLayout linearLayout = this.priceContainer;
            if (linearLayout == null) {
                this.priceContainer = new LinearLayout(this.mContext);
                this.containerSize.F(new Rect(0, 215, 0, 0));
                RelativeLayout.LayoutParams u = this.containerSize.u(this.priceContainer);
                u.addRule(14);
                addView(this.priceContainer, u);
            } else {
                f.c(linearLayout, this.containerSize);
            }
            TextView textView = this.mShaPrice;
            if (textView == null) {
                TextView textView2 = new TextView(this.mContext);
                this.mShaPrice = textView2;
                textView2.setId(R.id.mallfloor_item1);
                this.priceContainer.addView(this.mShaPrice, this.mShaPriceSize.i(this.mShaPrice));
                this.mShaPrice.setSingleLine();
                this.mShaPrice.setEllipsize(TextUtils.TruncateAt.END);
                this.mShaPrice.setGravity(17);
            } else {
                f.c(textView, this.mShaPriceSize);
            }
            this.mShaPrice.setMaxWidth(d.d(208));
            this.mShaPrice.setTextSize(0, d.d(36));
            this.mShaPrice.setTypeface(FontsUtil.getTypeFace(getContext()));
            this.mShaPrice.setTextColor(m.j(secKillElderEntity.getPriceColor(), -1371136));
            this.mShaPrice.setText(new SpannableString(a.e(product.getMiaoShaPrice())));
            this.mJdPriceSize.F(new Rect(8, 0, 0, 0));
            TextView textView3 = this.jdPrice;
            if (textView3 == null) {
                TextView textView4 = new TextView(this.mContext);
                this.jdPrice = textView4;
                this.priceContainer.addView(this.jdPrice, this.mJdPriceSize.i(textView4));
                this.jdPrice.setSingleLine();
                this.jdPrice.setEllipsize(TextUtils.TruncateAt.END);
                this.jdPrice.setGravity(17);
            } else {
                f.c(textView3, this.mJdPriceSize);
            }
            this.jdPrice.setTextSize(0, d.d(28));
            this.jdPrice.setPaintFlags(17);
            this.jdPrice.setTextColor(-7566196);
            this.jdPrice.setTypeface(FontsUtil.getTypeFace(getContext()));
            this.jdPrice.setText(new SpannableString(a.e(product.getJdPriceNoNull())));
            this.jdPrice.setVisibility((((((this.mShaPrice.getPaint().measureText(this.mShaPrice.getText().toString()) + ((float) d.d(8))) + this.jdPrice.getPaint().measureText(this.jdPrice.getText().toString())) > ((float) d.d(208)) ? 1 : (((this.mShaPrice.getPaint().measureText(this.mShaPrice.getText().toString()) + ((float) d.d(8))) + this.jdPrice.getPaint().measureText(this.jdPrice.getText().toString())) == ((float) d.d(208)) ? 0 : -1)) > 0) || TextUtils.isEmpty(product.getJdPriceNoNull())) ? false : true ? 0 : 8);
        }
    }

    /* loaded from: classes4.dex */
    public static class SecKillElderTitle extends RelativeLayout {
        private int flipperCount;
        private AtomicBoolean isSendExpo;
        private final f leftSize;
        private ElderFloorTitleLayout leftTitle;
        private int[] mBgOperate;
        private SecKillElderEntity mBindItem;
        private Context mContext;
        private TimeFormatView mDateView;
        private JDViewFlipper mFlipper;
        private b mFlipperRunnable;
        private f mFlipperSize;
        private Handler mHandler;
        private boolean mHasFlipperAnim;
        private f mLabelSize;
        private LabelLayout mLabelText;
        private LinearLayout mOperateLayout;
        private f mOperateSize;
        private x mPresenter;
        private SimpleDraweeView mTimeImgView;
        private RelativeLayout mTimeLayout;
        private TextView mTimeNameView;

        public SecKillElderTitle(Context context) {
            super(context);
            f fVar = new f(-2, -1);
            this.leftSize = fVar;
            this.flipperCount = 0;
            this.mHandler = new Handler(Looper.getMainLooper());
            this.mBgOperate = new int[]{-580315, -768442, -20831};
            this.isSendExpo = new AtomicBoolean(false);
            this.mFlipperRunnable = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder.SecKillElderTitle.3
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    int playCount = SecKillElderTitle.this.mBindItem.getPlayCount();
                    if (playCount <= 0 || SecKillElderTitle.this.mBindItem.isShowOperate() || !SecKillElderTitle.this.mHasFlipperAnim) {
                        return;
                    }
                    SecKillElderTitle.this.mFlipper.showNext();
                    SecKillElderTitle.access$408(SecKillElderTitle.this);
                    String str = "playCount==" + playCount + "---flipperCount==" + SecKillElderTitle.this.flipperCount;
                    if (SecKillElderTitle.this.flipperCount >= (playCount * 2) - 1) {
                        SecKillElderTitle.this.mPresenter.V(SecKillElderTitle.this.mBindItem.getOperateWord(), playCount);
                        SecKillElderTitle.this.mHasFlipperAnim = false;
                        return;
                    }
                    SecKillElderTitle.this.mHandler.postDelayed(this, 3000L);
                }
            };
            this.mContext = context;
            ElderFloorTitleLayout elderFloorTitleLayout = new ElderFloorTitleLayout(context, -1371136);
            this.leftTitle = elderFloorTitleLayout;
            elderFloorTitleLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder.SecKillElderTitle.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SecKillElderTitle.this.onTitleJump(view.getContext());
                }
            });
            addView(this.leftTitle, fVar.u(this.leftTitle));
            JDViewFlipper jDViewFlipper = new JDViewFlipper(getContext());
            this.mFlipper = jDViewFlipper;
            jDViewFlipper.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder.SecKillElderTitle.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SecKillElderTitle.this.onTitleJump(view.getContext());
                }
            });
            f fVar2 = new f(-2, 36);
            this.mFlipperSize = fVar2;
            fVar2.F(new Rect(0, 0, 24, 0));
            RelativeLayout.LayoutParams u = this.mFlipperSize.u(this.mFlipper);
            u.addRule(15);
            u.addRule(11);
            addView(this.mFlipper, u);
            this.mFlipper.setInAnimation(getContext(), R.anim.home_in_animation_bottom);
            this.mFlipper.setOutAnimation(getContext(), R.anim.home_out_animation_top);
            initFlipperView();
            this.mFlipper.setContentDescription("\u4eac\u4e1c\u79d2\u6740");
        }

        static /* synthetic */ int access$408(SecKillElderTitle secKillElderTitle) {
            int i2 = secKillElderTitle.flipperCount;
            secKillElderTitle.flipperCount = i2 + 1;
            return i2;
        }

        private void bindOperate(SecKillElderEntity secKillElderEntity) {
            int playCount = secKillElderEntity.getPlayCount();
            boolean isShowOperate = secKillElderEntity.isShowOperate();
            if (playCount <= 0 && !isShowOperate) {
                this.mFlipper.removeView(this.mOperateLayout);
                return;
            }
            if (this.mFlipper.indexOfChild(this.mOperateLayout) < 0) {
                m.b(this.mFlipper, this.mOperateLayout, -1);
            }
            String operateWord = secKillElderEntity.getOperateWord();
            if (TextUtils.isEmpty(operateWord)) {
                LabelLayout labelLayout = this.mLabelText;
                if (labelLayout != null) {
                    labelLayout.setVisibility(8);
                    return;
                }
                return;
            }
            String j2 = com.jingdong.app.mall.home.o.a.f.j(6, operateWord);
            int d = d.d(12);
            LabelLayout labelLayout2 = this.mLabelText;
            if (labelLayout2 == null) {
                this.mLabelText = new LabelLayout(getContext(), false, false);
                f fVar = new f(R2.anim.popup_center_enter, 36);
                this.mLabelSize = fVar;
                this.mOperateLayout.addView(this.mLabelText, fVar.i(this.mLabelText));
            } else {
                f.c(labelLayout2, this.mLabelSize);
            }
            this.mLabelText.t(this.mBgOperate);
            this.mLabelText.w(j2, 28);
            this.mLabelText.setGravity(17);
            this.mLabelText.setPadding(d, 0, d, 0);
            if (this.mFlipper.getCurrentView() != this.mOperateLayout) {
                this.mFlipper.showNext();
            }
        }

        private void bindRight() {
            bindSecKillTime(this.mBindItem);
            f.d(this.mFlipper, this.mFlipperSize, true);
            f.c(this.mOperateLayout, this.mOperateSize);
        }

        private void bindSecKillTime(SecKillElderEntity secKillElderEntity) {
            this.flipperCount = 0;
            f fVar = new f(35, -1);
            Typeface typeFace = FontsUtil.getTypeFace(getContext());
            TextView textView = this.mTimeNameView;
            if (textView == null) {
                TextView textView2 = new TextView(getContext());
                this.mTimeNameView = textView2;
                textView2.setIncludeFontPadding(false);
                this.mTimeNameView.setSingleLine();
                this.mTimeNameView.setEllipsize(TextUtils.TruncateAt.END);
                this.mTimeNameView.setTextColor(-1436928);
                this.mTimeNameView.setTypeface(typeFace);
                this.mTimeNameView.getPaint().setFakeBoldText(true);
                RelativeLayout relativeLayout = this.mTimeLayout;
                TextView textView3 = this.mTimeNameView;
                relativeLayout.addView(textView3, fVar.u(textView3));
            } else {
                f.c(textView, fVar);
            }
            this.mTimeNameView.setTextSize(0, d.d(31));
            this.mTimeNameView.setText(secKillElderEntity.getNameText());
            f fVar2 = new f(36, 36);
            fVar2.E(39, 0, 0, 0);
            SimpleDraweeView simpleDraweeView = this.mTimeImgView;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.mTimeImgView = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout relativeLayout2 = this.mTimeLayout;
                SimpleDraweeView simpleDraweeView2 = this.mTimeImgView;
                relativeLayout2.addView(simpleDraweeView2, fVar2.u(simpleDraweeView2));
            } else {
                f.c(simpleDraweeView, fVar2);
            }
            this.mTimeImgView.setImageResource(R.drawable.home_seckill_name_elder);
            f fVar3 = new f(R2.anim.mtrl_bottom_sheet_slide_in, -1);
            fVar3.F(new Rect(87, 0, 0, 0));
            TimeFormatView timeFormatView = this.mDateView;
            if (timeFormatView == null) {
                TimeFormatView timeFormatView2 = new TimeFormatView(getContext());
                this.mDateView = timeFormatView2;
                timeFormatView2.n(typeFace);
                this.mDateView.k(-1);
                this.mDateView.i(-2026752);
                RelativeLayout.LayoutParams u = fVar3.u(this.mDateView);
                u.addRule(15);
                this.mTimeLayout.addView(this.mDateView, u);
            } else {
                f.c(timeFormatView, fVar3);
            }
            this.mDateView.e(-381927);
            this.mDateView.h(d.d(36));
            this.mDateView.g(d.d(36));
            this.mDateView.l(d.d(32));
            this.mPresenter.W(this);
        }

        private LinearLayout createOperateWordView() {
            this.mOperateLayout = new LinearLayout(getContext());
            f fVar = new f(-1, 36);
            this.mOperateSize = fVar;
            LinearLayout linearLayout = this.mOperateLayout;
            linearLayout.setLayoutParams(fVar.i(linearLayout));
            this.mOperateLayout.setGravity(17);
            return this.mOperateLayout;
        }

        private RelativeLayout createTimeViewLayout() {
            this.mTimeLayout = new RelativeLayout(getContext());
            this.mTimeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            return this.mTimeLayout;
        }

        private void initFlipperView() {
            this.mFlipper.addView(createTimeViewLayout());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onTitleJump(Context context) {
            SecKillElderEntity secKillElderEntity = this.mBindItem;
            if (secKillElderEntity == null) {
                return;
            }
            JumpEntity operateJump = secKillElderEntity.getOperateJump();
            if (operateJump != null) {
                com.jingdong.app.mall.home.r.c.a.s("Home_SeckillOperaWord", "", operateJump.getSrvJson());
            }
            if (operateJump != null) {
                l.e(context, operateJump);
            } else {
                this.mBindItem.onItemClick(0, context);
            }
        }

        public void bind(SecKillElderEntity secKillElderEntity, x xVar) {
            if (secKillElderEntity == null || xVar == null) {
                return;
            }
            this.isSendExpo.set(false);
            this.mBindItem = secKillElderEntity;
            this.mPresenter = xVar;
            this.leftTitle.d(secKillElderEntity.getElement());
            f.c(this.leftTitle, this.leftSize);
            bindRight();
            postWorldExpo();
        }

        public void postWorldExpo() {
            JumpEntity operateJump;
            if (this.mBindItem == null || this.isSendExpo.getAndSet(true) || (operateJump = this.mBindItem.getOperateJump()) == null) {
                return;
            }
            com.jingdong.app.mall.home.r.c.a.y("Home_SeckillOperaWordExpo", "", operateJump.getSrvJson());
        }

        public void startFlipper() {
            this.mHandler.removeCallbacks(this.mFlipperRunnable);
            this.mHandler.postDelayed(this.mFlipperRunnable, 1000L);
        }

        public void stopFlipper() {
            this.mHandler.removeCallbacks(this.mFlipperRunnable);
        }

        public void updateTimeDrawable(String str, String str2, String str3) {
            this.mDateView.m(str, str2, str3);
        }

        public void updateTimeGone(boolean z) {
            c.k(z, this.mTimeLayout);
        }
    }

    public MallFloorSecKillElder(Context context) {
        super(context);
        this.mTitleSize = new f(-1, 100);
        this.mBgSize = new f(-1, -1);
        this.itemViews = new SecKillElderItemView[3];
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
        CopyOnWriteArrayList<Product> products;
        SecKillElderEntity secKillElderEntity = this.mBindData;
        if (secKillElderEntity == null || (products = secKillElderEntity.getProducts()) == null || products.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < 3 && i2 < products.size(); i2++) {
            f fVar = new f(208, 286);
            fVar.F(new Rect((i2 * 223) + 24, 100, 0, 0));
            Product product = products.get(i2);
            SecKillElderItemView secKillElderItemView = this.itemViews[i2];
            if (secKillElderItemView == null) {
                secKillElderItemView = new SecKillElderItemView(getContext());
                addView(secKillElderItemView, fVar.u(secKillElderItemView));
                this.itemViews[i2] = secKillElderItemView;
            } else {
                f.c(secKillElderItemView, fVar);
            }
            secKillElderItemView.bindItem(this.mBindData, product, i2);
        }
    }

    private void bindSecKill() {
        SecKillElderEntity Q = ((x) this.mPresenter).Q();
        this.mBindData = Q;
        if (Q == null) {
            return;
        }
        Q.setDataChangeCallBack(this);
        bindTitleLayout();
        bindItemView();
        bindFloorBg();
        sendExpo();
    }

    private void bindTitleLayout() {
        if (this.mBindData == null) {
            return;
        }
        SecKillElderTitle secKillElderTitle = this.mTitleLayout;
        if (secKillElderTitle == null) {
            SecKillElderTitle secKillElderTitle2 = new SecKillElderTitle(getContext());
            this.mTitleLayout = secKillElderTitle2;
            addView(secKillElderTitle2, this.mTitleSize.u(secKillElderTitle2));
        } else {
            f.c(secKillElderTitle, this.mTitleSize);
        }
        this.mTitleLayout.bind(this.mBindData, (x) this.mPresenter);
    }

    private void sendExpo() {
        com.jingdong.app.mall.home.r.c.a.A("Home_SeckillExpo", "", this.mBindData.getProductExpoJsonArr().toString(), RecommendMtaUtils.Home_PageId, l.f());
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.SecKillElderEntity.LadySecKillDataChangeCallBack
    public void onChange() {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorSecKillElder.1
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    MallFloorSecKillElder.this.onChange();
                }
            });
        } else {
            bindSecKill();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        if (m.I(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, false)) {
            sendExpo();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        bindSecKill();
        setContentDescription("\u4eac\u4e1c\u79d2\u6740");
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useRoundMarginColor() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public x createPresenter() {
        return new x();
    }
}
