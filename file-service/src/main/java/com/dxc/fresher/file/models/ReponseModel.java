package com.dxc.fresher.file.models;


import java.util.List;

public class ReponseModel {

    private int count;

    private List<FileModel> fileModel;

    public ReponseModel() {
    }

    public ReponseModel(int count, List<FileModel> fileModel) {
        this.count = count;
        this.fileModel = fileModel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FileModel> getFileModel() {
        return fileModel;
    }

    public void setFileModel(List<FileModel> fileModel) {
        this.fileModel = fileModel;
    }
}
