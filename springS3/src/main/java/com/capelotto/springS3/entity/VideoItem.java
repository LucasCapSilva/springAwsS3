package com.capelotto.springS3.entity;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VideoItem {
    private S3ObjectInputStream video;
    private String ext;
}