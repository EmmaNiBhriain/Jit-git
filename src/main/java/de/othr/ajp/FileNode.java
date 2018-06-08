package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileNode implements Serializable{

    private File targetFile;
    private HashUtil hasher;
    private String hashOfNode; //the SHA-1 hash of the file that will be added to the 'staging area'



    /**
     * Create a FileNode object. Use the HashUtil parameter to get the SHA-1 hash of the File parameter.
     * @param fileToAdd
     * @param hasher
     */
    public FileNode(File fileToAdd, HashUtil hasher){
        this.targetFile = fileToAdd;
        this.hasher = hasher; //assign a local variable to refer to the HashUtil object that creates a hash of a file

        try{
            byte[] data = Files.readAllBytes(Paths.get(targetFile.getAbsolutePath()));
            hashOfNode = hasher.byteArrayToHexString(data);
        }
        catch (IOException e){
            System.out.println("Error accessing path of file"  + e);
        }

    }

    public String getHashOfNode() {
        return hashOfNode;
    }
}
