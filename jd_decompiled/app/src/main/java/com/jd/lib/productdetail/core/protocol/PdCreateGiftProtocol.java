package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.lifecycle.MediatorLiveData;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.gift.PdWqResultEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdCreateGiftProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "CreateGift";
    private MediatorLiveData<PdWqResultEntity> mJsonData;

    public PdCreateGiftProtocol(String str) {
        super(str);
        this.mJsonData = new MediatorLiveData<>();
    }

    private Map<String, Object> listKeyMaps(String str) {
        HashMap hashMap = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap2 = new HashMap(16);
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    Object obj2 = jSONObject.get(obj);
                    if (obj2 == null) {
                        obj2 = "";
                    }
                    hashMap2.put(obj, obj2);
                }
                return hashMap2;
            } catch (Exception unused) {
                hashMap = hashMap2;
                return hashMap;
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        Map<String, Object> listKeyMaps;
        if ((objArr[0] instanceof String) && (listKeyMaps = listKeyMaps((String) objArr[0])) != null && !listKeyMaps.isEmpty()) {
            for (String str : listKeyMaps.keySet()) {
                try {
                    jSONObject.put(str, listKeyMaps.get(str));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        super.parseError(httpError);
        MediatorLiveData<PdWqResultEntity> mediatorLiveData = this.mJsonData;
        if (mediatorLiveData != null) {
            mediatorLiveData.postValue(null);
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (!TextUtils.isEmpty(str)) {
            this.mJsonData.postValue((PdWqResultEntity) JDJSON.parseObject(str, PdWqResultEntity.class));
        }
        return str;
    }
}
