package com.jingdong.jdreact.plugin.fileUpload;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes13.dex */
public class SimpleHttpClient {
    private static String Enter = "\r\n";
    public static int POOL_SIZE = 8;
    private static final String TAG = "SimpleHttpClient";
    private static ExecutorService sExecutorService = Executors.newFixedThreadPool(8);

    /* loaded from: classes13.dex */
    public interface HttpCallback<T> {
        void onError(T t);

        void onSuccess(T t);
    }

    public static int checkSelfPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str);
    }

    public static void doGet(final String str, final String str2, final String str3, final String str4, final String str5, final HttpCallback<String> httpCallback) {
        sExecutorService.submit(new Runnable() { // from class: com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient.1
            /* JADX WARN: Code restructure failed: missing block: B:49:0x01e8, code lost:
                if (r2 != null) goto L50;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                InputStream fileInputStream;
                String str6 = str3;
                String str7 = str4;
                String str8 = str5;
                if (TextUtils.isEmpty(str8)) {
                    str8 = "Boundary-bled-4060-99b9-fca7ff59c113";
                }
                File file = new File(str2);
                if (!file.exists()) {
                    httpCallback.onError("file not found");
                    return;
                }
                if (str2.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                    try {
                        if (SimpleHttpClient.hasGrantedPermission(JDReactHelper.newInstance().getApplicationContext())) {
                            fileInputStream = JDReactHelper.newInstance().getApplicationContext().getContentResolver().openInputStream(Uri.parse(str2));
                        } else {
                            httpCallback.onError("permission issue");
                            return;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        httpCallback.onError(e2.getMessage());
                        return;
                    }
                } else {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e3) {
                        String unused = SimpleHttpClient.TAG;
                        e3.toString();
                        httpCallback.onError(e3.getMessage());
                        return;
                    }
                }
                try {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        String unused2 = SimpleHttpClient.TAG;
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        try {
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setUseCaches(false);
                            httpURLConnection.setInstanceFollowRedirects(true);
                            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data;boundary=" + str8);
                            httpURLConnection.setConnectTimeout(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
                            httpURLConnection.setReadTimeout(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
                            try {
                                httpURLConnection.connect();
                                try {
                                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                                    if (str6 == null) {
                                        str6 = "--" + str8 + SimpleHttpClient.Enter + "Content-Type: application/octet-stream" + SimpleHttpClient.Enter + "Content-Disposition: form-data; filename=\"" + file.getName() + "\"; name=\"file\"" + SimpleHttpClient.Enter + SimpleHttpClient.Enter;
                                    }
                                    if (str7 == null) {
                                        str7 = SimpleHttpClient.Enter + "--" + str8 + SimpleHttpClient.Enter + "Content-Type: text/plain" + SimpleHttpClient.Enter + "Content-Disposition: form-data; name=\"parameter\"" + SimpleHttpClient.Enter + SimpleHttpClient.Enter + "hifreud" + SimpleHttpClient.Enter + "--" + str8 + "--";
                                    }
                                    try {
                                        int available = fileInputStream.available();
                                        String unused3 = SimpleHttpClient.TAG;
                                        String str9 = "---------file length" + available;
                                        String unused4 = SimpleHttpClient.TAG;
                                        String str10 = "---------head" + str6 + "      " + str7;
                                        try {
                                            dataOutputStream.writeBytes(str6);
                                            String str11 = JDReactConstant.FAILED;
                                            try {
                                                if (available > 10000) {
                                                    byte[] bArr = new byte[10000];
                                                    int i2 = 0;
                                                    while (true) {
                                                        int read = fileInputStream.read(bArr);
                                                        if (read == -1) {
                                                            break;
                                                        }
                                                        dataOutputStream.write(bArr, 0, read);
                                                        i2 += read;
                                                        String unused5 = SimpleHttpClient.TAG;
                                                        String str12 = "--------upload" + i2;
                                                    }
                                                } else {
                                                    byte[] bArr2 = new byte[available];
                                                    fileInputStream.read(bArr2);
                                                    dataOutputStream.write(bArr2);
                                                }
                                                dataOutputStream.writeBytes(str7);
                                                dataOutputStream.flush();
                                                dataOutputStream.close();
                                                fileInputStream.close();
                                                String unused6 = SimpleHttpClient.TAG;
                                                StringBuilder sb = new StringBuilder();
                                                InputStream inputStream = null;
                                                try {
                                                    inputStream = httpURLConnection.getInputStream();
                                                    byte[] bArr3 = new byte[2048];
                                                    while (true) {
                                                        int read2 = inputStream.read(bArr3);
                                                        if (read2 <= 0) {
                                                            break;
                                                        }
                                                        sb.append(new String(bArr3, 0, read2));
                                                    }
                                                } catch (Exception unused7) {
                                                    if (inputStream != null) {
                                                        inputStream.close();
                                                    }
                                                    String unused8 = SimpleHttpClient.TAG;
                                                    String str13 = "--------ResponseBody" + ((Object) sb);
                                                    String responseMessage = httpURLConnection.getResponseMessage();
                                                    String unused9 = SimpleHttpClient.TAG;
                                                    String str14 = "--------ResponseMessage" + responseMessage;
                                                    if (!"OK".equalsIgnoreCase(responseMessage) && httpURLConnection.getResponseCode() != 200) {
                                                        httpCallback.onError(JDReactConstant.FAILED);
                                                        httpURLConnection.disconnect();
                                                    }
                                                    httpCallback.onSuccess(sb.toString());
                                                    httpURLConnection.disconnect();
                                                }
                                            } catch (Exception e4) {
                                                String unused10 = SimpleHttpClient.TAG;
                                                e4.toString();
                                                httpURLConnection.disconnect();
                                                String message = e4.getMessage();
                                                if (!TextUtils.isEmpty(message)) {
                                                    str11 = message;
                                                }
                                                httpCallback.onError(str11);
                                            }
                                        } catch (IOException e5) {
                                            String unused11 = SimpleHttpClient.TAG;
                                            e5.toString();
                                            httpURLConnection.disconnect();
                                            httpCallback.onError(e5.getMessage());
                                        }
                                    } catch (IOException e6) {
                                        String unused12 = SimpleHttpClient.TAG;
                                        e6.toString();
                                        httpURLConnection.disconnect();
                                        httpCallback.onError(e6.getMessage());
                                    }
                                } catch (IOException e7) {
                                    String unused13 = SimpleHttpClient.TAG;
                                    e7.toString();
                                    httpURLConnection.disconnect();
                                    httpCallback.onError(e7.getMessage());
                                }
                            } catch (IOException e8) {
                                String unused14 = SimpleHttpClient.TAG;
                                e8.toString();
                                httpURLConnection.disconnect();
                                httpCallback.onError(e8.getMessage());
                            }
                        } catch (ProtocolException e9) {
                            String unused15 = SimpleHttpClient.TAG;
                            e9.toString();
                            httpURLConnection.disconnect();
                            httpCallback.onError(e9.getMessage());
                        }
                    } catch (IOException e10) {
                        String unused16 = SimpleHttpClient.TAG;
                        e10.toString();
                        httpCallback.onError(e10.getMessage());
                    }
                } catch (MalformedURLException e11) {
                    String unused17 = SimpleHttpClient.TAG;
                    e11.toString();
                    httpCallback.onError(e11.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasGrantedPermission(Context context) {
        return Build.VERSION.SDK_INT <= 28 || checkSelfPermission(context, "android.permission.READ_MEDIA_IMAGES") == 0;
    }
}
