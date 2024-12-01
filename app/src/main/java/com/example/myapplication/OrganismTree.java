package com.example.myapplication;

import java.io.Serializable;

/**
 * The <code>OrganismTree</code> represents
 * the ternary tree of OrganismNode objects.
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/

public class OrganismTree implements Serializable {
    private OrganismNode root;
    private OrganismNode cursor;

    /**
     * Creates a new organism tree with an apexPredator
     *
     * @param apexPredator
     *    The node representing the apex predator
     *
     * <dt>Postcondition
     *    <dd>An OrganismTree object is made, with apexPredator
     *         representing the apex predator. Both root and cursor
     *         reference this node.</dd>
     */
    public OrganismTree(OrganismNode apexPredator){
        root = apexPredator;
        cursor = root;
    }

    /**
     * Moves the cursor back to the root of the tree.
     *
     * <dt>Postcondition
     *    <dd>cursor now references the root of the tree.</dd>
     */
    public void cursorReset(){
        cursor = root;
    }

    /**
     * Moves cursor to one of cursor’s children.
     *
     * <dt>Precondition
     *    <dd>name references a valid name of one of cursor’s children.</dd>
     *
     * <dt>Postcondition
     *    <dd> Either an exception is thrown, or cursor now points to the node whose
     *         name is referenced by name, and cursor now points to a child of the
     *         original cursor reference.</dd>
     *
     * @param name
     *     The name of the node to be moved to.
     * @throws IllegalArgumentException
     *    Thrown if name does not reference a direct, valid child of cursor.
     */
    public void moveCursor(String name) throws IllegalArgumentException{
        if(cursor.getLeft() != null && name.equals(cursor.getLeft().getName())){
            cursor = cursor.getLeft();
        }
        else if(cursor.getRight() != null && name.equals(cursor.getRight().getName())){
            cursor = cursor.getRight();
        }
        else if(cursor.getMiddle() != null && name.equals(cursor.getMiddle().getName())){
            cursor = cursor.getMiddle();
        }
        else{
            throw new IllegalArgumentException("ERROR: This prey does not exist for this predator");
        }
    }

    /**
     * Returns a String including the organism at cursor and all its possible prey
     *
     * <dt>Postcondition
     *    <dd>cursor has not moved.</dd>
     *
     * @return
     *    A String containing the name of the cursor, and all the cursor’s possible prey.
     * @throws IsPlantException
     *    Thrown if the cursor currently references a plant object.
     */
    public String listPrey() throws IsPlantException {
        if(cursor.getIsPlant()){
            throw new IsPlantException();
        }
        String answer = cursor.getName();
        if(cursor.getLeft() == null){
            return answer;
        }
        answer += " -> " + cursor.getLeft().getName();
        if(cursor.getMiddle() == null){
            return answer;
        }
        answer += ", " + cursor.getMiddle().getName();
        if(cursor.getRight() == null){
            return answer;
        }
        answer += ", " + cursor.getRight().getName();
        return answer;
    }

    /**
     * Creates a new animal node with a specific name and diet and
     * adds it as a child of the cursor node.
     *
     * <dt>Precondition
     *   <dd>name does not reference another direct child of the cursor</dd>
     *   <dd>The cursor has an available position for another child node.</dd>
     *
     * <dt>Precondition
     *    <dd>Either an exception is thrown, or a new animal node named name is
     *        added as a child of the cursor with a specific diet.</dd>
     *    <dd>The cursor does not move.</dd>
     *
     * @param name
     *    The name of the child node.
     * @param isHerbivore
     *    Walue depending on whether the animal consumes plants.
     * @param isCarnivore
     *    Value depending on whether the animal consumes other animals.
     * @throws IllegalArgumentException
     *     Thrown if name references an exact name with one of its would-be siblings.
     * @throws PositionNotAvailableException
     *     Thrown if there is no available child position for a new node to be added.
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException,
                                                                                             PositionNotAvailableException{
        OrganismNode animal = new OrganismNode(name);
        animal.setIsHerbivore(isHerbivore);
        animal.setIsCarnivore(isCarnivore);
        addNode(animal, name);
    }

    /**
     * Creates a new plant node with a specific name and adds it as a child of the cursor node.
     *
     * <dt>Preconditions
     *    <dd>name does not reference another direct child of the cursor</dd>
     *    <dd>The cursor has an available position for another child node.</dd>
     *
     * <dt>Postconditions
     *    <dd>Either an exception is thrown, or a new plant node named name is added as a child of the cursor.</dd>
     *    <dd>The cursor does not move.</dd>
     *
     * @param name
     *    The name of the child node.
     * @throws IllegalArgumentException
     *    Thrown if name references an exact name with one of its would-be siblings.
     * @throws PositionNotAvailableException
     *    Thrown if there is no available child position for a new node to be added.
     */
    public void addPlantChild(String name) throws IllegalArgumentException,
                                                  PositionNotAvailableException{
        OrganismNode plant = new OrganismNode(name);
        plant.setIsPlant(true);
        addNode(plant, name);
    }

    /**
     * Removes the child node of cursor with name, and properly shifts the deleted node’s other siblings if
     * necessary. If the deleted node has any descendants, those nodes are deleted as well.
     *
     * <dt>Precondition
     *    <dd>name references a direct child of the cursor</dd>
     *
     * <dt>Postcondition
     *    <dd>The child node of cursor with name name has been removed, and all the deleted node’s
     *        descendants have been removed from the tree as well.</dd>
     *    <dd>he deleted node’s siblings are shifted if necessary</dd>
     *    <dd>cursor has not moved</dd>
     *
     * @param name
     *    The name of the node to be deleted.
     * @throws IllegalArgumentException
     *    Thrown if name does not reference a direct child of the cursor.
     */
    public void removeChild(String name) throws IllegalArgumentException{
        OrganismNode left = cursor.getLeft();
        OrganismNode middle = cursor.getMiddle();
        OrganismNode right = cursor.getRight();
        if(left != null && left.getName().equals(name)){
            cursor.setLeft(middle);
            cursor.setMiddle(right);
        }
        else if(middle != null && middle.getName().equals(name)){
            cursor.setMiddle(right);
        }
        else if(right == null || !right.getName().equals(name)){
            throw new IllegalArgumentException("Animal does not eat " + name);
        }
        cursor.setRight(null);
    }

    /**
     * Prints out a layered, indented tree by performing a preorder traversal starting at cursor.
     *
     * <dt>Postconditions
     *    <dd>cursor has not moved.</dd>
     *    <dd>root has not moved.</dd>
     */
    public String printOrganismTree(){
        return printTreeHelper("", cursor);
    }

    /**
     *Returns a String containing the path of organisms that leads from
     *the apex predator (root) to the organism at cursor.
     *
     * <dt>Postcondition
     *   <dd>cursor has not moved</dd>
     *
     * @return
     *     A String containing the food chain from the apex predator to the cursor.
     */
    public String listFoodChain(){
        return foodChainHelper(root);
    }
    /**
     * Returns a list of all plants currently at cursor and beneath cursor in the food pyramid.
     *
     * <dt>Postconditions
     *    <dd>cursor has not moved.</dd>
     *    <dd> root has not moved.</dd>
     *
     * @return
     *    A String containing a list of all the plants in the food pyramid.
     */
    public String listAllPlants(){
        return listPlantHelper(root);
    }

    /**
     * Returns the current organism that the cursor is pointing to
     *
     * @return
     *    The organism the cursor is pointing to
     */
    public OrganismNode getCursor(){
        return cursor;
    }

    private void addNode(OrganismNode organism, String name) throws PositionNotAvailableException{
        OrganismNode left = cursor.getLeft();
        OrganismNode middle = cursor.getMiddle();
        if((left != null && left.getName().equals(name))|| (middle != null && middle.getName().equals(name))){
            throw new IllegalArgumentException("ERROR: This prey already exists for this predator");
        }
        try{
            cursor.addPrey(organism);
        } catch(IsPlantException | DietMismatchException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String printTreeHelper(String indent, OrganismNode curNode){
        String ans = "";
        if(curNode.getIsPlant()){
            ans += indent + "- " + curNode.getName();
        }
        else{
            String newIndent = indent + "    ";
            ans += indent + "|- " + curNode.getName();
            if(curNode.getLeft() != null){
                ans += printTreeHelper(newIndent, curNode.getLeft());
                if(curNode.getMiddle() != null){
                    ans += printTreeHelper(newIndent, curNode.getMiddle());
                }
                if(curNode.getRight() != null){
                    ans += printTreeHelper(newIndent, curNode.getRight());
                }
            }
        }
        return ans;
    }

    private String listPlantHelper(OrganismNode curNode){
        if(curNode == null){
            return "";
        }
        if(curNode.getIsPlant()){
            return curNode.getName() + ", ";
        }
        String leftPath = listPlantHelper(curNode.getLeft());
        String rightPath = listPlantHelper(curNode.getRight());
        String middlePath = listPlantHelper(curNode.getMiddle());
        String plantList = "";
        if(!leftPath.equals("")){
            plantList += leftPath;
        }
        if(!middlePath.equals("")){
            plantList += middlePath;
        }
        if(!rightPath.equals("")){
            plantList += rightPath;
        }
        return plantList.strip();
    }

    private String foodChainHelper(OrganismNode curNode){
        if(curNode == null){
            return "";
        }
        if(curNode.getName() == cursor.getName()){
            return curNode.getName();
        }
        String leftPath = foodChainHelper(curNode.getLeft());
        if(!leftPath.equals("")){
            return curNode.getName() + " -> " + leftPath;
        }
        String middlePath = foodChainHelper(curNode.getMiddle());
        if(!middlePath.equals("")){
            return curNode.getName() + " -> " + middlePath;
        }
        String rightPath = foodChainHelper(curNode.getRight());
        if(!rightPath.equals("")){
            return curNode.getName() + " -> " + rightPath;
        }
        return "";
    }
}
