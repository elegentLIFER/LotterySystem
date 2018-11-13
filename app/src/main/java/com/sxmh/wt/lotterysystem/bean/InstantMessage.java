package com.sxmh.wt.lotterysystem.bean;

import java.util.List;

public class InstantMessage {

    /**
     * content : 00409249242
     * terminalIds : [15,17,23]
     */

    private String content;
    private List<Integer> terminalIds;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getTerminalIds() {
        return terminalIds;
    }

    public void setTerminalIds(List<Integer> terminalIds) {
        this.terminalIds = terminalIds;
    }
}
