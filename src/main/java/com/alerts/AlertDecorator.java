package com.alerts;

/**
 * The {@code AlertDecorator} class is an abstract decorator class for the {@link Alert} class.
 * It allows additional functionalities to be added to an {@code Alert} object dynamically.
 */
public abstract class AlertDecorator extends Alert {
    protected Alert decoratedAlert;

    /**
     * Constructs an {@code AlertDecorator} with the specified {@code Alert} to be decorated.
     *
     * @param decoratedAlert the {@code Alert} instance to be decorated
     */
    public AlertDecorator(Alert decoratedAlert) {
        super(decoratedAlert.getPatientId(), decoratedAlert.getCondition(), decoratedAlert.getTimestamp());
        this.decoratedAlert = decoratedAlert;
    }

    /**
     * Triggers the alert by invoking the {@code triggerAlert} method of the decorated {@code Alert}.
     */
    @Override
    public void triggerAlert() {
        decoratedAlert.triggerAlert();
    }

    /**
     * Returns the details of the alert by invoking the {@code getDetails} method of the decorated {@code Alert}.
     *
     * @return a string representation of the alert details
     */
    @Override
    public String getDetails() {
        return decoratedAlert.getDetails();
    }
}
