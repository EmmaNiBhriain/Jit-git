package de.othr.ajp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StagingArea implements Serializable{

    private Map<String, ArrayList<FileNode>> childMap;
    private Map<String, FileNode> fileNodes;
    private static final long serialVersionUID = 4L;
    private FileNode root;


    public StagingArea(Map<String, ArrayList<FileNode>> mapOfChildren, Map<String, FileNode> mapOfNodes, FileNode rootNode){
        this.childMap = mapOfChildren;
        this.fileNodes = mapOfNodes;
        this.root = rootNode;

    }

    public Map<String, ArrayList<FileNode>> getChildMap() {
        return childMap;
    }

    public Map<String, FileNode> getFileNodes() {
        return fileNodes;
    }


    public FileNode getRoot() {
        return root;
    }

}
