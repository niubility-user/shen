package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.listener.ICouponListener;
import com.jingdong.app.mall.bundle.dolphinlib.common.presenter.DolphinCouponInteractor;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.ToastUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.CouponEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponResponseEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinCouponAdapter extends BaseAdapter implements ICouponListener {
    private static final int COUPON_STATE_1 = 1;
    private static final int COUPON_STATE_2 = 2;
    private static final int COUPON_STATE_3 = 3;
    private BabelScope mBabelScope;
    private DolphinCouponFloorConfig mConfig;
    private Context mContext;
    private List<CouponEntity> mDatas = new ArrayList();
    private DolphinCouponInteractor mInteractor;
    private DolphinMtaEntity mMtaEntity;

    /* loaded from: classes19.dex */
    public class CouponViewHolder {
        ImageWraper mBg;
        TextView mCondition;
        TextView mCouponMoney;
        TextView mDesc;
        TextView mGetButton;
        ImageWraper mGoodsImage;

        public CouponViewHolder() {
        }
    }

    public DolphinCouponAdapter(Context context) {
        this.mContext = context;
        this.mInteractor = new DolphinCouponInteractor(context, this);
    }

    private void setBg(ImageWraper imageWraper, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            CommonServiceUtil.displayImage(str, imageWraper);
        } else if (TextUtils.isEmpty(str2)) {
        } else {
            float dip2px = DPIUtil.dip2px(this.mContext, 8.0f);
            float[] fArr = {dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px};
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(fArr);
            gradientDrawable.setColor(ColorUtil.parseColor(this.mConfig.couponBgColor, -1));
            imageWraper.setBackgroundDrawable(gradientDrawable);
        }
    }

    private void setGetBtn(TextView textView, final CouponEntity couponEntity, final int i2) {
        String str;
        String str2;
        String str3;
        int i3 = couponEntity.couponState;
        if (i3 == 1 || i3 == 2) {
            str = i3 == 2 ? "\u53bb\u4f7f\u7528" : "\u7acb\u5373\u9886\u53d6";
            DolphinCouponFloorConfig dolphinCouponFloorConfig = this.mConfig;
            String str4 = dolphinCouponFloorConfig.btnTextColor;
            str2 = dolphinCouponFloorConfig.btnBgColor;
            str3 = str4;
        } else {
            str = "\u5df2\u62a2\u5149";
            str3 = "#F2F2F2";
            str2 = JDDarkUtil.COLOR_CCCCCC;
        }
        textView.setText(str);
        textView.setTextColor(ColorUtil.parseColor(str3, -1));
        float dip2px = DPIUtil.dip2px(this.mContext, 10.0f);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px});
        gradientDrawable.setColor(ColorUtil.parseColor(str2, -1));
        textView.setBackgroundDrawable(gradientDrawable);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinCouponAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("asdf", "\u70b9\u51fb\u7b2c" + i2 + "\u4e2a\u4f18\u60e0\u5238");
                if (!CommonServiceUtil.hasLogin()) {
                    LogUtil.d("asdf", "\u672a\u767b\u5f55\uff01");
                    CommonServiceUtil.executeLogin(DolphinCouponAdapter.this.mContext, null);
                    return;
                }
                int i4 = couponEntity.couponState;
                if (i4 == 1) {
                    DolphinCouponInteractor dolphinCouponInteractor = DolphinCouponAdapter.this.mInteractor;
                    CouponEntity couponEntity2 = couponEntity;
                    dolphinCouponInteractor.getData(couponEntity2.key, couponEntity2.roleId, couponEntity2.batchId, i2);
                } else if (i4 == 2) {
                    CommonServiceUtil.execJump(DolphinCouponAdapter.this.mContext, couponEntity.jump);
                }
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<CouponEntity> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<CouponEntity> getDatas() {
        return this.mDatas;
    }

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.listener.ICouponListener
    public void getFail(String str) {
        LogUtil.d("asdf", "\u9886\u53d6\u5931\u8d25");
        ToastUtil.showToast(this.mContext, str);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<CouponEntity> list = this.mDatas;
        return (list == null || i2 < 0 || i2 > list.size() + (-1)) ? "" : this.mDatas.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.listener.ICouponListener
    public void getSuccess(Object obj) {
        LogUtil.d("asdf", "\u9886\u53d6\u6210\u529f");
        if (obj instanceof DolphinCouponResponseEntity) {
            DolphinCouponResponseEntity dolphinCouponResponseEntity = (DolphinCouponResponseEntity) obj;
            ToastUtil.showToast(this.mContext, TextUtils.isEmpty(dolphinCouponResponseEntity.msg) ? "\u9886\u53d6\u6210\u529f" : dolphinCouponResponseEntity.msg);
            this.mDatas.get(dolphinCouponResponseEntity.position).couponState = 2;
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        CouponViewHolder couponViewHolder;
        DolphinCouponFloorConfig dolphinCouponFloorConfig;
        if (view != null && (view.getTag() instanceof CouponViewHolder)) {
            couponViewHolder = (CouponViewHolder) view.getTag();
        } else {
            CouponViewHolder couponViewHolder2 = new CouponViewHolder();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_coupon_item, viewGroup, false);
            couponViewHolder2.mBg = (ImageWraper) inflate.findViewById(R.id.image_bg);
            couponViewHolder2.mGoodsImage = (ImageWraper) inflate.findViewById(R.id.goods_image);
            couponViewHolder2.mCouponMoney = (TextView) inflate.findViewById(R.id.coupon_money);
            couponViewHolder2.mCondition = (TextView) inflate.findViewById(R.id.coupon_condition);
            couponViewHolder2.mDesc = (TextView) inflate.findViewById(R.id.coupon_desc);
            couponViewHolder2.mGetButton = (TextView) inflate.findViewById(R.id.get_button);
            FontUtil.changeTextFont(couponViewHolder2.mCouponMoney, 4099);
            FontUtil.changeTextFont(couponViewHolder2.mCondition, 4099);
            FontUtil.changeTextFont(couponViewHolder2.mDesc, 4099);
            FontUtil.changeTextFont(couponViewHolder2.mGetButton, 4099);
            inflate.setTag(couponViewHolder2);
            couponViewHolder = couponViewHolder2;
            view = inflate;
        }
        CouponEntity couponEntity = this.mDatas.get(i2);
        if (couponEntity != null && (dolphinCouponFloorConfig = this.mConfig) != null) {
            setBg(couponViewHolder.mBg, dolphinCouponFloorConfig.couponBgImage, dolphinCouponFloorConfig.couponBgColor);
            CommonServiceUtil.displayImage(couponEntity.goodsImage, couponViewHolder.mGoodsImage);
            if (!TextUtils.isEmpty(couponEntity.money)) {
                if (couponEntity.money.contains("\u6298")) {
                    PriceUtil.setSpanViewDiscount(couponViewHolder.mCouponMoney, couponEntity.money);
                } else {
                    PriceUtil.setSpanViewPrice(couponViewHolder.mCouponMoney, couponEntity.money);
                }
                couponViewHolder.mCouponMoney.setTextColor(ColorUtil.parseColor(this.mConfig.themeTextColor, -1));
                couponViewHolder.mCouponMoney.setVisibility(0);
            } else {
                couponViewHolder.mCouponMoney.setVisibility(4);
            }
            if (!TextUtils.isEmpty(couponEntity.useDiscount)) {
                couponViewHolder.mCondition.setText(couponEntity.useDiscount);
                couponViewHolder.mCondition.setTextColor(ColorUtil.parseColor(this.mConfig.themeTextColor, -1));
                couponViewHolder.mCondition.setVisibility(0);
            } else {
                couponViewHolder.mCondition.setVisibility(4);
            }
            if (!TextUtils.isEmpty(couponEntity.useDescription)) {
                couponViewHolder.mDesc.setText(couponEntity.useDescription);
                couponViewHolder.mDesc.setTextColor(ColorUtil.parseColor(this.mConfig.descTextColor, -1));
                couponViewHolder.mDesc.setVisibility(0);
            } else {
                couponViewHolder.mDesc.setVisibility(4);
            }
            setGetBtn(couponViewHolder.mGetButton, couponEntity, i2);
        }
        return view;
    }

    public void setDatas(List<CouponEntity> list, DolphinCouponFloorConfig dolphinCouponFloorConfig) {
        this.mDatas = list;
        this.mConfig = dolphinCouponFloorConfig;
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }
}
