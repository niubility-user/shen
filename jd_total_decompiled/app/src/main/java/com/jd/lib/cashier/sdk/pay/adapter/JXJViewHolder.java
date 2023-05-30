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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u001e\u0010\u000b\u001a\n \b*\u0004\u0018\u00010\u00070\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/JXJViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "iconUrl", "", "b", "(Ljava/lang/String;)V", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", com.jingdong.jdsdk.a.a.a, "Landroid/widget/ImageView;", "imgIcon", "Landroid/view/View;", "contentView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class JXJViewHolder extends RecyclerView.ViewHolder {
    private static final String b;

    /* renamed from: a  reason: from kotlin metadata */
    private final ImageView imgIcon;

    static {
        String simpleName = JXJViewHolder.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "JXJViewHolder::class.java.simpleName");
        b = simpleName;
    }

    public JXJViewHolder(@NotNull View view) {
        super(view);
        this.imgIcon = (ImageView) view.findViewById(R.id.img_item_jxj_dialog_product);
    }

    public final void b(@NotNull String iconUrl) {
        r.b(b, " iconUrl = " + iconUrl);
        if (TextUtils.isEmpty(iconUrl)) {
            return;
        }
        ImageView imgIcon = this.imgIcon;
        Intrinsics.checkExpressionValueIsNotNull(imgIcon, "imgIcon");
        imgIcon.setVisibility(0);
        k.b(this.imgIcon, iconUrl, null, false);
    }
}
