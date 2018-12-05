package com.sjc;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MyQRCodeImage implements QRCodeImage {

    BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
    }

    //宽
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    //高
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    //像素还是颜色
    public int getPixel(int i, int j) {
        return bufferedImage.getRGB(i,j);
    }
}
