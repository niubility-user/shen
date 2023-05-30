package com.facebook.react.devsupport;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes12.dex */
public class MultipartStreamReader {
    private static final String CRLF = "\r\n";
    private final String mBoundary;
    private long mLastProgressEvent;
    private final BufferedSource mSource;

    /* loaded from: classes12.dex */
    public interface ChunkListener {
        void onChunkComplete(Map<String, String> map, Buffer buffer, boolean z) throws IOException;

        void onChunkProgress(Map<String, String> map, long j2, long j3) throws IOException;
    }

    public MultipartStreamReader(BufferedSource bufferedSource, String str) {
        this.mSource = bufferedSource;
        this.mBoundary = str;
    }

    private void emitChunk(Buffer buffer, boolean z, ChunkListener chunkListener) throws IOException {
        long indexOf = buffer.indexOf(ByteString.encodeUtf8("\r\n\r\n"));
        if (indexOf == -1) {
            chunkListener.onChunkComplete(null, buffer, z);
            return;
        }
        Buffer buffer2 = new Buffer();
        Buffer buffer3 = new Buffer();
        buffer.read(buffer2, indexOf);
        buffer.skip(r0.size());
        buffer.readAll(buffer3);
        chunkListener.onChunkComplete(parseHeaders(buffer2), buffer3, z);
    }

    private void emitProgress(Map<String, String> map, long j2, boolean z, ChunkListener chunkListener) throws IOException {
        if (map == null || chunkListener == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastProgressEvent > 16 || z) {
            this.mLastProgressEvent = currentTimeMillis;
            chunkListener.onChunkProgress(map, j2, map.get(HttpHeaders.CONTENT_LENGTH) != null ? Long.parseLong(map.get(HttpHeaders.CONTENT_LENGTH)) : 0L);
        }
    }

    private Map<String, String> parseHeaders(Buffer buffer) {
        HashMap hashMap = new HashMap();
        for (String str : buffer.readUtf8().split(CRLF)) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                hashMap.put(str.substring(0, indexOf).trim(), str.substring(indexOf + 1).trim());
            }
        }
        return hashMap;
    }

    public boolean readAllParts(ChunkListener chunkListener) throws IOException {
        boolean z;
        long j2;
        ByteString encodeUtf8 = ByteString.encodeUtf8("\r\n--" + this.mBoundary + CRLF);
        ByteString encodeUtf82 = ByteString.encodeUtf8("\r\n--" + this.mBoundary + "--" + CRLF);
        ByteString encodeUtf83 = ByteString.encodeUtf8("\r\n\r\n");
        Buffer buffer = new Buffer();
        long j3 = 0L;
        long j4 = 0L;
        long j5 = 0L;
        Map<String, String> map = null;
        while (true) {
            long max = Math.max(j3 - encodeUtf82.size(), j4);
            long indexOf = buffer.indexOf(encodeUtf8, max);
            if (indexOf == -1) {
                indexOf = buffer.indexOf(encodeUtf82, max);
                z = true;
            } else {
                z = false;
            }
            if (indexOf == -1) {
                long size = buffer.size();
                if (map == null) {
                    long indexOf2 = buffer.indexOf(encodeUtf83, max);
                    if (indexOf2 >= 0) {
                        this.mSource.read(buffer, indexOf2);
                        Buffer buffer2 = new Buffer();
                        j2 = j4;
                        buffer.copyTo(buffer2, max, indexOf2 - max);
                        j5 = buffer2.size() + encodeUtf83.size();
                        map = parseHeaders(buffer2);
                    } else {
                        j2 = j4;
                    }
                } else {
                    j2 = j4;
                    emitProgress(map, buffer.size() - j5, false, chunkListener);
                }
                if (this.mSource.read(buffer, 4096) <= 0) {
                    return false;
                }
                j3 = size;
                j4 = j2;
            } else {
                long j6 = j4;
                long j7 = indexOf - j6;
                if (j6 > 0) {
                    Buffer buffer3 = new Buffer();
                    buffer.skip(j6);
                    buffer.read(buffer3, j7);
                    emitProgress(map, buffer3.size() - j5, true, chunkListener);
                    emitChunk(buffer3, z, chunkListener);
                    j5 = 0;
                    map = null;
                } else {
                    buffer.skip(indexOf);
                }
                if (z) {
                    return true;
                }
                j4 = encodeUtf8.size();
                j3 = j4;
            }
        }
    }
}
