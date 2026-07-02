package de.htw_berlin.mysupps.service;

import de.htw_berlin.mysupps.model.Supplement;
import de.htw_berlin.mysupps.repository.SupplementRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public Iterable<Supplement> getAllSupplements() {
        return supplementRepository.findAll();
    }

    public Iterable<Supplement> filterSupplements(String query, String category, Boolean taken) {
        boolean hasQuery = query != null && !query.isBlank();
        boolean hasCategory = category != null && !category.isBlank();
        boolean hasTaken = taken != null;

        if (hasQuery && hasCategory && hasTaken) {
            return supplementRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseAndTaken(
                    query, category, taken
            );
        }

        if (hasQuery && hasCategory) {
            return supplementRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
                    query, category
            );
        }

        if (hasQuery && hasTaken) {
            return supplementRepository.findByNameContainingIgnoreCaseAndTaken(
                    query, taken
            );
        }

        if (hasCategory && hasTaken) {
            return supplementRepository.findByCategoryIgnoreCaseAndTaken(
                    category, taken
            );
        }

        if (hasQuery) {
            return supplementRepository.findByNameContainingIgnoreCase(query);
        }

        if (hasCategory) {
            return supplementRepository.findByCategoryIgnoreCase(category);
        }

        if (hasTaken) {
            return supplementRepository.findByTaken(taken);
        }

        return supplementRepository.findAll();
    }

    public Supplement saveSupplement(Supplement supplement) {
        supplement.setTaken(false);
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
