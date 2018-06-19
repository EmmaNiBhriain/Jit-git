package de.othr.ajp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for getting the SHA-1 hash of a byte array
 */
public class HashUtil {

    public HashUtil(){

    }

    /**
     * Return a String representing the SHA-1 hash of the byte array
     * @param content
     * @return
     */
    public static String byteArrayToHexString(byte[] content) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1"); //Set the type of hash that will be used as a message digest
            byte[] digested = md.digest(content); //get the hash of the byte array

            StringBuilder s = new StringBuilder();
            for(byte b : digested) { //build the string of the hash value by getting the character value of each byte of the byte array digested
                int value = b & 0xFF; // & 0xFF to treat byte as "unsigned"
                s.append(Integer.toHexString(value & 0x0F));
                s.append(Integer.toHexString(value >>> 4));
            }
            return s.toString();
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println("Cannot find SHA-1 algorithm " + ex);
            return "Error";
        }
    }
}
