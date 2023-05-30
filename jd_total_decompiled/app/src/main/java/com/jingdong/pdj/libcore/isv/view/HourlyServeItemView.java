package com.jingdong.pdj.libcore.isv.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.ui.PdAutoChangeTextSize;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.widget.PDFlowLayout;
import com.jingdong.pdj.libcore.R;
import com.jingdong.pdj.libcore.isv.entity.AttrInfos;
import com.jingdong.pdj.libcore.isv.entity.ServeAttributeValues;
import com.jingdong.pdj.libcore.isv.view.HourlyGoServeView;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010(\u001a\u00020\u001b\u00a2\u0006\u0004\b)\u0010*J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ#\u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00178\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010!\u00a8\u0006+"}, d2 = {"Lcom/jingdong/pdj/libcore/isv/view/HourlyServeItemView;", "Landroid/widget/LinearLayout;", "", "isDark", "", "changeDarkUI", "(Z)V", "Lcom/jingdong/pdj/libcore/isv/entity/AttrInfos;", "attrInfos", "bindData", "(Lcom/jingdong/pdj/libcore/isv/entity/AttrInfos;)V", "", "pos", "", "Lcom/jingdong/pdj/libcore/isv/entity/ServeAttributeValues;", ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, "invertSelection", "(ILjava/util/List;)V", "", "name", "Lcom/jingdong/common/ui/PdAutoChangeTextSize;", "getItemView", "(ILjava/lang/String;)Lcom/jingdong/common/ui/PdAutoChangeTextSize;", "Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setHourlyItemClickListener", "(Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;)V", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "mListener", "Lcom/jingdong/pdj/libcore/isv/view/HourlyGoServeView$HourlyItemClickListener;", "mTitleView", "Lcom/jingdong/common/ui/PdAutoChangeTextSize;", "Lcom/jingdong/common/widget/PDFlowLayout;", "mPDFlowLayout", "Lcom/jingdong/common/widget/PDFlowLayout;", "mTitleViewGroup", "Landroid/widget/LinearLayout;", "mTitleTipsView", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyServeItemView extends LinearLayout {
    private final Context mContext;
    private HourlyGoServeView.HourlyItemClickListener mListener;
    private final PDFlowLayout mPDFlowLayout;
    private final PdAutoChangeTextSize mTitleTipsView;
    private final PdAutoChangeTextSize mTitleView;
    private final LinearLayout mTitleViewGroup;

    public HourlyServeItemView(@NotNull Context context) {
        super(context);
        this.mContext = context;
        setOrientation(1);
        setPadding(DPIUtil.dip2px(18.0f), 0, DPIUtil.dip2px(18.0f), 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = DPIUtil.dip2px(12.0f);
        setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mTitleViewGroup = linearLayout;
        addView(linearLayout);
        PdAutoChangeTextSize pdAutoChangeTextSize = new PdAutoChangeTextSize(context);
        TextPaint paint = pdAutoChangeTextSize.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
        paint.setFakeBoldText(true);
        pdAutoChangeTextSize.setIncludeFontPadding(false);
        pdAutoChangeTextSize.setMaxLines(1);
        pdAutoChangeTextSize.setEllipsize(TextUtils.TruncateAt.END);
        pdAutoChangeTextSize.setTextSize(2, 13.0f);
        pdAutoChangeTextSize.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
        pdAutoChangeTextSize.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.mTitleView = pdAutoChangeTextSize;
        linearLayout.addView(pdAutoChangeTextSize);
        PdAutoChangeTextSize pdAutoChangeTextSize2 = new PdAutoChangeTextSize(context);
        pdAutoChangeTextSize2.setMaxLines(1);
        pdAutoChangeTextSize2.setEllipsize(TextUtils.TruncateAt.END);
        pdAutoChangeTextSize2.setIncludeFontPadding(false);
        pdAutoChangeTextSize2.setVisibility(8);
        pdAutoChangeTextSize2.setTextSize(2, 12.0f);
        pdAutoChangeTextSize2.setTextColor(Color.parseColor("#f23030"));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = DPIUtil.dip2px(8.0f);
        pdAutoChangeTextSize2.setLayoutParams(layoutParams2);
        this.mTitleTipsView = pdAutoChangeTextSize2;
        linearLayout.addView(pdAutoChangeTextSize2);
        PDFlowLayout pDFlowLayout = new PDFlowLayout(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = DPIUtil.dip2px(12.0f);
        pDFlowLayout.setLayoutParams(layoutParams3);
        this.mPDFlowLayout = pDFlowLayout;
        addView(pDFlowLayout);
    }

    public static final /* synthetic */ HourlyGoServeView.HourlyItemClickListener access$getMListener$p(HourlyServeItemView hourlyServeItemView) {
        HourlyGoServeView.HourlyItemClickListener hourlyItemClickListener = hourlyServeItemView.mListener;
        if (hourlyItemClickListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mListener");
        }
        return hourlyItemClickListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void bindData(@NotNull final AttrInfos attrInfos) {
        boolean z;
        boolean isBlank;
        Context context = this.mContext;
        if (context instanceof Activity) {
            this.mPDFlowLayout.setActivity((Activity) context);
        }
        if (this.mPDFlowLayout.getChildCount() > 0) {
            this.mPDFlowLayout.removeAllViews();
        }
        this.mPDFlowLayout.setSpace(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
        String str = attrInfos.attName;
        if (!(str == null || str.length() == 0)) {
            this.mTitleView.setText(attrInfos.attName);
            if (!attrInfos.havaSelectService()) {
                this.mTitleTipsView.setVisibility(0);
                this.mTitleTipsView.setText("\u8bf7\u9009\u62e9" + attrInfos.attName);
            }
        }
        List<ServeAttributeValues> list = attrInfos.attValueList;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = attrInfos.attValueList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ServeAttributeValues serveAttributeValues = attrInfos.attValueList.get(i2);
            if (serveAttributeValues != null) {
                String str2 = serveAttributeValues.attValName;
                if (str2 != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(str2);
                    if (!isBlank) {
                        z = false;
                        if (z) {
                            String str3 = serveAttributeValues.attValName;
                            Intrinsics.checkExpressionValueIsNotNull(str3, "serveAttributeValues.attValName");
                            final PdAutoChangeTextSize itemView = getItemView(i2, str3);
                            itemView.setSelected(serveAttributeValues.isSelect);
                            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.pdj.libcore.isv.view.HourlyServeItemView$bindData$1
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    Object tag = itemView.getTag();
                                    if (tag != null) {
                                        int intValue = ((Integer) tag).intValue();
                                        HourlyServeItemView hourlyServeItemView = HourlyServeItemView.this;
                                        List<ServeAttributeValues> list2 = attrInfos.attValueList;
                                        Intrinsics.checkExpressionValueIsNotNull(list2, "attrInfos.attValueList");
                                        hourlyServeItemView.invertSelection(intValue, list2);
                                        HourlyGoServeView.HourlyItemClickListener access$getMListener$p = HourlyServeItemView.access$getMListener$p(HourlyServeItemView.this);
                                        ServeAttributeValues serveAttributeValues2 = attrInfos.attValueList.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(serveAttributeValues2, "attrInfos.attValueList.get(pos)");
                                        access$getMListener$p.onClickItem(serveAttributeValues2);
                                        return;
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                                }
                            });
                            this.mPDFlowLayout.addView(itemView, new LinearLayout.LayoutParams(-2, DPIUtil.dip2px(30.0f)));
                        }
                    }
                }
                z = true;
                if (z) {
                }
            }
        }
    }

    public final void changeDarkUI(boolean isDark) {
        this.mTitleView.setTextColor(Color.parseColor(isDark ? "#ececec" : "#232326"));
        int i2 = isDark ? R.color.lib_hourly_core_style_text_dark_selector : R.color.lib_hourly_core_style_text_selector;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        ColorStateList colorStateList = context.getResources().getColorStateList(i2);
        Intrinsics.checkExpressionValueIsNotNull(colorStateList, "context.resources.getColorStateList(contentColor)");
        int i3 = isDark ? com.jingdong.common.R.drawable.lib_pd_style_button_new_dark : com.jingdong.common.R.drawable.lib_pd_style_button_new;
        if (this.mPDFlowLayout.getChildCount() > 0) {
            int childCount = this.mPDFlowLayout.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = this.mPDFlowLayout.getChildAt(i4);
                if (childAt instanceof PdAutoChangeTextSize) {
                    childAt.setBackgroundResource(i3);
                    ((PdAutoChangeTextSize) childAt).setTextColor(colorStateList);
                }
            }
        }
    }

    @NotNull
    public final PdAutoChangeTextSize getItemView(int pos, @NotNull String name) {
        PdAutoChangeTextSize pdAutoChangeTextSize = new PdAutoChangeTextSize(getContext());
        pdAutoChangeTextSize.setPadding(DPIUtil.dip2px(16.0f), 0, DPIUtil.dip2px(16.0f), 0);
        pdAutoChangeTextSize.setIncludeFontPadding(false);
        pdAutoChangeTextSize.setMaxLines(1);
        pdAutoChangeTextSize.setText(name);
        pdAutoChangeTextSize.setTag(Integer.valueOf(pos));
        pdAutoChangeTextSize.setGravity(17);
        pdAutoChangeTextSize.setEllipsize(TextUtils.TruncateAt.END);
        pdAutoChangeTextSize.setTextSize(2, 13.0f);
        return pdAutoChangeTextSize;
    }

    public final void invertSelection(int pos, @NotNull List<? extends ServeAttributeValues> list) {
        if (list.size() != this.mPDFlowLayout.getChildCount()) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ServeAttributeValues serveAttributeValues = list.get(i2);
            View itemView = this.mPDFlowLayout.getChildAt(i2);
            if (pos == i2) {
                serveAttributeValues.isSelect = !serveAttributeValues.isSelect;
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                itemView.setSelected(serveAttributeValues.isSelect);
                if (serveAttributeValues.isSelect) {
                    this.mTitleTipsView.setVisibility(8);
                } else {
                    this.mTitleTipsView.setVisibility(0);
                }
            } else if (serveAttributeValues.isSelect) {
                serveAttributeValues.isSelect = false;
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                itemView.setSelected(serveAttributeValues.isSelect);
            }
        }
    }

    public final void setHourlyItemClickListener(@NotNull HourlyGoServeView.HourlyItemClickListener listener) {
        this.mListener = listener;
    }
}
