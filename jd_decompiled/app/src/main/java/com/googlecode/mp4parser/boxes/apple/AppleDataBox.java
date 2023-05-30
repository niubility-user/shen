package com.googlecode.mp4parser.boxes.apple;

import androidx.room.RoomMasterTable;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import com.jd.aips.verify.VerifyEngine;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.phonecharge.phone.HttpAdress;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;
import com.jingdong.sdk.jdhttpdns.pojo.IpModelOld;
import com.tencent.connect.common.Constants;
import com.unionpay.tsmservice.data.Constant;
import com.wjlogin.onekey.sdk.common.a.b.h;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;
import jd.wjlogin_sdk.util.f;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: classes12.dex */
public abstract class AppleDataBox extends AbstractBox {
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static HashMap<String, String> language;
    int dataCountry;
    int dataLanguage;
    int dataType;

    static {
        ajc$preClinit();
        HashMap<String, String> hashMap = new HashMap<>();
        language = hashMap;
        hashMap.put("0", "English");
        language.put("1", "French");
        language.put("2", "German");
        language.put("3", "Italian");
        language.put("4", "Dutch");
        language.put("5", "Swedish");
        language.put("6", "Spanish");
        language.put("7", "Danish");
        language.put("8", "Portuguese");
        language.put("9", "Norwegian");
        language.put("10", "Hebrew");
        language.put("11", "Japanese");
        language.put("12", "Arabic");
        language.put("13", "Finnish");
        language.put("14", "Greek");
        language.put("15", "Icelandic");
        language.put("16", "Maltese");
        language.put(Constants.VIA_REPORT_TYPE_START_GROUP, "Turkish");
        language.put("18", "Croatian");
        language.put(Constants.VIA_ACT_TYPE_NINETEEN, "Traditional_Chinese");
        language.put("20", "Urdu");
        language.put("21", "Hindi");
        language.put("22", "Thai");
        language.put(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "Korean");
        language.put("24", "Lithuanian");
        language.put("25", "Polish");
        language.put(Constants.VIA_REPORT_TYPE_CHAT_VIDEO, "Hungarian");
        language.put("27", "Estonian");
        language.put(Constants.VIA_ACT_TYPE_TWENTY_EIGHT, "Lettish");
        language.put(CartConstant.SUIT_TYPE_COMMON_PACK_FULL_GIFT, "Sami");
        language.put("30", "Faroese");
        language.put("31", "Farsi");
        language.put("32", "Russian");
        language.put(h.f18353g, "Simplified_Chinese");
        language.put("34", "Flemish");
        language.put("35", "Irish");
        language.put("36", "Albanian");
        language.put("37", "Romanian");
        language.put("38", "Czech");
        language.put("39", "Slovak");
        language.put("40", "Slovenian");
        language.put("41", "Yiddish");
        language.put(RoomMasterTable.DEFAULT_ID, "Serbian");
        language.put("43", "Macedonian");
        language.put(h.f18352f, "Bulgarian");
        language.put(DYConstants.DY_I_45, "Ukrainian");
        language.put("46", "Belarusian");
        language.put(HourlyGoObserverManager.HOURLY_GO_SOURCE_VALUE_GUIDE_BUBBLE, "Uzbek");
        language.put(HourlyGoObserverManager.HOURLY_GO_SOURCE_VALUE_ORDER_BUBBLE, "Kazakh");
        language.put("49", "Azerbaijani");
        language.put("50", "AzerbaijanAr");
        language.put(VerifyEngine.JDJR_WEB_JS_TYPE, "Armenian");
        language.put("52", "Georgian");
        language.put("53", "Moldavian");
        language.put("54", "Kirghiz");
        language.put("55", "Tajiki");
        language.put("56", "Turkmen");
        language.put("57", "Mongolian");
        language.put("58", "MongolianCyr");
        language.put("59", "Pashto");
        language.put(Constant.TRANS_TYPE_LOAD, "Kurdish");
        language.put("61", "Kashmiri");
        language.put("62", "Sindhi");
        language.put(Constant.TRANS_TYPE_CASH_LOAD, "Tibetan");
        language.put("64", "Nepali");
        language.put("65", "Sanskrit");
        language.put(f.f19955e, "Marathi");
        language.put("67", "Bengali");
        language.put("68", "Assamese");
        language.put("69", "Gujarati");
        language.put("70", "Punjabi");
        language.put("71", "Oriya");
        language.put("72", "Malayalam");
        language.put(XView2Constants.PLAY_TYPE, "Kannada");
        language.put("74", "Tamil");
        language.put("75", "Telugu");
        language.put("76", "Sinhala");
        language.put("77", "Burmese");
        language.put("78", "Khmer");
        language.put("79", "Lao");
        language.put(IpModelOld.PORT_HTTP, "Vietnamese");
        language.put("81", "Indonesian");
        language.put("82", "Tagalog");
        language.put("83", "MalayRoman");
        language.put("84", "MalayArabic");
        language.put("85", "Amharic");
        language.put(HttpAdress.orderType_flow, "Galla");
        language.put(HttpAdress.orderType_flow, "Oromo");
        language.put("88", "Somali");
        language.put("89", "Swahili");
        language.put(DYConstants.DY_I_90, "Kinyarwanda");
        language.put("91", "Rundi");
        language.put("92", "Nyanja");
        language.put("93", "Malagasy");
        language.put("94", "Esperanto");
        language.put("128", "Welsh");
        language.put("129", "Basque");
        language.put("130", "Catalan");
        language.put("131", "Latin");
        language.put("132", "Quechua");
        language.put("133", "Guarani");
        language.put("134", "Aymara");
        language.put(DYConstants.DY_I_135, "Tatar");
        language.put("136", "Uighur");
        language.put("137", "Dzongkha");
        language.put("138", "JavaneseRom");
        language.put("32767", "Unspecified");
    }

