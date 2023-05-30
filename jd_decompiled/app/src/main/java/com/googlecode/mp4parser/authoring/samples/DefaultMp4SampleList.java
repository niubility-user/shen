package com.googlecode.mp4parser.authoring.samples;

import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.TrackBox;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes12.dex */
public class DefaultMp4SampleList extends AbstractList<Sample> {
    private static final Logger LOG = Logger.getLogger(DefaultMp4SampleList.class);
    SoftReference<ByteBuffer>[] cache;
    int[] chunkNumsStartSampleNum;
    long[] chunkOffsets;
    long[] chunkSizes;
    int lastChunk = 0;
    long[][] sampleOffsetsWithinChunks;
    SampleSizeBox ssb;
    Container topLevel;
    TrackBox trackBox;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class SampleImpl implements Sample {
        private int index;

        public SampleImpl(int i2) {
            this.index = i2;
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public synchronized ByteBuffer asByteBuffer() {
            long j2;
            ByteBuffer byteBuffer;
            int chunkForSample = DefaultMp4SampleList.this.getChunkForSample(this.index);
            DefaultMp4SampleList defaultMp4SampleList = DefaultMp4SampleList.this;
            SoftReference<ByteBuffer> softReference = defaultMp4SampleList.cache[chunkForSample];
            int i2 = defaultMp4SampleList.chunkNumsStartSampleNum[chunkForSample] - 1;
            long j3 = chunkForSample;
            long[] jArr = defaultMp4SampleList.sampleOffsetsWithinChunks[CastUtils.l2i(j3)];
            j2 = jArr[this.index - i2];
            if (softReference == null || (byteBuffer = softReference.get()) == null) {
                try {
                    DefaultMp4SampleList defaultMp4SampleList2 = DefaultMp4SampleList.this;
                    byteBuffer = defaultMp4SampleList2.topLevel.getByteBuffer(defaultMp4SampleList2.chunkOffsets[CastUtils.l2i(j3)], jArr[jArr.length - 1] + DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex((i2 + jArr.length) - 1));
                    DefaultMp4SampleList.this.cache[chunkForSample] = new SoftReference<>(byteBuffer);
                } catch (IOException e2) {
                    StringWriter stringWriter = new StringWriter();
                    e2.printStackTrace(new PrintWriter(stringWriter));
                    DefaultMp4SampleList.LOG.logError(stringWriter.toString());
                    throw new IndexOutOfBoundsException(e2.getMessage());
                }
            }
            return (ByteBuffer) ((ByteBuffer) byteBuffer.duplicate().position(CastUtils.l2i(j2))).slice().limit(CastUtils.l2i(DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index)));
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            return DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index);
        }

