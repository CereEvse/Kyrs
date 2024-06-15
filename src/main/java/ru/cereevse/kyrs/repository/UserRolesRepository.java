package ru.cereevse.kyrs.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cereevse.kyrs.model.UserRole;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
