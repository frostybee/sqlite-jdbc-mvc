package org.bee.sqlite.controllers;

import org.bee.sqlite.helpers.DBConnectionProvider;
import org.bee.sqlite.models.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles database operations.
 * @author Sleiman R.
 */
public class CustomerController {

    private final String DATABASE_FILE_NAME = "chinook_sqlite.db";
    private final String CUST_TABLE_NAME = "Customer";
    //-- Column names
    private final String CUSTOMER_ID = "CustomerId";
    private final String FIRSTNAME = "FirstName";
    private final String LASTNAME = "LastName";
    private final String COMPANY = "Company";
    private final String ADDRESS = "Address";
    private final String CITY = "City";
    private final String STATE = "State";
    private final String COUNTRY = "Country";
    private final String POSTALCODE = "PostalCode";
    private final String PHONE = "Phone";
    private final String FAX = "Fax";
    private final String EMAIL = "Email";
    private final String SUPPORTREP_ID = "SupportRepId";

    
    /**
     * Retrieves a list of customers stored in an SQLite database table.
     *
     * @see
     * {@link https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html}
     * @return the retrieved list of customers.
     */
    public List<Customer> getCustomersList() {
        List<Customer> customers = new ArrayList<>();
        String query = "";
        //-- Step 1 & 2) Open a connection to the specified database 
        //--         and create a prepared statement for executing SQL queries.                  
        try ( Connection dbConnection = DBConnectionProvider.getInstance().getConnection(DATABASE_FILE_NAME);  Statement stmt = dbConnection.createStatement()) {
            query = String.format("SELECT * FROM %s ", CUST_TABLE_NAME);
            try ( ResultSet resSet = stmt.executeQuery(query)) {
                //--  Step 3) Process the list of records that are stored in the result set (in memory records).
                while (resSet.next()) {
                    //-- Retreive values from the current record.
                    Customer customer = new Customer();
                    customer.setCustomerId(resSet.getInt(CUSTOMER_ID));
                    customer.setFirstName(resSet.getString(FIRSTNAME));
                    customer.setLastName(resSet.getString(LASTNAME));
                    customer.setCompany(resSet.getString(COMPANY));
                    customer.setCountry(resSet.getString(COUNTRY));
                    customer.setPostalCode(resSet.getString(POSTALCODE));
                    customer.setPhone(resSet.getString(PHONE));
                    customer.setAddress(resSet.getString(ADDRESS));
                    customer.setCity(resSet.getString(CITY));
                    customer.setState(resSet.getString(STATE));
                    customer.setFax(resSet.getString(FAX));
                    customer.setEmail(resSet.getString(EMAIL));
                    customer.setSupporterId(resSet.getInt(SUPPORTREP_ID));
                    //-- Step 4) Add the retrieved customer from the result set to the list.
                    customers.add(customer);
                }
            }
        } catch (SQLException ex) {
            System.err.println("An error has occured while trying to execute the following query: " + query);
            System.err.println("Error message: " + ex);
        }
        return customers;
    }
}
