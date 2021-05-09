Problem Statement:

The user is expected to implement a springboot implementation
connected to a H2 DB with RESTful endpoints that can perform the following:
- Take in a set of discrete integers each representing a discrete cash through a REST call and
saved them into a Database (H2);
-  Through a call to the REST endpoint, print out the set of discrete integers currently in the
Database; and
- Through a call to the REST endpoint, pay a value (integer) (and updating the discrete cash in
the Database)
  
#Database Schema

The entries are sorted in increasing order of value

    id, value

- Endpoint 1: GET /contents: Returns contents of wallet
- Endpoint 2: POST /pay/{amount}
    use case 1: pay exact without change
    use case 2: pay with change
  

// Read all entries from db and store in object array
sort the entries in ascending order of value
while payment >0 {
    if(obj.value >= 0 payment)
        obj.value-=payment;
        payment=0
    else 
        obj.value =0;
        payment-=obj.value
    updatedEntries.add(obj)
}

//insert updated the entries back into the db
    for(obj in updatedEntries){
           update the entries in db or delete if value =0

    }
}