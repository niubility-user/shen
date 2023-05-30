package com.jingdong.sdk.eldermode.helper;

import com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "", "Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;", "mtaInfo", "", "sendMtaInfo", "(Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeMtaSender {
    void sendMtaInfo(@Nullable ElderModeMtaInfo mtaInfo);
}
