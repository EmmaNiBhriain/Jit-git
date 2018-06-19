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

/**
 * Object that will be store in the Merkle Tree.
 * Every file that is added to the tree will have a FileNode object created for it
 */

public class FileNode implements Serializable{

    private static final long serialVersionUID = 2L;
    private String filename; //name of the file, example: test1.txt
    private ArrayList<FileNode> children = new ArrayList<>(); //ArrayList of children of the node
    private String hashOfNode = ""; //the SHA-1 hash of the file
    private String filepath; //the full path to the file

    /**
     * Constructor for the FileNode
     * @param filename is used to assign the filename. Every other field is optional or static and can therefore be set separately
     */
    public FileNode(String filename){
        this.filename = filename;
    }

    /**
     * Check if a Node has any children, if not, it is a leaf
     * @return
     */
    public boolean isLeaf(){
        if(children.isEmpty()){
            return true;
        }
        else
            return false;
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


    public ArrayList<FileNode> getChildren() {
        return children;
    }
    public String getFilepath() {
        return filepath;
    }


    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
