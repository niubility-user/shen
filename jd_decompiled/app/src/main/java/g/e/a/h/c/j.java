package g.e.a.h.c;

import com.jd.libs.jdmbridge.base.JDBridgeManager;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
class j implements f {
    private final JSONObject a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(InputStream inputStream, String str) {
        this.a = b(inputStream);
        c(str);
    }

    private JSONObject b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return new JSONObject(b.g(inputStream, "UTF-8"));
            } catch (IOException | JSONException unused) {
            }
        }
        return new JSONObject();
    }

    private void c(String str) {
        try {
            JSONObject e2 = e(str);
            if (e2 == null) {
                return;
            }
            String a = a("/configuration_version", "");
            BigDecimal bigDecimal = new BigDecimal("0.0");
            try {
                bigDecimal = BigDecimal.valueOf(Double.parseDouble(a));
            } catch (NumberFormatException unused) {
            }
            if (bigDecimal.compareTo(new BigDecimal("2.0")) == 0) {
                this.a.getJSONObject("client").put("app_id", e2.getString("app_id"));
            } else if (bigDecimal.compareTo(new BigDecimal(JDBridgeManager.SDK_VERSION)) >= 0) {
                Iterator<String> keys = e2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!"package_name".equals(next)) {
                        d(next, e2.get(next), this.a);
                    }
                }
            }
        } catch (JSONException unused2) {
        }
    }

    private void d(String str, Object obj, JSONObject jSONObject) throws JSONException {
        if (str == null || obj == null || jSONObject == null) {
            return;
        }
        if (!(obj instanceof JSONObject)) {
            jSONObject.put(str, obj);
            return;
        }
        JSONObject jSONObject2 = (JSONObject) obj;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            d(next, jSONObject2.get(next), jSONObject.getJSONObject(str));
        }
    }

    private JSONObject e(String str) throws JSONException {
        JSONArray jSONArray = this.a.getJSONArray("appInfos");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject.getString("package_name").equals(str)) {
                return jSONObject;
            }
        }
        return null;
    }

    @Override // g.e.a.h.c.f
    public String a(String str, String str2) {
        if (str.endsWith("/")) {
            return str2;
        }
        String[] split = str.split("/");
        try {
            JSONObject jSONObject = this.a;
            for (int i2 = 1; i2 < split.length; i2++) {
                if (i2 == split.length - 1) {
                    str = jSONObject.get(split[i2]).toString();
                    return str;
                }
                jSONObject = jSONObject.getJSONObject(split[i2]);
            }
        } catch (JSONException unused) {
            String str3 = "JSONException when reading 'path': " + str;
        }
        return str2;
    }

    public String toString() {
        return "InputStreamReader{config=" + this.a.toString().hashCode() + '}';
    }
}
