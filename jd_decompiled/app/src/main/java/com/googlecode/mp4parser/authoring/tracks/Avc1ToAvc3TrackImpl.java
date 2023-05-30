package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.MemoryDataSourceImpl;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.WrappingTrack;
import com.googlecode.mp4parser.util.Path;
import com.mp4parser.iso14496.part15.AvcConfigurationBox;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes12.dex */
public class Avc1ToAvc3TrackImpl extends WrappingTrack {
    AvcConfigurationBox avcC;
    List<Sample> samples;
    SampleDescriptionBox stsd;

    /* loaded from: classes12.dex */
    private class ReplaceSyncSamplesList extends AbstractList<Sample> {
        List<Sample> parentSamples;

        public ReplaceSyncSamplesList(List<Sample> list) {
            this.parentSamples = list;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parentSamples.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public Sample get(int i2) {
            if (Arrays.binarySearch(Avc1ToAvc3TrackImpl.this.getSyncSamples(), i2 + 1) >= 0) {
                final int lengthSizeMinusOne = Avc1ToAvc3TrackImpl.this.avcC.getLengthSizeMinusOne() + 1;
                ByteBuffer.allocate(lengthSizeMinusOne);
                this.parentSamples.get(i2);
                return new Sample
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002a: RETURN 
                      (wrap: com.googlecode.mp4parser.authoring.Sample : 0x0027: CONSTRUCTOR 
                      (r3v0 'this' com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl$ReplaceSyncSamplesList A[IMMUTABLE_TYPE, THIS])
                      (r1 I:java.nio.ByteBuffer A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r0v7 'lengthSizeMinusOne' int A[DONT_INLINE])
                      (r4 I:com.googlecode.mp4parser.authoring.Sample A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl$ReplaceSyncSamplesList, java.nio.ByteBuffer, int, com.googlecode.mp4parser.authoring.Sample):void (m), WRAPPED] (LINE:6) call: com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.ReplaceSyncSamplesList.1.<init>(com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl$ReplaceSyncSamplesList, java.nio.ByteBuffer, int, com.googlecode.mp4parser.authoring.Sample):void type: CONSTRUCTOR)
                     (LINE:6) in method: com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.ReplaceSyncSamplesList.get(int):com.googlecode.mp4parser.authoring.Sample, file: classes12.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl r0 = com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.this
                    long[] r0 = r0.getSyncSamples()
                    int r1 = r4 + 1
                    long r1 = (long) r1
                    int r0 = java.util.Arrays.binarySearch(r0, r1)
                    if (r0 < 0) goto L2b
                    com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl r0 = com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.this
                    com.mp4parser.iso14496.part15.AvcConfigurationBox r0 = r0.avcC
                    int r0 = r0.getLengthSizeMinusOne()
                    int r0 = r0 + 1
                    java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r0)
                    java.util.List<com.googlecode.mp4parser.authoring.Sample> r2 = r3.parentSamples
                    java.lang.Object r4 = r2.get(r4)
                    com.googlecode.mp4parser.authoring.Sample r4 = (com.googlecode.mp4parser.authoring.Sample) r4
                    com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl$ReplaceSyncSamplesList$1 r2 = new com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl$ReplaceSyncSamplesList$1
                    r2.<init>()
                    return r2
                L2b:
                    java.util.List<com.googlecode.mp4parser.authoring.Sample> r0 = r3.parentSamples
                    java.lang.Object r4 = r0.get(r4)
                    com.googlecode.mp4parser.authoring.Sample r4 = (com.googlecode.mp4parser.authoring.Sample) r4
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.Avc1ToAvc3TrackImpl.ReplaceSyncSamplesList.get(int):com.googlecode.mp4parser.authoring.Sample");
            }
        }

        public Avc1ToAvc3TrackImpl(Track track) throws IOException {
            super(track);
            if (VisualSampleEntry.TYPE3.equals(track.getSampleDescriptionBox().getSampleEntry().getType())) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                track.getSampleDescriptionBox().getBox(Channels.newChannel(byteArrayOutputStream));
                SampleDescriptionBox sampleDescriptionBox = (SampleDescriptionBox) Path.getPath(new IsoFile(new MemoryDataSourceImpl(byteArrayOutputStream.toByteArray())), SampleDescriptionBox.TYPE);
                this.stsd = sampleDescriptionBox;
                ((VisualSampleEntry) sampleDescriptionBox.getSampleEntry()).setType(VisualSampleEntry.TYPE4);
                this.avcC = (AvcConfigurationBox) Path.getPath((AbstractContainerBox) this.stsd, "avc./avcC");
                this.samples = new ReplaceSyncSamplesList(track.getSamples());
                return;
            }
            throw new RuntimeException("Only avc1 tracks can be converted to avc3 tracks");
        }

        @Override // com.googlecode.mp4parser.authoring.WrappingTrack, com.googlecode.mp4parser.authoring.Track
        public SampleDescriptionBox getSampleDescriptionBox() {
            return this.stsd;
        }

        @Override // com.googlecode.mp4parser.authoring.WrappingTrack, com.googlecode.mp4parser.authoring.Track
        public List<Sample> getSamples() {
            return this.samples;
        }
    }
