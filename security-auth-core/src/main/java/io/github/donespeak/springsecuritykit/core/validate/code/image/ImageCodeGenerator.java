package io.github.donespeak.springsecuritykit.core.validate.code.image;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCode;
import io.github.donespeak.springsecuritykit.core.validate.code.ValidateCodeGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Yang Guanrong
 * @date 2020/04/05 16:50
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private int defaultWidth;
    private int defaultHeight;
    private int codeLength;
    private int expireIn;

    public ImageCodeGenerator(int defaultWidth, int defaultHeight, int codeLength, int expireIn) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        this.codeLength = codeLength;
        this.expireIn = expireIn;

        Assert.isTrue(defaultWidth > 0, "parameter `defaultWidth` should be a positive integer.");
        Assert.isTrue(defaultHeight > 0, "parameter `defaultHeight` should be a positive integer.");
        Assert.isTrue(codeLength > 0, "parameter `codeLength` should be a positive integer.");
    }

    private int getWidth(ServletWebRequest request) {
        return ServletRequestUtils.getIntParameter(request.getRequest(), "width", defaultWidth);
    }

    private int getHeight(ServletWebRequest request) {
        return ServletRequestUtils.getIntParameter(request.getRequest(), "height", defaultHeight);
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String[] codes = generateCode(codeLength);
        BufferedImage image = generateBufferedImage(getWidth(request), getHeight(request), codes);
        return new ImageCode(image, StringUtils.join(codes, ""), expireIn);
    }

    private String[] generateCode(int codeLength) {

        Random random = new Random();
        String[] codes = new String[codeLength];
        for (int i = 0; i < codeLength; i++) {
            String rand = String.valueOf(random.nextInt(10));
            codes[i] = rand;
        }
        return codes;
    }

    private BufferedImage generateBufferedImage(int width, int height, String[] codes) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < codes.length; i ++) {
            String rand = codes[i];
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return image;
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
