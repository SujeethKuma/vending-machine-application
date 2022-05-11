# vending-machine-application

## Purpose

Implementation of Vending Machine application using Java/SpringBoot application.

### Prerequisites

To run the project you will need to have the following installed:

* Java 11

For information about the software versions used to build this API and a complete list of it's dependencies see build.gradle

### Running the application

 You can start the application which runs on the port 8080 from the current source files using Gradle as follows:

```
./gradlew clean bootRun
```

### Testing in Postman
You can test the below endpoints in the Postman

* Admin Endpoints

localhost:8080/admin/vendingMachine/productSlots/addNumberOfItem - PUT endpoint to add number of items to the product slot which accepts below sample request

```
{
"productSlot": "A0",
"numberOfItems": 2
}
```

localhost:8080/admin/vendingMachine/productSlots/{productSlot}/itemCount - GET endpoint which accepts product slot as path param

```
{productSlot} - "A0"

```

localhost:8080/admin/vendingMachine/productSlots/updatePrice - PUT endpoint to update price to the product slot which accepts the below sample request

```
{
"productSlot": "A0",
"priceOfItem": 2.0
}
```

localhost:8080/admin/vendingMachine/productSlots/{productSlot}/pricePerItem - GET endpoint to get the price per item in the product slot

```
{productSlot} - "A0"

```
localhost:8080/admin/vendingMachine/coins/addCoin - PUT end point to add coins to coin type which accepts the below sample request

```
{
"coinType": 0.20,
"numberOfCoins": 2
}
```

localhost:8080/admin/vendingMachine/coins/{coinType}/count - GET endpoint to get number of coins for the given coin type

```
{coinType} - 0.20

```

* Customer Endpoints

localhost:8080/user/vendingMachine/productSlots/{productSlot}/price - GET endpoint to get the price of the item in the product slot

```
{productSlot} - "A0"

```
localhost:8080/user/vendingMachine/purchaseProduct - POST endpoint to purchase the product and dispense the change which accepts the the below sample request

```
{
"productSlot": "A0",
"coins": [0.2, 0.5]
}
```

Below are the product slots available
```
A0, A1, A2, A3, A4, A5, A6, A7, A8, A9
```

Below are the coin types available

```
0.1, 0.2, 0.5, 1.0 - pences and pound
```


