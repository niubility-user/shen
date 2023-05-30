package com.jd.aips.verify.config;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class IdCardNfcSdk implements Serializable {
    static final long serialVersionUID = 1628005884940418002L;
    public Config config;
    public String version;

    /* loaded from: classes12.dex */
    public class Config implements Serializable {
        static final long serialVersionUID = 5272617166942542489L;
        public String idcard_nfc_appid;
        public String idcard_nfc_button_colour;
        public String idcard_nfc_detection_text;
        public int idcard_nfc_detection_timeout;
        public int idcard_nfc_envCode;
        public String idcard_nfc_info_confirmed_text;
        public String idcard_nfc_ip;
        public String idcard_nfc_navigation_background_colour;
        public String idcard_nfc_navigation_text;
        public int idcard_nfc_port;
        public String idcard_nfc_prepare_text;
        public String idcard_nfc_privacy_statement_background_colour;
        public String idcard_nfc_privacy_statement_text;
        public int idcard_nfc_retry_count;
        public boolean idcard_nfc_show_safety_tips_dialog = false;

        public Config() {
        }
    }
}
