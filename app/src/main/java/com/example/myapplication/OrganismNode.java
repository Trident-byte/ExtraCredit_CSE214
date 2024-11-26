package com.example.myapplication;

/**
 * The <code>OrganismNode</code> represents a single
 * organism in the ecosystem.
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/

public class OrganismNode {
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;

    /**
     * Creates an empty OrganismNode
     */
    public OrganismNode(){

    }

    /**
     * Creates an organism with a name
     *
     * @param name
     *    Name of the new organism
     */
    public OrganismNode(String name){
        this.name = name;
    }

    /**
     * Returns if the organism is a plant or not
     *
     * @return
     *    The boolean in the isPlant field
     */
    public boolean getIsPlant(){
        return isPlant;
    }

    /**
     * Returns if the organism is a herbivore or not
     *
     * @return
     *    The boolean in the isHerbivore field
     */
    public boolean getIsHerbivore(){
        return isHerbivore;
    }

    /**
     * Returns if the organism is a isCarnivore or not
     *
     * @return
     *    The boolean in the isPlant field
     */
    public boolean getIsCarnivore(){
        return isCarnivore;
    }

    /**
     * Returns the name of the OrganismNode
     *
     * @return
     *    The String in the name field
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the left OrganismNode child
     *
     * @return
     *    The OrganismNode in the left field
     */
    public OrganismNode getLeft() {
        return left;
    }

    /**
     * Returns the middle OrganismNode child
     *
     * @return
     *    The OrganismNode in the middle field
     */
    public OrganismNode getMiddle() {
        return middle;
    }

    /**
     * Returns the right OrganismNode child
     *
     * @return
     *    The OrganismNode in the right field
     */
    public OrganismNode getRight() {
        return right;
    }

    /**
     * Sets the value of the name field
     *
     * @param name
     *    New value of the name field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the value of the isCarnivore field
     *
     * @param carnivore
     *    New value of the isCarnivore field
     */
    public void setIsCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }

    /**
     * Sets the value of the isHerbivore field
     *
     * @param herbivore
     *    New value of the isHerbivore field
     */
    public void setIsHerbivore(boolean herbivore) {
        isHerbivore = herbivore;
    }

    /**
     * Sets the value of the isPlant field
     *
     * @param plant
     *    New value of the isPlant field
     */
    public void setIsPlant(boolean plant) {
        isPlant = plant;
    }

    /**
     * Sets the value of the left field
     *
     * @param left
     *    new value of the left field
     */
    public void setLeft(OrganismNode left) {
        this.left = left;
    }

    /**
     * Sets the value of the right field
     *
     * @param right
     *    new value of the right field
     */
    public void setRight(OrganismNode right) {
        this.right = right;
    }

    /**
     * Sets the value of the middle field
     *
     * @param middle
     *    new value of the middle field
     */
    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }

    /**
     * Adds preyNode as prey to this node.
     * Add child nodes in the following order:
     * left filled first, middle filled second, and right filled third.
     *
     * @param preyNode
     *    The OrganismNode to be added as prey of this organism.
     * @throws PositionNotAvailableException
     *    Thrown if there is no available child position for preyNode
     *    to be added (i.e. left, middle, and right are all being used)
     * @throws IsPlantException
     *    Thrown if this node is a plant node
     * @throws DietMismatchException
     *    Thrown if preyNode does not correctly correspond to the diet of this animal.
     */
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
        if(isPlant){
            throw new IsPlantException();
        }
        else if((isCarnivore && !preyNode.getIsPlant()) || (isHerbivore && preyNode.getIsPlant()) || (isCarnivore && isHerbivore)){
            if(left == null){
                left = preyNode;
            }
            else if(middle == null){
                middle = preyNode;
            }
            else if(right == null){
                right = preyNode;
            }
            else{
                throw new PositionNotAvailableException();
            }
        }
        else{
            System.out.println((isCarnivore && preyNode.getIsPlant())  + " + " + (isHerbivore && !preyNode.getIsPlant())  + " + " + (!isCarnivore || !isHerbivore));
            throw new DietMismatchException();
        }
    }
}
