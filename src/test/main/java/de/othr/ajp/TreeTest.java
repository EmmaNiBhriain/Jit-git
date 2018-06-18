//package de.othr.ajp;
//
//import org.junit.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import static de.othr.ajp.FileType.DIRECTORY;
//import static de.othr.ajp.FileType.FILE;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//
//public class TreeTest {
//
//    /**
//     * Add two files to a TreeBuilder and check that the hash of the root is a hash of a concatenation of the two hashed children nodes
//     */
//    //@Test
//    public void addTest(){
//        HashUtil hashUtil = mock(HashUtil.class);
//        File testFile1 = new File("new.txt"); //create two files to be hashed
//        File testFile2 = new File("newer.txt");
//
//        //FileNode node1 = new FileNode(testFile1, hashUtil); //create two file nodes with the hash of the created files
//        //FileNode node2 = new FileNode(testFile2, hashUtil);
//
//        TreeBuilder tree = new TreeBuilder(hashUtil);
//
//       // tree.addChildren(node1, node2);
//        //String hashOfTree = tree.getHashOfNode();
//
////        String expectedCombinationHash = node1.getHashOfNode().concat(node2.getHashOfNode());
//  //      String expectedHashOfTree = hashUtil.byteArrayToHexString(expectedCombinationHash.getBytes());
//
//    //    assertEquals(expectedHashOfTree, hashOfTree);
//
//    }
//
//    @Test
//    public void addToStagingAreaTest(){
//        HashUtil hashUtil = new HashUtil();
//        File testFile1 = new File("new.txt"); //create two files to be hashed
//        File testFile2 = new File("newer.txt");
//
//        TreeBuilder tree = new TreeBuilder(hashUtil);
//
//        //tree.addToStagingArea(testFile1);
//        //tree.addToStagingArea(testFile2);
//      //  assertEquals(2, tree.getStagingArea().size());
//    }
//
//    @Test
//    public void buildHashesTest(){
//        HashUtil hashUtil = new HashUtil();
//
//        TreeBuilder treeBuilder = new TreeBuilder(hashUtil);
//        FileNode root = new FileNode("new1");
//        FileNode child1 = new FileNode("new2");
//        FileNode child2 = new FileNode("new3");
//
//        child2.setHashOfNode("12345");
//        child2.setLeaf(true);
//        child2.setFileType(FILE);
//        child2.setContents("Contents of a test file");
//
//        child1.setFileType(DIRECTORY);
//
//        root.setFileType(DIRECTORY);
//
//        root.setChildren(child1);
//        child1.setChildren(child2);
//
//
//
//
//        treeBuilder.setRootNode(root);
//        treeBuilder.getChildMap().put(root.getFilename(), root.getChildrenNodes());
//        treeBuilder.getChildMap().put(child1.getFilename(), child1.getChildrenNodes());
//
//        treeBuilder.buildHashes(root, "Testing");
//
//        FileNode topFile = (FileNode) treeBuilder.hashedFiles.pop();
//        String content = "Commit " + "testing commit " + "\n" + topFile.getFileType() +  " " + topFile.getHashOfNode() + " " + topFile.getFilename();
//        System.out.println(content);
//        Serializer committer = new Serializer();
//
//        String hash = hashUtil.byteArrayToHexString(content.getBytes());
//        String fileName = ".jit/objects/" + hash;  //TODO outside of test: add back in ../../../
//        try{
//            Files.createFile(Paths.get(fileName));
//            committer.treeWriter(fileName, content);
//        }
//        catch (IOException e){
//            System.out.println("Error writing commit file");
//        }
//
//    }
//
//    @Test
//    public void removeTest(){
//
//    }
//}
