package com.wjlogin.onekey.sdk.common.a.b;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class a {
    public static String a(String str, String str2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), JDKeyStore.KEY_TYPE_AES);
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(b.a(str)));
        } catch (InvalidAlgorithmParameterException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return null;
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e6) {
            e6.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), JDKeyStore.KEY_TYPE_AES);
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            byte[] bytes = str.getBytes("utf-8");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return b.a(cipher.doFinal(bytes));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidAlgorithmParameterException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvalidKeyException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
            return null;
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e7) {
            e7.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e8) {
            e8.printStackTrace();
            return null;
        }
    }
}
