package vendingMachine.states;

import vendingMachine.VendingMachine;

public class NoCoinInsertedState implements State{

    private VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine)
    {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(amount);
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalArgumentException("No Coin is Inserted!!!");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalArgumentException("No Coin is Inserted!!!");
    }
}
