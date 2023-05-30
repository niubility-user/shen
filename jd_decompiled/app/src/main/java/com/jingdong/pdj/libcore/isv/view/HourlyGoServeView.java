package com.jingdong.pdj.libcore.isv.view;

import android.content.Context;
import android.widget.LinearLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.pdj.libcore.isv.entity.AttrInfos;
import com.jingdong.pdj.libcore.isv.entity.ProcessServeAttrInfo;
import com.jingdong.pdj.libcore.isv.entity.ServeAttributeValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView;", "Landroid/widget/LinearLayout;", "", CustomThemeConstance.NAVI_IMAGE_DARK_TAG, "", "changeDarkUI", "(Z)V", "Lcom/jingdong/pdj/libcore/isv/entity/ProcessServeAttrInfo;", "processServeAttrInfo", "setData", "(Lcom/jingdong/pdj/libcore/isv/entity/ProcessServeAttrInfo;)V", "Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setHourlyItemClickListener", "(Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;)V", "mIsDark", "Z", "Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;", "", "mViewType", "I", "Landroid/content/Context;", AnnoConst.Constructor_Context, "viewType", "<init>", "(Landroid/content/Context;I)V", "HourlyItemClickListener", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyGoServeView extends LinearLayout {
    private HourlyItemClickListener listener;
    private boolean mIsDark;
    private final int mViewType;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;", "", "Lcom/jingdong/pdj/libcore/isv/entity/ServeAttributeValues;", "serveAttributeValues", "", "onClickItem", "(Lcom/jingdong/pdj/libcore/isv/entity/ServeAttributeValues;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public interface HourlyItemClickListener {
        void onClickItem(@NotNull ServeAttributeValues serveAttributeValues);
    }

    public HourlyGoServeView(@NotNull Context context, int i2) {
        super(context);
        setOrientation(1);
        this.mViewType = i2;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    }

    public final void changeDarkUI(boolean r1) {
        this.mIsDark = r1;
    }

    public final void setData(@NotNull ProcessServeAttrInfo processServeAttrInfo) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        List<AttrInfos> list = processServeAttrInfo.attrInfos;
        if (list != null) {
            Intrinsics.checkExpressionValueIsNotNull(list, "processServeAttrInfo.attrInfos");
            if ((!list.isEmpty()) == true) {
                int size = processServeAttrInfo.attrInfos.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    HourlyServeItemView hourlyServeItemView = new HourlyServeItemView(context);
                    AttrInfos attrInfos = processServeAttrInfo.attrInfos.get(i2);
                    Intrinsics.checkExpressionValueIsNotNull(attrInfos, "processServeAttrInfo.attrInfos[i]");
                    hourlyServeItemView.bindData(attrInfos);
                    hourlyServeItemView.changeDarkUI(this.mIsDark);
                    HourlyItemClickListener hourlyItemClickListener = this.listener;
                    if (hourlyItemClickListener == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(CartCleanConstants.CART_CLEAN_DIALOG_LISTENER);
                    }
                    hourlyServeItemView.setHourlyItemClickListener(hourlyItemClickListener);
                    addView(hourlyServeItemView);
                }
            }
        }
    }

    public final void setHourlyItemClickListener(@NotNull HourlyItemClickListener r1) {
        this.listener = r1;
    }
}
