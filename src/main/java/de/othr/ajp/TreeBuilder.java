package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TreeBuilder<T> implements Serializable{

    private transient String rootFilename = ".";

    private transient FileNode rootNode; //to keep track of the node at the top of the tree

    private transient HashUtil hashUtil;


    private transient Serializer serializer;
    private static final long serialVersionUID = 1L;

    /**
     * Called from the Jit.add method
     * Call the appropriate functions to build the tree
     * @param hashUtil
     */
    public TreeBuilder(HashUtil hashUtil){
        serializer = new Serializer();
        this.hashUtil = hashUtil;
        rootNode = new FileNode(".");
    }


    /**
     * If there is no file already in the tree: create a new tree and add children to each node
     * Add each Node to the HashMap containing the filename as a key and the FileNode as a value
     *
     * If adding a node to an existing tree, traverse the tree until the equivalent nodes have different children nodes
     * Add the new nodes here
     *
     * When the leaf node is reached, get the hash of this
     * @param filePath
     * @param toBeAdded
     */
    public TreeBuilder addToStagingArea(String filePath, File toBeAdded){

        String contents = "";//serializer.readFile(filePath);

        String[] files = filePath.split("/");  //split the filepath into Strings so that each file is its own string
        for(int i=0; i<files.length; i++) {
            System.out.println(files[i]);
        }

        FileNode currentNode = rootNode;
        boolean match = false;

        int i=0;
        while(i<files.length){
            match = false;
            for(FileNode child : currentNode.getChildren()){
                if(child.getFilename().equals(files[i])){
                    currentNode = child;
                    i++;
                    match = true;
                    break;
                }

            }
            if(match)
                continue;


            FileNode child = new FileNode(files[i]);
            currentNode.getChildren().add(child);
            child.setFilepath(filePath);
            currentNode = child;
            i++;


        }

        return this;
    }


    /**
     * Get the hash of each node in the tree
     * The nodes of files already had their hashes computed when added to the tree
     * If a node does not have a hash and hash one child that is not a leaf, call this function of its child
     * If a node has no hash and has one child that is a leaf, create the hash by adding the word Directory followed by File followed by the name of the child
     * TODO cater for if a node has multiple children
     * TODO change Directory + file and directory + directory to directory + type and remove the isLeaf check, just check if the node has a hash
     *
     * @param fileNode
     */
    public void buildHashes(FileNode fileNode, String message){

        if(fileNode.isLeaf()) {
            String contents = serializer.readFile(fileNode.getFilepath());
            String hash = hashUtil.byteArrayToHexString(contents.getBytes());
            fileNode.setHashOfNode(hash);

            try {
                String fileName = ".jit/objects/" + hash;
                Files.createFile(Paths.get(fileName));
                serializer.fileWriter(fileName, contents);
            } catch (IOException e) {
                System.out.println("Error writing to file " + e);


            }

        }

        else { //directory

            String contents = "";
            if(fileNode.equals(rootNode)){
                contents = "Commit " + message + "\n";
            }
            else
                contents = "Directory \n";
            String type = "";
            for (FileNode child : fileNode.getChildren()) {

                buildHashes(child, message);
                if (child.isLeaf()) {
                    type = "File";
                } else {
                    type = "Directory";
                }
                contents += type + " " + child.getHashOfNode() + " " + child.getFilename() + "\n";

            }

            String hash = hashUtil.byteArrayToHexString(contents.getBytes());
            fileNode.setHashOfNode(hash);

            try {
                String fileName = ".jit/objects/" + hash;
                Files.createFile(Paths.get(fileName));
                serializer.fileWriter(fileName, contents);
            } catch (IOException e) {
                System.out.println("Error writing to file " + e);


            }
        }


    }




    public void remove(T node){

    }


    public FileNode getRootNode() {
        return rootNode;
    }


    public void setRootNode(FileNode rootNode) {
        this.rootNode = rootNode;
    }



}
