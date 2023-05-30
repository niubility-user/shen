package com.googlecode.mp4parser.authoring.builder;

/* loaded from: classes12.dex */
public class ByteBufferHelper {
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0086, code lost:
        if ((r1 instanceof java.nio.MappedByteBuffer) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008e, code lost:
        if ((r0.get(r2) instanceof java.nio.MappedByteBuffer) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a9, code lost:
        if (((java.nio.ByteBuffer) r0.get(r2)).limit() != (((java.nio.ByteBuffer) r0.get(r2)).capacity() - r1.capacity())) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ab, code lost:
        r2 = (java.nio.ByteBuffer) r0.get(r2);
        r2.limit(r1.limit() + r2.limit());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.nio.ByteBuffer> mergeAdjacentBuffers(java.util.List<java.nio.ByteBuffer> r6) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r6.size()
            r0.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        Ld:
            boolean r1 = r6.hasNext()
            if (r1 != 0) goto L14
            return r0
        L14:
            java.lang.Object r1 = r6.next()
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            int r2 = r0.size()
            int r2 = r2 + (-1)
            if (r2 < 0) goto L82
            boolean r3 = r1.hasArray()
            if (r3 == 0) goto L82
            java.lang.Object r3 = r0.get(r2)
            java.nio.ByteBuffer r3 = (java.nio.ByteBuffer) r3
            boolean r3 = r3.hasArray()
            if (r3 == 0) goto L82
            byte[] r3 = r1.array()
            java.lang.Object r4 = r0.get(r2)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            byte[] r4 = r4.array()
            if (r3 != r4) goto L82
            java.lang.Object r3 = r0.get(r2)
            java.nio.ByteBuffer r3 = (java.nio.ByteBuffer) r3
            int r3 = r3.arrayOffset()
            java.lang.Object r4 = r0.get(r2)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            int r4 = r4.limit()
            int r3 = r3 + r4
            int r4 = r1.arrayOffset()
            if (r3 != r4) goto L82
            java.lang.Object r2 = r0.remove(r2)
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            byte[] r3 = r1.array()
            int r4 = r2.arrayOffset()
            int r2 = r2.limit()
            int r1 = r1.limit()
            int r2 = r2 + r1
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r3, r4, r2)
            java.nio.ByteBuffer r1 = r1.slice()
            r0.add(r1)
            goto Ld
        L82:
            if (r2 < 0) goto Lbf
            boolean r3 = r1 instanceof java.nio.MappedByteBuffer
            if (r3 == 0) goto Lbf
            java.lang.Object r3 = r0.get(r2)
            boolean r3 = r3 instanceof java.nio.MappedByteBuffer
            if (r3 == 0) goto Lbf
            java.lang.Object r3 = r0.get(r2)
            java.nio.ByteBuffer r3 = (java.nio.ByteBuffer) r3
            int r3 = r3.limit()
            java.lang.Object r4 = r0.get(r2)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            int r4 = r4.capacity()
            int r5 = r1.capacity()
            int r4 = r4 - r5
            if (r3 != r4) goto Lbf
            java.lang.Object r2 = r0.get(r2)
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            int r1 = r1.limit()
            int r3 = r2.limit()
            int r1 = r1 + r3
            r2.limit(r1)
            goto Ld
        Lbf:
            r1.reset()
            r0.add(r1)
            goto Ld
        */
        throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.builder.ByteBufferHelper.mergeAdjacentBuffers(java.util.List):java.util.List");
    }
}
