package de.othr.ajp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jit {

    /**
     * Store the files in the staging area to the objects subdirectory.
     * @param comnent
     * @return
     */
    public void commit(String comnent){

    }

    /**
     * Initialise a repository for the project.
     * Create the .jit directory with two subdirectories objects and staging.
     * The staging subdirectory stores the data structure that holds the staging area.
     * The objects subdirectory stores objects after a commit.
     */
    public static void init(Path pathForRepository){

        //TODO get the current path
        try{
            Files.createDirectories(Paths.get(pathForRepository.toString()));
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
