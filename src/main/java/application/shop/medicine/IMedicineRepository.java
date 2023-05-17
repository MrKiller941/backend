package application.shop.medicine;

import java.util.List;

public interface IMedicineRepository {
    boolean add(Medicine medicine);
    boolean deleteByName(String name);
    List<Medicine> getAll();
}
