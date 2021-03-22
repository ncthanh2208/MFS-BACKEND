package com.dxc.fresher.file.services;


import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.exceptions.ApiRequestException;
import com.dxc.fresher.file.models.FileModel;
import com.dxc.fresher.file.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class FileStorageServiceImlp implements FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    @Transactional
    public List<FileModel> getAll(int offset) {
        return fileRepository.findAllFile(offset);
    }

    @Override
    @Transactional
    public int save(MultipartFile file, String userName) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        File fileDb = new File();
        try {
            fileDb.setData(file.getBytes());
            fileDb.setUserName(userName);
            fileDb.setName(filename);
            fileDb.setType(file.getContentType());
            fileDb.setSize(file.getSize());
            fileRepository.save(fileDb);
            return fileDb.getId();
        } catch (ApiRequestException | IOException e) {
            throw new ApiRequestException("FAIL!!!");
        }
    }

    @Override
    @Transactional
    public void saveFileDescripton(int fileId, File file) {
        Optional<File> fileDb = fileRepository.findById(fileId);
        fileDb.get().setComment(file.getComment());
        fileDb.get().setCategory(file.getCategory());
        fileRepository.save(fileDb.get());
    }

    @Override
    @Transactional
    public FileModel getFileById(int id) {
        FileModel fileDb = fileRepository.findFileById(id);
        return fileDb;
    }

    @Override
    @Transactional
    public long sumSizeFile(String userName) {
        return fileRepository.sumSizeFile(userName);
    }

    @Override
    public int countFileByCategory(String category) {
        return fileRepository.countFileByCategory(category);
    }

    @Override
    public int countFileByUserName(String userName) {
        return fileRepository.countByFileUserName(userName);
    }

    @Override
    public int countAllFile() {
        return fileRepository.countAllFile();
    }

    @Override
    @Transactional
    public List<FileModel> searchByCategory(String category,int id) {
        return fileRepository.findFileByCategory(category,id);
    }

    @Override
    @Transactional
    public List<FileModel> searchByUserName(String userName,int id) {
        return fileRepository.findByFileUserName(userName,id);
    }

    @Override
    @Transactional
    public void deleteFileById(int id) {
        fileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public File downFile(int id) {
        return fileRepository.findById(id).get();
    }

}
