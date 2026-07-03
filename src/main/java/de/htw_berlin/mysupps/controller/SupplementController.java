package de.htw_berlin.mysupps.controller;

import de.htw_berlin.mysupps.model.Supplement;
import de.htw_berlin.mysupps.service.SupplementService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SupplementController {

    private final SupplementService supplementService;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping("/")
    public String test() {
        return "MySupps Backend läuft!";
    }

    @GetMapping("/supplements")
    public Iterable<Supplement> getAllSupplements() {
        return supplementService.getAllSupplements();
    }

    @GetMapping("/supplements/user/{userId}")
    public Iterable<Supplement> getSupplementsByUser(@PathVariable Long userId) {
        return supplementService.getSupplementsByUser(userId);
    }

    @GetMapping("/supplements/user/{userId}/filter")
    public Iterable<Supplement> filterSupplementsByUser(
            @PathVariable Long userId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean taken
    ) {
        return supplementService.filterSupplementsByUser(userId, query, category, taken);
    }

    @PostMapping("/supplements/user/{userId}")
    public Supplement createSupplementForUser(
            @PathVariable Long userId,
            @RequestBody Supplement supplement
    ) {
        return supplementService.saveSupplementForUser(userId, supplement);
    }

    @PutMapping("/supplements/{id}")
    public Supplement updateSupplement(
            @PathVariable Long id,
            @RequestBody Supplement supplement
    ) {
        return supplementService.updateSupplement(id, supplement);
    }

    @PatchMapping("/supplements/{id}/toggle")
    public Supplement toggleTaken(@PathVariable Long id) {
        return supplementService.toggleTaken(id);
    }

    @DeleteMapping("/supplements/{id}")
    public void deleteSupplement(@PathVariable Long id) {
        supplementService.deleteSupplement(id);
    }
}
