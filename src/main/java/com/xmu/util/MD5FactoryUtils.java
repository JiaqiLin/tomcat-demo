package com.xmu.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5FactoryUtils {
    public static String eccrypt(String salt,String info) {
        String  md5str = DigestUtils.md5Hex(salt + info);
        return md5str;
    }
}
