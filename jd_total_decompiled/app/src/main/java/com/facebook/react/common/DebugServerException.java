package com.facebook.react.common;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.devsupport.StackTraceHelper;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DebugServerException extends RuntimeException {
    private static final String GENERIC_ERROR_MESSAGE = "\n\nTry the following to fix the issue:\n\u2022 Ensure that the packager server is running\n\u2022 Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n\u2022 Ensure Airplane Mode is disabled\n\u2022 If you're on a physical device connected to the same machine, run 'adb reverse tcp:8081 tcp:8081' to forward requests from your device\n\u2022 If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:8081\n\n";

    private DebugServerException(String str, String str2, int i2, int i3) {
        super(str + "\n  at " + str2 + ":" + i2 + ":" + i3);
    }

    public static DebugServerException makeGeneric(String str, Throwable th) {
        return makeGeneric(str, "", th);
    }

    @Nullable
    public static DebugServerException parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new DebugServerException(jSONObject.getString("description"), shortenFileName(jSONObject.getString(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME)), jSONObject.getInt(StackTraceHelper.LINE_NUMBER_KEY), jSONObject.getInt("column"));
        } catch (JSONException e2) {
            FLog.w(ReactConstants.TAG, "Could not parse DebugServerException from: " + str, e2);
            return null;
        }
    }

    private static String shortenFileName(String str) {
        return str.split("/")[r1.length - 1];
    }

    public DebugServerException(String str) {
        super(str);
    }

    public static DebugServerException makeGeneric(String str, String str2, Throwable th) {
        return new DebugServerException(str + GENERIC_ERROR_MESSAGE + str2, th);
    }

    public DebugServerException(String str, Throwable th) {
        super(str, th);
    }
}
