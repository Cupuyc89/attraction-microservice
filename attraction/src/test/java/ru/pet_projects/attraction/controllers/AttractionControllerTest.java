package ru.pet_projects.attraction.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.attraction.entities.Attraction;
import ru.pet_projects.attraction.entities.KindOfAttraction;
import ru.pet_projects.attraction.repository.AttractionRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AttractionController.class)
class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AttractionRepository attractionRepository;

    @Test
    void getAttractionById() throws Exception {
        Attraction attraction = new Attraction(1L, "Name", LocalDate.EPOCH, "Description",
                KindOfAttraction.OTHER, null, null);
        when(attractionRepository.findById(1L)).thenReturn(Optional.of(attraction));

        mockMvc.perform(get("/attraction/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.creationDate").value(LocalDate.EPOCH.toString()))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.kindOfAttraction").value(KindOfAttraction.OTHER.toString()));
    }
}
