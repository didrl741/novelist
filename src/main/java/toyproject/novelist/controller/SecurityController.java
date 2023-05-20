package toyproject.novelist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class SecurityController {

    private static final String UPLOAD_DIRECTORY = "/"; // Set your desired upload directory path
    private static final String UPLOAD_DIRECTORY2 = "C:\\Users\\didrl\\OneDrive - 홍익대학교\\바탕 화면\\";

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload-form";
    }

    @GetMapping("/show")
    public String showAll(Model model) {

        File dir = new File(".\\src\\main\\resources\\static\\img");
        String[] filenames = dir.list();

        for (int i = 0; i < filenames.length; i++) {
            System.out.println("file: " + filenames[i]);
        }

        model.addAttribute("files", filenames);

        return "show-image2";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) throws Exception {

//        String sourceFileName = files.getOriginalFilename();
//        File destinationFile;
//        String destinationFileName;
//        String fileUrl = "C:\\Users\\didrl\\OneDrive_Hongik\\바탕 화면\\img\\";
//
//        do {
//            destinationFile = new File(fileUrl + sourceFileName);
//        } while (destinationFile.exists());
//
//        destinationFile.getParentFile().mkdirs();
//        files.transferTo(destinationFile);
//
//        return "redirect:/";

        System.out.println("post 수신 시작!!!");
        if (!file.isEmpty()) {
            try {
                // Save the uploaded image to the server
                String fileName = file.getOriginalFilename();
                String fileUrl = "C:\\Users\\didrl\\OneDrive_Hongik\\바탕 화면\\img\\";
                String fileUrl2 = ".\\src\\main\\resources\\static\\img";
                System.out.println("fileName =====" + fileName);

                File uploadedFile = new File(fileUrl2, fileName);
                System.out.println("uploadedFile =====" + uploadedFile);
                FileCopyUtils.copy(file.getBytes(), uploadedFile);

                // Create a ModelAndView to show the uploaded image
                ModelAndView modelAndView = new ModelAndView("show-image");
                modelAndView.addObject("imageName", fileName);



//                return modelAndView;
                List<String> filess = new ArrayList<>();
                filess.add(fileName);
                System.out.println("fileName =====" + fileName);
                model.addAttribute("filess", filess);
                return "show-image";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        return new ModelAndView("upload-error");
        return "show-image";
    }
}
