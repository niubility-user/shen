package g.e.a.h.c;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public final class b {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void b(Reader reader, Writer writer) throws IOException {
        c(reader, writer, new char[4096]);
    }

    public static void c(Reader reader, Writer writer, char[] cArr) throws IOException {
        while (true) {
            int read = reader.read(cArr);
            if (-1 == read) {
                return;
            }
            writer.write(cArr, 0, read);
        }
    }

    public static Map<String, String> d(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(e(entry.getKey()), entry.getValue());
        }
        return hashMap;
    }

    public static String e(String str) {
        int i2 = 0;
        if (str.length() > 0) {
            while (str.charAt(i2) == '/') {
                i2++;
            }
        }
        return "/" + str.substring(i2);
    }

    public static g.e.a.b f(String str, String str2) {
        if (str == null) {
            if (str2 != null) {
                if (str2.contains("connect-drcn")) {
                    return g.e.a.b.f19448c;
                }
                if (str2.contains("connect-dre")) {
                    return g.e.a.b.d;
                }
                if (str2.contains("connect-drru")) {
                    return g.e.a.b.f19449e;
                }
                if (str2.contains("connect-dra")) {
                    return g.e.a.b.f19450f;
                }
            }
            return g.e.a.b.b;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case R2.attr.triggerId /* 2155 */:
                if (str.equals("CN")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.unButtonTextColor /* 2177 */:
                if (str.equals("DE")) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.color.C_Indigo_08 /* 2627 */:
                if (str.equals("RU")) {
                    c2 = 2;
                    break;
                }
                break;
            case R2.color.C_Lightblue_06_dark /* 2644 */:
                if (str.equals("SG")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return g.e.a.b.f19448c;
            case 1:
                return g.e.a.b.d;
            case 2:
                return g.e.a.b.f19449e;
            case 3:
                return g.e.a.b.f19450f;
            default:
                return g.e.a.b.b;
        }
    }

    public static String g(InputStream inputStream, String str) throws UnsupportedEncodingException, IOException {
        StringWriter stringWriter = new StringWriter();
        b(new InputStreamReader(inputStream, str), stringWriter);
        return stringWriter.toString();
    }
}
