package com.jingdong.common.impl.parse;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.List;

/* loaded from: classes5.dex */
public class JDJSONParser implements IJsonParse {
    private static final String TAG = "JDJSONParser";

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public boolean optBoolean(String str, String str2) {
        try {
            JDJSONObject jsonObject = toJsonObject(str);
            if (jsonObject != null) {
                return jsonObject.optBoolean(str2);
            }
            return false;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return false;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public int optInt(String str, String str2) {
        try {
            JDJSONObject jsonObject = toJsonObject(str);
            if (jsonObject != null) {
                return jsonObject.optInt(str2);
            }
            return 0;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return 0;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public String optString(String str, String str2) {
        try {
            JDJSONObject jsonObject = toJsonObject(str);
            if (jsonObject != null) {
                return jsonObject.optString(str2);
            }
            return null;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public <T> List<T> parseJsonToArray(String str, Class<T> cls) {
        try {
            return JDJSON.parseArray(str, cls);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse string to List in exception \n" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public <T> T parseJsonToObject(String str, Class<T> cls) {
        try {
            return (T) JDJSON.parseObject(str, cls);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "parse String to object in exception \n" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public String toJsonString(Object obj) {
        try {
            return JDJSON.toJSONString(obj);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public JDJSONArray optJsonArray(String str, String str2) {
        try {
            JDJSONObject jsonObject = toJsonObject(str);
            if (jsonObject != null) {
                return jsonObject.optJSONArray(str2);
            }
            return null;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    @Override // com.jingdong.common.protocol.parse.IJsonParse
    public JDJSONObject toJsonObject(String str) {
        try {
            return JDJSON.parseObject(str);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }
}
