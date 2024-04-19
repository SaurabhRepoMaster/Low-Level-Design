package vendingMachine.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Product {

    private final String productName;
    private final int id;
    private final double amount;

}
