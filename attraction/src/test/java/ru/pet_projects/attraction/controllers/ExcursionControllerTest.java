package ru.pet_projects.attraction.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.attraction.entities.Attraction;
import ru.pet_projects.attraction.entities.Excursion;
import ru.pet_projects.attraction.entities.KindOfAttraction;
import ru.pet_projects.attraction.repository.ExcursionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExcursionController.class)
class ExcursionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExcursionRepository excursionRepository;

    @Test
    void getByIdTest() throws Exception{
        Excursion excursion = new Excursion(1L, "Name",
                "Description", List.of(new Attraction(1L, "Attraction",
                LocalDate.EPOCH, "attraction", KindOfAttraction.OTHER,
                null, null)));
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        mockMvc.perform(get("/excursion/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.attractionList[0].id").value(1));
    }
}
