package de.othr.ajp;

import java.io.Serializable;

public class FileNode implements Serializable{

    private FileNode leftChild;
    private FileNode rightChild;

    private long hashValue;

    public void setChild(FileNode child){
        //check to see if I need to set left child or right child
    }

    public void setLeftChild(FileNode leftChildNode){
        this.leftChild = leftChildNode;
    }


    public void setRightChild(FileNode rightChildNode){
        this.rightChild = rightChildNode;
    }

}
