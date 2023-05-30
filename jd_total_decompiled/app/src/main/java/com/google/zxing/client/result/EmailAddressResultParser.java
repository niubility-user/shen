package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.tencent.smtt.sdk.WebView;
import java.util.Map;

/* loaded from: classes12.dex */
public final class EmailAddressResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String str;
        String massagedText = ResultParser.getMassagedText(result);
        String str2 = null;
        if (!massagedText.startsWith(WebView.SCHEME_MAILTO) && !massagedText.startsWith("MAILTO:")) {
            if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(massagedText)) {
                return new EmailAddressParsedResult(massagedText, null, null, WebView.SCHEME_MAILTO + massagedText);
            }
            return null;
        }
        String substring = massagedText.substring(7);
        int indexOf = substring.indexOf(63);
        if (indexOf >= 0) {
            substring = substring.substring(0, indexOf);
        }
        String urlDecode = ResultParser.urlDecode(substring);
        Map<String, String> parseNameValuePairs = ResultParser.parseNameValuePairs(massagedText);
        if (parseNameValuePairs != null) {
            if (urlDecode.isEmpty()) {
                urlDecode = parseNameValuePairs.get(RemoteMessageConst.TO);
            }
            str2 = parseNameValuePairs.get("subject");
            str = parseNameValuePairs.get("body");
        } else {
            str = null;
        }
        return new EmailAddressParsedResult(urlDecode, str2, str, massagedText);
    }
}
