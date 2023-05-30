package com.jd.lib.productdetail.mainimage.bigimage;

import android.text.TextUtils;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;

/* loaded from: classes15.dex */
public class k implements Observer<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdBigImageActivity.ImageFragment f4612g;

    public k(PdBigImageActivity.ImageFragment imageFragment) {
        this.f4612g = imageFragment;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result) {
        PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo;
        PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result2 = result;
        if (result2 == null || (pdPreferentialRecommendProductListInfo = result2.mData) == null || !TextUtils.equals(pdPreferentialRecommendProductListInfo.from, "4")) {
            return;
        }
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
        if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
            PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo2 = result2.mData;
            if (pdPreferentialRecommendProductListInfo2 instanceof PdPreferentialRecommendProductListInfo) {
                PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo3 = pdPreferentialRecommendProductListInfo2;
                PdBigImageActivity.ImageFragment imageFragment = this.f4612g;
                if (imageFragment.f4562k == imageFragment.S) {
                    imageFragment.G = pdPreferentialRecommendProductListInfo3;
                }
            }
            this.f4612g.h();
            PdBigImageActivity.Q.mRecommendContainer.a.removeObserver(this);
        } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
            this.f4612g.h();
            PdBigImageActivity.Q.mRecommendContainer.a.removeObserver(this);
        }
    }
}
