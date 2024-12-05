package ru.mtuci.antivirus.utils;

import java.io.*;

public class PipeHandler {

    private static final String PIPE_PATH = "\\\\.\\pipe\\AntivirusServicePipe";

    public String sendRegistrationData(String login, String password, String email) {
        String data = "register:" + login + ":" + password + ":" + email;

        String response = "";
        try (RandomAccessFile pipe = new RandomAccessFile(PIPE_PATH, "rw")) {
            // Write data to the named pipe
            pipe.writeBytes(data);
            pipe.writeByte('\n'); // Ensure the data is properly terminated

            // Read response from the named pipe
            response = pipe.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}