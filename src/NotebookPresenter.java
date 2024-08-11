import java.io.IOException;
import java.util.List;

public class NotebookPresenter {
    private NotebookModel model;
    private NotebookView view;

    public NotebookPresenter(NotebookModel model, NotebookView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            String command = view.getUserInput("Enter command (add, list, save, load, sort, findDay, findWeek, exit): ");
            switch (command) {
                case "add":
                    addNote();
                    break;
                case "list":
                    listNotes();
                    break;
                case "save":
                    saveNotes();
                    break;
                case "load":
                    loadNotes();
                    break;
                case "sort":
                    sortNotes();
                    break;
                case "findDay":
                    findNotesByDay();
                    break;
                case "findWeek":
                    findNotesByWeek();
                    break;
                case "exit":
                    return;
                default:
                    view.displayMessage("Unknown command");
            }
        }
    }

    private void addNote() {
        String date = view.getUserInput("Enter date: ");
        String time = view.getUserInput("Enter time: ");
        String description = view.getUserInput("Enter description: ");
        model.addNote(new Note(date, time, description));
    }

    private void listNotes() {
        view.displayNotes(model.getNotes());
    }

    private void saveNotes() {
        String filename = view.getUserInput("Enter filename to save: ");
        try {
            model.saveToFile(filename);
            view.displayMessage("Notes saved successfully");
        } catch (IOException e) {
            view.displayMessage("Error saving notes: " + e.getMessage());
        }
    }

    private void loadNotes() {
        String filename = view.getUserInput("Enter filename to load: ");
        try {
            model = NotebookModel.loadFromFile(filename);
            view.displayMessage("Notes loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessage("Error loading notes: " + e.getMessage());
        }
    }

    private void sortNotes() {
        model.sortNotesByDate();
        view.displayMessage("Notes sorted by date");
    }

    private void findNotesByDay() {
        String date = view.getUserInput("Enter date to find: ");
        List<Note> notes = model.getNotesByDate(date);
        view.displayNotes(notes);
    }

    private void findNotesByWeek() {
        String startDate = view.getUserInput("Enter start date of the week: ");
        String endDate = view.getUserInput("Enter end date of the week: ");
        List<Note> notes = model.getNotesByWeek(startDate, endDate);
        view.displayNotes(notes);
    }
}