package com.semantica.yada.events;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class FileUploadedEvent {

    private final UUID fileId;
    private final String name;
    private final String mimeType;
    private final Long size;
    private final Date creationDate;
    private final String hash;
    private final String originalPath;

}
