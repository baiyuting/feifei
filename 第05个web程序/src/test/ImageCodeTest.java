package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 本类是 测试生成验证码的类
 * <p>
 * 老肥先看哈
 */
public class ImageCodeTest {

    public static void main(String[] args) {
        BufferedImage bufferedImage = new BufferedImage(200, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.drawRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());//填充一个黑底
        Color[] colors = new Color[]{Color.red, Color.orange, Color.yellow, Color.green};// 设置红橙黄绿四种颜色，画四个数字上去
        graphics2D.setFont(new Font("黑体", Font.BOLD, 60));//设置子图大小
        for (int i = 1; i <= 4; i++) {//画上4个数字
            graphics2D.setColor(colors[new Random().nextInt(4)]);//随机一个颜色
            int x = (i - 1) * bufferedImage.getWidth() / 4;
            Integer num = new Random().nextInt(10);//[0,9] 之间的数字
            graphics2D.drawString(num.toString(), x, bufferedImage.getHeight());//一个数字一个数字的开始画
        }
        for (int y = 0; y <= bufferedImage.getHeight(); y++) {// 每次向下移动一个像素，将该行所有像素向左或者向右移动几个像素点 dx
            int dx = new Random().nextInt(3);
            if (new Random().nextInt(4) == 1)
                dx = -1 * dx;
            graphics2D.copyArea(0, y, bufferedImage.getWidth(), bufferedImage.getHeight(), dx, 0);
            graphics2D.setColor(Color.black);
            graphics2D.drawLine(0, y, dx, y);
        }
        for (int x = 0; x <= bufferedImage.getWidth(); x++) {//每次向右移动一个像素点，将该列的的所有像素向上或者向下移动几个像素点 dy
            int dy = new Random().nextInt(4);
            if (new Random().nextInt(3) == 1)
                dy = -1 * dy;
            graphics2D.copyArea(x, 0, 1, bufferedImage.getHeight(), 0, dy);
            graphics2D.setColor(Color.black);
            graphics2D.drawLine(x, 0, x, dy);
        }
        graphics2D.setColor(Color.white);
        int lineTimes = 10;//文本框 斜着划 10 条斜线
        for (int i = 1; i <= lineTimes; i++)
            graphics2D.drawLine(i * bufferedImage.getWidth() / lineTimes, 0, (i - 1) * bufferedImage.getWidth() / lineTimes, bufferedImage.getHeight());
        try {
            ImageIO.write(bufferedImage, "png", new File("D:\\tes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
