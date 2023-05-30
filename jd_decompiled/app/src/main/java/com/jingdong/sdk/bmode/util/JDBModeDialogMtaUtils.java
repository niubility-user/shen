package com.jingdong.sdk.bmode.util;

import com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000\u00a2\u0006\u0004\b\f\u0010\nR\u0016\u0010\u000e\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\u00048\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000f\u00a8\u0006\u0015"}, d2 = {"Lcom/jingdong/sdk/bmode/util/JDBModeDialogMtaUtils;", "", "", "mtaType", "", "eventId", "", "send", "(Ljava/lang/Integer;Ljava/lang/String;)V", "click$eldermodelib", "(Ljava/lang/String;)V", "click", "exposure$eldermodelib", "exposure", "EVENT_ID_APP_POPUPUPGRADETOAEXPO", "Ljava/lang/String;", "EVENT_ID_APP_POPUPUPGRADETOA", "EVENT_ID_APP_POPUPUPGRADETOACLOSE", "EVENT_ID_APP_POPUPUPGRADENOTTOA", "<init>", "()V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class JDBModeDialogMtaUtils {
    @NotNull
    public static final String EVENT_ID_APP_POPUPUPGRADENOTTOA = "APP_PopupUpgradeNotToA";
    @NotNull
    public static final String EVENT_ID_APP_POPUPUPGRADETOA = "APP_PopupUpgradeToA";
    @NotNull
    public static final String EVENT_ID_APP_POPUPUPGRADETOACLOSE = "APP_PopupUpgradeToAClose";
    @NotNull
    public static final String EVENT_ID_APP_POPUPUPGRADETOAEXPO = "APP_PopupUpgradeToAExpo";
    public static final JDBModeDialogMtaUtils INSTANCE = new JDBModeDialogMtaUtils();

    private JDBModeDialogMtaUtils() {
    }

    private final void send(Integer mtaType, String eventId) {
        IElderModeHelper helper = JDBModeManager.INSTANCE.getHelper();
        if (helper != null) {
            helper.sendMtaInfo(new ElderModeMtaInfo(mtaType, eventId, null, null, null, null, null, 124, null));
        }
    }

    public final void click$eldermodelib(@Nullable String eventId) {
        send(0, eventId);
    }

    public final void exposure$eldermodelib(@Nullable String eventId) {
        send(1, eventId);
    }
}
