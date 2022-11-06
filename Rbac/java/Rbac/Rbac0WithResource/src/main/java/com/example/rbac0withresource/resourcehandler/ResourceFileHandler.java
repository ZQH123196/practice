package com.example.rbac0withresource.resourcehandler;

import com.example.rbac0withresource.dao.entity.ResourceFile;
import com.example.rbac0withresource.dao.entity.ResourceFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResourceFileHandler {

        private ResourceFile resourceFile;
        private String permEl;
        private File file;
        UnsafeOpt unsafeOpt;


        public boolean createFile() throws IOException {
                if (file.exists()) {
                        throw new FileAlreadyExistsException("无法创建，文件["+ file.getName() +"]已经存在！");
                }
                return file.createNewFile();
        }

        /**
         * 在重启系统时，应当扫描尾缀为 .tmp 的文件，并进行重命名。
         * 这是为了避免极端情况：
         * 假设在临时文件创建完成，原有文件尚未删除的时刻，突然宕机，这就会导致文件的修改失败。
         * 特别是在创建大文件的时候，很可能会出现这种问题。
         * @return
         * @throws IOException
         */
        public boolean createFileOverride() throws IOException {
                File tmpFile = new File(file.getAbsolutePath() + "." + new Date().getTime() + ".tmp");
                tmpFile.createNewFile();
                file.delete();
                return tmpFile.renameTo(file);
        }

        public byte[] readFile() throws IOException {
                if (!permEl.contains("r")) {
                        throw new RuntimeException("无权限！");
                }
                return Files.readAllBytes(file.toPath());
        }

        public long writeFile(byte[] bytes) throws IOException {
                if (!permEl.contains("u")) {
                        throw new RuntimeException("无权限！");
                }
                Path path = Files.write(file.toPath(), bytes, StandardOpenOption.TRUNCATE_EXISTING);
                return Files.size(path);
        }

        public long appendFile(byte[] bytes) throws IOException {
                if (!permEl.contains("u")) {
                        throw new RuntimeException("无权限！");
                }
                Path path = Files.write(file.toPath(), bytes, StandardOpenOption.APPEND);
                return Files.size(path);
        }

        public boolean deleteFile() throws IOException {
                if (!permEl.contains("d")) {
                        throw new RuntimeException("无权限！");
                }
                return Files.deleteIfExists(file.toPath());
        }




        /**
         * 不安全的操作，使用可能会导致越权行为
         */
        static class UnsafeOpt {

                File file;

                UnsafeOpt(File file) {
                        this.file = file;
                }

                public File GetTargetFile() {
                        return this.file;
                }
        }

        private ResourceFileHandler(ResourceFile resourceFile, String permEl, File file, UnsafeOpt unsafeOpt) {
                this.resourceFile = resourceFile;
                this.permEl = permEl;
                this.file = file;
                this.unsafeOpt = unsafeOpt;
        }

        public static ResourceFileHandlerBuilder builder() {
                return new ResourceFileHandlerBuilder();
        }

        public static class ResourceFileHandlerBuilder {
                private ResourceFile resourceFile;
                private String permEl;
                private File file;
                private UnsafeOpt unsafeOpt;

                ResourceFileHandlerBuilder() {
                }

                public ResourceFileHandlerBuilder resource(ResourceFile resourceFile) {
                        this.resourceFile = resourceFile;
                        checkNameSafe(resourceFile.getFileName());
                        String filePath = resourceFile.getDirPath() + File.separator + resourceFile.getFileName();
                        this.file = new File(filePath);
                        this.unsafeOpt = new UnsafeOpt(this.file);
                        return this;
                }

                // windows 下的正则适配，因为 windows 的换行符是正则的关键字
                static String patternSep = File.separator.equals("\\") ? "\\\\" : File.separator;
                static Pattern safePatternName = Pattern.compile(patternSep);
                private void checkNameSafe(String fileName) {
                        Matcher matcher = safePatternName.matcher(fileName);
                        if (matcher.find()) {
                                throw new RuntimeException("文件名带非法字符！");
                        }
                }

                public ResourceFileHandlerBuilder permEl(String permEl) {
                        this.permEl = permEl;
                        return this;
                }

                public ResourceFileHandler build() {
                        return new ResourceFileHandler(resourceFile, permEl, file, unsafeOpt);
                }

                public String toString() {
                        return "ResourceFileHandler.ResourceFileHandlerBuilder(resource=" + this.resourceFile + ", permEl=" + this.permEl + ", file=" + this.file + ", unsafeOpt=" + this.unsafeOpt + ")";
                }
        }


}
