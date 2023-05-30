package com.jingdong.sdk.eldermode.util;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void send(java.lang.Integer r14, java.lang.String r15) {
        /*
            r13 = this;
            com.jingdong.sdk.eldermode.util.JDElderModeManager r0 = com.jingdong.sdk.eldermode.util.JDElderModeManager.INSTANCE
            com.jingdong.sdk.eldermode.helper.IElderModeHelper r0 = r0.getHelper()
            if (r0 == 0) goto L46
            java.lang.String r1 = com.jingdong.sdk.eldermode.util.JDElderModeDialogMtaUtils.source
            if (r1 == 0) goto L1c
            int r2 = r1.length()
            if (r2 <= 0) goto L14
            r2 = 1
            goto L15
        L14:
            r2 = 0
        L15:
            if (r2 == 0) goto L18
            goto L19
        L18:
            r1 = 0
        L19:
            if (r1 == 0) goto L1c
            goto L1e
        L1c:
            java.lang.String r1 = "qita"
        L1e:
            com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo r12 = new com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "{\"source\":\""
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "\"}"
            r2.append(r1)
            java.lang.String r9 = r2.toString()
            r10 = 60
            r11 = 0
            r2 = r12
            r3 = r14
            r4 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r0.sendMtaInfo(r12)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.eldermode.util.JDElderModeDialogMtaUtils.send(java.lang.Integer, java.lang.String):void");
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
