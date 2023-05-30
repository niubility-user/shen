package okhttp3.internal.platform;

import android.annotation.SuppressLint;
import android.net.ssl.SSLSockets;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"NewApi"})
/* loaded from: classes11.dex */
public class Android10Platform extends AndroidPlatform {
    Android10Platform(Class<?> cls) {
        super(cls, null, null, null, null);
    }

    @Nullable
    public static Platform buildIfSupported() {
        if (Platform.isAndroid()) {
            try {
                if (AndroidPlatform.getSdkInt() >= 29) {
                    return new Android10Platform(Class.forName("com.android.org.conscrypt.SSLParametersImpl"));
                }
            } catch (ClassNotFoundException unused) {
            }
            return null;
        }
        return null;
    }

    private void enableSessionTickets(SSLSocket sSLSocket) {
        if (SSLSockets.isSupportedSocket(sSLSocket)) {
            SSLSockets.setUseSessionTickets(sSLSocket, true);
        }
    }

    @Override // okhttp3.internal.platform.AndroidPlatform, okhttp3.internal.platform.Platform
    @SuppressLint({"NewApi"})
    @IgnoreJRERequirement
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) throws IOException {
        try {
            enableSessionTickets(sSLSocket);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            sSLParameters.setApplicationProtocols((String[]) Platform.alpnProtocolNames(list).toArray(new String[0]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalArgumentException e2) {
            throw new IOException("Android internal error", e2);
        }
    }

    @Override // okhttp3.internal.platform.AndroidPlatform, okhttp3.internal.platform.Platform
    @Nullable
    @IgnoreJRERequirement
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        String applicationProtocol = sSLSocket.getApplicationProtocol();
        if (applicationProtocol == null || applicationProtocol.isEmpty()) {
            return null;
        }
        return applicationProtocol;
    }
}
