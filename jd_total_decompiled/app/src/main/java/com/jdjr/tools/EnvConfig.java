package com.jdjr.tools;

import android.content.Context;
import com.jdjr.dns.R;

/* loaded from: classes18.dex */
public class EnvConfig {
    public static String getApplyDigitalCertUrl(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_apply_url_test_env : R.string.security_apply_url);
    }

    public static String getImportServerCert(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_digital_cert_import_cert_test_env : R.string.security_digital_cert_import_cert);
    }

    public static String getImportServerCertGm(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_digital_cert_import_cert_gm_test_evn : R.string.security_digital_cert_import_cert_gm);
    }

    public static String getKeyboardCert(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_keyboard_cert_test_env : R.string.security_keyboard_cert_pro_env);
    }

    public static String getKeyboardCertGm(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_keyboard_cert_test_env_gm : R.string.security_keyboard_cert_pro_env_gm);
    }

    public static String getP7P1Cert(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_digital_cert_test_env : R.string.security_digital_cert_pro_env);
    }

    public static String getP7P1CertGm(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_digital_cert_test_env_gm : R.string.security_digital_cert_pro_env_gm);
    }

    public static String getSecurityCommunicationCert(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_cert_test_env : R.string.security_communication_cert_pro_env);
    }

    public static String getSecurityCommunicationCertGm(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_cert_test_env_gm : R.string.security_communication_cert_pro_env_gm);
    }

    public static String getSecurityCommunicationIp(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_ip_test_env : R.string.security_communication_ip_pro_env);
    }

    public static String getSecurityCommunicationIpGm(Context context) {
        return context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_ip_test_env : R.string.security_communication_ip_pro_env);
    }

    public static int getSecurityCommunicationPort(Context context) {
        return Integer.parseInt(context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_port_test_env : R.string.security_communication_port_pro_env));
    }

    public static int getSecurityCommunicationPortGm(Context context) {
        return Integer.parseInt(context.getString(EnvSwitchUtils.isTestEnvironment() ? R.string.security_communication_port_test_env : R.string.security_communication_port_pro_env));
    }
}
