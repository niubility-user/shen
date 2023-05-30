package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReaderVariable;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.OriginalFormatBox;
import com.coremedia.iso.boxes.ProtectionSchemeInformationBox;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SchemeInformationBox;
import com.coremedia.iso.boxes.SchemeTypeBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.MemoryDataSourceImpl;
import com.googlecode.mp4parser.authoring.Edit;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;
import com.googlecode.mp4parser.authoring.tracks.h265.H265TrackImpl;
import com.googlecode.mp4parser.boxes.cenc.CencEncryptingSampleList;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.CencSampleEncryptionInformationGroupEntry;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.RangeStartMap;
import com.mp4parser.iso14496.part15.AvcConfigurationBox;
import com.mp4parser.iso14496.part15.HevcConfigurationBox;
import com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import com.mp4parser.iso23001.part7.TrackEncryptionBox;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;

/* loaded from: classes12.dex */
public class CencEncryptingTrackImpl implements CencEncryptedTrack {
    List<CencSampleAuxiliaryDataFormat> cencSampleAuxiliaryData;
    Object configurationBox;
    UUID defaultKeyId;
    boolean dummyIvs;
    private final String encryptionAlgo;
    RangeStartMap<Integer, SecretKey> indexToKey;
    Map<UUID, SecretKey> keys;
    Map<GroupEntry, long[]> sampleGroups;
    List<Sample> samples;
    Track source;
    SampleDescriptionBox stsd;
    boolean subSampleEncryption;

