package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.sort;

public class BuildRanges {
    private List<Range> ranges;

    public BuildRanges(List<Integer> groups) {
        this.ranges = buildCategories(groups);
    }

    public List<Range> getRanges() {
        return this.ranges;
    }

    private List<Range> buildCategories(List<Integer> groups) {
        List<Integer> sortedGroups = new ArrayList<>(groups);
        sortedGroups.add(0, 0);
        sortedGroups.add(MAX_VALUE);
        sort(sortedGroups);

        return IntStream.range(0, sortedGroups.size() - 1)
                .mapToObj(index -> new Range(sortedGroups.get(index), sortedGroups.get(index + 1)))
                .toList();
    }
}
