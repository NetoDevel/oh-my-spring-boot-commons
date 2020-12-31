package com.netodevel.helpers;

import java.io.IOException;
import java.net.Socket;

/**
 * @author NetoDevel
 */
public class SocketKit {

    private SocketKit() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isPortAlreadyUsed(Integer port) {
        try (Socket ignored = new Socket("localhost", port)) {
            ignored.close();
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }
}
