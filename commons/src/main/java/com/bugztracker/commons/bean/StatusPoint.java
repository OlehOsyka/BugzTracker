package com.bugztracker.commons.bean;

import java.util.Date;

/**
 * Author: Yuliia Vovk
 * Date: 22.02.16
 * Time: 10:46
 */
public class StatusPoint {

    private int opened;
    private int closed;
    private Date date;

    public StatusPoint(int opened, int closed, Date date) {
        this.opened = opened;
        this.closed = closed;
        this.date = date;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
