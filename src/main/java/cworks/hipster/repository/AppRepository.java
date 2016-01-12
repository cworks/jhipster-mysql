package cworks.hipster.repository;

import cworks.hipster.domain.App;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the App entity.
 */
public interface AppRepository extends JpaRepository<App,Long> {

}
