package br.com.fesa.projectmidas.negocio;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SecurityHelper {

    private SecurityHelper() {

    }

    public static String getSHA512(String entrada) {
        String retorno = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] bytes = messageDigest.digest(entrada.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            retorno = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    public static String getSHA512(String entrada, String salt) {
        String retorno = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest(entrada.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            retorno = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
