package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: classes.dex */
public class HintMediaHeaderBox extends AbstractMediaHeaderBox {
    public static final String TYPE = "hmhd";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private long avgBitrate;
    private int avgPduSize;
    private long maxBitrate;
    private int maxPduSize;

    static {
        ajc$preClinit();
    }

    public HintMediaHeaderBox() {
        super(TYPE);
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("HintMediaHeaderBox.java", HintMediaHeaderBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getMaxPduSize", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "int"), 42);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getAvgPduSize", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "int"), 46);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getMaxBitrate", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "long"), 50);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getAvgBitrate", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "long"), 54);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "toString", "com.coremedia.iso.boxes.HintMediaHeaderBox", "", "", "", "java.lang.String"), 84);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.maxPduSize = IsoTypeReader.readUInt16(byteBuffer);
        this.avgPduSize = IsoTypeReader.readUInt16(byteBuffer);
        this.maxBitrate = IsoTypeReader.readUInt32(byteBuffer);
        this.avgBitrate = IsoTypeReader.readUInt32(byteBuffer);
        IsoTypeReader.readUInt32(byteBuffer);
    }

    public long getAvgBitrate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.avgBitrate;
    }

    public int getAvgPduSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.avgPduSize;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt16(byteBuffer, this.maxPduSize);
        IsoTypeWriter.writeUInt16(byteBuffer, this.avgPduSize);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxBitrate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.avgBitrate);
        IsoTypeWriter.writeUInt32(byteBuffer, 0L);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected long getContentSize() {
        return 20L;
    }

    public long getMaxBitrate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.maxBitrate;
    }

    public int getMaxPduSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.maxPduSize;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return "HintMediaHeaderBox{maxPduSize=" + this.maxPduSize + ", avgPduSize=" + this.avgPduSize + ", maxBitrate=" + this.maxBitrate + ", avgBitrate=" + this.avgBitrate + '}';
    }
}
