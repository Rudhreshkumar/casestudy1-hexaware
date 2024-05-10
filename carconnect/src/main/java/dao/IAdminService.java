package dao;



import entity.Admin;

public interface IAdminService {
    void getAdminById(String adminId);
    void getAdminByUsername(String username);
    void registerAdmin(Admin adminData);
    void updateAdmin(Admin adminData);
    void deleteAdmin(String  adminId);
}
