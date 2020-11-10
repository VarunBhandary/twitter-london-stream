// Databricks notebook source
// DBTITLE 1,Import Packages 
/*Author: Varun Bhandary*/
/*Date: 10/11/2020*/
/*Dependencies on Maven Library: com.microsoft.azure:azure-eventhubs:3.2.2 */

import scala.collection.JavaConverters._
import com.microsoft.azure.eventhubs._
import java.util.concurrent._
import scala.collection.immutable._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

// COMMAND ----------

// DBTITLE 1,Variables for Connection to EventHub
/*To be added to Azure Keyvault backed secret scope in future. Secret Scopes are not available on Azure Databricks Community Edition*/
val namespaceName = "##REDACTED##"
val eventHubName = "##REDACTED##"
val sasKeyName = "##REDACTED##"
val sasKey = "##REDACTED##"
val connStr = new ConnectionStringBuilder()
			.setNamespaceName(namespaceName)
			.setEventHubName(eventHubName)
			.setSasKeyName(sasKeyName)
			.setSasKey(sasKey)

// COMMAND ----------

val pool = Executors.newScheduledThreadPool(1)
// val eventHubClient = EventHubClient.create(connStr.toString(), pool)
val eventHubClient = EventHubClient.createFromConnectionString(connStr.toString(), pool)

def sleep(time: Long): Unit = Thread.sleep(time)

def sendEvent(message: String, delay: Long) = {
  sleep(delay)
  val messageData = EventData.create(message.getBytes("UTF-8"))
  eventHubClient.get().send(messageData)
  System.out.println("Sent event: " + message + "\n")
}

// Add your own values to the list
val testSource = List("Azure is the greatest!", "Azure isn't working :(", "Azure is okay.")

// Specify 'test' if you prefer to not use Twitter API and loop through a list of values you define in `testSource`
// Otherwise specify 'twitter'
val dataSource = "twitter"

if (dataSource == "twitter") {

  import twitter4j._
  import twitter4j.TwitterFactory
  import twitter4j.Twitter
  import twitter4j.conf.ConfigurationBuilder

  // Twitter configuration!
  // Replace values below with your Twitter API registration and App secrets and tokens

  val twitterConsumerKey = "##REDACTED##"
  val twitterConsumerSecret = "##REDACTED##"
  val twitterOauthAccessToken = "##REDACTED##"
  val twitterOauthTokenSecret = "##REDACTED##"

  val cb = new ConfigurationBuilder()
	cb.setDebugEnabled(true)
	.setOAuthConsumerKey(twitterConsumerKey)
	.setOAuthConsumerSecret(twitterConsumerSecret)
	.setOAuthAccessToken(twitterOauthAccessToken)
	.setOAuthAccessTokenSecret(twitterOauthTokenSecret)

  val twitterFactory = new TwitterFactory(cb.build())
  val twitter = twitterFactory.getInstance()

  // Getting tweets with keyword "london" and sending them to the Event Hub in realtime!
  val query = new Query(" #london ")
  query.setCount(100)
  query.lang("en")
  var finished = false
  while (!finished) {
	val result = twitter.search(query)
	val statuses = result.getTweets()
	var lowestStatusId = Long.MaxValue
	for (status <- statuses.asScala) {
	  if(!status.isRetweet()){
		sendEvent(status.getText(), 5000)
	  }
	  lowestStatusId = Math.min(status.getId(), lowestStatusId)
	}
	query.setMaxId(lowestStatusId - 1)
  }

} else if (dataSource == "test") {
  // Loop through the list of test input data
  while (true) {
	testSource.foreach {
	  sendEvent(_,5000)
	}
  }

} else {
  System.out.println("Unsupported Data Source. Set 'dataSource' to \"twitter\" or \"test\"")
}
