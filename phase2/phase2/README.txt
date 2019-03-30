
------------------------ALERTS.TXT------------------
alerts.txt should have lines that read "The number of (int, describing the denomination of the bill)
 Dollar Bills has fallen below (int, describing the number of bills required in the system,
 we have chosen 20 as the threshold)

 ---------------------ATMDATA.TXT--------------------


 --------------------DATA.TXT------------------------


--------------------DEPOSITS.TXT---------------------


-------------------------EMPLOYEEDATA.TXT---------------


-----------------------OUTGOING.TXT---------------------


-----------------------SUPERHERO.TXT-------------------


----------------------TRANSACTIONDATA.TXT--------------------


--------------------USERDATA.TXT------------------------


---------------------------------DESCRIPTION OF CLASSES--------------------------
1. Package Phase2

2. Package Phase2.Accounts
- includes account interface as well as the different types of accounts (Asset, Credit card,
 Cashback card, Chequing, Savings, Line of credit) which extend Account, and the AccountManager which
 stores and has access to all of the account information.

3. Package Phase2.Data

4. Package Phase2.Display

5. Package Phase2.Interfaces

6. Package Phase2.People

7. Package Phase2.Requests

8. Package Phase2.Transactions

------------------------------HOW TO RUN THE PROGRAM?----------------------------


-----------------------------HOW TO ACCESS THE BANK MANAGER, BANK TELLER ACCOUNTS?------------------
 -The Bank Manager already has an account. To access the account, username: bankmanager ; password: bestboss


-------------------------HOW TO CREATE AN USER ACCOUNT?-----------------------------
 1. Individual Account
- a request to create an account would be made in the AccountRequest class, with the user, account type, and
currency code. The account type should be individual. The user would have to first be created through a
UserRequest.

 2. Joint Account
- a request to create an account would be made in the AccountRequest class, with the user, account type, and
currency code. The account type should be a joint account. The users would have to first be created through a
UserRequest.

 ----------------------FUNCTIONALITY FOR USERS-------------------------------
 1. How to create an account?

 2. How to initiate a transaction?

 3. How to undo a transaction?

-----------------------FUNCTIONALITY FOR EMPLOYEES--------------------------
 1. Complete users' requests