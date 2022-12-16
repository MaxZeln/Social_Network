package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
