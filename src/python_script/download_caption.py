from youtube_transcript_api import YouTubeTranscriptApi

import sys

# Check if the parameter exists
if len(sys.argv) > 1:
    # Access the first parameter value
    parameter = sys.argv[1]
    # print("Parameter value:", parameter)
    srt = YouTubeTranscriptApi.get_transcript(parameter)
else:
    srt = YouTubeTranscriptApi.get_transcript("SW14tOda_kI")
    # print("No parameter provided.")

# prints the result
print(srt)