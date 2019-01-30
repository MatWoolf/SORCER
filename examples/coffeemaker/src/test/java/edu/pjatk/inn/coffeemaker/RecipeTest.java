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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sorcer.eo.operator.*;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class RecipeTest extends TestCase {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    private Recipe r1;
    private Recipe r2;

    protected void setUp() throws Exception {
        r1 = new Recipe();
        r2 = new Recipe();
        super.setUp();
    }
    public void testGetAmtChocolate() {
        assertEquals(0, r1.getAmtChocolate());
    }

    public void testSetAmtChocolate() {
        //Equivalence class - neg
        r1.setAmtChocolate(-5);
        assertEquals(0,r1.getAmtChocolate());
        //Equivalence class - pos
        r1.setAmtChocolate(5);
        assertEquals(5,r1.getAmtChocolate());
        //Boundary
        r1.setAmtChocolate(-1);
        assertEquals(5,r1.getAmtChocolate());
        r1.setAmtChocolate(0);
        assertEquals(0,r1.getAmtChocolate());
        r1.setAmtChocolate(1);
        assertEquals(1,r1.getAmtChocolate());
    }

    public void testGetAmtCoffee() {
        assertEquals(0, r1.getAmtCoffee());
    }

    public void testSetAmtCoffee() {
        r1.setAmtCoffee(-5);
        assertEquals(0,r1.getAmtCoffee());
        r1.setAmtCoffee(5);
        assertEquals(5,r1.getAmtCoffee());
        r1.setAmtCoffee(-1);
        assertEquals(5,r1.getAmtCoffee());
        r1.setAmtCoffee(0);
        assertEquals(0,r1.getAmtCoffee());
        r1.setAmtCoffee(1);
        assertEquals(1,r1.getAmtCoffee());
    }

    public void testGetAmtMilk() {
        assertEquals(0, r1.getAmtMilk());
    }

    public void testSetAmtMilk() {
        r1.setAmtMilk(-5);
        assertEquals(0,r1.getAmtMilk());
        r1.setAmtMilk(5);
        assertEquals(5,r1.getAmtMilk());
        r1.setAmtMilk(-1);
        assertEquals(5,r1.getAmtMilk());
        r1.setAmtMilk(0);
        assertEquals(0,r1.getAmtMilk());
        r1.setAmtMilk(1);
        assertEquals(1,r1.getAmtMilk());
    }

    public void testGetAmtSugar() {
        assertEquals(0, r1.getAmtSugar());
    }

    public void testSetAmtSugar() {
        r1.setAmtSugar(-5);
        assertEquals(0,r1.getAmtSugar());
        r1.setAmtSugar(5);
        assertEquals(5,r1.getAmtSugar());
        r1.setAmtSugar(-1);
        assertEquals(5,r1.getAmtSugar());
        r1.setAmtSugar(0);
        assertEquals(0,r1.getAmtSugar());
        r1.setAmtSugar(1);
        assertEquals(1,r1.getAmtSugar());
    }

    public void testGetName() {
        assertEquals("", r1.getName());
    }

    public void testSetName() {
        r1.setName("Coffee");
        assertEquals("Coffee", r1.getName());
    }

    public void testGetPrice() {
        assertEquals(0, r1.getPrice());
    }

    public void testSetPrice() {
        r1.setPrice(50);
        assertEquals(50, r1.getPrice());
    }

    /*
     * Class under test for boolean equals(Recipe)
     */
    public void testEqualsRecipe() {
        r1.setName(null);
        r2.setName("asdf");
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
        r1.setName("jkl");
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
        r1.setName("asdf");
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
    }

    /*
     * Class under test for String toString()
     */
    public void testToString() {
        r1.setName(null);
        assertEquals(r1.toString(), "");
        r1.setName("asdf");
        assertEquals(r1.toString(), "asdf");
    }

}