package software.ulpgc.kata3.apps.swing;

import software.ulpgc.kata3.MapHistogramDataFieldBuilder;
import software.ulpgc.kata3.Title;
import software.ulpgc.kata3.TsvTitleReader;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        File file = new File("./title.basics.tsv");
        List<Title> titles = new TsvTitleReader(file).read();
        titles.sort(Comparator.comparingInt(Title::startYear));
        Map<String, Integer> yearStats = getYearStats(titles);

        JFreeVerticalHistogramTitleStatisticDisplayer histogram = new JFreeVerticalHistogramTitleStatisticDisplayer(
                new MapHistogramDataFieldBuilder(yearStats),
                "Year Statistics Histogram"
        );
        HistogramMainFrame mainFrame = new HistogramMainFrame(histogram);
        mainFrame.display();
    }

    private static Map<String, Integer> getYearStats(List<Title> titles) {
        Map<String, Integer> stats = new LinkedHashMap<>();
        for (Title t: titles)
            stats.put(toString(t.startYear()), stats.getOrDefault(toString(t.startYear()), 0) + 1);

        return stats;
    }

    private static String toString(int i) {
        return String.valueOf(i);
    }
}