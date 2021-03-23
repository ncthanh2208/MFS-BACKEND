package com.dxc.fresher.file.services;


import com.dxc.fresher.file.api.UserApi;
import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.exceptions.ApiRequestException;
import com.dxc.fresher.file.models.FileModel;
import com.dxc.fresher.file.models.UserModel;
import com.dxc.fresher.file.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class FileStorageServiceImlp implements FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserApi userApi;


    @Override
    @Transactional
    public int save(MultipartFile file, String userName) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        File fileDb = new File();
         UserModel user = userApi.findByUserName(userName);
        try {
        if(user.getLevel().equals("Gold")&& file.getSize() < 20971520) {
            fileDb.setData(file.getBytes());
            fileDb.setUserName(userName);
            fileDb.setName(filename);
            fileDb.setType(file.getContentType());
            fileDb.setSize(file.getSize());
            fileRepository.save(fileDb);
            return fileDb.getId();
        }
        if(user.getLevel().equals("Silver") && file.getSize() < 10485760){
            fileDb.setData(file.getBytes());
            fileDb.setUserName(userName);
            fileDb.setName(filename);
            fileDb.setType(file.getContentType());
            fileDb.setSize(file.getSize());
            fileRepository.save(fileDb);
           long size = fileRepository.sumSizeFile(userName)+file.getSize();
           if(size >= 52428800){
               UserModel userModel = userApi.findByUserName(userName);
               userModel.setLevel("Gold");
               userApi.updateUser(userModel,userModel.getId());
           }
                    return fileDb.getId();
        }
      if(user.getLevel().equals("Bronze") && file.getSize()< 5242880){
            fileDb.setData(file.getBytes());
            fileDb.setUserName(userName);
            fileDb.setName(filename);
            fileDb.setType(file.getContentType());
            fileDb.setSize(file.getSize());
            fileRepository.save(fileDb);
          long size = fileRepository.sumSizeFile(userName)+file.getSize();
          if(size >= 20971520 ){
              UserModel userModel = userApi.findByUserName(userName);
              userModel.setLevel("Silver");
              userApi.updateUser(userModel,userModel.getId());
          }
                return fileDb.getId();
        }

        } catch (ApiRequestException | IOException e) {
            throw new ApiRequestException("FAIL!!!");
        }
        return 0;
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
    public int countFileByCategory(String category) {
        return fileRepository.countFileByCategory(category);
    }

    @Override
    @Transactional
    public int countFileByUserName(String userName) {
        return fileRepository.countByFileUserName(userName);
    }

    @Override
    @Transactional
    public int countAllFile() {
        return fileRepository.countAllFile();
    }

    @Override
    @Transactional
    public List<FileModel> getAll(int page) {
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        return fileRepository.findAllFile(offset);
    }

    @Override
    @Transactional
    public List<FileModel> searchByCategory(String category,int page) {
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        return fileRepository.findFileByCategory(category,offset);
    }

    @Override
    @Transactional
    public List<FileModel> searchByUserName(String userName,int page) {
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        return fileRepository.findByFileUserName(userName,offset);
    }

    @Override
    @Transactional
    public File downFile(int id,String userName) {
        File fileDb = fileRepository.findById(id).get();
        UserModel userModel = userApi.findByUserName(userName);
        if ("Gold".equals(userModel.getLevel())) {
            return fileDb;
        }
        if ("Silver".equals(userModel.getLevel())) {
            String s = String.valueOf(LocalDate.now());
            String s1 = String.valueOf(userModel.getDate());
            if (!s1.equals(s)) {
                userModel.setDate(LocalDate.now());
                userModel.setDailyLimit(0L);
                userApi.updateUser(userModel, userModel.getId());
            }
            Long total = userModel.getDailyLimit() + fileDb.getSize();
            if (total <= 52428800) {
                userModel.setDailyLimit(total);
                userApi.updateUser(userModel, userModel.getId());
                return fileDb;
            }
        }
        if ("Bronze".equals(userModel.getLevel())) {
            String s = String.valueOf(LocalDate.now());
            String s1 = String.valueOf(userModel.getDate());
            if (!s1.equals(s)) {
                userModel.setDate(LocalDate.now());
                userModel.setDailyLimit(0L);
                userApi.updateUser(userModel, userModel.getId());
            }
            Long total = userModel.getDailyLimit() + fileDb.getSize();
            if (total <= 20971520) {
                userModel.setDailyLimit(total);
                userApi.updateUser(userModel, userModel.getId());
                return fileDb;
            }
        }
        throw new ApiRequestException("Level qua thap nha cu");
    }

    @Override
    @Transactional
    public void deleteFileById(int id) {
        fileRepository.deleteById(id);
    }

}
