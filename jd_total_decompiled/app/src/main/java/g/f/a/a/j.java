package g.f.a.a;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes12.dex */
public class j {
    private static String a = "";
    private static Map<String, String> b;

    private HttpURLConnection a(Map<String, String> map) {
        String str = a;
        if (str != null && !"".equals(str)) {
            try {
                URL url = new URL(a);
                int hashCode = a.hashCode();
                g.f.a.b.b.b("SpeechUploadRecordData", " URL\uff1a " + a);
                g.f.a.b.b.b(hashCode + "-Url", a);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setChunkedStreamingMode(32768);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setReadTimeout(5000);
                c(httpURLConnection, map);
                httpURLConnection.connect();
                return httpURLConnection;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private String b(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                g.f.a.b.b.b("SpeechUploadRecordData", "getResponse: " + sb.toString());
                return sb.toString();
            }
        }
    }

    private void c(HttpURLConnection httpURLConnection, Map<String, String> map) {
        int hashCode = a.hashCode();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            g.f.a.b.b.b(hashCode + "-Head", entry.getKey() + ":" + entry.getValue());
        }
    }

    private a f(int i2, byte[] bArr) {
        int i3;
        HashMap hashMap;
        HttpURLConnection a2;
        int i4;
        String str = "";
        try {
            hashMap = new HashMap(b);
            hashMap.put("Sequence-Id", Integer.toString(i2));
            if (i2 != 1 && i2 != -1) {
                hashMap.remove("Property");
            }
            a2 = a(hashMap);
        } catch (SocketTimeoutException e2) {
            e2.printStackTrace();
            i3 = -1004;
        } catch (IOException e3) {
            e3.printStackTrace();
            i3 = -1008;
        }
        if (a2 == null) {
            return new a(-1004);
        }
        g.f.a.b.b.b("SpeechUploadRecordData", "send param: " + hashMap.toString());
        g.f.a.b.b.b("SpeechUploadRecordData", "send Data len: " + bArr.length);
        int hashCode = a.hashCode();
        g.f.a.b.b.b(hashCode + "-Body", new String(bArr));
        OutputStream outputStream = a2.getOutputStream();
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
        int responseCode = a2.getResponseCode();
        g.f.a.b.b.b("SpeechUploadRecordData", "12233 errCode : " + responseCode);
        Map<String, List<String>> headerFields = a2.getHeaderFields();
        for (String str2 : headerFields.keySet()) {
            if (!TextUtils.isEmpty(str2)) {
                List<String> list = headerFields.get(str2);
                StringBuilder sb = new StringBuilder(str2);
                sb.append(":");
                if (list != null) {
                    for (int i5 = 0; i5 < list.size(); i5++) {
                        String str3 = list.get(i5);
                        if (i5 != 0) {
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                        sb.append(str3);
                    }
                    g.f.a.b.b.b(hashCode + "-ResponseHeade", sb.toString());
                }
            }
        }
        if (responseCode == 302) {
            String str4 = hashCode + "-ResponseCode";
            g.f.a.b.b.b(str4, responseCode + ",location:" + a2.getHeaderField(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID));
        } else {
            g.f.a.b.b.b(hashCode + "-ResponseCode", responseCode + "");
        }
        if (responseCode != 200) {
            if (responseCode == 451) {
                i4 = IMediaPlayer.MEDIA_ERROR_UNSUPPORTED;
            } else {
                a = "https://asrapi-base.jd.com/asr";
                i4 = IMediaPlayer.MEDIA_ERROR_MALFORMED;
            }
            return new a(i4);
        }
        g.f.a.b.b.b(hashCode + "-Body", new String(bArr));
        str = b(a2.getInputStream());
        g.f.a.b.b.b(hashCode + "-Response", str);
        i3 = 0;
        return new a(str, i3);
    }

    public void d(String str, Map<String, String> map) {
        g.f.a.b.b.b("SpeechUploadRecordData create URL \uff1a " + a, new String[0]);
        if (!a.equals("https://asrapi-base.jd.com/asr") || a.equals("")) {
            a = str;
        }
        b = map;
    }

    public a e(int i2, byte[] bArr) {
        a f2 = f(i2, bArr);
        return f2.a() == -1004 ? f(i2, bArr) : f2;
    }
}
