package com.jingdong.sdk.bmode.util;

import com.google.gson.Gson;
import com.jingdong.sdk.eldermode.entity.BModeSlimUserData;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 )2\u00020\u0001:\u0001)B\u0011\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u00a2\u0006\u0004\b'\u0010(J\u0019\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J-\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0016\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R$\u0010!\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006*"}, d2 = {"Lcom/jingdong/sdk/bmode/util/JDBModeDataHelper;", "", "", "callFromLogin", "", "handleBMode", "(Z)V", "", "adviseFinalVersion", "plus", "handleBModeAutoSwitch", "(ZLjava/lang/String;Ljava/lang/String;)V", "Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "slimUserData", "handleMtaData", "(Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;)V", "json", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "parseModeResponse", "(Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "", "operateType", "handleMajorResponse", "(Ljava/lang/String;Ljava/lang/Integer;Z)V", "needShowNormalModeDialog", "()Z", "getForbiddenVersionSwitch", "()Ljava/lang/String;", "clear", "()V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "helper", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "response", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "getResponse$eldermodelib", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "setResponse$eldermodelib", "(Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;)V", "<init>", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;)V", "Companion", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDBModeDataHelper {
    private static final String OPERATE_RESULT_FAIL = "-1";
    private static final String OPERATE_RESULT_SUCCESS = "0";
    private final IElderModeHelper helper;
    @Nullable
    private ElderModeResponse response;

    public JDBModeDataHelper(@Nullable IElderModeHelper iElderModeHelper) {
        this.helper = iElderModeHelper;
    }

    private final void handleBMode(boolean callFromLogin) {
        BModeSlimUserData slimUserData;
        ElderModeResponse elderModeResponse = this.response;
        if (elderModeResponse == null || (slimUserData = elderModeResponse.getSlimUserData()) == null) {
            return;
        }
        String adviseFinalMode = slimUserData.getAdviseFinalMode();
        if (adviseFinalMode != null && adviseFinalMode.hashCode() == 48 && adviseFinalMode.equals("0")) {
            handleBModeAutoSwitch(callFromLogin, slimUserData.getAdviseFinalVersion(), slimUserData.getPlus());
        }
        String slimReportResult = slimUserData.getSlimReportResult();
        if (slimReportResult != null) {
            int hashCode = slimReportResult.hashCode();
            if (hashCode != 48) {
                if (hashCode == 1444) {
                    slimReportResult.equals("-1");
                }
            } else if (slimReportResult.equals("0")) {
                JDBModeManager.INSTANCE.setReportAutoMode("");
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.log("B\u7248\u6e05\u7a7a\u4e0a\u62a5\u624b\u52a8/\u81ea\u52a8\u5207\u6362\u6a21\u5f0f\u7f13\u5b58");
                }
            }
        }
        handleMtaData(slimUserData);
    }

    static /* synthetic */ void handleBMode$default(JDBModeDataHelper jDBModeDataHelper, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        jDBModeDataHelper.handleBMode(z);
    }

    private final void handleBModeAutoSwitch(boolean callFromLogin, String adviseFinalVersion, String plus) {
        JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
        String currentMode = jDBModeManager.getCurrentMode();
        String str = "1";
        switch (currentMode.hashCode()) {
            case 48:
                if (!currentMode.equals("0") || adviseFinalVersion == null) {
                    return;
                }
                int hashCode = adviseFinalVersion.hashCode();
                if (hashCode != 48) {
                    if (hashCode == 50 && adviseFinalVersion.equals("2")) {
                        jDBModeManager.switchMode("2", "0", callFromLogin);
                        return;
                    }
                    return;
                }
                adviseFinalVersion.equals("0");
                return;
            case 49:
                currentMode.equals("1");
                return;
            case 50:
                if (!currentMode.equals("2") || adviseFinalVersion == null) {
                    return;
                }
                int hashCode2 = adviseFinalVersion.hashCode();
                if (hashCode2 != 48) {
                    if (hashCode2 != 50) {
                        return;
                    }
                    adviseFinalVersion.equals("2");
                    return;
                } else if (adviseFinalVersion.equals("0")) {
                    if (Intrinsics.areEqual(plus, "1")) {
                        IElderModeHelper iElderModeHelper = this.helper;
                        if (iElderModeHelper != null) {
                            iElderModeHelper.log("PLUS\u4f1a\u5458\uff0c\u5207\u6362\u6a21\u5f0f\u7f6e\u4e3a\u624b\u52a8\uff0c\u5728\u64cd\u4f5cB\u7248\u540c\u6b65\u7248\u672c\u670d\u52a1\u65f6\u4f7f\u7528");
                        }
                    } else {
                        str = "0";
                    }
                    jDBModeManager.switchMode("0", str, callFromLogin);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    static /* synthetic */ void handleBModeAutoSwitch$default(JDBModeDataHelper jDBModeDataHelper, boolean z, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        jDBModeDataHelper.handleBModeAutoSwitch(z, str, str2);
    }

    public static /* synthetic */ void handleMajorResponse$default(JDBModeDataHelper jDBModeDataHelper, String str, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        jDBModeDataHelper.handleMajorResponse(str, num, z);
    }

    private final void handleMtaData(BModeSlimUserData slimUserData) {
        JDBModeManager jDBModeManager = JDBModeManager.INSTANCE;
        String adviseVersion = slimUserData.getAdviseVersion();
        if (adviseVersion == null) {
            adviseVersion = "999";
        }
        jDBModeManager.setAdviseVersion(adviseVersion);
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            String buriedExpLabel = slimUserData.getBuriedExpLabel();
            if (buriedExpLabel == null) {
                buriedExpLabel = "";
            }
            iElderModeHelper.putString(JDBModeManager.CACHE_KEY_MODE_BURIED_EXPLABEL, buriedExpLabel);
        }
        IElderModeHelper iElderModeHelper2 = this.helper;
        if (iElderModeHelper2 != null) {
            String populationType = slimUserData.getPopulationType();
            iElderModeHelper2.putString(JDBModeManager.CACHE_KEY_MODE_POPULATION_TYPE, populationType != null ? populationType : "999");
        }
    }

    private final ElderModeResponse parseModeResponse(String json) {
        ElderModeResponse elderModeResponse = null;
        if (json != null) {
            try {
                return (ElderModeResponse) new Gson().fromJson(json, ElderModeResponse.class);
            } catch (Throwable th) {
                IElderModeHelper iElderModeHelper = this.helper;
                if (iElderModeHelper != null) {
                    iElderModeHelper.handleException(new IllegalArgumentException("fromJson fail first time", th));
                }
                try {
                    elderModeResponse = (ElderModeResponse) new Gson().fromJson(json, ElderModeResponse.class);
                } catch (Throwable th2) {
                    IElderModeHelper iElderModeHelper2 = this.helper;
                    if (iElderModeHelper2 != null) {
                        iElderModeHelper2.handleException(new IllegalArgumentException("fromJson fail second time", th2));
                    }
                }
                return elderModeResponse;
            }
        }
        return null;
    }

    public final void clear() {
    }

    @NotNull
    public final String getForbiddenVersionSwitch() {
        BModeSlimUserData slimUserData;
        String forbiddenVersionSwitch;
        ElderModeResponse elderModeResponse = this.response;
        return (elderModeResponse == null || (slimUserData = elderModeResponse.getSlimUserData()) == null || (forbiddenVersionSwitch = slimUserData.getForbiddenVersionSwitch()) == null) ? "999" : forbiddenVersionSwitch;
    }

    @Nullable
    /* renamed from: getResponse$eldermodelib  reason: from getter */
    public final ElderModeResponse getResponse() {
        return this.response;
    }

    public final void handleMajorResponse(@Nullable String json, @Nullable Integer operateType, boolean callFromLogin) {
        IElderModeHelper iElderModeHelper = this.helper;
        if (iElderModeHelper != null) {
            iElderModeHelper.log("B\u7248\u8bf7\u6c42\u7248\u672c\u5207\u6362\u670d\u52a1\u8fd4\u56de\u503c\uff1a" + json);
        }
        ElderModeResponse parseModeResponse = parseModeResponse(json);
        if (parseModeResponse != null) {
            if ((!Intrinsics.areEqual(parseModeResponse.getCode(), "0")) != false) {
                IElderModeHelper iElderModeHelper2 = this.helper;
                if (iElderModeHelper2 != null) {
                    iElderModeHelper2.log("B\u7248\u8bf7\u6c42\u7248\u672c\u5207\u6362\u670d\u52a1\u8fd4\u56de code \u5931\u8d25");
                    return;
                }
                return;
            }
            this.response = parseModeResponse;
            IElderModeHelper iElderModeHelper3 = this.helper;
            if (iElderModeHelper3 != null) {
                iElderModeHelper3.log("B\u7248\u8bf7\u6c42\u7248\u672c\u5207\u6362\u670d\u52a1\u8fd4\u56de\u64cd\u4f5c\u7c7b\u578b\uff1a" + operateType);
            }
            handleBMode(callFromLogin);
            return;
        }
        IElderModeHelper iElderModeHelper4 = this.helper;
        if (iElderModeHelper4 != null) {
            iElderModeHelper4.log("B\u7248\u8bf7\u6c42\u7248\u672c\u5207\u6362\u670d\u52a1\u8fd4\u56de response \u4e3a null");
        }
    }

    public final boolean needShowNormalModeDialog() {
        BModeSlimUserData slimUserData;
        ElderModeResponse elderModeResponse = this.response;
        if (elderModeResponse == null || (slimUserData = elderModeResponse.getSlimUserData()) == null || !Intrinsics.areEqual(slimUserData.getWindows(), "1") || slimUserData.getSlimStandardInfo() == null) {
            return false;
        }
        IElderModeHelper iElderModeHelper = this.helper;
        return iElderModeHelper == null || iElderModeHelper.getInt(JDBModeManager.CACHE_KEY_MODE_SWITCH_DIALOG_HAS_SHOW) != 1;
    }

    public final void setResponse$eldermodelib(@Nullable ElderModeResponse elderModeResponse) {
        this.response = elderModeResponse;
    }
}
