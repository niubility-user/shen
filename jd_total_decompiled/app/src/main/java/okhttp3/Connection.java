package okhttp3;

import java.net.Socket;
import javax.annotation.Nullable;

/* loaded from: classes11.dex */
public interface Connection {
    @Nullable
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
