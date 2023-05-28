package com.summary.application.service;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OpenAI {
    String prompt = "";

    public static void main(String[] args) {
            try {
                // OpenAI API endpoint
                String apiEndpoint = "https://api.openai.com/v1/engines/davinci-codex/completions";

                // OpenAI API key
                String apiKey = "YOUR_API_KEY";

                // Set the prompt
                String prompt = "Once upon a time and continue it please";

                // Prepare the JSON request body
                String jsonBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";

                // Create the OkHttpClient
                OkHttpClient client = new OkHttpClient();

                // Create the request object
                Request request = new Request.Builder()
                        .url(apiEndpoint)
                        .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                        .addHeader("Authorization", "Bearer " + apiKey)
                        .build();

                // Send the request and get the response
                Response response = client.newCall(request).execute();

                // Parse and print the response body
                String responseBody = response.body() != null ? response.body().string() : "";
                System.out.println(responseBody);

                // Close the response
                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
