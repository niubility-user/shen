package com.facebook.react.devsupport;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.devsupport.BundleDeltaClient;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.InspectorPackagerConnection;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.FileIoHandler;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.packagerconnection.RequestOnlyHandler;
import com.facebook.react.packagerconnection.Responder;
import com.jdpay.net.http.HTTP;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Okio;
import okio.Sink;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class DevServerHelper {
    private static final String DEBUGGER_MSG_DISABLE = "{ \"id\":1,\"method\":\"Debugger.disable\" }";
    private static final int HTTP_CONNECT_TIMEOUT_MS = 5000;
    private static final int LONG_POLL_FAILURE_DELAY_MS = 5000;
    private static final int LONG_POLL_KEEP_ALIVE_DURATION_MS = 120000;
    private static final String PACKAGER_OK_STATUS = "packager-status:running";
    public static final String RELOAD_APP_EXTRA_JS_PROXY = "jsproxy";
    private final BundleDownloader mBundleDownloader;
    private InspectorPackagerConnection.BundleStatusProvider mBundlerStatusProvider;
    private final OkHttpClient mClient;
    @Nullable
    private InspectorPackagerConnection mInspectorPackagerConnection;
    @Nullable
    private OkHttpClient mOnChangePollingClient;
    private boolean mOnChangePollingEnabled;
    @Nullable
    private OnServerContentChangeListener mOnServerContentChangeListener;
    private final String mPackageName;
    @Nullable
    private JSPackagerClient mPackagerClient;
    private final Handler mRestartOnChangePollingHandler;
    private final DevInternalSettings mSettings;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum BundleType {
        BUNDLE("bundle"),
        DELTA("delta"),
        MAP("map");
        
        private final String mTypeID;

        BundleType(String str) {
            this.mTypeID = str;
        }

        public String typeID() {
            return this.mTypeID;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnServerContentChangeListener {
        void onServerContentChanged();
    }

    /* loaded from: classes12.dex */
    public interface PackagerCommandListener {
        @Nullable
        Map<String, RequestHandler> customCommandHandlers();

        void onCaptureHeapCommand(Responder responder);

        void onPackagerConnected();

        void onPackagerDevMenuCommand();

        void onPackagerDisconnected();

        void onPackagerReloadCommand();
    }

    /* loaded from: classes12.dex */
    public interface PackagerCustomCommandProvider {
    }

    /* loaded from: classes12.dex */
    public interface SymbolicationListener {
        void onSymbolicationComplete(@Nullable Iterable<StackFrame> iterable);
    }

    public DevServerHelper(DevInternalSettings devInternalSettings, String str, InspectorPackagerConnection.BundleStatusProvider bundleStatusProvider) {
        this.mSettings = devInternalSettings;
        this.mBundlerStatusProvider = bundleStatusProvider;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient build = builder.connectTimeout(Final.FIVE_SECOND, timeUnit).readTimeout(0L, timeUnit).writeTimeout(0L, timeUnit).build();
        this.mClient = build;
        this.mBundleDownloader = new BundleDownloader(build);
        this.mRestartOnChangePollingHandler = new Handler(Looper.getMainLooper());
        this.mPackageName = str;
    }

    private String createBundleURL(String str, BundleType bundleType, String str2) {
        return String.format(Locale.US, "http://%s/%s.%s?platform=android&dev=%s&minify=%s", str2, str, bundleType.typeID(), Boolean.valueOf(getDevMode()), Boolean.valueOf(getJSMinifyMode()));
    }

    private String createLaunchJSDevtoolsCommandUrl() {
        return String.format(Locale.US, "http://%s/launch-js-devtools", this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    private String createOnChangeEndpointUrl() {
        return String.format(Locale.US, "http://%s/onchange", this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    private static String createOpenStackFrameURL(String str) {
        return String.format(Locale.US, "http://%s/open-stack-frame", str);
    }

    private static String createPackagerStatusURL(String str) {
        return String.format(Locale.US, "http://%s/status", str);
    }

    private static String createResourceURL(String str, String str2) {
        return String.format(Locale.US, "http://%s/%s", str, str2);
    }

    private static String createSymbolicateURL(String str) {
        return String.format(Locale.US, "http://%s/symbolicate", str);
    }

    private void enqueueOnChangeEndpointLongPolling() {
        ((OkHttpClient) Assertions.assertNotNull(this.mOnChangePollingClient)).newCall(new Request.Builder().url(createOnChangeEndpointUrl()).tag(this).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.10
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (DevServerHelper.this.mOnChangePollingEnabled) {
                    FLog.d(ReactConstants.TAG, "Error while requesting /onchange endpoint", (Throwable) iOException);
                    DevServerHelper.this.mRestartOnChangePollingHandler.postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.DevServerHelper.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DevServerHelper.this.handleOnChangePollingResponse(false);
                        }
                    }, Final.FIVE_SECOND);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                DevServerHelper.this.handleOnChangePollingResponse(response.code() == 205);
            }
        });
    }

    private BundleDeltaClient.ClientType getDeltaClientType() {
        if (this.mSettings.isBundleDeltasCppEnabled()) {
            return BundleDeltaClient.ClientType.NATIVE;
        }
        if (this.mSettings.isBundleDeltasEnabled()) {
            return BundleDeltaClient.ClientType.DEV_SUPPORT;
        }
        return BundleDeltaClient.ClientType.NONE;
    }

    private boolean getDevMode() {
        return this.mSettings.isJSDevModeEnabled();
    }

    private String getHostForJSProxy() {
        String str = (String) Assertions.assertNotNull(this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf > -1) {
            return AndroidInfoHelpers.DEVICE_LOCALHOST + str.substring(lastIndexOf);
        }
        return AndroidInfoHelpers.DEVICE_LOCALHOST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getInspectorAttachUrl(String str) {
        return String.format(Locale.US, "http://%s/nuclide/attach-debugger-nuclide?title=%s&app=%s&device=%s", AndroidInfoHelpers.getServerHost(), str, this.mPackageName, AndroidInfoHelpers.getFriendlyDeviceName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getInspectorDeviceUrl() {
        return String.format(Locale.US, "http://%s/inspector/device?name=%s&app=%s", this.mSettings.getPackagerConnectionSettings().getInspectorServerHost(), AndroidInfoHelpers.getFriendlyDeviceName(), this.mPackageName);
    }

    private boolean getJSMinifyMode() {
        return this.mSettings.isJSMinifyEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnChangePollingResponse(boolean z) {
        if (this.mOnChangePollingEnabled) {
            if (z) {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevServerHelper.9
                    @Override // java.lang.Runnable
                    public void run() {
                        if (DevServerHelper.this.mOnServerContentChangeListener != null) {
                            DevServerHelper.this.mOnServerContentChangeListener.onServerContentChanged();
                        }
                    }
                });
            }
            enqueueOnChangeEndpointLongPolling();
        }
    }

    public void attachDebugger(final Context context, final String str) {
        new AsyncTask<Void, String, Boolean>() { // from class: com.facebook.react.devsupport.DevServerHelper.5
            public boolean doSync() {
                try {
                    new OkHttpClient().newCall(new Request.Builder().url(DevServerHelper.this.getInspectorAttachUrl(str)).build()).execute();
                    return true;
                } catch (IOException e2) {
                    FLog.e(ReactConstants.TAG, "Failed to send attach request to Inspector", e2);
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(doSync());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    return;
                }
                Toast.makeText(context, context.getString(R.string.catalyst_debugjs_nuclide_failure), 1).show();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void closeInspectorConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mInspectorPackagerConnection != null) {
                    DevServerHelper.this.mInspectorPackagerConnection.closeQuietly();
                    DevServerHelper.this.mInspectorPackagerConnection = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void closePackagerConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mPackagerClient != null) {
                    DevServerHelper.this.mPackagerClient.close();
                    DevServerHelper.this.mPackagerClient = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void disableDebugger() {
        InspectorPackagerConnection inspectorPackagerConnection = this.mInspectorPackagerConnection;
        if (inspectorPackagerConnection != null) {
            inspectorPackagerConnection.sendEventToAllConnections(DEBUGGER_MSG_DISABLE);
        }
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, getDeltaClientType());
    }

    @Nullable
    public File downloadBundleResourceFromUrlSync(String str, File file) {
        Sink sink;
        try {
            Response execute = this.mClient.newCall(new Request.Builder().url(createResourceURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost(), str)).build()).execute();
            if (!execute.isSuccessful()) {
                if (execute != null) {
                    execute.close();
                }
                return null;
            }
            try {
                sink = Okio.sink(file);
            } catch (Throwable th) {
                th = th;
                sink = null;
            }
            try {
                Okio.buffer(execute.body().source()).readAll(sink);
                if (sink != null) {
                    sink.close();
                }
                if (execute != null) {
                    execute.close();
                }
                return file;
            } catch (Throwable th2) {
                th = th2;
                if (sink != null) {
                    sink.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            FLog.e(ReactConstants.TAG, "Failed to fetch resource synchronously - resourcePath: \"%s\", outputFile: \"%s\"", str, file.getAbsolutePath(), e2);
            return null;
        }
    }

    public String getDevServerBundleURL(String str) {
        return createBundleURL(str, this.mSettings.isBundleDeltasEnabled() ? BundleType.DELTA : BundleType.BUNDLE, this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    public String getJSBundleURLForRemoteDebugging(String str) {
        return createBundleURL(str, BundleType.BUNDLE, getHostForJSProxy());
    }

    public String getSourceMapUrl(String str) {
        return createBundleURL(str, BundleType.MAP);
    }

    public String getSourceUrl(String str) {
        return createBundleURL(str, this.mSettings.isBundleDeltasEnabled() ? BundleType.DELTA : BundleType.BUNDLE);
    }

    public String getWebsocketProxyURL() {
        return String.format(Locale.US, "ws://%s/debugger-proxy?role=client", this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    public void isPackagerRunning(final PackagerStatusCallback packagerStatusCallback) {
        this.mClient.newCall(new Request.Builder().url(createPackagerStatusURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost())).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.8
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                FLog.w(ReactConstants.TAG, "The packager does not seem to be running as we got an IOException requesting its status: " + iOException.getMessage());
                packagerStatusCallback.onPackagerStatusFetched(false);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    FLog.e(ReactConstants.TAG, "Got non-success http code from packager when requesting status: " + response.code());
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                ResponseBody body = response.body();
                if (body == null) {
                    FLog.e(ReactConstants.TAG, "Got null body response from packager when requesting status");
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                String string = body.string();
                if (!DevServerHelper.PACKAGER_OK_STATUS.equals(string)) {
                    FLog.e(ReactConstants.TAG, "Got unexpected response from packager when requesting status: " + string);
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                packagerStatusCallback.onPackagerStatusFetched(true);
            }
        });
    }

    public void launchJSDevtools() {
        this.mClient.newCall(new Request.Builder().url(createLaunchJSDevtoolsCommandUrl()).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.11
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    public void openInspectorConnection() {
        if (this.mInspectorPackagerConnection != null) {
            FLog.w(ReactConstants.TAG, "Inspector connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    DevServerHelper devServerHelper = DevServerHelper.this;
                    devServerHelper.mInspectorPackagerConnection = new InspectorPackagerConnection(devServerHelper.getInspectorDeviceUrl(), DevServerHelper.this.mPackageName, DevServerHelper.this.mBundlerStatusProvider);
                    DevServerHelper.this.mInspectorPackagerConnection.connect();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void openPackagerConnection(final String str, final PackagerCommandListener packagerCommandListener) {
        if (this.mPackagerClient != null) {
            FLog.w(ReactConstants.TAG, "Packager connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("reload", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.1
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(@Nullable Object obj) {
                            packagerCommandListener.onPackagerReloadCommand();
                        }
                    });
                    hashMap.put("devMenu", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.2
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(@Nullable Object obj) {
                            packagerCommandListener.onPackagerDevMenuCommand();
                        }
                    });
                    hashMap.put("captureHeap", new RequestOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.3
                        @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onRequest(@Nullable Object obj, Responder responder) {
                            packagerCommandListener.onCaptureHeapCommand(responder);
                        }
                    });
                    Map<String, RequestHandler> customCommandHandlers = packagerCommandListener.customCommandHandlers();
                    if (customCommandHandlers != null) {
                        hashMap.putAll(customCommandHandlers);
                    }
                    hashMap.putAll(new FileIoHandler().handlers());
                    ReconnectingWebSocket.ConnectionCallback connectionCallback = new ReconnectingWebSocket.ConnectionCallback() { // from class: com.facebook.react.devsupport.DevServerHelper.1.4
                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onConnected() {
                            packagerCommandListener.onPackagerConnected();
                        }

                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onDisconnected() {
                            packagerCommandListener.onPackagerDisconnected();
                        }
                    };
                    DevServerHelper.this.mPackagerClient = new JSPackagerClient(str, DevServerHelper.this.mSettings.getPackagerConnectionSettings(), hashMap, connectionCallback);
                    DevServerHelper.this.mPackagerClient.init();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void openStackFrameCall(StackFrame stackFrame) {
        ((Call) Assertions.assertNotNull(this.mClient.newCall(new Request.Builder().url(createOpenStackFrameURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost())).post(RequestBody.create(MediaType.parse(HTTP.CONTENT_TYPE_JSON), stackFrame.toJSON().toString())).build()))).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.7
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                FLog.w(ReactConstants.TAG, "Got IOException when attempting to open stack frame: " + iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    public void startPollingOnChangeEndpoint(OnServerContentChangeListener onServerContentChangeListener) {
        if (this.mOnChangePollingEnabled) {
            return;
        }
        this.mOnChangePollingEnabled = true;
        this.mOnServerContentChangeListener = onServerContentChangeListener;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mOnChangePollingClient = builder.connectionPool(new ConnectionPool(1, 120000L, timeUnit)).connectTimeout(Final.FIVE_SECOND, timeUnit).build();
        enqueueOnChangeEndpointLongPolling();
    }

    public void stopPollingOnChangeEndpoint() {
        this.mOnChangePollingEnabled = false;
        this.mRestartOnChangePollingHandler.removeCallbacksAndMessages(null);
        OkHttpClient okHttpClient = this.mOnChangePollingClient;
        if (okHttpClient != null) {
            OkHttpCallUtil.cancelTag(okHttpClient, this);
            this.mOnChangePollingClient = null;
        }
        this.mOnServerContentChangeListener = null;
    }

    public void symbolicateStackTrace(Iterable<StackFrame> iterable, final SymbolicationListener symbolicationListener) {
        try {
            String createSymbolicateURL = createSymbolicateURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
            JSONArray jSONArray = new JSONArray();
            Iterator<StackFrame> it = iterable.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJSON());
            }
            ((Call) Assertions.assertNotNull(this.mClient.newCall(new Request.Builder().url(createSymbolicateURL).post(RequestBody.create(MediaType.parse(HTTP.CONTENT_TYPE_JSON), new JSONObject().put("stack", jSONArray).toString())).build()))).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.6
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    FLog.w(ReactConstants.TAG, "Got IOException when attempting symbolicate stack trace: " + iOException.getMessage());
                    symbolicationListener.onSymbolicationComplete(null);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        symbolicationListener.onSymbolicationComplete(Arrays.asList(StackTraceHelper.convertJsStackTrace(new JSONObject(response.body().string()).getJSONArray("stack"))));
                    } catch (JSONException unused) {
                        symbolicationListener.onSymbolicationComplete(null);
                    }
                }
            });
        } catch (JSONException e2) {
            FLog.w(ReactConstants.TAG, "Got JSONException when attempting symbolicate stack trace: " + e2.getMessage());
        }
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo, Request.Builder builder) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, getDeltaClientType(), builder);
    }

    private String createBundleURL(String str, BundleType bundleType) {
        return createBundleURL(str, bundleType, this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }
}
