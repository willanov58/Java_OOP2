import java.util.List;
import java.util.Scanner;

public class NotebookView {
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayNotes(List<Note> notes) {
        for (Note note : notes) {
            System.out.println("Date: " + note.getDate() + ", Time: " + note.getTime() + ", Description: " + note.getDescription());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}