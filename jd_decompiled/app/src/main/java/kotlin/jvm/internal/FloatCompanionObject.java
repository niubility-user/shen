package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001c\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lkotlin/jvm/internal/FloatCompanionObject;", "", "", "NaN", "F", "getNaN", "()F", "MIN_VALUE", "getMIN_VALUE", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY", "MAX_VALUE", "getMAX_VALUE", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FloatCompanionObject {
    public static final FloatCompanionObject INSTANCE = new FloatCompanionObject();
    private static final float MIN_VALUE = MIN_VALUE;
    private static final float MIN_VALUE = MIN_VALUE;
    private static final float MAX_VALUE = Float.MAX_VALUE;
    private static final float POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final float POSITIVE_INFINITY = POSITIVE_INFINITY;
    private static final float NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final float NEGATIVE_INFINITY = NEGATIVE_INFINITY;
    private static final float NaN = Float.NaN;

    private FloatCompanionObject() {
    }

    public final float getMAX_VALUE() {
        return MAX_VALUE;
    }

    public final float getMIN_VALUE() {
        return MIN_VALUE;
    }

    public final float getNEGATIVE_INFINITY() {
        return NEGATIVE_INFINITY;
    }

    public final float getNaN() {
        return NaN;
    }

    public final float getPOSITIVE_INFINITY() {
        return POSITIVE_INFINITY;
    }
}
