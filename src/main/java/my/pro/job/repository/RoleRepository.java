package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByRole(String role);
}
