package tv.danmaku.ijk.media.ext.dynamic;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tv.danmaku.ijk.media.ext.config.DynamicLibInfoBean;
import tv.danmaku.ijk.media.utils.PlayerFileUtils;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class RemoteSoLoader {
    private static final String TAG = "RemoteSoLoader";
    private static volatile RemoteSoLoader mInstance;
    private boolean loadEnable;
    private RemoteSoLoaderCallback mCallback;
    private ExecutorService mFileExecService;

    /* loaded from: classes11.dex */
    public interface RemoteSoLoaderCallback {
        void onLoadResult(boolean z);
    }

    private RemoteSoLoader() {
    }

    private boolean checkExitWithValid(Context context, List<String> list, DynamicLibInfoBean.ArchLibInfo archLibInfo) {
        String fileMD5;
        if (archLibInfo != null && archLibInfo.getKeySoMd5() != null) {
            File dir = context.getDir("libs", 0);
            if (!dir.exists()) {
                String str = "checkExitWithValid, file dir[" + dir.getAbsolutePath() + "] no exit, create result: " + dir.mkdirs();
                return false;
            } else if (list != null && list.size() > 0) {
                String str2 = dir.getAbsolutePath() + File.separator + list.get(list.size() - 1);
                File file = new File(str2);
                if (file.exists() && (fileMD5 = PlayerToolsUtil.getFileMD5(file)) != null && !TextUtils.isEmpty(fileMD5)) {
                    if (fileMD5.equals(archLibInfo.getKeySoMd5())) {
                        return true;
                    }
                    String str3 = "check local so, unSame\uff01[serve md5=" + archLibInfo.getKeySoMd5() + ", local md5=" + fileMD5 + "]";
                }
                String str4 = "checkExitWithValid, so name=" + list.get(list.size() - 1) + ", file path=" + str2;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copy(Context context, String str, String str2) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            int i2 = 0;
            for (int i3 = 0; i3 < listFiles.length; i3++) {
                String str3 = "path: " + listFiles[i3].getAbsolutePath() + ", file name: " + listFiles[i3].getName();
                if (listFiles[i3].getAbsolutePath().contains(".so")) {
                    i2 += realCopy(context, listFiles[i3].getAbsolutePath(), listFiles[i3].getName());
                }
            }
            RemoteSoLoaderCallback remoteSoLoaderCallback = this.mCallback;
            if (remoteSoLoaderCallback != null) {
                remoteSoLoaderCallback.onLoadResult(i2 == 0);
            }
            ExecutorService executorService = this.mFileExecService;
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private void downloadRemoteFile(final Context context, final Pair<String, List<String>> pair, final DynamicLibInfoBean.ArchLibInfo archLibInfo, final String str) {
        Request build = new Request.Builder().url(archLibInfo.getDownloadUrl()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        okHttpClient.newCall(build).enqueue(new Callback() { // from class: tv.danmaku.ijk.media.ext.dynamic.RemoteSoLoader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String str2 = "download onFailure: error=" + iOException.getMessage();
            }

            /* JADX WARN: Removed duplicated region for block: B:54:0x011b A[Catch: IOException -> 0x011e, TRY_LEAVE, TryCatch #9 {IOException -> 0x011e, blocks: (B:52:0x0116, B:54:0x011b), top: B:67:0x0116 }] */
            /* JADX WARN: Removed duplicated region for block: B:67:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onResponse(Call call, Response response) {
                FileOutputStream fileOutputStream;
                final File file2;
                String fileMD5;
                byte[] bArr = new byte[2048];
                InputStream inputStream = null;
                try {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                    }
                    if (response.code() != 200) {
                        String str2 = "download file failed, check downloadUrl:" + archLibInfo.getDownloadUrl();
                        return;
                    }
                    InputStream byteStream = response.body().byteStream();
                    try {
                        file2 = new File(str, ((String) pair.first) + ".zip");
                        fileOutputStream = new FileOutputStream(file2);
                        while (true) {
                            try {
                                int read = byteStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            } catch (Exception e3) {
                                e = e3;
                                inputStream = byteStream;
                                try {
                                    String str3 = "download onFailure: error=" + e.getMessage();
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (fileOutputStream == null) {
                                        return;
                                    }
                                    fileOutputStream.close();
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused) {
                                            throw th;
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                inputStream = byteStream;
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                throw th;
                            }
                        }
                        fileOutputStream.flush();
                        if (RemoteSoLoader.this.mFileExecService == null) {
                            RemoteSoLoader.this.mFileExecService = Executors.newSingleThreadExecutor();
                        }
                        fileMD5 = PlayerToolsUtil.getFileMD5(file2.getAbsoluteFile());
                        String str4 = "download file success, save path:" + file2.getAbsolutePath() + ", md5=" + fileMD5;
                    } catch (Exception e4) {
                        e = e4;
                        fileOutputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                    }
                    if (fileMD5 != null && fileMD5.equals(archLibInfo.getZipFileMd5())) {
                        RemoteSoLoader.this.mFileExecService.execute(new Runnable() { // from class: tv.danmaku.ijk.media.ext.dynamic.RemoteSoLoader.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                String str5 = str + ((String) pair.first);
                                if (PlayerFileUtils.unzip(file2.getAbsolutePath(), str5)) {
                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                    RemoteSoLoader.this.copy(context, str5 + File.separator + LibUtils.getCurrentAbi().getAbiName(), context.getDir("libs", 0).getAbsolutePath());
                                }
                            }
                        });
                        if (byteStream != null) {
                            byteStream.close();
                        }
                        fileOutputStream.close();
                        return;
                    }
                    String str5 = "check download file, unSame\uff01[serve md5=" + archLibInfo.getZipFileMd5() + ", local md5=" + fileMD5 + "]";
                    if (byteStream != null) {
                        try {
                            byteStream.close();
                        } catch (IOException unused2) {
                            return;
                        }
                    }
                    fileOutputStream.close();
                } catch (IOException unused3) {
                }
            }
        });
    }

    public static RemoteSoLoader getInstance() {
        if (mInstance == null) {
            synchronized (RemoteSoLoader.class) {
                if (mInstance == null) {
                    mInstance = new RemoteSoLoader();
                }
            }
        }
        return mInstance;
    }

    public static int realCopy(Context context, String str, String str2) {
        try {
            String str3 = "realCopy, from: " + str + ", file exit: " + new File(str).exists();
            File file = new File(context.getDir("libs", 0), str2);
            String str4 = "realCopy, to: " + file.getAbsolutePath();
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.close();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return 0;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void loadDeclareSo(Context context, Pair<String, List<String>> pair, @NonNull DynamicLibInfoBean dynamicLibInfoBean, RemoteSoLoaderCallback remoteSoLoaderCallback) {
        if (!this.loadEnable && remoteSoLoaderCallback != null) {
            remoteSoLoaderCallback.onLoadResult(false);
        }
        if (context == null || pair.second == null || !dynamicLibInfoBean.isEnable() || dynamicLibInfoBean.getArchInfo() == null) {
            if (remoteSoLoaderCallback != null) {
                remoteSoLoaderCallback.onLoadResult(false);
                return;
            }
            return;
        }
        this.mCallback = remoteSoLoaderCallback;
        if (checkExitWithValid(context, (List) pair.second, dynamicLibInfoBean.getArchInfo())) {
            remoteSoLoaderCallback.onLoadResult(true);
            return;
        }
        downloadRemoteFile(context, pair, dynamicLibInfoBean.getArchInfo(), context.getExternalCacheDir().getAbsolutePath() + "/JDVideoCacheDir/SoCache/");
    }

    public void setLoadEnable(boolean z) {
        this.loadEnable = z;
        String str = "RemoteSo load enable: " + z;
    }
}
