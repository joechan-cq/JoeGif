package com.joe.giflibrary.model;

import java.util.ArrayList;

/**
 * Description
 * Created by chenqiao on 2016/11/11.
 */
public class GifImageBlock {
    public static final byte FLAG_IMAGE_BLOCK = 0x2c;

    private byte[] data;
    private byte header;
    private int offsetX;
    private int offsetY;
    private int imageWidth;
    private int imageHeight;
    private boolean localColorTableFlag;
    private boolean interlaceFlag;
    private boolean localSortFlag;
    private int localPixel;
    private int[] color_table;
    private byte LZWSize;
    private ArrayList<byte[]> imageEncodeData;

    public GifImageBlock() {
        header = FLAG_IMAGE_BLOCK;
        imageEncodeData = new ArrayList<>();
    }

    public int[] getColor_table() {
        return color_table;
    }

    public void setColor_table(int[] color_table) {
        this.color_table = color_table;
    }

    public ArrayList<byte[]> getImageEncodeData() {
        return imageEncodeData;
    }

    public void setImageEncodeData(ArrayList<byte[]> imageEncodeData) {
        this.imageEncodeData = imageEncodeData;
    }

    public void addImageData(byte[] imageDataBlock) {
        imageEncodeData.add(imageDataBlock);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
        if (data[0] != FLAG_IMAGE_BLOCK || data.length != 10) {
            throw new IllegalArgumentException("data bytes do not match IMAGE_BLOCK(0x2c)");
        }
        setOffsetX(((data[2] & 0xff) << 8) | data[1]);
        setOffsetY(((data[4] & 0xff) << 8) | data[3]);
        setImageWidth(((data[6] & 0xff) << 8) | data[5]);
        setImageHeight(((data[8] & 0xff) << 8) | data[7]);
        setLocalColorTableFlag((data[9] & 0b1000_0000) != 0);
        setInterlaceFlag((data[9] & 0b0100_0000) != 0);
        setLocalSortFlag((data[9] & 0b0010_0000) != 0);
        setLocalPixel(data[9] & 0x07);
    }

    public byte getHeader() {
        return header;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public boolean isLocalColorTableFlag() {
        return localColorTableFlag;
    }

    public void setLocalColorTableFlag(boolean localColorTableFlag) {
        this.localColorTableFlag = localColorTableFlag;
    }

    public boolean isInterlaceFlag() {
        return interlaceFlag;
    }

    public void setInterlaceFlag(boolean interlaceFlag) {
        this.interlaceFlag = interlaceFlag;
    }

    public boolean isLocalSortFlag() {
        return localSortFlag;
    }

    public void setLocalSortFlag(boolean localSortFlag) {
        this.localSortFlag = localSortFlag;
    }

    public int getLocalPixel() {
        return localPixel;
    }

    public void setLocalPixel(int localPixel) {
        this.localPixel = localPixel;
    }

    public void setLZWSize(byte LZWSize) {
        this.LZWSize = LZWSize;
    }

    public byte getLZWSize() {
        return LZWSize;
    }
}