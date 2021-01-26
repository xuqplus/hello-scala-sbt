package v3_2_2

import com.amazon.sqs.javamessaging.{ProviderConfiguration, SQSConnectionFactory}
import com.amazonaws.auth.{AWSCredentialsProviderChain, AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.model.GetQueueUrlResult
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}
import org.scalatest.funsuite.AnyFunSuite

class AwsSqsTest extends AnyFunSuite {
  var awsKey = "AKIASZXRS7VIHQSUYA72"
  var awsSecret = "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"

  test("sqs connection") {
    info("define aws credentials")
    // awsCredentials
    val awsCredentials = new BasicAWSCredentials(awsKey, awsSecret)

    val configuration = new ProviderConfiguration
    info("create sqs client builder configuration")
    val builder: AmazonSQSClientBuilder = AmazonSQSClientBuilder.standard()
    info("set region")
    builder.setRegion(Regions.AP_EAST_1.getName)
    builder.setCredentials(new AWSCredentialsProviderChain(new AWSStaticCredentialsProvider(awsCredentials)))
    info("build the client")
    val client: AmazonSQS = builder.build()
    // if no further arguments set
    // a new connection factory will be with all defaults (credentials and region) set automatically
    info("build connection factory")
    val connectionFactory = new SQSConnectionFactory(configuration, client)

    info("create connection")
    val connection = connectionFactory.createConnection()

    assert(null != connection)

    info("getQueueUrl=>")
    val urlResult: GetQueueUrlResult = client.getQueueUrl("hello-sqs")
    assert(null != urlResult)
    info("getQueueUrl=>" + urlResult.getQueueUrl)
  }
}
