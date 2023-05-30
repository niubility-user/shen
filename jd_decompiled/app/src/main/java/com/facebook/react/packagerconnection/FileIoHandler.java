package com.facebook.react.packagerconnection;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class FileIoHandler implements Runnable {
    private static final long FILE_TTL = 30000;
    private static final String TAG = JSPackagerClient.class.getSimpleName();
    private final Map<String, RequestHandler> mRequestHandlers;
    private int mNextHandle = 1;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Map<Integer, TtlFileInputStream> mOpenFiles = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class TtlFileInputStream {
        private final FileInputStream mStream;
        private long mTtl = System.currentTimeMillis() + 30000;

        public TtlFileInputStream(String str) throws FileNotFoundException {
            this.mStream = new FileInputStream(str);
        }

        private void extendTtl() {
            this.mTtl = System.currentTimeMillis() + 30000;
        }

        public void close() throws IOException {
            this.mStream.close();
        }

        public boolean expiredTtl() {
            return System.currentTimeMillis() >= this.mTtl;
        }

        public String read(int i2) throws IOException {
            extendTtl();
            byte[] bArr = new byte[i2];
            return Base64.encodeToString(bArr, 0, this.mStream.read(bArr), 0);
        }
    }

    public FileIoHandler() {
        HashMap hashMap = new HashMap();
        this.mRequestHandlers = hashMap;
        hashMap.put("fopen", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.1
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(@Nullable Object obj, Responder responder) {
                JSONObject jSONObject;
                synchronized (FileIoHandler.this.mOpenFiles) {
                    try {
                        jSONObject = (JSONObject) obj;
                    } catch (Exception e2) {
                        responder.error(e2.toString());
                    }
                    if (jSONObject != null) {
                        String optString = jSONObject.optString("mode");
                        if (optString != null) {
                            String optString2 = jSONObject.optString(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
                            if (optString2 != null) {
                                if (optString.equals("r")) {
                                    responder.respond(Integer.valueOf(FileIoHandler.this.addOpenFile(optString2)));
                                } else {
                                    throw new IllegalArgumentException("unsupported mode: " + optString);
                                }
                            } else {
                                throw new Exception("missing params.filename");
                            }
                        } else {
                            throw new Exception("missing params.mode");
                        }
                    } else {
                        throw new Exception("params must be an object { mode: string, filename: string }");
                    }
                }
            }
        });
        hashMap.put("fclose", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.2
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(@Nullable Object obj, Responder responder) {
                synchronized (FileIoHandler.this.mOpenFiles) {
                    try {
                    } catch (Exception e2) {
                        responder.error(e2.toString());
                    }
                    if (obj instanceof Number) {
                        TtlFileInputStream ttlFileInputStream = (TtlFileInputStream) FileIoHandler.this.mOpenFiles.get(Integer.valueOf(((Integer) obj).intValue()));
                        if (ttlFileInputStream != null) {
                            FileIoHandler.this.mOpenFiles.remove(Integer.valueOf(((Integer) obj).intValue()));
                            ttlFileInputStream.close();
                            responder.respond("");
                        } else {
                            throw new Exception("invalid file handle, it might have timed out");
                        }
                    } else {
                        throw new Exception("params must be a file handle");
                    }
                }
            }
        });
        hashMap.put("fread", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.3
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(@Nullable Object obj, Responder responder) {
                JSONObject jSONObject;
                synchronized (FileIoHandler.this.mOpenFiles) {
                    try {
                        jSONObject = (JSONObject) obj;
                    } catch (Exception e2) {
                        responder.error(e2.toString());
                    }
                    if (jSONObject != null) {
                        int optInt = jSONObject.optInt("file");
                        if (optInt != 0) {
                            int optInt2 = jSONObject.optInt(ApkDownloadTable.FIELD_SIZE);
                            if (optInt2 != 0) {
                                TtlFileInputStream ttlFileInputStream = (TtlFileInputStream) FileIoHandler.this.mOpenFiles.get(Integer.valueOf(optInt));
                                if (ttlFileInputStream != null) {
                                    responder.respond(ttlFileInputStream.read(optInt2));
                                } else {
                                    throw new Exception("invalid file handle, it might have timed out");
                                }
                            } else {
                                throw new Exception("invalid or missing read size");
                            }
                        } else {
                            throw new Exception("invalid or missing file handle");
                        }
                    } else {
                        throw new Exception("params must be an object { file: handle, size: number }");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int addOpenFile(String str) throws FileNotFoundException {
        int i2 = this.mNextHandle;
        this.mNextHandle = i2 + 1;
        this.mOpenFiles.put(Integer.valueOf(i2), new TtlFileInputStream(str));
        if (this.mOpenFiles.size() == 1) {
            this.mHandler.postDelayed(this, 30000L);
        }
        return i2;
    }

    public Map<String, RequestHandler> handlers() {
        return this.mRequestHandlers;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.mOpenFiles) {
            Iterator<TtlFileInputStream> it = this.mOpenFiles.values().iterator();
            while (it.hasNext()) {
                TtlFileInputStream next = it.next();
                if (next.expiredTtl()) {
                    it.remove();
                    try {
                        next.close();
                    } catch (IOException e2) {
                        FLog.e(TAG, "closing expired file failed: " + e2.toString());
                    }
                }
            }
            if (!this.mOpenFiles.isEmpty()) {
                this.mHandler.postDelayed(this, 30000L);
            }
        }
    }
}
