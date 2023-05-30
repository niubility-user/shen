package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.ChunkOffsetBox;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.SchemeTypeBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack;
import com.googlecode.mp4parser.util.Path;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import com.mp4parser.iso23001.part7.TrackEncryptionBox;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes12.dex */
public class CencMp4TrackImplImpl extends Mp4TrackImpl implements CencEncryptedTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private UUID defaultKeyId;
    private List<CencSampleAuxiliaryDataFormat> sampleEncryptionEntries;

    /* loaded from: classes12.dex */
    private class FindSaioSaizPair {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Container container;
        private SampleAuxiliaryInformationOffsetsBox saio;
        private SampleAuxiliaryInformationSizesBox saiz;

        public FindSaioSaizPair(Container container) {
            this.container = container;
        }

        public SampleAuxiliaryInformationOffsetsBox getSaio() {
            return this.saio;
        }

        public SampleAuxiliaryInformationSizesBox getSaiz() {
            return this.saiz;
        }

        public FindSaioSaizPair invoke() {
            List boxes = this.container.getBoxes(SampleAuxiliaryInformationSizesBox.class);
            List boxes2 = this.container.getBoxes(SampleAuxiliaryInformationOffsetsBox.class);
            this.saiz = null;
            this.saio = null;
            for (int i2 = 0; i2 < boxes.size(); i2++) {
                if ((this.saiz == null && ((SampleAuxiliaryInformationSizesBox) boxes.get(i2)).getAuxInfoType() == null) || "cenc".equals(((SampleAuxiliaryInformationSizesBox) boxes.get(i2)).getAuxInfoType())) {
                    this.saiz = (SampleAuxiliaryInformationSizesBox) boxes.get(i2);
                } else {
                    SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = this.saiz;
                    if (sampleAuxiliaryInformationSizesBox != null && sampleAuxiliaryInformationSizesBox.getAuxInfoType() == null && "cenc".equals(((SampleAuxiliaryInformationSizesBox) boxes.get(i2)).getAuxInfoType())) {
                        this.saiz = (SampleAuxiliaryInformationSizesBox) boxes.get(i2);
                    } else {
                        throw new RuntimeException("Are there two cenc labeled saiz?");
                    }
                }
                if ((this.saio == null && ((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i2)).getAuxInfoType() == null) || "cenc".equals(((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i2)).getAuxInfoType())) {
                    this.saio = (SampleAuxiliaryInformationOffsetsBox) boxes2.get(i2);
                } else {
                    SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = this.saio;
                    if (sampleAuxiliaryInformationOffsetsBox != null && sampleAuxiliaryInformationOffsetsBox.getAuxInfoType() == null && "cenc".equals(((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i2)).getAuxInfoType())) {
                        this.saio = (SampleAuxiliaryInformationOffsetsBox) boxes2.get(i2);
                    } else {
                        throw new RuntimeException("Are there two cenc labeled saio?");
                    }
                }
            }
            return this;
        }
    }

    public CencMp4TrackImplImpl(String str, TrackBox trackBox, IsoFile... isoFileArr) throws IOException {
        super(str, trackBox, isoFileArr);
        long j2;
        int i2;
        Container container;
        long j3;
        int i3;
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
        this.sampleEncryptionEntries = new ArrayList();
        long trackId = trackBox.getTrackHeaderBox().getTrackId();
        if (trackBox.getParent().getBoxes(MovieExtendsBox.class).size() > 0) {
            Iterator it = ((Box) trackBox.getParent()).getParent().getBoxes(MovieFragmentBox.class).iterator();
            while (it.hasNext()) {
                MovieFragmentBox movieFragmentBox = (MovieFragmentBox) it.next();
                Iterator it2 = movieFragmentBox.getBoxes(TrackFragmentBox.class).iterator();
                while (it2.hasNext()) {
                    TrackFragmentBox trackFragmentBox = (TrackFragmentBox) it2.next();
                    if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == trackId) {
                        TrackEncryptionBox trackEncryptionBox = (TrackEncryptionBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
                        this.defaultKeyId = trackEncryptionBox.getDefault_KID();
                        if (trackFragmentBox.getTrackFragmentHeaderBox().hasBaseDataOffset()) {
                            container = ((Box) trackBox.getParent()).getParent();
                            j3 = trackFragmentBox.getTrackFragmentHeaderBox().getBaseDataOffset();
                        } else {
                            container = movieFragmentBox;
                            j3 = 0;
                        }
                        FindSaioSaizPair invoke = new FindSaioSaizPair(trackFragmentBox).invoke();
                        SampleAuxiliaryInformationOffsetsBox saio = invoke.getSaio();
                        SampleAuxiliaryInformationSizesBox saiz = invoke.getSaiz();
                        long[] offsets = saio.getOffsets();
                        List boxes = trackFragmentBox.getBoxes(TrackRunBox.class);
                        long j4 = trackId;
                        int i4 = 0;
                        int i5 = 0;
                        while (i4 < offsets.length) {
                            int size = ((TrackRunBox) boxes.get(i4)).getEntries().size();
                            long j5 = offsets[i4];
                            Iterator it3 = it;
                            long[] jArr = offsets;
                            List list = boxes;
                            int i6 = i5;
                            long j6 = 0;
                            while (true) {
                                i3 = i5 + size;
                                if (i6 >= i3) {
                                    break;
                                }
                                j6 += saiz.getSize(i6);
                                i6++;
                                movieFragmentBox = movieFragmentBox;
                                it2 = it2;
                            }
                            ByteBuffer byteBuffer = container.getByteBuffer(j3 + j5, j6);
                            int i7 = i5;
                            while (i7 < i3) {
                                this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox.getDefaultIvSize(), byteBuffer, saiz.getSize(i7)));
                                i7++;
                                i3 = i3;
                                movieFragmentBox = movieFragmentBox;
                                it2 = it2;
                            }
                            i4++;
                            offsets = jArr;
                            i5 = i3;
                            boxes = list;
                            it = it3;
                        }
                        trackId = j4;
                    }
                }
            }
            return;
        }
        TrackEncryptionBox trackEncryptionBox2 = (TrackEncryptionBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
        this.defaultKeyId = trackEncryptionBox2.getDefault_KID();
        ChunkOffsetBox chunkOffsetBox = (ChunkOffsetBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stco[0]");
        long[] blowup = trackBox.getSampleTableBox().getSampleToChunkBox().blowup((chunkOffsetBox == null ? (ChunkOffsetBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/co64[0]") : chunkOffsetBox).getChunkOffsets().length);
        FindSaioSaizPair invoke2 = new FindSaioSaizPair((Container) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]")).invoke();
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = invoke2.saio;
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = invoke2.saiz;
        Container parent = ((MovieBox) trackBox.getParent()).getParent();
        if (sampleAuxiliaryInformationOffsetsBox.getOffsets().length == 1) {
            long j7 = sampleAuxiliaryInformationOffsetsBox.getOffsets()[0];
            if (sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize() > 0) {
                i2 = (sampleAuxiliaryInformationSizesBox.getSampleCount() * sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize()) + 0;
            } else {
                i2 = 0;
                for (int i8 = 0; i8 < sampleAuxiliaryInformationSizesBox.getSampleCount(); i8++) {
                    i2 += sampleAuxiliaryInformationSizesBox.getSampleInfoSizes()[i8];
                }
            }
            ByteBuffer byteBuffer2 = parent.getByteBuffer(j7, i2);
            for (int i9 = 0; i9 < sampleAuxiliaryInformationSizesBox.getSampleCount(); i9++) {
                this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox2.getDefaultIvSize(), byteBuffer2, sampleAuxiliaryInformationSizesBox.getSize(i9)));
            }
        } else if (sampleAuxiliaryInformationOffsetsBox.getOffsets().length == blowup.length) {
            int i10 = 0;
            for (int i11 = 0; i11 < blowup.length; i11++) {
                long j8 = sampleAuxiliaryInformationOffsetsBox.getOffsets()[i11];
                if (sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize() > 0) {
                    j2 = (sampleAuxiliaryInformationSizesBox.getSampleCount() * blowup[i11]) + 0;
                } else {
                    j2 = 0;
                    for (int i12 = 0; i12 < blowup[i11]; i12++) {
                        j2 += sampleAuxiliaryInformationSizesBox.getSize(i10 + i12);
                    }
                }
                ByteBuffer byteBuffer3 = parent.getByteBuffer(j8, j2);
                for (int i13 = 0; i13 < blowup[i11]; i13++) {
                    this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox2.getDefaultIvSize(), byteBuffer3, sampleAuxiliaryInformationSizesBox.getSize(i10 + i13)));
                }
                i10 = (int) (i10 + blowup[i11]);
            }
        } else {
            throw new RuntimeException("Number of saio offsets must be either 1 or number of chunks");
        }
    }

    private CencSampleAuxiliaryDataFormat parseCencAuxDataFormat(int i2, ByteBuffer byteBuffer, long j2) {
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
        if (j2 > 0) {
            byte[] bArr = new byte[i2];
            cencSampleAuxiliaryDataFormat.iv = bArr;
            byteBuffer.get(bArr);
            if (j2 > i2) {
                cencSampleAuxiliaryDataFormat.pairs = new CencSampleAuxiliaryDataFormat.Pair[IsoTypeReader.readUInt16(byteBuffer)];
                int i3 = 0;
                while (true) {
                    CencSampleAuxiliaryDataFormat.Pair[] pairArr = cencSampleAuxiliaryDataFormat.pairs;
                    if (i3 >= pairArr.length) {
                        break;
                    }
                    pairArr[i3] = cencSampleAuxiliaryDataFormat.createPair(IsoTypeReader.readUInt16(byteBuffer), IsoTypeReader.readUInt32(byteBuffer));
                    i3++;
                }
            }
        }
        return cencSampleAuxiliaryDataFormat;
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public UUID getDefaultKeyId() {
        return this.defaultKeyId;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "enc(" + super.getName() + ")";
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public List<CencSampleAuxiliaryDataFormat> getSampleEncryptionEntries() {
        return this.sampleEncryptionEntries;
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public boolean hasSubSampleEncryption() {
        return false;
    }

    public String toString() {
        return "CencMp4TrackImpl{handler='" + getHandler() + "'}";
    }
}
