package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;

/**
 * @author   Team 8
 * @version  1.0.0.0
 */
public class Recipe implements Serializable {
	/**
	 * Name of the recipe
	 */
    private String name;
	/**
	 * Price of the recipe
	 */
	private int price;
	/**
	 * Amount of coffee used in recipe
	 */
    private int amtCoffee;
	/**
	 * Amount of milk used in recipe
	 */
	private int amtMilk;
	/**
	 * Amount of sugar used in recipe
	 */
    private int amtSugar;
	/**
	 * Amount of chocolate used in recipe
	 */
	private int amtChocolate;

	/**
	 * Constructor of Recipe class
	 */
	public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }
    
    /**
	 * @return   Returns the amount of chocolate in recipe.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}
    /**
	 * @param amtChocolate   The amount of chocolate to set.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		} 
	}
    /**
	 * @return   Returns the amount of choclate in recipe.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}
    /**
	 * @param amtCoffee   The amount of coffee to set.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		} 
	}
    /**
	 * @return   Returns the amount of milk in recipe.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}
    /**
	 * @param amtMilk   The amount of milk to set.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		} 
	}
    /**
	 * @return   Returns the amount of sugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}
    /**
	 * @param amtSugar   The amount of sugar to set.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		} 
	}
    /**
	 * @return   Returns the name of recipe.
	 */
    public String getName() {
		return name;
	}
    /**
	 * @param name   The name of recipe to set.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}
    /**
	 * @return   Returns the price.
	 */
    public int getPrice() {
		return price;
	}
    /**
	 * @param price   The price to set.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		} 
	}

	/**
	 * Checks is recipes are equal comparing their names
	 * @param r recipe to compare with
	 * @return Boolean value defining if recipes have the same name
	 */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

	/**
	 * @return String representation of recipe (name of recipe)
	 */
	public String toString() {
    	return name;
    }

	/**
	 * Returns the recipe created from context
	 * @param context Context containing data about recipe
	 * @return Recipe created from context
	 * @throws ContextException Exception that may be thrown during getting data from context
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		r.name = (String)context.getValue("name");
		r.price = (int)context.getValue("price");
		r.amtCoffee = (int)context.getValue("amtCoffee");
		r.amtMilk = (int)context.getValue("amtMilk");
		r.amtSugar = (int)context.getValue("amtSugar");
		r.amtChocolate = (int)context.getValue("amtChocolate");
		return r;
	}

	/**
	 * Returns the ServiceContext containing information about recipe
	 * @param recipe recipe to be passed to service context
	 * @return ServiceContext with information about recipe
	 * @throws ContextException Exception that may be thrown during creation of ServiceContext
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("name", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		return cxt;
	}


}
