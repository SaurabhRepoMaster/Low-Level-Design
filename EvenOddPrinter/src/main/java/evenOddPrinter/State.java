package evenOddPrinter;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class State {
    private PrinterType nextToPrint;

    public State(@NonNull final PrinterType printerType) {
        this.nextToPrint = printerType;
    }
}
