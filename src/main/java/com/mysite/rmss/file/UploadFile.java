package com.mysite.rmss.file;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class UploadFile {

    private String uploadName; // 유저가 업로드한 파일명
    private String storedName; // 서버에 내부 관리명

    protected UploadFile() {}

    public UploadFile(String uploadName, String storedName) {
        this.uploadName = uploadName;
        this.storedName = storedName;
    }
}
