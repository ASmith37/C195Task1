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
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(String.format("%s | %s", LocalDateTime.now().format(d), message));
        fw.close();
    }
}
