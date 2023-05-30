package org.eclipse.paho.client.mqttv3.internal.security;

import com.jd.dynamic.DYConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import jd.wjlogin_sdk.util.ReplyCode;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes11.dex */
public class SSLSocketFactoryFactory {
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
    public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
    public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
    public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
    public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
    public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
    public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
    private static final String xorTag = "{xor}";
    private Hashtable configs;
    private Properties defaultProperties;
    private Logger logger;
    public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
    public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
    public static final String KEYSTORE = "com.ibm.ssl.keyStore";
    public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
    public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
    public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
    public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
    public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
    public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
    public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
    public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
    public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
    public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
    public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
    private static final String[] propertyKeys = {SSLPROTOCOL, JSSEPROVIDER, KEYSTORE, KEYSTOREPWD, KEYSTORETYPE, KEYSTOREPROVIDER, KEYSTOREMGR, TRUSTSTORE, TRUSTSTOREPWD, TRUSTSTORETYPE, TRUSTSTOREPROVIDER, TRUSTSTOREMGR, CIPHERSUITES, CLIENTAUTH};
    private static final byte[] key = {-99, ReplyCode.reply0xa7, -39, Byte.MIN_VALUE, 5, -72, ReplyCode.reply0x89, -100};

    public SSLSocketFactoryFactory() {
        this.logger = null;
        this.configs = new Hashtable();
    }

