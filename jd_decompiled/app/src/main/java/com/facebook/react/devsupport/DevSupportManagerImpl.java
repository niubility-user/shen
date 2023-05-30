package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.NativeDeltaClient;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.DevServerHelper;
import com.facebook.react.devsupport.InspectorPackagerConnection;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.devsupport.JSCSamplingProfiler;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.packagerconnection.Responder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes12.dex */
public class DevSupportManagerImpl implements DevSupportManager, DevServerHelper.PackagerCommandListener, DevInternalSettings.Listener {
    public static final String EMOJI_FACE_WITH_NO_GOOD_GESTURE = " \u1f645";
    public static final String EMOJI_HUNDRED_POINTS_SYMBOL = " \u1f4af";
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String JS_BUNDLE_FILE_NAME = "ReactNativeDevBundle.js";
    private static final String RELOAD_APP_ACTION_SUFFIX = ".RELOAD_APP_ACTION";
    private final Context mApplicationContext;
    @Nullable
    private DevBundleDownloadListener mBundleDownloadListener;
    private InspectorPackagerConnection.BundleStatus mBundleStatus;
    @Nullable
    private ReactContext mCurrentContext;
    private final LinkedHashMap<String, DevOptionHandler> mCustomDevOptions;
    @Nullable
    private Map<String, RequestHandler> mCustomPackagerCommandHandlers;
    @Nullable
    private DebugOverlayController mDebugOverlayController;
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler;
    private final DevLoadingViewController mDevLoadingViewController;
    private boolean mDevLoadingViewVisible;
    @Nullable
    private AlertDialog mDevOptionsDialog;
    private final DevServerHelper mDevServerHelper;
    private DevInternalSettings mDevSettings;
    @Nullable
    private List<ErrorCustomizer> mErrorCustomizers;
    private final List<ExceptionLogger> mExceptionLoggers;
    private boolean mIsDevSupportEnabled;
    private boolean mIsReceiverRegistered;
    private boolean mIsShakeDetectorStarted;
    private boolean mIsShowDevOptionsDialog;
    @Nullable
    private String mJSAppBundleName;
    private final File mJSBundleTempFile;
    private int mLastErrorCookie;
    @Nullable
    private StackFrame[] mLastErrorStack;
    @Nullable
    private String mLastErrorTitle;
    @Nullable
    private ErrorType mLastErrorType;
    private final ReactInstanceManagerDevHelper mReactInstanceManagerHelper;
    @Nullable
    private RedBoxDialog mRedBoxDialog;
    @Nullable
    private RedBoxHandler mRedBoxHandler;
    private final BroadcastReceiver mReloadAppBroadcastReceiver;
    private final ShakeDetector mShakeDetector;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum ErrorType {
        JS,
        NATIVE
    }

    /* loaded from: classes12.dex */
    private interface ExceptionLogger {
        void log(Exception exc);
    }

    /* loaded from: classes12.dex */
    private class JSExceptionLogger implements ExceptionLogger {
        private JSExceptionLogger() {
        }

