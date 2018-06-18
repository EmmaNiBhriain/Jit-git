package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static de.othr.ajp.FileType.DIRECTORY;
import static de.othr.ajp.FileType.FILE;

public class TreeBuilder<T> implements Serializable{
    //protected ArrayList<FileNode> stagingArea; //files that will be hashed
    //private String hashOfNode;

    private Map<String, ArrayList<FileNode>> childMap = new HashMap<>(); //store the name of the node and an arrayList of all its children
    private Map<String, FileNode> fileNodeMap = new HashMap<>(); //store the names of each node with the node object as a value
    private transient String rootFilename = ".";

    private transient FileNode rootNode; //to keep track of the node at the top of the tree

    private transient Map<FileNode, String> hashes = new HashMap<>();
    private transient HashUtil hashUtil;
    private transient FileNode currentNode;
    private transient boolean write = false;

    private transient Serializer serializer;
    private static final long serialVersionUID = 1L;
    public transient Stack<FileNode> hashedFiles = new Stack<>();

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








        /*if(fileNodeMap.containsKey(files[files.length-1])){ //TODO go through tree to check if file is already in
            System.out.println("File already exists in staging area");
        }
        else{
            write = true;
            if(rootFilename == "."){ //if there is no existing tree, build a new one
                buildNewTree(files, toBeAdded); //build a tree by creating FileNodes to represent each file
            }


            else if(rootFilename.equals(files[0]) ){ //if adding a filename to an existing tree: traverse the tree and at each level check if the child node is the same as the next filename in the array

                addToExistingTree(files, toBeAdded);

            }


            ArrayList<FileNode> children = currentNode.getChildrenNodes();
            FileNode leafNode = new FileNode(children.get(children.size()-1).getFilename()); //create a new leaf node with no children
            leafNode.setBytesOfFile(toBeAdded); //create the byte stream of the file that will be used to generate the SHA-1 hash
            leafNode.setFileType(FILE);
            leafNode.setContents(contents);
            System.out.println("Name : " + leafNode.getFilename() + " Leaf Node ");


            //set the hash of the leaf node
            leafNode.setHashOfNode(hashUtil.byteArrayToHexString(leafNode.getBytesOfFile()));
            System.out.println(leafNode.getHashOfNode());

            childMap.put(leafNode.getFilename(), leafNode.getChildrenNodes()); //Add the leaf node
            fileNodeMap.put(leafNode.getFilename(), leafNode); //add the leaf node to a hashmap for storing all nodes*/

            //hashes.put(leafNode, leafNode.getHashOfNode());

            //printTree(rootNode);

            //serializer.treeWriter("../../../.jit/staging/staging.ser", this);




        //}


        return this;
    }

    /**
     * Create the Merkle Tree using the String array that stores the name of each file/directory that needs to be included
     * The root node is created using the first element of the array
     * The second element is its child
     *
     * @param files
     */
//    public void buildNewTree(String[] files, File toBeAdded){
//
//        int childIndex = 1;
//
//        rootNode = new FileNode(files[0]); //create the root node
//        FileNode childNode = new FileNode(files[childIndex]); //create the first child of the root node
//        rootNode.setChildren(childNode); //add the child of the root node to the root node object
//
//        childMap.put(rootNode.getFilename(), rootNode.getChildrenNodes());//add the filename of the root to a hashmap with a list of its children as a value
//        fileNodeMap.put(rootNode.getFilename(), rootNode); //add the root node to a hashmap for storing all nodes
//
//
//        System.out.println("Name : " + rootNode.getFilename() + " Child "  + rootNode.getChildrenNodes().get(0).getFilename());
//        if(rootNode.getChildrenNodes().size()>0){
//            rootNode.setFileType(DIRECTORY);
//        }
//        else{
//            rootNode.setFileType(FILE);
//        }
//        rootFilename = rootNode.getFilename();
//        childIndex ++;
//
//        currentNode = rootNode;
//
//        while(childIndex < files.length){ //While there is still another file in the path, create a new Node for the current Node and add its child
//            FileNode newNode = new FileNode(currentNode.getChildrenNodes().get(0).getFilename()); //create a new node using the child of the previous node as the name and the child of the file as the child
//            FileNode child = new FileNode(files[childIndex]);
//            newNode.setChildren(child);
//            newNode.setFileType(DIRECTORY);
//            childIndex ++;
//            System.out.println("Name : " + newNode.getFilename() + " Child "  + newNode.getChildrenNodes().get(0).getFilename());
//            currentNode = newNode;
//
//            fileNodeMap.put(currentNode.getFilename(), currentNode); //add the current node to a hashmap for storing all nodes
//            childMap.put(newNode.getFilename(), newNode.getChildrenNodes()); //Add the new node to the hashmap with its children as a value
//        }
//
//
//
//    }

    /**
     * Add a file to an existing Merkle tree
     * @param files
     * @param toBeAdded
     */
