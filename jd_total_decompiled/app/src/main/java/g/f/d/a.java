package g.f.d;

import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JSONObject a(String str, JSONObject jSONObject) {
        OutputStream outputStream;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream2;
        Throwable th;
        OutputStream outputStream3;
        OutputStream outputStream4;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (Exception e2) {
            e = e2;
            httpURLConnection = null;
            outputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestProperty("version", "1.1");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
            httpURLConnection.connect();
            outputStream3 = httpURLConnection.getOutputStream();
            try {
                outputStream3.write(jSONObject.toString().getBytes());
                outputStream3.flush();
                outputStream3.close();
                bufferedReader3 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } catch (Exception e3) {
                outputStream4 = outputStream3;
                e = e3;
                bufferedReader2 = 0;
            } catch (Throwable th3) {
                httpURLConnection2 = httpURLConnection;
                th = th3;
                bufferedReader = 0;
            }
        } catch (Exception e4) {
            e = e4;
            outputStream2 = null;
            outputStream4 = outputStream2;
            bufferedReader2 = outputStream2;
            try {
                e.printStackTrace();
                if (httpURLConnection != null) {
                }
                if (outputStream4 != null) {
                }
                if (bufferedReader2 != 0) {
                }
                return null;
            } catch (Throwable th4) {
                httpURLConnection2 = httpURLConnection;
                th = th4;
                outputStream3 = outputStream4;
                bufferedReader = bufferedReader2;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (outputStream3 != null) {
                    try {
                        outputStream3.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                if (bufferedReader != 0) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            httpURLConnection2 = httpURLConnection;
            th = th;
            outputStream3 = outputStream;
            bufferedReader = outputStream;
            if (httpURLConnection2 != null) {
            }
            if (outputStream3 != null) {
            }
            if (bufferedReader != 0) {
            }
            throw th;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer("");
            while (true) {
                String readLine = bufferedReader3.readLine();
                if (readLine != null) {
                    stringBuffer.append(new String(readLine.getBytes(), "utf-8"));
                } else {
                    JSONObject jSONObject2 = new JSONObject(stringBuffer.toString());
                    String str2 = "------- http response" + stringBuffer.toString();
                    bufferedReader3.close();
                    httpURLConnection.disconnect();
                    return jSONObject2;
                }
            }
        } catch (Exception e7) {
            e = e7;
            outputStream4 = null;
            bufferedReader2 = bufferedReader3;
            e.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (outputStream4 != null) {
                try {
                    outputStream4.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            if (bufferedReader2 != 0) {
                try {
                    bufferedReader2.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th6) {
            httpURLConnection2 = httpURLConnection;
            th = th6;
            outputStream3 = null;
            bufferedReader = bufferedReader3;
            if (httpURLConnection2 != null) {
            }
            if (outputStream3 != null) {
            }
            if (bufferedReader != 0) {
            }
            throw th;
        }
    }
}
