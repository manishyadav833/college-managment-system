package com.college.website.controller;
import com.college.website.entity.Achievement;
import com.college.website.entity.Gallery;
import com.college.website.repository.AchievementRepository;
import com.college.website.repository.ContactMessageRepository;
import com.college.website.repository.GalleryRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.college.website.entity.Notice;
import com.college.website.repository.NoticeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    // ADMIN LOGIN PAGE
    @GetMapping("/admin")
    public String adminLogin(){

        return "admin-login";
    }
    // DASHBOARD
    @GetMapping("/admin/dashboard")

    public String adminDashboard(Model model){

        model.addAttribute(
                "notices",
                noticeRepository.findAll()
        );

        model.addAttribute(
                "messages",
                contactMessageRepository.findAll()
        );

        return "admin-dashboard";
    }

    // ADD NOTICE
    @PostMapping("/admin/add-notice")
    public String addNotice(@RequestParam String title,
                            @RequestParam String description){

        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setDescription(description);

        noticeRepository.save(notice);

        return "redirect:/admin/dashboard";
    }

    // DELETE NOTICE
    @PostMapping("/admin/delete-notice/{id}")
    public String deleteNotice(@PathVariable Long id){

        noticeRepository.deleteById(id);

        return "redirect:/admin/dashboard";
    }
    @PostMapping("/admin/delete-achievement/{id}")

    public String deleteAchievement(@PathVariable Long id){

        achievementRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/admin/add-achievement")
    public String addAchievement(

            @RequestParam String title,
            @RequestParam String description,
            @RequestParam("imageFile") MultipartFile imageFile

    ) {

        try {

            // IMAGE NAME
            String fileName = imageFile.getOriginalFilename();

            // SAVE PATH
            String uploadDir =
                    "src/main/resources/static/images/";

            Path path = Paths.get(uploadDir + fileName);

            // SAVE IMAGE
            Files.write(path, imageFile.getBytes());

            // SAVE DB
            Achievement achievement = new Achievement();

            achievement.setTitle(title);
            achievement.setDescription(description);
            achievement.setImage(fileName);

            achievementRepository.save(achievement);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/admin/dashboard";
    }
    @GetMapping("/admin/messages")
    public String viewMessages(Model model){

        model.addAttribute("messages",
                contactMessageRepository.findAll());

        return "admin-messages";
    }
    @GetMapping("/admin/gallery")
    public String galleryPage(Model model){

        model.addAttribute("galleryList",
                galleryRepository.findAll());

        return "admin-gallery";
    }
    @PostMapping("/admin/upload-gallery")
    public String uploadGallery(@RequestParam("title") String title,
                                @RequestParam("imageFile") MultipartFile imageFile) {
        try {

            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            System.out.println(uploadDir);
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis()+".jpg";

            Path path = Paths.get(uploadDir , fileName);

            Files.write(path, imageFile.getBytes());

            Gallery gallery = new Gallery();

            gallery.setTitle(title);

            gallery.setImageName(fileName);

            galleryRepository.save(gallery);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/gallery";
    }
}