package com.alerts;

/**
 * The {@code RepeatedAlertDecorator} class is a decorator for {@link Alert} objects that triggers the alert repeatedly.
 * It extends the {@link AlertDecorator} class and adds functionality to trigger the alert multiple times at specified intervals.
 */
public class RepeatedAlertDecorator extends AlertDecorator {
    private int repeatCount;
    private long interval; // in milliseconds

    /**
     * Constructs a {@code RepeatedAlertDecorator} with the specified decorated alert, repeat count, and interval.
     *
     * @param decoratedAlert the {@code Alert} object to be decorated
     * @param repeatCount the number of times the alert should be repeated
     * @param interval the interval between each repetition in milliseconds
     */
    public RepeatedAlertDecorator(Alert decoratedAlert, int repeatCount, long interval) {
        super(decoratedAlert);
        this.repeatCount = repeatCount;
        this.interval = interval;
    }

    /**
     * Triggers the alert repeatedly for the specified number of times at the specified interval.
     * The alert is triggered by invoking the decorated alert's trigger method.
     */
    @Override
    public void triggerAlert() {
        for (int i = 0; i < repeatCount; i++) {
            super.triggerAlert();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


