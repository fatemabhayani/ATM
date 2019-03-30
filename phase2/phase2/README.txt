------------------------ALERTS.TXT------------------------
alerts.txt should contain lines that read "The number of (integer denoting denomination) dollar bills in the ATM is
less than 20." The integer denoting the denomination should be either 5, 10, 20, or 50. The CashMachine class checks
the amount of bills in the machine after bills are withdrawn, and writes to the file if necessary.

------------------------DEPOSITS.TXT------------------------
This text file represents a physical cheque that a user would have to input into the bank machine to make a deposit.
If you want to make a deposit, you should write the following text into deposits.txt:
Deposit [amount] dollars into [User.username]'s account [account number].
In this case, amount is a double representing the number of dollars in CAD. The Deposit class reads deposits.txt and
extracts the amount.

------------------------OUTGOING.TXT------------------------
This text file holds the information of a bill payment to an account outside of the ATM system. It is written to
when a user makes a Bill transaction for a specified amount. The Bill class writes the amount payed in the form:
[double amount] [currency code]
For example, "500 USD" is a valid line that can be written to outgoing.txt.

-----------------------SUPERHERO.TXT-------------------
This is a text file containing a list of random superhero names. The ATM display class reads this file to a
random line every time the display is called and uses this name when greeting new users that are logging in!

------------------------DATA TXT FILES------------------------



------------------------DESCRIPTION OF CLASSES------------------------
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