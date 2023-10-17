package cz.cvut.fel.omo.cv3.view.dashboard;

import cz.cvut.fel.omo.cv3.view.dashboard.components.DashboardComponent;

import java.util.List;

public class Dashboard {

    private final List<DashboardComponent> components;

    public Dashboard(List<DashboardComponent> components) {
        this.components = components;
    }

    public void printDashboard() {
        printComponents(components);
    }

    private void printComponents(List<? extends DashboardComponent> components) {
        for (DashboardComponent component : components) {
            component.getHeader().ifPresent(header -> {
                System.out.println(header);
                System.out.println(component.getHeaderDelimiter());
            });
            System.out.println(component.getContent());
            System.out.println(component.getComponentBottomDelimiter());
        }
    }
}
