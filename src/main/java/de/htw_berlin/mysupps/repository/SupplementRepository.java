package de.htw_berlin.mysupps.repository;

import de.htw_berlin.mysupps.model.Supplement;
import org.springframework.data.repository.CrudRepository;

public interface SupplementRepository extends CrudRepository<Supplement, Long> {

    Iterable<Supplement> findByNameContainingIgnoreCase(String name);

    Iterable<Supplement> findByCategoryIgnoreCase(String category);

    Iterable<Supplement> findByTaken(boolean taken);

    Iterable<Supplement> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
            String name,
            String category
    );

    Iterable<Supplement> findByNameContainingIgnoreCaseAndTaken(
            String name,
            boolean taken
    );

    Iterable<Supplement> findByCategoryIgnoreCaseAndTaken(
            String category,
            boolean taken
    );

    Iterable<Supplement> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseAndTaken(
            String name,
            String category,
            boolean taken
    );
}
