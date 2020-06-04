package com.yjzh.emergency.po;

import java.util.Date;

public class albumInfo {
    private Integer albumId;

    private String title;

    private String content;

    private Long userId;

    private Date publishTime;

    private Integer schoolId;

    private Integer termId;

    private Integer classId;

    private String sourceType;

    private Integer praiseNum;

    private String isAttachmented;

    private String isDeleted;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    public albumInfo(Integer albumId, String title, String content, Long userId, Date publishTime, Integer schoolId, Integer termId, Integer classId, String sourceType, Integer praiseNum, String isAttachmented, String isDeleted, Long createUser, Date createTime, Long updateUser, Date updateTime) {
        this.albumId = albumId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.publishTime = publishTime;
        this.schoolId = schoolId;
        this.termId = termId;
        this.classId = classId;
        this.sourceType = sourceType;
        this.praiseNum = praiseNum;
        this.isAttachmented = isAttachmented;
        this.isDeleted = isDeleted;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }

    public albumInfo() {
        super();
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getIsAttachmented() {
        return isAttachmented;
    }

    public void setIsAttachmented(String isAttachmented) {
        this.isAttachmented = isAttachmented == null ? null : isAttachmented.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}