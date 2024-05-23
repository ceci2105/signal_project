package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {
    private int repeatCount;
    private long interval; // in milliseconds

    public RepeatedAlertDecorator(Alert decoratedAlert, int repeatCount, long interval) {
        super(decoratedAlert);
        this.repeatCount = repeatCount;
        this.interval = interval;
    }

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

