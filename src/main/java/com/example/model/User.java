package com.example.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="users")
@Embeddable
public class User{
    @Id
    private Long chatId;
    private String name;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                '}';
    }
}
