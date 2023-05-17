package application.shop.medicine;

public class Medicine {
    
    private int cost;
    private String name;
    private int count;
    private String img;

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
