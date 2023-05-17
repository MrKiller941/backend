package infrastructure.jpa.medicine;

import java.io.Serializable;

import application.shop.medicine.Medicine;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"medic\"")
public class EMedicine implements Serializable {
    
    @Id
    @Column(name = "\"id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"cost\"")
    private int cost;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"count\"")
    private int count;

    @Column(name = "\"img\"")
    private String img;

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setAll(Medicine medicine){
        setName(medicine.getName());
        setCost(medicine.getCost());
        setCount(medicine.getCount());
        setImg(medicine.getImg());
    }
}
