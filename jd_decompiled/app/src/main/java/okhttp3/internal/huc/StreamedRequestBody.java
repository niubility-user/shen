package okhttp3.internal.huc;

import java.io.IOException;
import okhttp3.internal.http.UnrepeatableRequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Pipe;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes11.dex */
final class StreamedRequestBody extends OutputStreamRequestBody implements UnrepeatableRequestBody {
    private final Pipe pipe;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamedRequestBody(long j2) {
        Pipe pipe = new Pipe(IjkMediaMeta.AV_CH_TOP_FRONT_CENTER);
        this.pipe = pipe;
        initOutputStream(Okio.buffer(pipe.sink()), j2);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Buffer buffer = new Buffer();
        while (this.pipe.source().read(buffer, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) != -1) {
            bufferedSink.write(buffer, buffer.size());
        }
    }
}
