package com.realaction.calulatordemo;

import java.math.BigDecimal;

public class Utils {
	/**
	 * 两个字符类型的小数进行相加为a+b;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addBigDecimal(String a, String b) {
		double a1 = Double.parseDouble(a);
		double b1 = Double.parseDouble(b);
		BigDecimal a2 = BigDecimal.valueOf(a1);
		BigDecimal b2 = BigDecimal.valueOf(b1);
		BigDecimal c2 = a2.add(b2);
		String c1 = c2 + "";
		return c1;
	}

	/**
	 * 两个字符类型的小数进行相减为a-b;
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String reduceBigDecimal(String a, String b) {
		double a1 = Double.parseDouble(a);
		double b1 = Double.parseDouble(b);
		BigDecimal a2 = BigDecimal.valueOf(a1);
		BigDecimal b2 = BigDecimal.valueOf(b1);
		BigDecimal c2 = a2.subtract(b2);
		String c1 = c2 + "";
		return c1;
	}

	/**
	 * 两个字符类型的数相乘 a*b=c；
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String multipliedString(String a, String b) {
		double a1 = Double.parseDouble(a);
		double b1 = Double.parseDouble(b);
		BigDecimal a2 = BigDecimal.valueOf(a1);
		BigDecimal b2 = BigDecimal.valueOf(b1);
		BigDecimal c2 = a2.multiply(b2);
		String c1 = c2 + "";
		return c1;
	}

	/**
	 * 两个字符类型的数相除 a/b=c；
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divideString(String a, String b) {
		double a1 = Double.parseDouble(a);
		double b1 = Double.parseDouble(b);
		BigDecimal a2 = BigDecimal.valueOf(a1);
		BigDecimal b2 = BigDecimal.valueOf(b1);
		BigDecimal c2 = a2.divide(b2, a2.scale());
		String c1 = c2 + "";
		return c1;
	}

	public static String yunsuanjibie(String s) {
		String r = "";
		int p = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
					|| s.charAt(i) == '/') {
				p++;
			}
		}
		String k[] = new String[2 * p + 1];
		int k1 = 0;
		int first = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
					|| s.charAt(i) == '/') {
				k[k1] = s.substring(first, i);
				k1++;
				k[k1] = "" + s.charAt(i);
				k1++;
				first = i + 1;
			}
		}
		k[k1] = s.substring(first, s.length());
		int kp = p;
		while (kp > 0) {
			for (int i = 0; i < k.length; i++) {
				if (k[i].equals("*") || k[i].equals("/")) {
					int l;
					for (l = i - 1; l > -1; l--) {
						if (!(k[l].equals("p")))
							break;
					}
					int q;
					for (q = i + 1; q < k.length; q++) {
						if (!(k[l].equals("p")))
							break;
					}
					if (k[i].equals("*")) {
						k[i] = "" + multipliedString(k[l], k[q]);
						k[l] = "p";
						k[q] = "p";
						kp--;
					} else {
						k[i] = "" + divideString(k[l], k[q]);
						k[l] = "p";
						k[q] = "p";
						kp--;
					}
					break;
				}
			}
			for (int i = 0; i < 2 * p + 1; i++) {
				if (k[i].equals("+") || k[i].equals("-")) {
					int l;
					for (l = i - 1; l > -1; l--) {
						if (!(k[l].equals("p")))
							break;
					}
					int q;
					for (q = i + 1; q < k.length; q++) {
						if (!(k[q].equals("p")))
							break;
					}
					if (k[i].equals("+")) {
						k[i] = "" + addBigDecimal(k[l], k[q]);
						k[l] = "p";
						k[q] = "p";
						kp--;
					} else {
						k[i] = "" + reduceBigDecimal(k[l], k[q]);
						k[l] = "p";
						k[q] = "p";
						kp--;
					}
					break;
				}
			}
			for (int i = 0; i < k.length; i++) {
				if (!(k[i].equals("p"))) {
					r = k[i];
					break;
				}
			}
		}
		return r;
	}

	public static String sizeyunsuan(String s) {
		while (true) {
			int first = 0;
			int last = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(')
					first = i;
				if (s.charAt(i) == ')') {
					last = i;
					break;
				}
			}
			if (last == 0) {
				return yunsuanjibie(s);
			} else {
				String s1 = s.substring(0, first);
				String s2 = s.substring(first + 1, last);
				String s3 = s.substring(last + 1, s.length());
				s = s1 + yunsuanjibie(s2) + s3;
			}
		}
	}

}
