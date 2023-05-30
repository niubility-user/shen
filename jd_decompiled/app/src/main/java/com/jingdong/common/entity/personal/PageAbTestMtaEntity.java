package com.jingdong.common.entity.personal;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.common.utils.ABTestUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ4\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0006H\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R'\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\bR\u001b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u0019\u0010\u0004\u00a8\u0006\u001c"}, d2 = {"Lcom/jingdong/common/entity/personal/PageAbTestMtaEntity;", "", "Lcom/jingdong/common/entity/personal/LibMtaEntity;", "component1", "()Lcom/jingdong/common/entity/personal/LibMtaEntity;", "Ljava/util/HashMap;", "", "component2", "()Ljava/util/HashMap;", "expoMta", ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, JDViewKitEventHelper.ActionType_Copy, "(Lcom/jingdong/common/entity/personal/LibMtaEntity;Ljava/util/HashMap;)Lcom/jingdong/common/entity/personal/PageAbTestMtaEntity;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/HashMap;", "getAbTest", "Lcom/jingdong/common/entity/personal/LibMtaEntity;", "getExpoMta", "<init>", "(Lcom/jingdong/common/entity/personal/LibMtaEntity;Ljava/util/HashMap;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final /* data */ class PageAbTestMtaEntity {
    @Nullable
    private final HashMap<String, String> abTest;
    @Nullable
    private final LibMtaEntity expoMta;

    public PageAbTestMtaEntity(@Nullable LibMtaEntity libMtaEntity, @Nullable HashMap<String, String> hashMap) {
        this.expoMta = libMtaEntity;
        this.abTest = hashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PageAbTestMtaEntity copy$default(PageAbTestMtaEntity pageAbTestMtaEntity, LibMtaEntity libMtaEntity, HashMap hashMap, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            libMtaEntity = pageAbTestMtaEntity.expoMta;
        }
        if ((i2 & 2) != 0) {
            hashMap = pageAbTestMtaEntity.abTest;
        }
        return pageAbTestMtaEntity.copy(libMtaEntity, hashMap);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final LibMtaEntity getExpoMta() {
        return this.expoMta;
    }

    @Nullable
    public final HashMap<String, String> component2() {
        return this.abTest;
    }

    @NotNull
    public final PageAbTestMtaEntity copy(@Nullable LibMtaEntity expoMta, @Nullable HashMap<String, String> abTest) {
        return new PageAbTestMtaEntity(expoMta, abTest);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof PageAbTestMtaEntity) {
                PageAbTestMtaEntity pageAbTestMtaEntity = (PageAbTestMtaEntity) other;
                return Intrinsics.areEqual(this.expoMta, pageAbTestMtaEntity.expoMta) && Intrinsics.areEqual(this.abTest, pageAbTestMtaEntity.abTest);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final HashMap<String, String> getAbTest() {
        return this.abTest;
    }

    @Nullable
    public final LibMtaEntity getExpoMta() {
        return this.expoMta;
    }

    public int hashCode() {
        LibMtaEntity libMtaEntity = this.expoMta;
        int hashCode = (libMtaEntity != null ? libMtaEntity.hashCode() : 0) * 31;
        HashMap<String, String> hashMap = this.abTest;
        return hashCode + (hashMap != null ? hashMap.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "PageAbTestMtaEntity(expoMta=" + this.expoMta + ", abTest=" + this.abTest + ")";
    }
}