//    public void addToExistingTree(String[]files, File toBeAdded){
//        currentNode = rootNode;
//        int i = 1; //one element ahead of the current file, i.e the child
//        while (files[i].equals(currentNode.getChildrenNodes().get(0).getFilename())) { //while the child of the current node is equal to the equivalent level of the new file, traverse to the next level
//
//            System.out.println("Current node: " + currentNode.getFilename() + "Current file " + files[i] + " Current child " + currentNode.getChildrenNodes().get(0).getFilename() + " files[+1] " + files[i+1]);
//
//            FileNode childNode = currentNode.getChildrenNodes().get(0);
//            currentNode = childMap.get(childNode).get(0);
//            i++;
//        }
//        //when the child of the current node is different to the next level in the file path, add the rest of the path to the tree
//        while (i < files.length) {
//            System.out.println("Current node " + currentNode.getFilename() + " Current folder of new file " + files[i]);
//            FileNode newNode = new FileNode(currentNode.getChildrenNodes().get(0).getFilename()); //create a new node using the child of the previous node as the name and the child of the file as the child
//            FileNode child = new FileNode(files[i]);
//            newNode.setChildren(child);
//            newNode.setFileType(DIRECTORY);
//
//            fileNodeMap.put(newNode.getFilename(), newNode); //add the current node to a hashmap for storing all nodes
//
//            childMap.put(newNode.getFilename(), newNode.getChildrenNodes());
//            i++;
//            System.out.println("Name : " + newNode.getFilename() + " Child " + newNode.getChildrenNodes().get(0).getFilename());
//            currentNode = newNode;
//        }
//
//
//    }

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
                serializer.treeWriter(fileName, contents);
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
                contents = "Directory \n ";
            String type = "";
            for (FileNode child : fileNode.getChildren()) {

                buildHashes(child, message);
                if (child.isLeaf()) {
                    type = "File";
                } else {
                    type = "Directory";
                }
                contents += type + " " + child.getHashOfNode() + " " + child.getFilename() + "\n";
                //buildHashes(fileNode);

            }

            String hash = hashUtil.byteArrayToHexString(contents.getBytes());
            fileNode.setHashOfNode(hash);

            try {
                String fileName = ".jit/objects/" + hash;
                Files.createFile(Paths.get(fileName));
                serializer.treeWriter(fileName, contents);
            } catch (IOException e) {
                System.out.println("Error writing to file " + e);


            }
        }

        //if((fileNode.getHashOfNode().equals(""))&&(fileNode.getChildrenNodes().size() ==1) &&(!fileNode.getChildrenNodes().get(0).isLeaf())&&(childMap.get(fileNode.getFilename()).get(0).getHashOfNode().equals(""))){
