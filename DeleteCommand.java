// Concrete Command: DeleteCommand
class DeleteCommand implements Command {
    private StringBuilder text;
    private String deletedText;
    private int position;

    DeleteCommand(StringBuilder text, int position, int length) {
        this.text = text;
        this.position = position;
        this.deletedText = text.substring(position, position + length);
    }

    public void execute() {
        text.delete(position, position + deletedText.length());
    }

    public void undo() {
        text.insert(position, deletedText);
    }
}
