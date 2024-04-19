package vendingMachine.tester;

import vendingMachine.VendingMachine;
import vendingMachine.inventory.Product;

public class Main {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();
        Product hersheys = new Product("Hersheys", 1, 5.0);

        //inserting 3 hersheys
        for(int i = 0;i<3;i++)
            vendingMachine.addProduct(hersheys);

        Product biskFarm = new Product("biskFarm", 2, 2.0);

        //inserting 3 biskFarm
        for(int i = 0;i<3;i++)
            vendingMachine.addProduct(biskFarm);

        vendingMachine.insertCoin(5.0);
        vendingMachine.insertCoin(3.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoin(5.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoin(7.0);
        vendingMachine.pressButton(2);
        
    }
}