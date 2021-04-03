package com.capelotto.springS3.entity;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class fileModel {
    private S3ObjectInputStream file;
    private String ext;
}