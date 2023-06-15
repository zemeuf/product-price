# Product Price
This application exposes an endpoint that, given a productId and an amount, calculates the discount and the final price

## Design choices
### Architecture
* Spring boot was used to expose the API and wire all components, together with an H2 database to have some products available in-memory
* To avoid managing complex business rules in Java code (which could lead to huge nested if statements) and for the sake of simplicity to read and maintain these rules, Drools engine was used to manage discount calculation
  * A rules file was created in `src/resources/rules/discount.drl`, where it's possible to express all the discount conditions, based on the policy type and amount
  * Rules are expressed using `mvel` dialect

### Managing discount policies
* Each policy is associated with an entry in `DiscountType` (there are currently 2, `quantity` and `percentage`)
* Also, each product is associated with one policy
* In case of adding a new policy, the steps are the following:
  * Add a new entry in `DiscountType`
  * Add the rules associated with the new policy in `src/resources/rules/discount.drl`
  * Configure new products for the policy, by adding them to the database (use the enum ordinal value for the `discount_type` column)

## How to build
Docker is the only dependency, since there is a Dockerfile supporting multi-stage building. To compile and build an image, run `docker build --platform linux/amd64 -t product-price .` (--platform might change depending on your OS system)

## How to run
* Run `docker run -p 8000:8000 product-price`
* The exposed endpoint is `/products/{productId}/final-price?amount=xx`
  * `productId` is the product id. Possible values are `68b0a517-ebee-4e75-a514-ccba8f4af4ba` and `3c56bd40-9c2d-4c96-b130-d836d29bcd52` (please check `src/resources/data.sql`)
  * `amount` is the quantity to buy. It should be a value greater than 0

## Possible improvements
* Introduce logging
* Add more tests
* Adopt a more modular architecture (like hexagonal), to reduce coupling between layers and to be framework-agnostic