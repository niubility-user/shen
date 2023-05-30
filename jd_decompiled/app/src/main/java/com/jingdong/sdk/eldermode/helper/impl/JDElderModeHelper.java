package com.jingdong.sdk.eldermode.helper.impl;

import android.content.Context;
import android.view.View;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.e;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.eldermode.entity.ElderModeMtaInfo;
import com.jingdong.sdk.eldermode.helper.IElderModeCache;
import com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig;
import com.jingdong.sdk.eldermode.helper.IElderModeDialog;
import com.jingdong.sdk.eldermode.helper.IElderModeExceptionHandler;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import com.jingdong.sdk.eldermode.helper.IElderModeLogger;
import com.jingdong.sdk.eldermode.helper.IElderModeMtaSender;
import com.jingdong.sdk.eldermode.helper.IElderModeNetworkController;
import com.jingdong.sdk.eldermode.helper.IElderModeOverseasConfig;
import com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig;
import com.jingdong.sdk.eldermode.helper.IElderModeToast;
import com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001zB\t\b\u0012\u00a2\u0006\u0004\bv\u0010=B\u0011\b\u0012\u0012\u0006\u0010x\u001a\u00020w\u00a2\u0006\u0004\bv\u0010yJ\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0011J\u001f\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u001aH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010!\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0083\u0001\u0010-\u001a\u00020\n2\u0006\u0010#\u001a\u00020\b2\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010$2\u0006\u0010&\u001a\u00020\u00152%\u0010+\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\n\u0018\u00010'2%\u0010,\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u001f\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\n\u0018\u00010'H\u0016\u00a2\u0006\u0004\b-\u0010.J\u0019\u00101\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010/H\u0016\u00a2\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u0002032\u0006\u00104\u001a\u000203H\u0016\u00a2\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u00020\n2\u0006\u00109\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b:\u0010\fJ\u000f\u0010;\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b;\u0010\u0007J\u000f\u0010<\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b>\u0010\u0007J\u0017\u0010@\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010B\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\bB\u0010AJ-\u0010F\u001a\u00020\u00052\b\u0010?\u001a\u0004\u0018\u00010\u00022\b\u0010D\u001a\u0004\u0018\u00010C2\b\u0010E\u001a\u0004\u0018\u00010CH\u0016\u00a2\u0006\u0004\bF\u0010GJ-\u0010H\u001a\u00020\u00052\b\u0010?\u001a\u0004\u0018\u00010\u00022\b\u0010D\u001a\u0004\u0018\u00010C2\b\u0010E\u001a\u0004\u0018\u00010CH\u0016\u00a2\u0006\u0004\bH\u0010GJ\u0017\u0010I\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\bI\u0010\fR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010O\u001a\u00020\u00158V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bM\u0010NR\u0018\u0010Q\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR$\u0010S\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00058V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\bS\u0010\u0007\"\u0004\bT\u0010UR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0018\u0010]\u001a\u0004\u0018\u00010\\8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010`\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010e\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0018\u0010h\u001a\u0004\u0018\u00010g8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010k\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0018\u0010n\u001a\u0004\u0018\u00010m8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0018\u0010q\u001a\u0004\u0018\u00010p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0018\u0010?\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010sR$\u0010t\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00058V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\bt\u0010\u0007\"\u0004\bu\u0010U\u00a8\u0006{"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeHelper;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "isDebug", "()Z", "", "msg", "", "log", "(Ljava/lang/String;)V", "key", "getString", "(Ljava/lang/String;)Ljava/lang/String;", "defaultValue", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "value", "putString", "(Ljava/lang/String;Ljava/lang/String;)V", "", "getInt", "(Ljava/lang/String;)I", "putInt", "(Ljava/lang/String;I)V", "", "getLong", "(Ljava/lang/String;)J", "putLong", "(Ljava/lang/String;J)V", "", e.a, "handleException", "(Ljava/lang/Throwable;)V", "functionId", "", "params", "callTimeout", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "response", "success", "fail", "request", "(Ljava/lang/String;Ljava/util/Map;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;", "mtaInfo", "sendMtaInfo", "(Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;)V", "", "normalSize", "getScaleSize", "(F)F", "getTextSizeScaleMode", "()Ljava/lang/String;", "mode", "setTextSizeScaleMode", "isLargeSizeMode", "setTextSizeToLargeMode", "()V", "hasLogin", AnnoConst.Constructor_Context, "registerLoginReceiver", "(Landroid/content/Context;)V", "unregisterLoginReceiver", "Landroid/view/View$OnClickListener;", "onOkButtonClickListener", "onCancelButtonClickListener", "showElderModeDialog", "(Landroid/content/Context;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "showNormalModeDialog", "showToast", "Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "mtaSender", "Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "getOverseasArea", "()I", "overseasArea", "Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "userInfoConfig", "Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "isDarkMode", "setDarkMode", "(Z)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "darkModeConfig", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", XView2Constants.XVIEW2_ACTION_DIALOG, "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "exceptionHandler", "Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", XView2Constants.XVIEW2_ACTION_TOAST, "Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "overseasConfig", "Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "debug", "Ljava/lang/Boolean;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "cache", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "logger", "Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "networkController", "Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "textSizeConfig", "Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "Landroid/content/Context;", "isDarkModeFollowSystem", "setDarkModeFollowSystem", "<init>", "Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "builder", "(Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;)V", "Builder", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class JDElderModeHelper implements IElderModeHelper {
    private IElderModeCache cache;
    private Context context;
    private IElderModeDarkModeConfig darkModeConfig;
    private Boolean debug;
    private IElderModeDialog dialog;
    private IElderModeExceptionHandler exceptionHandler;
    private IElderModeLogger logger;
    private IElderModeMtaSender mtaSender;
    private IElderModeNetworkController networkController;
    private IElderModeOverseasConfig overseasConfig;
    private IElderModeTextSizeConfig textSizeConfig;
    private IElderModeToast toast;
    private IElderModeUserInfoConfig userInfoConfig;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\\\u001a\u00020[\u00a2\u0006\u0004\bj\u0010kJ\u0015\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020 \u00a2\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020#\u00a2\u0006\u0004\b$\u0010%J\r\u0010'\u001a\u00020&\u00a2\u0006\u0004\b'\u0010(R$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R$\u0010!\u001a\u0004\u0018\u00010 8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b!\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR$\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u0019\u0010\\\u001a\u00020[8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_R$\u0010$\u001a\u0004\u0018\u00010#8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010`\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010i\u00a8\u0006l"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "", "", "debug", "(Z)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "cache", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", XView2Constants.XVIEW2_ACTION_DIALOG, "(Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", XView2Constants.XVIEW2_ACTION_TOAST, "(Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "logger", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "exceptionHandler", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "networkController", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "mtaSender", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "darkModeConfig", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "textSizeConfig", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "overseasConfig", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "userInfoConfig", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;)Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper$Builder;", "Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper;", HybridSDK.APP_VERSION_CODE, "()Lcom/jingdong/sdk/eldermode/helper/impl/JDElderModeHelper;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "getLogger$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;", "setLogger$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeLogger;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "getDialog$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "setDialog$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "getNetworkController$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "setNetworkController$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "getOverseasConfig$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;", "setOverseasConfig$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeOverseasConfig;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "getTextSizeConfig$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;", "setTextSizeConfig$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeTextSizeConfig;)V", "Ljava/lang/Boolean;", "getDebug$eldermodelib", "()Ljava/lang/Boolean;", "setDebug$eldermodelib", "(Ljava/lang/Boolean;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "getMtaSender$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;", "setMtaSender$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeMtaSender;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "getCache$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;", "setCache$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeCache;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "getDarkModeConfig$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;", "setDarkModeConfig$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeDarkModeConfig;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "getExceptionHandler$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;", "setExceptionHandler$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeExceptionHandler;)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "getUserInfoConfig$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;", "setUserInfoConfig$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeUserInfoConfig;)V", "Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", "getToast$eldermodelib", "()Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;", "setToast$eldermodelib", "(Lcom/jingdong/sdk/eldermode/helper/IElderModeToast;)V", "<init>", "(Landroid/content/Context;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class Builder {
        @Nullable
        private IElderModeCache cache;
        @NotNull
        private final Context context;
        @Nullable
        private IElderModeDarkModeConfig darkModeConfig;
        @Nullable
        private Boolean debug;
        @Nullable
        private IElderModeDialog dialog;
        @Nullable
        private IElderModeExceptionHandler exceptionHandler;
        @Nullable
        private IElderModeLogger logger;
        @Nullable
        private IElderModeMtaSender mtaSender;
        @Nullable
        private IElderModeNetworkController networkController;
        @Nullable
        private IElderModeOverseasConfig overseasConfig;
        @Nullable
        private IElderModeTextSizeConfig textSizeConfig;
        @Nullable
        private IElderModeToast toast;
        @Nullable
        private IElderModeUserInfoConfig userInfoConfig;

        public Builder(@NotNull Context context) {
            this.context = context;
        }

        @NotNull
        public final JDElderModeHelper build() {
            return new JDElderModeHelper(this, null);
        }

        @NotNull
        public final Builder cache(@NotNull IElderModeCache cache) {
            this.cache = cache;
            return this;
        }

        @NotNull
        public final Builder darkModeConfig(@NotNull IElderModeDarkModeConfig darkModeConfig) {
            this.darkModeConfig = darkModeConfig;
            return this;
        }

        @NotNull
        public final Builder debug(boolean debug) {
            this.debug = Boolean.valueOf(debug);
            return this;
        }

        @NotNull
        public final Builder dialog(@NotNull IElderModeDialog dialog) {
            this.dialog = dialog;
            return this;
        }

        @NotNull
        public final Builder exceptionHandler(@NotNull IElderModeExceptionHandler exceptionHandler) {
            this.exceptionHandler = exceptionHandler;
            return this;
        }

        @Nullable
        /* renamed from: getCache$eldermodelib  reason: from getter */
        public final IElderModeCache getCache() {
            return this.cache;
        }

        @NotNull
        public final Context getContext() {
            return this.context;
        }

        @Nullable
        /* renamed from: getDarkModeConfig$eldermodelib  reason: from getter */
        public final IElderModeDarkModeConfig getDarkModeConfig() {
            return this.darkModeConfig;
        }

        @Nullable
        /* renamed from: getDebug$eldermodelib  reason: from getter */
        public final Boolean getDebug() {
            return this.debug;
        }

        @Nullable
        /* renamed from: getDialog$eldermodelib  reason: from getter */
        public final IElderModeDialog getDialog() {
            return this.dialog;
        }

        @Nullable
        /* renamed from: getExceptionHandler$eldermodelib  reason: from getter */
        public final IElderModeExceptionHandler getExceptionHandler() {
            return this.exceptionHandler;
        }

        @Nullable
        /* renamed from: getLogger$eldermodelib  reason: from getter */
        public final IElderModeLogger getLogger() {
            return this.logger;
        }

        @Nullable
        /* renamed from: getMtaSender$eldermodelib  reason: from getter */
        public final IElderModeMtaSender getMtaSender() {
            return this.mtaSender;
        }

        @Nullable
        /* renamed from: getNetworkController$eldermodelib  reason: from getter */
        public final IElderModeNetworkController getNetworkController() {
            return this.networkController;
        }

        @Nullable
        /* renamed from: getOverseasConfig$eldermodelib  reason: from getter */
        public final IElderModeOverseasConfig getOverseasConfig() {
            return this.overseasConfig;
        }

        @Nullable
        /* renamed from: getTextSizeConfig$eldermodelib  reason: from getter */
        public final IElderModeTextSizeConfig getTextSizeConfig() {
            return this.textSizeConfig;
        }

        @Nullable
        /* renamed from: getToast$eldermodelib  reason: from getter */
        public final IElderModeToast getToast() {
            return this.toast;
        }

        @Nullable
        /* renamed from: getUserInfoConfig$eldermodelib  reason: from getter */
        public final IElderModeUserInfoConfig getUserInfoConfig() {
            return this.userInfoConfig;
        }

        @NotNull
        public final Builder logger(@NotNull IElderModeLogger logger) {
            this.logger = logger;
            return this;
        }

        @NotNull
        public final Builder mtaSender(@NotNull IElderModeMtaSender mtaSender) {
            this.mtaSender = mtaSender;
            return this;
        }

        @NotNull
        public final Builder networkController(@NotNull IElderModeNetworkController networkController) {
            this.networkController = networkController;
            return this;
        }

        @NotNull
        public final Builder overseasConfig(@NotNull IElderModeOverseasConfig overseasConfig) {
            this.overseasConfig = overseasConfig;
            return this;
        }

        public final void setCache$eldermodelib(@Nullable IElderModeCache iElderModeCache) {
            this.cache = iElderModeCache;
        }

        public final void setDarkModeConfig$eldermodelib(@Nullable IElderModeDarkModeConfig iElderModeDarkModeConfig) {
            this.darkModeConfig = iElderModeDarkModeConfig;
        }

        public final void setDebug$eldermodelib(@Nullable Boolean bool) {
            this.debug = bool;
        }

        public final void setDialog$eldermodelib(@Nullable IElderModeDialog iElderModeDialog) {
            this.dialog = iElderModeDialog;
        }

        public final void setExceptionHandler$eldermodelib(@Nullable IElderModeExceptionHandler iElderModeExceptionHandler) {
            this.exceptionHandler = iElderModeExceptionHandler;
        }

        public final void setLogger$eldermodelib(@Nullable IElderModeLogger iElderModeLogger) {
            this.logger = iElderModeLogger;
        }

        public final void setMtaSender$eldermodelib(@Nullable IElderModeMtaSender iElderModeMtaSender) {
            this.mtaSender = iElderModeMtaSender;
        }

        public final void setNetworkController$eldermodelib(@Nullable IElderModeNetworkController iElderModeNetworkController) {
            this.networkController = iElderModeNetworkController;
        }

        public final void setOverseasConfig$eldermodelib(@Nullable IElderModeOverseasConfig iElderModeOverseasConfig) {
            this.overseasConfig = iElderModeOverseasConfig;
        }

        public final void setTextSizeConfig$eldermodelib(@Nullable IElderModeTextSizeConfig iElderModeTextSizeConfig) {
            this.textSizeConfig = iElderModeTextSizeConfig;
        }

        public final void setToast$eldermodelib(@Nullable IElderModeToast iElderModeToast) {
            this.toast = iElderModeToast;
        }

        public final void setUserInfoConfig$eldermodelib(@Nullable IElderModeUserInfoConfig iElderModeUserInfoConfig) {
            this.userInfoConfig = iElderModeUserInfoConfig;
        }

        @NotNull
        public final Builder textSizeConfig(@NotNull IElderModeTextSizeConfig textSizeConfig) {
            this.textSizeConfig = textSizeConfig;
            return this;
        }

        @NotNull
        public final Builder toast(@NotNull IElderModeToast toast) {
            this.toast = toast;
            return this;
        }

        @NotNull
        public final Builder userInfoConfig(@NotNull IElderModeUserInfoConfig userInfoConfig) {
            this.userInfoConfig = userInfoConfig;
            return this;
        }
    }

    public /* synthetic */ JDElderModeHelper(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeHelper
    @Nullable
    public Context getContext() {
        return this.context;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public int getInt(@NotNull String key) {
        IElderModeCache iElderModeCache = this.cache;
        if (iElderModeCache != null) {
            return iElderModeCache.getInt(key);
        }
        return 0;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public long getLong(@NotNull String key) {
        IElderModeCache iElderModeCache = this.cache;
        if (iElderModeCache != null) {
            return iElderModeCache.getLong(key);
        }
        return 0L;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeOverseasConfig
    public int getOverseasArea() {
        IElderModeOverseasConfig iElderModeOverseasConfig = this.overseasConfig;
        if (iElderModeOverseasConfig != null) {
            return iElderModeOverseasConfig.getOverseasArea();
        }
        return 0;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public float getScaleSize(float normalSize) {
        IElderModeTextSizeConfig iElderModeTextSizeConfig = this.textSizeConfig;
        return iElderModeTextSizeConfig != null ? iElderModeTextSizeConfig.getScaleSize(normalSize) : normalSize;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    @NotNull
    public String getString(@NotNull String key) {
        String string;
        IElderModeCache iElderModeCache = this.cache;
        return (iElderModeCache == null || (string = iElderModeCache.getString(key)) == null) ? "" : string;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    @NotNull
    public String getTextSizeScaleMode() {
        String textSizeScaleMode;
        IElderModeTextSizeConfig iElderModeTextSizeConfig = this.textSizeConfig;
        return (iElderModeTextSizeConfig == null || (textSizeScaleMode = iElderModeTextSizeConfig.getTextSizeScaleMode()) == null) ? "" : textSizeScaleMode;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeExceptionHandler
    public void handleException(@Nullable Throwable e2) {
        IElderModeExceptionHandler iElderModeExceptionHandler = this.exceptionHandler;
        if (iElderModeExceptionHandler != null) {
            iElderModeExceptionHandler.handleException(e2);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public boolean hasLogin() {
        IElderModeUserInfoConfig iElderModeUserInfoConfig = this.userInfoConfig;
        if (iElderModeUserInfoConfig != null) {
            return iElderModeUserInfoConfig.hasLogin();
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public boolean isDarkMode() {
        IElderModeDarkModeConfig iElderModeDarkModeConfig = this.darkModeConfig;
        if (iElderModeDarkModeConfig != null) {
            return iElderModeDarkModeConfig.isDarkMode();
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public boolean isDarkModeFollowSystem() {
        IElderModeDarkModeConfig iElderModeDarkModeConfig = this.darkModeConfig;
        if (iElderModeDarkModeConfig != null) {
            return iElderModeDarkModeConfig.isDarkModeFollowSystem();
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeHelper
    public boolean isDebug() {
        Boolean bool = this.debug;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public boolean isLargeSizeMode() {
        IElderModeTextSizeConfig iElderModeTextSizeConfig = this.textSizeConfig;
        if (iElderModeTextSizeConfig != null) {
            return iElderModeTextSizeConfig.isLargeSizeMode();
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeLogger
    public void log(@Nullable String msg) {
        IElderModeLogger iElderModeLogger = this.logger;
        if (iElderModeLogger != null) {
            iElderModeLogger.log(msg);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putInt(@NotNull String key, int value) {
        IElderModeCache iElderModeCache = this.cache;
        if (iElderModeCache != null) {
            iElderModeCache.putInt(key, value);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putLong(@NotNull String key, long value) {
        IElderModeCache iElderModeCache = this.cache;
        if (iElderModeCache != null) {
            iElderModeCache.putLong(key, value);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    public void putString(@NotNull String key, @NotNull String value) {
        IElderModeCache iElderModeCache = this.cache;
        if (iElderModeCache != null) {
            iElderModeCache.putString(key, value);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public void registerLoginReceiver(@NotNull Context context) {
        IElderModeUserInfoConfig iElderModeUserInfoConfig = this.userInfoConfig;
        if (iElderModeUserInfoConfig != null) {
            iElderModeUserInfoConfig.registerLoginReceiver(context);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeNetworkController
    public void request(@NotNull String functionId, @Nullable Map<String, String> params, int callTimeout, @Nullable Function1<? super String, Unit> success, @Nullable Function1<? super Throwable, Unit> fail) {
        IElderModeNetworkController iElderModeNetworkController = this.networkController;
        if (iElderModeNetworkController != null) {
            iElderModeNetworkController.request(functionId, params, callTimeout, success, fail);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeMtaSender
    public void sendMtaInfo(@Nullable ElderModeMtaInfo mtaInfo) {
        IElderModeMtaSender iElderModeMtaSender = this.mtaSender;
        if (iElderModeMtaSender != null) {
            iElderModeMtaSender.sendMtaInfo(mtaInfo);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public void setDarkMode(boolean z) {
        IElderModeDarkModeConfig iElderModeDarkModeConfig = this.darkModeConfig;
        if (iElderModeDarkModeConfig != null) {
            iElderModeDarkModeConfig.setDarkMode(z);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDarkModeConfig
    public void setDarkModeFollowSystem(boolean z) {
        IElderModeDarkModeConfig iElderModeDarkModeConfig = this.darkModeConfig;
        if (iElderModeDarkModeConfig != null) {
            iElderModeDarkModeConfig.setDarkModeFollowSystem(z);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public void setTextSizeScaleMode(@NotNull String mode) {
        IElderModeTextSizeConfig iElderModeTextSizeConfig = this.textSizeConfig;
        if (iElderModeTextSizeConfig != null) {
            iElderModeTextSizeConfig.setTextSizeScaleMode(mode);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeTextSizeConfig
    public void setTextSizeToLargeMode() {
        IElderModeTextSizeConfig iElderModeTextSizeConfig = this.textSizeConfig;
        if (iElderModeTextSizeConfig != null) {
            iElderModeTextSizeConfig.setTextSizeToLargeMode();
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDialog
    public boolean showElderModeDialog(@Nullable Context context, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener) {
        IElderModeDialog iElderModeDialog = this.dialog;
        if (iElderModeDialog != null) {
            return iElderModeDialog.showElderModeDialog(context, onOkButtonClickListener, onCancelButtonClickListener);
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDialog
    public boolean showNormalModeDialog(@Nullable Context context, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener) {
        IElderModeDialog iElderModeDialog = this.dialog;
        if (iElderModeDialog != null) {
            return iElderModeDialog.showNormalModeDialog(context, onOkButtonClickListener, onCancelButtonClickListener);
        }
        return false;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeToast
    public void showToast(@NotNull String msg) {
        IElderModeToast iElderModeToast = this.toast;
        if (iElderModeToast != null) {
            iElderModeToast.showToast(msg);
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeUserInfoConfig
    public void unregisterLoginReceiver(@NotNull Context context) {
        IElderModeUserInfoConfig iElderModeUserInfoConfig = this.userInfoConfig;
        if (iElderModeUserInfoConfig != null) {
            iElderModeUserInfoConfig.unregisterLoginReceiver(context);
        }
    }

    private JDElderModeHelper() {
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeCache
    @NotNull
    public String getString(@NotNull String key, @NotNull String defaultValue) {
        String string;
        IElderModeCache iElderModeCache = this.cache;
        return (iElderModeCache == null || (string = iElderModeCache.getString(key, defaultValue)) == null) ? "" : string;
    }

    private JDElderModeHelper(Builder builder) {
        Context applicationContext = builder.getContext().getApplicationContext();
        this.context = applicationContext == null ? builder.getContext() : applicationContext;
        this.debug = builder.getDebug();
        this.cache = builder.getCache();
        this.dialog = builder.getDialog();
        this.toast = builder.getToast();
        this.logger = builder.getLogger();
        this.exceptionHandler = builder.getExceptionHandler();
        this.networkController = builder.getNetworkController();
        this.mtaSender = builder.getMtaSender();
        this.darkModeConfig = builder.getDarkModeConfig();
        this.textSizeConfig = builder.getTextSizeConfig();
        this.overseasConfig = builder.getOverseasConfig();
        this.userInfoConfig = builder.getUserInfoConfig();
    }
}
