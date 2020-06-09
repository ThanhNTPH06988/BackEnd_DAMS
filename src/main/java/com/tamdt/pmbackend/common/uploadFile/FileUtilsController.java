package com.tamdt.pmbackend.common.uploadFile;


import com.tamdt.pmbackend.common.helper.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileUtilsController {

    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/uploadSignFileString", method = RequestMethod.POST)
    public String uploadSignFileString(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        return fileName;
    }

    // upload 1 file
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/fileStore/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(file.getOriginalFilename(), fileName, file.getContentType(), file.getSize());
    }

    @RequestMapping(value = "/uploadMultipleFiles", method = RequestMethod.POST)
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    private List<String> uploadMultiFile(@RequestParam("files") MultipartFile[] files){
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadSignFile(file))
                .collect(Collectors.toList());
    }

    private String uploadSignFile(MultipartFile file){
        return fileStorageService.storeFile(file);
    }

    @RequestMapping(value = "/fileStore", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<?> deleteFile(@RequestParam String fileGuiId) {
        ConcurrentHashMap<String, Object> value = new ConcurrentHashMap<String, Object>();
        try {
            fileStorageService.deleteFile(fileGuiId);
            value.put("data", 1L);
        }catch (Exception e) {
            value.put("errorMessage", ExceptionCode.buildMessage(e));
        }
        return new ResponseEntity<Object>(value, HttpStatus.OK);
    }

}
