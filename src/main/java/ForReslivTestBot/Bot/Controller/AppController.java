package ForReslivTestBot.Bot.Controller;

import ForReslivTestBot.Bot.Model.City;
import ForReslivTestBot.Bot.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    CityService cityService;

    @GetMapping("/getAll")
    public List<City> getAll() {
        return cityService.getAll();
    }

    @PostMapping("/add/{cityName}")
    public String addCity(@PathVariable String cityName, @RequestBody String cityInformation) {
        return cityService.addCity(cityName, cityInformation);
    }

    @PostMapping("/edit/{cityName}")
    public String editCity(@PathVariable String cityName, @RequestBody String cityInformation) {
        return cityService.editCity(cityName, cityInformation);
    }

    @PostMapping("delete/{cityName}")
    public String deleteCity(@PathVariable String cityName) {
        return cityService.deleteCity(cityName);
    }
}
