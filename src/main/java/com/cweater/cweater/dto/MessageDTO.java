package com.cweater.cweater.dto;

public class MessageDTO {

    private String text;
    private String tag;
    private UserDTO author;

    @Override
    public String toString() {
        return text + "\t" + tag + "\t" + author.getUsername();
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
