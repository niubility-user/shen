package k.b.a;

import com.jd.dynamic.DYConstants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes11.dex */
class b {
    private Properties a;

    /* loaded from: classes11.dex */
    private static class a {
        static final b a = new b();
    }

    private b() {
        this.a = null;
        e();
    }

    private String a(char c2) {
        String property = d().getProperty(Integer.toHexString(c2).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b c() {
        return a.a;
    }

    private Properties d() {
        return this.a;
    }

    private void e() {
        try {
            g(new Properties());
            d().load(d.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    private boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    private void g(Properties properties) {
        this.a = properties;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] b(char c2) {
        String a2 = a(c2);
        if (a2 != null) {
            return a2.substring(a2.indexOf("(") + 1, a2.lastIndexOf(")")).split(DYConstants.DY_REGEX_COMMA);
        }
        return null;
    }
}
