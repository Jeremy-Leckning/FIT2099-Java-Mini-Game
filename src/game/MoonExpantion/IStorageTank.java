package game.MoonExpantion;

/**
 * Interface for a storage item. Holds only 1 element
 */
interface IStorageTank {

    /**
     * Adds 1 unit to storage tank
     * @throws Exception if tank is full
     */
    void increment() throws Exception;

    /**
     * Removes 1 unit from tank
     * @throws Exception if tank is empty
     */
    void decrement()throws Exception;

    /**
     * Determines if tank is full
     * @return true/false
     */
    boolean isFilled();

    /**
     * Determines if tank is empty
     * @return true/false
     */
    boolean isEmpty();

    Element holds();
}
