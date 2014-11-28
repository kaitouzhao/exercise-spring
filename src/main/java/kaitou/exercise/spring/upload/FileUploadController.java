package kaitou.exercise.spring.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 赵立伟
 * Date: 2014/5/27
 * Time: 17:16
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public
    @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "You failed to upload " + name + " because the file was empty.";
        }
        BufferedOutputStream stream = null;
        try {
            byte[] bytes = file.getBytes();
            stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-upload")));
            stream.write(bytes);
            return "You successfully uploaded " + name + " into " + name + "-uploaded !";
        } catch (IOException e) {
            return "You failed to upload " + name + " => " + e.getMessage();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            }
        }
    }
}
