package com.coremedia.iso.boxes.threegpp26244;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public class LocationInformationBox extends AbstractFullBox {
    public static final String TYPE = "loci";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_14 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_15 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    private String additionalNotes;
    private double altitude;
    private String astronomicalBody;
    private String language;
    private double latitude;
    private double longitude;
    private String name;
    private int role;

    static {
        ajc$preClinit();
    }

    public LocationInformationBox() {
        super(TYPE);
        this.name = "";
        this.astronomicalBody = "";
        this.additionalNotes = "";
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("LocationInformationBox.java", LocationInformationBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getLanguage", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 30);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setLanguage", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", IjkMediaMeta.IJKM_KEY_LANGUAGE, "", "void"), 34);
        ajc$tjp_10 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getAltitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 70);
        ajc$tjp_11 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setAltitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", "altitude", "", "void"), 74);
        ajc$tjp_12 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getAstronomicalBody", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 78);
        ajc$tjp_13 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setAstronomicalBody", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "astronomicalBody", "", "void"), 82);
        ajc$tjp_14 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getAdditionalNotes", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 86);
        ajc$tjp_15 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setAdditionalNotes", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "additionalNotes", "", "void"), 90);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getName", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "java.lang.String"), 38);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setName", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "java.lang.String", "name", "", "void"), 42);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getRole", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "int"), 46);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setRole", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "int", "role", "", "void"), 50);
        ajc$tjp_6 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getLongitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 54);
        ajc$tjp_7 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setLongitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", PdLVBody.LONGITUDE, "", "void"), 58);
        ajc$tjp_8 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getLatitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "", "", "", "double"), 62);
        ajc$tjp_9 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setLatitude", "com.coremedia.iso.boxes.threegpp26244.LocationInformationBox", "double", PdLVBody.LATITUDE, "", "void"), 66);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.language = IsoTypeReader.readIso639(byteBuffer);
        this.name = IsoTypeReader.readString(byteBuffer);
        this.role = IsoTypeReader.readUInt8(byteBuffer);
        this.longitude = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.latitude = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.altitude = IsoTypeReader.readFixedPoint1616(byteBuffer);
        this.astronomicalBody = IsoTypeReader.readString(byteBuffer);
        this.additionalNotes = IsoTypeReader.readString(byteBuffer);
    }

    public String getAdditionalNotes() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this));
        return this.additionalNotes;
    }

    public double getAltitude() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.altitude;
    }

    public String getAstronomicalBody() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.astronomicalBody;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeIso639(byteBuffer, this.language);
        byteBuffer.put(Utf8.convert(this.name));
        byteBuffer.put((byte) 0);
        IsoTypeWriter.writeUInt8(byteBuffer, this.role);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.longitude);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.latitude);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.altitude);
        byteBuffer.put(Utf8.convert(this.astronomicalBody));
        byteBuffer.put((byte) 0);
        byteBuffer.put(Utf8.convert(this.additionalNotes));
        byteBuffer.put((byte) 0);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected long getContentSize() {
        return Utf8.convert(this.name).length + 22 + Utf8.convert(this.astronomicalBody).length + Utf8.convert(this.additionalNotes).length;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.language;
    }

    public double getLatitude() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.latitude;
    }

    public double getLongitude() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.longitude;
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.name;
    }

    public int getRole() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.role;
    }

    public void setAdditionalNotes(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, str));
        this.additionalNotes = str;
    }

    public void setAltitude(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.doubleObject(d)));
        this.altitude = d;
    }

    public void setAstronomicalBody(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, str));
        this.astronomicalBody = str;
    }

    public void setLanguage(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.language = str;
    }

    public void setLatitude(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.doubleObject(d)));
        this.latitude = d;
    }

    public void setLongitude(double d) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.doubleObject(d)));
        this.longitude = d;
    }

    public void setName(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.name = str;
    }

    public void setRole(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i2)));
        this.role = i2;
    }
}
