package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.jingdong.corelib.utils.Log;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class JdWebviewBlackListUtil {
    private static final String TAG = "JdWebviewBlackListUtil";
    private static List<String> disableWebViewCacheBlackList;
    private static List<String> hideMoreBtnBlackList;

    static {
        ArrayList arrayList = new ArrayList();
        hideMoreBtnBlackList = arrayList;
        arrayList.add("in.m.jd.com/help/app/private_policy.html");
        hideMoreBtnBlackList.add("in.m.jd.com/help/app/user_authority_info.html");
        hideMoreBtnBlackList.add("in.m.jd.com/help/app/order_sharing_info.html");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/m/mreg");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/bind/enterbind");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/bind/loginbind");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/bind/regbind");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/risk/warning");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/risk/mosms");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/risk/riskuserbind");
        hideMoreBtnBlackList.add("plogin.m.jd.com/user/mfindpwd_notitle");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/m/mfindpwd");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdsmobilewithemail");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdsendemail");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdnoset");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdhistoryreceivermobile");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdreset");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfindpwdhistoryreceiver");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/mfpwd_penter");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/risk/instrument_msg");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/risk/instrument_msg_no_send");
        hideMoreBtnBlackList.add("passport.m.360buy.com/payPassword/validateFindPayPassword");
        hideMoreBtnBlackList.add("passport.m.jd.com/payPassword/validateFindPayPassword");
        hideMoreBtnBlackList.add("msc.jd.com/payPwd/loginpage/wcoo/index");
        hideMoreBtnBlackList.add("msc.jd.com/payPwd/loginpage/wcoo/toForgetPage");
        hideMoreBtnBlackList.add("m.jd.com//help/app/register.html");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/bind/unbind");
        hideMoreBtnBlackList.add("m.jd.com/help/app/register.html");
        hideMoreBtnBlackList.add("msc.jd.com/verify/loginpage/wcoo/toVeriPage");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/m/enterprisereg");
        hideMoreBtnBlackList.add("msc.jd.com/account/loginpage/wcoo/toSecurityPwd");
        hideMoreBtnBlackList.add("msc.jd.com/payPwd");
        hideMoreBtnBlackList.add("jdw.jd.hk/taxrate.html");
        hideMoreBtnBlackList.add("in.m.jd.com/help/app/address.html");
        hideMoreBtnBlackList.add("msc.jd.com/phone");
        hideMoreBtnBlackList.add("in.m.jd.com/help/app/peisonshoufei.html");
        hideMoreBtnBlackList.add("in.m.jd.com/html/fuwushuoming/index.html");
        hideMoreBtnBlackList.add("plogin.m.jd.com/cgi-bin/m/thirdapp_auth_page");
        hideMoreBtnBlackList.add("msc.jd.com/auth/loginpage/wcoo/toAuthPage?source=2");
        hideMoreBtnBlackList.add("jdpaycert.jd.com/jdpay/thirdAccess.action");
        ArrayList arrayList2 = new ArrayList();
        disableWebViewCacheBlackList = arrayList2;
        arrayList2.add("m.jd.com/user/apppay.action");
    }

    public static boolean needHideRightPopButton(String str) {
        String str2;
        try {
            str2 = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        Log.d(TAG, "needHideRightPopButton decoded url:" + str2);
        for (int i2 = 0; i2 < hideMoreBtnBlackList.size(); i2++) {
            if (str2.contains(hideMoreBtnBlackList.get(i2))) {
                return true;
            }
        }
        return str2.contains("moreHide=true");
    }

    public static boolean shouldDisableWebViewCache(String str) {
        String str2;
        try {
            str2 = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = "";
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        Log.d(TAG, "disableWebViewCacheBlackList decoded url:" + str2);
        for (int i2 = 0; i2 < disableWebViewCacheBlackList.size(); i2++) {
            if (str2.contains(disableWebViewCacheBlackList.get(i2))) {
                return true;
            }
        }
        return false;
    }
}
