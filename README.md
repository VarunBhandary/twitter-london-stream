# twitter-london-stream
GCP Project for Twitter streaming "london" tags.

The project uses following Azure Resources -
1. Azure Event Hub. 
2. Azure VM with the stream analytics dotnet app based on the following Microsoft Project. https://github.com/Azure/azure-stream-analytics
3. Azure Stream Analytics with a near realtime job with Tumbling windows.


## The following Azure DevOps Pipeline Publishes the Azure Resources 
[![Build Status](https://dev.azure.com/vbrgtogether/Tweet%20Stream/_apis/build/status/VarunBhandary.twitter-london-stream?branchName=refs%2Fpull%2F2%2Fmerge)](https://dev.azure.com/vbrgtogether/Tweet%20Stream/_build/latest?definitionId=1&branchName=refs%2Fpull%2F2%2Fmerge)
