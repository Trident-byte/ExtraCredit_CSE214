package com.example.myapplication;

import java.util.Scanner;

/**
 * The <code>FoodPyramid</code> hosts the main method
 * and the primary way for the user to interact with
 * the OrganismTree.
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/

public class FoodPyramid {
    /**
     * The main method runs the program and prompts the menu below,
     * and it allows various functions to be performed on your
     * constructed ternary tree.
     *
     * @param args
     *    Arguments given in the command line before running
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        System.out.print("What is the name of the apex predator?: ");
        String apexPredator = input.nextLine().strip();
        OrganismNode newNode = new OrganismNode(apexPredator);
        boolean[] diet = askOrganismType(input);
        newNode.setIsCarnivore(diet[1]);
        newNode.setIsHerbivore(diet[0]);
        System.out.println("\n\nConstructing food pyramid. . .");
        OrganismTree tree = new OrganismTree(newNode);
        printMenu();
        while(isRunning){
            String command = prompter(input, "Please enter a command: ", true);
            if(command.equals("pc")){
                addPlant(input, tree);
            }
            else if(command.equals("ac")){
                addAnimal(input, tree);
            }
            else if(command.equals("rc")){
                remove(input, tree);
            }
            else if(command.equals("p")){
                try{
                    System.out.println(tree.listPrey());
                } catch(IsPlantException e){
                    System.out.println("Cursor is pointing to a plant");
                }
            }
            else if(command.equals("c")){
                System.out.println(tree.listFoodChain());
            }
            else if(command.equals("f")){
                tree.printOrganismTree();
            }
            else if(command.equals("lp")){
                System.out.println(tree.listAllPlants());
            }
            else if(command.equals("r")){
                tree.cursorReset();
                System.out.println("Cursor successfully reset to root");
            }
            else if(command.equals("m")){
                move(input, tree);
            }
            else if(command.equals("q")){
                isRunning = false;
            }
        }
        input.close();
    }

    private static void printMenu(){
        System.out.println("Menu:\n");
        System.out.println("(PC) - Create New Plant Child");
        System.out.println("(AC) - Create New Animal Child");
        System.out.println("(RC) - Remove Child");
        System.out.println("(P) - Print Out Cursor’s Prey");
        System.out.println("(C) - Print Out Food Chain");
        System.out.println("(F) - Print Out Food Pyramid at Cursor");
        System.out.println("(LP) - List All Plants Supporting Cursor");
        System.out.println("(R) - Reset Cursor to Root");
        System.out.println("(M) - Move Cursor to Child");
        System.out.println("(Q) - Quit");
    }

    private static boolean[] askOrganismType(Scanner input){
        String type = prompter(input, "Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ", false);
        boolean[] diet = new boolean[2];
        if(type.equals("C")){
            diet[1] = true;
        }
        else if(type.equals("H")){
            diet[0] = true;
        }
        else if(type.equals("O")){
            diet[1] = true;
            diet[0] = true;
        }
        return diet;
    }

    private static void addPlant(Scanner input, OrganismTree tree){
        String plantName = prompter(input, "What is the name of the organism?: ", false);
        try{
            tree.addPlantChild(plantName);
            System.out.println(plantName + " has successfully been added as prey for the "
                    + tree.getCursor().getName() + "!");
        } catch(PositionNotAvailableException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addAnimal(Scanner input, OrganismTree tree){
        String animalName = prompter(input, "What is the name of the organism?: ", false);
        try{
            boolean[] diet = askOrganismType(input);
            tree.addAnimalChild(animalName, diet[0], diet[1]);
            System.out.println("A(n) " + animalName + " has successfully been added as prey for the "
                    + tree.getCursor().getName() + "!");
        } catch(PositionNotAvailableException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    private static void remove(Scanner input, OrganismTree tree){
        String preyName = prompter(input, "What is the name of the organism to be removed?: ", false);
        try{
            tree.removeChild(preyName);
            System.out.println("A(n) " + preyName + " has been successfully removed as prey for the "
                                + tree.getCursor().getName() + "!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void move(Scanner input, OrganismTree tree){
        String organism = prompter(input, "Move to?: ", false);
        try{
            tree.moveCursor(organism);
            System.out.println("Cursor successfully moved to " + organism + "!");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static String prompter(Scanner input, String prompt, boolean command){
        System.out.print(prompt);
        String answer = "";
        if(input.hasNextLine()){
            answer = input.nextLine();
        }
        if(command){
            if(answer.equals("")){
                answer = "q";
            }
            answer = answer.strip().toLowerCase();
        }
        return answer;
    }

}
