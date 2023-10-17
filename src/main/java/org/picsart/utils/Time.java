package org.picsart.utils;

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) throws IllegalArgumentException {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException();
        } else {
            this.hour = hour;
            this.minute = minute;
        }
    }

    public void plusMinute() {
        if (minute == 59) {
            if (hour == 23) {
                hour = 0;
            } else {
                hour++;
            }
            minute = 0;
        } else {
            minute++;
        }
    }

    public Time plusMinutes(int minutes) {
        int newHour = hour + (minutes % 60);
        int newMinute = minute + minutes / 60;
        if (newHour > 23) {
            newHour = newHour % 24;
        }
        if (newMinute > 59) {
            newMinute = newMinute % 60;
        }
        return new Time(newHour, newMinute);
    }

    public int differenceMillis(Time time) {
        return Math.abs(((hour - time.hour) * 60 + (minute - time.minute)) * 1000);
    }

    public int getHour() { return hour; }
    public int getMinute() {return minute; }

    @Override
    public int compareTo(Time o) {
        if (hour > o.hour) return 1;
        else if (hour < o.hour) return -1;
        else return Integer.compare(minute, o.minute);
    }

    @Override
    public String toString() {
        return "%02d:%02d".formatted(hour, minute);
    }
}
