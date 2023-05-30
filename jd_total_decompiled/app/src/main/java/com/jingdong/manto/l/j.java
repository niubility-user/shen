package com.jingdong.manto.l;

import android.text.TextUtils;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class j {
    private final MantoHandler a;
    final Map<String, List<a>> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    final Map<String, Boolean> f13233c = new HashMap();

    /* loaded from: classes15.dex */
    public interface a {
        void a();

        void b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(MantoHandler mantoHandler) {
        this.a = mantoHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Runnable runnable) {
        this.a.a(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<a> remove = this.b.remove(str);
        if (MantoUtils.isEmpty(remove)) {
            return;
        }
        Iterator<a> it = remove.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        remove.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, a aVar) {
        List<a> list;
        if (TextUtils.isEmpty(str) || aVar == null || (list = this.b.get(str)) == null) {
            return;
        }
        list.remove(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f13233c.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<a> remove = this.b.remove(str);
        if (MantoUtils.isEmpty(remove)) {
            return;
        }
        Iterator<a> it = remove.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }
}
