package com.jacquinc.admin.application.utils;

/**
 * Created by xiezm on 17/3/30.
 */
public class PasswordUtil {

    public static final int INTERATIONS = 1024;

    private static final int SALT_SIZE = 8;

    public static final String ALGORITHM = "SHA-1";
    
    private PasswordUtil(){
    	//empty
    }

    public static class HashPassword {
        private String salt;
        private String password;

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static HashPassword encrypt(String plainText) {
        HashPassword hashPassword = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        hashPassword.salt = Encodes.encodeHex(salt);
        byte[] bytePassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
        hashPassword.password = Encodes.encodeHex(bytePassword);
        return hashPassword;
    }

    public static HashPassword encrypt(String plainText, String salt) {
        HashPassword hashPassword = new HashPassword();
        byte[] bytePassword = Digests.sha1(plainText.getBytes(), Encodes.decodeHex(salt), INTERATIONS);
        hashPassword.password = Encodes.encodeHex(bytePassword);
        return hashPassword;
    }
}
