package evenOddPrinter;

import lombok.NonNull;

public class Printer implements Runnable {

    private final int step;
    private final State state;
    private int currentValue;
    private final PrinterType currentPrinterType;
    private final PrinterType nextPrinterType;
    private final int maxValue;

    public Printer(@NonNull final Integer startValue, @NonNull final int step, @NonNull final State state,
                   @NonNull final PrinterType currentPrinterType, @NonNull final PrinterType nextPrinterType,
                   @NonNull Integer maxValue){
        this.currentValue = startValue;
        this.step = step;
        this.state = state;
        this.currentPrinterType = currentPrinterType;
        this.nextPrinterType = nextPrinterType;
        this.maxValue = maxValue;
    }

    @Override
    public void run(){
        while (currentValue <= maxValue) {
            synchronized (state) {
                while (this.currentPrinterType != state.getNextToPrint()) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                    System.out.println(currentPrinterType.toString() + ": " + currentValue);
                    currentValue += step;
                    state.setNextToPrint(this.nextPrinterType);
                    state.notifyAll();
            }
        }
    }
}
