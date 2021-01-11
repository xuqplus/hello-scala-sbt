package v3_2_2

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

class BTest extends AnyFeatureSpec with GivenWhenThen {

  info("1")
  info("3")

  Feature("feature 0") {
    Scenario("scenario 0") {
      Given("1")
      assert(true)

      When("2")

      Then("3")
      assert(false)
    }
  }

  info("2")
}