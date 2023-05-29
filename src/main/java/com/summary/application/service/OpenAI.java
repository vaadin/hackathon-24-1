package com.summary.application.service;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenAI {

    public static void main(String[] args) {
        getSummary("[{'text': 'hello friends and welcome to geeks for', 'start': 0.0, 'duration': 1.92}, {'text': 'geeks in this tutorial we will learn how', 'start': 1.92, 'duration': 2.64}, {'text': 'to reverse a stack using recursion so', 'start': 4.56, 'duration': 2.279}, {'text': 'here is the problem we have to reverse a', 'start': 6.839, 'duration': 2.131}, {'text': 'stack and we are not allowed to use', 'start': 8.97, 'duration': 1.65}, {'text': 'loops like while four etc we can use', 'start': 10.62, 'duration': 4.05}, {'text': 'functions of stacks like is empty push', 'start': 14.67, 'duration': 3.42}, {'text': 'and pop the idea of the solution is to', 'start': 18.09, 'duration': 3.51}, {'text': 'hold all the values and function call', 'start': 21.6, 'duration': 2.46}, {'text': 'stack until the start becomes empty when', 'start': 24.06, 'duration': 3.059}, {'text': 'the start becomes empty we insert all', 'start': 27.119, 'duration': 2.4}, {'text': 'the hell values one by one at the bottom', 'start': 29.519, 'duration': 3.031}, {'text': 'of the start let us now look at how it', 'start': 32.55, 'duration': 2.22}, {'text': 'works', 'start': 34.77, 'duration': 0.51}, {'text': 'let us assume our stag as 1 2 3 4 with 1', 'start': 35.28, 'duration': 4.92}, {'text': 'at top we pop all the values 1 by 1 and', 'start': 40.2, 'duration': 4.41}, {'text': 'hold all of them in function call stack', 'start': 44.61, 'duration': 2.73}, {'text': 'until this side becomes empty so here we', 'start': 47.34, 'duration': 3.059}, {'text': 'go we pop all the values until the side', 'start': 50.399, 'duration': 4.171}, {'text': 'becomes empty so a function called stag', 'start': 54.57, 'duration': 2.94}, {'text': 'in order is 4 3 2 and 1', 'start': 57.51, 'duration': 4.639}, {'text': 'since the stack is empty now we need to', 'start': 62.149, 'duration': 3.19}, {'text': 'insert all the hello elements one by one', 'start': 65.339, 'duration': 2.491}, {'text': 'at the bottom of stack now how to insert', 'start': 67.83, 'duration': 3.42}, {'text': 'elements at the bottom of stack to do so', 'start': 71.25, 'duration': 3.24}, {'text': 'we first pop all the elements and hold', 'start': 74.49, 'duration': 2.73}, {'text': 'their values in function call stack', 'start': 77.22, 'duration': 2.1}, {'text': 'until the start is empty then we push', 'start': 79.32, 'duration': 3.18}, {'text': 'the element that was supposed to be', 'start': 82.5, 'duration': 1.53}, {'text': 'inserted at the bottom and then we push', 'start': 84.03, 'duration': 3.21}, {'text': \"all the elements back let's see how it\", 'start': 87.24, 'duration': 2.79}, {'text': 'works', 'start': 90.03, 'duration': 0.33}, {'text': 'since the stack is empty now we push the', 'start': 90.36, 'duration': 2.759}, {'text': 'first element and function called stack', 'start': 93.119, 'duration': 1.831}, {'text': \"that is 4 so the stack now becomes 4 I'd\", 'start': 94.95, 'duration': 5.04}, {'text': 'talk now we need to insert 3 at the end', 'start': 99.99, 'duration': 3.169}, {'text': 'according to the algorithm we pop the', 'start': 103.159, 'duration': 2.981}, {'text': 'entire stack and hold its value in the', 'start': 106.14, 'duration': 2.79}, {'text': 'function called stack then we push the', 'start': 108.93, 'duration': 2.939}, {'text': 'element to be inserted at the end so we', 'start': 111.869, 'duration': 3.481}, {'text': 'pop 4 and push 3 that is the next', 'start': 115.35, 'duration': 4.53}, {'text': 'element in the function call stack so a', 'start': 119.88, 'duration': 3.36}, {'text': 'stack becomes 3 at top now we push back', 'start': 123.24, 'duration': 3.57}, {'text': 'all the elements and function called', 'start': 126.81, 'duration': 2.16}, {'text': 'stack that is element which just popped', 'start': 128.97, 'duration': 2.7}, {'text': 'so we push four', 'start': 131.67, 'duration': 1.8}, {'text': 'so our staff becomes 4 3 with 4 at the', 'start': 133.47, 'duration': 4.56}, {'text': 'top now we need to insert the next held', 'start': 138.03, 'duration': 3.81}, {'text': 'value in the function call such that is', 'start': 141.84, 'duration': 2.07}, {'text': 'to putting to the algorithm we need to', 'start': 143.91, 'duration': 2.25}, {'text': 'pop 4 and 3 and then push 2 at the top', 'start': 146.16, 'duration': 4.73}, {'text': 'now we will push back the elements we', 'start': 150.89, 'duration': 3.04}, {'text': 'just popped so our stack becomes 4 3 2', 'start': 153.93, 'duration': 5.9}, {'text': 'similarly we insert 1 and we can clearly', 'start': 159.83, 'duration': 3.07}, {'text': 'see that the stack of the worst let us', 'start': 162.9, 'duration': 3.63}, {'text': 'now look at the implementation this code', 'start': 166.53, 'duration': 2.43}, {'text': 'has been taken from geeks for geeks', 'start': 168.96, 'duration': 1.56}, {'text': 'website the function reverse reverses', 'start': 170.52, 'duration': 2.79}, {'text': 'the stack in this function if the stack', 'start': 173.31, 'duration': 3.75}, {'text': 'is not empty we hold all the items in', 'start': 177.06, 'duration': 2.94}, {'text': 'the function called stack until we reach', 'start': 180.0, 'duration': 2.43}, {'text': 'the end of stack then we insert all', 'start': 182.43, 'duration': 2.55}, {'text': 'these items one by one at the bottom of', 'start': 184.98, 'duration': 3.24}, {'text': 'stack using insert add bottom function', 'start': 188.22, 'duration': 2.81}, {'text': 'the function insert at bottom insert an', 'start': 191.03, 'duration': 3.22}, {'text': 'element at the bottom of stack if the', 'start': 194.25, 'duration': 2.82}, {'text': 'stack is empty we push the element at', 'start': 197.07, 'duration': 1.889}, {'text': 'the top else we pop and hold all the', 'start': 198.959, 'duration': 3.511}, {'text': 'values in function call stack until we', 'start': 202.47, 'duration': 2.61}, {'text': 'reach end of the sack when the start', 'start': 205.08, 'duration': 2.67}, {'text': 'becomes empty this statement again', 'start': 207.75, 'duration': 1.86}, {'text': 'becomes foo and the element is inserted', 'start': 209.61, 'duration': 2.37}, {'text': 'then we push back all the elements that', 'start': 211.98, 'duration': 3.12}, {'text': 'we just popped that is the elements held', 'start': 215.1, 'duration': 2.85}, {'text': \"in the function call stack that's it for\", 'start': 217.95, 'duration': 2.4}, {'text': 'this tutorial thank you for watching', 'start': 220.35, 'duration': 1.35}, {'text': 'please leave as she likes and comments', 'start': 221.7, 'duration': 4.16}]\n");
    }

    public static String getSummary(String caption) {
            try {
                // OpenAI API key
                String apiKey = "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";

                // Set the prompt
                String prompt = "Check the caption below, and make a summary of it in 5 points with timestamps, try to catch the most important key points: \n\n";

                // String instruction = "You are a friendly and helpful social robot. Your name is Furhat. You give very brief answers.";
                List<ChatMessage> messages = new ArrayList<>();
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setRole("system");
                chatMessage.setContent(prompt + caption);
                messages.add(chatMessage);


                // use the new AI service rather not the client
                OpenAiService service = new OpenAiService(apiKey, Duration.ofSeconds(20));
                ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                        .messages(messages)
                        .model("gpt-3.5-turbo")
                        .build();
                List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();
                choices.forEach(System.out::println);

                return choices.get(0).getMessage().getContent();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return "";
            }
        }
    }
