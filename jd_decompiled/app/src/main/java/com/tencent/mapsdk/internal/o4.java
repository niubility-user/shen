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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static final com.tencent.mapsdk.internal.o4.a a(int r1) {
            /*
                if (r1 < 0) goto L8
                values()
                r0 = 4
                if (r1 < r0) goto L9
            L8:
                r1 = 0
            L9:
                com.tencent.mapsdk.internal.o4$a[] r0 = values()
                r1 = r0[r1]
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.o4.a.a(int):com.tencent.mapsdk.internal.o4$a");
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.tencent.mapsdk.internal.o4.b a(int r6) {
            /*
                r0 = 6
                r1 = 0
                if (r6 < 0) goto L9
                values()
                if (r6 < r0) goto La
            L9:
                r6 = 0
            La:
                com.tencent.mapsdk.internal.o4$b r2 = com.tencent.mapsdk.internal.o4.b.LEFT_BOTTOM
                com.tencent.mapsdk.internal.o4$b[] r3 = values()
            L10:
                if (r1 >= r0) goto L1d
                r4 = r3[r1]
                int r5 = r4.a
                if (r5 != r6) goto L1a
                r2 = r4
                goto L1d
            L1a:
                int r1 = r1 + 1
                goto L10
            L1d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.o4.b.a(int):com.tencent.mapsdk.internal.o4$b");
        }
    }

    void a();

    void a(b bVar);

    boolean a(ViewGroup viewGroup, Bundle bundle);

    Rect d();

    b getPosition();
}
