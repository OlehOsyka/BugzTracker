package com.bugztracker.commons.entity.issue;

/**
 * Created by Oleh_Osyka for EPMC-CLO
 * Date: 13.02.2016
 * Time: 14:56
 */
public enum Category {

    ISSUE(1), BUG(2), IMPROVEMENT(3);

    private final static Category[] values = Category.values();
    private final int value;

    Category(int v) {
        value = v;
    }

    public int value() {
        return value;
    }

    public static Category fromValue(int typeCode) {
        for (Category category : values) {
            if (category.value == typeCode) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid status type code: " + typeCode);
    }
}
