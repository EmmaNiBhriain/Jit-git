package de.othr.ajp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used for writing objects to a file
 */
public class Serializer<T> {


    private StagingArea readTree;
    private Map<String, ArrayList<FileNode>> childMap;
    private Map<String, FileNode> allNodes;



    private StagingArea readStagingArea;

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
            readStagingArea = (StagingArea) in.readObject();

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

    public StagingArea getReadStagingArea() {
        return readStagingArea;
    }



}
