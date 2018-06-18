package de.othr.ajp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used for writing objects to a file
 */
public class Serializer<T> {


    private Map<String, ArrayList<FileNode>> childMap;
    private Map<String, FileNode> allNodes;




    private FileNode readStagingArea;

    public void treeWriter(String filename, T object){

        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(new File(filename)));
            out.writeObject(object);
            System.out.println("Object written successfully to file");
        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }

    }

    public void treeReader(String fileToRead){
        try{
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(fileToRead)));
            readStagingArea = (FileNode) in.readObject();

            //childMap = tree.getChildMap();
            //allNodes = tree.getFileNodes();
            //readTree.rebuild();

        }
        catch(IOException e){
            System.out.println("Could not read from file " + e);
        }
        catch(ClassNotFoundException f){
            System.out.println("Could not find class " + f);
        }

    }

    public String readFile(String fileToRead){
        String fileContents = "";
        try{
            return new String(Files.readAllBytes(Paths.get(fileToRead)));
//            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
//                    new FileInputStream(fileToRead)));
//             fileContents = in.readObject().toString();
           //  System.out.println(fileContents);
        }
        catch(IOException e){
            System.out.println("Could not read from file " + e);
        }
//        catch (ClassNotFoundException e){
//            System.out.println("class not found" + e);
//        }
        return fileContents;
    }

    public FileNode getReadStagingArea() {
        return readStagingArea;
    }
}
