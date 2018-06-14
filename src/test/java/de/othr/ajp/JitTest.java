package de.othr.ajp;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static de.othr.ajp.Jit.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class JitTest {


    @Rule
    public ExpectedException thrownException = ExpectedException.none();


    /**
     * Test the 'init' command
     * Test that the .jit file is created and the two subdirectories 'objects' and 'staging' are both created.
     * Check that an IOException is thrown when the program is unable to create the repositories.
     */
    @Test
    public void initTest(){

        assertFalse(Files.exists(Paths.get(".jit"))); //check that the .jit directory does not exist before the init command is called

        init(); //call the init command to create a repository
        assertTrue(Files.exists(Paths.get(".jit"))); //check that the .jit directory is created in the project folder

        assertTrue(Files.exists(Paths.get(".jit/objects"))); //check that the .jit directory contains the directory "objects"
        assertTrue(Files.exists(Paths.get(".jit/staging"))); //check that the .jit directory contains the directory "staging"


        //thrownException.expect(IOException.class);
        //thrownException.expectMessage("Unable to create directory");


    }

    //@After
    public void cleanUp(){
        try{
            Files.delete(Paths.get(".jit/objects"));
            Files.delete(Paths.get(".jit/staging"));
            Files.delete(Paths.get(".jit"));
        }
        catch (IOException e){
            System.out.println("Could not delete these directories" + e);
        }
    }
    /**
     * Use mockito to create a mock of the staging area as it had not been created yet at the time of writing the test.
     */
    @Test
    public void addTest(){
        if(Files.exists(Paths.get("othajpjit/.jit/staging"))){
            FileNode stagingMock = mock(FileNode.class);
            FileNode fileChildMock = mock(FileNode.class);

            File newFile = mock(File.class);
            File childFile = mock(File.class);
            add(newFile);
            add(childFile);

            //verify(stagingMock, times(1)).setChild();
        }
        else{
            //do something
        }


    }


    @Test
    public void commitTest(){


    }

}
