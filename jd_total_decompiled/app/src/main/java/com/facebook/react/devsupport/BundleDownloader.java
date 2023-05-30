package com.facebook.react.devsupport;

import android.util.Pair;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeDeltaClient;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.BundleDeltaClient;
import com.facebook.react.devsupport.MultipartStreamReader;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class BundleDownloader {
    private static final int FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER = -2;
    private static final String TAG = "BundleDownloader";
    private BundleDeltaClient mBundleDeltaClient;
    private final OkHttpClient mClient;
    @Nullable
    private Call mDownloadBundleFromURLCall;

    /* loaded from: classes12.dex */
    public static class BundleInfo {
        @Nullable
        private String mDeltaClientName;
        private int mFilesChangedCount;
        @Nullable
        private String mUrl;

        @Nullable
        public static BundleInfo fromJSONString(String str) {
            if (str == null) {
                return null;
            }
            BundleInfo bundleInfo = new BundleInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                bundleInfo.mDeltaClientName = jSONObject.getString("deltaClient");
                bundleInfo.mUrl = jSONObject.getString("url");
                bundleInfo.mFilesChangedCount = jSONObject.getInt("filesChangedCount");
                return bundleInfo;
            } catch (JSONException unused) {
                return null;
            }
        }

        @Nullable
        public String getDeltaClient() {
            return this.mDeltaClientName;
        }

        public int getFilesChangedCount() {
            return this.mFilesChangedCount;
        }

        public String getUrl() {
            String str = this.mUrl;
            return str != null ? str : "unknown";
        }

        @Nullable
        public String toJSONString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("deltaClient", this.mDeltaClientName);
                jSONObject.put("url", this.mUrl);
                jSONObject.put("filesChangedCount", this.mFilesChangedCount);
                return jSONObject.toString();
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public BundleDownloader(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
    }

    private String formatBundleUrl(String str, BundleDeltaClient.ClientType clientType) {
        BundleDeltaClient bundleDeltaClient;
        return (BundleDeltaClient.isDeltaUrl(str) && (bundleDeltaClient = this.mBundleDeltaClient) != null && bundleDeltaClient.canHandle(clientType)) ? this.mBundleDeltaClient.extendUrlForDelta(str) : str;
    }

    private BundleDeltaClient getBundleDeltaClient(BundleDeltaClient.ClientType clientType) {
        BundleDeltaClient bundleDeltaClient = this.mBundleDeltaClient;
        if (bundleDeltaClient == null || !bundleDeltaClient.canHandle(clientType)) {
            this.mBundleDeltaClient = BundleDeltaClient.create(clientType);
        }
        return this.mBundleDeltaClient;
    }

    private static void populateBundleInfo(String str, Headers headers, BundleDeltaClient.ClientType clientType, BundleInfo bundleInfo) {
        bundleInfo.mDeltaClientName = clientType == BundleDeltaClient.ClientType.NONE ? null : clientType.name();
        bundleInfo.mUrl = str;
        String str2 = headers.get("X-Metro-Files-Changed-Count");
        if (str2 != null) {
            try {
                bundleInfo.mFilesChangedCount = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                bundleInfo.mFilesChangedCount = -2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processBundleResult(String str, int i2, Headers headers, BufferedSource bufferedSource, File file, BundleInfo bundleInfo, BundleDeltaClient.ClientType clientType, DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        boolean storePlainJSInFile;
        if (i2 != 200) {
            String readUtf8 = bufferedSource.readUtf8();
            DebugServerException parse = DebugServerException.parse(readUtf8);
            if (parse != null) {
                devBundleDownloadListener.onFailure(parse);
                return;
            }
            devBundleDownloadListener.onFailure(new DebugServerException("The development server returned response error code: " + i2 + "\n\nURL: " + str + "\n\nBody:\n" + readUtf8));
            return;
        }
        if (bundleInfo != null) {
            populateBundleInfo(str, headers, clientType, bundleInfo);
        }
        File file2 = new File(file.getPath() + DefaultDiskStorage.FileType.TEMP);
        NativeDeltaClient nativeDeltaClient = null;
        if (BundleDeltaClient.isDeltaUrl(str)) {
            BundleDeltaClient bundleDeltaClient = getBundleDeltaClient(clientType);
            Assertions.assertNotNull(bundleDeltaClient);
            Pair<Boolean, NativeDeltaClient> processDelta = bundleDeltaClient.processDelta(headers, bufferedSource, file2);
            storePlainJSInFile = ((Boolean) processDelta.first).booleanValue();
            nativeDeltaClient = (NativeDeltaClient) processDelta.second;
        } else {
            this.mBundleDeltaClient = null;
            storePlainJSInFile = storePlainJSInFile(bufferedSource, file2);
        }
        if (storePlainJSInFile && !file2.renameTo(file)) {
            throw new IOException("Couldn't rename " + file2 + " to " + file);
        }
        devBundleDownloadListener.onSuccess(nativeDeltaClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMultipartResponse(final String str, final Response response, String str2, final File file, @Nullable final BundleInfo bundleInfo, final BundleDeltaClient.ClientType clientType, final DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        if (new MultipartStreamReader(response.body().source(), str2).readAllParts(new MultipartStreamReader.ChunkListener() { // from class: com.facebook.react.devsupport.BundleDownloader.2
            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkComplete(Map<String, String> map, Buffer buffer, boolean z) throws IOException {
                if (z) {
                    int code = response.code();
                    if (map.containsKey("X-Http-Status")) {
                        code = Integer.parseInt(map.get("X-Http-Status"));
                    }
                    BundleDownloader.this.processBundleResult(str, code, Headers.of(map), buffer, file, bundleInfo, clientType, devBundleDownloadListener);
                } else if (map.containsKey(HttpHeaders.CONTENT_TYPE) && map.get(HttpHeaders.CONTENT_TYPE).equals(HTTP.CONTENT_TYPE_JSON)) {
                    try {
                        JSONObject jSONObject = new JSONObject(buffer.readUtf8());
                        devBundleDownloadListener.onProgress(jSONObject.has("status") ? jSONObject.getString("status") : null, jSONObject.has("done") ? Integer.valueOf(jSONObject.getInt("done")) : null, jSONObject.has("total") ? Integer.valueOf(jSONObject.getInt("total")) : null);
                    } catch (JSONException e2) {
                        FLog.e(ReactConstants.TAG, "Error parsing progress JSON. " + e2.toString());
                    }
                }
            }

            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkProgress(Map<String, String> map, long j2, long j3) throws IOException {
                if ("application/javascript".equals(map.get(HttpHeaders.CONTENT_TYPE))) {
                    devBundleDownloadListener.onProgress("Downloading JavaScript bundle", Integer.valueOf((int) (j2 / 1024)), Integer.valueOf((int) (j3 / 1024)));
                }
            }
        })) {
            return;
        }
        devBundleDownloadListener.onFailure(new DebugServerException("Error while reading multipart response.\n\nResponse code: " + response.code() + "\n\nURL: " + str.toString() + "\n\n"));
    }

    private static boolean storePlainJSInFile(BufferedSource bufferedSource, File file) throws IOException {
        Sink sink;
        try {
            sink = Okio.sink(file);
            try {
                bufferedSource.readAll(sink);
                if (sink != null) {
                    sink.close();
                    return true;
                }
                return true;
            } catch (Throwable th) {
                th = th;
                if (sink != null) {
                    sink.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sink = null;
        }
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, @Nullable BundleInfo bundleInfo, BundleDeltaClient.ClientType clientType) {
        downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, clientType, new Request.Builder());
    }

    public void downloadBundleFromURL(final DevBundleDownloadListener devBundleDownloadListener, final File file, String str, @Nullable final BundleInfo bundleInfo, final BundleDeltaClient.ClientType clientType, Request.Builder builder) {
        Call call = (Call) Assertions.assertNotNull(this.mClient.newCall(builder.url(formatBundleUrl(str, clientType)).build()));
        this.mDownloadBundleFromURLCall = call;
        call.enqueue(new Callback() { // from class: com.facebook.react.devsupport.BundleDownloader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call2, IOException iOException) {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                devBundleDownloadListener.onFailure(DebugServerException.makeGeneric("Could not connect to development server.", "URL: " + call2.request().url().toString(), iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call2, Response response) throws IOException {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                String httpUrl = response.request().url().toString();
                Matcher matcher = Pattern.compile("multipart/mixed;.*boundary=\"([^\"]+)\"").matcher(response.header("content-type"));
                try {
                    if (matcher.find()) {
                        BundleDownloader.this.processMultipartResponse(httpUrl, response, matcher.group(1), file, bundleInfo, clientType, devBundleDownloadListener);
                    } else {
                        BundleDownloader.this.processBundleResult(httpUrl, response.code(), response.headers(), Okio.buffer(response.body().source()), file, bundleInfo, clientType, devBundleDownloadListener);
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (Throwable th) {
                    if (response != null) {
                        try {
                            response.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        });
    }
}
