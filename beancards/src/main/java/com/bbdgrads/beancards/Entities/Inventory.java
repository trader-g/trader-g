package com.bbdgrads.beancards.Entities;

import java.util.ArrayList;

public class Inventory {
    public final int inventoryLength = 10;
    public ArrayList<BeanCard> inventory = new ArrayList<>();

    public Inventory(){
        // Could be generateRandomInventory() in service
        for (int i = 0; i < inventoryLength; i++) {
            inventory.add(new BeanCard());
        }
    }

    public Inventory(ArrayList<BeanCard> inventory){
        this.inventory = inventory;
    }

    public ArrayList<BeanCard> getInventory(){
        return inventory;
    }

    public void setInventory(ArrayList<BeanCard> newInventory){
        // Might have issues if arrays are not same size
        this.inventory = newInventory;
    }

    public void addCard(BeanCard newCard){
        inventory.add(newCard);
    }

    public void removeCard(BeanCard cardToRemove){
        inventory.remove(cardToRemove);
    }

    public void removeCardAtIndex(int index){
        inventory.remove(index);
    }

    @Override
    public String toString(){
        String temp = "";
        temp += "INVENTORY:\n";
        for (BeanCard beanCard : inventory) {
            temp += beanCard + "\n";
        }
        temp += "Total number of cards: " + inventory.size();

        return temp;
    }

    
}
