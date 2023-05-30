package com.googlecode.mp4parser.authoring.samples;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackExtendsBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Path;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class FragmentedMp4SampleList extends AbstractList<Sample> {
    private List<TrackFragmentBox> allTrafs;
    private int[] firstSamples;
    IsoFile[] fragments;
    private SoftReference<Sample>[] sampleCache;
    Container topLevel;
    TrackBox trackBox;
    TrackExtendsBox trex;
    private Map<TrackRunBox, SoftReference<ByteBuffer>> trunDataCache = new HashMap();
    private int size_ = -1;

    public FragmentedMp4SampleList(long j2, Container container, IsoFile... isoFileArr) {
        this.trackBox = null;
        this.trex = null;
        this.topLevel = container;
        this.fragments = isoFileArr;
        for (TrackBox trackBox : Path.getPaths(container, "moov[0]/trak")) {
            if (trackBox.getTrackHeaderBox().getTrackId() == j2) {
                this.trackBox = trackBox;
            }
        }
        if (this.trackBox != null) {
            for (TrackExtendsBox trackExtendsBox : Path.getPaths(container, "moov[0]/mvex[0]/trex")) {
                if (trackExtendsBox.getTrackId() == this.trackBox.getTrackHeaderBox().getTrackId()) {
                    this.trex = trackExtendsBox;
                }
            }
            this.sampleCache = (SoftReference[]) Array.newInstance(SoftReference.class, size());
            initAllFragments();
            return;
        }
        throw new RuntimeException("This MP4 does not contain track " + j2);
    }

    private int getTrafSize(TrackFragmentBox trackFragmentBox) {
        List<Box> boxes = trackFragmentBox.getBoxes();
        int i2 = 0;
        for (int i3 = 0; i3 < boxes.size(); i3++) {
            Box box = boxes.get(i3);
            if (box instanceof TrackRunBox) {
                i2 += CastUtils.l2i(((TrackRunBox) box).getSampleCount());
            }
        }
        return i2;
    }

    private List<TrackFragmentBox> initAllFragments() {
        List<TrackFragmentBox> list = this.allTrafs;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.topLevel.getBoxes(MovieFragmentBox.class).iterator();
        while (it.hasNext()) {
            for (TrackFragmentBox trackFragmentBox : ((MovieFragmentBox) it.next()).getBoxes(TrackFragmentBox.class)) {
                if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == this.trackBox.getTrackHeaderBox().getTrackId()) {
                    arrayList.add(trackFragmentBox);
                }
            }
        }
        IsoFile[] isoFileArr = this.fragments;
        if (isoFileArr != null) {
            for (IsoFile isoFile : isoFileArr) {
                Iterator it2 = isoFile.getBoxes(MovieFragmentBox.class).iterator();
                while (it2.hasNext()) {
                    for (TrackFragmentBox trackFragmentBox2 : ((MovieFragmentBox) it2.next()).getBoxes(TrackFragmentBox.class)) {
                        if (trackFragmentBox2.getTrackFragmentHeaderBox().getTrackId() == this.trackBox.getTrackHeaderBox().getTrackId()) {
                            arrayList.add(trackFragmentBox2);
                        }
                    }
                }
            }
        }
        this.allTrafs = arrayList;
        this.firstSamples = new int[arrayList.size()];
        int i2 = 1;
        for (int i3 = 0; i3 < this.allTrafs.size(); i3++) {
            this.firstSamples[i3] = i2;
            i2 += getTrafSize(this.allTrafs.get(i3));
        }
        return arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        int i2 = this.size_;
        if (i2 != -1) {
            return i2;
        }
        Iterator it = this.topLevel.getBoxes(MovieFragmentBox.class).iterator();
        int i3 = 0;
        while (it.hasNext()) {
            for (TrackFragmentBox trackFragmentBox : ((MovieFragmentBox) it.next()).getBoxes(TrackFragmentBox.class)) {
                if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == this.trackBox.getTrackHeaderBox().getTrackId()) {
                    Iterator it2 = trackFragmentBox.getBoxes(TrackRunBox.class).iterator();
                    while (it2.hasNext()) {
                        i3 = (int) (i3 + ((TrackRunBox) it2.next()).getSampleCount());
                    }
                }
            }
        }
        for (IsoFile isoFile : this.fragments) {
            Iterator it3 = isoFile.getBoxes(MovieFragmentBox.class).iterator();
            while (it3.hasNext()) {
                for (TrackFragmentBox trackFragmentBox2 : ((MovieFragmentBox) it3.next()).getBoxes(TrackFragmentBox.class)) {
                    if (trackFragmentBox2.getTrackFragmentHeaderBox().getTrackId() == this.trackBox.getTrackHeaderBox().getTrackId()) {
                        Iterator it4 = trackFragmentBox2.getBoxes(TrackRunBox.class).iterator();
                        while (it4.hasNext()) {
                            i3 = (int) (i3 + ((TrackRunBox) it4.next()).getSampleCount());
                        }
                    }
                }
            }
        }
        this.size_ = i3;
        return i3;
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i2) {
        long j2;
        ByteBuffer byteBuffer;
        long defaultSampleSize;
        Sample sample;
        SoftReference<Sample>[] softReferenceArr = this.sampleCache;
        if (softReferenceArr[i2] == null || (sample = softReferenceArr[i2].get()) == null) {
            int i3 = i2 + 1;
            int length = this.firstSamples.length;
            do {
                length--;
            } while (i3 - this.firstSamples[length] < 0);
            TrackFragmentBox trackFragmentBox = this.allTrafs.get(length);
            int i4 = i3 - this.firstSamples[length];
            MovieFragmentBox movieFragmentBox = (MovieFragmentBox) trackFragmentBox.getParent();
            int i5 = 0;
            for (Box box : trackFragmentBox.getBoxes()) {
                if (box instanceof TrackRunBox) {
                    TrackRunBox trackRunBox = (TrackRunBox) box;
                    int i6 = i4 - i5;
                    if (trackRunBox.getEntries().size() <= i6) {
                        i5 += trackRunBox.getEntries().size();
                    } else {
                        List<TrackRunBox.Entry> entries = trackRunBox.getEntries();
                        TrackFragmentHeaderBox trackFragmentHeaderBox = trackFragmentBox.getTrackFragmentHeaderBox();
                        boolean isSampleSizePresent = trackRunBox.isSampleSizePresent();
                        boolean hasDefaultSampleSize = trackFragmentHeaderBox.hasDefaultSampleSize();
                        long j3 = 0;
                        if (isSampleSizePresent) {
                            j2 = 0;
                        } else {
                            if (hasDefaultSampleSize) {
                                defaultSampleSize = trackFragmentHeaderBox.getDefaultSampleSize();
                            } else {
                                TrackExtendsBox trackExtendsBox = this.trex;
                                if (trackExtendsBox != null) {
                                    defaultSampleSize = trackExtendsBox.getDefaultSampleSize();
                                } else {
                                    throw new RuntimeException("File doesn't contain trex box but track fragments aren't fully self contained. Cannot determine sample size.");
                                }
                            }
                            j2 = defaultSampleSize;
                        }
                        SoftReference<ByteBuffer> softReference = this.trunDataCache.get(trackRunBox);
                        ByteBuffer byteBuffer2 = softReference != null ? softReference.get() : null;
                        if (byteBuffer2 == null) {
                            Container container = movieFragmentBox;
                            if (trackFragmentHeaderBox.hasBaseDataOffset()) {
                                j3 = 0 + trackFragmentHeaderBox.getBaseDataOffset();
                                container = movieFragmentBox.getParent();
                            }
                            if (trackRunBox.isDataOffsetPresent()) {
                                j3 += trackRunBox.getDataOffset();
                            }
                            Iterator<TrackRunBox.Entry> it = entries.iterator();
                            int i7 = 0;
                            while (it.hasNext()) {
                                i7 = isSampleSizePresent ? (int) (i7 + it.next().getSampleSize()) : (int) (i7 + j2);
                            }
                            try {
                                ByteBuffer byteBuffer3 = container.getByteBuffer(j3, i7);
                                this.trunDataCache.put(trackRunBox, new SoftReference<>(byteBuffer3));
                                byteBuffer = byteBuffer3;
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        } else {
                            byteBuffer = byteBuffer2;
                        }
                        int i8 = 0;
                        for (int i9 = 0; i9 < i6; i9++) {
                            i8 = (int) (isSampleSizePresent ? i8 + entries.get(i9).getSampleSize() : i8 + j2);
                        }
                        final long sampleSize = isSampleSizePresent ? entries.get(i6).getSampleSize() : j2;
                        final ByteBuffer byteBuffer4 = byteBuffer;
                        final int i10 = i8;
                        Sample sample2 = new Sample() { // from class: com.googlecode.mp4parser.authoring.samples.FragmentedMp4SampleList.1
                            @Override // com.googlecode.mp4parser.authoring.Sample
                            public ByteBuffer asByteBuffer() {
                                return (ByteBuffer) ((ByteBuffer) byteBuffer4.position(i10)).slice().limit(CastUtils.l2i(sampleSize));
                            }

                            @Override // com.googlecode.mp4parser.authoring.Sample
                            public long getSize() {
                                return sampleSize;
                            }

                            @Override // com.googlecode.mp4parser.authoring.Sample
                            public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
                                writableByteChannel.write(asByteBuffer());
                            }
                        };
                        this.sampleCache[i2] = new SoftReference<>(sample2);
                        return sample2;
                    }
                }
            }
            throw new RuntimeException("Couldn't find sample in the traf I was looking");
        }
        return sample;
    }
}
