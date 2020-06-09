package com.tamdt.pmbackend.business.controllers.BusAlg;


import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.uploadFile.FileStorageService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class BusAlgController {

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/staffUploadExcel",method = RequestMethod.POST)
    public final ResponseEntity<?> staffUploadExcel(@RequestParam("file") MultipartFile file){
//        String fileName = fileStorageService.storeFile(file);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/fileStore/")
//                .path(fileName)
//                .toUriString();
//
//        UploadFileResponse res = new UploadFileResponse(file.getOriginalFilename(), fileName, file.getContentType(), file.getSize());

        //phan tich excel

        List<String> tempStudentList = new ArrayList<>();
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            //get header file excel
            XSSFRow rowTitle = worksheet.getRow(4);
            ConcurrentHashMap<Integer,String> mapHeader = new ConcurrentHashMap<>();
            mapHeader.put(1,rowTitle.getCell(1).toString().trim());
            mapHeader.put(2,rowTitle.getCell(2).toString().trim());
            mapHeader.put(3,rowTitle.getCell(3).toString().trim());
            mapHeader.put(4,rowTitle.getCell(4).toString().trim());
            mapHeader.put(5,rowTitle.getCell(5).toString().trim());
            mapHeader.put(6,rowTitle.getCell(6).toString().trim());
            mapHeader.put(7,rowTitle.getCell(7).toString().trim());
            mapHeader.put(8,rowTitle.getCell(8).toString().trim());
            mapHeader.put(9,rowTitle.getCell(9).toString().trim());
            mapHeader.put(10,rowTitle.getCell(10).toString().trim());
            if(!mapHeader.equals(AppConstant.headerExcelStaff)){
                //khong dung dinh dang file bieu mau
                return null;
            }


            for(int i=5;i<worksheet.getPhysicalNumberOfRows() ;i++) {
                XSSFRow row = worksheet.getRow(i);
                System.out.println(row.getCell(1).toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
