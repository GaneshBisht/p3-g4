package edu.uwosh.cs342.project3;

import java.io.*;
import java.math.*;
import java.util.*;
import java.security.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.spec.*;
public class RESEncryption {
	private String key;
	
	public RESEncryption (String key) {
		
		this.key = key;
		
	}
	
	
	
	public String decrypt(String encryptedString) {

	        

	        String password = key;

	        if (password.length() < 8 ) {
	            System.err.println("Password must be at least eight characters long");
	            System.exit(0);
	        }
	 
	        try {
	            BigInteger bi = new BigInteger(encryptedString);
	            InputStream bin = new ByteArrayInputStream(bi.toByteArray() );

	            // Create a key.
	            byte[] desKeyData = password.getBytes();
	            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
	            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	            SecretKey desKey = keyFactory.generateSecret(desKeySpec);

	            // Use Data Encryption Standard cipher
	            Cipher des = Cipher.getInstance("des");
	            des.init(Cipher.DECRYPT_MODE, desKey);

	            String decrypted_string = new String("");

	            byte[] input = new byte[64];
	            while (true) {
	                int bytesRead = bin.read(input);
	                if (bytesRead == -1) break;
	                byte[] output = des.update(input, 0, bytesRead);
	                if (output != null) decrypted_string = decrypted_string.concat(new String(output)) ;
	            }
	      
	            byte[] output = des.doFinal();
	            if (output != null) decrypted_string = decrypted_string.concat(new String(output));
	            bin.close();
	            return decrypted_string;
	        }
	        catch (InvalidKeySpecException e) {System.err.println(e);}
	        catch (InvalidKeyException e) {System.err.println(e);}
	        catch (NoSuchAlgorithmException e) {
	            System.err.println(e);
	            e.printStackTrace();
	        }
	        catch (NoSuchPaddingException e) {System.err.println(e);}
	        catch (BadPaddingException e) {System.err.println(e);}
	        catch (IllegalBlockSizeException e) {System.err.println(e);}
	        catch (IOException e) {System.err.println(e);}
			return "Error: Caught exception, could not complete decryption";
		
		
	}
	
	public String encrypt(String stringToEncrypt) {


	        String the_string = stringToEncrypt;
	        String password = key;

	        if (password.length() < 8 ) {
	            return "Password must be at least eight characters long";
	            
	        }
	    
	        try {
	            InputStream in = new ByteArrayInputStream(the_string.getBytes() );

	            // Create a key.
	            byte[] desKeyData = password.getBytes();
	            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
	            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	            SecretKey desKey = keyFactory.generateSecret(desKeySpec);

	            // Use Data Encryption Standard cipher
	            Cipher des = Cipher.getInstance("des");
	            des.init(Cipher.ENCRYPT_MODE, desKey);
	      
	            ArrayList<Byte> enc_bytes = new ArrayList<Byte>();
	      
	            byte[] input = new byte[64];
	            while (true) {
	                int bytesRead = in.read(input);
	                if (bytesRead == -1) break;
	                byte[] output = des.update(input, 0, bytesRead);
	                if (output != null) 
	                    for (int i = 0; i < output.length; i++) enc_bytes.add(new Byte(output[i]));  
	            }
	      
	            byte[] output = des.doFinal();
	            if (output != null) 
	                for (int i = 0; i < output.length; i++) enc_bytes.add(new Byte(output[i]));  
	            in.close();
	            Object enc_data_wrap [] =  enc_bytes.toArray();
	            byte enc_data [] = new byte [enc_data_wrap.length];
	            for (int i = 0; i < enc_data_wrap.length; i++) 
	                enc_data[i] = ((Byte)enc_data_wrap[i]).byteValue();
	            BigInteger bi = new BigInteger(enc_data);
	            return bi.toString();
	        }
	        catch (InvalidKeySpecException e) {System.err.println(e);}
	        catch (InvalidKeyException e) {System.err.println(e);}
	        catch (NoSuchAlgorithmException e) {
	            System.err.println(e);
	            e.printStackTrace();
	        }
	        catch (NoSuchPaddingException e) {System.err.println(e);}
	        catch (BadPaddingException e) {System.err.println(e);}
	        catch (IllegalBlockSizeException e) {System.err.println(e);}
	        catch (IOException e) {System.err.println(e);}
			return "Error: caught exception, unable to complete encryption";
		
		
	}

}
