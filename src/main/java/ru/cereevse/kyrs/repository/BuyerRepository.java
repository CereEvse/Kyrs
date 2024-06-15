package ru.cereevse.kyrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.cereevse.kyrs.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long>, PagingAndSortingRepository<Buyer, Long> {
}
