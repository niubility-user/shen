package com.xiaomi.push;

import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes11.dex */
public enum r4 {
    TCP_CONN_FAIL(1),
    TCP_CONN_TIME(2),
    PING_RTT(3),
    CHANNEL_CON_FAIL(4),
    CHANNEL_CON_OK(5),
    ICMP_PING_FAIL(6),
    ICMP_PING_OK(7),
    CHANNEL_ONLINE_RATE(8),
    BATCH_TCP_CONN_SUCCESS(1000),
    BATCH_TCP_CONN_FAIL(1001),
    CHANNEL_STATS_COUNTER(8000),
    GSLB_REQUEST_SUCCESS(10000),
    GSLB_TCP_NOACCESS(R2.drawable.live_list_like_05),
    GSLB_TCP_NETUNREACH(10102),
    GSLB_TCP_CONNREFUSED(10103),
    GSLB_TCP_NOROUTETOHOST(10104),
    GSLB_TCP_TIMEOUT(10105),
    GSLB_TCP_INVALARG(10106),
    GSLB_TCP_UKNOWNHOST(10107),
    GSLB_TCP_ERR_OTHER(R2.drawable.login_qq_normal),
    GSLB_ERR(R2.drawable.security_general_six_square_last_item_bg_dark),
    CONN_SUCCESS(20000),
    CONN_TCP_NOACCESS(R2.string.mtrl_picker_invalid_format_use),
    CONN_TCP_NETUNREACH(R2.string.mtrl_picker_invalid_range),
    CONN_TCP_CONNREFUSED(R2.string.mtrl_picker_navigate_to_year_description),
    CONN_TCP_NOROUTETOHOST(R2.string.mtrl_picker_out_of_range),
    CONN_TCP_TIMEOUT(R2.string.mtrl_picker_range_header_only_end_selected),
    CONN_TCP_INVALARG(R2.string.mtrl_picker_range_header_only_start_selected),
    CONN_TCP_UKNOWNHOST(R2.string.mtrl_picker_range_header_selected),
    CONN_TCP_ERR_OTHER(R2.string.owidgets_point_lack),
    CONN_XMPP_ERR(R2.string.recent_app_brand),
    CONN_BOSH_UNKNOWNHOST(R2.string.recommend_ensure),
    CONN_BOSH_ERR(R2.string.security_digital_cert_import_cert),
    BIND_SUCCESS(30000),
    BIND_TCP_READ_TIMEOUT_DEPRECTED(30101),
    BIND_TCP_CONNRESET_DEPRECTED(30102),
    BIND_TCP_BROKEN_PIPE_DEPRECTED(30103),
    BIND_TCP_READ_TIMEOUT(30108),
    BIND_TCP_CONNRESET(30109),
    BIND_TCP_BROKEN_PIPE(30110),
    BIND_TCP_ERR(30199),
    BIND_XMPP_ERR(30399),
    BIND_BOSH_ITEM_NOT_FOUND(30401),
    BIND_BOSH_ERR(30499),
    BIND_TIMEOUT(30501),
    BIND_INVALID_SIG(30502),
    CHANNEL_TCP_READTIMEOUT_DEPRECTED(40101),
    CHANNEL_TCP_CONNRESET_DEPRECTED(40102),
    CHANNEL_TCP_BROKEN_PIPE_DEPRECTED(40103),
    CHANNEL_TCP_READTIMEOUT(40108),
    CHANNEL_TCP_CONNRESET(40109),
    CHANNEL_TCP_BROKEN_PIPE(40110),
    CHANNEL_TCP_ERR(40199),
    CHANNEL_XMPPEXCEPTION(40399),
    CHANNEL_BOSH_ITEMNOTFIND(40401),
    CHANNEL_BOSH_EXCEPTION(40499),
    CHANNEL_TIMER_DELAYED(PkgDetailEntity.OPEN_ERROR);
    

    /* renamed from: a  reason: collision with other field name */
    private final int f202a;

    r4(int i2) {
        this.f202a = i2;
    }

