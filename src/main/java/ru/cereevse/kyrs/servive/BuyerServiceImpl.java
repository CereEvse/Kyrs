package ru.cereevse.kyrs.servive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.cereevse.kyrs.exceptions.ExceptionHandler;
import ru.cereevse.kyrs.model.Buyer;
import ru.cereevse.kyrs.repository.BuyerRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final ExceptionHandler exceptionHandler;
    @Override
    public void addBuyer(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    @Override
    public Page<Buyer> getAllBuyers(int page) {
        int size = 4;
        return buyerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    @Override
    public Optional<Buyer> putBuyerById(Long id, Buyer updatedBuyer) {
        Optional<Buyer> existingBuyer = buyerRepository.findById(id);
        if (existingBuyer.isPresent()) {
            Buyer buyerToUpdate = existingBuyer.get();
            if (updatedBuyer.getName() != null) {
                buyerToUpdate.setName(updatedBuyer.getName());
            }
            if (updatedBuyer.getSurname() != null) {
                buyerToUpdate.setSurname(updatedBuyer.getSurname());
            }
            if (updatedBuyer.getTelNumber() != null) {
                buyerToUpdate.setTelNumber(updatedBuyer.getTelNumber());
            }
            buyerRepository.save(buyerToUpdate);
        }
        return existingBuyer;
    }


    @Override
    public void deleteBuyerById(Long id) {
        buyerRepository.deleteById(id);
    }
}
