package org.serialization;

import java.io.*;

public class SerializationTester {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*Note:
        //Serialization:
        //
        //Definition: Serialization is the process of converting a data structure or object into a format
         that can be easily stored (such as in a file) or transmitted (such as across a network).
        //Purpose: The main purpose of serialization is to save the state of an object in order to be able to recreate it when needed.
        //Usage: Serialization is commonly used when the need arises to send data over a network or store it in a file. Serialized data can also be used to persist application state between sessions.
        //Formats: Serialized data can be in the form of a byte stream, XML, JSON, or any other format suitable for storage or transmission.
        //Deserialization:
        //
        //Definition: Deserialization is the process of reconstructing the original data structure or object from the serialized data.
        //Purpose: The main purpose of deserialization is to recreate the object that was serialized earlier.
        //Usage: Deserialization is used to retrieve the original state of an object from its serialized form. This is typically done when the object needs to be used again after being stored or transmitted.
        //Process: During deserialization, the serialized data is read and converted back into the original data structure or object.
        */


        //In Java, the transient keyword is used to indicate that a field should not be serialized.
        // When an instance of a class is serialized, any field marked as transient will not be included
        // in the serialized output. This is useful when you have sensitive data in an object that should not be persisted.


        // Creating an object
        Message message = new Message("Hello, this is example of serialization","SensitiveData");

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/saudwive/Documents/sample.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(message);
        out.close();
        fileOutputStream.close();
        System.out.println("Message has been serialized.");

        // Deserialization
        FileInputStream fileIn = new FileInputStream("/Users/saudwive/Documents/sample.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Message object = (Message) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Message has been deserialized. Message: " + object.message);
        System.out.println("Sensitive data: " + object.sensitiveData);

    }
}