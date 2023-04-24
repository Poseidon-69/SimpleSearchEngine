import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchGUI extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton localSearchButton;
    private JButton webSearchButton;
    private JList<String> resultListView;

    private SearchEngine searchEngine;

    public SearchGUI() {
        searchEngine = new SearchEngine();

        // Initialize JFrame components
        searchField = new JTextField();
        localSearchButton = new JButton("Local Search");
        webSearchButton = new JButton("Web Search");
        localSearchButton.addActionListener(this);
        webSearchButton.addActionListener(this);

        resultListView = new JList<>();

        // Add components to JFrame
        JPanel panel = new JPanel();
        panel.add(searchField);
        panel.add(localSearchButton);
        panel.add(webSearchButton);
        getContentPane().add(panel, "North");
        getContentPane().add(new JScrollPane(resultListView), "Center");

        // Configure JFrame
        setTitle("Search Engine");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String query = searchField.getText();
        List<String> results;

        if (e.getSource() == localSearchButton) {
            results = searchEngine.search(query);
        } else {
            results = searchEngine.searchWeb(query);
        }

        resultListView.setListData(results.toArray(new String[0]));
    }

    public static void main(String[] args) {
        new SearchGUI();
    }
}

