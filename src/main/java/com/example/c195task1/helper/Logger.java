package com.example.c195task1.helper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class for holding logging functionality
 *
 */
public class Logger {
    /** This method takes a string and logs it to a file.
     * It adds a date to the written line.
     * @param message The message to write to the file
     * @throws IOException
     */
    public static void log(String message) throws IOException {
        DateTimeFormatter d = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String filename = "LoginActivity.txt";
        PrintWriter pw = new PrintWriter(filename);
        pw.println(String.format("%s | %s", LocalDateTime.now().format(d), message));
        pw.close();
    }
}
