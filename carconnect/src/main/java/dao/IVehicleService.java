
package dao;

import entity.Vehicle;

public interface IVehicleService {
    Vehicle getVehicleById(int vehicleId);
    boolean  getAvailableVehicles();
    void addVehicle(Vehicle vehicleData);
    boolean updateVehicle(Vehicle vehicleData);
    void removeVehicle(int vehicleId);
    boolean getallVehicle();
}
