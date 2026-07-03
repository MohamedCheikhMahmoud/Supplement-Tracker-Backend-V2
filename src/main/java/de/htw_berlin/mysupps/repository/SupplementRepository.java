package de.htw_berlin.mysupps.repository;

import de.htw_berlin.mysupps.model.Supplement;
import org.springframework.data.repository.CrudRepository;

public interface SupplementRepository extends CrudRepository<Supplement, Long> {

    Iterable<Supplement> findByUserId(Long userId);

    Iterable<Supplement> findByUserIdAndNameContainingIgnoreCase(Long userId, String name);

    Iterable<Supplement> findByUserIdAndCategoryIgnoreCase(Long userId, String category);

    Iterable<Supplement> findByUserIdAndTaken(Long userId, boolean taken);

    Iterable<Supplement> findByUserIdAndNameContainingIgnoreCaseAndCategoryIgnoreCase(
            Long userId,
            String name,
            String category
    );

    Iterable<Supplement> findByUserIdAndNameContainingIgnoreCaseAndTaken(
            Long userId,
            String name,
            boolean taken
    );

    Iterable<Supplement> findByUserIdAndCategoryIgnoreCaseAndTaken(
            Long userId,
            String category,
            boolean taken
    );

    Iterable<Supplement> findByUserIdAndNameContainingIgnoreCaseAndCategoryIgnoreCaseAndTaken(
            Long userId,
            String name,
            String category,
            boolean taken
    );
}
