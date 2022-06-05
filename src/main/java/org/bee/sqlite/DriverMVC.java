package org.bee.sqlite;

import org.bee.sqlite.controllers.CustomerController;
import org.bee.sqlite.models.Customer;

import java.util.List;

/**
 * A class for testing interactions with SQLite database.
 *
 * This sample demonstrates querying an SQLite database using SQLite JDBC
 * driver. As data source, the Chinook database is used in this example.
 *
 * @see {@link https://www.sqlitetutorial.net/sqlite-sample-database/}
 * @see {@link https://github.com/lerocha/chinook-database}
 * @see {@link https://github.com/xerial/sqlite-jdbc}
 *
 * @author Sleiman R.
 */
public class DriverMVC {

    public static void main(String[] args) {
        CustomerController custController = new CustomerController();
        //-- Retrieve th list of customers. 
        List<Customer> customers = custController.getCustomersList();
        printListOfCustomers(customers);
    }

    private static void printListOfCustomers(List<Customer> customers) {
        System.out.println("===========================");
        System.out.printf("Total customers found: %d \n", customers.size());
        System.out.println("===========================");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
