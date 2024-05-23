package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {
    private String priority;

    public PriorityAlertDecorator(Alert decoratedAlert, String priority) {
        super(decoratedAlert);
        this.priority = priority;
    }

    @Override
    public void triggerAlert() {
        System.out.println("Priority: " + priority);
        super.triggerAlert();
    }

    @Override
    public String getDetails() {
        return "Priority: " + priority + ", " + super.getDetails();
    }
}

