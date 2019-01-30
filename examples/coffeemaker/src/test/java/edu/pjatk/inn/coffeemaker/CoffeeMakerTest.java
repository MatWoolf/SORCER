package edu.pjatk.inn.coffeemaker;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static sorcer.eo.operator.*;

/**
 * @author Mike Sobolewski
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	private Recipe espresso, mocha, macchiato, americano;

	@Before
	public void setUp() throws ContextException {
		coffeeMaker = new CoffeeMaker();
		inventory = coffeeMaker.checkInventory();

		espresso = new Recipe();
		espresso.setName("espresso");
		espresso.setPrice(50);
		espresso.setAmtCoffee(6);
		espresso.setAmtMilk(1);
		espresso.setAmtSugar(1);
		espresso.setAmtChocolate(0);

		mocha = new Recipe();
		mocha.setName("mocha");
		mocha.setPrice(100);
		mocha.setAmtCoffee(8);
		mocha.setAmtMilk(1);
		mocha.setAmtSugar(1);
		mocha.setAmtChocolate(2);

		macchiato = new Recipe();
		macchiato.setName("macchiato");
		macchiato.setPrice(40);
		macchiato.setAmtCoffee(7);
		macchiato.setAmtMilk(1);
		macchiato.setAmtSugar(2);
		macchiato.setAmtChocolate(0);

		americano = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);
	}

	@Test
	public void testAddRecipe() {
		assertTrue(coffeeMaker.addRecipe(espresso));
	}

	@Test
	public void testContextCofee() throws ContextException {
		assertTrue(espresso.getAmtCoffee() == 6);
	}

	@Test
	public void testContextMilk() throws ContextException {
		assertTrue(espresso.getAmtMilk() == 1);
	}

	@Test
	public void addRecepie() throws java.lang.Exception {
		coffeeMaker.addRecipe(mocha);
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addContextRecepie() throws java.lang.Exception {
		coffeeMaker.addRecipe(Recipe.getContext(mocha));
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addServiceRecepie() throws java.lang.Exception {
		Exertion cmt = task(sig("addRecipe", coffeeMaker),
						context(types(Recipe.class), args(espresso),
							result("recipe/added")));

		logger.info("isAdded: " + eval(cmt));
		assertEquals(coffeeMaker.getRecipeForName("espresso").getName(), "espresso");
	}

	@Test
	public void addRecipes() throws java.lang.Exception {
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		coffeeMaker.addRecipe(americano);

		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
		assertEquals(coffeeMaker.getRecipeForName("macchiato").getName(), "macchiato");
		assertEquals(coffeeMaker.getRecipeForName("americano").getName(), "americano");
	}

	@Test
	public void makeCoffee() throws java.lang.Exception {
		coffeeMaker.addRecipe(espresso);
		assertEquals(coffeeMaker.makeCoffee(espresso, 200), 150);
	}

	@Test
	public void testDeleteRecipe() throws java.lang.Exception{
		assertFalse(coffeeMaker.deleteRecipe(mocha));
		coffeeMaker.addRecipe(mocha);
		assertTrue(coffeeMaker.deleteRecipe(mocha));
		coffeeMaker.addRecipe(mocha);
	}

	@Test
	public void testEditRecipe()throws java.lang.Exception {
		assertFalse(coffeeMaker.editRecipe(espresso,mocha));
		//coffeeMaker.addRecipe(espresso);
		//assertTrue(coffeeMaker.editRecipe(espresso,mocha));
		assertEquals(coffeeMaker.getRecipeForName("mocha"),mocha);
	}

	@Test
	public void testAddInventory() throws java.lang.Exception{
		//An inventory is initialized with 15 units of each ingredient
		assertTrue(coffeeMaker.addInventory(0,0,0,0));
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);

		assertFalse(coffeeMaker.addInventory(-1,0,0,0));
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);

		assertFalse(coffeeMaker.addInventory(0,-1,0,0));
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);

		assertFalse(coffeeMaker.addInventory(0,0,-1,0));
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);

		assertFalse(coffeeMaker.addInventory(0,0,0,-1));
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);
	}

	@Test
	public void testCheckInventory() throws java.lang.Exception{
		assertEquals(coffeeMaker.checkInventory().getChocolate(), 15);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 15);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 15);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 15);
	}

	@Test
	public void testMakeCoffee() throws java.lang.Exception{
		coffeeMaker.addRecipe(espresso);
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
		assertEquals(coffeeMaker.makeCoffee(espresso, 45), 45);
		assertEquals(coffeeMaker.makeCoffee(americano, 60), 60);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
		assertEquals(coffeeMaker.makeCoffee(espresso, 100), 100);
	}

	@Test
	public void testGetRecipes() throws java.lang.Exception{
		Recipe r[] = coffeeMaker.getRecipes();

		coffeeMaker.addRecipe(espresso);
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		assertEquals(r[0], macchiato);
		assertEquals(r[1], mocha);
		assertEquals(r[2], espresso);
	}

	@Test
	public void testGetRecipeForName() throws java.lang.Exception{
		coffeeMaker.addRecipe(espresso);
		assertEquals(coffeeMaker.getRecipeForName("Coffee"), espresso);
		assertEquals(coffeeMaker.getRecipeForName("Mocha"), null);
	}

	@Test
	public void testGetRecipeFull() throws java.lang.Exception{
		boolean [] recipeFull = coffeeMaker.getRecipeFull();
		for(int i = 0; i < recipeFull.length; i++) {
			assertFalse(recipeFull[i]);
		}
	}

}

