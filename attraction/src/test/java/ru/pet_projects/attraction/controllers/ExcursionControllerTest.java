package ru.pet_projects.attraction.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.attraction.entities.Excursion;
import ru.pet_projects.attraction.repository.ExcursionRepository;

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
                "Description", null);
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        mockMvc.perform(get("/excursion/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void createTest() throws Exception{

        Excursion excursionJson = new Excursion(null, "Name",
                "Description", null);

        Excursion excursion = new Excursion(1L, "Name",
                "Description",null);
        when(excursionRepository.save(any(Excursion.class))).thenReturn(excursion);

        mockMvc.perform(post("/excursion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(excursionJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void updateTest() throws Exception{

        Excursion excursionOld = new Excursion(1L, "Names",
                "Descriptions", null);
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursionOld));

        Excursion excursion = new Excursion(1L, "Name",
                "Description",null);
        when(excursionRepository.save(any(Excursion.class))).thenReturn(excursion);

        mockMvc.perform(put("/excursion/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(excursion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void deleteTest() throws Exception{
        Excursion excursion = new Excursion(1L, "Name",
                "Description", null);
        when(excursionRepository.findById(1L)).thenReturn(Optional.of(excursion));

        doNothing().when(excursionRepository).deleteById(1L);
        mockMvc.perform(delete("/excursion/1"))
                .andExpect(status().isNoContent());

        verify(excursionRepository).deleteById(1L);
    }
}
