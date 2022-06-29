package com.example.gujengapp;

public class Word {
    private String gDefaultTranslation;
    private String gGujTranslation;
    private int gImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int gAudioResourceId;

    public Word(String gDefaultTranslation, String gGujTranslation, int gAudioResourceId) {
        this.gDefaultTranslation = gDefaultTranslation;
        this.gGujTranslation = gGujTranslation;
        this.gAudioResourceId = gAudioResourceId;
    }

    public Word(String gDefaultTranslation, String gGujTranslation, int gImageResourceId, int gAudioResourceId) {
        this.gGujTranslation = gGujTranslation;
        this.gDefaultTranslation = gDefaultTranslation;
        this.gImageResourceId = gImageResourceId;
        this.gAudioResourceId  = gAudioResourceId;
    }

    public String getDefaultTranslation() {
        return gDefaultTranslation;
    }

    public String getGujTranslation() {
        return gGujTranslation;
    }

    public int getImageResourceId() {
        return gImageResourceId;
    }

    public int getAudioResourceId() {
        return gAudioResourceId;
    }

    public boolean hasImage() {
        return gImageResourceId != NO_IMAGE_PROVIDED;
    }
}