    public static r4 a(int i2) {
        if (i2 != 30501) {
            if (i2 != 30502) {
                switch (i2) {
                    case 1:
                        return TCP_CONN_FAIL;
                    case 2:
                        return TCP_CONN_TIME;
                    case 3:
                        return PING_RTT;
                    case 4:
                        return CHANNEL_CON_FAIL;
                    case 5:
                        return CHANNEL_CON_OK;
                    case 6:
                        return ICMP_PING_FAIL;
                    case 7:
                        return ICMP_PING_OK;
                    case 8:
                        return CHANNEL_ONLINE_RATE;
                    default:
                        switch (i2) {
                            case 8000:
                                return CHANNEL_STATS_COUNTER;
                            case 10000:
                                return GSLB_REQUEST_SUCCESS;
                            case R2.drawable.login_qq_normal /* 10199 */:
                                return GSLB_TCP_ERR_OTHER;
                            case R2.drawable.security_general_six_square_last_item_bg_dark /* 10999 */:
                                return GSLB_ERR;
                            case 20000:
                                return CONN_SUCCESS;
                            case R2.string.owidgets_point_lack /* 20199 */:
                                return CONN_TCP_ERR_OTHER;
                            case R2.string.recent_app_brand /* 20399 */:
                                return CONN_XMPP_ERR;
                            case R2.string.recommend_ensure /* 20407 */:
                                return CONN_BOSH_UNKNOWNHOST;
                            case R2.string.security_digital_cert_import_cert /* 20499 */:
                                return CONN_BOSH_ERR;
                            case 30000:
                                return BIND_SUCCESS;
                            case 30199:
                                return BIND_TCP_ERR;
                            case 30399:
                                return BIND_XMPP_ERR;
                            case 30401:
                                return BIND_BOSH_ITEM_NOT_FOUND;
                            case 30499:
                                return BIND_BOSH_ERR;
                            case 40199:
                                return CHANNEL_TCP_ERR;
                            case 40399:
                                return CHANNEL_XMPPEXCEPTION;
                            case 40401:
                                return CHANNEL_BOSH_ITEMNOTFIND;
                            case 40499:
                                return CHANNEL_BOSH_EXCEPTION;
                            case PkgDetailEntity.OPEN_ERROR /* 50001 */:
                                return CHANNEL_TIMER_DELAYED;
                            default:
                                switch (i2) {
                                    case R2.drawable.live_list_like_05 /* 10101 */:
                                        return GSLB_TCP_NOACCESS;
                                    case 10102:
                                        return GSLB_TCP_NETUNREACH;
                                    case 10103:
                                        return GSLB_TCP_CONNREFUSED;
                                    case 10104:
                                        return GSLB_TCP_NOROUTETOHOST;
                                    case 10105:
                                        return GSLB_TCP_TIMEOUT;
                                    case 10106:
                                        return GSLB_TCP_INVALARG;
                                    case 10107:
                                        return GSLB_TCP_UKNOWNHOST;
                                    default:
                                        switch (i2) {
                                            case R2.string.mtrl_picker_invalid_format_use /* 20101 */:
                                                return CONN_TCP_NOACCESS;
                                            case R2.string.mtrl_picker_invalid_range /* 20102 */:
                                                return CONN_TCP_NETUNREACH;
                                            case R2.string.mtrl_picker_navigate_to_year_description /* 20103 */:
                                                return CONN_TCP_CONNREFUSED;
                                            case R2.string.mtrl_picker_out_of_range /* 20104 */:
                                                return CONN_TCP_NOROUTETOHOST;
                                            case R2.string.mtrl_picker_range_header_only_end_selected /* 20105 */:
                                                return CONN_TCP_TIMEOUT;
                                            case R2.string.mtrl_picker_range_header_only_start_selected /* 20106 */:
                                                return CONN_TCP_INVALARG;
                                            case R2.string.mtrl_picker_range_header_selected /* 20107 */:
                                                return CONN_TCP_UKNOWNHOST;
                                            default:
                                                switch (i2) {
                                                    case 30101:
                                                        return BIND_TCP_READ_TIMEOUT_DEPRECTED;
                                                    case 30102:
                                                        return BIND_TCP_CONNRESET_DEPRECTED;
                                                    case 30103:
                                                        return BIND_TCP_BROKEN_PIPE_DEPRECTED;
                                                    default:
                                                        switch (i2) {
                                                            case 30108:
                                                                return BIND_TCP_READ_TIMEOUT;
                                                            case 30109:
                                                                return BIND_TCP_CONNRESET;
                                                            case 30110:
                                                                return BIND_TCP_BROKEN_PIPE;
                                                            default:
                                                                switch (i2) {
                                                                    case 40101:
                                                                        return CHANNEL_TCP_READTIMEOUT_DEPRECTED;
                                                                    case 40102:
                                                                        return CHANNEL_TCP_CONNRESET_DEPRECTED;
                                                                    case 40103:
                                                                        return CHANNEL_TCP_BROKEN_PIPE_DEPRECTED;
                                                                    default:
                                                                        switch (i2) {
                                                                            case 40108:
                                                                                return CHANNEL_TCP_READTIMEOUT;
                                                                            case 40109:
                                                                                return CHANNEL_TCP_CONNRESET;
                                                                            case 40110:
                                                                                return CHANNEL_TCP_BROKEN_PIPE;
                                                                            default:
                                                                                return null;
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            }
            return BIND_INVALID_SIG;
        }
        return BIND_TIMEOUT;
    }

    public int a() {
        return this.f202a;
    }
}
