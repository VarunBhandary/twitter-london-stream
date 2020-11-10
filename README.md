# Project "twitter-london-stream"
#### Microsoft Azure based Project for Twitter streaming "london" tags.

![Solution Architecture Diagram (Highlevel)](https://github.com/VarunBhandary/twitter-london-stream/blob/main/architecture_azure_twitter_streaming.png?raw=true)


### Pre-Requisites

#### The project uses following Azure Resources:
1. Azure Event Hub. 
2. Azure VM with the stream analytics dotnet app based on the following Microsoft Project. https://github.com/Azure/azure-stream-analytics
3. Azure Stream Analytics with a near realtime job with Tumbling windows.
4. Microsoft Power BI (Guided Near-REaltime reports) or Azure Databricks (Data Exploration)

#### Account and Subscriptions:
1. Visual Studio Code (with Extensions Azure Stream Analytics, Python, SQL, Azure Storage)
2. Microsoft Azure Account
3. Azure DevOps Account (Signup)
4. Power BI (Pro Account for Realtime Dataset Push from Azure Stream Analytics)
5. Power BI Desktop for (Offline non-Realtime Analytics)
6. Optional. Databricks Account with Spark 2.0 Cluster.
7. Optional. Install Maven Library (Maven Coordinates: com.microsoft.azure:azure-eventhubs-spark_2.11:2.3.15)

#### VM Setup (Can also go for AKS with a custom image):
1. Windows 2016 Server
2. dotnet-sdk-2.1.811-win-x64
3. Powershell

#### Steps for Setup and Operation:
1. Deploy Resources.
2. Connect to the Azure Account from Visual Studio Code after extensions are setup.
3. Make changes to the Stream Analytics Job as required and publish changes through VS Code.
4. Build the DOTNET App follow Instructions published by Microsoft on  https://github.com/Azure/azure-stream-analytics. You can also use another python based client with Tweepy.
5. Run the DOTNET Client to push Tweets to the Event Hub.
6. Start the Stream Analytics Job.
7. Based on the Consumtion Pattern i.e. Power BI Pro Realtime Dataset. Databricks Notebook for real-time analysis. Batch Processing via Storage Account.


#### The following Azure DevOps Pipeline Publishes the Azure Resources (CI/CD)
[![Build Status](https://dev.azure.com/vbrgtogether/Tweet%20Stream/_apis/build/status/VarunBhandary.twitter-london-stream?branchName=refs%2Fpull%2F2%2Fmerge)](https://dev.azure.com/vbrgtogether/Tweet%20Stream/_build/latest?definitionId=1&branchName=refs%2Fpull%2F2%2Fmerge)
