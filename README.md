# Warehouse-Management-System
A terminal based application:

# Introduction :
MiNet is a Swedish network-based Hardware Store that is getting more and more
popular since we offer high quality on our products with relatively low prices. At
the moment we are opening physical stores in Stockholm, Gothenburg and
Malmö. As we are expanding explosively, we need to have a Warehouse
Management System to organize and structure our inventory, and we hope you
can help us develop that system. Our development manager has designed the
test so that it will give us a better sense of your skills as a developer and what
approaches and work methodologies you do use to solve problems and prioritize
requirements without providing you with detailed instruction.

## Mandatory requirements:
Create an inventory.
Users are the cashier,manager and the admin.
Each one have it's different interface access depending on the role.
The inventory has different entries for each product.
There are different amounts of items of each product.
A cashier is able to sale items.
A cashier can remove item from inventory after it has been sold.
A cashier can check if an item is sold out.
A cashier makes an order on the sold out item.
A cashier can only recieve back an item from the customer if it has the recipet number.
A cashier then can search for the transaction of the returned item and delete it from the system.
At the end the item will be placed back into the inventory.
Each product always has an item that can't get sold.
A manager is able to display the order list.
A manager can review the inventory.
A manager can decide to order the item or not.
Saving a copy of order details and the transaction at the end.
The admin hires the cashiers and the managers.
The admin has access to everything.
The admin pays salaries to employees.
The admin can review the transactions, costs, profits and everything of the laast year.
The admin can view statistics of best selling items, profits, retail and purchase prices.
The admin and the managers can communicate via a chat channel.
Notify others when someone send a message.
Separate text files for saving logins, transactions, order lists, ordered items, returned items, receipts,
statistics and chat conversations.
The main menu promts user to choose its role to login with.
Only one user at a time can write into a specific file.
Automated test for each user type.
Fill the inventory with different types and amounts of items.
The system have at least one manager and two cashiers.


## Extra requirements
There should be different payment options for customers. If a user already has an
account registered in the system, they can buy on credit, otherwise they have to
pay by card or cash.
Implement a commission-based system that gives the cashiers a certain percent
of the sales the more they sell.
Create lists of related products where the user has the ability to sort them by best
selling, cheapest, amount existing in the inventory or alphabetical order. The user
may also want to filter the items by a certain price limit that the user enters.

* In this project most of the mandatory requirements have been developed.

## Java SDK version
17.0.2 (build 17.0.2 + 8)

## How to run
* Create a jar file https://www.youtube.com/watch?v=_XQjs1xGtaU.
* The jar file and the src folder must be in the same place to be able to run it.
* Use cmd (command line) to run by writing the following 
```bash
java -jar MinetWarehouseManagementSystem.jar
``` 
