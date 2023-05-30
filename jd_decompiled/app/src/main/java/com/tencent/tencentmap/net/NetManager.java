package com.tencent.tencentmap.net;

import com.tencent.map.tools.net.http.HttpCanceler;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class NetManager {
    private static NetManager sInstance;

    private NetManager() {
    }

    public static synchronized NetManager getInstance() {
        NetManager netManager;
        synchronized (NetManager.class) {
            if (sInstance == null) {
                sInstance = new NetManager();
            }
            netManager = sInstance;
        }
        return netManager;
    }

    public NetResponse doGet(String str) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, int i2, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).retryNum(i2).canceler(httpCanceler).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2, int i2) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2, int i2, int i3, HashMap<String, String> hashMap) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).timeOut(i3).header(hashMap).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2, int i2, int i3, HashMap<String, String> hashMap, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).timeOut(i3).retryNum(i2).header(hashMap).canceler(httpCanceler).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2, int i2, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).canceler(httpCanceler).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doGet(String str, String str2, int i2, HashMap<String, String> hashMap, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doGet = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).header(hashMap).canceler(httpCanceler).doGet();
        if (doGet != null) {
            return new NetResponse(doGet);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2, int i3, HashMap<String, String> hashMap) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).timeOut(i3).retryNum(i2).header(hashMap).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).canceler(httpCanceler).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2, HashMap<String, String> hashMap, int i3) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).timeOut(i3).retryNum(i2).header(hashMap).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2, HashMap<String, String> hashMap, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).retryNum(i2).header(hashMap).canceler(httpCanceler).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, String str2, byte[] bArr, int i2, HashMap<String, String> hashMap, HttpCanceler httpCanceler, int i3) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).timeOut(i3).retryNum(i2).header(hashMap).canceler(httpCanceler).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, byte[] bArr) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPost(String str, byte[] bArr, int i2, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).retryNum(i2).canceler(httpCanceler).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public NetResponse doPostNoBuffer(String str, String str2, byte[] bArr) {
        com.tencent.map.tools.net.NetResponse doPost = com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).userAgent(str2).postData(bArr).doPost();
        if (doPost != null) {
            return new NetResponse(doPost);
        }
        return null;
    }

    public void doRangePost(String str, byte[] bArr, String str2, String str3, String str4, String str5, HttpCanceler httpCanceler) {
        com.tencent.map.tools.net.NetManager.getInstance().builder().url(str).token(str2).postData(bArr).nonce(str3).timestamp(str4).startTag(str5).canceler(httpCanceler).doPost();
    }
}
