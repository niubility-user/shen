package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class AbstractH26XTrack extends AbstractTrack {
    public static int BUFFER = 67107840;
    protected List<CompositionTimeToSample.Entry> ctts;
    private DataSource dataSource;
    protected long[] decodingTimes;
    protected List<SampleDependencyTypeBox.Entry> sdtp;
    protected List<Integer> stss;
    protected TrackMetaData trackMetaData;
    boolean tripleZeroIsEndOfSequence;

    /* loaded from: classes12.dex */
    public static class LookAhead {
        ByteBuffer buffer;
        DataSource dataSource;
        long start;
        long bufferStartPos = 0;
        int inBufferPos = 0;

        public LookAhead(DataSource dataSource) throws IOException {
            this.dataSource = dataSource;
            fillBuffer();
        }

        public void discardByte() {
            this.inBufferPos++;
        }

        public void discardNext3AndMarkStart() {
            int i2 = this.inBufferPos + 3;
            this.inBufferPos = i2;
            this.start = this.bufferStartPos + i2;
        }

        public void fillBuffer() throws IOException {
            DataSource dataSource = this.dataSource;
            this.buffer = dataSource.map(this.bufferStartPos, Math.min(dataSource.size() - this.bufferStartPos, AbstractH26XTrack.BUFFER));
        }

        public ByteBuffer getNal() {
            long j2 = this.start;
            long j3 = this.bufferStartPos;
            if (j2 >= j3) {
                this.buffer.position((int) (j2 - j3));
                ByteBuffer slice = this.buffer.slice();
                slice.limit((int) (this.inBufferPos - (this.start - this.bufferStartPos)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }

        public boolean nextThreeEquals000or001orEof(boolean z) throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 3) {
                return this.buffer.get(i2) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && ((this.buffer.get(this.inBufferPos + 2) == 0 && z) || this.buffer.get(this.inBufferPos + 2) == 1);
            } else if (this.bufferStartPos + i2 + 3 > this.dataSource.size()) {
                return this.bufferStartPos + ((long) this.inBufferPos) == this.dataSource.size();
            } else {
                this.bufferStartPos = this.start;
                this.inBufferPos = 0;
                fillBuffer();
                return nextThreeEquals000or001orEof(z);
            }
        }

        public boolean nextThreeEquals001() throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 3) {
                return this.buffer.get(i2) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && this.buffer.get(this.inBufferPos + 2) == 1;
            } else if (this.bufferStartPos + i2 + 3 < this.dataSource.size()) {
                return false;
            } else {
                throw new EOFException();
            }
        }
    }

    public AbstractH26XTrack(DataSource dataSource, boolean z) {
        super(dataSource.toString());
        this.ctts = new ArrayList();
        this.sdtp = new ArrayList();
        this.stss = new ArrayList();
        this.trackMetaData = new TrackMetaData();
        this.tripleZeroIsEndOfSequence = true;
        this.dataSource = dataSource;
        this.tripleZeroIsEndOfSequence = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static InputStream cleanBuffer(InputStream inputStream) {
        return new CleanInputStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] toArray(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        int remaining = duplicate.remaining();
        byte[] bArr = new byte[remaining];
        duplicate.get(bArr, 0, remaining);
        return bArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.dataSource.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Sample createSampleObject(List<? extends ByteBuffer> list) {
        byte[] bArr = new byte[list.size() * 4];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Iterator<? extends ByteBuffer> it = list.iterator();
        while (it.hasNext()) {
            wrap.putInt(it.next().remaining());
        }
        ByteBuffer[] byteBufferArr = new ByteBuffer[list.size() * 2];
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = i2 * 2;
            byteBufferArr[i3] = ByteBuffer.wrap(bArr, i2 * 4, 4);
            byteBufferArr[i3 + 1] = list.get(i2);
        }
        return new SampleImpl(byteBufferArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer findNextNal(LookAhead lookAhead) throws IOException {
        while (!lookAhead.nextThreeEquals001()) {
            try {
                lookAhead.discardByte();
            } catch (EOFException unused) {
                return null;
            }
        }
        lookAhead.discardNext3AndMarkStart();
        while (!lookAhead.nextThreeEquals000or001orEof(this.tripleZeroIsEndOfSequence)) {
            lookAhead.discardByte();
        }
        return lookAhead.getNal();
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.ctts;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.sdtp;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.decodingTimes;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        long[] jArr = new long[this.stss.size()];
        for (int i2 = 0; i2 < this.stss.size(); i2++) {
            jArr[i2] = this.stss.get(i2).intValue();
        }
        return jArr;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }

    public AbstractH26XTrack(DataSource dataSource) {
        this(dataSource, true);
    }
}
