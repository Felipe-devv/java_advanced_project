package com.java.project.kebab;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.kebab.payload.AddUpdateKebabRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/kebab")
public class KebabController {
    
    @Autowired
    private KebabService kebabService;

    @GetMapping("/all")
    public ResponseEntity<?> getKebabs() {
        return ResponseEntity.ok(kebabService.getAllKebabs());
    }

    @GetMapping("/{kebabId}")
    public ResponseEntity<?> getKebabById(@PathVariable Integer kebabId) {
        try{
            return ResponseEntity.ok(kebabService.getKebabById(kebabId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addNewKebab(@RequestBody AddUpdateKebabRequest request) {
        try{
            kebabService.insertKebab(request);
            return ResponseEntity.ok("Kebab added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{kebabId}")
    public ResponseEntity<?> putMethodName(@PathVariable Integer kebabId, @RequestBody AddUpdateKebabRequest request) {
        try{
            kebabService.updateKebab(request, kebabId);
            return ResponseEntity.ok("Kebab updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{kebabId}")
    public ResponseEntity<?> deleteKebab(@PathVariable Integer kebabId) {
        try{
            kebabService.deleteKebab(kebabId);
            return ResponseEntity.ok("Kebab deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }   

    @DeleteMapping("/deactivate/{kebabID}")
    public ResponseEntity<?> deactivateKebab(@PathVariable Integer kebabID) {
        try{
            kebabService.deactivateKebab(kebabID);
            return ResponseEntity.ok("Kebab deactivated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
