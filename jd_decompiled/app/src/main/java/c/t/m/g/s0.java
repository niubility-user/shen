package c.t.m.g;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class s0 {
    public static volatile s0 d;
    public float a = 0.0f;
    public float b = 100.0f;

    /* renamed from: c  reason: collision with root package name */
    public boolean f662c = false;

    public static s0 a() {
        if (d == null) {
            synchronized (s0.class) {
                if (d == null) {
                    d = new s0();
                }
            }
        }
        return d;
    }

    public boolean b(List<Float> list, int i2) {
        float f2;
        if (list.size() >= 5) {
            int size = list.size();
            float[] fArr = new float[size];
            int i3 = 0;
            while (true) {
                f2 = 0.0f;
                if (i3 >= size) {
                    break;
                }
                Float f3 = list.get(i3);
                if (f3 != null) {
                    f2 = f3.floatValue();
                }
                fArr[i3] = f2;
                i3++;
            }
            Arrays.sort(fArr);
            float[] fArr2 = new float[5];
            for (int i4 = 0; i4 < 5; i4++) {
                fArr2[i4] = fArr[(size - 1) - i4];
                f2 += fArr2[i4];
            }
            float f4 = f2 / 5.0f;
            int i5 = (f4 > 22.0f ? 1 : (f4 == 22.0f ? 0 : -1));
            if (this.a < f4) {
                this.a = f4;
            }
            if (this.b > f4) {
                this.b = f4;
            }
            if (f4 - f4 > 2.0f) {
                this.f662c = false;
            }
            if (f4 > (this.a + this.b) / 2.0f) {
                this.f662c = true;
            } else if (i5 < 0) {
                this.f662c = false;
            }
        }
        return this.f662c;
    }
}
