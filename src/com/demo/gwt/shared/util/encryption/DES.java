package com.demo.gwt.shared.util.encryption;

import java.security.InvalidKeyException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * ���ܽ��ܹ�����
 * 
 * @author �Ƽ�
 * 
 */
public class DES {

	protected static Logger logger = Logger.getLogger(DES.class);

	/**
	 * ���� �����㷨,����DES-8,DESede-24,Blowfish
	 */
	private static final String ALGORITHM = "DESede";

	/**
	 * 24�ֽڵ���Կ(8�ı���)
	 */
	private static final byte[] KEY_BYTES = { 0x11, 0x22, 0x4F, 0x58,
			(byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
			(byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40,
			0x36, (byte) 0xE2 };

	/**
	 * ����
	 * 
	 * @param src
	 *            srcΪ�����ܵ����ݻ�������Դ��
	 * @return
	 */
	private static byte[] encryptMode(byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(KEY_BYTES, ALGORITHM);
			// ����
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (javax.crypto.NoSuchPaddingException e) {
			logger.error(e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param src
	 *            srcΪ���ܺ�Ļ�����
	 * @return
	 */
	private static byte[] decryptMode(byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(KEY_BYTES, ALGORITHM);
			// ����
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (javax.crypto.NoSuchPaddingException e) {
			logger.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage());
		} catch (BadPaddingException e) {
			logger.error(e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * ת����ʮ�������ַ���
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * �ַ���תʮ������
	 * 
	 * @param b
	 * @return
	 */
	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("���Ȳ���ż��");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * ����
	 * 
	 * @param date
	 *            Ҫ���ܵ��ַ���
	 * @return
	 */
	public final static String encrypt(String date) {
		try {
			return byte2hex(encryptMode(date.getBytes()));
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param data
	 *            Ҫ���ܵ��ַ���
	 * @return
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decryptMode(hex2byte(data.getBytes())));
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		String szSrc = "s164222786";
		System.out.println("����ǰ���ַ���:" + szSrc);
		String encoded = encrypt(szSrc);
		System.out.println("���ܺ���ַ���:" + encoded);
		String srcBytes = decrypt(encoded);
		System.out.println("���ܺ���ַ���:" + srcBytes);
	}

}
