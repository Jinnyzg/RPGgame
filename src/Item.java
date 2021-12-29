/**
 * A class that defines all items in the world, which implements Tradable(can buy and sell)
 */
public class Item implements Tradable {
    private String name;
    private int price;
    private int requiredLevel;
    private ItemType itemType;

    public Item(ItemType itemType, String name, int price, int requiredLevel) {
        this.name = name;
        this.price = price;
        this.requiredLevel = requiredLevel;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    @Override
    public boolean buy(Hero buyer) {
        if (buyer.getMoney() < getPrice()) {
            System.out.println("not enough money...");
            return false;
        } else if (buyer.getLevel() < requiredLevel) {
            System.out.println("level is too low to buy...");
            return false;
        } else {
            buyer.updateMoney(-1 * getPrice());
            buyer.getBackpack().get(itemType).add(this);
            System.out.println(buyer.getName() + " buys " + getName() + " successfully!");
            return true;
        }
    }

    @Override
    public void sell(Hero seller) {
        seller.updateMoney((int) (getPrice() * 0.5));
        seller.getBackpack().get(itemType).remove(this);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                "& price=" + price +
                "& requiredLevel=" + requiredLevel +
                '\n';
    }
}
