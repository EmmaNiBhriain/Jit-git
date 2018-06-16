package de.othr.ajp;

import java.util.ArrayList;
import java.util.Map;

public class PrintTree {

    private TreeBuilder treeBuilder;
    private FileNode rootNode;

    public PrintTree(TreeBuilder tree){
        this.treeBuilder = tree;
        rootNode = treeBuilder.getRootNode();
        printLevels(rootNode);
    }

    public void printLevels(FileNode node){
        Map<String, ArrayList<FileNode>> childMapping = treeBuilder.getChildMap();
        ArrayList<FileNode> nodeChildren = childMapping.get(node.getFilename());


        if(nodeChildren.size() != 0){
            System.out.println(node.getFilename());
        }

        //TODO if the node children array has elements, get the corresponding nodes from the filenode map and call function on each node.
        else if(node.getChildrenNodes().size() == 1){ //if a node has exactly one child, print out this level's name and then call this function on the childNode
            System.out.println(node.getFilename());
            printLevels(node.getChildrenNodes().get(0));
        }
        else if(node.getChildrenNodes().size() >1){ //if a node has more than one child print out this node and then print out each of its children's nodes
            System.out.println(node.getFilename());
            for(FileNode file : node.getChildrenNodes()){
                printLevels(file);
            }
        }
    }


}
