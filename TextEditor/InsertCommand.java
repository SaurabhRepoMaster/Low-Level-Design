// Concrete Command: InsertCommand
class InsertCommand implements Command {
    private StringBuilder text;
    private String insertedText;
    private int position;

    InsertCommand(StringBuilder text, String insertedText, int position) {
        this.text = text;
        this.insertedText = insertedText;
        this.position = position;
    }

    public void execute() {
        text.insert(position, insertedText);
    }

    public void undo() {
        text.delete(position, position + insertedText.length());
    }
}
