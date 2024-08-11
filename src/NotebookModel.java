import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class NotebookModel implements Serializable {
    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Note> getNotesByDate(String date) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes) {
            if (note.getDate().equals(date)) {
                result.add(note);
            }
        }
        return result;
    }

    public List<Note> getNotesByWeek(String startDate, String endDate) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes) {
            if (note.getDate().compareTo(startDate) >= 0 && note.getDate().compareTo(endDate) <= 0) {
                result.add(note);
            }
        }
        return result;
    }

    public void sortNotesByDate() {
        notes.sort(Comparator.comparing(Note::getDate));
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static NotebookModel loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (NotebookModel) ois.readObject();
        }
    }
}

class Note implements Serializable {
    private String date;
    private String time;
    private String description;

    public Note(String date, String time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}