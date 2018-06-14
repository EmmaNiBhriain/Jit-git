package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MerkleTree<T> implements Serializable{



    protected ArrayList<File> stagingArea; //files that will be hashed

    private String hashOfNode;

    private ArrayList<String> children = new ArrayList<>();
    private String rootFilename = ".";

    private FileNode rootNode; //to keep track of the node at the top of the tree

    private Map<FileNode, String> hashes = new HashMap<>();
    private transient HashUtil hashUtil;

    private transient Serializer serializer;
    private static final long serialVersionUID = 1L;

    public MerkleTree(HashUtil hashUtil){
        serializer = new Serializer();

        this.hashUtil = hashUtil;
    }


    public void addToStagingArea(String filePath, File toBeAdded){

        //stagingArea.add(toBeAdded);
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

            //The final file is a lead node
            FileNode leafNode = new FileNode(currentNode.getChildren().get(0).getFilename());
            leafNode.setBytesOfFile(toBeAdded);
            System.out.println("Name : " + leafNode.getFilename() + " Leaf Node ");

            //set the hash of the leaf node
            leafNode.setHashOfNode(hashUtil.byteArrayToHexString(leafNode.getBytesOfFile()));
            System.out.println(leafNode.getHashOfNode());
            hashes.put(leafNode, leafNode.getHashOfNode());


        }


        else { //if adding a filename to an existing tree: traverse the tree and at each level check if the child node is the same as the next filename in the array
            FileNode currentNode = rootNode;
            int i = 1; //one element ahead of the current file, i.e the child
            while (currentNode.getChildren().get(0).equals(files[i])) { //while the child of the current node is equal to the equivalent level of the new file, traverse to the next level
                currentNode = rootNode.getChildren().get(0);
                i++;
            }
            //when the child of the current node is different to the next level in the file path, add the rest of the path to the tree
            while (i < files.length) {
                FileNode newNode = new FileNode(currentNode.getChildren().get(0).getFilename()); //create a new node using the child of the previous node as the name and the child of the file as the child
                FileNode child = new FileNode(files[i]);
                newNode.setChildren(child);
                i++;
                System.out.println("Name : " + newNode.getFilename() + " Child " + newNode.getChildren().get(0).getFilename());
                currentNode = newNode;
            }

            FileNode leafNode = new FileNode(currentNode.getChildren().get(0).getFilename()); //create a new leaf node with no children
            leafNode.setBytesOfFile(toBeAdded); //create the byte stream of the file that will be used to generate the SHA-1 hash
            System.out.println("Name : " + leafNode.getFilename() + " Leaf Node ");

            //set the hash of the leaf node
            leafNode.setHashOfNode(hashUtil.byteArrayToHexString(leafNode.getBytesOfFile()));
            System.out.println(leafNode.getHashOfNode());

            hashes.put(leafNode, leafNode.getHashOfNode());

        }

        serializer.treeWriter("../../../.jit/staging/staging.ser", this);

    }


    /**
     * Get the combined hash of the node's children
     * If the children do not have hashes, create them
     * All leaf nodes have their hash computed when they are added to the tree
     * @param node
     * @throws IOException
     */
    public void hashTree(FileNode node) throws IOException{

    }




    public void remove(T node){

    }


    public String getHashOfNode(){
        return this.hashOfNode;
    }

    /*public ArrayList<String> getStagingArea() {
        return stagingArea;
    }*/


    public FileNode getRootNode() {
        return rootNode;
    }

}
