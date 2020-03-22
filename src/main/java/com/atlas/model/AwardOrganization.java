package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AwardOrganization {
    private int oid;
    private String name;
    private String websiteUrl;

    public AwardOrganization(int oid, String name, String websiteUrl) {
        this.oid = oid;
        this.name = name;
        this.websiteUrl = websiteUrl;
    }

    public AwardOrganization(@JsonProperty("name") String name,
                             @JsonProperty("websiteUrl") String websiteUrl) {
        this.oid = 0;
        this.name = name;
        this.websiteUrl = websiteUrl;
    }

    public int getOid() {
        return oid;
    }

    public String getName() {
        return name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
}
