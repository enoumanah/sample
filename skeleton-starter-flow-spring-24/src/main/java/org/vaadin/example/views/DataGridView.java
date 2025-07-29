package org.vaadin.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.vaadin.example.DataItem;
import org.vaadin.example.StringSimilarity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("data-grid")
public class DataGridView extends VerticalLayout {

    private List<DataItem> dataItems;
    private Grid<DataItem> grid;

    public DataGridView() {
        dataItems = loadDataFromCSV();
        TextField searchField = new TextField("Search by Name");
        Button searchButton = new Button("Search");
        grid = new Grid<>(DataItem.class);

        grid.setColumns("id", "schema", "name", "aliases", "birthDate", "country", "dataset",
                "firstSeen", "lastSeen", "lastChange");

        grid.addColumn(DataItem::getLevenshteinScore).setHeader("Levenshtein Score");
        grid.addColumn(DataItem::getPhoneticScore).setHeader("Phonetic Score");
        grid.addColumn(DataItem::getTotalScore).setHeader("Total Score");
        grid.addColumn(DataItem::getDatabase).setHeader("Database");

        searchButton.addClickListener(event -> {
            String searchTerm = searchField.getValue();
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                grid.setItems(dataItems);
                return;
            }

            List<DataItem> filteredItems = dataItems.stream()
                    .map(item -> {
                        int levDistance = StringSimilarity.levenshteinDistance(
                                item.getName().toLowerCase(), searchTerm.toLowerCase());

                        int phoneticScore = StringSimilarity.getSimilarityScore(item.getName(), searchTerm);

                        int maxLen = Math.max(item.getName().length(), searchTerm.length());
                        int levSimilarityScore = maxLen > 0
                                ? (int) Math.round((1.0 - ((double) levDistance / maxLen)) * 100)
                                : 100;

                        int totalScore = (int) Math.round((levSimilarityScore * 0.6) + (phoneticScore * 0.4));

                        item.setLevenshteinScore(levSimilarityScore);
                        item.setPhoneticScore(phoneticScore);
                        item.setTotalScore(totalScore);

                        return item;
                    })
                    .filter(item -> item.getName().toLowerCase().contains(searchTerm.toLowerCase())
                            || item.getTotalScore() >= 70)
                    .sorted((a, b) -> Integer.compare(b.getTotalScore(), a.getTotalScore()))
                    .collect(Collectors.toList());

            grid.setItems(filteredItems);

            if (filteredItems.isEmpty()) {
                Notification notification = Notification.show("No results found");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                Notification.show(filteredItems.size() + " results found");
            }

            // Debug output
            System.out.println("Searching for: " + searchTerm);
            filteredItems.forEach(item -> {
                String name = item.getName();
                StringSimilarity.logScoreBreakdown(name, searchTerm);
            });
        });

        add(searchField, searchButton, grid);
    }

    private List<DataItem> loadDataFromCSV() {
        List<DataItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/targets.simple.csv")))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Skip header line if present
                if (isFirstLine && line.toLowerCase().contains("id") && line.toLowerCase().contains("name")) {
                    isFirstLine = false;
                    continue;
                }
                isFirstLine = false;

                String[] fields = line.split(",");
                if (fields.length >= 15) {
                    DataItem item = new DataItem(
                            fields.length > 0 ? fields[0] : "",
                            fields.length > 1 ? fields[1] : "",
                            fields.length > 2 ? fields[2] : "",
                            fields.length > 3 ? fields[3] : "",
                            fields.length > 4 ? fields[4] : "",
                            fields.length > 5 ? fields[5] : "",
                            fields.length > 6 ? fields[6] : "",
                            fields.length > 7 ? fields[7] : "",
                            fields.length > 8 ? fields[8] : "",
                            fields.length > 9 ? fields[9] : "",
                            fields.length > 10 ? fields[10] : "",
                            fields.length > 11 ? fields[11] : "",
                            fields.length > 12 ? fields[12] : "",
                            fields.length > 13 ? fields[13] : "",
                            fields.length > 14 ? fields[14] : "",
                            0,
                            0,
                            0,
                            "PEP");
                    items.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Notification.show("Error loading CSV file: " + e.getMessage())
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

        return items;
    }
}