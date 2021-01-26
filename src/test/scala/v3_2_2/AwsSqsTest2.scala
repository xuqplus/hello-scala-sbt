package v3_2_2

import com.amazon.sqs.javamessaging._
import com.amazon.sqs.javamessaging.message.{SQSMessage, SQSTextMessage}
import com.amazonaws.auth.{AWSCredentialsProviderChain, AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.model.{DeleteMessageRequest, GetQueueUrlResult, SendMessageRequest, SendMessageResult}
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}
import javax.jms.{QueueReceiver, QueueSession, Session}
import org.joda.time.DateTime
import org.scalatest.funsuite.AnyFunSuite

class AwsSqsTest2 extends AnyFunSuite {
  var awsKey = "AKIASZXRS7VIHQSUYA72"
  var awsSecret = "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"
  var region = Regions.AP_EAST_1
  val queue = "hello-sqs2"

  def getClient(): AmazonSQSMessagingClientWrapper = {
    getConnection.getWrappedAmazonSQSClient
  }

  def getConnection(): SQSConnection = {
    info("define aws credentials")
    // awsCredentials
    val awsCredentials = new BasicAWSCredentials(awsKey, awsSecret)

    val configuration = new ProviderConfiguration
    info("create sqs client builder configuration")
    val builder: AmazonSQSClientBuilder = AmazonSQSClientBuilder.standard()
    info("set region")
    builder.setRegion(region.getName)
    builder.setCredentials(new AWSCredentialsProviderChain(new AWSStaticCredentialsProvider(awsCredentials)))
    info("build the client")
    val client: AmazonSQS = builder.build()
    // if no further arguments set
    // a new connection factory will be with all defaults (credentials and region) set automatically
    info("build connection factory")
    val connectionFactory = new SQSConnectionFactory(configuration, client)

    info("create connection")
    val connection: SQSConnection = connectionFactory.createConnection()

    connection
  }

  test("sqs queue creation") {
    info("queue name=>" + queue)
    val client: AmazonSQSMessagingClientWrapper = getClient()
    if (!client.queueExists(queue)) {
      client.createQueue(queue)
    }

    val result: GetQueueUrlResult = client.getQueueUrl(queue)
    assert(null != result && result.getQueueUrl.endsWith(queue))
  }

  test("send message") {
    info("queue name=>" + queue)
    val client: AmazonSQSMessagingClientWrapper = getClient()
    if (!client.queueExists(queue)) {
      client.createQueue(queue)
    }

    val sendMessageRequest: SendMessageRequest = new SendMessageRequest(
      client.getQueueUrl(queue).getQueueUrl, String.format("message-%s-%s", System.currentTimeMillis(), DateTime.now().toString()))
    val sendMessageResult: SendMessageResult = client.sendMessage(sendMessageRequest)
    assert(null != sendMessageResult)
    assert(null != sendMessageResult.getMessageId)
    info("SendMessageResult=>" + sendMessageResult)
  }

  test("receive message") {
    info("queue name=>" + queue)
    val connection: SQSConnection = getConnection()
    val session: QueueSession = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE)
    val queueReceiver: QueueReceiver = session.createReceiver(session.createQueue(queue))
    queueReceiver.setMessageListener(message => {
      if (message.isInstanceOf[SQSMessage]) {
        val m2: SQSMessage = message.asInstanceOf[SQSMessage]
        info("message.id=>" + m2.getJMSMessageID)
        info("message.receiptHandle=>" + m2.getReceiptHandle)
        if (message.isInstanceOf[SQSTextMessage]) {
          val m3: SQSTextMessage = message.asInstanceOf[SQSTextMessage]
          info("message.text=>" + m3.getText)
        }
      }
    })
    connection.start()

    Thread.sleep(10000L)
    info("receive message end ..")
  }

  test("delete message") {
    info("queue name=>" + queue)
    val connection: SQSConnection = getConnection()
    val session: QueueSession = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE)
    val queueReceiver: QueueReceiver = session.createReceiver(session.createQueue(queue))
    queueReceiver.setMessageListener(message => {
      if (message.isInstanceOf[SQSMessage]) {
        val m2: SQSMessage = message.asInstanceOf[SQSMessage]
        info("message.id=>" + m2.getJMSMessageID)
        info("message.receiptHandle=>" + m2.getReceiptHandle)
        if (message.isInstanceOf[SQSTextMessage]) {
          val m3: SQSTextMessage = message.asInstanceOf[SQSTextMessage]
          info("message.text=>" + m3.getText)
        }

        val client: AmazonSQSMessagingClientWrapper = getClient()
        val deleteMessageRequest = new DeleteMessageRequest(
          client.getQueueUrl(queue).getQueueUrl, m2.getReceiptHandle)
        client.deleteMessage(deleteMessageRequest)
      }
    })
    connection.start()

    Thread.sleep(10000L)
    info("delete message end ..")
  }
}
