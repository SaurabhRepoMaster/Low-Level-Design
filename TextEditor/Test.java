public class Test {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.insertText("Hello ", 0);
        editor.insertText("world", 6);
        System.out.println(editor.getText()); // Output: Hello world

        editor.deleteText(5, 1);
        System.out.println(editor.getText()); // Output: Helloworld

        editor.undo();
        System.out.println(editor.getText()); // Output: Hello world

        editor.redo();
        System.out.println(editor.getText()); // Output: Helloworld
    }
}
