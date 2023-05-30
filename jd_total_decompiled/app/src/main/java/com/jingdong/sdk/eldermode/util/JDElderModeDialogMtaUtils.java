package com.jingdong.sdk.eldermode.util;

import com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000\u00a2\u0006\u0004\b\f\u0010\nR\u0016\u0010\u000e\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00048\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000fR$\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\nR\u0016\u0010\u0018\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/sdk/eldermode/util/JDElderModeDialogMtaUtils;", "", "", "mtaType", "", "eventId", "", "send", "(Ljava/lang/Integer;Ljava/lang/String;)V", "click$eldermodelib", "(Ljava/lang/String;)V", "click", "exposure$eldermodelib", "exposure", "EVENT_ID_ELDERPOP_CHANGE", "Ljava/lang/String;", "EVENT_ID_ELDERPOP_DETAIL", "EVENT_ID_ELDERPOP_CLOSE", "EVENT_ID_ELDERPOP_NO", "SOURCE_DEFAULT", "source", "getSource$eldermodelib", "()Ljava/lang/String;", "setSource$eldermodelib", "EVENT_ID_ELDERPOP_EXPO", "<init>", "()V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class JDElderModeDialogMtaUtils {
    @NotNull
    public static final String EVENT_ID_ELDERPOP_CHANGE = "ElderPop_Change";
    @NotNull
    public static final String EVENT_ID_ELDERPOP_CLOSE = "ElderPop_Close";
    @NotNull
    public static final String EVENT_ID_ELDERPOP_DETAIL = "ElderPop_Detail";
    @NotNull
    public static final String EVENT_ID_ELDERPOP_EXPO = "ElderPop_Expo";
    @NotNull
    public static final String EVENT_ID_ELDERPOP_NO = "ElderPop_No";
    public static final JDElderModeDialogMtaUtils INSTANCE = new JDElderModeDialogMtaUtils();
    private static final String SOURCE_DEFAULT = "qita";
    @Nullable
    private static String source;

    private JDElderModeDialogMtaUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (r1 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void send(Integer mtaType, String eventId) {
        IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
        if (helper != null) {
            String str = source;
            if (str != null) {
                if (!(str.length() > 0)) {
                    str = null;
                }
            }
            str = SOURCE_DEFAULT;
            helper.sendMtaInfo(new ElderModeMtaInfo(mtaType, eventId, null, null, null, null, "{\"source\":\"" + str + "\"}", 60, null));
        }
    }

    public final void click$eldermodelib(@Nullable String eventId) {
        send(0, eventId);
    }

    public final void exposure$eldermodelib(@Nullable String eventId) {
        send(1, eventId);
    }

    @Nullable
    public final String getSource$eldermodelib() {
        return source;
    }

    public final void setSource$eldermodelib(@Nullable String str) {
        source = str;
    }
}
