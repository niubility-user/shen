package jpbury;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jdpay.bury.proguard.ImplMethodsKeep;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

@ImplMethodsKeep
/* loaded from: classes11.dex */
public class u implements JsonSerializer<t> {
    private void a(JsonObject jsonObject) {
        Iterator<Map.Entry<String, JsonElement>> it = jsonObject.entrySet().iterator();
        while (it.hasNext()) {
            JsonElement value = it.next().getValue();
            if (value.isJsonPrimitive() && TextUtils.isEmpty(value.getAsString())) {
                it.remove();
            }
        }
    }

    private void a(JsonSerializationContext jsonSerializationContext, Object obj, JsonObject jsonObject) {
        JsonElement serialize;
        if (obj == null || (serialize = jsonSerializationContext.serialize(obj)) == null) {
            return;
        }
        JsonObject asJsonObject = serialize.getAsJsonObject();
        a(asJsonObject);
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive()) {
                jsonObject.add(entry.getKey(), value);
            } else {
                try {
                    jsonObject.add(entry.getKey(), new JsonPrimitive(x.a().toJson(value)));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(t tVar, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject asJsonObject = jsonSerializationContext.serialize(tVar.a()).getAsJsonObject();
        a(asJsonObject);
        a(jsonSerializationContext, tVar.b(), asJsonObject);
        return asJsonObject;
    }
}
