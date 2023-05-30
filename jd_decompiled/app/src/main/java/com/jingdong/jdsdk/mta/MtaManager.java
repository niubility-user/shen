package com.jingdong.jdsdk.mta;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.task.Productor;
import com.jingdong.common.task.TaskManager;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes14.dex */
public class MtaManager {
    private static MtaManager mInstance;
    private ConcurrentHashMap<String, Set<String>> mCacheMap = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Productor<Void> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12897c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f12898e;

        a(String str, Context context, String str2, String str3, String str4) {
            this.a = str;
            this.b = context;
            this.f12897c = str2;
            this.d = str3;
            this.f12898e = str4;
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            String setString = MtaManager.this.getSetString(this.a);
            if (setString != null) {
                JDMtaUtils.onClickWithPageId(this.b, this.f12897c, this.a, this.d + setString, this.f12898e);
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes14.dex */
    class b implements Productor<Void> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12900c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f12901e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f12902f;

        b(String str, Context context, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = context;
            this.f12900c = str2;
            this.d = str3;
            this.f12901e = str4;
            this.f12902f = str5;
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            String setString = MtaManager.this.getSetString(this.a);
            if (setString != null) {
                JDMtaUtils.onClickWithPageId(this.b, this.f12900c, this.a, this.d + setString, this.f12901e, this.f12902f);
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes14.dex */
    class c implements Productor<Void> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12904c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f12905e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f12906f;

        c(String str, Context context, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = context;
            this.f12904c = str2;
            this.d = str3;
            this.f12905e = str4;
            this.f12906f = str5;
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            String setString = MtaManager.this.getSetString(this.a);
            if (setString != null) {
                JDMtaUtils.sendExposureData(this.b, this.a, this.f12904c, this.d, this.f12905e, this.f12906f + setString, "", "", "");
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes14.dex */
    class d implements Productor<Void> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12908c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f12909e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f12910f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f12911g;

        d(String str, Context context, String str2, String str3, String str4, String str5, String str6) {
            this.a = str;
            this.b = context;
            this.f12908c = str2;
            this.d = str3;
            this.f12909e = str4;
            this.f12910f = str5;
            this.f12911g = str6;
        }

        @Override // com.jingdong.common.task.Productor
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void run() {
            String setString = MtaManager.this.getSetString(this.a);
            if (setString != null) {
                JDMtaUtils.sendExposureDataWithExt(this.b, this.f12908c, this.d + setString, this.f12909e, this.a, this.f12910f, this.f12911g, null);
                return null;
            }
            return null;
        }
    }

    private MtaManager() {
    }

    public static synchronized MtaManager getInstance() {
        MtaManager mtaManager;
        synchronized (MtaManager.class) {
            if (mInstance == null) {
                mInstance = new MtaManager();
            }
            mtaManager = mInstance;
        }
        return mtaManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSetString(String str) {
        synchronized (this) {
            Set<String> set = this.mCacheMap.get(str);
            if (set == null) {
                return null;
            }
            this.mCacheMap.remove(str);
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = set.iterator();
            for (int i2 = 0; it.hasNext() && i2 < 500; i2++) {
                sb.append(it.next());
                sb.append(CartConstant.KEY_YB_INFO_LINK);
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    public void add(String str, String str2) {
        Set<String> set;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        synchronized (this) {
            set = this.mCacheMap.get(str);
            if (set == null) {
                set = Collections.synchronizedSet(new LinkedHashSet());
                this.mCacheMap.put(str, set);
            }
        }
        if (set.contains(str2)) {
            return;
        }
        set.add(str2);
    }

    public void clear() {
        this.mCacheMap.clear();
    }

    public void onClickWithPageId(Context context, String str, String str2, String str3) {
        onClickWithPageId(context, str, str2, str3, "");
    }

    public void remove(String str) {
        synchronized (this) {
            if (this.mCacheMap.containsKey(str)) {
                this.mCacheMap.remove(str);
            }
        }
    }

    public void sendExposureData(Context context, String str, String str2, String str3, String str4, String str5) {
        TaskManager.executeTask(new c(str2, context, str4, str3, str, str5), null);
    }

    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        TaskManager.executeTask(new d(str2, context, str, str5, str4, str3, str6), null);
    }

    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
        TaskManager.executeTask(new a(str2, context, str, str4, str3), null);
    }

    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4, String str5) {
        TaskManager.executeTask(new b(str2, context, str, str5, str3, str4), null);
    }
}
