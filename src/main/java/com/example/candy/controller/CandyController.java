package com.example.candy.controller;

import com.example.candy.model.Candy;
import com.example.candy.service.ICandyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/candies")
public class CandyController {
    @Autowired
    private ICandyService candyService;

    @Value("${upload-path}")
    private String upload;

    @GetMapping
    public ResponseEntity<Page<Candy>> findAll(@PageableDefault(size = 8)
                                               Pageable pageable) {
        return new ResponseEntity<>(candyService.findAllPage(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart Candy candy,
                                    @RequestPart(required = false) MultipartFile image) {
        String imagePath;
        try {
            if (image != null && !image.isEmpty()) {
                imagePath = image.getOriginalFilename();
                FileCopyUtils.copy(image.getBytes(), new File(upload + imagePath));
                candy.setImagePath("/image/" + imagePath);
            } else {
                candy.setImagePath("/image/default.jpg");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        candyService.save(candy);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id,
                                    @RequestPart Candy candy,
                                    @RequestPart(required = false) MultipartFile image) {
        Optional<Candy> candyOptional = candyService.findOne(id);
        if (candyOptional.isPresent()) {
            String imagePath;
            try {
                if (image != null && !image.isEmpty()) {
                    imagePath = image.getOriginalFilename();
                    FileCopyUtils.copy(image.getBytes(), new File(upload + imagePath));
                    candy.setImagePath("/image/" + imagePath);
                } else {
                    candy.setImagePath(candyOptional.get().getImagePath());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            candy.setId(id);
            candyService.save(candy);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Candy>> delete(@PathVariable Long id) {
        Optional<Candy> candyOptional = candyService.findOne(id);
        if (candyOptional.isPresent()) {
            candyService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candy>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(candyService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Candy>> filter(@RequestParam(required = false, defaultValue = "0") Long min,
                                              @RequestParam(required = false, defaultValue = "999999999") Long max,
                                              @RequestParam(required = false, defaultValue = "") String name,
                                              @PageableDefault(size = 8) Pageable pageable) {
        return new ResponseEntity<>(candyService.filter(min, max, name, pageable), HttpStatus.OK);
    }
}
