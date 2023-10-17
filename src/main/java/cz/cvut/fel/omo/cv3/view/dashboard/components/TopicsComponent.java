package cz.cvut.fel.omo.cv3.view.dashboard.components;

import cz.cvut.fel.omo.cv3.model.Topic;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class TopicsComponent implements DashboardComponent {

    private final Collection<Topic> topics;

    public TopicsComponent(Collection<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public Optional<String> getHeader() {
        return Optional.of("Available topics");
    }

    @Override
    public String getContent() {
        return topics.stream()
                     .map(t -> t.getName() + "(" + t.getPosts().size() + " posts)")
                     .collect(Collectors.joining(", "));
    }
}
