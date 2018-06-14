package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileNode implements Serializable{

    private String filename;


    private ArrayList<String> children = new ArrayList<>();
    private static boolean leaf;

    private File targetFile;
    private HashUtil hasher;
    private String hashOfNode; //the SHA-1 hash of the file that will be added to the 'staging area'

    public FileNode(String filename, String childname){
        this.filename = filename;
        this.children.add(childname);

        if(children.size()!=0)
            leaf = false;
        else
            leaf = true;
    }

    public static boolean isLeaf(){
        if(leaf){
            return true;
        }
        else{
            return false;
        }
    }



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



    public ArrayList<String> getChildren() {
        return children;
    }

    public String getFilename() {
        return filename;
    }

    public String getHashOfNode() {
        return hashOfNode;
    }
}
