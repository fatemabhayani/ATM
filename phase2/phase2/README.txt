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

------------------------SUPERHERO.TXT------------------------
This is a text file containing a list of random superhero names. The ATM display class reads this file to a
random line every time the display is called and uses this name when greeting new users that are logging in!

------------------------DATA TXT FILES------------------------
There are several files created that store the ATM data. A file called [username].txt is created to write the
data of every user in the ATM at the time the program exits. We also have a file called


------------------------DESCRIPTION OF CLASSES------------------------
1. package phase2

2. package phase2.Accounts
- includes account interface as well as the different types of accounts (Asset, Credit card,
 Cashback card, Chequing, Savings, Line of credit) which extend Account, and the AccountManager which
 stores and has access to all of the account information.

3. package phase2.Data

4. package phase2.Display

5. package phase2.People

6. package phase2.Requests

7. package phase2.Transactions

------------------------HOW TO RUN THE PROGRAM?----------------------------
Run ATM.java to run the entire program.

------------------------HOW TO ACCESS THE BANK EMPLOYEE ACCOUNTS?------------------------
The bank manager and 5 bank tellers already have accounts set up. To access the bank manager, enter
username: bankmanager, password: bestboss. To access the bank tellers, enter username: teller[i],
password: bestemployee[i], where i is an integer from 1 to 5. New bank tellers and bank managers cannot
be created in the ATM.

------------------------HOW TO CREATE AN USER?------------------------
Before logging in, the ATM will ask if the user has a pre-existing account or not. If "no" is typed, the
new user can input their desired username and password, and a UserRequest will be sent to the bank manager.
To create this account with the specified username and password, the bank manager first must log in and
complete the request. Of course the bank manager may also ignore the request, and decline to make an account
for this new user. After the bank manager has accepted the request the user can log in with their chosen username
and password. The ATM will not allow a new user to request a username that has already been taken.

------------------------HOW TO CREATE AN USER ACCOUNT?------------------------
1. Individual Account
After a user has logged in, they can request to create an account of a specific type ("sv": Savings, "cq": Chequing,
"cc": CreditCard, "cb": CashBackCard, "lc": LineOfCredit) with a primary currency type (denoted by a 3 letter
currency code, e.g. "USD", "CAD", "INR"). An AccountRequest is sent to the bank manager, who can again complete the
request, by creating and adding the specified account to the user's list of accounts, or ignoring the request.
After the bank manager completes the request, the user will see this account when they log in next and will be able to
make transactions from it.

2. Joint Account
After creating an account, a user can choose to add a second user to this account (by specifying their username),
which allows this user to view the balance and make transactions from this account. This is done by adding this
user as a second owner (owner2), and adds this account to the second user's list of accounts (stored in
AccountManager).

------------------------HOW TO UNDO A TRANSACTION?------------------------


------------------------FUNCTIONALITY FOR USERS------------------------
 1. How to create an account?

 2. How to initiate a transaction?

 3. How to undo a transaction?

------------------------FUNCTIONALITY FOR BANK TELLERS------------------------
1. Complete users' requests

------------------------FUNCTIONALITY FOR BANK MANAGER------------------------
1. Complete users' requests