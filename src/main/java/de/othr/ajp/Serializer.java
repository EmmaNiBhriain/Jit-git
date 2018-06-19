package de.othr.ajp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class used for writing objects to a file
 */
public class Serializer<T> {

    private FileNode readStagingArea;

    /**
     * Write a tree object to a file
     * @param filename the file name the object will be written to
     * @param object
     */
    public void treeWriter(String filename, T object){

        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(new File(filename)));
            out.writeObject(object);
            System.out.println("Object written successfully to file");
            out.close();
        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }

    }

    /**
     * This writer is used for writing the hashes to the files in the objects folder after a commit
     * This uses a separate writer to the tree due to encoding issues
     * It is not necessary to be able to read the staging.ser file in plain text but for testing purposes the committed files needed to be read in plaintext
     * Using the treeWriter method to write the committed files resulted in bad encoding of some symbols
     * @param filename
     * @param fileContents
     */
    public void fileWriter(String filename, String fileContents){

        try{

            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            Scanner scanner = new Scanner(fileContents);

            while(scanner.hasNext()) {
                bw.write(scanner.nextLine());
                bw.newLine();
                bw.flush();
            }
            bw.close();

        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }

    }

    /**
     * Read the existing tree object from the staging.ser file
     * @param fileToRead
     */
    public void treeReader(String fileToRead){
        try{
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(fileToRead)));
            readStagingArea = (FileNode) in.readObject();
            in.close();


        }
        catch(IOException e){
            System.out.println("Could not read from file " + e);
        }
        catch(ClassNotFoundException f){
            System.out.println("Could not find class " + f);
        }

    }

    /**
     * Read the contents of a file. This is used to get the contents of a file that is added to the staging area.
     * @param fileToRead
     * @return
     */
    public String readFile(String fileToRead){
        String fileContents = "";
        try{
            return new String(Files.readAllBytes(Paths.get(fileToRead)));

        }
        catch(IOException e){
            System.out.println("Could not read from file " + e);
        }

        return fileContents;
    }

    public FileNode getReadStagingArea() {
        return readStagingArea;
    }
}
