package de.othr.ajp;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MerkleTree<T> implements Serializable{



    protected ArrayList<String> stagingArea;

    private String hashOfNode;

    private ArrayList<String> children = new ArrayList<>();
    private String childDirectory;
    private String rootFilename = ".";
    private FileNode rootNode; //to keep track of the node at the top of the tree

    private HashUtil hashUtil;

    public MerkleTree(HashUtil hashUtil){

        this.hashUtil = hashUtil;
    }


    public void addToStagingArea(String filePath, File toBeAdded){
        int childIndex = 1;

        String[] files = filePath.split("/");
        for(int i=0; i<files.length; i++) {
            System.out.println(files[i]);

        }

        if(rootFilename == "."){

            rootNode = new FileNode(files[0]); //create the root node
            FileNode childNode = new FileNode(files[childIndex]); //create the first child of the root node
            rootNode.setChildren(childNode); //add the child of the root node to the root node object

            System.out.println("Name : " + rootNode.getFilename() + " Child "  + rootNode.getChildren().get(0).getFilename());


            rootFilename = rootNode.getFilename();

            childIndex ++;

            FileNode currentNode = rootNode;

            while(childIndex < files.length){
                FileNode newNode = new FileNode(currentNode.getChildren().get(0).getFilename()); //create a new node using the child of the previous node as the name and the child of the file as the child
                FileNode child = new FileNode(files[childIndex]);
                newNode.setChildren(child);
                childIndex ++;
                System.out.println("Name : " + newNode.getFilename() + " Child "  + newNode.getChildren().get(0).getFilename());
                currentNode = newNode;
            }

            FileNode newNode = new FileNode(currentNode.getChildren().get(0).getFilename());
            System.out.println("Name : " + newNode.getFilename() + " Leaf Node ");


        }


        else{ //if adding a filename to an existing tree: traverse the tree and at each level check if the child node is the same as the next filename in the array
            FileNode currentNode = rootNode;
            int i=1; //one element ahead of the current file, i.e the child
            while(currentNode.getChildren().get(0).equals(files[i])){ //while the child of the current node is equal to the equivalent level of the new file, traverse to the next level
                currentNode = rootNode.getChildren().get(0);
                i++;
            }
            //when the child of the current node is different to the next level in the file path, add the rest of the path to the tree
            while(i <files.length){
                FileNode newNode = new FileNode(currentNode.getChildren().get(0).getFilename()); //create a new node using the child of the previous node as the name and the child of the file as the child
                FileNode child = new FileNode(files[i]);
                newNode.setChildren(child);
                i ++;
                System.out.println("Name : " + newNode.getFilename() + " Child "  + newNode.getChildren().get(0));
                currentNode = newNode;
            }

            FileNode newNode = new FileNode(currentNode.getChildren().get(0).getFilename());
            System.out.println("Name : " + newNode.getFilename() + " Leaf Node ");


        }
    }

    /**
     * If a node is not a leaf node, get its child file
     * Continue until file at leaf node is reached
     * @param rootNode
     */
    public void buildTree(String rootNode){

    }


    /**
     * Get the hash of each file in the staging area ArrayList and create File Node objects
     * Get the concatenation of every hash, represented in a StringBuffer
     */
    public void buildTree(){
        StringBuffer hashes = new StringBuffer();
        for(String plainFile: stagingArea){
            //FileNode newNode = new FileNode(plainFile, hashUtil);
            //hashes.append(newNode.getHashOfNode()); //Create a string that is a concatenation of every hash
        }

        hashOfNode = hashes.toString();
        System.out.println(hashOfNode);
    }
    

    public void remove(T node){

    }


    public String getHashOfNode(){
        return this.hashOfNode;
    }

    public ArrayList<String> getStagingArea() {
        return stagingArea;
    }
}
