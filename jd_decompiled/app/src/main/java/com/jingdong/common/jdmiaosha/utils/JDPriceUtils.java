package com.jingdong.common.jdmiaosha.utils;

import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.view.JDPriceTextView;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/common/jdmiaosha/utils/JDPriceUtils;", "", "", "str", "", "isSupportNegative", "", "formatStringToDouble", "(Ljava/lang/String;Z)D", "Lcom/jingdong/common/jdmiaosha/view/JDPriceTextView;", "tvMiaoShaPrice", "tvJdPrice", "miaoShaPrice", JshopConst.JSKEY_PRODUCT_JDPRICE, "", "handlePricesCompare", "(Lcom/jingdong/common/jdmiaosha/view/JDPriceTextView;Lcom/jingdong/common/jdmiaosha/view/JDPriceTextView;Ljava/lang/String;Ljava/lang/String;)V", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class JDPriceUtils {
    public static final JDPriceUtils INSTANCE = new JDPriceUtils();

    private JDPriceUtils() {
    }

    private final double formatStringToDouble(String str, boolean isSupportNegative) {
        if (!TextUtils.isEmpty(str)) {
            if (str == null) {
                try {
                    Intrinsics.throwNpe();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Double doub = Double.valueOf(str);
            if (!isSupportNegative) {
                if (Double.compare(doub.doubleValue(), 0) > 0) {
                    Intrinsics.checkExpressionValueIsNotNull(doub, "doub");
                    return doub.doubleValue();
                }
            } else {
                Intrinsics.checkExpressionValueIsNotNull(doub, "doub");
                return doub.doubleValue();
            }
        }
        return 0.0d;
    }

    static /* synthetic */ double formatStringToDouble$default(JDPriceUtils jDPriceUtils, String str, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return jDPriceUtils.formatStringToDouble(str, z);
    }

    public final void handlePricesCompare(@NotNull JDPriceTextView tvMiaoShaPrice, @NotNull JDPriceTextView tvJdPrice, @NotNull String miaoShaPrice, @NotNull String jdPrice) {
        tvMiaoShaPrice.setText(miaoShaPrice);
        if (!TextUtils.isEmpty(jdPrice) && formatStringToDouble$default(this, jdPrice, false, 2, null) > 0 && formatStringToDouble$default(this, jdPrice, false, 2, null) > formatStringToDouble$default(this, miaoShaPrice, false, 2, null)) {
            tvJdPrice.setText(jdPrice);
        } else {
            tvJdPrice.setText("");
        }
    }
}
