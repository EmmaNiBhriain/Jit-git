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
    private ArrayList<FileNode> children = new ArrayList<>();
    private String hashOfNode = ""; //the SHA-1 hash of the file that will be added to the 'staging area'


    private String filepath;

    public FileNode(String filename){

        this.filename = filename;

    }

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
