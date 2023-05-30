package com.jd.lib.productdetail.mainimage.adapter;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.holder.video.PdMVideoViewHolder;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMainImageAdapter extends RecyclerView.Adapter<PdMainImageBaseHolder> {
    public PdMainImagePresenter a;
    public WareBusinessUnitMainImageEntity b;

    /* renamed from: c  reason: collision with root package name */
    public List<WareBusinessMagicHeadPicInfoEntity> f4548c;
    public Context d;

    /* renamed from: e  reason: collision with root package name */
    public PdMVideoViewHolder f4549e;

    public PdMainImageAdapter(PdMainImagePresenter pdMainImagePresenter) {
        this.a = pdMainImagePresenter;
    }

    public PdMainImagePagerEntity a(int i2) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.b;
        if (wareBusinessUnitMainImageEntity == null || wareBusinessUnitMainImageEntity.extMap == null || (list = this.f4548c) == null || i2 <= -1 || i2 >= list.size() || (wareBusinessMagicHeadPicInfoEntity = this.f4548c.get(i2)) == null) {
            return null;
        }
        PdMainImagePagerEntity pdMainImagePagerEntity = new PdMainImagePagerEntity();
        pdMainImagePagerEntity.position = i2;
        WareBusinessWareImageEntity wareBusinessWareImageEntity = wareBusinessMagicHeadPicInfoEntity.wareImage;
        if (wareBusinessWareImageEntity != null) {
            pdMainImagePagerEntity.imageUrl = wareBusinessWareImageEntity.small;
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.b;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity2.extMap;
        pdMainImagePagerEntity.skuId = extMapEntity.skuId;
        pdMainImagePagerEntity.magicHeadPicData = wareBusinessMagicHeadPicInfoEntity;
        pdMainImagePagerEntity.mIsDefault = extMapEntity.mIsDefault;
        pdMainImagePagerEntity.topImageEntity = wareBusinessUnitMainImageEntity2;
        return pdMainImagePagerEntity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WareBusinessMagicHeadPicInfoEntity> list = this.f4548c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (this.f4548c.get(i2).iViewType > 0) {
            return -821837990;
        }
        if (TextUtils.isEmpty(this.f4548c.get(i2).anchorType)) {
            return -2026296098;
        }
        return this.f4548c.get(i2).anchorType.hashCode();
    }

    public void h(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, Context context) {
        PdMainImagePresenter pdMainImagePresenter;
        k kVar;
        PdMainImagePresenter pdMainImagePresenter2;
        this.d = context;
        PdMVideoViewHolder pdMVideoViewHolder = this.f4549e;
        if (pdMVideoViewHolder != null && (pdMainImagePresenter = this.a) != null && pdMainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI && (kVar = pdMVideoViewHolder.E) != null && (pdMainImagePresenter2 = pdMVideoViewHolder.f4654n) != null) {
            kVar.n(pdMainImagePresenter2.getMainImageParams().mManagerKey);
            pdMVideoViewHolder.E = null;
        }
        this.b = wareBusinessUnitMainImageEntity;
        this.f4548c = wareBusinessUnitMainImageEntity.magicHeadPicInfo;
        notifyDataSetChanged();
        this.a.changeSku(context);
    }

    public boolean l(int i2) {
        return i2 == 3001893 || i2 == 93043258 || i2 == 3001922 || i2 == 3001942 || i2 == 3001944 || i2 == 112439551 || i2 == 49203567 || i2 == -1410592619 || i2 == 93064423;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull PdMainImageBaseHolder pdMainImageBaseHolder, int i2) {
        try {
            pdMainImageBaseHolder.i(a(i2));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x016f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder onCreateViewHolder(@androidx.annotation.NonNull android.view.ViewGroup r6, int r7) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.adapter.PdMainImageAdapter.onCreateViewHolder(android.view.ViewGroup, int):androidx.recyclerview.widget.RecyclerView$ViewHolder");
    }
}
