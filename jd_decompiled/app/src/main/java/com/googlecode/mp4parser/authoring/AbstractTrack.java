package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public abstract class AbstractTrack implements Track {
    String name;
    List<Edit> edits = new ArrayList();
    Map<GroupEntry, long[]> sampleGroups = new HashMap();

    public AbstractTrack(String str) {
        this.name = str;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        long j2 = 0;
        for (long j3 : getSampleDurations()) {
            j2 += j3;
        }
        return j2;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.edits;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getName() {
        return this.name;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.sampleGroups;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return null;
    }
}
