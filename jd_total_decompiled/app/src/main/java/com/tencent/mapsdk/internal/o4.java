package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewGroup;

/* loaded from: classes9.dex */
public interface o4 extends h5 {

    /* loaded from: classes9.dex */
    public enum a {
        LEFT(0),
        RIGHT(1),
        BOTTOM(2),
        TOP(3);
        
        public int a;

        a(int i2) {
            this.a = i2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
            if (r1 >= 4) goto L5;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static final a a(int i2) {
            if (i2 >= 0) {
                values();
            }
            i2 = 0;
            return values()[i2];
        }
    }

    /* loaded from: classes9.dex */
    public enum b {
        LEFT_BOTTOM(0),
        CENTER_BOTTOM(4),
        RIGHT_BOTTOM(1),
        LEFT_TOP(3),
        CENTER_TOP(5),
        RIGHT_TOP(2);
        
        public final int a;

        b(int i2) {
            this.a = i2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:5:0x0007, code lost:
            if (r6 >= 6) goto L6;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static b a(int i2) {
            if (i2 >= 0) {
                values();
            }
            i2 = 0;
            b bVar = LEFT_BOTTOM;
            b[] values = values();
            for (int i3 = 0; i3 < 6; i3++) {
                b bVar2 = values[i3];
                if (bVar2.a == i2) {
                    return bVar2;
                }
            }
            return bVar;
        }
    }

    void a();

    void a(b bVar);

    boolean a(ViewGroup viewGroup, Bundle bundle);

    Rect d();

    b getPosition();
}
