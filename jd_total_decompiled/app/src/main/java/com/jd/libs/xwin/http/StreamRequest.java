package com.jd.libs.xwin.http;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import i.a.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class StreamRequest extends BaseRequest {
    private StreamListener listener;

    @Keep
    /* loaded from: classes16.dex */
    public interface StreamListener {
        void onConnect(int i2, Map<String, List<String>> map, InputStream inputStream);

        void onError(int i2, Map<String, List<String>> map, String str);

        void onStart();
    }

    /* loaded from: classes16.dex */
    public class a implements a.InterfaceC0843a {
        public a() {
        }

        @Override // i.a.a.InterfaceC0843a
        public void a() {
            StreamRequest.this.disconnect();
        }
    }

    public StreamRequest(String str) {
        super(str);
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onError(int i2, String str) {
        StreamListener streamListener = this.listener;
        if (streamListener != null) {
            streamListener.onError(i2, null, str);
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onStart() {
        StreamListener streamListener = this.listener;
        if (streamListener != null) {
            streamListener.onStart();
        }
    }

    @Override // com.jd.libs.xwin.http.BaseRequest
    public void onSuccess(int i2, Map<String, List<String>> map, int i3, @Nullable InputStream inputStream) {
        if (i2 != 200) {
            StreamListener streamListener = this.listener;
            if (streamListener != null) {
                streamListener.onError(i2, map, "code is not 200");
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            disconnect();
            return;
        }
        i.a.a aVar = null;
        if (inputStream != null) {
            try {
                aVar = new i.a.a(inputStream, new a());
            } catch (Exception e3) {
                e3.printStackTrace();
                StreamListener streamListener2 = this.listener;
                if (streamListener2 != null) {
                    streamListener2.onError(-1, map, "write file error: " + e3.getMessage());
                }
                try {
                    if (aVar != null) {
                        aVar.close();
                    } else if (inputStream == null) {
                        return;
                    } else {
                        inputStream.close();
                        disconnect();
                    }
                    return;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
        }
        StreamListener streamListener3 = this.listener;
        if (streamListener3 != null) {
            streamListener3.onConnect(i2, map, aVar);
        }
    }

    public void setListener(StreamListener streamListener) {
        this.listener = streamListener;
    }
}
