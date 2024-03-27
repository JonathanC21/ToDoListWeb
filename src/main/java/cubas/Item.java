package cubas;

public class Item {

    private String itemName;
    private int itemID;

    /**
     * Constructor for Item object. Sets the Item's name
     * @param name name of the new Item object
     */
    public Item(String name) {

        setItemName(name);
    }

    /**
     * Setter for Item name.
     * @param name name to set for Item
     */
    public void setItemName(String name){

        this.itemName = name;
    }

    /**
     * Returns the Item's name.
     * @return the Item name
     */
    public String getItemName(){

        return itemName;
    }
    public void setItemID(int id){

        this.itemID = id;
    }

    /**
     * Returns the Item's name.
     * @return the Item name
     */
    public int getItemID(){

        return itemID;
    }



}
