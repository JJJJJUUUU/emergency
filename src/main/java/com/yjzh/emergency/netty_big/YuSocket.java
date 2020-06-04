package com.yjzh.emergency.netty_big;

import java.io.Serializable;
import java.util.Arrays;

public class YuSocket implements Serializable{
    private int head=824;//36
    private int contentLength;
    private byte[] content;

    @Override
    public String toString() {
        return "YuSocket{" +
                "head=" + head +
                ", contentLength=" + contentLength +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}