package ForReslivTestBot.Bot.Service;

import ForReslivTestBot.Bot.Model.City;
import ForReslivTestBot.Bot.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public City getCityByName(String name) {
        return cityRepository.getCityByName(name);
    }

    public boolean existByName(String name) {
        return cityRepository.existsByName(name);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public String addCity(String name, String information) {
        if (!cityRepository.existsByName(name)) {
            City city = new City(name, information);
            cityRepository.save(city);
            System.out.println("Added city: " + name + ", information: " + information);
            return "Сity successfully added!";
        } else {
            return "This a city exists!";
        }
    }

    public String editCity(String name, String information) {
        if (cityRepository.existsByName(name)) {
            City city = cityRepository.getCityByName(name);
            city.setInformation(information);
            cityRepository.save(city);
            System.out.println("Edited city: " + name + ", information: " + information);
            return "Сity successfully edited!";
        } else {
            return "City not found!";
        }
    }

    public String deleteCity(String name) {
        if (cityRepository.existsByName(name)) {
            City city = cityRepository.getCityByName(name);
            cityRepository.delete(city);
            System.out.println("Deleted city: " + name);
            return "Сity successfully deleted!";
        } else {
            return "City not found!";
        }
    }
}
