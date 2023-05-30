package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public class YogaValue {
    public final YogaUnit unit;
    public final float value;
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);

    /* renamed from: com.jd.dynamic.yoga.YogaValue$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jd$dynamic$yoga$YogaUnit;

        static {
            int[] iArr = new int[YogaUnit.values().length];
            $SwitchMap$com$jd$dynamic$yoga$YogaUnit = iArr;
            try {
                iArr[YogaUnit.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaUnit[YogaUnit.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public YogaValue(float f2, int i2) {
        this(f2, YogaUnit.fromInt(i2));
    }

    public YogaValue(float f2, YogaUnit yogaUnit) {
        this.value = f2;
        this.unit = yogaUnit;
    }

    public static YogaValue parse(String str) {
        if (str == null) {
            return null;
        }
        if ("undefined".equals(str)) {
            return UNDEFINED;
        }
        if ("auto".equals(str)) {
            return AUTO;
        }
        float f2 = 0.0f;
        if (str.endsWith("%")) {
            try {
                f2 = Float.parseFloat(str.substring(0, str.length() - 1));
            } catch (Exception unused) {
            }
            return new YogaValue(f2, YogaUnit.PERCENT);
        }
        try {
            f2 = Float.parseFloat(str);
        } catch (Exception unused2) {
        }
        return new YogaValue(f2, YogaUnit.POINT);
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            YogaUnit yogaUnit = this.unit;
            if (yogaUnit == yogaValue.unit) {
                return yogaUnit == YogaUnit.UNDEFINED || yogaUnit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    public String toString() {
        int i2 = AnonymousClass1.$SwitchMap$com$jd$dynamic$yoga$YogaUnit[this.unit.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        return "auto";
                    }
                    throw new IllegalStateException();
                }
                return this.value + "%";
            }
            return Float.toString(this.value);
        }
        return "undefined";
    }
}
