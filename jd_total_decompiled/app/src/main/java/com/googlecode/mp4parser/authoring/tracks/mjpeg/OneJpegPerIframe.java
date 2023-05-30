package com.googlecode.mp4parser.authoring.tracks.mjpeg;

import com.coremedia.iso.Hex;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Edit;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ObjectDescriptorFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/* loaded from: classes12.dex */
public class OneJpegPerIframe extends AbstractTrack {
    File[] jpegs;
    long[] sampleDurations;
    SampleDescriptionBox stsd;
    long[] syncSamples;
    TrackMetaData trackMetaData;

    public OneJpegPerIframe(String str, File[] fileArr, Track track) throws IOException {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.jpegs = fileArr;
        if (track.getSyncSamples().length == fileArr.length) {
            BufferedImage read = ImageIO.read(fileArr[0]);
            this.trackMetaData.setWidth(read.getWidth());
            this.trackMetaData.setHeight(read.getHeight());
            this.trackMetaData.setTimescale(track.getTrackMetaData().getTimescale());
            long[] sampleDurations = track.getSampleDurations();
            long[] syncSamples = track.getSyncSamples();
            this.sampleDurations = new long[syncSamples.length];
            long j2 = 0;
            boolean z = true;
            long j3 = 0;
            int i2 = 1;
            for (int i3 = 1; i3 < sampleDurations.length; i3++) {
                if (i2 < syncSamples.length && i3 == syncSamples[i2]) {
                    this.sampleDurations[i2 - 1] = j3;
                    i2++;
                    j3 = 0;
                }
                j3 += sampleDurations[i3];
            }
            long[] jArr = this.sampleDurations;
            jArr[jArr.length - 1] = j3;
            this.stsd = new SampleDescriptionBox();
            VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE1);
            this.stsd.addBox(visualSampleEntry);
            ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
            eSDescriptorBox.setData(ByteBuffer.wrap(Hex.decodeHex("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102")));
            eSDescriptorBox.setEsDescriptor((ESDescriptor) ObjectDescriptorFactory.createFrom(-1, ByteBuffer.wrap(Hex.decodeHex("038080801B000100048080800D6C11000000000A1CB4000A1CB4068080800102"))));
            visualSampleEntry.addBox(eSDescriptorBox);
            this.syncSamples = new long[fileArr.length];
            int i4 = 0;
            while (true) {
                long[] jArr2 = this.syncSamples;
                if (i4 >= jArr2.length) {
                    break;
                }
                int i5 = i4 + 1;
                jArr2[i4] = i5;
                i4 = i5;
            }
            double d = 0.0d;
            boolean z2 = true;
            for (Edit edit : track.getEdits()) {
                if (edit.getMediaTime() == -1 && !z) {
                    throw new RuntimeException("Cannot accept edit list for processing (1)");
                }
                if (edit.getMediaTime() >= 0 && !z2) {
                    throw new RuntimeException("Cannot accept edit list for processing (2)");
                }
                if (edit.getMediaTime() == -1) {
                    d += edit.getSegmentDuration();
                } else {
                    double mediaTime = edit.getMediaTime();
                    double timeScale = edit.getTimeScale();
                    Double.isNaN(mediaTime);
                    Double.isNaN(timeScale);
                    d -= mediaTime / timeScale;
                    z2 = false;
                    z = false;
                }
            }
            if (track.getCompositionTimeEntries() != null && track.getCompositionTimeEntries().size() > 0) {
                int[] blowupCompositionTimes = CompositionTimeToSample.blowupCompositionTimes(track.getCompositionTimeEntries());
                for (int i6 = 0; i6 < blowupCompositionTimes.length && i6 < 50; i6++) {
                    blowupCompositionTimes[i6] = (int) (blowupCompositionTimes[i6] + j2);
                    j2 += track.getSampleDurations()[i6];
                }
                Arrays.sort(blowupCompositionTimes);
                double d2 = blowupCompositionTimes[0];
                double timescale = track.getTrackMetaData().getTimescale();
                Double.isNaN(d2);
                Double.isNaN(timescale);
                d += d2 / timescale;
            }
            if (d < 0.0d) {
                List<Edit> edits = getEdits();
                double timescale2 = getTrackMetaData().getTimescale();
                Double.isNaN(timescale2);
                long j4 = (long) ((-d) * timescale2);
                long timescale3 = getTrackMetaData().getTimescale();
                double duration = getDuration();
                double timescale4 = getTrackMetaData().getTimescale();
                Double.isNaN(duration);
                Double.isNaN(timescale4);
                edits.add(new Edit(j4, timescale3, 1.0d, duration / timescale4));
                return;
            } else if (d > 0.0d) {
                getEdits().add(new Edit(-1L, getTrackMetaData().getTimescale(), 1.0d, d));
                List<Edit> edits2 = getEdits();
                long timescale5 = getTrackMetaData().getTimescale();
                double duration2 = getDuration();
                double timescale6 = getTrackMetaData().getTimescale();
                Double.isNaN(duration2);
                Double.isNaN(timescale6);
                edits2.add(new Edit(0L, timescale5, 1.0d, duration2 / timescale6));
                return;
            } else {
                return;
            }
        }
        throw new RuntimeException("Number of sync samples doesn't match the number of stills (" + track.getSyncSamples().length + " vs. " + fileArr.length + ")");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "vide";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.sampleDurations;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return new AbstractList<Sample>() { // from class: com.googlecode.mp4parser.authoring.tracks.mjpeg.OneJpegPerIframe.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return OneJpegPerIframe.this.jpegs.length;
            }

            @Override // java.util.AbstractList, java.util.List
            public Sample get(final int i2) {
                return new Sample() { // from class: com.googlecode.mp4parser.authoring.tracks.mjpeg.OneJpegPerIframe.1.1
                    ByteBuffer sample = null;

                    @Override // com.googlecode.mp4parser.authoring.Sample
                    public ByteBuffer asByteBuffer() {
                        if (this.sample == null) {
                            try {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.jpegs[i2], "r");
                                this.sample = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        }
                        return this.sample;
                    }

                    @Override // com.googlecode.mp4parser.authoring.Sample
                    public long getSize() {
                        return OneJpegPerIframe.this.jpegs[i2].length();
                    }

                    @Override // com.googlecode.mp4parser.authoring.Sample
                    public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
                        RandomAccessFile randomAccessFile = new RandomAccessFile(OneJpegPerIframe.this.jpegs[i2], "r");
                        randomAccessFile.getChannel().transferTo(0L, randomAccessFile.length(), writableByteChannel);
                        randomAccessFile.close();
                    }
                };
            }
        };
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.syncSamples;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
