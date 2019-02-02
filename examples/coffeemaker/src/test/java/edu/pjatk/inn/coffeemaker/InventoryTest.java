package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;
import sorcer.service.Exertion;

import static org.junit.Assert.*;
import static sorcer.eo.operator.*;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class InventoryTest extends TestCase {

    private Inventory inventory;

    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        super.setUp();
    }

    public void testInventory() throws Exception {
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    public void testGetChocolate() {
        assertEquals(15, inventory.getChocolate());
    }

    public void testSetChocolate() {
        inventory.setChocolate(8);
        assertEquals(8, inventory.getChocolate());
    }

    public void testSetChocolateSecend() {
        inventory.setChocolate(-6);
        assertEquals(15, inventory.getChocolate());
    }

    public void testGetCoffee() {
        assertEquals(15, inventory.getCoffee());
    }

    public void testSetCoffee() {
        inventory.setCoffee(4);
        assertEquals(4, inventory.getCoffee());
    }

    public void testSetCoffeeSecend() {
        inventory.setCoffee(-6);
        assertEquals(15, inventory.getCoffee());
    }

    public void testGetMilk() {
        assertEquals(15, inventory.getMilk());
    }

    public void testSetMilk() {
        inventory.setMilk(4);
        assertEquals(4, inventory.getMilk());
    }

    public void testSetMilkSecend() {
        inventory.setMilk(-6);
        assertEquals(15, inventory.getMilk());
    }

    public void testGetSugar() {
        assertEquals(15, inventory.getSugar());
    }

    public void testSetSugar() {
        inventory.setSugar(4);
        assertEquals(1, inventory.getSugar());
    }

    public void testSetSugarSecend() {
        inventory.setSugar(-6);
        assertEquals(15, inventory.getSugar());
    }

    public void testMIngredients() {
        Recipe recipe = new Recipe();
        recipe.setAmtChocolate(1);
        assertFalse(inventory.enoughIngredients(recipe));
        recipe.setAmtChocolate(0);
        recipe.setAmtCoffee(2);
        assertFalse(inventory.enoughIngredients(recipe));
        recipe.setAmtChocolate(0);
        recipe.setAmtCoffee(0);
        recipe.setAmtMilk(3);
        assertFalse(inventory.enoughIngredients(recipe));
        recipe.setAmtMilk(0);
        recipe.setAmtSugar(4);
        assertFalse(inventory.enoughIngredients(recipe));
    }
}