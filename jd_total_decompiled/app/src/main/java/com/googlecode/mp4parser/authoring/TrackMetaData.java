package com.googlecode.mp4parser.authoring;

import com.googlecode.mp4parser.util.Matrix;
import java.util.Date;

/* loaded from: classes12.dex */
public class TrackMetaData implements Cloneable {
    private double height;
    int layer;
    private long timescale;
    private float volume;
    private double width;
    private String language = "eng";
    private Date modificationTime = new Date();
    private Date creationTime = new Date();
    private Matrix matrix = Matrix.ROTATE_0;
    private long trackId = 1;
    private int group = 0;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public int getGroup() {
        return this.group;
    }

    public double getHeight() {
        return this.height;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getLayer() {
        return this.layer;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public Date getModificationTime() {
        return this.modificationTime;
    }

    public long getTimescale() {
        return this.timescale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public double getWidth() {
        return this.width;
    }

    public void setCreationTime(Date date) {
        this.creationTime = date;
    }

    public void setGroup(int i2) {
        this.group = i2;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setLayer(int i2) {
        this.layer = i2;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void setModificationTime(Date date) {
        this.modificationTime = date;
    }

    public void setTimescale(long j2) {
        this.timescale = j2;
    }

    public void setTrackId(long j2) {
        this.trackId = j2;
    }

    public void setVolume(float f2) {
        this.volume = f2;
    }

    public void setWidth(double d) {
        this.width = d;
    }
}
