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
import static sorcer.co.operator.ent;
import static sorcer.co.operator.inEnt;
import static sorcer.eo.operator.*;

public class ScannerTest {
    private final static Logger logger = LoggerFactory.getLogger(ScannerTest.class);

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
    public void addCode() throws Exception {
        Exertion exertion = task(sig("scan", ScannerImpl.class),
                context("scanner", inEnt("code", "c2,m1,s1,ch0,p32,kaw5")));


        logger.info(context(exert(exertion)) + "\n" + get(context(exert(exertion)), ScannerImpl.RESULT_STATUS));
    }

    @Test
    public void chekIf() throws Exception {
        Exertion exertion = task(sig("scan", ScannerImpl.class),
                context("scanner", inEnt("code", "c1")));

        if (get(context(exert(exertion)), ScannerImpl.RESULT_STATUS).equals(true)) {
            logger.info("checkIf", "ok");
            logger.info("checkIf", get(context(exertion), ScannerImpl.RESULT_PATH));

        } else {
            logger.info("checkIf", "error");
            logger.info("checkIf", get(context(exertion), ScannerImpl.RESULT_PATH));
        }
    }

    @Test
    public void scanTest() throws Exception {
        String v1 = "c1,m1,s2,ch0,p123,kaw2";
        int v2 = 2000;

        Context context = context("scanner", inEnt("code", v1));
        Task scanner = task("hej", sig("scan", ScannerImpl.class), context);

        Task coffee = task("coffee", sig("makeCoffee", CoffeeMaker.class), context(
                ent("recipe/name", "kaw2"),
                ent("coffee/paid", 2000),
                ent("coffee/change"),
                ent("recipe")));
        if ((Boolean) exert(scanner).getValue("result/status")) {
            Job drinkCoffee = job(scanner, coffee,
                    pipe(outPoint(scanner, "result/value"), inPoint(coffee, "recipe")));
        }else{
            get(exert(scanner),ScannerImpl.RESULT_PATH);
        }
    }
}