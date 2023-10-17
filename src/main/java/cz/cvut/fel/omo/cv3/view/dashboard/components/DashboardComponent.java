package cz.cvut.fel.omo.cv3.view.dashboard.components;

import java.util.Optional;

public interface DashboardComponent {

    default Optional<String> getHeader() {
        return Optional.empty();
    }

    default String getHeaderDelimiter() {
        return "----------------------------";
    }

    String getContent();

    default String getComponentBottomDelimiter() {
        return "\n\n";
    }

}
