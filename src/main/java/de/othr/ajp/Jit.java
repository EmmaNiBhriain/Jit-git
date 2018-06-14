package de.othr.ajp;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jit {
    private static MerkleTree merkleTree;

    /**
     * Constructor that takes an instance of merkleTree object as a constructor and assigns the value to a merkleTree variable
     * @param merkleTree
     */
    public Jit(MerkleTree merkleTree){
        this.merkleTree = merkleTree;
    }

    /**
     * Store the files in the staging area to the objects subdirectory.
     * @param comment
     * @return
     */
    public void commit(String comment){

    }

    /**
     * Initialise a repository for the project.
     * Create the .jit directory with two subdirectories objects and staging.
     * The staging subdirectory stores the data structure that holds the staging area.
     * The objects subdirectory stores objects after a commit.
     */
    public static void init(){

        try{
            Files.createDirectories(Paths.get( "../../../.jit"));
            Files.createDirectories(Paths.get("../../../.jit/objects"));
            Files.createDirectories(Paths.get("../../../.jit/staging"));

        }
        catch (IOException e) {
            System.out.println("Error creating directory");
        }
            }

    /**
     * Remove a file fromm the staging area
     */
    public void remove(File filename){
        if(filename.exists()){
            System.out.println("File exists and will be removed from jit");
            merkleTree.remove(filename);

        }
        else{
            System.out.println("File does not exist and cannot be removed from jit");
        }

    }

    /**
     * If a file exists, add it to the staging area to be included in the next commit.
     */
    public static void add(File filename){
        if(filename.exists()){
            System.out.println("File exists and can be added to jit");
            //merkleTree.addChild(filename); //add the file to the merkle tree
        }
        else{
            System.out.println("File does not exist. Please check that the name has been typed correctly");
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
     * @param args
     */
    public static void main(String[] args){

        /*CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        Options options = new Options(); // commons cli object to declare possibly command line inputs

        options.addOption("init", false, "Initialise a repository by creating a .jit directory");
        options.addOption("add", true, "Add the file to the repository");
        options.addOption("remove", true, "Remove a file from the repository");
        options.addOption("commit", true, "Commit the changes to the repository");
        options.addOption("checkout", true, "Revert to an earlier commit");


        try {
            cmd = parser.parse(options, args);

            if(cmd.hasOption("init")){
                System.out.println("Initialising Repository");
                init();
            }
            else if(cmd.hasOption("add")){
                System.out.println("Adding file to repository");

                String fileToAdd = cmd.getOptionValue("add"); //get the name of the file

                if(fileToAdd == null) {
                    // print default date
                }
                else {
                    System.out.println(fileToAdd);
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }*/




        for(int i=0; i<args.length; i++){
            System.out.println(args[i]);
        }

        if(args.length>0){
            System.out.println(args.length);
            if(args[0].equals("init")){
                System.out.println("Initialising repository");
                //init();
            }
            else if(args[0].equals("add")){
                System.out.println(args[1]); //print the name of the file to be added
                //add(args[1])

            }
            else if(args[0].equals("remove")){
                System.out.println("remove " + args[1]);
            }
            else if(args[0].equals("commit")){
                System.out.println("commit " + args[1]);
            }
            else if(args[0].equals("checkout")){
                System.out.println("checkout");
            }

            else{
                System.out.println(args[0]);
            }

        }

    }
}
