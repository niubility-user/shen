package c.t.m.g;

import android.content.Context;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.io.File;

/* loaded from: classes.dex */
public class u3 {
    public j4 a;

    /* loaded from: classes.dex */
    public class a implements e6 {
        public a(u3 u3Var) {
        }

        @Override // c.t.m.g.e6
        public void a(int i2, String str, String str2) {
            j4.p(str, str2);
        }
    }

    public u3() {
        j4.o("2.2.0_20210909");
    }

    public void a() {
        j4 j4Var = this.a;
        if (j4Var != null) {
            j4Var.c();
            this.a = null;
        }
    }

    public void b(Context context) {
        c();
        File externalFilesDir = context.getExternalFilesDir("data");
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        new File(externalFilesDir, "DrDebugLog");
        w5.b(new a(this));
    }

    public final void c() {
        j4 w = j4.w();
        this.a = w;
        if (w != null) {
            w.r(true);
            this.a.e();
        }
        j4.w().l(Final.FIVE_SECOND);
    }
}
