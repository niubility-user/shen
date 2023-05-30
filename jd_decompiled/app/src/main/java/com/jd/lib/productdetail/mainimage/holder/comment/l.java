package com.jd.lib.productdetail.mainimage.holder.comment;

import android.text.TextUtils;
import android.view.View;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.List;

/* loaded from: classes15.dex */
public class l implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ boolean f4777g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdCommentInfo f4778h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f4779i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ PdMImageZcxView f4780j;

    public l(PdMImageZcxView pdMImageZcxView, boolean z, PdCommentInfo pdCommentInfo, int i2) {
        this.f4780j = pdMImageZcxView;
        this.f4777g = z;
        this.f4778h = pdCommentInfo;
        this.f4779i = i2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdCommentInfo.BuyersCommentInfo buyersCommentInfo;
        List<PdCommentInfo.BuyersCommentInfoList> list;
        if (this.f4777g) {
            PdMImageZcxView pdMImageZcxView = this.f4780j;
            PdCommentInfo pdCommentInfo = this.f4778h;
            int i2 = this.f4779i;
            int i3 = PdMImageZcxView.f4756n;
            pdMImageZcxView.getClass();
            if (pdCommentInfo == null || (buyersCommentInfo = pdCommentInfo.buyersCommentInfo) == null || (list = buyersCommentInfo.buyersCommentInfoList) == null || list.size() < 4) {
                return;
            }
            PdCommentInfo.BuyersCommentInfoList buyersCommentInfoList = pdCommentInfo.buyersCommentInfo.buyersCommentInfoList.get(i2);
            if (buyersCommentInfoList != null && !TextUtils.isEmpty(buyersCommentInfoList.ugcDetailOpenApp)) {
                OpenAppUtils.openAppForInner(pdMImageZcxView.getContext(), buyersCommentInfoList.ugcDetailOpenApp);
            } else {
                OpenAppUtils.openAppForInner(pdMImageZcxView.getContext(), pdCommentInfo.buyersShowInfo);
            }
            PdMainImagePresenter pdMainImagePresenter = pdMImageZcxView.f4758h;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
            }
        }
    }
}
