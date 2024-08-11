public class Main {
    public static void main(String[] args) {
        NotebookModel model = new NotebookModel();
        NotebookView view = new NotebookView();
        NotebookPresenter presenter = new NotebookPresenter(model, view);
        presenter.run();
    }
}