package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerBaseItem;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerCardItem;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerCardLayout;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerOrderItem;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerRedPacketItem;
import com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerSkuItem;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.v;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class MallFloorNewcomer extends BaseMallFloor<v> {
    private LinearLayout mBenefitLayout;
    private JDJSONArray mData;
    private SimpleDraweeView mFloorBg;
    private SimpleDraweeView mIcon;
    private ConcurrentHashMap<String, NewcomerBaseItem> mItemCache;
    private NewcomerFloorEntity.NewcomerOrderModel mOrderModel;
    private NewcomerOrderItem mOrderOne;
    private NewcomerOrderItem mOrderThree;
    private NewcomerOrderItem mOrderTwo;

    public MallFloorNewcomer(Context context) {
        super(context);
        this.mItemCache = new ConcurrentHashMap<>();
        setBackgroundColor(0);
    }

    private void clearFloor() {
        h hVar;
        JDHomeFragment z0 = JDHomeFragment.z0();
        d dVar = this.mFloorBindElement;
        if (dVar == null || z0 == null || (hVar = dVar.mParentModel) == null) {
            return;
        }
        hVar.c0 = false;
        HomeRecyclerAdapter t0 = z0.t0();
        if (t0 != null) {
            t0.q(this.mFloorBindElement);
        }
    }

    private Drawable getDefaultDrawable() {
        float d = com.jingdong.app.mall.home.floor.common.d.d(12);
        float[] fArr = {d, d, d, d, d, d, d, d};
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(IconFloorEntity.BGCOLOR_DEFAULT);
        gradientDrawable.setCornerRadii(fArr);
        return gradientDrawable;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void initBenefitLayout() {
        JDJSONObject jSONObject;
        NewcomerFloorEntity.NewcomerBaseModel newcomerRedPacketModel;
        NewcomerBaseItem newcomerBaseItem;
        f fVar;
        NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel;
        if (!((v) this.mPresenter).R()) {
            c.k(true, this.mBenefitLayout);
            return;
        }
        c.k(false, this.mBenefitLayout);
        f fVar2 = new f(R2.attr.contentDescription, R2.anim.pickerview_dialog_scale_in);
        fVar2.F(new Rect(0, 0, 0, 12));
        LinearLayout linearLayout = this.mBenefitLayout;
        if (linearLayout == null) {
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            this.mBenefitLayout = linearLayout2;
            linearLayout2.setOrientation(0);
            RelativeLayout.LayoutParams u = fVar2.u(this.mBenefitLayout);
            u.addRule(14);
            u.addRule(12);
            addView(this.mBenefitLayout, u);
        } else {
            f.c(linearLayout, fVar2);
        }
        this.mBenefitLayout.removeAllViews();
        for (int i2 = 1; i2 < this.mData.size() && (jSONObject = this.mData.getJSONObject(i2)) != null; i2++) {
            if (this.mItemCache.size() > 6) {
                this.mItemCache.clear();
            }
            int intValue = jSONObject.getIntValue("positionType");
            int intValue2 = jSONObject.getIntValue("moduleSize");
            String concat = String.valueOf(i2).concat(CartConstant.KEY_YB_INFO_LINK).concat(String.valueOf(intValue)).concat(CartConstant.KEY_YB_INFO_LINK).concat(String.valueOf(intValue2));
            NewcomerBaseItem newcomerBaseItem2 = null;
            if (intValue == 0 || intValue == 1) {
                newcomerRedPacketModel = new NewcomerFloorEntity.NewcomerRedPacketModel(jSONObject);
                newcomerBaseItem = this.mItemCache.get(concat);
                if (newcomerBaseItem == null) {
                    newcomerBaseItem = new NewcomerRedPacketItem(this.mContext);
                    this.mItemCache.put(concat, newcomerBaseItem);
                }
                fVar = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            } else if (intValue != 2) {
                int i3 = 334;
                if (intValue == 3) {
                    newcomerRedPacketModel = new NewcomerFloorEntity.NewcomerCardModel(jSONObject);
                    newcomerBaseItem = this.mItemCache.get(concat);
                    if (newcomerBaseItem == null) {
                        newcomerBaseItem = intValue2 == 1 ? new NewcomerCardItem(this.mContext) : new NewcomerCardLayout(this.mContext);
                        this.mItemCache.put(concat, newcomerBaseItem);
                    }
                    if (intValue2 == 1) {
                        i3 = R2.anim.pickerview_dialog_scale_in;
                    } else if (intValue2 != 2) {
                        i3 = 510;
                    }
                    fVar = new f(i3, R2.anim.pickerview_dialog_scale_in);
                } else if (intValue == 4) {
                    newcomerRedPacketModel = new NewcomerFloorEntity.NewcomerRedPacketModel(jSONObject);
                    newcomerBaseItem = this.mItemCache.get(concat);
                    if (newcomerBaseItem == null) {
                        newcomerBaseItem = new NewcomerRedPacketItem(this.mContext);
                        this.mItemCache.put(concat, newcomerBaseItem);
                    }
                    if (intValue2 == 1) {
                        fVar = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
                    } else if (intValue2 == 2) {
                        fVar = new f(334, R2.anim.pickerview_dialog_scale_in);
                    } else {
                        fVar = new f(510, R2.anim.pickerview_dialog_scale_in);
                    }
                } else {
                    newcomerBaseModel = null;
                    fVar = null;
                    if (newcomerBaseItem2 != null) {
                        return;
                    }
                    if (i2 != 1) {
                        fVar.F(new Rect(18, 0, 0, 0));
                    }
                    newcomerBaseItem2.setLayoutParams(fVar.i(newcomerBaseItem2));
                    m.a(this.mBenefitLayout, newcomerBaseItem2);
                    newcomerBaseItem2.a(newcomerBaseModel);
                }
            } else {
                newcomerRedPacketModel = new NewcomerFloorEntity.NewcomerSkuModel(jSONObject);
                newcomerBaseItem = this.mItemCache.get(concat);
                if (newcomerBaseItem == null) {
                    newcomerBaseItem = new NewcomerSkuItem(this.mContext);
                    this.mItemCache.put(concat, newcomerBaseItem);
                }
                fVar = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
            }
            NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel2 = newcomerRedPacketModel;
            newcomerBaseItem2 = newcomerBaseItem;
            newcomerBaseModel = newcomerBaseModel2;
            if (newcomerBaseItem2 != null) {
            }
        }
    }

    private void initFloorBg() {
        NewcomerFloorEntity.NewcomerOrderModel newcomerOrderModel = this.mOrderModel;
        if (newcomerOrderModel == null) {
            return;
        }
        String str = newcomerOrderModel.img;
        f fVar = new f(-1, -1);
        SimpleDraweeView simpleDraweeView = this.mFloorBg;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mFloorBg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView2 = this.mFloorBg;
            addView(simpleDraweeView2, 0, fVar.u(simpleDraweeView2));
        } else {
            f.d(simpleDraweeView, fVar, true);
        }
        e.m(this.mFloorBg, str, getDefaultDrawable());
    }

    private void initIcon() {
        if (!((v) this.mPresenter).R()) {
            c.k(true, this.mIcon);
            return;
        }
        c.k(false, this.mIcon);
        NewcomerFloorEntity.NewcomerOrderModel newcomerOrderModel = this.mOrderModel;
        if (newcomerOrderModel == null) {
            return;
        }
        String str = newcomerOrderModel.iconImg;
        f fVar = new f(36, 36);
        fVar.F(new Rect(0, 122, 8, 0));
        SimpleDraweeView simpleDraweeView = this.mIcon;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mIcon = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.mIcon);
            u.addRule(11);
            addView(this.mIcon, u);
        } else {
            f.d(simpleDraweeView, fVar, true);
        }
        e.u(this.mIcon, str);
        this.mIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorNewcomer.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpEntity jumpEntity;
                if (MallFloorNewcomer.this.mOrderModel == null || l.k() || (jumpEntity = MallFloorNewcomer.this.mOrderModel.iconJumpUrl) == null) {
                    return;
                }
                l.e(((BaseMallColorFloor) MallFloorNewcomer.this).mContext, jumpEntity);
            }
        });
    }

    private void initOrderLayout() {
        if (this.mOrderModel == null) {
            return;
        }
        f fVar = new f(162, 92);
        fVar.F(new Rect(12, 12, 0, 0));
        NewcomerOrderItem newcomerOrderItem = this.mOrderOne;
        if (newcomerOrderItem == null) {
            NewcomerOrderItem newcomerOrderItem2 = new NewcomerOrderItem(this.mContext);
            this.mOrderOne = newcomerOrderItem2;
            addView(newcomerOrderItem2, fVar.u(newcomerOrderItem2));
        } else {
            f.c(newcomerOrderItem, fVar);
        }
        NewcomerOrderItem newcomerOrderItem3 = this.mOrderOne;
        NewcomerFloorEntity.NewcomerOrderModel newcomerOrderModel = this.mOrderModel;
        newcomerOrderItem3.bindData(newcomerOrderModel.info0, newcomerOrderModel.infoText0, "", newcomerOrderModel.fontColor);
        f fVar2 = new f(162, 92);
        fVar2.F(new Rect(274, 12, 0, 0));
        NewcomerOrderItem newcomerOrderItem4 = this.mOrderTwo;
        if (newcomerOrderItem4 == null) {
            NewcomerOrderItem newcomerOrderItem5 = new NewcomerOrderItem(this.mContext);
            this.mOrderTwo = newcomerOrderItem5;
            addView(newcomerOrderItem5, fVar2.u(newcomerOrderItem5));
        } else {
            f.c(newcomerOrderItem4, fVar2);
        }
        NewcomerOrderItem newcomerOrderItem6 = this.mOrderTwo;
        NewcomerFloorEntity.NewcomerOrderModel newcomerOrderModel2 = this.mOrderModel;
        newcomerOrderItem6.bindData(newcomerOrderModel2.info1, newcomerOrderModel2.infoText1, "", newcomerOrderModel2.fontColor);
        f fVar3 = new f(162, 92);
        fVar3.F(new Rect(R2.attr.cardBackgroundColor, 12, 0, 0));
        NewcomerOrderItem newcomerOrderItem7 = this.mOrderThree;
        if (newcomerOrderItem7 == null) {
            NewcomerOrderItem newcomerOrderItem8 = new NewcomerOrderItem(this.mContext);
            this.mOrderThree = newcomerOrderItem8;
            addView(newcomerOrderItem8, fVar3.u(newcomerOrderItem8));
        } else {
            f.c(newcomerOrderItem7, fVar3);
        }
        NewcomerOrderItem newcomerOrderItem9 = this.mOrderThree;
        NewcomerFloorEntity.NewcomerOrderModel newcomerOrderModel3 = this.mOrderModel;
        newcomerOrderItem9.bindData(newcomerOrderModel3.info2, newcomerOrderModel3.infoText2, "", newcomerOrderModel3.fontColor);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        if (isFloorDisplay()) {
            ((v) this.mPresenter).V();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        if (!((v) this.mPresenter).S()) {
            clearFloor();
            return;
        }
        JDJSONArray P = ((v) this.mPresenter).P();
        this.mData = P;
        if (P == null) {
            return;
        }
        this.mOrderModel = new NewcomerFloorEntity.NewcomerOrderModel(P.getJSONObject(0));
        initFloorBg();
        initOrderLayout();
        initIcon();
        initBenefitLayout();
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorNewcomer.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewcomerFloorEntity.onItemClick(((BaseMallColorFloor) MallFloorNewcomer.this).mContext, MallFloorNewcomer.this.mOrderModel);
            }
        });
        notifyLayoutParamsChange();
        if (((v) this.mPresenter).j()) {
            return;
        }
        ((v) this.mPresenter).V();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public v createPresenter() {
        return new v();
    }
}
