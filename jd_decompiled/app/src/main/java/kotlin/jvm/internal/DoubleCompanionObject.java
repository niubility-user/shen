package kotlin.jvm.internal;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001c\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lkotlin/jvm/internal/DoubleCompanionObject;", "", "", "MIN_VALUE", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "getMIN_VALUE", "()D", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY", "NaN", "getNaN", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY", "MAX_VALUE", "getMAX_VALUE", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DoubleCompanionObject {
    public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
    private static final double MIN_VALUE = MIN_VALUE;
    private static final double MIN_VALUE = MIN_VALUE;
    private static final double MAX_VALUE = Double.MAX_VALUE;
    private static final double POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final double POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final double NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final double NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final double NaN = NaN;
    private static final double NaN = NaN;

    private DoubleCompanionObject() {
    }

    public final double getMAX_VALUE() {
        return MAX_VALUE;
    }

    public final double getMIN_VALUE() {
        return MIN_VALUE;
    }

    public final double getNEGATIVE_INFINITY() {
        return NEGATIVE_INFINITY;
    }

    public final double getNaN() {
        return NaN;
    }

    public final double getPOSITIVE_INFINITY() {
        return POSITIVE_INFINITY;
    }
}
