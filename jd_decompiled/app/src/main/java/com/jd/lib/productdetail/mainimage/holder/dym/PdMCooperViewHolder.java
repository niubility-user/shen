package com.jd.lib.productdetail.mainimage.holder.dym;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.platform.floor.isv.FloorCooperateManager;
import com.jingdong.sdk.platform.floor.isv.IBaseView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdMCooperViewHolder extends PdMainImageBaseHolder {
    public IBaseView B;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMCooperViewHolder.this.m(true, false, false);
        }
    }

    public PdMCooperViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    public static void G(JSONObject jSONObject, WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, String str, String str2) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        if (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null) {
            return;
        }
        try {
            jSONObject.put("pd_isv_unique_skuid", extMapEntity.skuId);
            WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity = wareBusinessUnitMainImageEntity.extMap.category;
            if (categoryEntity != null) {
                jSONObject.put("categoryId3", categoryEntity.thdId);
            }
            jSONObject.put("pd_isv_unique_skutag", str);
            jSONObject.put("pd_isv_unique_manange_key", str2);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public final String H() {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        String str = wareBusinessMagicHeadPicInfoEntity != null ? wareBusinessMagicHeadPicInfoEntity.mIsvData : "";
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = this.f4654n.appImageWidth;
            float f2 = i2;
            jSONObject.put("width", PDUtils.px2dp(f2));
            jSONObject.put("isDark", this.f4654n.getMainImageParams().isDark ? "1" : "0");
            jSONObject.put("isBigImage", false);
            jSONObject.put("frameIndex", getAdapterPosition());
            G(jSONObject, this.v, this.f4654n.getMainImageParams().mSkuTag, this.f4654n.getMainImageParams().mManagerKey);
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
            if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && extMapEntity.magicHeadPicType == 1) {
                i2 = (int) ((f2 * 4.0f) / 3.0f);
            }
            jSONObject.put("height", PDUtils.px2dp(i2));
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public String I() {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity == null) {
            return "";
        }
        return wareBusinessMagicHeadPicInfoEntity.iViewType == 2 ? wareBusinessMagicHeadPicInfoEntity.mfStyleId : wareBusinessMagicHeadPicInfoEntity.anchorType;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        IBaseView iBaseView = this.B;
        if (iBaseView != null && iBaseView.getView() != null) {
            Object tag = this.B.getView().getTag();
            if ((tag instanceof String) && TextUtils.equals(tag.toString(), I())) {
                this.B.onBindData(H());
                return;
            }
            View view = this.itemView;
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).removeView(this.B.getView());
            }
        }
        FloorCooperateManager a2 = PdMCooperManager.a(this.f4654n.getMainImageParams().mManagerKey);
        if (a2 != null) {
            View view2 = this.itemView;
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                IBaseView createView = a2.createView(viewGroup, I(), this.r.iViewType == 2, H());
                this.B = createView;
                if (createView != null && createView.getView() != null) {
                    this.B.getView().setTag(I());
                    viewGroup.addView(this.B.getView(), new ViewGroup.LayoutParams(-1, -1));
                }
                this.itemView.setOnClickListener(new a());
            }
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void r() {
        this.f4651k = true;
        IBaseView iBaseView = this.B;
        if (iBaseView != null) {
            iBaseView.onDestroy();
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void z() {
        super.z();
    }
}
