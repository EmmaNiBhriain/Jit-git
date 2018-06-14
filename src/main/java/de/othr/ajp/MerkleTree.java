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

        if(rootFilename == "."){
            String[] files = filePath.split("/");
            for(int i=0; i<files.length; i++) {
                System.out.println(files[i]);

            }
            rootNode = new FileNode(files[0], files[childIndex]);
            rootFilename = rootNode.getFilename();

            childIndex ++;

            FileNode currentNode = rootNode;

            while(childIndex < files.length){
                FileNode newNode = new FileNode(currentNode.getChildren().get(0), files[childIndex] ); //create a new node using the child of the previous node as the name and the child of the file as the child
                childIndex ++;
                System.out.println("Name : " + newNode.getFilename() + " Child "  + newNode.getChildren().get(0));
                currentNode = newNode;
            }

            FileNode newNode = new FileNode(currentNode.getChildren().get(0), "");
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







    /**
     * Add either a left and a right child node or a left and a right subtree to the Merkle tree object
     * Get the hash of the two children, concatenate them and use this to get the hash of the tree root
     * @param leftChild
     * @param rightChild
     */
   /* public void addChildren(T leftChild, T rightChild){
        String combinedHashOfChildren = " ";

        if((leftChild.getClass().equals(MerkleTree.class))&&(rightChild.getClass().equals(MerkleTree.class))){
            this.leftChildTree = (MerkleTree) leftChild;
            this.rightChildTree = (MerkleTree) rightChild;
            combinedHashOfChildren = leftChildTree.getHashOfNode().concat(rightChildTree.getHashOfNode());
        }
        else if((leftChild.getClass().equals(FileNode.class))&&(rightChild.getClass().equals(FileNode.class))){
            this.leftChildNode = (FileNode) leftChild;
            this.rightChildNode = (FileNode) rightChild;
            combinedHashOfChildren = leftChildNode.getHashOfNode().concat(rightChildNode.getHashOfNode());
        }

        if(!combinedHashOfChildren.equals(" "))
            hashOfNode = hashUtil.byteArrayToHexString(combinedHashOfChildren.getBytes()); //get the hash of the combined hash of the child tree nodes
        else
            System.out.println("combinedHashOfChildren has not been calculated");
    }*/

    /**
     * TODO check if this is needed
     * Method for adding a single node to the tree
     * @param
     */
    /*public void addChild(T singleChild){
        if((this.leftChildNode.equals(null) && this.rightChildNode.equals(null)) && (this.leftChildTree.equals(null)  && this.rightChildTree.equals(null))){
            //
        }
    }*/

    public void remove(T node){

    }


    public String getHashOfNode(){
        return this.hashOfNode;
    }

    public ArrayList<String> getStagingArea() {
        return stagingArea;
    }
}
