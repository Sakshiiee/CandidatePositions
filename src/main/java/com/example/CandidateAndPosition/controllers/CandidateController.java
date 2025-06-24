package com.example.CandidateAndPosition.controllers;

import com.example.CandidateAndPosition.dtos.CandidateDto;
import com.example.CandidateAndPosition.dtos.PageableResponse;
import com.example.CandidateAndPosition.services.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Create
    @PostMapping
    public ResponseEntity<CandidateDto> createCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        CandidateDto created = candidateService.create(candidateDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> updateCandidate(@Valid @RequestBody CandidateDto candidateDto,
                                                        @PathVariable Long candidateId) {
        CandidateDto updated = candidateService.update(candidateId, candidateDto);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

//    // Patch
//    @PatchMapping("/{candidateId}")
//    public ResponseEntity<CandidateDto> patchCandidate(@RequestBody CandidateDto candidateDto,
//                                                       @PathVariable Long candidateId) {
//        CandidateDto patched = candidateService.patch(candidateId, candidateDto);
//        return new ResponseEntity<>(patched, HttpStatus.OK);
//    }

    // Delete
    @DeleteMapping("/{candidateId}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long candidateId) {
        candidateService.delete(candidateId);
        return new ResponseEntity<>("Candidate deleted with given Id!", HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<PageableResponse<CandidateDto>> getAllCandidates(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<CandidateDto> all = candidateService.getAllCandidates(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all, HttpStatus.CREATED);
    }

    // Get By ID
    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long candidateId) {
        CandidateDto candidate = candidateService.getById(candidateId);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    // Get By Email
    @GetMapping("/email/{email}")
    public ResponseEntity<CandidateDto> getCandidateByEmail(@PathVariable String email) {
        CandidateDto candidate = candidateService.getByEmail(email);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    // Search by Name
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<CandidateDto>> searchCandidate(@PathVariable String keywords) {
        List<CandidateDto> candidates = candidateService.search(keywords);
        return new ResponseEntity<>(candidates, HttpStatus.CREATED);
    }
}
