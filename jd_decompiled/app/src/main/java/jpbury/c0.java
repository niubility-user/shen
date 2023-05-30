package jpbury;

import com.jdpay.bury.IdExtension;
import com.jdpay.bury.SessionPack;

/* loaded from: classes11.dex */
public interface c0 {
    SessionPack a();

    SessionPack a(String str);

    SessionPack a(String str, String str2, String str3);

    void a(String str, SessionPack sessionPack);

    void a(String str, String str2);

    void a(String str, String str2, IdExtension idExtension, String str3, String str4, int i2, boolean z);

    void a(String str, String str2, IdExtension idExtension, String str3, String str4, boolean z);

    void a(String str, String str2, String str3, String str4, String str5, String str6, int i2, boolean z);

    void a(String str, String str2, Throwable th, String str3, boolean z);

    SessionPack b(String str, String str2);

    String b();
}
