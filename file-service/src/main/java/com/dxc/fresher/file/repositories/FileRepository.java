package com.dxc.fresher.file.repositories;


import com.dxc.fresher.file.entities.File;
import com.dxc.fresher.file.models.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FileRepository extends JpaRepository<File,Integer> {

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f LIMIT 6 OFFSET :offset", nativeQuery = true)
    public List<FileModel> findAllFile(@Param("offset") int offset);

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f WHERE f.id=:fileId ", nativeQuery = true)
    public FileModel findFileById(@Param("fileId") int id);

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f WHERE f.category=:category LIMIT 6 OFFSET :offset", nativeQuery = true)
    public List<FileModel> findFileByCategory(@Param("category") String category,@Param("offset") int offset);

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f WHERE f.username=:userName LIMIT 6 OFFSET :offset", nativeQuery = true)
    public List<FileModel> findFileByUserName(@Param("userName") String userName,@Param("offset") int offset);

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f WHERE f.name LIKE %:name% LIMIT 6 OFFSET :offset", nativeQuery = true)
    public List<FileModel> findFileByName(@Param("name") String name,@Param("offset") int offset);

    @Query(value = "SELECT count(*) FROM File f WHERE f.name LIKE %:name% ", nativeQuery = true)
    public int countFileByName(@Param("name") String name);

    @Query(value = "SELECT f.id,f.category,f.comment,f.name,f.size,f.type,f.username FROM File f WHERE f.size >= :size LIMIT 6 OFFSET :offset", nativeQuery = true)
    public List<FileModel> findFileBySize(@Param("size") long size,@Param("offset") int offset);

    @Query(value = "SELECT count(*) FROM File f WHERE f.size >= :size", nativeQuery = true)
    public int countFileBySize(@Param("size") long size);

    @Query(value = "SELECT count(*) FROM File f WHERE f.category=:category", nativeQuery = true)
    public int countFileByCategory(@Param("category") String category);

    @Query(value = "SELECT count(*) FROM File f WHERE f.username=:userName", nativeQuery = true)
    public int countByFileUserName(@Param("userName") String userName);

    @Query(value = "SELECT count(*) FROM File f", nativeQuery = true)
    public int countAllFile();

    @Query(value = "SELECT sum(f.size) FROM File f WHERE f.username=:userName GROUP BY f.username ", nativeQuery = true)
    public long sumSizeFile(@Param("userName") String userName);
}
