package bugztracker.util;

import bugztracker.exception.EncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 17:47
 */
public final class MD5Encoder {

    private MD5Encoder() {
    }

    public static String encrypt(String passwordToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncodingException("Can't encrypt password to MD5", e);
        }
    }
}
