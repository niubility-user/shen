package com.googlecode.mp4parser.authoring.tracks.webvtt;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.CuePayloadBox;
import com.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.CueSettingsBox;
import com.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.VTTCueBox;
import com.googlecode.mp4parser.authoring.tracks.webvtt.sampleboxes.VTTEmptyCueBox;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Mp4Arrays;
import com.mp4parser.iso14496.part30.WebVTTConfigurationBox;
import com.mp4parser.iso14496.part30.WebVTTSampleEntry;
import com.mp4parser.iso14496.part30.WebVTTSourceLabelBox;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class WebVttTrack extends AbstractTrack {
    long[] sampleDurations;
    WebVTTSampleEntry sampleEntry;
    List<Sample> samples;
    SampleDescriptionBox stsd;
    TrackMetaData trackMetaData;
    private static final String WEBVTT_FILE_HEADER_STRING = "^\ufeff?WEBVTT((\\u0020|\t).*)?$";
    private static final Pattern WEBVTT_FILE_HEADER = Pattern.compile(WEBVTT_FILE_HEADER_STRING);
    private static final String WEBVTT_METADATA_HEADER_STRING = "\\S*[:=]\\S*";
    private static final Pattern WEBVTT_METADATA_HEADER = Pattern.compile(WEBVTT_METADATA_HEADER_STRING);
    private static final String WEBVTT_CUE_IDENTIFIER_STRING = "^(?!.*(-->)).*$";
    private static final Pattern WEBVTT_CUE_IDENTIFIER = Pattern.compile(WEBVTT_CUE_IDENTIFIER_STRING);
    private static final String WEBVTT_TIMESTAMP_STRING = "(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}";
    private static final Pattern WEBVTT_TIMESTAMP = Pattern.compile(WEBVTT_TIMESTAMP_STRING);
    private static final String WEBVTT_CUE_SETTING_STRING = "\\S*:\\S*";
    private static final Pattern WEBVTT_CUE_SETTING = Pattern.compile(WEBVTT_CUE_SETTING_STRING);
    private static final Sample EMPTY_SAMPLE = new Sample() { // from class: com.googlecode.mp4parser.authoring.tracks.webvtt.WebVttTrack.1
        ByteBuffer vtte;

        {
            VTTEmptyCueBox vTTEmptyCueBox = new VTTEmptyCueBox();
            ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(vTTEmptyCueBox.getSize()));
            this.vtte = allocate;
            try {
                vTTEmptyCueBox.getBox(new ByteBufferByteChannel(allocate));
                this.vtte.rewind();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public ByteBuffer asByteBuffer() {
            return this.vtte.duplicate();
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            return this.vtte.remaining();
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
            writableByteChannel.write(this.vtte.duplicate());
        }
    };

    /* loaded from: classes12.dex */
    private static class BoxBearingSample implements Sample {
        List<Box> boxes;

        public BoxBearingSample(List<Box> list) {
            this.boxes = list;
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public ByteBuffer asByteBuffer() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                writeTo(Channels.newChannel(byteArrayOutputStream));
                return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            Iterator<Box> it = this.boxes.iterator();
            long j2 = 0;
            while (it.hasNext()) {
                j2 += it.next().getSize();
            }
            return j2;
        }

        @Override // com.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
            Iterator<Box> it = this.boxes.iterator();
            while (it.hasNext()) {
                it.next().getBox(writableByteChannel);
            }
        }
    }

    public WebVttTrack(InputStream inputStream, String str, Locale locale) throws IOException {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.samples = new ArrayList();
        this.sampleDurations = new long[0];
        this.trackMetaData.setTimescale(1000L);
        this.trackMetaData.setLanguage(locale.getISO3Language());
        this.stsd = new SampleDescriptionBox();
        WebVTTSampleEntry webVTTSampleEntry = new WebVTTSampleEntry();
        this.sampleEntry = webVTTSampleEntry;
        this.stsd.addBox(webVTTSampleEntry);
        WebVTTConfigurationBox webVTTConfigurationBox = new WebVTTConfigurationBox();
        this.sampleEntry.addBox(webVTTConfigurationBox);
        this.sampleEntry.addBox(new WebVTTSourceLabelBox());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String readLine = bufferedReader.readLine();
        if (readLine != null && WEBVTT_FILE_HEADER.matcher(readLine).matches()) {
            webVTTConfigurationBox.setConfig(String.valueOf(webVTTConfigurationBox.getConfig()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + readLine);
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 != null) {
                    if (readLine2.isEmpty()) {
                        long j2 = 0;
                        while (true) {
                            String readLine3 = bufferedReader.readLine();
                            if (readLine3 == null) {
                                return;
                            }
                            if (!"".equals(readLine3.trim())) {
                                readLine3 = WEBVTT_CUE_IDENTIFIER.matcher(readLine3).find() ? bufferedReader.readLine() : readLine3;
                                Matcher matcher = WEBVTT_TIMESTAMP.matcher(readLine3);
                                if (matcher.find()) {
                                    long parseTimestampUs = parseTimestampUs(matcher.group());
                                    if (matcher.find()) {
                                        String group = matcher.group();
                                        long parseTimestampUs2 = parseTimestampUs(group);
                                        Matcher matcher2 = WEBVTT_CUE_SETTING.matcher(readLine3.substring(readLine3.indexOf(group) + group.length()));
                                        String str2 = null;
                                        while (matcher2.find()) {
                                            str2 = matcher2.group();
                                        }
                                        StringBuilder sb = new StringBuilder();
                                        while (true) {
                                            String readLine4 = bufferedReader.readLine();
                                            if (readLine4 == null || readLine4.isEmpty()) {
                                                break;
                                            }
                                            if (sb.length() > 0) {
                                                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                            }
                                            sb.append(readLine4.trim());
                                        }
                                        if (parseTimestampUs != j2) {
                                            this.sampleDurations = Mp4Arrays.copyOfAndAppend(this.sampleDurations, parseTimestampUs - j2);
                                            this.samples.add(EMPTY_SAMPLE);
                                        }
                                        this.sampleDurations = Mp4Arrays.copyOfAndAppend(this.sampleDurations, parseTimestampUs2 - parseTimestampUs);
                                        VTTCueBox vTTCueBox = new VTTCueBox();
                                        if (str2 != null) {
                                            CueSettingsBox cueSettingsBox = new CueSettingsBox();
                                            cueSettingsBox.setContent(str2);
                                            vTTCueBox.setCueSettingsBox(cueSettingsBox);
                                        }
                                        CuePayloadBox cuePayloadBox = new CuePayloadBox();
                                        cuePayloadBox.setContent(sb.toString());
                                        vTTCueBox.setCuePayloadBox(cuePayloadBox);
                                        this.samples.add(new BoxBearingSample(Collections.singletonList(vTTCueBox)));
                                        j2 = parseTimestampUs2;
                                    } else {
                                        throw new IOException("Expected cue end time: " + readLine3);
                                    }
                                } else {
                                    throw new IOException("Expected cue start time: " + readLine3);
                                }
                            }
                        }
                    } else if (WEBVTT_METADATA_HEADER.matcher(readLine2).find()) {
                        webVTTConfigurationBox.setConfig(String.valueOf(webVTTConfigurationBox.getConfig()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + readLine2);
                    } else {
                        throw new IOException("Expected WebVTT metadata header. Got " + readLine2);
                    }
                } else {
                    throw new IOException("Expected an empty line after webvtt header");
                }
            }
        } else {
            throw new IOException("Expected WEBVTT. Got " + readLine);
        }
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        if (str.matches(WEBVTT_TIMESTAMP_STRING)) {
            String[] split = str.split("\\.", 2);
            long j2 = 0;
            for (String str2 : split[0].split(":")) {
                j2 = (j2 * 60) + Long.parseLong(str2);
            }
            return (j2 * 1000) + Long.parseLong(split[1]);
        }
        throw new NumberFormatException("has invalid format");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "text";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        int length = this.sampleDurations.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = (this.sampleDurations[i2] * this.trackMetaData.getTimescale()) / 1000;
        }
        return jArr;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
