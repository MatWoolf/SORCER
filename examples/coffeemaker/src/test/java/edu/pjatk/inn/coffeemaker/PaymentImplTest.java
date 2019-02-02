package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import edu.pjatk.inn.coffeemaker.impl.PaymentImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.*;

import static org.junit.Assert.*;
import static sorcer.co.operator.*;
import static sorcer.co.operator.val;
import static sorcer.eo.operator.*;
import static sorcer.po.operator.ent;

public class PaymentImplTest {
    private final static Logger logger = LoggerFactory.getLogger(PaymentImpl.class);

    private CoffeeMaker coffeeMaker;
    private PaymentImpl qr;
    private Inventory inventory;
    private Recipe kaw1, kaw2, kaw3, kaw4;

    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();

        kaw1 = new Recipe();
        kaw1.setName("kaw1");
        kaw1.setPrice(250);
        kaw1.setAmtCoffee(3);
        kaw1.setAmtMilk(2);
        kaw1.setAmtSugar(2);
        kaw1.setAmtChocolate(3);

        kaw2 = new Recipe();
        kaw2.setName("kaw2");
        kaw2.setPrice(300);
        kaw2.setAmtCoffee(3);
        kaw2.setAmtMilk(3);
        kaw2.setAmtSugar(2);
        kaw2.setAmtChocolate(0);

        kaw3 = new Recipe();
        kaw3.setName("kaw3");
        kaw3.setPrice(280);
        kaw3.setAmtCoffee(0);
        kaw3.setAmtMilk(2);
        kaw3.setAmtSugar(2);
        kaw3.setAmtChocolate(3);

        kaw4 = new Recipe();
        kaw4.setName("kaw4");
        kaw4.setPrice(320);
        kaw4.setAmtCoffee(3);
        kaw4.setAmtMilk(1);
        kaw4.setAmtSugar(1);
        kaw4.setAmtChocolate(0);
    }




    @Test
    public void testPay() throws Exception {

        Context context = context(val("name", "espresso"), val("price", 50),
                val("amtCoffee", 6), val("amtMilk", 0),
                val("amtSugar", 1), val("amtChocolate", 0));

        Task coffee = task("coffee", sig("makeCoffee", CoffeeService.class), context(
                val("recipe/name", "espresso"),
                val("coffee/paid", 120),
                val("coffee/change"),
                val("recipe", context)));


        Task pay = task("pay", sig("pay", Payment.class), context(
                val("payment/cost", context.getValue("price"))),
                val("payment/balance", "120"));
        val("isMobile", true);

        Job drinkCoffee = job(pay, coffee,
                pipe(outPoint(pay, "payment/result"), inPoint(coffee, "coffee/isPaid")));

       assertEquals("payment/result", true);
    }
}