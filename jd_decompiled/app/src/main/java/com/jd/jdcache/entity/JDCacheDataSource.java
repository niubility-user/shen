package com.jd.jdcache.entity;

import androidx.annotation.Keep;
import com.jd.jdcache.util.IUsefulCheckKt;
import com.jd.jdcache.util.UrlHelper;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0015\u001a\u00020\u000e\u0012(\b\u0002\u0010\u0016\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0012\u00a2\u0006\u0004\b*\u0010+B]\b\u0016\u0012\u0006\u0010,\u001a\u00020\t\u0012\b\b\u0002\u0010-\u001a\u00020\u001f\u0012\u0010\b\u0002\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\"\b\u0002\u0010/\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b*\u00100J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\n\u001a\u00020\u00052\u001e\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b0\u0002H\u0002\u00a2\u0006\u0004\b\n\u0010\u0007J\u0017\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J0\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0012H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014JD\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u000e2(\b\u0002\u0010\u0016\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0012H\u00c6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\tH\u00d6\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bH\u00d6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b \u0010!RB\u0010\u0016\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\"\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010%R\"\u0010\u0015\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010&\u001a\u0004\b'\u0010\u0010\"\u0004\b(\u0010)\u00a8\u00061"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheDataSource;", "", "", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "sourceList", "", "setSourceList1", "(Ljava/util/List;)V", "Lkotlin/Triple;", "", "setSourceList2", "sourceStr", "setSourceStr", "(Ljava/lang/String;)V", "Lcom/jd/jdcache/entity/JDCacheFileDetail;", "component1", "()Lcom/jd/jdcache/entity/JDCacheFileDetail;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "component2", "()Ljava/util/HashMap;", "localFileDirDetail", "localFileMap", JDViewKitEventHelper.ActionType_Copy, "(Lcom/jd/jdcache/entity/JDCacheFileDetail;Ljava/util/HashMap;)Lcom/jd/jdcache/entity/JDCacheDataSource;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/HashMap;", "getLocalFileMap", "setLocalFileMap", "(Ljava/util/HashMap;)V", "Lcom/jd/jdcache/entity/JDCacheFileDetail;", "getLocalFileDirDetail", "setLocalFileDirDetail", "(Lcom/jd/jdcache/entity/JDCacheFileDetail;)V", "<init>", "(Lcom/jd/jdcache/entity/JDCacheFileDetail;Ljava/util/HashMap;)V", "offlineDirPath", "isRelativePath", "sourceList1", "sourceList2", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final /* data */ class JDCacheDataSource {
    @NotNull
    private JDCacheFileDetail localFileDirDetail;
    @Nullable
    private HashMap<String, JDCacheLocalResp> localFileMap;

    public JDCacheDataSource(@NotNull JDCacheFileDetail jDCacheFileDetail, @Nullable HashMap<String, JDCacheLocalResp> hashMap) {
        this.localFileDirDetail = jDCacheFileDetail;
        this.localFileMap = hashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JDCacheDataSource copy$default(JDCacheDataSource jDCacheDataSource, JDCacheFileDetail jDCacheFileDetail, HashMap hashMap, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            jDCacheFileDetail = jDCacheDataSource.localFileDirDetail;
        }
        if ((i2 & 2) != 0) {
            hashMap = jDCacheDataSource.localFileMap;
        }
        return jDCacheDataSource.copy(jDCacheFileDetail, hashMap);
    }

    private final void setSourceList1(List<JDCacheLocalResp> sourceList) {
        List mutableList;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) sourceList);
        Map map = null;
        Collection useful$default = IUsefulCheckKt.useful$default(mutableList, false, 1, null);
        if (!(useful$default == null || useful$default.isEmpty())) {
            map = (Map) HashMap.class.newInstance();
            for (Object obj : useful$default) {
                String urlToKey = UrlHelper.INSTANCE.urlToKey(((JDCacheLocalResp) obj).getUrl());
                if (urlToKey != null) {
                    Intrinsics.checkExpressionValueIsNotNull(map, "map");
                    map.put(urlToKey, obj);
                }
            }
        }
        this.localFileMap = (HashMap) map;
    }

    private final void setSourceList2(List<Triple<String, String, String>> sourceList) {
        ArrayList arrayList = new ArrayList(sourceList.size());
        Iterator<T> it = sourceList.iterator();
        while (it.hasNext()) {
            Triple triple = (Triple) it.next();
            arrayList.add(new JDCacheLocalResp((String) triple.component1(), (String) triple.component2(), null, (String) triple.component3(), null, false, 52, null));
        }
        Map map = null;
        Collection useful$default = IUsefulCheckKt.useful$default(arrayList, false, 1, null);
        if (!(useful$default == null || useful$default.isEmpty())) {
            map = (Map) HashMap.class.newInstance();
            for (Object obj : useful$default) {
                String urlToKey = UrlHelper.INSTANCE.urlToKey(((JDCacheLocalResp) obj).getUrl());
                if (urlToKey != null) {
                    Intrinsics.checkExpressionValueIsNotNull(map, "map");
                    map.put(urlToKey, obj);
                }
            }
        }
        this.localFileMap = (HashMap) map;
    }

    private final void setSourceStr(String sourceStr) {
        Map map = null;
        Collection useful$default = IUsefulCheckKt.useful$default(JDCacheLocalRespKt.jsonArrayParse(sourceStr), false, 1, null);
        if (!(useful$default == null || useful$default.isEmpty())) {
            map = (Map) HashMap.class.newInstance();
            for (Object obj : useful$default) {
                String urlToKey = UrlHelper.INSTANCE.urlToKey(((JDCacheLocalResp) obj).getUrl());
                if (urlToKey != null) {
                    Intrinsics.checkExpressionValueIsNotNull(map, "map");
                    map.put(urlToKey, obj);
                }
            }
        }
        this.localFileMap = (HashMap) map;
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final JDCacheFileDetail getLocalFileDirDetail() {
        return this.localFileDirDetail;
    }

    @Nullable
    public final HashMap<String, JDCacheLocalResp> component2() {
        return this.localFileMap;
    }

    @NotNull
    public final JDCacheDataSource copy(@NotNull JDCacheFileDetail jDCacheFileDetail, @Nullable HashMap<String, JDCacheLocalResp> hashMap) {
        return new JDCacheDataSource(jDCacheFileDetail, hashMap);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof JDCacheDataSource) {
                JDCacheDataSource jDCacheDataSource = (JDCacheDataSource) other;
                return Intrinsics.areEqual(this.localFileDirDetail, jDCacheDataSource.localFileDirDetail) && Intrinsics.areEqual(this.localFileMap, jDCacheDataSource.localFileMap);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final JDCacheFileDetail getLocalFileDirDetail() {
        return this.localFileDirDetail;
    }

    @Nullable
    public final HashMap<String, JDCacheLocalResp> getLocalFileMap() {
        return this.localFileMap;
    }

    public int hashCode() {
        JDCacheFileDetail jDCacheFileDetail = this.localFileDirDetail;
        int hashCode = (jDCacheFileDetail != null ? jDCacheFileDetail.hashCode() : 0) * 31;
        HashMap<String, JDCacheLocalResp> hashMap = this.localFileMap;
        return hashCode + (hashMap != null ? hashMap.hashCode() : 0);
    }

    public final void setLocalFileDirDetail(@NotNull JDCacheFileDetail jDCacheFileDetail) {
        this.localFileDirDetail = jDCacheFileDetail;
    }

    public final void setLocalFileMap(@Nullable HashMap<String, JDCacheLocalResp> hashMap) {
        this.localFileMap = hashMap;
    }

    @NotNull
    public String toString() {
        return "JDCacheDataSource(localFileDirDetail=" + this.localFileDirDetail + ", localFileMap=" + this.localFileMap + ")";
    }

    public /* synthetic */ JDCacheDataSource(JDCacheFileDetail jDCacheFileDetail, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(jDCacheFileDetail, (i2 & 2) != 0 ? null : hashMap);
    }

    public /* synthetic */ JDCacheDataSource(String str, boolean z, List list, List list2, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : str2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.util.HashMap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public JDCacheDataSource(@org.jetbrains.annotations.NotNull java.lang.String r4, boolean r5, @org.jetbrains.annotations.Nullable java.util.List<com.jd.jdcache.entity.JDCacheLocalResp> r6, @org.jetbrains.annotations.Nullable java.util.List<kotlin.Triple<java.lang.String, java.lang.String, java.lang.String>> r7, @org.jetbrains.annotations.Nullable java.lang.String r8) {
        /*
            r3 = this;
            com.jd.jdcache.entity.JDCacheFileDetail r0 = new com.jd.jdcache.entity.JDCacheFileDetail
            if (r5 == 0) goto L36
            java.io.File r5 = new java.io.File
            com.jd.jdcache.JDCacheSetting r1 = com.jd.jdcache.JDCacheSetting.INSTANCE
            com.jd.jdcache.JDCacheParamsProvider r1 = r1.getParamsProvider()
            if (r1 == 0) goto L2e
            java.lang.String r1 = r1.getCacheDir()
            if (r1 == 0) goto L2e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = java.io.File.separator
            r2.append(r1)
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            if (r4 == 0) goto L2e
            r5.<init>(r4)
            goto L3b
        L2e:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "Cache dir need to be set by JDCacheParamsProvider"
            r4.<init>(r5)
            throw r4
        L36:
            java.io.File r5 = new java.io.File
            r5.<init>(r4)
        L3b:
            r0.<init>(r5)
            r4 = 2
            r5 = 0
            r3.<init>(r0, r5, r4, r5)
            if (r6 == 0) goto L49
            r3.setSourceList1(r6)
            goto L54
        L49:
            if (r7 == 0) goto L4f
            r3.setSourceList2(r7)
            goto L54
        L4f:
            if (r8 == 0) goto L54
            r3.setSourceStr(r8)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.entity.JDCacheDataSource.<init>(java.lang.String, boolean, java.util.List, java.util.List, java.lang.String):void");
    }
}
