package ForReslivTestBot.Bot.Repository;

import ForReslivTestBot.Bot.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City getCityByName(String name);

    boolean existsByName(String name);
}
