package com.summary.application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallPythonScriptForYoutubeCaption {

    public static void main(String[] args) {
        String video = "https://www.youtube.com/watch?v=IjKQke8XeMI";
        // get the part after v= from the URL
        String videoId = video.substring(video.indexOf("=") + 1);
        System.out.println("VideoId: " + videoId);
        System.out.println(getCaptionFromPythonScript(videoId));
    }

    public static String getCaptionFromPythonScript(String videoId) {
        try {
            // fix this to be able to pass parameters:

            // Path to the Python script
            String pythonScriptPath = "src/python_script/download_caption.py";

            // Build the command to execute the Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath);
            processBuilder.command().add(videoId);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                result.append(line);
            }

            return result.toString();
            // Wait for the process to complete
//            int exitCode = process.waitFor();
//            System.out.println("Python script executed with exit code: " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
