package com.atlas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoiceActor {
    private final int pid;
    private final String primaryLanguage ;

    public VoiceActor(@JsonProperty("pid") int pid, @JsonProperty("specialization") String primaryLanguage ) {
        this.pid = pid;
        this.primaryLanguage  = primaryLanguage ;
    }

    public VoiceActor(@JsonProperty("specialization") String primaryLanguage ) {
        this.pid = 0;
        this.primaryLanguage  = primaryLanguage ;
    }

    public int getPid() {
        return pid;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage ;
    }
}
