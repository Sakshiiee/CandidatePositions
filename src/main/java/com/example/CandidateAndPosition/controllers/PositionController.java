package com.example.CandidateAndPosition.controllers;

import com.example.CandidateAndPosition.dtos.PageableResponse;
import com.example.CandidateAndPosition.dtos.PositionDto;
import com.example.CandidateAndPosition.services.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionController {


    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    //create position
    @PostMapping
    public ResponseEntity<PositionDto> createPosition(@Valid @RequestBody PositionDto positionDto) {
        PositionDto created = positionService.create(positionDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update positio
    @PutMapping("/update/{positionId}")
    public ResponseEntity<PositionDto> updatePosition(@Valid @RequestBody PositionDto positionDto,
                                                      @PathVariable Long positionId) {
        PositionDto updated = positionService.update(positionId, positionDto);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

//    // Patch for partial updation
//    @PatchMapping("/{positionId}")
//    public ResponseEntity<PositionDto> patchPosition(@RequestBody PositionDto positionDto,
//                                                     @PathVariable Long positionId) {
//        PositionDto patched = positionService.patch(positionId, positionDto);
//        return new ResponseEntity<>(patched, HttpStatus.CREATED);
//    }

    // Delete
    @DeleteMapping("/{positionId}")
    public ResponseEntity<String> deletePosition(@PathVariable Long positionId) {
        positionService.delete(positionId);
        return new ResponseEntity<>("Position deleted with given Id!", HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<PageableResponse<PositionDto>> getAllPositions(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "positionName", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<PositionDto> all = positionService.getAllPositions(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all, HttpStatus.CREATED);
    }

    // Get By ID
    @GetMapping("/{positionId}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable Long positionId) {
        PositionDto position = positionService.getById(positionId);
        return new ResponseEntity<>(position, HttpStatus.CREATED);
    }

    // Get By Name
    @GetMapping("/name/{positionName}")
    public ResponseEntity<PositionDto> getPositionByName(@PathVariable String positionName) {
        PositionDto position = positionService.getByPositionName(positionName);
        return new ResponseEntity<>(position, HttpStatus.CREATED);
    }
}