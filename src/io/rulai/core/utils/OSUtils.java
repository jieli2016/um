package io.rulai.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class OSUtils {

	private static String localhost = null;

	public static String getLocalHostname() {
		if (localhost == null) {
			synchronized (OSUtils.class) {
				boolean foundHostname = false;
				String osName = System.getProperty("os.name");
				if ("Linux".equals(osName) || "linux".equals(osName)) {
					File ff = new File("/proc/sys/kernel/hostname");
					if (ff.exists() && !ff.isDirectory()) {
						try {
							String k = IOUtils.readTextContents(ff
									.getAbsolutePath());
							localhost = k.trim();
							foundHostname = true;
						} catch (IOException e) {
						}
					}
				}
				if (!foundHostname) {
					try {
						InetAddress localAddr = java.net.InetAddress
								.getLocalHost();
						// set localhost to localAddr first, in case
						// getCanonicalHostFails
						localhost = localAddr + "";
						localhost = localAddr.getCanonicalHostName();
						if (localhost.contains(".")) {
							localhost = localhost.substring(0,
									localhost.indexOf('.'));
						}
					} catch (UnknownHostException e) {
						localhost = "";
					}
				}
				if (localhost != null && localhost.contains(".")) {
					localhost = localhost.substring(0, localhost.indexOf('.'));
				}
			}
		}
		return localhost;
	}

}
