package com.jd.libs.hybrid.preload;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class RequestHolder {
    public static final int STATUS_DEL = -1;
    public static final int STATUS_END = 1;
    public static final int STATUS_FAIL = 2;
    public static final int STATUS_NULL = -2;
    public static final int STATUS_REQUEST = 0;
    private Map<String, Integer> a = new HashMap();
    private Map<String, String> b = new HashMap();

    public void clear(String str) {
        Map<String, Integer> map;
        if (str == null || (map = this.a) == null || this.b == null) {
            return;
        }
        Integer num = null;
        for (String str2 : map.keySet()) {
            if (str2.endsWith(str)) {
                num = this.a.get(str2);
                str = str2;
            }
        }
        if (num != null) {
            if (num.intValue() == 0) {
                this.a.put(str, -1);
            } else if (num.intValue() == 1) {
                this.a.remove(str);
                this.b.remove(str);
            }
        }
    }

    public String getResult(String str) {
        Map<String, String> map = this.b;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public int getStatus(String str) {
        Map<String, Integer> map;
        Integer num;
        if (str == null || (map = this.a) == null || this.b == null || (num = map.get(str)) == null) {
            return -2;
        }
        return num.intValue();
    }

    public void onEnd(String str, String str2) {
        Map<String, Integer> map;
        Integer num;
        if (str == null || (map = this.a) == null || this.b == null || (num = map.get(str)) == null) {
            return;
        }
        if (num.intValue() == -1) {
            this.a.remove(str);
        } else if (num.intValue() == 0) {
            this.a.put(str, 1);
            this.b.put(str, str2);
        }
    }

    public void onError(String str) {
        Map<String, Integer> map;
        Integer num;
        if (str == null || (map = this.a) == null || this.b == null || (num = map.get(str)) == null) {
            return;
        }
        if (num.intValue() == -1) {
            this.a.remove(str);
        } else if (num.intValue() == 0) {
            this.a.put(str, 2);
        }
    }

    public void onRequest(String str) {
        Map<String, Integer> map;
        if (str == null || (map = this.a) == null || this.b == null) {
            return;
        }
        map.put(str, 0);
    }

    public void removeDirectly(String str) {
        Map<String, Integer> map;
        if (str == null || (map = this.a) == null || this.b == null) {
            return;
        }
        map.remove(str);
        this.b.remove(str);
    }
}
