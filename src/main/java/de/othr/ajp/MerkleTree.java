package de.othr.ajp;


import java.io.File;

public class MerkleTree<T> {

    private String hashOfNode;

    private MerkleTree leftChildTree; //left child subtree
    private MerkleTree rightChildTree; // right child subtree

    private FileNode leftChildNode; //left child node
    private FileNode rightChildNode; //right child node

    private HashUtil hashUtil;

    public MerkleTree(HashUtil hashUtil){
        this.hashUtil = hashUtil;
    }


    /**
     * Add either a left and a right child node or a left and a right subtree to the Merkle tree object
     * Get the hash of the two children, concatenate them and use this to get the hash of the tree root
     * @param leftChild
     * @param rightChild
     */
    public void addChildren(T leftChild, T rightChild){
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
    }

    /**
     * TODO check if this is needed
     * Method for adding a single node to the tree
     * @param singleChild
     */
    public void addChild(T singleChild){
        if((this.leftChildNode.equals(null) && this.rightChildNode.equals(null)) && (this.leftChildTree.equals(null)  && this.rightChildTree.equals(null))){
        }
    }


    public String getHashOfNode(){
        return this.hashOfNode;
    }
}
