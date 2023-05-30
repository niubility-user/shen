package com.jingdong.common.utils.mta.base;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001Ja\u0010\f\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\f\u0010\rJw\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000eH&\u00a2\u0006\u0004\b\u0010\u0010\u0011Ja\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0012\u0010\rJw\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000eH&\u00a2\u0006\u0004\b\u0013\u0010\u0011\u00a8\u0006\u0014"}, d2 = {"Lcom/jingdong/common/utils/mta/base/JdPersonalMtaInterface;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "eventId", "eventParam", "pageId", WebPerfManager.PAGE_NAME, "pageParam", "jsonParam", "", "clickMta", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/util/HashMap;", "ext", "clickMtaExtMap", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)V", "exposureMta", "exposureMtaExtMap", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface JdPersonalMtaInterface {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void clickMta$default(JdPersonalMtaInterface jdPersonalMtaInterface, Context context, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
            if (obj == null) {
                jdPersonalMtaInterface.clickMta(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) == 0 ? str6 : "");
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clickMta");
        }

        public static /* synthetic */ void clickMtaExtMap$default(JdPersonalMtaInterface jdPersonalMtaInterface, Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap hashMap, int i2, Object obj) {
            if (obj == null) {
                jdPersonalMtaInterface.clickMtaExtMap(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? "" : str6, hashMap);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clickMtaExtMap");
        }

        public static /* synthetic */ void exposureMta$default(JdPersonalMtaInterface jdPersonalMtaInterface, Context context, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
            if (obj == null) {
                jdPersonalMtaInterface.exposureMta(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) == 0 ? str6 : "");
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposureMta");
        }

        public static /* synthetic */ void exposureMtaExtMap$default(JdPersonalMtaInterface jdPersonalMtaInterface, Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap hashMap, int i2, Object obj) {
            if (obj == null) {
                jdPersonalMtaInterface.exposureMtaExtMap(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? "" : str6, hashMap);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposureMtaExtMap");
        }
    }

    void clickMta(@Nullable Context context, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String pageName, @Nullable String pageParam, @Nullable String jsonParam);

    void clickMtaExtMap(@Nullable Context context, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String pageName, @Nullable String pageParam, @Nullable String jsonParam, @Nullable HashMap<String, String> ext);

    void exposureMta(@Nullable Context context, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String pageName, @Nullable String pageParam, @Nullable String jsonParam);

    void exposureMtaExtMap(@Nullable Context context, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String pageName, @Nullable String pageParam, @Nullable String jsonParam, @Nullable HashMap<String, String> ext);
}