    public AppleDataBox(String str, int i2) {
        super(str);
        this.dataType = i2;
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("AppleDataBox.java", AppleDataBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getLanguageString", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "", "", "", "java.lang.String"), 25);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getDataType", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 43);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getDataCountry", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 47);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setDataCountry", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "int", "dataCountry", "", "void"), 51);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getDataLanguage", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "", "", "", "int"), 55);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setDataLanguage", "com.googlecode.mp4parser.boxes.apple.AppleDataBox", "int", "dataLanguage", "", "void"), 59);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void _parseDetails(ByteBuffer byteBuffer) {
        parseData(parseDataLength4ccTypeCountryLanguageAndReturnRest(byteBuffer));
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void getContent(ByteBuffer byteBuffer) {
        writeDataLength4ccTypeCountryLanguage(byteBuffer);
        byteBuffer.put(writeData());
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected long getContentSize() {
        return getDataLength() + 16;
    }

    public int getDataCountry() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.dataCountry;
    }

    public int getDataLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.dataLanguage;
    }

    protected abstract int getDataLength();

    public int getDataType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.dataType;
    }

    public String getLanguageString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        HashMap<String, String> hashMap = language;
        StringBuilder sb = new StringBuilder();
        sb.append(this.dataLanguage);
        String str = hashMap.get(sb.toString());
        if (str == null) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[2]);
            IsoTypeWriter.writeUInt16(wrap, this.dataLanguage);
            wrap.reset();
            return new Locale(IsoTypeReader.readIso639(wrap)).getDisplayLanguage();
        }
        return str;
    }

    protected abstract void parseData(ByteBuffer byteBuffer);

    @DoNotParseDetail
    protected ByteBuffer parseDataLength4ccTypeCountryLanguageAndReturnRest(ByteBuffer byteBuffer) {
        int i2 = byteBuffer.getInt();
        byteBuffer.getInt();
        this.dataType = byteBuffer.getInt();
        short s = byteBuffer.getShort();
        this.dataCountry = s;
        if (s < 0) {
            this.dataCountry = s + 65536;
        }
        short s2 = byteBuffer.getShort();
        this.dataLanguage = s2;
        if (s2 < 0) {
            this.dataLanguage = s2 + 65536;
        }
        int i3 = i2 - 16;
        ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.duplicate().slice().limit(i3);
        byteBuffer.position(i3 + byteBuffer.position());
        return byteBuffer2;
    }

    public void setDataCountry(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i2)));
        this.dataCountry = i2;
    }

    public void setDataLanguage(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i2)));
        this.dataLanguage = i2;
    }

    protected abstract byte[] writeData();

    @DoNotParseDetail
    protected void writeDataLength4ccTypeCountryLanguage(ByteBuffer byteBuffer) {
        byteBuffer.putInt(getDataLength() + 16);
        byteBuffer.put("data".getBytes());
        byteBuffer.putInt(this.dataType);
        IsoTypeWriter.writeUInt16(byteBuffer, this.dataCountry);
        IsoTypeWriter.writeUInt16(byteBuffer, this.dataLanguage);
    }
}
