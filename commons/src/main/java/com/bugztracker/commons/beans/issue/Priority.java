package com.bugztracker.commons.beans.issue;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 13.02.2016
 * Time: 14:55
 */
public enum Priority {

    BLOCKER(1), CRITICAL(2), MAJOR(3), TRIVIAL(4), MINOR(5);

    private final static Priority[] values = values();
    private final int value;

    Priority(int v) {
        value = v;
    }

    public int value() {
        return value;
    }

    public static Priority fromValue(int typeCode) {
        for (Priority priority : values) {
            if (priority.value == typeCode) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid status type code: " + typeCode);
    }
}
