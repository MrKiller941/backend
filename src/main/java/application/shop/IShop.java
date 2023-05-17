package application.shop;

import java.util.List;

import application.shop.medicine.IMedicineRepository;
import application.shop.medicine.Medicine;

public interface IShop {
    boolean add(Medicine medicine);
    boolean delete(Medicine medicine);
    List<Medicine> getAll();
    void setMedicineRepository(IMedicineRepository medicineRepository);
}
