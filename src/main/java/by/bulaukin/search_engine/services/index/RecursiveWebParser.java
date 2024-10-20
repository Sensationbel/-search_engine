package by.bulaukin.search_engine.services.index;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
@Slf4j
public class RecursiveWebParser extends RecursiveAction {

    private Set<String> urlsList;
    private WebParser webParser;

    @Override
    protected void compute() {
        if (!urlsList.isEmpty()) {
            ForkJoinTask.invokeAll(createSubtasks());
        }
    }

    private Collection<RecursiveWebParser> createSubtasks() {
        List<RecursiveWebParser> taskList = new ArrayList<>();
        urlsList.forEach(recUrl -> {
            Set<String> pages = webParser.parsPage(recUrl);
            if (!pages.isEmpty()) {
                RecursiveWebParser task = new RecursiveWebParser(pages, webParser);
                taskList.add(task);
            }
        });
        return taskList;
    }
}
