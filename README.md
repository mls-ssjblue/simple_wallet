Problem Statement:

The user is expected to implement a springboot implementation
connected to a H2 DB with RESTful endpoints that can perform the following:
- Take in a set of discrete integers each representing a discrete cash through a REST call and
saved them into a Database (H2);
-  Through a call to the REST endpoint, print out the set of discrete integers currently in the
Database; and
- Through a call to the REST endpoint, pay a value (integer) (and updating the discrete cash in
the Database)
  
Database Schema:
- Table: WALLET
- Columns: id, value

To Run the application: Run Main method in Application.java

API:
- POST /insert: Insert an array of coins in the wallet
- GET /getWalletContents: Returns coins in wallet in sorted order
- POST /pay: Pay an amount using coins from wallet, returns updated wallet contents