package com.example.light;

public class StringToLight {
	public StringToLight(String str) {
		char[] b = str.toCharArray();
		String c = null;
		char[] d = null;
		for (int i = 0; i < b.length; i++) {
			c = Integer.toBinaryString((int) b[i]);
			d = c.toCharArray();
			for (int j = 0; j < d.length; j++) {
				if (d[j] == '0') {
					try {
						Thread.currentThread();
						Thread.sleep(500);// ×è¶Ï0.5Ãë
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Thread.currentThread();
						Thread.sleep(500);// ×è¶Ï0.5Ãë
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

		}

	}

}
