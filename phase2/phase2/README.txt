------------------------BEFORE RUNNING------------------------
Before running the program, you will need to download the .jar at the following address:
http://www.java2s.com/Code/JarDownload/json-simple/json-simple-1.1.jar.zip
After this, you will need to go to Project Structure -> Project Settings -> Modules -> select the + sign and
choose "JARs or Directories", and open the .jar file you get from the link above. This is so that the convert
method in ForeignCurrency runs, since we used an API to get conversion rates between currency types.

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
data of every user in the ATM at the time the program exits. We also have a file called ListOfNames.txt which lists
the username of every user in the ATM, so that when reading the data, we can access the file for every user.


------------------------DESCRIPTION OF CLASSES------------------------
1. package phase2
- Includes the packages Accounts, Data, Display, Interfaces, People, Request, Transactions, and Tradable.
- Also includes general classes ATMTime and CashMachine. These are the classes that do not
  inherit from or relate to other packages.

2. package phase2.Accounts
- Includes Account interface as well as the different types of accounts (AssetAccount, Savings, Chequing, CreditCard,
  CashBackCard, and LineOfCredit) which extend Account.
- Also includes the AccountManager which stores and has access to the collection of one User's accounts.

3. package phase2.Data
- Includes all the program's output and input files, including alerts.txt, deposits.txt, outgoing.txt, and all
  .txt files that save the ATM data after each program run.
- Also includes DataReader, DataSaver, and WriteFile classes, which assist in reading and writing to these files.

4. package phase2.Display
- Includes all the classes with main methods that assist in the running of our program, including ATM,
  ManagerDisplay, UserDisplay, AccountDisplay, BillDisplay, DepositDisplay, Transfer Display, WithdrawDisplay.

5. package phase2.People
- Includes classes for BankManager, User, BankTeller, and the UserManager class that manages the collection of
  users of the ATM.

6. package phase2.Requests
- Includes an abstract Request class and its subclasses for UndoRequest, AccountRequest, and UserRequest.
  The requests are sent to the BankManager and BankTellers for them to perform actions such as creating new
  accounts and undoing past transactions.

7. package phase2.Transactions
- Includes an abstract Transaction class and its subclasses for the types of transactions a user can make,
  Bill, Deposit, Transfer, and Withdraw.

8. package phase2.Tradable
- Includes the ForeignCurrency and CryptoCurrency classes that represents different types of currency that users
  can make transactions with. This includes a convert method which converts to other types of currency.

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
After a user has logged in, they can request to undo a transaction of a specific account. The account stores
all of its past transactions, so it can request to undo any past transaction (by inputting an integer, where 0
represents the most recent transaction). They do this by viewing their past transactions and selecting the transaction
number to undo. An UndoRequest is sent to the bank manager, who can again either complete or ignore the request.
If the request is completed the transaction is undone, and the balance of the account that made the transaction is
changed accordingly. If the transaction was a transfer, the balance of the account that received the transfer will
also be changed. A Bill transaction cannot be undone.

------------------------HOW TO INITIATE A TRANSACTION?------------------------
After a user has logged in, they can make a transaction by first choosing the category of the account (i.e.
"sv": Savings, "cq": Chequing, "cc": CreditCard, "cb": CashBackCard, "lc": LineOfCredit), choosing a specific
account of that type, and then making a transaction from the account. The user can choose which type of transaction
they would like to make, Bill, Withdraw, Deposit, or Transfer. To make a transfer the user will need the account
number of the account they would like to transfer to. To make a deposit, deposits.txt will need to have a line of
text of the correct format.

------------------------FUNCTIONALITY FOR BANK TELLERS------------------------
1. Restocking the cash machine:
Bank tellers can read alerts.txt and restock the cash machine with bills accordingly.
2. Completing requests to undo transactions:
Bank tellers can only complete and ignore an UndoRequest. The bank tellers and bank manager share access of
a list of UndoRequests.

------------------------FUNCTIONALITY FOR BANK MANAGER------------------------
1. Restocking the cash machine:
The bank manager can read alerts.txt and restock the cash machine with bills accordingly.
2. Completing all types of requests:
The bank manager can complete and ignore any request type, and they are the only one who can complete requests to
make new users and accounts.
3. The bank manager can set the date of the ATM, specifying the year, month, day, hour, minute, and second.