package com.ohgiraffers.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")                    // 메인에 적은 이름과 동일해야 한다.
    public String singleFileUpload(@RequestParam String singleFileDescription,
                                   @RequestParam MultipartFile singleFile,
                                   Model model) {

        System.out.println("singleFileDescription = " + singleFileDescription);
        System.out.println("singleFile = " + singleFile);

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();   // 폴더를 여러개 생성
        }

        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename(); // 이름을 가져옴
        String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 뒤에서 부터 . 을 찾아서 확장자와 원래 이름 분리

        System.out.println("originFileName = " + originFileName);
        System.out.println("ext = " + ext);

        // 랜덤으로 id 생성
        String savedName = UUID.randomUUID() + ext;
        System.out.println("savedName = " + savedName);

        /* 파일 저장 */
        try {          // try catch 필요
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 완료!");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패!");
        }

        return "result";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(@RequestParam String multiFileDescription,
                                  @RequestParam List<MultipartFile> multiFile,
                                  Model model) {
        System.out.println("multiFileDescription = " + multiFileDescription);
        System.out.println("multiFile = " + multiFile);

        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<FileDTO> files = new ArrayList<>();

        /* 파일명 변경 처리 후 저장 :  다중 파일 반복문 처리 */
        try {
            for (MultipartFile file : multiFile) {
                String originFileName = file.getOriginalFilename();
                System.out.println("originFileName = " + originFileName);

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                System.out.println("ext = " + ext);

                String savedName = UUID.randomUUID() + ext;
                System.out.println("savedName = " + savedName);

                /* 파일에 관한 정보 추출 후 보관 */
                files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));

                /* 파일 저장 */
                file.transferTo(new File(filePath + "/" + savedName));
            }
            /* 서버에 정해진 경로로 파일 저장이 완료 되면 List<FileDTO> 타입의 객체에 저장된 정보를 DB에 insert 한다. */
            model.addAttribute("message", "파일 업로드 완료!");
        } catch (IOException e) {
            /* 파일 저장 중간에 실패 시 이전에 저장된 파일 삭제 */
            for (FileDTO file : files) {
                new File(filePath + "/" + file.getSavedName()).delete();
            }
            model.addAttribute("message", "파일 업로드 실패!");
        }
        return "result";
    }
}
