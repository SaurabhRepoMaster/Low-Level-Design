package vendingMachine.states;

import vendingMachine.VendingMachine;
import vendingMachine.inventory.Inventory;
import vendingMachine.inventory.Product;

public class DispenseState implements State {

    private VendingMachine vendingMachine;
    public DispenseState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        throw new IllegalStateException("Product getting dispensed!!");
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("Product getting dispensed!!");
    }

    @Override
    public void dispense(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);

        inventory.deductProductCount(aisleNumber);
        double change = vendingMachine.getAmount()- product.getAmount();
        vendingMachine.setAmount(0);
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getCoinInsertedState());
        System.out.println("Product with id: "+product.getId()+" is getting dispensed. Please collect change of "+change+"!");

    }
}
