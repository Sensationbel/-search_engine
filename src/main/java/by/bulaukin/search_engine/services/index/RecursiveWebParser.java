package by.bulaukin.search_engine.services.index;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@AllArgsConstructor
@Slf4j
public class RecursiveWebParser extends RecursiveAction {

    private Set<String> urlsList;
    private WebParser webParser;

    @Override
    protected void compute() {
        if(!urlsList.isEmpty()) {
             ForkJoinTask.invokeAll(createSubtasks());
        }
    }

    private Collection<RecursiveWebParser> createSubtasks() {
        List<RecursiveWebParser> taskList = new ArrayList<>();
        urlsList.forEach(recUrl -> {
           RecursiveWebParser task = new RecursiveWebParser(webParser.parsPage(recUrl), webParser);
          taskList.add(task);
        });
        return taskList;
    }
}
