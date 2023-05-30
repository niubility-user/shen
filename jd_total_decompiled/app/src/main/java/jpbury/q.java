package jpbury;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jdpay.bury.proguard.ImplMethodsKeep;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ImplMethodsKeep
/* loaded from: classes11.dex */
public class q implements JsonSerializer<p> {
    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(p pVar, Type type, JsonSerializationContext jsonSerializationContext) {
        HashMap hashMap = new HashMap();
        Iterator<l> it = pVar.a().c().iterator();
        while (it.hasNext()) {
            l next = it.next();
            if (next != null) {
                String b = next.b();
                if (!TextUtils.isEmpty(b)) {
                    hashMap.put(next.a(), b);
                }
            }
        }
        for (Map.Entry<String, String> entry : pVar.c().entrySet()) {
            if (entry != null) {
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    hashMap.put(entry.getKey(), value);
                }
            }
        }
        JsonObject asJsonObject = jsonSerializationContext.serialize(hashMap).getAsJsonObject();
        int b2 = pVar.b();
        if (b2 != -1) {
            asJsonObject.addProperty("logLevel", Integer.valueOf(b2));
        }
        return asJsonObject;
    }
}
