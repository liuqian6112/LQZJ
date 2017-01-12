package com.lqzj.web.model;

public enum FileType {
    PAGE("page");

    private final String folder;

    public String getFolder() {
        return folder;
    }

    FileType(String folder) {
        this.folder = folder;
    }
}
