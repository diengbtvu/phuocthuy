package com.phuocthuy.controller;
import com.phuocthuy.model.request.ImageUploadRequest;
import com.phuocthuy.model.response.ImageUploadResponse;
import com.phuocthuy.repository.entity.Image;
import com.phuocthuy.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadBase64Image(
            @RequestBody ImageUploadRequest request) {
        try {
            if (request.getFile() == null || request.getFile().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ImageUploadResponse("No image data provided", null));
            }

            Image image = imageService.storeBase64(request);

            String message = "Uploaded image successfully";
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ImageUploadResponse(message, image.getId()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ImageUploadResponse("Invalid base64 string", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ImageUploadResponse("Could not upload image", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            Image image = imageService.getImage(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getName() + "\"")
                    .body(image.getData());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint để lấy ảnh dưới dạng Base64 (tùy chọn)
    @GetMapping("/{id}/base64")
    public ResponseEntity<String> getImageAsBase64(@PathVariable Long id) {
        try {
            Image image = imageService.getImage(id);
            String base64 = String.format("data:%s;base64,%s",
                    image.getType(),
                    Base64.getEncoder().encodeToString(image.getData())
            );
            return ResponseEntity.ok(base64);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}