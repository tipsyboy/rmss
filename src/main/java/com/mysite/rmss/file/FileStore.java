package com.mysite.rmss.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Configuration
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        /**
         * 유저에게 받은 이미지 파일을 서버에 저장될 파일명을 생성해서 서버에 저장하고 UploadFile 인스턴스를 반환
         */

        if (multipartFile.isEmpty()) {
            return new UploadFile("basic-image.jpg", "basic-image.jpg");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeImageName = createStoreImageName(originalFilename);

        // 파일을 서버에 저장
        multipartFile.transferTo(new File(getFullPath(storeImageName)));

        // 생성한 인스턴스 리턴
        return new UploadFile(originalFilename, storeImageName);
    }

    private String createStoreImageName(String originalFilename) {
        String ext = extractExt(originalFilename);
        UUID uuid = UUID.randomUUID();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
