package com.mp4parser.streaming.rawformats;

import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;
import com.mp4parser.streaming.AbstractStreamingTrack;
import com.mp4parser.streaming.MultiTrackFragmentedMp4Writer;
import com.mp4parser.streaming.StreamingSample;
import com.mp4parser.streaming.StreamingTrack;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes14.dex */
public class H264TrackAdapter extends AbstractStreamingTrack {
    H264TrackImpl h264Track;

    public H264TrackAdapter(H264TrackImpl h264TrackImpl) throws InterruptedException {
        this.h264Track = h264TrackImpl;
        this.samples = new ArrayBlockingQueue(100, true);
        new Thread() { // from class: com.mp4parser.streaming.rawformats.H264TrackAdapter.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    H264TrackAdapter.this.parse();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
        this.stsd = h264TrackImpl.getSampleDescriptionBox();
    }

    public static void main(String[] strArr) throws IOException, InterruptedException {
        new MultiTrackFragmentedMp4Writer(new StreamingTrack[]{new H264TrackAdapter(new H264TrackImpl(new FileDataSourceImpl("c:\\content\\big_buck_bunny_1080p_h264-2min.h264")))}, new FileOutputStream("output.mp4")).write();
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public String getHandler() {
        return this.h264Track.getHandler();
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public String getLanguage() {
        return this.h264Track.getTrackMetaData().getLanguage();
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public long getTimescale() {
        return this.h264Track.getTrackMetaData().getTimescale();
    }

    public void parse() throws InterruptedException {
        List<Sample> samples = this.h264Track.getSamples();
        for (int i2 = 0; i2 < samples.size(); i2++) {
            System.err.println("Jo! " + i2 + " of " + samples.size());
            final long j2 = this.h264Track.getSampleDurations()[i2];
            samples.get(i2);
            this.samples.put(new StreamingSample
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0049: INVOKE 
                  (wrap: java.util.concurrent.BlockingQueue<com.mp4parser.streaming.StreamingSample> : 0x0042: IGET (r7v0 'this' com.mp4parser.streaming.rawformats.H264TrackAdapter A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:7) com.mp4parser.streaming.AbstractStreamingTrack.samples java.util.concurrent.BlockingQueue)
                  (wrap: com.mp4parser.streaming.StreamingSample : 0x0046: CONSTRUCTOR 
                  (r7v0 'this' com.mp4parser.streaming.rawformats.H264TrackAdapter A[IMMUTABLE_TYPE, THIS])
                  (r2 I:com.googlecode.mp4parser.authoring.Sample A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r3v2 'j2' long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.mp4parser.streaming.rawformats.H264TrackAdapter, com.googlecode.mp4parser.authoring.Sample, long):void (m), WRAPPED] call: com.mp4parser.streaming.rawformats.H264TrackAdapter.2.<init>(com.mp4parser.streaming.rawformats.H264TrackAdapter, com.googlecode.mp4parser.authoring.Sample, long):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.BlockingQueue.put(java.lang.Object):void A[MD:(E):void throws java.lang.InterruptedException (c)] (LINE:7) in method: com.mp4parser.streaming.rawformats.H264TrackAdapter.parse():void, file: classes14.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
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
                com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl r0 = r7.h264Track
                java.util.List r0 = r0.getSamples()
                r1 = 0
            L7:
                int r2 = r0.size()
                if (r1 < r2) goto L15
                java.io.PrintStream r0 = java.lang.System.err
                java.lang.String r1 = "Jo!"
                r0.println(r1)
                return
            L15:
                java.io.PrintStream r2 = java.lang.System.err
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Jo! "
                r3.<init>(r4)
                r3.append(r1)
                java.lang.String r4 = " of "
                r3.append(r4)
                int r4 = r0.size()
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                r2.println(r3)
                com.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl r2 = r7.h264Track
                long[] r2 = r2.getSampleDurations()
                r3 = r2[r1]
                java.lang.Object r2 = r0.get(r1)
                com.googlecode.mp4parser.authoring.Sample r2 = (com.googlecode.mp4parser.authoring.Sample) r2
                java.util.concurrent.BlockingQueue<com.mp4parser.streaming.StreamingSample> r5 = r7.samples
                com.mp4parser.streaming.rawformats.H264TrackAdapter$2 r6 = new com.mp4parser.streaming.rawformats.H264TrackAdapter$2
                r6.<init>()
                r5.put(r6)
                int r1 = r1 + 1
                goto L7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mp4parser.streaming.rawformats.H264TrackAdapter.parse():void");
        }
    }
