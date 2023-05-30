package com.jd.libs.hybrid.datasnapshot;

import androidx.annotation.Keep;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J%\u0010\u0006\u001a\u00020\u00052\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jd/libs/hybrid/datasnapshot/ISnapshotListener;", "", "", "", "data", "", "onReceived", "(Ljava/util/Map;)V", "data-snapshot_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public interface ISnapshotListener {
    void onReceived(@Nullable Map<String, ? extends Object> data);
}
