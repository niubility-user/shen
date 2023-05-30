package com.facebook.react.uimanager.layoutanimation;

/* loaded from: classes12.dex */
enum LayoutAnimationType {
    CREATE,
    UPDATE,
    DELETE;

    /* renamed from: com.facebook.react.uimanager.layoutanimation.LayoutAnimationType$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType;

        static {
            int[] iArr = new int[LayoutAnimationType.values().length];
            $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType = iArr;
            try {
                iArr[LayoutAnimationType.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[LayoutAnimationType.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[LayoutAnimationType.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static String toString(LayoutAnimationType layoutAnimationType) {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[layoutAnimationType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return "delete";
                }
                throw new IllegalArgumentException("Unsupported LayoutAnimationType: " + layoutAnimationType);
            }
            return "update";
        }
        return "create";
    }
}
