/**
 * Copyright (C) 2011 The Serval Project
 *
 * This file is part of Serval Software (http://www.servalproject.org)
 *
 * Serval Software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.servalproject.system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.servalproject.ServalBatPhoneApplication;

import android.util.Log;

public enum WifiMode {
	Adhoc(120, "Adhoc"), Client(90, "Client"), Ap(45, "Access Point"), Off(
			5 * 60, "Off"), Unsupported(0, "Unsupported"), Unknown(0, "Unknown");

	int sleepTime;
	String display;

	static {
		System.loadLibrary("iwstatus");
	}

	// The native iwstatus (iwconfig read-only command) here doesn't work,
	// even though the same code from the same library works from the command
	// line (this is because iwconfig requires root to READ the wifi mode).
	// public static native String iwstatus(String s);
	public static String iwstatus() {
		CoreTask coretask = ServalBatPhoneApplication.context.coretask;
		try {
			StringBuilder out = new StringBuilder();
			coretask.runCommandForOutput(coretask.hasRootPermission(), true,
					coretask.DATA_FILE_PATH + "/bin/iwconfig", out);
			return out.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static native String ifstatus(String s);

	WifiMode(int sleepTime, String display) {
		this.sleepTime = sleepTime;
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	private static WifiMode values[] = WifiMode.values();

	public static WifiMode nextMode(WifiMode m) {
		// return the next wifi mode
		if (m == null || m.ordinal() + 1 == values.length)
			return values[0];

		return values[m.ordinal() + 1];
	}

	public static WifiMode getWiFiMode() {
		// find out what mode the wifi interface is in by asking iwconfig
		if (ChipsetDetection.getDetection().getWifiChipset()
				.lacksWirelessExtensions()) {
			// We cannot use iwstatus, so see if our interface/IP is available.
			// IP address is probably the safest option.
			String ipaddr = ServalBatPhoneApplication.context.getIpAddress();
			if (ipaddr.contains("/")) {
				ipaddr = ipaddr.substring(0, ipaddr.indexOf('/'));
			}
			try {
				for (Enumeration<NetworkInterface> enumeration = NetworkInterface
						.getNetworkInterfaces(); enumeration.hasMoreElements();) {
					NetworkInterface networkInterface = enumeration
							.nextElement();
					for (Enumeration<InetAddress> enumIpAddress = networkInterface
							.getInetAddresses(); enumIpAddress
							.hasMoreElements();) {
						InetAddress iNetAddress = enumIpAddress.nextElement();
						if (!iNetAddress.isLoopbackAddress()) {
							// Check if this matches
							if (ipaddr.equals(iNetAddress.getHostAddress())) {
								// Bingo, so interface must not be down.
								return WifiMode.Unknown;
							}

						}
					}
				}
			} catch (Exception e) {
				Log.e("BatPhone/WifiMode", e.toString(), e);
			}
		} else {
			String iw = iwstatus();
			if (iw.contains("Mode:")) {
				// not sure why, but if not run as root, mode is incorrect
				// (this is because iwconfig needs to be run as root to
				// correctly
				// return the wifi mode -- this is probably a linux kernel/wifi
				// driver bug).
				if (ServalBatPhoneApplication.context.coretask
						.hasRootPermission()) {
					int b = iw.indexOf("Mode:") + 5;
					int e = iw.substring(b).indexOf(" ");
					String mode = iw.substring(b, b + e).toLowerCase();

					if (mode.contains("adhoc") || mode.contains("ad-hoc"))
						return WifiMode.Adhoc;
					if (mode.contains("client") || mode.contains("managed"))
						return WifiMode.Client;
					if (mode.contains("master"))
						return WifiMode.Ap;
				}

				// Found, but unrecognised = unknown
				return WifiMode.Unknown;
			}
		}

		// Not found, so off
		return WifiMode.Off;
	}
}
