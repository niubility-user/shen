package k.a.a.e.a;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes11.dex */
public class f extends h {
    public f(File file, boolean z, int i2) throws FileNotFoundException {
        super(file, z, i2);
    }

    @Override // k.a.a.e.a.h
    protected File f(int i2) throws IOException {
        String canonicalPath = this.f20223h.getCanonicalPath();
        return new File(canonicalPath.substring(0, canonicalPath.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) + k.a.a.i.c.h(i2));
    }
}
