package vendingMachine.inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {

    private Map<Integer,Product> aisleToProductmap;
    private Map<Integer,Integer> productIdToCountMap;
    Queue<Integer> availableAisles;

    public Inventory(final int aisleCount){
        availableAisles = new LinkedList<>();
        for(int i = 1;i<=aisleCount;i++)
            availableAisles.add(i);

        aisleToProductmap = new HashMap<>();
        productIdToCountMap = new HashMap<>();
    }


    public void addProduct(Product product) throws Exception {
        int productId = product.getId();
        int productCount = productIdToCountMap.getOrDefault(productId,0);
        if(productCount==0)
        {
            if(availableAisles.isEmpty())
                throw new Exception("Out of space to add new product");

            aisleToProductmap.put(availableAisles.poll(),product);
        }
        productIdToCountMap.put(productId,productCount+1);
    }

    public Product getProductAt(int aisleNumber) {
        return aisleToProductmap.get(aisleNumber);
    }

    public void deductProductCount(int aisleNumber) {
        int productId = aisleToProductmap.get(aisleNumber).getId();
        int updatedProductCount = productIdToCountMap.get(productId)-1;
        if(updatedProductCount==0)
        {
            productIdToCountMap.remove(productId);
            aisleToProductmap.remove(productId);
            availableAisles.add(aisleNumber);
        }
        else
            productIdToCountMap.put(productId,updatedProductCount);
    }

    public boolean checkIfProductAvailable(int productId) {
        int productCount = productIdToCountMap.get(productId);
        return productCount>0;
    }
}
