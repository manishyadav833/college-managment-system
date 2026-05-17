package com.college.website.controller;
import com.college.website.repository.AchievementRepository;
import com.college.website.repository.GalleryRepository;
import com.college.website.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.college.website.entity.ContactMessage;
import com.college.website.repository.ContactMessageRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    @GetMapping("/admission-process")
    public String admissionProcess() {
        return "admission-process";
    }

    @GetMapping("/required-documents")
    public String requiredDocuments() {
        return "required-documents";
    }

    @GetMapping("/fees-structure")
    public String feesStructure() {
        return "fees-structure";
    }
    @GetMapping("/contact1")
    public String contact() {
        return "contact1";
    }
    @GetMapping("/admission")
    public String admission() {
        return "admission";
    }

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("notices",
                noticeRepository.findAll());

        model.addAttribute("achievements",
                achievementRepository.findAll());
        model.addAttribute("galleryList", galleryRepository.findAll());
        return "index";
    }
}