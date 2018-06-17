package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileNode implements Serializable{

    private static final long serialVersionUID = 2L;
    private String filename;
    private byte[] bytesOfFile;
    private Map<String, ArrayList<FileNode>> children = new HashMap<>();
    private boolean leaf = false;
    private String hashOfNode = ""; //the SHA-1 hash of the file that will be added to the 'staging area'

    public FileNode(String filename){

        this.filename = filename;
        ArrayList<FileNode> childNodes = new ArrayList<>(); //create a new empty arraylist of children
        children.put(filename, childNodes);
    }

    public boolean isLeaf(){
        if(leaf==true){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Get the byte array representation of the file
     * This can be used to get the hash of a file later if needed
     * @param file
     */
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


    /**
     * If the node does not already have children, create a new arraylist and add this given child to it
     * Otherwise, add the child to the list of children for this node
     * @param child
     */
    public void setChildren(FileNode child){
        if(this.getChildrenNodes().size()!=0){
            ArrayList<FileNode> childrenList = new ArrayList<>();
            childrenList.add(child);
            children.put(this.getFilename(), childrenList); //use the current name of the file and add to hashmap with this child as a key
        }

        else{
            this.getChildrenNodes().add(child); //TODO check that this value is update
        }

    }

    /**
     * Return all the nodes of the children
     * @return
     */
    public ArrayList<FileNode> getChildrenNodes() {
        return children.get(this.filename); //retrieve the list of children from the hashmap using this object's filename as a key
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

    /**
     * Create a toString for the FileNode type
     * @return
     */
    @Override
    public String toString(){
        return "Name of current file: " + this.filename + "\t Number of children: " + this.children.size();
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
