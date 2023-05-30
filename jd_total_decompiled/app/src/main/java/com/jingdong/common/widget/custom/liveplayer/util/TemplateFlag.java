package com.jingdong.common.widget.custom.liveplayer.util;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/common/widget/custom/liveplayer/util/TemplateFlag;", "", "<init>", "()V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class TemplateFlag {
    @JvmField
    @NotNull
    public static final HashMap<String, String> sTemplateFlagMap;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        sTemplateFlagMap = hashMap;
        hashMap.put("normal", TemplateFlagKt.VALUE_MEDIUM);
        hashMap.put(TemplateFlagKt.KEY_SMALL, TemplateFlagKt.VALUE_SMALL);
    }
}
