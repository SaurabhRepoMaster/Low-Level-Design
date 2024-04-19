package vendingMachine;

import lombok.Getter;
import lombok.Setter;
import vendingMachine.inventory.Inventory;
import vendingMachine.inventory.Product;
import vendingMachine.states.CoinInsertedState;
import vendingMachine.states.DispenseState;
import vendingMachine.states.NoCoinInsertedState;
import vendingMachine.states.State;

@Getter
@Setter
public class VendingMachine {

    private NoCoinInsertedState noCoinInsertedState;
    private CoinInsertedState coinInsertedState;
    private DispenseState dispenseState;
    private Inventory inventory;
    private State currentVendingMachineState;
    private double amount;
    private static final int AISLE_COUNT=2;

    public VendingMachine(){
        this.noCoinInsertedState=new NoCoinInsertedState(this);
        this.coinInsertedState = new CoinInsertedState(this);
        this.dispenseState = new DispenseState(this);
        currentVendingMachineState = noCoinInsertedState;
        amount = 0.0;
        inventory = new Inventory(AISLE_COUNT);
    }

    public boolean hasSufficientAmount(double expectedAmount) {
        return expectedAmount <= this.amount;
    }

    public void insertCoin(double amount)
    {
        this.currentVendingMachineState.insertCoin(amount);
        System.out.println(amount+ " coin is inserted");
    }

    public void pressButton(int aisleNumber)
    {
        this.currentVendingMachineState.pressButton(aisleNumber);
        this.currentVendingMachineState.dispense(aisleNumber);
    }

    public void addProduct(Product product)
    {
       try{
           this.getInventory().addProduct(product);
       }catch(Exception e)
       {
           e.printStackTrace();
       }
    }
}
