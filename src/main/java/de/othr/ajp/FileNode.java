package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileNode implements Serializable{

    private static final long serialVersionUID = 2L;

    private String filename;

    private byte[] bytesOfFile;

    private ArrayList<FileNode> children = new ArrayList<>();
    private static boolean leaf = false;

    private File targetFile;
    private HashUtil hasher;
    private String hashOfNode; //the SHA-1 hash of the file that will be added to the 'staging area'

    public FileNode(String filename){
        this.filename = filename;
    }

    public static boolean isLeaf(){
        if(leaf){
            return true;
        }
        else{
            return false;
        }
    }


    public void setBytesOfFile(File file){
        Path path = Paths.get(file.getAbsolutePath());
        try{
            bytesOfFile = Files.readAllBytes(path);

        }
        catch(IOException e){
            System.out.println("Error reading bytes from file" + e);
        }

    }

    public byte[] getBytesOfFile(){
        return bytesOfFile;
    }


    public void setChildren(FileNode child){
        children.add(child);
    }

    public ArrayList<FileNode> getChildren() {
        return children;
    }

    public String getFilename() {
        return filename;
    }

    public String getHashOfNode() {
        return hashOfNode;
    }

    public void setHashOfNode(String hash){
        this.hashOfNode = hash;
    }
}
