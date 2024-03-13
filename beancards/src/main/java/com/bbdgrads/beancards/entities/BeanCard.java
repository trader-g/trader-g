package com.bbdgrads.beancards.entities;

import java.lang.Math;

public class BeanCard {
    private String beanType;
    private String beanSize;

    public static final String[] beanTypes = { "Black bean", "Kidney bean", "Coffee bean",
            "Lima bean", "Pinto bean", "Edamame bean", "Mung beans", "Cannelini beans",
            "Garbanzo beans", "Green beans", "Fava beans", "Borloti beans"
    };
    public static final String[] beanSizes = { "Regular (common)", "Large (uncommon)", "Jumbo (rare)",
            "Massive (epic)", "Gigantic (mystic)" };

    public BeanCard() {
        int randType = (int) (Math.random() * beanTypes.length);
        // following could be in it's own service
        double randSize = Math.random() * 100;
        int randSizeChoice;

        if(randSize < 50){
            randSizeChoice = 0;
        }
        else if(randSize < 75){
            randSizeChoice = 1;
        }
        else if(randSize < 90){
            randSizeChoice = 2;
        }
        else if(randSize < 95){
            randSizeChoice = 3;
        }
        else randSizeChoice = 4; // 95 < randSize < 100
            

        this.beanType = beanTypes[randType];
        this.beanSize = beanSizes[randSizeChoice];
    }

    public BeanCard(String beanType, String beanSize) {
        this.beanType = beanType;
        this.beanSize = beanSize;
    }

    public String getBeanType(){
        return this.beanType;
    }
    
    public String getBeanSize(){
        return this.beanSize;
    }

    public void setBeanType(String newBeanType){
        this.beanType = newBeanType;
    }
    
    public void setBeanSize(String newBeanSize){
        this.beanSize = newBeanSize;
    }

    @Override
    public String toString(){
        return "Bean Card: " + beanType + " of size " + beanSize;
    }
}

