//package de.othr.ajp;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class PrintTree {
//
//    private TreeBuilder treeBuilder;
//    private FileNode rootNode;
//
//    public PrintTree(TreeBuilder tree){
//        this.treeBuilder = tree;
//        rootNode = treeBuilder.getRootNode();
//        printLevels(rootNode);
//    }
//
//    /**
//     * Pass a node to this method to print it
//     * If the node has children, call this function recursively until all nodes have been printed
//     * @param node
//     */
//    public void printLevels(FileNode node){
//        Map<String, ArrayList<FileNode>> childMapping = treeBuilder.getChildMap();
//        ArrayList<FileNode> nodeChildren = childMapping.get(node.getFilename()); //arraylist of all children for a given node
//
//
//        if(nodeChildren.size() == 0){
//            System.out.println(node.getFilename());
//        }
//
//        else if(nodeChildren.size() == 1){ //if a node has exactly one child, print out this level's name and then call this function on the childNode
//            System.out.println(node.getFilename()); //print the name of the current node
//
//            FileNode fileNode = nodeChildren.get(0); //get the node of the child
//            printLevels(fileNode);
//        }
//        else if(nodeChildren.size() >1){ //if a node has more than one child print out this node and then print out each of its children's nodes
//            System.out.println(node.getFilename());
//
//            for(FileNode file : node.getChildrenNodes()){
//
//                printLevels(file);
//            }
//        }
//    }
//
//
//}
