package com.alerts;

/**
 * The {@code PriorityAlertDecorator} class is a decorator for {@link Alert} objects that adds a priority level to alerts.
 * It extends the {@link AlertDecorator} class and adds functionality to specify and display the priority of alerts.
 */
public class PriorityAlertDecorator extends AlertDecorator {
    private String priority;

    /**
     * Constructs a {@code PriorityAlertDecorator} with the specified decorated alert and priority.
     *
     * @param decoratedAlert the {@code Alert} object to be decorated
     * @param priority the priority level of the alert
     */
    public PriorityAlertDecorator(Alert decoratedAlert, String priority) {
        super(decoratedAlert);
        this.priority = priority;
    }

    /**
     * Triggers the alert, printing the priority level before invoking the decorated alert's trigger method.
     */
    @Override
    public void triggerAlert() {
        System.out.println("Priority: " + priority);
        super.triggerAlert();
    }

    /**
     * Returns the details of the alert, including the priority level and details of the decorated alert.
     *
     * @return a string representation of the alert details including priority and decorated alert details
     */
    @Override
    public String getDetails() {
        return "Priority: " + priority + ", " + super.getDetails();
    }
}

