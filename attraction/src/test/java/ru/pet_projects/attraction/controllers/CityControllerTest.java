package ru.pet_projects.attraction.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.attraction.entities.Attraction;
import ru.pet_projects.attraction.entities.City;
import ru.pet_projects.attraction.entities.KindOfAttraction;
import ru.pet_projects.attraction.repository.CityRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CityRepository cityRepository;

    @Test
    void getByIdTest() throws Exception{
        Attraction attraction = new Attraction(1L, "Attraction",
                LocalDate.EPOCH, "attraction", KindOfAttraction.OTHER,
                null, List.of());

        City city = new City(1L, "Name",
                123456, List.of(attraction), true);
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        mockMvc.perform(get("/city/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.population").value(123456))
                .andExpect(jsonPath("$.attractionList[0].name")
                            .value("Attraction"))
                .andExpect(jsonPath("$.attractionList[0].id")
                            .value(1))
                .andExpect(jsonPath("$.hasSubway").value(true));
    }


    @Test
    void createCityTest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Attraction attraction = new Attraction(1L, "Attraction",
                LocalDate.EPOCH, "attraction", KindOfAttraction.OTHER,
                null, null);

        City cityJson = new City(null, "Name",
                123456, List.of(attraction), true);

        City city = new City(1L, "Name", 123456,
                List.of(attraction), true);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        mockMvc.perform(post("/city/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cityJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.population").value(123456))
                .andExpect(jsonPath("$.attractionList[0].name")
                        .value("Attraction"))
                .andExpect(jsonPath("$.attractionList[0].id")
                        .value(1))
                .andExpect(jsonPath("$.hasSubway").value(true));
    }

    @Test
    void updateCityTest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Attraction attraction = new Attraction(1L, "Attraction",
                LocalDate.EPOCH, "attraction", KindOfAttraction.OTHER,
                null, null);

        City oldCity = new City(1L, "Names",
                1234561, List.of(attraction), false);
        when(cityRepository.findById(1L)).thenReturn(Optional.of(oldCity));

        City city = new City(1L, "Name", 123456,
                List.of(attraction), true);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        mockMvc.perform(put("/city/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.population").value(123456))
                .andExpect(jsonPath("$.hasSubway").value(true));
    }
}
