package com.dxc.fresher.file.services;

import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.models.FileModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface FileStorageService {

    public long sumSizeFile(String userName);

    public int countFileByCategory(String category);

    public int countFileByUserName(String userName);

    public int countAllFile();

    public List<FileModel> searchByCategory(String category,int id);

    public List<FileModel> searchByUserName(String userName,int id);

    public void deleteFileById(int id);

    public File downFile(int id);

    public List<FileModel> getAll(int offset);

    public int save(MultipartFile file, String userName);

    public void saveFileDescripton(int fileId, File file);

    public FileModel getFileById(int id);
}
