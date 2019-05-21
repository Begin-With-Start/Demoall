package demo.minifly.com.fuction_demo.utils;

import java.security.MessageDigest;

public class MD5 {
	public static String getMd5(String content) {
		StringBuffer buf = null;
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(content.getBytes()); 
			byte b[] = md.digest(); 
	
			int i; 
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if (i < 0) i+= 256; 
				if(i < 16) 
				buf.append("0"); 
				buf.append(Integer.toHexString(i));
			} 
			
			System.out.println("result: " + buf.toString());//32λ�ļ���
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		
		if (buf == null) {
			return null;
		}
		
		return buf.toString();
	} 
}