    public CencEncryptingTrackImpl(Track track, UUID uuid, SecretKey secretKey, boolean z) {
        this(track, uuid, Collections.singletonMap(uuid, secretKey), null, "cenc", z);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.source.getCompositionTimeEntries();
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public UUID getDefaultKeyId() {
        return this.defaultKeyId;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        return this.source.getDuration();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.source.getEdits();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "enc(" + this.source.getName() + ")";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.source.getSampleDependencies();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public synchronized SampleDescriptionBox getSampleDescriptionBox() {
        if (this.stsd == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                this.source.getSampleDescriptionBox().getBox(Channels.newChannel(byteArrayOutputStream));
                int i2 = 0;
                this.stsd = (SampleDescriptionBox) new IsoFile(new MemoryDataSourceImpl(byteArrayOutputStream.toByteArray())).getBoxes().get(0);
                OriginalFormatBox originalFormatBox = new OriginalFormatBox();
                originalFormatBox.setDataFormat(this.stsd.getSampleEntry().getType());
                if (this.stsd.getSampleEntry() instanceof AudioSampleEntry) {
                    ((AudioSampleEntry) this.stsd.getSampleEntry()).setType(AudioSampleEntry.TYPE_ENCRYPTED);
                } else if (this.stsd.getSampleEntry() instanceof VisualSampleEntry) {
                    ((VisualSampleEntry) this.stsd.getSampleEntry()).setType(VisualSampleEntry.TYPE_ENCRYPTED);
                } else {
                    throw new RuntimeException("I don't know how to cenc " + this.stsd.getSampleEntry().getType());
                }
                ProtectionSchemeInformationBox protectionSchemeInformationBox = new ProtectionSchemeInformationBox();
                protectionSchemeInformationBox.addBox(originalFormatBox);
                SchemeTypeBox schemeTypeBox = new SchemeTypeBox();
                schemeTypeBox.setSchemeType(this.encryptionAlgo);
                schemeTypeBox.setSchemeVersion(65536);
                protectionSchemeInformationBox.addBox(schemeTypeBox);
                SchemeInformationBox schemeInformationBox = new SchemeInformationBox();
                TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox();
                trackEncryptionBox.setDefaultIvSize(this.defaultKeyId == null ? 0 : 8);
                if (this.defaultKeyId != null) {
                    i2 = 1;
                }
                trackEncryptionBox.setDefaultAlgorithmId(i2);
                UUID uuid = this.defaultKeyId;
                if (uuid == null) {
                    uuid = new UUID(0L, 0L);
                }
                trackEncryptionBox.setDefault_KID(uuid);
                schemeInformationBox.addBox(trackEncryptionBox);
                protectionSchemeInformationBox.addBox(schemeInformationBox);
                this.stsd.getSampleEntry().addBox(protectionSchemeInformationBox);
            } catch (IOException unused) {
                throw new RuntimeException("Dumping stsd to memory failed");
            }
        }
        return this.stsd;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.source.getSampleDurations();
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public List<CencSampleAuxiliaryDataFormat> getSampleEncryptionEntries() {
        return this.cencSampleAuxiliaryData;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.sampleGroups;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return new CencEncryptingSampleList(this.indexToKey, this.source.getSamples(), this.cencSampleAuxiliaryData, this.encryptionAlgo);
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.source.getSubsampleInformationBox();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.source.getSyncSamples();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.source.getTrackMetaData();
    }

    @Override // com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public boolean hasSubSampleEncryption() {
        return this.subSampleEncryption;
    }

    public boolean isClearNal(ByteBuffer byteBuffer) {
        Object obj = this.configurationBox;
        if (obj instanceof HevcConfigurationBox) {
            int i2 = H265TrackImpl.getNalUnitHeader(byteBuffer.slice()).nalUnitType;
            if (i2 < 0 || i2 > 9) {
                if (i2 < 16 || i2 > 21) {
                    return i2 < 16 || i2 > 21;
                }
                return false;
            }
            return false;
        } else if (obj instanceof AvcConfigurationBox) {
            int i3 = H264TrackImpl.getNalUnitHeader(byteBuffer.slice()).nal_unit_type;
            return (i3 == 19 || i3 == 2 || i3 == 3 || i3 == 4 || i3 == 20 || i3 == 5 || i3 == 1) ? false : true;
        } else {
            throw new RuntimeException("Subsample encryption is activated but the CencEncryptingTrackImpl can't say if this sample is to be encrypted or not!");
        }
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z) {
        this(track, uuid, map, map2, str, z, false);
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z, boolean z2) {
        this.keys = new HashMap();
        char c2 = 0;
        this.dummyIvs = false;
        this.subSampleEncryption = false;
        SecretKey secretKey = null;
        this.stsd = null;
        this.source = track;
        this.keys = map;
        this.defaultKeyId = uuid;
        this.dummyIvs = z;
        this.encryptionAlgo = str;
        this.sampleGroups = new HashMap();
        for (Map.Entry<GroupEntry, long[]> entry : track.getSampleGroups().entrySet()) {
            if (!(entry.getKey() instanceof CencSampleEncryptionInformationGroupEntry)) {
                this.sampleGroups.put(entry.getKey(), entry.getValue());
            }
            c2 = 0;
            secretKey = null;
        }
        if (map2 != null) {
            for (Map.Entry<CencSampleEncryptionInformationGroupEntry, long[]> entry2 : map2.entrySet()) {
                this.sampleGroups.put(entry2.getKey(), entry2.getValue());
            }
        }
        this.sampleGroups = new HashMap<GroupEntry, long[]>(this.sampleGroups) { // from class: com.googlecode.mp4parser.authoring.tracks.CencEncryptingTrackImpl.1
            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public long[] put(GroupEntry groupEntry, long[] jArr) {
                if (!(groupEntry instanceof CencSampleEncryptionInformationGroupEntry)) {
                    return (long[]) super.put((AnonymousClass1) groupEntry, (GroupEntry) jArr);
                }
                throw new RuntimeException("Please supply CencSampleEncryptionInformationGroupEntries in the constructor");
            }
        };
        this.samples = track.getSamples();
        this.cencSampleAuxiliaryData = new ArrayList();
        BigInteger bigInteger = new BigInteger("1");
        int i2 = 8;
        byte[] bArr = new byte[8];
        if (!z) {
            new SecureRandom().nextBytes(bArr);
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            arrayList.addAll(map2.keySet());
        }
        this.indexToKey = new RangeStartMap<>();
        int i3 = -1;
        int i4 = 0;
        int i5 = -1;
        while (i4 < track.getSamples().size()) {
            int i6 = 0;
            int i7 = 0;
            while (i6 < arrayList.size()) {
                BigInteger bigInteger3 = bigInteger2;
                if (Arrays.binarySearch(getSampleGroups().get((GroupEntry) arrayList.get(i6)), i4) >= 0) {
                    i7 = i6 + 1;
                }
                i6++;
                bigInteger2 = bigInteger3;
                secretKey = null;
                i2 = 8;
            }
            if (i5 != i7) {
                if (i7 == 0) {
                    this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i4), (Integer) map.get(uuid));
                } else {
                    int i8 = i7 - 1;
                    if (((CencSampleEncryptionInformationGroupEntry) arrayList.get(i8)).getKid() != null) {
                        SecretKey secretKey2 = map.get(((CencSampleEncryptionInformationGroupEntry) arrayList.get(i8)).getKid());
                        if (secretKey2 != null) {
                            this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i4), (Integer) secretKey2);
                        } else {
                            throw new RuntimeException("Key " + ((CencSampleEncryptionInformationGroupEntry) arrayList.get(i8)).getKid() + " was not supplied for decryption");
                        }
                    } else {
                        this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i4), (Integer) secretKey);
                    }
                }
                i5 = i7;
            }
            i4++;
            c2 = 0;
        }
        for (Box box : track.getSampleDescriptionBox().getSampleEntry().getBoxes()) {
            if (box instanceof AvcConfigurationBox) {
                this.configurationBox = box;
                this.subSampleEncryption = true;
                i3 = ((AvcConfigurationBox) box).getLengthSizeMinusOne() + 1;
            }
            if (box instanceof HevcConfigurationBox) {
                this.configurationBox = box;
                this.subSampleEncryption = true;
                i3 = ((HevcConfigurationBox) box).getLengthSizeMinusOne() + 1;
            }
        }
        for (int i9 = 0; i9 < this.samples.size(); i9++) {
            Sample sample = this.samples.get(i9);
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
            this.cencSampleAuxiliaryData.add(cencSampleAuxiliaryDataFormat);
            if (this.indexToKey.get(Integer.valueOf(i9)) != null) {
                byte[] byteArray = bigInteger2.toByteArray();
                byte[] bArr2 = new byte[i2];
                System.arraycopy(byteArray, byteArray.length - i2 > 0 ? byteArray.length - i2 : 0, bArr2, 8 - byteArray.length < 0 ? 0 : 8 - byteArray.length, byteArray.length > i2 ? 8 : byteArray.length);
                cencSampleAuxiliaryDataFormat.iv = bArr2;
                ByteBuffer byteBuffer = (ByteBuffer) sample.asByteBuffer().rewind();
                if (this.subSampleEncryption) {
                    if (z2) {
                        CencSampleAuxiliaryDataFormat.Pair[] pairArr = new CencSampleAuxiliaryDataFormat.Pair[1];
                        pairArr[c2] = cencSampleAuxiliaryDataFormat.createPair(byteBuffer.remaining(), 0L);
                        cencSampleAuxiliaryDataFormat.pairs = pairArr;
                    } else {
                        ArrayList arrayList2 = new ArrayList(5);
                        while (byteBuffer.remaining() > 0) {
                            int l2i = CastUtils.l2i(IsoTypeReaderVariable.read(byteBuffer, i3));
                            int i10 = l2i + i3;
                            arrayList2.add(cencSampleAuxiliaryDataFormat.createPair((i10 < 112 || isClearNal(byteBuffer.duplicate())) ? i10 : (i10 % 16) + 96, i10 - r12));
                            byteBuffer.position(byteBuffer.position() + l2i);
                        }
                        cencSampleAuxiliaryDataFormat.pairs = (CencSampleAuxiliaryDataFormat.Pair[]) arrayList2.toArray(new CencSampleAuxiliaryDataFormat.Pair[arrayList2.size()]);
                    }
                }
                bigInteger2 = bigInteger2.add(bigInteger);
            }
        }
    }
}
