package com.example.mynotebook.models;

public class Note {
    private static int noteCount = 0;
    public int id;
    public String title;
    public String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.id = noteCount;
        noteCount++;
    }

    public void setId(int id) {
        this.id = id;
    }
}