        @Override // com.facebook.react.devsupport.DevSupportManagerImpl.ExceptionLogger
        public void log(Exception exc) {
            StringBuilder sb = new StringBuilder(exc.getMessage() == null ? "Exception in native call from JS" : exc.getMessage());
            for (Throwable cause = exc.getCause(); cause != null; cause = cause.getCause()) {
                sb.append("\n\n");
                sb.append(cause.getMessage());
            }
            if (exc instanceof JSException) {
                FLog.e(ReactConstants.TAG, "Exception in native call from JS", exc);
                String stack = ((JSException) exc).getStack();
                sb.append("\n\n");
                sb.append(stack);
                DevSupportManagerImpl.this.showNewError(sb.toString(), new StackFrame[0], -1, ErrorType.JS);
                return;
            }
            DevSupportManagerImpl.this.showNewJavaError(sb.toString(), exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class JscProfileTask extends AsyncTask<String, Void, Void> {
        private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private final String mSourceUrl;

        private JscProfileTask(String str) {
            this.mSourceUrl = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                String uri = Uri.parse(this.mSourceUrl).buildUpon().path("/jsc-profile").query(null).build().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                for (String str : strArr) {
                    okHttpClient.newCall(new Request.Builder().url(uri).post(RequestBody.create(JSON, str)).build()).execute();
                }
            } catch (IOException e2) {
                FLog.e(ReactConstants.TAG, "Failed not talk to server", e2);
            }
            return null;
        }
    }

    public DevSupportManagerImpl(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, int i2) {
        this(context, reactInstanceManagerDevHelper, str, z, null, null, i2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebsocketJavaScriptExecutor.JSExecutorConnectCallback getExecutorConnectCallback(final SimpleSettableFuture<Boolean> simpleSettableFuture) {
        return new WebsocketJavaScriptExecutor.JSExecutorConnectCallback() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.23
            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onFailure(Throwable th) {
                DevSupportManagerImpl.this.mDevLoadingViewController.hide();
                DevSupportManagerImpl.this.mDevLoadingViewVisible = false;
                FLog.e(ReactConstants.TAG, "Unable to connect to remote debugger", th);
                simpleSettableFuture.setException(new IOException(DevSupportManagerImpl.this.mApplicationContext.getString(R.string.catalyst_remotedbg_error), th));
            }

            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onSuccess() {
                simpleSettableFuture.set(Boolean.TRUE);
                DevSupportManagerImpl.this.mDevLoadingViewController.hide();
                DevSupportManagerImpl.this.mDevLoadingViewVisible = false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getReloadAppAction(Context context) {
        return context.getPackageName() + RELOAD_APP_ACTION_SUFFIX;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCaptureHeap(final Responder responder) {
        ReactContext reactContext = this.mCurrentContext;
        if (reactContext == null) {
            return;
        }
        ((JSCHeapCapture) reactContext.getNativeModule(JSCHeapCapture.class)).captureHeap(this.mApplicationContext.getCacheDir().getPath(), new JSCHeapCapture.CaptureCallback() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.21
            @Override // com.facebook.react.devsupport.JSCHeapCapture.CaptureCallback
            public void onFailure(JSCHeapCapture.CaptureException captureException) {
                responder.error(captureException.toString());
            }

            @Override // com.facebook.react.devsupport.JSCHeapCapture.CaptureCallback
            public void onSuccess(File file) {
                responder.respond(file.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePokeSamplingProfiler() {
        try {
            Iterator<String> it = JSCSamplingProfiler.poke(60000L).iterator();
            while (it.hasNext()) {
                String next = it.next();
                Toast.makeText(this.mCurrentContext, next == null ? "Started JSC Sampling Profiler" : "Stopped JSC Sampling Profiler", 1).show();
                new JscProfileTask(getSourceUrl()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, next);
            }
        } catch (JSCSamplingProfiler.ProfilerException e2) {
            showNewJavaError(e2.getMessage(), e2);
        }
    }

    private void hideDevOptionsDialog() {
        AlertDialog alertDialog = this.mDevOptionsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDevOptionsDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> pair) {
        List<ErrorCustomizer> list = this.mErrorCustomizers;
        if (list == null) {
            return pair;
        }
        Iterator<ErrorCustomizer> it = list.iterator();
        while (it.hasNext()) {
            Pair<String, StackFrame[]> customizeErrorInfo = it.next().customizeErrorInfo(pair);
            if (customizeErrorInfo != null) {
                pair = customizeErrorInfo;
            }
        }
        return pair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reload() {
        UiThreadUtil.assertOnUiThread();
        if (this.mIsDevSupportEnabled) {
            DebugOverlayController debugOverlayController = this.mDebugOverlayController;
            if (debugOverlayController != null) {
                debugOverlayController.setFpsDebugViewVisible(this.mDevSettings.isFpsDebugEnabled());
            }
            if (!this.mIsShakeDetectorStarted) {
                this.mShakeDetector.start((SensorManager) this.mApplicationContext.getSystemService("sensor"));
                this.mIsShakeDetectorStarted = true;
            }
            if (!this.mIsReceiverRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(getReloadAppAction(this.mApplicationContext));
                this.mApplicationContext.registerReceiver(this.mReloadAppBroadcastReceiver, intentFilter);
                this.mIsReceiverRegistered = true;
            }
            if (this.mDevLoadingViewVisible) {
                this.mDevLoadingViewController.showMessage("Reloading...");
            }
            this.mDevServerHelper.openPackagerConnection(getClass().getSimpleName(), this);
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                this.mDevServerHelper.startPollingOnChangeEndpoint(new DevServerHelper.OnServerContentChangeListener() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.25
                    @Override // com.facebook.react.devsupport.DevServerHelper.OnServerContentChangeListener
                    public void onServerContentChanged() {
                        DevSupportManagerImpl.this.handleReloadJS();
                    }
                });
                return;
            } else {
                this.mDevServerHelper.stopPollingOnChangeEndpoint();
                return;
            }
        }
        DebugOverlayController debugOverlayController2 = this.mDebugOverlayController;
        if (debugOverlayController2 != null) {
            debugOverlayController2.setFpsDebugViewVisible(false);
        }
        if (this.mIsShakeDetectorStarted) {
            this.mShakeDetector.stop();
            this.mIsShakeDetectorStarted = false;
        }
        if (this.mIsReceiverRegistered) {
            this.mApplicationContext.unregisterReceiver(this.mReloadAppBroadcastReceiver);
            this.mIsReceiverRegistered = false;
        }
        hideRedboxDialog();
        hideDevOptionsDialog();
        this.mDevLoadingViewController.hide();
        this.mDevServerHelper.closePackagerConnection();
        this.mDevServerHelper.stopPollingOnChangeEndpoint();
    }

    private void reloadJSInProxyMode() {
        this.mDevServerHelper.launchJSDevtools();
        this.mReactInstanceManagerHelper.onReloadWithJSDebugger(new JavaJSExecutor.Factory() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.22
            @Override // com.facebook.react.bridge.JavaJSExecutor.Factory
            public JavaJSExecutor create() throws Exception {
                WebsocketJavaScriptExecutor websocketJavaScriptExecutor = new WebsocketJavaScriptExecutor();
                SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
                websocketJavaScriptExecutor.connect(DevSupportManagerImpl.this.mDevServerHelper.getWebsocketProxyURL(), DevSupportManagerImpl.this.getExecutorConnectCallback(simpleSettableFuture));
                try {
                    simpleSettableFuture.get(90L, TimeUnit.SECONDS);
                    return websocketJavaScriptExecutor;
                } catch (InterruptedException e2) {
                    e = e2;
                    throw new RuntimeException(e);
                } catch (ExecutionException e3) {
                    throw ((Exception) e3.getCause());
                } catch (TimeoutException e4) {
                    e = e4;
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void resetCurrentContext(@Nullable ReactContext reactContext) {
        if (this.mCurrentContext == reactContext) {
            return;
        }
        this.mCurrentContext = reactContext;
        DebugOverlayController debugOverlayController = this.mDebugOverlayController;
        if (debugOverlayController != null) {
            debugOverlayController.setFpsDebugViewVisible(false);
        }
        if (reactContext != null) {
            this.mDebugOverlayController = new DebugOverlayController(reactContext);
        }
        if (this.mDevSettings.isHotModuleReplacementEnabled() && this.mCurrentContext != null) {
            try {
                URL url = new URL(getSourceUrl());
                ((HMRClient) this.mCurrentContext.getJSModule(HMRClient.class)).enable("android", url.getPath().substring(1), url.getHost(), url.getPort());
            } catch (MalformedURLException e2) {
                showNewJavaError(e2.getMessage(), e2);
            }
        }
        reloadSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNewError(@Nullable final String str, final StackFrame[] stackFrameArr, final int i2, final ErrorType errorType) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.5
            @Override // java.lang.Runnable
            public void run() {
                if (DevSupportManagerImpl.this.mRedBoxDialog == null) {
                    Activity currentActivity = DevSupportManagerImpl.this.mReactInstanceManagerHelper.getCurrentActivity();
                    if (currentActivity != null && !currentActivity.isFinishing()) {
                        DevSupportManagerImpl devSupportManagerImpl = DevSupportManagerImpl.this;
                        DevSupportManagerImpl devSupportManagerImpl2 = DevSupportManagerImpl.this;
                        devSupportManagerImpl.mRedBoxDialog = new RedBoxDialog(currentActivity, devSupportManagerImpl2, devSupportManagerImpl2.mRedBoxHandler);
                    } else {
                        FLog.e(ReactConstants.TAG, "Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: " + str);
                        return;
                    }
                }
                if (DevSupportManagerImpl.this.mRedBoxDialog.isShowing()) {
                    return;
                }
                Pair processErrorCustomizers = DevSupportManagerImpl.this.processErrorCustomizers(Pair.create(str, stackFrameArr));
                DevSupportManagerImpl.this.mRedBoxDialog.setExceptionDetails((String) processErrorCustomizers.first, (StackFrame[]) processErrorCustomizers.second);
                DevSupportManagerImpl.this.updateLastErrorInfo(str, stackFrameArr, i2, errorType);
                if (DevSupportManagerImpl.this.mRedBoxHandler != null && errorType == ErrorType.NATIVE) {
                    DevSupportManagerImpl.this.mRedBoxHandler.handleRedbox(str, stackFrameArr, RedBoxHandler.ErrorType.NATIVE);
                }
                DevSupportManagerImpl.this.mRedBoxDialog.resetReporting();
                DevSupportManagerImpl.this.mRedBoxDialog.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLastErrorInfo(@Nullable String str, StackFrame[] stackFrameArr, int i2, ErrorType errorType) {
        this.mLastErrorTitle = str;
        this.mLastErrorStack = stackFrameArr;
        this.mLastErrorCookie = i2;
        this.mLastErrorType = errorType;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void addCustomDevOption(String str, DevOptionHandler devOptionHandler) {
        this.mCustomDevOptions.put(str, devOptionHandler);
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    @Nullable
    public Map<String, RequestHandler> customCommandHandlers() {
        return this.mCustomPackagerCommandHandlers;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public File downloadBundleResourceFromUrlSync(String str, File file) {
        return this.mDevServerHelper.downloadBundleResourceFromUrlSync(str, file);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public DeveloperSettings getDevSettings() {
        return this.mDevSettings;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean getDevSupportEnabled() {
        return this.mIsDevSupportEnabled;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getDownloadedJSBundleFile() {
        return this.mJSBundleTempFile.getAbsolutePath();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getJSBundleURLForRemoteDebugging() {
        return this.mDevServerHelper.getJSBundleURLForRemoteDebugging((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public StackFrame[] getLastErrorStack() {
        return this.mLastErrorStack;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    @Nullable
    public String getLastErrorTitle() {
        return this.mLastErrorTitle;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceMapUrl() {
        String str = this.mJSAppBundleName;
        return str == null ? "" : this.mDevServerHelper.getSourceMapUrl((String) Assertions.assertNotNull(str));
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public String getSourceUrl() {
        String str = this.mJSAppBundleName;
        return str == null ? "" : this.mDevServerHelper.getSourceUrl((String) Assertions.assertNotNull(str));
    }

    @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        if (this.mIsDevSupportEnabled) {
            Iterator<ExceptionLogger> it = this.mExceptionLoggers.iterator();
            while (it.hasNext()) {
                it.next().log(exc);
            }
            return;
        }
        this.mDefaultNativeModuleCallExceptionHandler.handleException(exc);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker(ReactMarkerConstants.RELOAD, this.mDevSettings.getPackagerConnectionSettings().getDebugServerHost());
        hideRedboxDialog();
        if (this.mDevSettings.isRemoteJSDebugEnabled()) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Proxy");
            this.mDevLoadingViewController.showForRemoteJSEnabled();
            this.mDevLoadingViewVisible = true;
            reloadJSInProxyMode();
            return;
        }
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Server");
        reloadJSFromServer(this.mDevServerHelper.getDevServerBundleURL((String) Assertions.assertNotNull(this.mJSAppBundleName)));
    }

    public boolean hasBundleInAssets(String str) {
        try {
            for (String str2 : this.mApplicationContext.getAssets().list("")) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        } catch (IOException unused) {
            FLog.e(ReactConstants.TAG, "Error while loading assets list");
        }
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public boolean hasUpToDateJSBundleInCache() {
        if (this.mIsDevSupportEnabled && this.mJSBundleTempFile.exists()) {
            try {
                String packageName = this.mApplicationContext.getPackageName();
                if (this.mJSBundleTempFile.lastModified() > this.mApplicationContext.getPackageManager().getPackageInfo(packageName, 0).lastUpdateTime) {
                    File file = new File(String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, packageName));
                    if (file.exists()) {
                        return this.mJSBundleTempFile.lastModified() > file.lastModified();
                    }
                    return true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                FLog.e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void hideRedboxDialog() {
        RedBoxDialog redBoxDialog = this.mRedBoxDialog;
        if (redBoxDialog != null) {
            redBoxDialog.dismiss();
            this.mRedBoxDialog = null;
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
        this.mDevServerHelper.isPackagerRunning(packagerStatusCallback);
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onCaptureHeapCommand(final Responder responder) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.20
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerImpl.this.handleCaptureHeap(responder);
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
    public void onInternalSettingsChanged() {
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onNewReactContextCreated(ReactContext reactContext) {
        resetCurrentContext(reactContext);
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerConnected() {
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDevMenuCommand() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.19
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerImpl.this.showDevOptionsDialog();
            }
        });
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerDisconnected() {
    }

    @Override // com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener
    public void onPackagerReloadCommand() {
        this.mDevServerHelper.disableDebugger();
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.18
            @Override // java.lang.Runnable
            public void run() {
                DevSupportManagerImpl.this.handleReloadJS();
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void onReactInstanceDestroyed(ReactContext reactContext) {
        if (reactContext == this.mCurrentContext) {
            resetCurrentContext(null);
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
        if (this.mErrorCustomizers == null) {
            this.mErrorCustomizers = new ArrayList();
        }
        this.mErrorCustomizers.add(errorCustomizer);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadJSFromServer(String str) {
        ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_START);
        this.mDevLoadingViewController.showForUrl(str);
        this.mDevLoadingViewVisible = true;
        final BundleDownloader.BundleInfo bundleInfo = new BundleDownloader.BundleInfo();
        this.mDevServerHelper.downloadBundleFromURL(new DevBundleDownloadListener() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.24
            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onFailure(final Exception exc) {
                DevSupportManagerImpl.this.mDevLoadingViewController.hide();
                DevSupportManagerImpl.this.mDevLoadingViewVisible = false;
                synchronized (DevSupportManagerImpl.this) {
                    DevSupportManagerImpl.this.mBundleStatus.isLastDownloadSucess = Boolean.FALSE;
                }
                if (DevSupportManagerImpl.this.mBundleDownloadListener != null) {
                    DevSupportManagerImpl.this.mBundleDownloadListener.onFailure(exc);
                }
                FLog.e(ReactConstants.TAG, "Unable to download JS bundle", exc);
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.24.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Exception exc2 = exc;
                        if (exc2 instanceof DebugServerException) {
                            DevSupportManagerImpl.this.showNewJavaError(((DebugServerException) exc2).getMessage(), exc);
                            return;
                        }
                        DevSupportManagerImpl devSupportManagerImpl = DevSupportManagerImpl.this;
                        devSupportManagerImpl.showNewJavaError(devSupportManagerImpl.mApplicationContext.getString(R.string.catalyst_jsload_error), exc);
                    }
                });
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onProgress(@Nullable String str2, @Nullable Integer num, @Nullable Integer num2) {
                DevSupportManagerImpl.this.mDevLoadingViewController.updateProgress(str2, num, num2);
                if (DevSupportManagerImpl.this.mBundleDownloadListener != null) {
                    DevSupportManagerImpl.this.mBundleDownloadListener.onProgress(str2, num, num2);
                }
            }

            @Override // com.facebook.react.devsupport.interfaces.DevBundleDownloadListener
            public void onSuccess(@Nullable final NativeDeltaClient nativeDeltaClient) {
                DevSupportManagerImpl.this.mDevLoadingViewController.hide();
                DevSupportManagerImpl.this.mDevLoadingViewVisible = false;
                synchronized (DevSupportManagerImpl.this) {
                    DevSupportManagerImpl.this.mBundleStatus.isLastDownloadSucess = Boolean.TRUE;
                    DevSupportManagerImpl.this.mBundleStatus.updateTimestamp = System.currentTimeMillis();
                }
                if (DevSupportManagerImpl.this.mBundleDownloadListener != null) {
                    DevSupportManagerImpl.this.mBundleDownloadListener.onSuccess(nativeDeltaClient);
                }
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.24.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ReactMarker.logMarker(ReactMarkerConstants.DOWNLOAD_END, bundleInfo.toJSONString());
                        DevSupportManagerImpl.this.mReactInstanceManagerHelper.onJSBundleLoadedFromServer(nativeDeltaClient);
                    }
                });
            }
        }, this.mJSBundleTempFile, str, bundleInfo);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void reloadSettings() {
        if (UiThreadUtil.isOnUiThread()) {
            reload();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.17
                @Override // java.lang.Runnable
                public void run() {
                    DevSupportManagerImpl.this.reload();
                }
            });
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setDevSupportEnabled(boolean z) {
        this.mIsDevSupportEnabled = z;
        reloadSettings();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void setIsShowDevOptionsDialog(boolean z) {
        this.mIsShowDevOptionsDialog = z;
    }

    public void setJSAppBundleName(String str) {
        this.mJSAppBundleName = str;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showDevOptionsDialog() {
        String string;
        String string2;
        String string3;
        String string4;
        if (this.mDevOptionsDialog == null && this.mIsDevSupportEnabled && !ActivityManager.isUserAMonkey() && this.mIsShowDevOptionsDialog) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_reloadjs), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.6
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            if (this.mDevSettings.isNuclideJSDebugEnabled()) {
                linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_debugjs_nuclide) + EMOJI_HUNDRED_POINTS_SYMBOL, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.7
                    @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                    public void onOptionSelected() {
                        DevSupportManagerImpl.this.mDevServerHelper.attachDebugger(DevSupportManagerImpl.this.mApplicationContext, ReactConstants.TAG);
                    }
                });
            }
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_debugjs_off);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_debugjs);
            }
            if (this.mDevSettings.isNuclideJSDebugEnabled()) {
                string = string + EMOJI_FACE_WITH_NO_GOOD_GESTURE;
            }
            linkedHashMap.put(string, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.8
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(!DevSupportManagerImpl.this.mDevSettings.isRemoteJSDebugEnabled());
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                string2 = this.mApplicationContext.getString(R.string.catalyst_live_reload_off);
            } else {
                string2 = this.mApplicationContext.getString(R.string.catalyst_live_reload);
            }
            linkedHashMap.put(string2, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.9
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setReloadOnJSChangeEnabled(!DevSupportManagerImpl.this.mDevSettings.isReloadOnJSChangeEnabled());
                }
            });
            if (this.mDevSettings.isHotModuleReplacementEnabled()) {
                string3 = this.mApplicationContext.getString(R.string.catalyst_hot_module_replacement_off);
            } else {
                string3 = this.mApplicationContext.getString(R.string.catalyst_hot_module_replacement);
            }
            linkedHashMap.put(string3, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.10
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setHotModuleReplacementEnabled(!DevSupportManagerImpl.this.mDevSettings.isHotModuleReplacementEnabled());
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_element_inspector), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.11
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setElementInspectorEnabled(!DevSupportManagerImpl.this.mDevSettings.isElementInspectorEnabled());
                    DevSupportManagerImpl.this.mReactInstanceManagerHelper.toggleElementInspector();
                }
            });
            if (this.mDevSettings.isFpsDebugEnabled()) {
                string4 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor_off);
            } else {
                string4 = this.mApplicationContext.getString(R.string.catalyst_perf_monitor);
            }
            linkedHashMap.put(string4, new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.12
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    if (!DevSupportManagerImpl.this.mDevSettings.isFpsDebugEnabled()) {
                        Activity currentActivity = DevSupportManagerImpl.this.mReactInstanceManagerHelper.getCurrentActivity();
                        if (currentActivity == null) {
                            FLog.e(ReactConstants.TAG, "Unable to get reference to react activity");
                        } else {
                            DebugOverlayController.requestPermission(currentActivity);
                        }
                    }
                    DevSupportManagerImpl.this.mDevSettings.setFpsDebugEnabled(!DevSupportManagerImpl.this.mDevSettings.isFpsDebugEnabled());
                }
            });
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_poke_sampling_profiler), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.13
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.handlePokeSamplingProfiler();
                }
            });
            linkedHashMap.put(this.mApplicationContext.getString(R.string.catalyst_settings), new DevOptionHandler() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.14
                @Override // com.facebook.react.devsupport.interfaces.DevOptionHandler
                public void onOptionSelected() {
                    Intent intent = new Intent(DevSupportManagerImpl.this.mApplicationContext, DevSettingsActivity.class);
                    intent.setFlags(268435456);
                    DevSupportManagerImpl.this.mApplicationContext.startActivity(intent);
                }
            });
            if (this.mCustomDevOptions.size() > 0) {
                linkedHashMap.putAll(this.mCustomDevOptions);
            }
            final DevOptionHandler[] devOptionHandlerArr = (DevOptionHandler[]) linkedHashMap.values().toArray(new DevOptionHandler[0]);
            Activity currentActivity = this.mReactInstanceManagerHelper.getCurrentActivity();
            if (currentActivity != null && !currentActivity.isFinishing()) {
                AlertDialog create = new AlertDialog.Builder(currentActivity).setItems((CharSequence[]) linkedHashMap.keySet().toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.16
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        devOptionHandlerArr[i2].onOptionSelected();
                        DevSupportManagerImpl.this.mDevOptionsDialog = null;
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.15
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        DevSupportManagerImpl.this.mDevOptionsDialog = null;
                    }
                }).create();
                this.mDevOptionsDialog = create;
                create.show();
                return;
            }
            FLog.e(ReactConstants.TAG, "Unable to launch dev options menu because react activity isn't available");
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJSError(String str, ReadableArray readableArray, int i2) {
        showNewError(str, StackTraceHelper.convertJsStackTrace(readableArray), i2, ErrorType.JS);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void showNewJavaError(@Nullable String str, Throwable th) {
        FLog.e(ReactConstants.TAG, "Exception in native call", th);
        showNewError(str, StackTraceHelper.convertJavaStackTrace(th), -1, ErrorType.NATIVE);
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
        if (this.mIsDevSupportEnabled) {
            this.mDevServerHelper.openInspectorConnection();
        }
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void updateJSError(final String str, final ReadableArray readableArray, final int i2) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.4
            @Override // java.lang.Runnable
            public void run() {
                if (DevSupportManagerImpl.this.mRedBoxDialog != null && DevSupportManagerImpl.this.mRedBoxDialog.isShowing() && i2 == DevSupportManagerImpl.this.mLastErrorCookie) {
                    StackFrame[] convertJsStackTrace = StackTraceHelper.convertJsStackTrace(readableArray);
                    Pair processErrorCustomizers = DevSupportManagerImpl.this.processErrorCustomizers(Pair.create(str, convertJsStackTrace));
                    DevSupportManagerImpl.this.mRedBoxDialog.setExceptionDetails((String) processErrorCustomizers.first, (StackFrame[]) processErrorCustomizers.second);
                    DevSupportManagerImpl.this.updateLastErrorInfo(str, convertJsStackTrace, i2, ErrorType.JS);
                    if (DevSupportManagerImpl.this.mRedBoxHandler != null) {
                        DevSupportManagerImpl.this.mRedBoxHandler.handleRedbox(str, convertJsStackTrace, RedBoxHandler.ErrorType.JS);
                        DevSupportManagerImpl.this.mRedBoxDialog.resetReporting();
                    }
                    DevSupportManagerImpl.this.mRedBoxDialog.show();
                }
            }
        });
    }

    public DevSupportManagerImpl(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i2, @Nullable Map<String, RequestHandler> map) {
        ArrayList arrayList = new ArrayList();
        this.mExceptionLoggers = arrayList;
        this.mCustomDevOptions = new LinkedHashMap<>();
        this.mDevLoadingViewVisible = false;
        this.mIsReceiverRegistered = false;
        this.mIsShakeDetectorStarted = false;
        this.mIsDevSupportEnabled = false;
        this.mIsShowDevOptionsDialog = true;
        this.mLastErrorCookie = 0;
        this.mReactInstanceManagerHelper = reactInstanceManagerDevHelper;
        this.mApplicationContext = context;
        this.mJSAppBundleName = str;
        this.mDevSettings = new DevInternalSettings(context, this);
        this.mBundleStatus = new InspectorPackagerConnection.BundleStatus();
        this.mDevServerHelper = new DevServerHelper(this.mDevSettings, context.getPackageName(), new InspectorPackagerConnection.BundleStatusProvider() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.1
            @Override // com.facebook.react.devsupport.InspectorPackagerConnection.BundleStatusProvider
            public InspectorPackagerConnection.BundleStatus getBundleStatus() {
                return DevSupportManagerImpl.this.mBundleStatus;
            }
        });
        this.mBundleDownloadListener = devBundleDownloadListener;
        this.mShakeDetector = new ShakeDetector(new ShakeDetector.ShakeListener() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.2
            @Override // com.facebook.react.common.ShakeDetector.ShakeListener
            public void onShake() {
                DevSupportManagerImpl.this.showDevOptionsDialog();
            }
        }, i2);
        this.mCustomPackagerCommandHandlers = map;
        this.mReloadAppBroadcastReceiver = new BroadcastReceiver() { // from class: com.facebook.react.devsupport.DevSupportManagerImpl.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (DevSupportManagerImpl.getReloadAppAction(context2).equals(intent.getAction())) {
                    if (intent.getBooleanExtra(DevServerHelper.RELOAD_APP_EXTRA_JS_PROXY, false)) {
                        DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(true);
                        DevSupportManagerImpl.this.mDevServerHelper.launchJSDevtools();
                    } else {
                        DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(false);
                    }
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            }
        };
        this.mJSBundleTempFile = new File(context.getFilesDir(), JS_BUNDLE_FILE_NAME);
        this.mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();
        setDevSupportEnabled(z);
        this.mRedBoxHandler = redBoxHandler;
        this.mDevLoadingViewController = new DevLoadingViewController(context, reactInstanceManagerDevHelper);
        arrayList.add(new JSExceptionLogger());
    }
}
