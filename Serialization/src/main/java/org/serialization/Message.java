package org.serialization;

import java.io.Serializable;

public class Message implements Serializable {

    public String message;
    public transient String sensitiveData; // Transient field


    public Message(String message, String sensitiveData)
    {
        this.message = message;
        this.sensitiveData=sensitiveData;
    }

}
