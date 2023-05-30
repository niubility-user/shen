package jd.wjlogin_sdk.common;

import java.net.URI;
import java.util.Map;

/* loaded from: classes.dex */
public interface WJDGuardProxy {
    Map<String, String> getJDGuardSign(URI uri, byte[] bArr, String str, String str2, boolean z);
}
