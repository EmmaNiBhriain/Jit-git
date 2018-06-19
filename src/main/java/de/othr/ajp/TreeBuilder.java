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
     * Split the filePath into strings for each file in the path and store in the String[] files
     * Traverse the tree until the child of the node is different to the child of the same file in the filepath
     * Add the new file to the current node
     *
     * @param filePath
     * @param toBeAdded
     */
    public TreeBuilder addToStagingArea(String filePath, File toBeAdded){

        String contents = "";//serializer.readFile(filePath);

        String[] files = filePath.split("/");  //split the filepath into Strings so that each file is its own string

        FileNode currentNode = rootNode;
        boolean match = false;

        int i=0;
        while(i<files.length){ //for each file, if its child matches the equivalent filename from the new file path, continue traversing through the tree and set the current node as the child
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


            /**
             * If the child of the current child is the same as the equivalent file in the filepath, add this file as a child of the current node
             */
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
     * For leaf nodes, i.e. files, get the hash of the contents read from the file and write the contents to a file with the hash of the contents as a name
     * For directory nodes, build the contents based on the hashes of its child(ren) and write to file in .jit/objects
     * In the case of the rootNode, also add the commit message
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
                contents = "Commit " + message + "\n"; //The root node (".") has the commit message added to the contents of the file
            }
            else //contruct the contents of the file representing the directory
                contents = "Directory \n";
            String type = "";
            for (FileNode child : fileNode.getChildren()) { //get the hash of each child

                buildHashes(child, message);

                //Contruct the contents of the file
                if (child.isLeaf()) {
                    type = "File";
                } else {
                    type = "Directory";
                }
                contents += type + " " + child.getHashOfNode() + " " + child.getFilename() + "\n";

            }

            //get the hash of the contents
            String hash = hashUtil.byteArrayToHexString(contents.getBytes());
            fileNode.setHashOfNode(hash);

            /**
             * Write contents to file with the hash as its name
             */
            try {
                String fileName = ".jit/objects/" + hash;
                Files.createFile(Paths.get(fileName));
                serializer.fileWriter(fileName, contents);
            } catch (IOException e) {
                System.out.println("Error writing to file " + e);


            }
        }


    }


    /**
     * Remove a file from the Merkle tree
     * Break the file path into strings each representing each file
     *
     * @param filename
     */
    public void remove(String filename){
        String[] files = filename.split("/");  //split the filepath into Strings so that each file is its own string

        boolean delete = false;
        //int i=0;
        FileNode currentNode = rootNode;
        while(!currentNode.getChildren().isEmpty()){ //while the current node has children
            for(FileNode child : currentNode.getChildren()){ //call this method on each child
                if(child.getFilename().equals(files[files.length-1])){

                    currentNode.getChildren().remove(child);
                    /**
                     * After removing the child, if the node has no more children, recursively remove the file until all nodes with no children are removed
                     */
                    if(currentNode.getChildren().isEmpty()){
                        if(!currentNode.equals(rootNode)){
                            String filePath = files[0];
                            for(int i=1; i<files.length-1; i++)
                                filePath += "/"+ files[i];
                            System.out.println(filePath);
                            remove(filePath);

                        }
                        break;

                    }


                }

                else{
                    for(FileNode childOfNode : currentNode.getChildren()){
                        currentNode = childOfNode;
                    }

                }
            }

        }


    }


    public FileNode getRootNode() {
        return rootNode;
    }


    public void setRootNode(FileNode rootNode) {
        this.rootNode = rootNode;
    }



}
