package de.othr.ajp;

import java.io.*;

/**
 * Class used for writing objects to a file
 */
public class Serializer {


    private MerkleTree readTree;

    public void treeWriter(String filename, MerkleTree tree){

        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(new File(filename)));
            out.writeObject(tree);
        }
        catch (IOException e){
            System.out.println("Could not write to file" + e);
        }

    }

    public void treeReader(String fileToRead){
        try{
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(fileToRead)));
            final MerkleTree tree = (MerkleTree) in.readObject();
            readTree = tree;
        }
        catch(IOException e){
            System.out.println("Could not read from file " + e);
        }
        catch(ClassNotFoundException f){
            System.out.println("Could not find class " + f);
        }

    }


    public MerkleTree getReadTree() {
        return readTree;
    }

}
