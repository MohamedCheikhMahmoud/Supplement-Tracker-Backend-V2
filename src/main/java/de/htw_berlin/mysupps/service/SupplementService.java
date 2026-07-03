package de.htw_berlin.mysupps.service;

import de.htw_berlin.mysupps.model.Supplement;
import de.htw_berlin.mysupps.model.User;
import de.htw_berlin.mysupps.repository.SupplementRepository;
import de.htw_berlin.mysupps.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;
    private final UserRepository userRepository;

    public SupplementService(
            SupplementRepository supplementRepository,
            UserRepository userRepository
    ) {
        this.supplementRepository = supplementRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Supplement> getAllSupplements() {
        return supplementRepository.findAll();
    }

    public Iterable<Supplement> getSupplementsByUser(Long userId) {
        return supplementRepository.findByUserId(userId);
    }

    public Iterable<Supplement> filterSupplementsByUser(
            Long userId,
            String query,
            String category,
            Boolean taken
    ) {
        boolean hasQuery = query != null && !query.isBlank();
        boolean hasCategory = category != null && !category.isBlank();
        boolean hasTaken = taken != null;

        if (hasQuery && hasCategory && hasTaken) {
            return supplementRepository.findByUserIdAndNameContainingIgnoreCaseAndCategoryIgnoreCaseAndTaken(
                    userId, query, category, taken
            );
        }

        if (hasQuery && hasCategory) {
            return supplementRepository.findByUserIdAndNameContainingIgnoreCaseAndCategoryIgnoreCase(
                    userId, query, category
            );
        }

        if (hasQuery && hasTaken) {
            return supplementRepository.findByUserIdAndNameContainingIgnoreCaseAndTaken(
                    userId, query, taken
            );
        }

        if (hasCategory && hasTaken) {
            return supplementRepository.findByUserIdAndCategoryIgnoreCaseAndTaken(
                    userId, category, taken
            );
        }

        if (hasQuery) {
            return supplementRepository.findByUserIdAndNameContainingIgnoreCase(userId, query);
        }

        if (hasCategory) {
            return supplementRepository.findByUserIdAndCategoryIgnoreCase(userId, category);
        }

        if (hasTaken) {
            return supplementRepository.findByUserIdAndTaken(userId, taken);
        }

        return supplementRepository.findByUserId(userId);
    }

    public Supplement saveSupplementForUser(Long userId, Supplement supplement) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User nicht gefunden"));

        supplement.setId(null);
        supplement.setTaken(false);
        supplement.setUser(user);

        return supplementRepository.save(supplement);
    }

    public Supplement updateSupplement(Long id, Supplement updatedSupplement) {
        Supplement supplement = supplementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplement nicht gefunden"));

        supplement.setName(updatedSupplement.getName());
        supplement.setCategory(updatedSupplement.getCategory());
        supplement.setDosage(updatedSupplement.getDosage());
        supplement.setIntakeTime(updatedSupplement.getIntakeTime());
        supplement.setNotes(updatedSupplement.getNotes());
        supplement.setTaken(updatedSupplement.isTaken());

        return supplementRepository.save(supplement);
    }

    public Supplement toggleTaken(Long id) {
        Supplement supplement = supplementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplement nicht gefunden"));

        supplement.setTaken(!supplement.isTaken());
        return supplementRepository.save(supplement);
    }

    public void deleteSupplement(Long id) {
        supplementRepository.deleteById(id);
    }
}
