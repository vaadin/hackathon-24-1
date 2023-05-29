# Summary

This Hackathon project had two main subprojects:
- Summary view: which can generate a summary test for shorter YouTube videos (Tokenization is not done, so 4-5 min is the limit now)
- Map view: which is getting the user IP and then using GeoLocate service for address and adding that to the Map (not finished)


## Notes:
- Pyhton 3 need to be installed properly, plus:
- pip install youtube_transcript_api
- test if you can execute this python script src/python_script/download_caption.py

## Steps:
1. Enter a Youtube video URL
2. Click on Get Caption
3. Click on Get Summary
4. Wait for the summary to be generated (usually 5-10 seconds, and check for the logs in the console for potential errors)


## Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different IDEs](https://vaadin.com/docs/latest/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).
