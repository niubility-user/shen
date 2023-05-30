package com.jd.lib.cashier.sdk.pay.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.r;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R!\u0010\r\u001a\n \b*\u0004\u0018\u00010\u00070\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/GWJProductViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "imgUrl", "", "b", "(Ljava/lang/String;)V", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", com.jingdong.jdsdk.a.a.a, "Landroid/widget/ImageView;", "getGwjImg", "()Landroid/widget/ImageView;", "gwjImg", "Landroid/view/View;", "contentView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class GWJProductViewHolder extends RecyclerView.ViewHolder {
    private static final String b;

    /* renamed from: a  reason: from kotlin metadata */
    private final ImageView gwjImg;

    static {
        String simpleName = GWJProductViewHolder.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "GWJProductViewHolder::class.java.simpleName");
        b = simpleName;
    }

    public GWJProductViewHolder(@NotNull View view) {
        super(view);
        this.gwjImg = (ImageView) view.findViewById(R.id.skuImg);
    }

    public final void b(@NotNull String imgUrl) {
        r.b(b, " imgUrl = " + imgUrl);
        if (!TextUtils.isEmpty(imgUrl)) {
            ImageView imageView = this.gwjImg;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            k.b(this.gwjImg, imgUrl, null, false);
            return;
        }
        ImageView imageView2 = this.gwjImg;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
    }
}
