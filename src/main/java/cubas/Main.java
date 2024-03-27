package cubas;

import cubas.entity.ItemEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();
    static  ArrayList<Item> toDoList = new ArrayList<>();

    public static void main(String[] args) {

        //ArrayList<Item> toDoList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int userSelectionInput = 1;
        String userNameInput;
        int userPositionInput = 0;

        displayWelcomeMessage();
        getDatabaseList();

        while(userSelectionInput != 0){

            displayUserOptions();
            userSelectionInput = getUserSelection(scan);

            switch (userSelectionInput){

                case 0:
                    break;
                case 1:
                    System.out.println("Type in name of item");
                    userNameInput = scan.next();
                    addItem(userNameInput);
                    break;
                case 2:
                    if (toDoList.isEmpty()){

                        System.out.println("List is empty\n");
                        break;
                    }
                    viewToDoListFromDatabase();
                    System.out.println("Type in position of item to delete from list");
                    userPositionInput = scan.nextInt();
                    deleteItem(userPositionInput);
                    break;
                case 3:
                    viewToDoListFromDatabase();
                    break;
            }
        }
        System.out.println("\nQuitting application...");
        entityManager.close();
        entityManagerFactory.close();
        System.exit(0);
    }

    /**
     * Adds an Item object to an Item ArrayList.
     * @param name the name of the new Item
     */
    public static void addItem(String name){


        addItemToDatabase(name);
        System.out.println("Added item: " + name + "\n");

    }

    /**
     * Adds an item to an MySQL database
     * @param name the name of the item to add to the database
     */
    public static void addItemToDatabase(String name) {

        try{
            Item item = new Item(name);
            ItemEntity newItem = new ItemEntity();
            newItem.setName(item.getItemName());
            transaction.begin();
            entityManager.persist(newItem);
            transaction.commit();
        } finally {

            if (transaction.isActive()){

                transaction.rollback();
            }
        }
    }

    /**
     * Removes an item from the database based on its ID
     * @param id the id to remove
     */
    public static void removeItemFromDatabase(int id) {

        try{
            ItemEntity newItem = new ItemEntity();
            newItem.setId(id);
            transaction.begin();
            entityManager.remove(entityManager.contains(newItem) ? newItem : entityManager.merge(newItem));
            entityManager.createNativeQuery("ALTER TABLE to_do_list.item AUTO_INCREMENT = 0");
            transaction.commit();
        } finally {

            if (transaction.isActive()){

                transaction.rollback();
            }
        }
    }

    public static void getDatabaseList(){
        try{

            transaction.begin();
            Query getTableID = entityManager.createNativeQuery("SELECT ID from to_do_list.item");
            Query getTableName = entityManager.createNativeQuery("SELECT name from to_do_list.item");
            List IDList = getTableID.getResultList();
            List nameList = getTableName.getResultList();
            toDoList.clear();
            for (int i = 0; i < IDList.size();i++){
                Item newItem = new Item((String) nameList.get(i));
                newItem.setItemID((Integer) IDList.get(i));
                System.out.println("ID-" + newItem.getItemID() + " Name-" + newItem.getItemName());
                toDoList.add(newItem);

            }

            transaction.commit();
        } finally {

            if (transaction.isActive()){

                transaction.rollback();
            }
        }
    }


    /**
     * Returns the user's int input.
     * If a non int input is sent, then it notifies the user and tries again.
     * @param scan the scanner object used to get the input
     * @return the int that the user inputted
     */
    public static int getUserSelection(Scanner scan){
        boolean correctInput = false;
        int userSelectionInput = 0;

        while (!correctInput) {
            try {

                userSelectionInput = scan.nextInt();
                correctInput = true;
            } catch (InputMismatchException e) {

                scan.next();
                System.out.println("Invalid input");
            }
        }
        return userSelectionInput;
    }

    /**
     * Deletes an Item from an Item ArrayList.
     * Finds the item by position in the list . If the Item is not found in the list, the user is notified.
     * @param id the index of the Item to delete
     */
    public static void deleteItem(int id){

        removeItemFromDatabase(id);
        System.out.println("Item ID " + id +" has been removed from the list\n");
    }

    /**
     * Prints out a list of Items in an Item ArrayList.
     */
    public static void viewToDoListFromDatabase(){

        System.out.println("    To-Do List   ");
        System.out.println("-----------------");

        getDatabaseList();
        System.out.println();
    }

    /**
     * Prints out a fancy message when the application begins.
     */
    public static void displayWelcomeMessage(){

        System.out.println("To-Do Application");
        System.out.println("-----------------");
    }

    /**
     * Prints out valid input options for the user to type.
     */
    public static void displayUserOptions(){

        System.out.println("Type 1 to add item");
        System.out.println("Type 2 to delete item");
        System.out.println("Type 3 to view item list");
        System.out.println("Type 0 to quit the application\n");
    }

    public static ArrayList<Item> getToDoList(){
        getDatabaseList();
        return toDoList;
    }

}