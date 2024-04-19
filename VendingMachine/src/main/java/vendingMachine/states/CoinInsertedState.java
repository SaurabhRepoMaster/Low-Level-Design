package vendingMachine.states;

import vendingMachine.VendingMachine;
import vendingMachine.inventory.Inventory;
import vendingMachine.inventory.Product;

public class CoinInsertedState implements State{

    private VendingMachine vendingMachine;
    public CoinInsertedState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(vendingMachine.getAmount()+amount);
    }

    @Override
    public void pressButton(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);
        if(!vendingMachine.hasSufficientAmount(product.getAmount()))
            throw new IllegalStateException("Insufficient amount to buy this product");
        if(!inventory.checkIfProductAvailable(product.getId()))
            throw new IllegalStateException("Product Not Available!!");
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getDispenseState());
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("Product Not Choosen yet!!");
    }
}
