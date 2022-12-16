package ru.learnup.socialnetwork.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.socialnetwork.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
