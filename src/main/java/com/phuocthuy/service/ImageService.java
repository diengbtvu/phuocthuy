package com.phuocthuy.service;

import com.phuocthuy.model.request.ImageUploadRequest;
import com.phuocthuy.repository.ImageRepository;
import com.phuocthuy.repository.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image storeBase64(ImageUploadRequest request) {
        try {
            // Loại bỏ prefix nếu có
            String base64Content = removeBase64Prefix(request.getFile());

            // Chuyển base64 thành byte array
            byte[] imageBytes = Base64.getDecoder().decode(base64Content);

            // Tạo đối tượng Image mới
            Image image = new Image();
            image.setName("image_" + System.currentTimeMillis() + ".jpg");
            image.setType("image/jpeg");
            image.setData(imageBytes);
            image.setOwner(request.getOwner());
            image.setPublic(request.isPublic());
            image.setClassOfImage(request.getClassOfImage());
            // set now
            LocalDateTime now = LocalDateTime.now();
            image.setCreatedAt(now);

            return imageRepository.save(image);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid base64 string", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    private String removeBase64Prefix(String base64String) {
        if (base64String.contains(",")) {
            return base64String.split(",")[1];
        }
        return base64String;
    }

    private String getExtensionFromType(String contentType) {
        switch (contentType) {
            case "image/png":
                return ".png";
            case "image/jpeg":
                return ".jpg";
            default:
                return ".jpg";
        }
    }

    // Các phương thức khác của service (nếu có)
    public Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    public List<Image> getAllPublicImages() {
        return imageRepository.findByIsPublicTrue();
    }

    public List<Image> getUserImages(String owner) {
        return imageRepository.findByOwner(owner);
    }
}