    private void checkPropertyKeys(Properties properties) throws IllegalArgumentException {
        for (String str : properties.keySet()) {
            if (!keyValid(str)) {
                StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
                stringBuffer.append(" is not a valid IBM SSL property key.");
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    private void convertPassword(Properties properties) {
        String property = properties.getProperty(KEYSTOREPWD);
        if (property != null && !property.startsWith(xorTag)) {
            properties.put(KEYSTOREPWD, obfuscate(property.toCharArray()));
        }
        String property2 = properties.getProperty(TRUSTSTOREPWD);
        if (property2 == null || property2.startsWith(xorTag)) {
            return;
        }
        properties.put(TRUSTSTOREPWD, obfuscate(property2.toCharArray()));
    }

    public static char[] deObfuscate(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] decode = SimpleBase64Encoder.decode(str.substring(5));
            for (int i2 = 0; i2 < decode.length; i2++) {
                byte b = decode[i2];
                byte[] bArr = key;
                decode[i2] = (byte) ((b ^ bArr[i2 % bArr.length]) & 255);
            }
            return toChar(decode);
        } catch (Exception unused) {
            return null;
        }
    }

    private String getProperty(String str, String str2, String str3) {
        String propertyFromConfig = getPropertyFromConfig(str, str2);
        return (propertyFromConfig == null && str3 != null) ? System.getProperty(str3) : propertyFromConfig;
    }

    private String getPropertyFromConfig(String str, String str2) {
        String str3 = null;
        Properties properties = str != null ? (Properties) this.configs.get(str) : null;
        if (properties == null || (str3 = properties.getProperty(str2)) == null) {
            Properties properties2 = this.defaultProperties;
            if (properties2 == null || (str3 = properties2.getProperty(str2)) != null) {
            }
            return str3;
        }
        return str3;
    }

    private SSLContext getSSLContext(String str) throws MqttSecurityException {
        SSLContext sSLContext;
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        TrustManagerFactory trustManagerFactory;
        KeyManagerFactory keyManagerFactory;
        String str2 = str;
        String sSLProtocol = getSSLProtocol(str);
        if (sSLProtocol == null) {
            sSLProtocol = DEFAULT_PROTOCOL;
        }
        Logger logger = this.logger;
        if (logger != null) {
            Object[] objArr = new Object[2];
            objArr[0] = str2 != null ? str2 : "null (broker defaults)";
            objArr[1] = sSLProtocol;
            logger.fine(CLASS_NAME, "getSSLContext", "12000", objArr);
        }
        String jSSEProvider = getJSSEProvider(str);
        try {
            if (jSSEProvider == null) {
                sSLContext = SSLContext.getInstance(sSLProtocol);
            } else {
                sSLContext = SSLContext.getInstance(sSLProtocol, jSSEProvider);
            }
            Logger logger2 = this.logger;
            if (logger2 != null) {
                Object[] objArr2 = new Object[2];
                objArr2[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr2[1] = sSLContext.getProvider().getName();
                logger2.fine(CLASS_NAME, "getSSLContext", "12001", objArr2);
            }
            String property = getProperty(str2, KEYSTORE, null);
            if (property == null) {
                property = getProperty(str2, KEYSTORE, SYSKEYSTORE);
            }
            Logger logger3 = this.logger;
            String str3 = DYConstants.DY_NULL_STR;
            if (logger3 != null) {
                Object[] objArr3 = new Object[2];
                objArr3[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr3[1] = property != null ? property : DYConstants.DY_NULL_STR;
                logger3.fine(CLASS_NAME, "getSSLContext", "12004", objArr3);
            }
            char[] keyStorePassword = getKeyStorePassword(str);
            Logger logger4 = this.logger;
            if (logger4 != null) {
                Object[] objArr4 = new Object[2];
                objArr4[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr4[1] = keyStorePassword != null ? obfuscate(keyStorePassword) : DYConstants.DY_NULL_STR;
                logger4.fine(CLASS_NAME, "getSSLContext", "12005", objArr4);
            }
            String keyStoreType = getKeyStoreType(str);
            if (keyStoreType == null) {
                keyStoreType = KeyStore.getDefaultType();
            }
            Logger logger5 = this.logger;
            if (logger5 != null) {
                Object[] objArr5 = new Object[2];
                objArr5[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr5[1] = keyStoreType != null ? keyStoreType : DYConstants.DY_NULL_STR;
                logger5.fine(CLASS_NAME, "getSSLContext", "12006", objArr5);
            }
            String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
            String keyStoreProvider = getKeyStoreProvider(str);
            String keyManager = getKeyManager(str);
            if (keyManager != null) {
                defaultAlgorithm = keyManager;
            }
            if (property == null || keyStoreType == null || defaultAlgorithm == null) {
                keyManagerArr = null;
            } else {
                try {
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(new FileInputStream(property), keyStorePassword);
                    if (keyStoreProvider != null) {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm, keyStoreProvider);
                    } else {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm);
                    }
                    Logger logger6 = this.logger;
                    if (logger6 != null) {
                        Object[] objArr6 = new Object[2];
                        objArr6[0] = str2 != null ? str2 : "null (broker defaults)";
                        if (defaultAlgorithm == null) {
                            defaultAlgorithm = DYConstants.DY_NULL_STR;
                        }
                        objArr6[1] = defaultAlgorithm;
                        logger6.fine(CLASS_NAME, "getSSLContext", "12010", objArr6);
                        Logger logger7 = this.logger;
                        Object[] objArr7 = new Object[2];
                        objArr7[0] = str2 != null ? str2 : "null (broker defaults)";
                        objArr7[1] = keyManagerFactory.getProvider().getName();
                        logger7.fine(CLASS_NAME, "getSSLContext", "12009", objArr7);
                    }
                    keyManagerFactory.init(keyStore, keyStorePassword);
                    keyManagerArr = keyManagerFactory.getKeyManagers();
                } catch (FileNotFoundException e2) {
                    throw new MqttSecurityException(e2);
                } catch (IOException e3) {
                    throw new MqttSecurityException(e3);
                } catch (KeyStoreException e4) {
                    throw new MqttSecurityException(e4);
                } catch (UnrecoverableKeyException e5) {
                    throw new MqttSecurityException(e5);
                } catch (CertificateException e6) {
                    throw new MqttSecurityException(e6);
                }
            }
            String trustStore = getTrustStore(str);
            Logger logger8 = this.logger;
            if (logger8 != null) {
                Object[] objArr8 = new Object[2];
                objArr8[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr8[1] = trustStore != null ? trustStore : DYConstants.DY_NULL_STR;
                logger8.fine(CLASS_NAME, "getSSLContext", "12011", objArr8);
            }
            char[] trustStorePassword = getTrustStorePassword(str);
            Logger logger9 = this.logger;
            if (logger9 != null) {
                Object[] objArr9 = new Object[2];
                objArr9[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr9[1] = trustStorePassword != null ? obfuscate(trustStorePassword) : DYConstants.DY_NULL_STR;
                logger9.fine(CLASS_NAME, "getSSLContext", "12012", objArr9);
            }
            String trustStoreType = getTrustStoreType(str);
            if (trustStoreType == null) {
                trustStoreType = KeyStore.getDefaultType();
            }
            Logger logger10 = this.logger;
            if (logger10 != null) {
                Object[] objArr10 = new Object[2];
                objArr10[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr10[1] = trustStoreType != null ? trustStoreType : DYConstants.DY_NULL_STR;
                logger10.fine(CLASS_NAME, "getSSLContext", "12013", objArr10);
            }
            String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
            String trustStoreProvider = getTrustStoreProvider(str);
            String trustManager = getTrustManager(str);
            if (trustManager != null) {
                defaultAlgorithm2 = trustManager;
            }
            if (trustStore == null || trustStoreType == null || defaultAlgorithm2 == null) {
                trustManagerArr = null;
            } else {
                try {
                    KeyStore keyStore2 = KeyStore.getInstance(trustStoreType);
                    keyStore2.load(new FileInputStream(trustStore), trustStorePassword);
                    if (trustStoreProvider != null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2, trustStoreProvider);
                    } else {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2);
                    }
                    Logger logger11 = this.logger;
                    if (logger11 != null) {
                        Object[] objArr11 = new Object[2];
                        objArr11[0] = str2 != null ? str2 : "null (broker defaults)";
                        if (defaultAlgorithm2 != null) {
                            str3 = defaultAlgorithm2;
                        }
                        objArr11[1] = str3;
                        logger11.fine(CLASS_NAME, "getSSLContext", "12017", objArr11);
                        Logger logger12 = this.logger;
                        Object[] objArr12 = new Object[2];
                        if (str2 == null) {
                            str2 = "null (broker defaults)";
                        }
                        objArr12[0] = str2;
                        objArr12[1] = trustManagerFactory.getProvider().getName();
                        logger12.fine(CLASS_NAME, "getSSLContext", "12016", objArr12);
                    }
                    trustManagerFactory.init(keyStore2);
                    trustManagerArr = trustManagerFactory.getTrustManagers();
                } catch (FileNotFoundException e7) {
                    throw new MqttSecurityException(e7);
                } catch (IOException e8) {
                    throw new MqttSecurityException(e8);
                } catch (KeyStoreException e9) {
                    throw new MqttSecurityException(e9);
                } catch (CertificateException e10) {
                    throw new MqttSecurityException(e10);
                }
            }
            sSLContext.init(keyManagerArr, trustManagerArr, null);
            return sSLContext;
        } catch (KeyManagementException e11) {
            throw new MqttSecurityException(e11);
        } catch (NoSuchAlgorithmException e12) {
            throw new MqttSecurityException(e12);
        } catch (NoSuchProviderException e13) {
            throw new MqttSecurityException(e13);
        }
    }

    public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
        try {
            Class.forName("javax.net.ssl.SSLServerSocketFactory");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private boolean keyValid(String str) {
        int i2;
        String[] strArr;
        while (true) {
            strArr = propertyKeys;
            i2 = (i2 < strArr.length && !strArr[i2].equals(str)) ? i2 + 1 : 0;
        }
        return i2 < strArr.length;
    }

    public static String obfuscate(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] bArr = toByte(cArr);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            byte[] bArr2 = key;
            bArr[i2] = (byte) ((b ^ bArr2[i2 % bArr2.length]) & 255);
        }
        StringBuffer stringBuffer = new StringBuffer(xorTag);
        stringBuffer.append(new String(SimpleBase64Encoder.encode(bArr)));
        return stringBuffer.toString();
    }

    public static String packCipherSuites(String[] strArr) {
        if (strArr != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                stringBuffer.append(strArr[i2]);
                if (i2 < strArr.length - 1) {
                    stringBuffer.append(',');
                }
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public static byte[] toByte(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] bArr = new byte[cArr.length * 2];
        int i2 = 0;
        for (int i3 = 0; i3 < cArr.length; i3++) {
            int i4 = i2 + 1;
            bArr[i2] = (byte) (cArr[i3] & '\u00ff');
            i2 = i4 + 1;
            bArr[i4] = (byte) ((cArr[i3] >> '\b') & 255);
        }
        return bArr;
    }

    public static char[] toChar(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length / 2];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            int i4 = i2 + 1;
            cArr[i3] = (char) ((bArr[i2] & 255) + ((bArr[i4] & 255) << 8));
            i3++;
            i2 = i4 + 1;
        }
        return cArr;
    }

    public static String[] unpackCipherSuites(String str) {
        if (str == null) {
            return null;
        }
        Vector vector = new Vector();
        int indexOf = str.indexOf(44);
        int i2 = 0;
        while (indexOf > -1) {
            vector.add(str.substring(i2, indexOf));
            i2 = indexOf + 1;
            indexOf = str.indexOf(44, i2);
        }
        vector.add(str.substring(i2));
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    public SSLSocketFactory createSocketFactory(String str) throws MqttSecurityException {
        SSLContext sSLContext = getSSLContext(str);
        Logger logger = this.logger;
        if (logger != null) {
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = getEnabledCipherSuites(str) != null ? getProperty(str, CIPHERSUITES, null) : "null (using platform-enabled cipher suites)";
            logger.fine(CLASS_NAME, "createSocketFactory", "12020", objArr);
        }
        return sSLContext.getSocketFactory();
    }

    public boolean getClientAuthentication(String str) {
        String property = getProperty(str, CLIENTAUTH, null);
        if (property != null) {
            return Boolean.valueOf(property).booleanValue();
        }
        return false;
    }

    public Properties getConfiguration(String str) {
        Object obj;
        if (str == null) {
            obj = this.defaultProperties;
        } else {
            obj = this.configs.get(str);
        }
        return (Properties) obj;
    }

    public String[] getEnabledCipherSuites(String str) {
        return unpackCipherSuites(getProperty(str, CIPHERSUITES, null));
    }

    public String getJSSEProvider(String str) {
        return getProperty(str, JSSEPROVIDER, null);
    }

    public String getKeyManager(String str) {
        return getProperty(str, KEYSTOREMGR, SYSKEYMGRALGO);
    }

    public String getKeyStore(String str) {
        String propertyFromConfig = getPropertyFromConfig(str, KEYSTORE);
        return propertyFromConfig != null ? propertyFromConfig : System.getProperty(SYSKEYSTORE);
    }

    public char[] getKeyStorePassword(String str) {
        String property = getProperty(str, KEYSTOREPWD, SYSKEYSTOREPWD);
        if (property != null) {
            if (property.startsWith(xorTag)) {
                return deObfuscate(property);
            }
            return property.toCharArray();
        }
        return null;
    }

    public String getKeyStoreProvider(String str) {
        return getProperty(str, KEYSTOREPROVIDER, null);
    }

    public String getKeyStoreType(String str) {
        return getProperty(str, KEYSTORETYPE, SYSKEYSTORETYPE);
    }

    public String getSSLProtocol(String str) {
        return getProperty(str, SSLPROTOCOL, null);
    }

    public String getTrustManager(String str) {
        return getProperty(str, TRUSTSTOREMGR, SYSTRUSTMGRALGO);
    }

    public String getTrustStore(String str) {
        return getProperty(str, TRUSTSTORE, SYSTRUSTSTORE);
    }

    public char[] getTrustStorePassword(String str) {
        String property = getProperty(str, TRUSTSTOREPWD, SYSTRUSTSTOREPWD);
        if (property != null) {
            if (property.startsWith(xorTag)) {
                return deObfuscate(property);
            }
            return property.toCharArray();
        }
        return null;
    }

    public String getTrustStoreProvider(String str) {
        return getProperty(str, TRUSTSTOREPROVIDER, null);
    }

    public String getTrustStoreType(String str) {
        return getProperty(str, TRUSTSTORETYPE, null);
    }

    public void initialize(Properties properties, String str) throws IllegalArgumentException {
        checkPropertyKeys(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        convertPassword(properties2);
        if (str != null) {
            this.configs.put(str, properties2);
        } else {
            this.defaultProperties = properties2;
        }
    }

    public void merge(Properties properties, String str) throws IllegalArgumentException {
        checkPropertyKeys(properties);
        Properties properties2 = this.defaultProperties;
        if (str != null) {
            properties2 = (Properties) this.configs.get(str);
        }
        if (properties2 == null) {
            properties2 = new Properties();
        }
        convertPassword(properties);
        properties2.putAll(properties);
        if (str != null) {
            this.configs.put(str, properties2);
        } else {
            this.defaultProperties = properties2;
        }
    }

    public boolean remove(String str) {
        if (str != null) {
            if (this.configs.remove(str) != null) {
                return true;
            }
        } else if (this.defaultProperties != null) {
            this.defaultProperties = null;
            return true;
        }
        return false;
    }

    public SSLSocketFactoryFactory(Logger logger) {
        this();
        this.logger = logger;
    }
}
