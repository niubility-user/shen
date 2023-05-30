package k.a.a.e.a;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes11.dex */
public class m extends h {

    /* renamed from: l  reason: collision with root package name */
    private int f20239l;

    public m(File file, boolean z, int i2) throws FileNotFoundException {
        super(file, z, i2);
        this.f20239l = i2;
    }

    @Override // k.a.a.e.a.h
    protected File f(int i2) throws IOException {
        if (i2 == this.f20239l) {
            return this.f20223h;
        }
        String canonicalPath = this.f20223h.getCanonicalPath();
        return new File(canonicalPath.substring(0, canonicalPath.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) + (i2 >= 9 ? ".z" : ".z0") + (i2 + 1));
    }
}
