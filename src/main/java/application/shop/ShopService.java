package application.shop;

import java.util.List;

import application.shop.medicine.IMedicineRepository;
import application.shop.medicine.Medicine;

public class ShopService implements IShop {

    private IMedicineRepository medicineRepository;

    @Override
    public boolean add(Medicine medicine) {
       return medicineRepository.add(medicine);
    }

    @Override
    public void setMedicineRepository(IMedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public boolean delete(Medicine medicine) {
        return medicineRepository.deleteByName(medicine.getName());
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.getAll();
    }
    
}
