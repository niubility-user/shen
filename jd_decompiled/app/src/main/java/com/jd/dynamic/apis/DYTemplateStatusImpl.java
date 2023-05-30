package com.jd.dynamic.apis;

import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/jd/dynamic/apis/DYTemplateStatusImpl;", "Lcom/jd/dynamic/apis/DYTemplateStatus;", "", "haveCache", "()Z", "haveBackup", "haveLocal", JDBusinessAddress.TYPE_BACKUP, "Z", "cache", "<init>", "(ZZ)V", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DYTemplateStatusImpl extends DYTemplateStatus {
    private final boolean a;
    private final boolean b;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DYTemplateStatusImpl() {
        /*
            r3 = this;
            r0 = 0
            r1 = 3
            r2 = 0
            r3.<init>(r0, r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.apis.DYTemplateStatusImpl.<init>():void");
    }

    public DYTemplateStatusImpl(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public /* synthetic */ DYTemplateStatusImpl(boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2);
    }

    @Override // com.jd.dynamic.apis.DYTemplateStatus
    /* renamed from: haveBackup  reason: from getter */
    public boolean getB() {
        return this.b;
    }

    @Override // com.jd.dynamic.apis.DYTemplateStatus
    /* renamed from: haveCache  reason: from getter */
    public boolean getA() {
        return this.a;
    }

    @Override // com.jd.dynamic.apis.DYTemplateStatus
    public boolean haveLocal() {
        return this.a || this.b;
    }
}
