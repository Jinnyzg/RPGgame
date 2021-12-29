
/**
 * an interface that defines the behavior of a tradable item(can buy and sell)
 */
public interface Tradable {
    boolean buy(Hero buyer);

    void sell(Hero seller);
}
