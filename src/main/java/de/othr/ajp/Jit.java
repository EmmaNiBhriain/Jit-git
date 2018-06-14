package de.othr.ajp;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public static void add(String filePath){
        String relativeFilePath = "../../../" + filePath;
        System.out.println(relativeFilePath);
        File toBeAdded = new File(relativeFilePath);
        System.out.println(toBeAdded.getName());
        if(toBeAdded.exists()){
            System.out.println("File exists and can be added to jit");
            merkleTree.addToStagingArea(filePath, toBeAdded); //add the file to the Merkle tree
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
        HashUtil hashUtil = new HashUtil();
        MerkleTree tree = new MerkleTree(hashUtil);
        File stagingFile = new File("../../../.jit/staging/staging.ser");

        if(stagingFile.exists()){
            Serializer serializer = new Serializer();
            serializer.treeReader("../../../.jit/staging/staging.ser");
            tree = serializer.getReadTree();
            System.out.println("Existing file has been read successfully. Root is " + tree.getRootNode().getFilename());

        }
        else{
            System.out.println("No previously existing files");
        }

        Jit jit = new Jit(tree);

        for(int i=0; i<args.length; i++){
            System.out.println(args[i]);
        }

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