//
//            FileNode childNode =fileNode.getChildrenNodes().get(0);
//            FileNode childObj = childMap.get(fileNode.getFilename()).get(0);
//            buildHashes(childObj, message);
//            buildHashes(fileNode, message);
//        }
//        else if((fileNode.getChildrenNodes().size() < 2)&&(fileNode.getHashOfNode().equals(""))){
//            String contents = "";
//            String type;
//            String childType;
//
//
//            if(fileNode.getFileType().equals(DIRECTORY))
//                type = "Directory";
//            else
//                type = "File";
//
//            if(fileNode.getHashOfNode().equals("")){
//                if(!fileNode.isLeaf()) {
//
//
//                    if (fileNode.getChildrenNodes().get(0).getFileType().equals(FILE))
//                        childType = "File";
//                    else
//                        childType = "Directory";
//
//                    contents = type + "\n" + childType + " " + fileNode.getChildrenNodes().get(0).getHashOfNode() + " " + fileNode.getChildrenNodes().get(0).getFilename();
//
//                    String hash = hashUtil.byteArrayToHexString(contents.getBytes());
//                    fileNode.setHashOfNode(hash);
//
//                    hashedFiles.push(fileNode);
//
//                    if(childMap.get(fileNode.getFilename()).get(0).getHashOfNode().equals("")){
//                        FileNode childObj = childMap.get(fileNode.getFilename()).get(0);
//                        buildHashes(childObj, message);
//                    }
//                    else if(childMap.get(fileNode.getFilename()).get(0).getFileType().equals(FILE)){
//                        Serializer childSerializer = new Serializer();
//
//                        try{
//                            String fileName = ".jit/objects/" + fileNode.getChildrenNodes().get(0).getHashOfNode();  //TODO outside of test: add back in ../../../
//                            Files.createFile(Paths.get(fileName));
//                            childSerializer.treeWriter(fileName, fileNode.getChildrenNodes().get(0).getContents());
//                        }
//                        catch (IOException e){
//                            System.out.println("Could not create file" +e);
//                        }
//                    }
//                }
//            }
//            else{
//                contents = fileNode.getContents();
//            }
//
//
//            System.out.println(contents);
//
//            //write contents to file with hash as the name
//            Serializer serializer = new Serializer();
//
//            try{
//                String fileName = ".jit/objects/" + fileNode.getHashOfNode();  //TODO outside of test: add back in ../../../
//                Files.createFile(Paths.get(fileName));
//                serializer.treeWriter(fileName, contents);
//            }
//            catch (IOException e){
//                System.out.println("Could not create file" +e);
//            }
//
//        }
//        else if((!fileNode.getHashOfNode().equals(""))&&(fileNode.getChildrenNodes().size() ==1)){
//
//        }



        /*
        else if((fileNode.getHashOfNode().equals(""))&&(fileNode.getChildrenNodes().size() ==1) &&(!fileNode.getChildrenNodes().get(0).isLeaf())&&(!childMap.get(fileNode.getFilename()).get(0).getHashOfNode().equals(""))){

            String contents = "Directory \nDirectory " + fileNode.getChildrenNodes().get(0).getHashOfNode() + " " + fileNode.getChildrenNodes().get(0).getFilename();
            fileNode.setHashOfNode(hashUtil.byteArrayToHexString(contents.getBytes()));
            System.out.println(contents);

            //write contents to file with hash as the name
            Serializer serializer = new Serializer();
            try{
                String fileName = ".jit/objects/" + fileNode.getHashOfNode(); //TODO outside of test: add back in ../../../
                Files.createFile(Paths.get(fileName));
                serializer.treeWriter(fileName, contents);
            }
            catch (IOException e){
                System.out.println("Could not create file");
            }
        }*/

    }





    /**
     * Rebuild the tree using the HashMap
     * For each node, get the node whose name matches that of its childrens nodes
     */
    /*public void rebuild(){
        System.out.println(this.rootFilename);
        currentNode = rootNode;
        ArrayList<FileNode> childrenList = currentNode.getChildrenNodes();
        for(FileNode child : childrenList){

            //currentNode = childMap.get(child);
            System.out.println(currentNode.getFilename());
        }
    }*/

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

    /**
     * To print the entire tree, pass the rootnode as a parameter
     * //@param starterNode
//     */
//    public void printTree(FileNode starterNode){
//        currentNode = starterNode;
//        System.out.println(currentNode.getFilename());
//        if(currentNode.getChildrenNodes().size()!=0){
//            for(FileNode child : currentNode.getChildrenNodes()){
//                printTree(child);
//            }
//
//        }
//    }



    public Map<String, ArrayList<FileNode>> getChildMap() {
        return childMap;
    }

    public Map<String, FileNode> getFileNodeMap() {
        return fileNodeMap;
    }


    public FileNode getRootNode() {
        return rootNode;
    }

    public void setChildMap(Map<String, ArrayList<FileNode>> childMap) {
        this.childMap = childMap;
    }

    public void setFileNodeMap(Map<String, FileNode> fileNodeMap) {
        this.fileNodeMap = fileNodeMap;
    }

    public void setRootFilename(String rootFilename) {
        this.rootFilename = rootFilename;
    }

    public void setRootNode(FileNode rootNode) {
        this.rootNode = rootNode;
    }


//    public boolean writeFlag() {
//        return write;
//    }

}
