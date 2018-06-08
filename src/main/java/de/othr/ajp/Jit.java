package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jit {
    private MerkleTree merkleTree;

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
            Files.createDirectories(Paths.get( ".jit"));
            Files.createDirectories(Paths.get(".jit/objects"));
            Files.createDirectories(Paths.get(".jit/staging"));

        }
        catch (IOException e) {
            System.out.println("Error creating directory");
        }
            }

    /**
     * Remove a file fromm the staging area
     */
    public void remove(File filename){

    }

    /**
     * If a file exists, add it to the staging area to be included in the next commit.
     */
    public static void add(File filename){


    }

    /**
     * Delete the contents of the workspace. Recreate the workspace based on the commit hash passed as a parameter here.
     * The content is retrieved from the object subdirectory of the .jit directory.
     * @param hash
     */
    public void checkout(String hash){

    }
}
