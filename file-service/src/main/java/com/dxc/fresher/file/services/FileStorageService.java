package com.dxc.fresher.file.services;

import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.models.FileModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface FileStorageService {

    public  List<FileModel> searchByNameAndSize(String name, long size, int page);

    public int countFilebyNameAndSize(String name,long size);

    public int countFileByCategory(String category);

    public int countFileByUserName(String userName);

    public int countAllFile();

    public List<FileModel> searchByCategory(String category,int page);

    public List<FileModel> searchByUserName(String userName,int page);

    public void deleteFileById(int id);

    public File downFile(int id,String userName);

    public List<FileModel> getAll(int page);

    public int save(MultipartFile file, String userName,String category,String comment);

    public void saveFileDescripton(int fileId, File file);

    public FileModel getFileById(int id);
}
