package ru.pet_projects.order_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.order_service.entities.Order;
import ru.pet_projects.order_service.entities.OrderLifeCycle;
import ru.pet_projects.order_service.entities.User;
import ru.pet_projects.order_service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void getUserByIdTest() throws Exception{
        User user = new User(1L, "admn", "admn@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PLACED,
                null, 1L)));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("admn"))
                .andExpect(jsonPath("$.email").value("admn@admin.com"))
                .andExpect(jsonPath("$.orders").isNotEmpty());
    }

    @Test
    void createUserTest() throws Exception{
        User userJson = new User(null, "admn", "admn@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PLACED,
                        null, 1L)));

        User user = new User(1L, "admn", "admn@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PLACED,
                        null, 1L)));
        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("admn"))
                .andExpect(jsonPath("$.email").value("admn@admin.com"))
                .andExpect(jsonPath("$.orders").isNotEmpty());
    }

    @Test
    void updateUserTest() throws Exception{
        User oldUser = new User(1L, "admn", "admn@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PLACED,
                        null, 1L)));
        when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));

        User user = new User(1L, "admin", "admin@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PAID,
                        null, 2L)));
        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@admin.com"))
                .andExpect(jsonPath("$.orders[0].excursionId").value(2));
    }

    @Test
    void deleteUserTest() throws Exception{
        User user = new User(1L, "admn", "admn@admin.com",
                List.of(new Order(1L, OrderLifeCycle.PLACED,
                        null, 1L)));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        doNothing().when(userRepository).deleteById(1L);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());

        verify(userRepository).deleteById(1L);
    }
}