        public String toString() {
            return "Sample(index: " + this.index + " size: " + DefaultMp4SampleList.this.ssb.getSampleSizeAtIndex(this.index) + ")";
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
            writableByteChannel.write(asByteBuffer());
        }
    }

    public DefaultMp4SampleList(long j2, Container container) {
        int i2;
        this.trackBox = null;
        this.cache = null;
        int i3 = 0;
        this.topLevel = container;
        for (TrackBox trackBox : ((MovieBox) container.getBoxes(MovieBox.class).get(0)).getBoxes(TrackBox.class)) {
            if (trackBox.getTrackHeaderBox().getTrackId() == j2) {
                this.trackBox = trackBox;
            }
        }
        TrackBox trackBox2 = this.trackBox;
        if (trackBox2 != null) {
            long[] chunkOffsets = trackBox2.getSampleTableBox().getChunkOffsetBox().getChunkOffsets();
            this.chunkOffsets = chunkOffsets;
            this.chunkSizes = new long[chunkOffsets.length];
            SoftReference<ByteBuffer>[] softReferenceArr = new SoftReference[chunkOffsets.length];
            this.cache = softReferenceArr;
            Arrays.fill(softReferenceArr, new SoftReference(null));
            this.sampleOffsetsWithinChunks = new long[this.chunkOffsets.length];
            this.ssb = this.trackBox.getSampleTableBox().getSampleSizeBox();
            List<SampleToChunkBox.Entry> entries = this.trackBox.getSampleTableBox().getSampleToChunkBox().getEntries();
            SampleToChunkBox.Entry[] entryArr = (SampleToChunkBox.Entry[]) entries.toArray(new SampleToChunkBox.Entry[entries.size()]);
            SampleToChunkBox.Entry entry = entryArr[0];
            long firstChunk = entry.getFirstChunk();
            int l2i = CastUtils.l2i(entry.getSamplesPerChunk());
            int size = size();
            int i4 = 0;
            int i5 = 1;
            int i6 = 0;
            int i7 = 1;
            do {
                i4++;
                if (i4 == firstChunk) {
                    if (entryArr.length > i5) {
                        SampleToChunkBox.Entry entry2 = entryArr[i5];
                        i6 = l2i;
                        l2i = CastUtils.l2i(entry2.getSamplesPerChunk());
                        i5++;
                        firstChunk = entry2.getFirstChunk();
                    } else {
                        i6 = l2i;
                        l2i = -1;
                        firstChunk = Long.MAX_VALUE;
                    }
                }
                this.sampleOffsetsWithinChunks[i4 - 1] = new long[i6];
                i7 += i6;
            } while (i7 <= size);
            this.chunkNumsStartSampleNum = new int[i4 + 1];
            SampleToChunkBox.Entry entry3 = entryArr[0];
            long firstChunk2 = entry3.getFirstChunk();
            int l2i2 = CastUtils.l2i(entry3.getSamplesPerChunk());
            int i8 = 0;
            int i9 = 1;
            int i10 = 1;
            int i11 = 0;
            while (true) {
                i2 = i8 + 1;
                this.chunkNumsStartSampleNum[i8] = i9;
                if (i2 == firstChunk2) {
                    if (entryArr.length > i10) {
                        SampleToChunkBox.Entry entry4 = entryArr[i10];
                        i11 = l2i2;
                        i10++;
                        l2i2 = CastUtils.l2i(entry4.getSamplesPerChunk());
                        firstChunk2 = entry4.getFirstChunk();
                    } else {
                        i11 = l2i2;
                        l2i2 = -1;
                        firstChunk2 = Long.MAX_VALUE;
                    }
                }
                i9 += i11;
                if (i9 > size) {
                    break;
                }
                i8 = i2;
            }
            this.chunkNumsStartSampleNum[i2] = Integer.MAX_VALUE;
            long j3 = 0;
            for (int i12 = 1; i12 <= this.ssb.getSampleCount(); i12++) {
                while (i12 == this.chunkNumsStartSampleNum[i3]) {
                    i3++;
                    j3 = 0;
                }
                long[] jArr = this.chunkSizes;
                int i13 = i3 - 1;
                int i14 = i12 - 1;
                jArr[i13] = jArr[i13] + this.ssb.getSampleSizeAtIndex(i14);
                this.sampleOffsetsWithinChunks[i13][i12 - this.chunkNumsStartSampleNum[i13]] = j3;
                j3 += this.ssb.getSampleSizeAtIndex(i14);
            }
            return;
        }
        throw new RuntimeException("This MP4 does not contain track " + j2);
    }

    synchronized int getChunkForSample(int i2) {
        int i3 = i2 + 1;
        int[] iArr = this.chunkNumsStartSampleNum;
        int i4 = this.lastChunk;
        if (i3 >= iArr[i4] && i3 < iArr[i4 + 1]) {
            return i4;
        }
        if (i3 < iArr[i4]) {
            this.lastChunk = 0;
            while (true) {
                int[] iArr2 = this.chunkNumsStartSampleNum;
                int i5 = this.lastChunk;
                if (iArr2[i5 + 1] > i3) {
                    return i5;
                }
                this.lastChunk = i5 + 1;
            }
        } else {
            this.lastChunk = i4 + 1;
            while (true) {
                int[] iArr3 = this.chunkNumsStartSampleNum;
                int i6 = this.lastChunk;
                if (iArr3[i6 + 1] > i3) {
                    return i6;
                }
                this.lastChunk = i6 + 1;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return CastUtils.l2i(this.trackBox.getSampleTableBox().getSampleSizeBox().getSampleCount());
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i2) {
        if (i2 < this.ssb.getSampleCount()) {
            return new SampleImpl(i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
