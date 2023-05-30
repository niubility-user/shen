package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.entity.WebLogEntity;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/* loaded from: classes12.dex */
public class WebLogHelper {
    public static final int LVL_DEBUG = 0;
    public static final int LVL_DISABLE = -1;
    public static final int LVL_ERROR = 4;
    public static final int LVL_LOG = 2;
    public static final int LVL_TIP = 1;
    public static final int LVL_WARNING = 3;
    public static final int sJsReportLevel = getLegalLvlInt(SwitchQueryFetcher.getSwitchIntValue("webJsLogLevel", -1));
    public static boolean showXLog;
    private final String TAG;
    private int actualJsReportLevel;
    private final int greyJsReportLevel;
    private final Map<String, WebLogEntity> logData;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.web.util.WebLogHelper$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel;

        static {
            int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
            $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel = iArr;
            try {
                iArr[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.TIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WebLogHelper() {
        int legalLvlInt = getLegalLvlInt(SwitchQueryFetcher.getSwitchIntValue("webJsLogLevel2", -1));
        this.greyJsReportLevel = legalLvlInt;
        this.TAG = "WebLog";
        this.actualJsReportLevel = -1;
        this.logData = new ConcurrentHashMap();
        int i2 = sJsReportLevel;
        if (i2 > -1) {
            if (legalLvlInt > -1) {
                this.actualJsReportLevel = legalLvlInt;
            } else {
                this.actualJsReportLevel = i2;
            }
        }
        Log.d("WebLog", "The JS message minimum report level is " + this.actualJsReportLevel);
    }

    private int getJsMsgLvlInt(ConsoleMessage.MessageLevel messageLevel) {
        if (messageLevel != null) {
            int i2 = AnonymousClass2.$SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[messageLevel.ordinal()];
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return i2 != 5 ? 0 : 4;
                    }
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    private static int getLegalLvlInt(int i2) {
        if (i2 < -1 || i2 > 4) {
            return -1;
        }
        return i2;
    }

    public void addJsLog(final String str, final ConsoleMessage consoleMessage) {
        if (sJsReportLevel <= -1 || TextUtils.isEmpty(str) || consoleMessage == null) {
            return;
        }
        try {
            boolean z = true;
            if (OKLog.D) {
                Locale locale = Locale.CHINA;
                Object[] objArr = new Object[4];
                objArr[0] = consoleMessage.messageLevel() != null ? consoleMessage.messageLevel().name() : "Null_Lvl";
                objArr[1] = consoleMessage.message();
                objArr[2] = consoleMessage.sourceId();
                objArr[3] = Integer.valueOf(consoleMessage.lineNumber());
                OKLog.d("WebLog", String.format(locale, "[%s]: %s (%s:%d)", objArr));
            }
            if (getJsMsgLvlInt(consoleMessage.messageLevel()) < this.actualJsReportLevel) {
                z = false;
            }
            if (z) {
                if (BaseInfo.getAndroidSDKVersion() >= 24) {
                    this.logData.compute(str, new BiFunction<String, WebLogEntity, WebLogEntity>() { // from class: com.jingdong.common.web.util.WebLogHelper.1
                        @Override // java.util.function.BiFunction
                        public WebLogEntity apply(String str2, WebLogEntity webLogEntity) {
                            if (webLogEntity == null) {
                                webLogEntity = new WebLogEntity(str);
                            }
                            webLogEntity.addJsLog(consoleMessage);
                            return webLogEntity;
                        }
                    });
                    return;
                }
                synchronized (this.logData) {
                    WebLogEntity webLogEntity = this.logData.get(str);
                    if (webLogEntity == null) {
                        webLogEntity = new WebLogEntity(str);
                    }
                    webLogEntity.addJsLog(consoleMessage);
                    this.logData.put(str, webLogEntity);
                }
            }
        } catch (Exception e2) {
            Log.e("WebLog", e2.getMessage(), e2);
        }
    }

    public void reportLogs() {
        if (sJsReportLevel <= -1) {
            return;
        }
        if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("Report web logs. There are ");
            Map<String, WebLogEntity> map = this.logData;
            sb.append(map != null ? map.size() : 0);
            sb.append(" un_uploaded logs");
            Log.d("WebLog", sb.toString());
        }
        Map<String, WebLogEntity> map2 = this.logData;
        if (map2 == null || map2.isEmpty()) {
            return;
        }
        synchronized (this.logData) {
            try {
                Iterator<WebLogEntity> it = this.logData.values().iterator();
                while (it.hasNext()) {
                    WebLogEntity next = it.next();
                    if (next != null) {
                        next.reportLogs();
                    }
                    it.remove();
                }
            } catch (Exception e2) {
                Log.e("WebLog", e2.getMessage(), e2);
            }
        }
    }
}
