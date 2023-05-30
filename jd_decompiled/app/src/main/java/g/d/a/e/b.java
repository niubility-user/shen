package g.d.a.e;

import com.heytap.msp.push.mode.BaseMode;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class b extends BaseMode {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f19432c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private int f19433e = -2;

    /* renamed from: f  reason: collision with root package name */
    private String f19434f;

    public static <T> String c(List<T> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        return sb.toString();
    }

    public int a() {
        return this.f19432c;
    }

    public int b() {
        return this.f19433e;
    }

    public void d(String str) {
    }

    public void e(String str) {
    }

    public void f(int i2) {
        this.f19432c = i2;
    }

    public void g(int i2) {
        this.f19433e = i2;
    }

    public String getContent() {
        return this.d;
    }

    @Override // com.heytap.msp.push.mode.BaseMode
    public int getType() {
        return 4105;
    }

    public void setAppPackage(String str) {
        this.f19434f = str;
    }

    public void setContent(String str) {
        this.d = str;
    }

    public String toString() {
        return "CallBackResult{, mRegisterID='" + this.a + "', mSdkVersion='" + this.b + "', mCommand=" + this.f19432c + "', mContent='" + this.d + "', mAppPackage=" + this.f19434f + "', mResponseCode=" + this.f19433e + '}';
    }
}
