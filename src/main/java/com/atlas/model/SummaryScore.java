package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryScore {
    private int gid;
    private String title;
    private double avg;
    private double max;
    private double min;
    private int count;

    public SummaryScore(@JsonProperty("gid") int gid,
                        @JsonProperty("title") String title,
                        @JsonProperty("avg") double avg,
                        @JsonProperty("max") double max,
                        @JsonProperty("min") double min,
                        @JsonProperty("count") int count) {
        this.gid = gid;
        this.title = title;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public int getGid() {
        return gid;
    }

    public String getTitle() {
        return title;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public int getCount() {
        return count;
    }
}
