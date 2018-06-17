import de.othr.ajp.HashUtil;
import de.othr.ajp.Jit;
import de.othr.ajp.Serializer;
import de.othr.ajp.TreeBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrintTreeTest {
    private HashUtil hashUtil = new HashUtil();

    @Test
    public void printLevelsTest(){

        TreeBuilder treeBuilder = new TreeBuilder(hashUtil);
        Jit.add("src/main/java/test1.txt");
        //treeBuilder.addToStagingArea("src/main/java/test1.txt", )

        assertEquals(4, treeBuilder.getFileNodeMap().size());

    }
}
