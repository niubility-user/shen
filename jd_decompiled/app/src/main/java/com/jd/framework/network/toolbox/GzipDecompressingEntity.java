package com.jd.framework.network.toolbox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* loaded from: classes13.dex */
public class GzipDecompressingEntity extends DecompressingEntity {
    public GzipDecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jd.framework.network.toolbox.DecompressingEntity
    public InputStream decorate(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }

    @Override // com.jd.framework.network.toolbox.DecompressingEntity
    public /* bridge */ /* synthetic */ InputStream getContent() throws IOException {
        return super.getContent();
    }

    public Header getContentEncoding() {
        return null;
    }

    public long getContentLength() {
        return -1L;
    }

    @Override // com.jd.framework.network.toolbox.DecompressingEntity
    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }
}
