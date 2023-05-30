package com.jingdong.common.impl.encrypt;

import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.protocol.ParserModule;
import com.jingdong.common.protocol.encrypt.IEncrypt;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class Encrypt implements IEncrypt {
    private static final String TAG = "Encrypt";

    @Override // com.jingdong.common.protocol.encrypt.IEncrypt
    public <T> T decrypt(String str, Class<T> cls) {
        Map<String, String> decrypt = JsonEncryptUtil.decrypt(str);
        if (Log.D) {
            Log.d(TAG, "decrypt value = " + str);
        }
        if (decrypt != null) {
            try {
                IJsonParse jsonParser = UtilFactory.getInstance().getJsonParser(ParserModule.PARSER_FASTJSON);
                if (jsonParser != null) {
                    String jsonString = jsonParser.toJsonString(decrypt);
                    if (Log.D) {
                        Log.d(TAG, "the map json = " + jsonString);
                    }
                    return (T) jsonParser.parseJsonToObject(jsonString, cls);
                }
                return null;
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(TAG, "parse map to entity in exception and msg = \n" + e2.getMessage());
                    return null;
                }
                return null;
            }
        }
        return null;
    }
}
