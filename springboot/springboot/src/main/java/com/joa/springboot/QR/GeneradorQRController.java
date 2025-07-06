package com.joa.springboot.QR;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@RestController
public class GeneradorQRController {

    @Autowired
    private GeneradorQRServicio qrCodeService;
    
    @GetMapping("/v1/qrcode")
    public void generateQRCode(HttpServletResponse response,
                               @RequestParam String text,
                               @RequestParam(defaultValue = "350") int width,
                               @RequestParam(defaultValue = "350") int height) throws Exception {
        BufferedImage image = qrCodeService.generateQRCode(text, width, height);
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}