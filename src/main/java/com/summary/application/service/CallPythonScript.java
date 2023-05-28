package com.summary.application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallPythonScript {

    public static void main(String[] args) {
        try {
            // Path to the Python script
            String pythonScriptPath = "/Users/pczuczor/Projects/utils/youtube/download_caption.py";

            // Build the command to execute the Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
