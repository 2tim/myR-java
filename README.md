# myRetail

Live Demo available at: http://ec2-13-59-199-234.us-east-2.compute.amazonaws.com:8080/products/

Technology Stack:

  * Java 8
  * Spring Boot Framework for serving/connecting to API and NoSQL DB connection.
  * Spring REST Docs, kinda
  * MongoDB for data storage
  * Gradle for build and package management
  * Docker for simple deployment with gradle plugin


Instructions for Installation:

All development was done on a Apple PowerBook but should run on other systems That

  * Install MongoDB https://docs.mongodb.com/
  * Ensure Java 8 available
  * Enuse gradle is installed:
    Mac: `brew install gradle`
    Ubuntu: `apt install gradle`
  * clone code from github: https://github.com/2tim/myR-java.git
  * cd to mR directory
  * init gradle with `gradle wrapper`
  * To run go into the project directory and run `./gradlew bootRun`
  * The api should be accessible at http://localhost:8080/products/
  
  For a docker build use:
  * `./gradlew buildImage`

TODO:

  * [Generate Docs for the API with Rest Docs](https://espressoprogrammer.com/spring-rest-docs-example-2/)
  * Write negative test cases for controller.
  * Add packaging with Docker

**API Specification**
----

* **URL**

  `/products/{id}`

* **Method:**


  `GET`


   **Required `GET`:**

   `{id}` must be an integer that corresponds to a product ID.

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"id":13860429,"name":"SpongeBob SquarePants: SpongeBob's Frozen Face-off","current_price":{"value":13.49,"currency_code":"USD"}}`

* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />

  If the product does not exist on the redsky site: <br />

    **Content:** `{ "status" : "No Product found" }` <br />

  If a price is unavailable in the local repository: <br />

    **Content:** `{ "status" : "No Price found" }` <br />

* **Sample Call:**

  curl -v http://localhost:8080/products/13860428

* **Notes:**

  The interface ensures that the non string items specified in the example document are returned as an integer and float.

* **Method:**


  `PUT`


   **Required `PUT`:**

   `{id}` must be an integer that corresponds to a product ID.

   **PUT body:**

   `{"value": 23.42,"currency_code":"USD"}`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"id":13860429,"name":"SpongeBob SquarePants: SpongeBob's Frozen Face-off","current_price":{"value":13.49,"currency_code":"USD"}}`

* **Error Response:**


  * **Code:** 400 BAD_REQUEST <br />
  This will occur on a malformed value or currecy_code.
    **Content:** `{ "status" : "failed" }`

  * **Code:** 404 NOT_FOUND <br />
  This will occur on other errors.
    **Content:** `{ "status" : "failed" }`


* **Sample Call:**

  curl -v -H 'Content-Type: application/json' -H 'Accept: application/json' -X PUT -d '{"value": 23.42,"currency_code":"USD"}' http://localhost:8080/products/138

* **Notes:**

  The interface ensures that the user provided a valid dollar amount and currency code.
