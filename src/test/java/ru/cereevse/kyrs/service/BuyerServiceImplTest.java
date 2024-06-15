package ru.cereevse.kyrs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Buyer;
import ru.cereevse.kyrs.repository.BuyerRepository;
import ru.cereevse.kyrs.servive.BuyerServiceImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuyerServiceImplTest {

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private BuyerServiceImpl buyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBuyer() {
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Sem");
        buyer.setSurname("Rasell");
        buyer.setTelNumber("122333456");

        // Mock the save method
        when(buyerRepository.save(buyer)).thenReturn(buyer);

        buyerService.addBuyer(buyer);

        verify(buyerRepository, times(1)).save(buyer);
    }

    @Test
    void getAllBuyers() {
        Buyer buyer1 = new Buyer();
        buyer1.setIdBuyer(1L);
        buyer1.setName("Sem");
        buyer1.setSurname("Rasell");

        Buyer buyer2 = new Buyer();
        buyer2.setIdBuyer(2L);
        buyer2.setName("Alla");
        buyer2.setSurname("Croft");

        Page<Buyer> page = new PageImpl<>(Arrays.asList(buyer1, buyer2));

        when(buyerRepository.findAll(PageRequest.of(0, 4))).thenReturn(page);

        Page<Buyer> result = buyerService.getAllBuyers(0);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(buyerRepository, times(1)).findAll(PageRequest.of(0, 4));
    }

    @Test
    void getBuyerById() {
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("Sem");
        buyer.setSurname("Rasell");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(buyer));

        Optional<Buyer> result = buyerService.getBuyerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sem", result.get().getName());
        verify(buyerRepository, times(1)).findById(1L);
    }

    @Test
    void putBuyerById() {
        Buyer existingBuyer = new Buyer();
        existingBuyer.setIdBuyer(1L);
        existingBuyer.setName("Sem");
        existingBuyer.setSurname("Rasell");

        Buyer updatedBuyer = new Buyer();
        updatedBuyer.setName("Alla");
        updatedBuyer.setSurname("Croft");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(existingBuyer));
        when(buyerRepository.save(existingBuyer)).thenReturn(existingBuyer);

        Optional<Buyer> result = buyerService.putBuyerById(1L, updatedBuyer);

        assertTrue(result.isPresent());
        assertEquals("Alla", result.get().getName());
        assertEquals("Croft", result.get().getSurname());
        verify(buyerRepository, times(1)).findById(1L);
        verify(buyerRepository, times(1)).save(existingBuyer);
    }

    @Test
    void deleteBuyerById() {
        // Mock the deleteById method
        doNothing().when(buyerRepository).deleteById(1L);

        buyerService.deleteBuyerById(1L);

        verify(buyerRepository, times(1)).deleteById(1L);
    }
}
