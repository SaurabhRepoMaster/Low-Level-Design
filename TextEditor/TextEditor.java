import java.util.Stack;

// Invoker
class TextEditor {
    private StringBuilder text = new StringBuilder();
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void insertText(String insertedText, int position) {
        InsertCommand command = new InsertCommand(text, insertedText, position);
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void deleteText(int position, int length) {
        DeleteCommand command = new DeleteCommand(text, position, length);
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    public String getText() {
        return text.toString();
    }
}
