package de.othr.ajp;

import de.othr.ajp.HashUtil;
import de.othr.ajp.Jit;
import de.othr.ajp.Serializer;
import de.othr.ajp.TreeBuilder;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PrintTreeTest {
    private HashUtil hashUtil = new HashUtil();

    @Test
    public void printLevelsTest(){

        TreeBuilder treeBuilder = new TreeBuilder(hashUtil);
        Jit jit = new Jit(treeBuilder);
        jit.add("src/main/java/test1.txt");

        assertEquals(4, treeBuilder.getFileNodeMap().size()); //check that

        Map<String, FileNode> map  = treeBuilder.getFileNodeMap();


        FileNode firstChildName = treeBuilder.getRootNode().getChildrenNodes().get(0); //rootnode = src, firstChild = main
        FileNode firstChildObj = map.get(firstChildName.getFilename());


        FileNode secondChildName = firstChildObj.getChildrenNodes().get(0); //java directory
        FileNode secondChildObj = map.get(secondChildName.getFilename());

        FileNode thirdChildName = secondChildObj.getChildrenNodes().get(0); //test1.txt
        FileNode thirdChildObj = map.get(thirdChildName.getFilename());


        assertEquals(1, treeBuilder.getRootNode().getChildrenNodes().size()); //assert that the src node has one child
        assertEquals(1, firstChildObj.getChildrenNodes().size()); //assert that the 'main' node has one child
        assertEquals(1, secondChildObj.getChildrenNodes().size()); //assert that the java node has one child
        assertEquals(0, thirdChildObj.getChildrenNodes().size()); //assert that the test1.txt node has one child

        PrintTree printTree = new PrintTree(treeBuilder);

        //jit.add("src/main/java/de/othr/ajp/FileNode.java");
        //PrintTree printSecondTree = new PrintTree(treeBuilder);


    }
}
