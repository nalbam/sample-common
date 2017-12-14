package com.nalbam.common.encoder;

import java.security.MessageDigest;

public class Sha256Encoder {

    public static String encode(final CharSequence value) {
        final StringBuilder hexString = new StringBuilder();
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(value.toString().getBytes("UTF-8"));
            for (final byte aHash : hash) {
                final String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
        return hexString.toString();
    }

}
