package com.dxc.fresher.file.controllers;

import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.models.FileModel;
import com.dxc.fresher.file.models.ReponseModel;
import com.dxc.fresher.file.services.FileStorageService;
import org.apache.http.entity.ContentType;
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
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileServiceController {

    @Autowired
    private FileStorageService storageService;


    @GetMapping("/results")
    private ResponseEntity<ReponseModel> searchByName(@RequestParam(name = "name") String name,@RequestParam(name= "size") long size,@RequestParam(name = "page") int page){
        List<FileModel> fileModels = storageService.searchByNameAndSize(name,size,page);
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countFilebyNameAndSize(name,size));
        reponseModel.setFileModel(fileModels);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);
    }

    //---------Get File By Category -----------------
    @GetMapping("/results/{category}")
    private ResponseEntity<ReponseModel> searchByCategory(@PathVariable("category") String category, @RequestParam(name = "page") int page) {

        List<FileModel> files = storageService.searchByCategory(category, page);
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countFileByCategory(category));
        reponseModel.setFileModel(files);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);

    }

    //---------Get File By UserName -----------------
    @GetMapping("/file/{userName}")
    private ResponseEntity<ReponseModel> searchByUserName(@PathVariable("userName") String userName, @RequestParam(name = "page") int page) {
        List<FileModel> files = storageService.searchByUserName(userName, page);
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countFileByUserName(userName));
        reponseModel.setFileModel(files);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);

    }

    //---------Get File By Id -----------------
    @GetMapping("/getfile/{id}")
    private ResponseEntity<FileModel> getFileById(@PathVariable("id") int id) {
        FileModel file = storageService.getFileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(file);
    }

    //---------- Get All File----------
    @GetMapping()
    public ResponseEntity<ReponseModel> getList(@RequestParam(name = "page") int page) {
        List<FileModel> files = storageService.getAll(page);
        ReponseModel reponseModel = new ReponseModel();
        reponseModel.setCount(storageService.countAllFile());
        reponseModel.setFileModel(files);
        return ResponseEntity.status(HttpStatus.OK).body(reponseModel);
    }

    //------------Download-----------
    @GetMapping("/{userName}/download/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("userName") String userName, @PathVariable("id") int id, HttpServletResponse response) {
        File fileDB = storageService.downFile(id, userName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileDB.getName() + "\"")
                .body(new ByteArrayResource(fileDB.getData()));
    }


    @PostMapping(value = "/{userName}", consumes = "multipart/form-data")
    public int uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("category") String category, @RequestPart("comment") String comment,@PathVariable("userName") String userName) {
        return storageService.save(file, userName,category,comment);
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<Void> saveComment(@PathVariable("fileId") int fileId, @RequestBody File file) {
        storageService.saveFileDescripton(fileId, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable("fileId") int fileId) {
        storageService.deleteFileById(fileId);
        return ResponseEntity.ok().build();
    }

}
