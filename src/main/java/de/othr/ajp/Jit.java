package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Jit {
    public static TreeBuilder treeBuilder;
    public static boolean testing = false;

    /**
     * Constructor that takes an instance of treeBuilder object as a constructor and assigns the value to a treeBuilder variable
     * @param treeBuilder
     */
    public Jit(TreeBuilder treeBuilder){
        this.treeBuilder = treeBuilder;
    }

    /**
     * Store the files in the staging area to the objects subdirectory.
     * @param comment
     * @return
     */
    public void commit(String comment, FileNode rootNode){
        treeBuilder.setRootNode(rootNode);

        treeBuilder.buildHashes(rootNode, comment); //assign each node in the tree a hashValue, construct the content for the files and write them to the .jit/objects folder
    }

    /**
     * Initialise a repository for the project.
     * Create the .jit directory with two subdirectories objects and staging.
     * The staging subdirectory stores the data structure that holds the staging area.
     * The objects subdirectory stores objects after a commit.
     */
    public static void init(){

        try{
            Files.createDirectories(Paths.get( ".jit"));
            Files.createDirectories(Paths.get(".jit/objects"));
            Files.createDirectories(Paths.get(".jit/staging"));

        }
        catch (IOException e) {
            System.out.println("Error creating directory");
        }

    }

    /**
     * Check that a file exists and then call the function in treeBuilder to remove the file from the Merkle Tree
     */
    public void remove(String filename){
        File removeFile = new File(filename);
        if(removeFile.exists()){
            System.out.println("File exists and will be removed from jit");
            treeBuilder.remove(filename);

        }
        else{
            System.out.println("File does not exist and cannot be removed from jit");
        }

    }

    /**
     * If a file exists, add it to the Merkle Tree that representst the staging area to be included in the next commit.
     */
    public static void add(String filePath){

        String relativeFilePath =  filePath;
        System.out.println(relativeFilePath);
        File toBeAdded = new File(filePath);;


        System.out.println(toBeAdded.getName());
        if(toBeAdded.exists()){
            System.out.println("File exists and can be added to jit");

            treeBuilder = treeBuilder.addToStagingArea(filePath,toBeAdded); //Build a tree using the given filepath
            Serializer serializer = new Serializer();
            serializer.treeWriter(".jit/staging/staging.ser", treeBuilder.getRootNode());//write the stagingArea object to a file

        }
        else{
            System.out.println("File does not exist. Please check that the name has been typed correctly. Entered name " + toBeAdded.getPath());
        }



    }

    /**
     * Delete the contents of the workspace. Recreate the workspace based on the commit hash passed as a parameter here.
     * The content is retrieved from the object subdirectory of the .jit directory.
     * @param hash
     */
    public void checkout(String hash){

    }


    /**
     * The main method reads the arguments for the jit command and calls the appropriate method
     * If a Merkle tree has previously existed for this project, read the tree from the staging file, otherwise create a new tree
     *
     * @param args
     */
    public static void main(String[] args){

        HashUtil hashUtil = new HashUtil(); //create an instance of the HashUtil class
        treeBuilder = new TreeBuilder(hashUtil); //create a new TreeBuilder object
        File stagingFile = new File(".jit/staging/staging.ser"); //set the staging area file

        if(stagingFile.exists()){
            Serializer serializer = new Serializer();
            serializer.treeReader(".jit/staging/staging.ser");

            FileNode stagingArea = serializer.getReadStagingArea();

            treeBuilder.setRootNode(stagingArea);
            System.out.println("Existing file has been read successfully. Root is " + stagingArea.getFilename());


        }
        else{
            System.out.println("No previously existing files");
        }

        Jit jit = new Jit(treeBuilder);


        if(args.length>0){
            System.out.println(args.length);
            if(args[0].equals("init")){
                System.out.println("Initialising repository");
                init();
            }
            else if(args[0].equals("add")){
                System.out.println(args[1]); //print the name of the file to be added

                jit.add(args[1]);

            }
            else if(args[0].equals("remove")){
                System.out.println("remove " + args[1]);
                jit.remove(args[1]);

                //Write the new Merkle tree to the staging file (overwrites the existing tree)
                Serializer serializer = new Serializer();
                serializer.treeWriter(".jit/staging/staging.ser", treeBuilder.getRootNode());//write the stagingArea object to a file //../../../

            }
            else if(args[0].equals("commit")){
                System.out.println("commit " + args[1]);

                jit.commit(args[1], treeBuilder.getRootNode());

            }
            else if(args[0].equals("checkout")){
                System.out.println("checkout - not yet implemented");
            }

            else{
                System.out.println("Invalid command " + args[0]);
            }

        }

    }
}
