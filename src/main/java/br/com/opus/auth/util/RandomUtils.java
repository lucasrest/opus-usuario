package br.com.opus.auth.util;

import java.security.SecureRandom;

public class RandomUtils {

    public static String gerarCodigoRecuperacao() {
        return String.format("%06d", new SecureRandom().nextInt(1000000));
    }
}
