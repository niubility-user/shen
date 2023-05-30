package com.jingdong.pdj.libcore.cube;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.IFunctionFactory;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/pdj/libcore/cube/DynamicUtils;", "", "<init>", "()V", "Companion", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class DynamicUtils {
    private static final String CUBE_HOURLYGOCOMMONEVENT = "hourlyGoCommonEvent";
    private static final String CUBE_NEARBY = "nearby";
    private static final String CUBE_NEARBYSEARCH = "nearbySearch";

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int cube_baseline = 200001;
    @NotNull
    private static HashMap<String, Integer> cube_template_viewType_Map = new HashMap<>();
    @NotNull
    private static HashMap<Integer, String> cube_viewType_template_Map = new HashMap<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b(\u0010)J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J/\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013R>\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\u0014j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t`\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR>\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\u0014j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0002`\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\"\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010\u0006R\u0016\u0010$\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0016\u0010'\u001a\u00020\t8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010%\u00a8\u0006*"}, d2 = {"Lcom/jingdong/pdj/libcore/cube/DynamicUtils$Companion;", "", "", "businessType", "", JDReactConstant.PREPARE, "(I)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "templateId", "Lcom/jd/dynamic/base/CommFunction;", "commFunction", "Lcom/jd/dynamic/apis/DYContainerConfig;", "getDynamicConfig", "(Landroid/content/Context;ILjava/lang/String;Lcom/jd/dynamic/base/CommFunction;)Lcom/jd/dynamic/apis/DYContainerConfig;", "config", "Lcom/jd/dynamic/apis/DynamicContainer;", "getDynamicView", "(Lcom/jd/dynamic/apis/DYContainerConfig;)Lcom/jd/dynamic/apis/DynamicContainer;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "cube_viewType_template_Map", "Ljava/util/HashMap;", "getCube_viewType_template_Map", "()Ljava/util/HashMap;", "setCube_viewType_template_Map", "(Ljava/util/HashMap;)V", "cube_template_viewType_Map", "getCube_template_viewType_Map", "setCube_template_viewType_Map", "cube_baseline", "I", "getCube_baseline", "()I", "setCube_baseline", "CUBE_HOURLYGOCOMMONEVENT", "Ljava/lang/String;", "CUBE_NEARBY", "CUBE_NEARBYSEARCH", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getCube_baseline() {
            return DynamicUtils.cube_baseline;
        }

        @NotNull
        public final HashMap<String, Integer> getCube_template_viewType_Map() {
            return DynamicUtils.cube_template_viewType_Map;
        }

        @NotNull
        public final HashMap<Integer, String> getCube_viewType_template_Map() {
            return DynamicUtils.cube_viewType_template_Map;
        }

        @NotNull
        public final DYContainerConfig getDynamicConfig(@NotNull Context context, int businessType, @NotNull String templateId, @Nullable final CommFunction commFunction) {
            return new DYContainerConfig(context, businessType == 0 ? "nearby" : DynamicUtils.CUBE_NEARBYSEARCH, templateId, new IFunctionFactory() { // from class: com.jingdong.pdj.libcore.cube.DynamicUtils$Companion$getDynamicConfig$1
                @Override // com.jd.dynamic.base.IFunctionFactory
                @Nullable
                public final CommFunction createCommFunction(@Nullable String p0) {
                    if (TextUtils.equals(p0, "hourlyGoCommonEvent")) {
                        return CommFunction.this;
                    }
                    return null;
                }
            });
        }

        @Nullable
        public final DynamicContainer getDynamicView(@NotNull DYContainerConfig config) {
            return DynamicSdk.getDriver().createContainer(config);
        }

        public final void prepare(int businessType) {
            DynamicSdk.getDriver().prepare(businessType == 0 ? "nearby" : DynamicUtils.CUBE_NEARBYSEARCH, "");
        }

        public final void setCube_baseline(int i2) {
            DynamicUtils.cube_baseline = i2;
        }

        public final void setCube_template_viewType_Map(@NotNull HashMap<String, Integer> hashMap) {
            DynamicUtils.cube_template_viewType_Map = hashMap;
        }

        public final void setCube_viewType_template_Map(@NotNull HashMap<Integer, String> hashMap) {
            DynamicUtils.cube_viewType_template_Map = hashMap;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
