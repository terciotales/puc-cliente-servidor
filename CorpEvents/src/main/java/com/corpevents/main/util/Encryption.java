package com.corpevents.main.util;

import java.security.MessageDigest;

public class Encryption {
    /**
     * Função para criptografar a senha do usuário em MD5
     *
     * @param password Senha para ser criptografada
     */
    public static String encryptPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder(32);

        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
