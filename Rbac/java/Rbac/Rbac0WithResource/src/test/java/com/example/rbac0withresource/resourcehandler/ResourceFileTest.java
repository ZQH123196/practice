package com.example.rbac0withresource.resourcehandler;

import com.example.rbac0withresource.dao.entity.ResourceFile;
import com.example.rbac0withresource.dao.entity.Permission;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class ResourceFileTest {


    String dirPath = "./";
    String fileName = "123.txt";

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(dirPath+fileName));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test() throws IOException {
        Permission permission = new Permission();
        permission.setExpression("crud");


        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setDirPath(dirPath);
        resourceFile.setFileName(fileName);

        String expression = permission.getExpression();
        ResourceFileHandler resourceFileHandler = ResourceFileHandler
                .builder()
                .permEl(expression)
                .resource(resourceFile)
                .build();


        // c
        boolean createRes = resourceFileHandler.createFile();
        boolean createOverrideRes = resourceFileHandler.createFileOverride();
        // u
        long writeRes = resourceFileHandler.writeFile("1".getBytes(StandardCharsets.UTF_8));
        long appendRes = resourceFileHandler.appendFile("2".getBytes(StandardCharsets.UTF_8));
        // r
        byte[] readRes = resourceFileHandler.readFile();
        // d
        boolean deleteRes = false;
        try {
            deleteRes = resourceFileHandler.deleteFile();
        } catch (RuntimeException ignored) {
        }


        File file = resourceFileHandler.unsafeOpt.GetTargetFile();

        Assertions.assertTrue(createRes);
        Assertions.assertTrue(createOverrideRes);
        Assertions.assertEquals(1, writeRes);
        Assertions.assertEquals(2, appendRes);
        Assertions.assertEquals(2, readRes.length);

        Assertions.assertTrue(deleteRes);


    }

}