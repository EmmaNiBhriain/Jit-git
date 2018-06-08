package de.othr.ajp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserInterface {
    private Scanner input;

    public UserInterface(){
        this.menuChoice();
    }

    /**
     * call methods based on the choice selected by the user
     */
    public void menuChoice(){
        String command = consoleReader().toLowerCase().trim();
        while(!command.equals(null)){

            if(command.startsWith("jit add")){
                String fileToAdd = command.substring(8);
                File newFile = new File(fileToAdd);
                System.out.println(fileToAdd);
                //Jit.add(newFile);
            }
            else if(command.startsWith("jit remove")){
                String fileToRemove = command.substring(11);
                File newFile = new File(fileToRemove);
                System.out.println(newFile);
                //Jit.remove(newFile);
            }
            else if(command.startsWith("jit commit")){
                String message = command.substring(10);
                System.out.println(message);
                //Jit.commit(message);
            }
            else if(command.startsWith("jit checkout")){
                String hashCommit = command.substring(17);
                System.out.println(hashCommit);
                //Jit.checkout(hashCommit);
            }
            else if(command.equals("jit init")){
                //Jit.init();
            }
            else{
                System.out.println("Unrecognised command. Please check spelling and try again");
            }


            command=consoleReader();
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        UserInterface ui = new UserInterface();

    }


    /**
     * Display the options for the user
     * @return the number of the choice selected
     */
    public String consoleReader(){
        StringBuffer userInput = new StringBuffer("");
        input = new Scanner(System.in);
        System.out.println("Please enter a command");

        while(input.hasNext()){
            userInput = userInput.append(input.next()+" ");
        }


        String option = userInput.toString();
        return option;
    }

}
