package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMImageCommentNewRootView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    public PdMImageCommentView f4719g;

    /* renamed from: h  reason: collision with root package name */
    public PdMImageCommentZcxView f4720h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4721i;

    public PdMImageCommentNewRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x023a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo r18, java.lang.String r19, boolean r20, java.lang.String r21, int r22) {
        /*
            Method dump skipped, instructions count: 1514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.holder.comment.PdMImageCommentNewRootView.a(com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo, java.lang.String, boolean, java.lang.String, int):void");
    }

    public void b(boolean z, int i2) {
        PdMImageCommentView pdMImageCommentView = this.f4719g;
        pdMImageCommentView.s = z;
        if (i2 == 2) {
            if (z) {
                pdMImageCommentView.p.setVisibility(0);
            } else {
                pdMImageCommentView.p.setVisibility(8);
            }
            View view = pdMImageCommentView.r;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = pdMImageCommentView.f4743n;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            if (!z) {
                View view3 = pdMImageCommentView.f4743n;
                if (view3 != null) {
                    view3.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView = pdMImageCommentView.f4739j;
                if (pdMImageCommentItemView != null) {
                    pdMImageCommentItemView.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView2 = pdMImageCommentView.f4740k;
                if (pdMImageCommentItemView2 != null) {
                    pdMImageCommentItemView2.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView3 = pdMImageCommentView.f4741l;
                if (pdMImageCommentItemView3 != null) {
                    pdMImageCommentItemView3.setClickable(false);
                }
            }
        } else {
            View view4 = pdMImageCommentView.r;
            if (view4 != null) {
                view4.setVisibility(z ? 0 : 8);
                pdMImageCommentView.f4743n.setVisibility(pdMImageCommentView.s ? 0 : 8);
            }
            if (!z) {
                View view5 = pdMImageCommentView.f4743n;
                if (view5 != null) {
                    view5.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView4 = pdMImageCommentView.f4739j;
                if (pdMImageCommentItemView4 != null) {
                    pdMImageCommentItemView4.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView5 = pdMImageCommentView.f4740k;
                if (pdMImageCommentItemView5 != null) {
                    pdMImageCommentItemView5.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView6 = pdMImageCommentView.f4741l;
                if (pdMImageCommentItemView6 != null) {
                    pdMImageCommentItemView6.setClickable(false);
                }
            }
            pdMImageCommentView.p.setVisibility(8);
        }
        PdMImageCommentZcxView pdMImageCommentZcxView = this.f4720h;
        pdMImageCommentZcxView.r = z;
        pdMImageCommentZcxView.c(i2 == 2);
    }

    public final boolean c(PdCommentItemInfo pdCommentItemInfo) {
        return (pdCommentItemInfo == null || TextUtils.isEmpty(pdCommentItemInfo.commentScore) || PDUtils.stringToFloat(pdCommentItemInfo.commentScore) <= 3.0f || TextUtils.isEmpty(pdCommentItemInfo.commentData)) ? false : true;
    }

    public void d(PdMainImagePresenter pdMainImagePresenter) {
        PdMImageCommentZcxView pdMImageCommentZcxView = this.f4720h;
        if (pdMImageCommentZcxView != null) {
            pdMImageCommentZcxView.s(pdMainImagePresenter);
        }
        PdMImageCommentView pdMImageCommentView = this.f4719g;
        if (pdMImageCommentView != null) {
            pdMImageCommentView.k(pdMainImagePresenter);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4719g = (PdMImageCommentView) findViewById(R.id.lib_pd_holder_topimage_item_comment);
        this.f4720h = (PdMImageCommentZcxView) findViewById(R.id.lib_pd_holder_topimage_item_comment_zcx);
    }
}
