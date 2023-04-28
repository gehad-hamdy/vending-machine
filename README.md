# vending-machine

## Prerequisites
* JDK 17. [Amazon Correto](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) is preferable.
* PostgresSQL 10+.

## Running Locally
1. Make sure the IDE is using JDK 17. (IntelliJ: File > Project Structure...> Project Settings > Project > Project SKD).
2. Make sure the IDE is delegating build actions to Maven. (IntelliJ: Open Preferences > Build, Execution, Deployment > Build Tools > Maven > Runner and check Delegate IDE build/run actions to Maven.)
2. Create file `application-default.properties` in the root of the project and add these lines to
   it with the correct DB parameters:
    ```properties
    ## Datasource
    spring.datasource.url=jdbc:postgresql://localhost:5432/flapkap
    spring.datasource.username=username
    spring.datasource.password=password
    ``` 

### flapkap DB
Run `psql postgres` and execute these SQL statements:

```sql
create role flapkap with login password 'flapkap';
create database flapkap with owner flapkap;
```
### Running APIs collection
* You will find the Postman collection (vending-machine.postman_collection.json) in the project path to test the API's