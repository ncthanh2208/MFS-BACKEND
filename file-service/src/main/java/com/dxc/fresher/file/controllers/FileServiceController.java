package com.dxc.fresher.file.controllers;

import com.dxc.fresher.file.api.UserApi;
import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.exceptions.ApiRequestException;
import com.dxc.fresher.file.models.FileModel;
import com.dxc.fresher.file.models.ReponseModel;
import com.dxc.fresher.file.models.UserModel;
import com.dxc.fresher.file.repositories.FileRepository;
import com.dxc.fresher.file.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/files")
public class FileServiceController {

    @Autowired
    private UserApi userApi;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private FileRepository fileRepository;

    private static Logger log = LoggerFactory.getLogger(File.class);

    //---------Get File By Category -----------------
    @GetMapping("/results/{category}")
    private ResponseEntity<ReponseModel> searchByCategory(@PathVariable("category") String category,@RequestParam(name = "page") int page){
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        List<FileModel> files = storageService.searchByCategory(category,offset);
        if(!files.isEmpty()){
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countFileByCategory(category));
        reponseModel.setFileModel(files);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);
        }else {
            throw new ApiRequestException(category +"not found!");
        }
    }
//---------Get File By UserName -----------------
    @GetMapping("/file/{userName}")
    private ResponseEntity<ReponseModel> searchByUserName(@PathVariable("userName") String userName,@RequestParam(name = "page") int page){
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        List<FileModel> files = storageService.searchByUserName(userName,offset);
        if(!files.isEmpty()) {
            ReponseModel reponseModel = new ReponseModel();
            reponseModel.setCount(storageService.countFileByUserName(userName));
            reponseModel.setFileModel(files);
            return ResponseEntity.status(HttpStatus.OK).body(reponseModel);
        }else {
            throw new ApiRequestException(userName +"not found!");
        }
    }
//---------Get File By Id -----------------
    @GetMapping("/getfile/{id}")
    private ResponseEntity<FileModel> getFileById(@PathVariable("id") int id){
        FileModel file = storageService.getFileById(id);
        if(file != null){
           return ResponseEntity.status(HttpStatus.OK).body(file);
       }else{
            throw new ApiRequestException("File not found!");
        }
    }
//---------- Get All File----------
    @GetMapping()
    public ResponseEntity<ReponseModel> getList(@RequestParam(name = "page") int page){
        int offset = 0;
        if(page == 1){
            offset =0;
        }
        if (page != 1){
            offset = (page-1) *6;
        }
        List<FileModel> files = storageService.getAll(offset);
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countAllFile());
        reponseModel.setFileModel(files);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);
    }

    //------------Download-----------
    @GetMapping("/{userName}/download/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("userName") String userName,@PathVariable("id") int id, HttpServletResponse response ){
        File fileDB = storageService.downFile(id);
        UserModel userModel = userApi.findByUserName(userName);
        if(userModel.getLevel().equals("Gold")){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileDB.getName() + "\"")
                .body(new ByteArrayResource(fileDB.getData()));
        }
        if(userModel.getLevel().equals("Silver")){
            String s = String.valueOf(LocalDate.now());
            String s1 = String.valueOf(userModel.getDate());
            if(!s1.equals(s)){
                userModel.setDate(LocalDate.now());
                userModel.setDailyLimit(0L);
                userApi.updateUser(userModel,userModel.getId());
            }
            Long total = userModel.getDailyLimit()+ fileDB.getSize();
            if(total <= 52428800){
                userModel.setDailyLimit(total);
                userApi.updateUser(userModel,userModel.getId());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileDB.getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileDB.getName() + "\"")
                        .body(new ByteArrayResource(fileDB.getData()));
            }
        }
        if(userModel.getLevel().equals("Bronze")){
            String s = String.valueOf(LocalDate.now());
            String s1 = String.valueOf(userModel.getDate());
            if(!s1.equals(s)){
                userModel.setDate(LocalDate.now());
                userModel.setDailyLimit(0L);
                userApi.updateUser(userModel,userModel.getId());
            }
            Long total = userModel.getDailyLimit()+ fileDB.getSize();
            if(total <= 20971520){
                userModel.setDailyLimit(total);
                userApi.updateUser(userModel,userModel.getId());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileDB.getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileDB.getName() + "\"")
                        .body(new ByteArrayResource(fileDB.getData()));
            }
        }
        throw new ApiRequestException("Download Fail!!!");
    }


    @PostMapping("/{userName}")
    public int uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("userName") String userName) {
        UserModel user = userApi.findByUserName(userName);
        if(user.getLevel().equals("Gold")&& file.getSize() < 20971520) {
            return storageService.save(file, userName);
        }
        if(user.getLevel().equals("Silver") && file.getSize() < 10485760){
            int id = storageService.save(file, userName);
           long size = storageService.sumSizeFile(userName)+file.getSize();
           if(size >= 52428800){
               UserModel userModel = userApi.findByUserName(userName);
               userModel.setLevel("Gold");
               userApi.updateUser(userModel,userModel.getId());
           }
                return id ;
        }
      if(user.getLevel().equals("Bronze") && file.getSize()< 5242880){
          int id = storageService.save(file, userName);
          long size = storageService.sumSizeFile(userName)+file.getSize();
          if(size >= 20971520 ){
              UserModel userModel = userApi.findByUserName(userName);
              userModel.setLevel("Silver");
              userApi.updateUser(userModel,userModel.getId());
          }
                return id;
        }
            throw new ApiRequestException("Upload Fails!");
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<Void> saveComment(@PathVariable("fileId") int fileId,@RequestBody File file){
        storageService.saveFileDescripton(fileId,file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{fileId}")
    public  ResponseEntity<Void> deleteFile(@PathVariable("fileId") int fileId){
        Optional<File> fileDb = fileRepository.findById(fileId);
        if(fileDb.isPresent()){
            storageService.deleteFileById(fileId);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
