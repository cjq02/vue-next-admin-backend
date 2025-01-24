package com.jacquinc.admin.common.util;

import com.jacquinc.admin.application.bo.RsaBO;
import com.jacquinc.admin.application.constants.Constants;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.spring.context.utils.SpringContextUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by JiangSF on 18/2/1.
 */
public class RsaEncryptKeyUtil {

    private static Logger logger = LoggerFactory.getLogger(RsaEncryptKeyUtil.class);

    private static final BouncyCastleProvider BOUNCY_CASTLE_PROVIDER = new BouncyCastleProvider();

    /**
     * 生成RSA秘钥
     *
     * @param deviceuuid
     * @return
     */
    public static RsaBO getEncryptKey(String deviceuuid) {
        RSAPublicKey rsaPublicKey = getRsaPublicKey(deviceuuid);
        return getRsaVO(rsaPublicKey);
    }

    private static RSAPublicKey getRsaPublicKey(String deviceuuid) {
        RSAPublicKey rsaPublicKey = null;
        ICacheClient cacheClient = getCacheClient();
        if (cacheClient.get(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_RSA_PREFIX_KEY + deviceuuid) == null) {
            try {
                // 生成密钥对，并存放于session
                KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
                keyPairGen.initialize(1024, new SecureRandom());
                KeyPair keyPair = keyPairGen.generateKeyPair();
                cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_RSA_PREFIX_KEY + deviceuuid, keyPair);
            } catch (Exception e) {
                logger.error("rsa encrypt exception: " + e.getMessage(), e);
                throw new BusinessException("系统错误");
            }
        }
        // 获取公钥
        rsaPublicKey = (RSAPublicKey) ((KeyPair) cacheClient.get(
                Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_RSA_PREFIX_KEY + deviceuuid)).getPublic();
        return rsaPublicKey;
    }

    private static RsaBO getRsaVO(RSAPublicKey rsaPublicKey) {
        // 加密模数
        String rsaModule = rsaPublicKey.getModulus().toString(16);
        // 加密指数
        String rsaExponent = rsaPublicKey.getPublicExponent().toString(16);
        // 返回私钥加密属性
        logger.debug("rsaModule:" + rsaModule);
        logger.debug("rsaExponent:" + rsaExponent);
        RsaBO rsaVOExt = new RsaBO();
        rsaVOExt.setRsaModule(rsaModule);
        rsaVOExt.setRsaExponent(rsaExponent);
        return rsaVOExt;
    }

    private static ICacheClient getCacheClient() {
        return SpringContextUtil.getBean(ICacheClient.class);
    }

    public static String decrypt(String deviceuuid, String password) throws BusinessException {
        ICacheClient cacheClient = getCacheClient();
        KeyPair keyPair = (KeyPair) cacheClient.get(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_RSA_PREFIX_KEY + deviceuuid);
        if (keyPair == null) {
            throw new BusinessException("加密异常！");
        }
        return decryptData(keyPair.getPrivate(), password);
    }

    private static String decryptData(PrivateKey privateKey, String encryptedData) {
        try {
            byte[] encryptedDataArray = hexStringToBytes(encryptedData);
            byte[] decryptedDataArray = decryptDataArray(privateKey, encryptedDataArray);
            StringBuilder decryptedDataBuffer = new StringBuilder();
            decryptedDataBuffer.append(new String(decryptedDataArray));
            String decryptedData = decryptedDataBuffer.reverse().toString();
            return decryptedData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    public static byte[] decryptDataArray(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", BOUNCY_CASTLE_PROVIDER);
            cipher.init(2, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);

            for(int j = 0; raw.length - j * blockSize > 0; ++j) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            }

            return bout.toByteArray();
        } catch (Exception var6) {
            logger.error("RSA解密出错：" + var6.getMessage(), var6);
            throw new Exception(var6.getMessage());
        }
    }
}
