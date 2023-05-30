package com.jd.libs.xwin.http;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public class b extends BaseRequest {

    /* renamed from: g  reason: collision with root package name */
    private a f6239g;

    /* loaded from: classes16.dex */
    public interface a {
        void onError(int i2, Map<String, List<String>> map, String str);

        void onRedirect(int i2, Map<String, List<String>> map, String str);

        void onStart();

        void onSusses(int i2, Map<String, List<String>> map, String str);
    }

    public b(String str) {
        super(str);
    }

    public void a(a aVar) {
        this.f6239g = aVar;
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onError(int i2, String str) {
        a aVar = this.f6239g;
        if (aVar != null) {
            aVar.onError(i2, null, str);
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onRedirect(int i2, Map<String, List<String>> map, String str) {
        a aVar = this.f6239g;
        if (aVar != null) {
            aVar.onRedirect(i2, map, str);
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onStart() {
        a aVar = this.f6239g;
        if (aVar != null) {
            aVar.onStart();
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onSuccess(int i2, Map<String, List<String>> map, int i3, InputStream inputStream) {
        StringBuilder sb;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.f6239g == null) {
            if (inputStream != null) {
                inputStream.close();
            }
            disconnect();
        }
        BufferedReader bufferedReader = null;
        if (this.mMethod != 261) {
            StringBuilder sb2 = new StringBuilder();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb2.append(readLine);
                    sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                } catch (Exception unused) {
                    onError(-1, "read stream error");
                }
            }
            bufferedReader = bufferedReader2;
            sb = sb2;
        } else {
            sb = null;
        }
        if (i2 == 200) {
            this.f6239g.onSusses(i2, map, sb != null ? sb.toString() : "");
        } else {
            this.f6239g.onError(i2, map, bufferedReader != null ? bufferedReader.toString() : "");
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        disconnect();
        disconnect();
    }
}
