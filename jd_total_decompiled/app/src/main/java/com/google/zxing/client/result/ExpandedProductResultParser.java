package com.google.zxing.client.result;

import com.alibaba.fastjson.parser.JSONLexer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import kotlin.text.Typography;

/* loaded from: classes12.dex */
public final class ExpandedProductResultParser extends ResultParser {
    private static String findAIvalue(int i2, String str) {
        if (str.charAt(i2) != '(') {
            return null;
        }
        String substring = str.substring(i2 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < substring.length(); i3++) {
            char charAt = substring.charAt(i3);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i2, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i2);
        for (int i3 = 0; i3 < substring.length(); i3++) {
            char charAt = substring.charAt(i3);
            if (charAt == '(') {
                if (findAIvalue(i3, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x020e, code lost:
        if (r1.equals("10") == false) goto L13;
     */
    @Override // com.google.zxing.client.result.ResultParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ExpandedProductParsedResult parse(Result result) {
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        HashMap hashMap = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        int i2 = 0;
        while (i2 < massagedText.length()) {
            String findAIvalue = findAIvalue(i2, massagedText);
            if (findAIvalue == null) {
                return expandedProductParsedResult;
            }
            char c2 = 2;
            int length = i2 + findAIvalue.length() + 2;
            String findValue = findValue(length, massagedText);
            int length2 = length + findValue.length();
            findAIvalue.hashCode();
            switch (findAIvalue.hashCode()) {
                case R2.attr.placeholderImageScaleType /* 1536 */:
                    if (findAIvalue.equals("00")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.attr.placeholderText /* 1537 */:
                    if (findAIvalue.equals("01")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.attr.progress_cancel /* 1567 */:
                    break;
                case R2.attr.prospectNum /* 1568 */:
                    if (findAIvalue.equals("11")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.attr.pstsDividerColor /* 1570 */:
                    if (findAIvalue.equals("13")) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.attr.pstsDividerPadding /* 1572 */:
                    if (findAIvalue.equals("15")) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.attr.pstsIndicatorColor /* 1574 */:
                    if (findAIvalue.equals(Constants.VIA_REPORT_TYPE_START_GROUP)) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567966:
                    if (findAIvalue.equals("3100")) {
                        c2 = 7;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567967:
                    if (findAIvalue.equals("3101")) {
                        c2 = '\b';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567968:
                    if (findAIvalue.equals("3102")) {
                        c2 = '\t';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567969:
                    if (findAIvalue.equals("3103")) {
                        c2 = '\n';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567970:
                    if (findAIvalue.equals("3104")) {
                        c2 = 11;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567971:
                    if (findAIvalue.equals("3105")) {
                        c2 = '\f';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567972:
                    if (findAIvalue.equals("3106")) {
                        c2 = '\r';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567973:
                    if (findAIvalue.equals("3107")) {
                        c2 = 14;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567974:
                    if (findAIvalue.equals("3108")) {
                        c2 = 15;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1567975:
                    if (findAIvalue.equals("3109")) {
                        c2 = 16;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568927:
                    if (findAIvalue.equals("3200")) {
                        c2 = 17;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568928:
                    if (findAIvalue.equals("3201")) {
                        c2 = 18;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568929:
                    if (findAIvalue.equals("3202")) {
                        c2 = 19;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568930:
                    if (findAIvalue.equals("3203")) {
                        c2 = 20;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568931:
                    if (findAIvalue.equals("3204")) {
                        c2 = 21;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568932:
                    if (findAIvalue.equals("3205")) {
                        c2 = 22;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568933:
                    if (findAIvalue.equals("3206")) {
                        c2 = 23;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568934:
                    if (findAIvalue.equals("3207")) {
                        c2 = 24;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568935:
                    if (findAIvalue.equals("3208")) {
                        c2 = 25;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1568936:
                    if (findAIvalue.equals("3209")) {
                        c2 = JSONLexer.EOI;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575716:
                    if (findAIvalue.equals("3920")) {
                        c2 = 27;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575717:
                    if (findAIvalue.equals("3921")) {
                        c2 = 28;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575718:
                    if (findAIvalue.equals("3922")) {
                        c2 = 29;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575719:
                    if (findAIvalue.equals("3923")) {
                        c2 = 30;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575747:
                    if (findAIvalue.equals("3930")) {
                        c2 = 31;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575748:
                    if (findAIvalue.equals("3931")) {
                        c2 = ' ';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575749:
                    if (findAIvalue.equals("3932")) {
                        c2 = '!';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1575750:
                    if (findAIvalue.equals("3933")) {
                        c2 = Typography.quote;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    str2 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 1:
                    str = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 2:
                    str3 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 3:
                    str4 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 4:
                    str5 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 5:
                    str6 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 6:
                    str7 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    str12 = findAIvalue.substring(3);
                    str11 = findValue;
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                case 31:
                case ' ':
                case '!':
                case '\"':
                    if (findValue.length() < 4) {
                        return null;
                    }
                    String substring = findValue.substring(3);
                    str13 = findValue.substring(0, 3);
                    str11 = substring;
                    str12 = findAIvalue.substring(3);
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
                default:
                    hashMap.put(findAIvalue, findValue);
                    continue;
                    i2 = length2;
                    expandedProductParsedResult = null;
            }
            str8 = findValue;
            i2 = length2;
            expandedProductParsedResult = null;
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, hashMap);
    }
}
